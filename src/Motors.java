import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class Motors implements Runnable {
	private double target;
	private double oldTarget;
	private int range;
	private boolean mustPause;
	private boolean mustStop;
	public String internalMode;
	public NXTRegulatedMotor motor;
	public int lockTarget;
	private Thread thread;
	private boolean targetFlag;
	private char motorPort;
	
	public Motors(char motorPort, int range, int speed){
		this.motorPort = motorPort;
		if(motorPort=='A') this.motor = Motor.A;
		if(motorPort=='B') this.motor = Motor.B;
		this.range = range;
		this.motor.setAcceleration(3000);
		this.motor.setSpeed(speed);
		target = range;
		thread = new Thread(this);
		internalMode = "research";
		oldTarget=0;
	}

	public void stopThread(){
		mustPause = true;
		mustStop = true;
	}
	
	public void pauseThread(){ 
		mustPause = true;
	}
	
	public void startThread(){ 
		mustStop = false;
		mustPause = false;
		if (!thread.isAlive()) thread.start();	
	}
	
	public void computeTarget(){
		if (internalMode.equals("research")){
			targetFlag = true;
			if(oldTarget!=0){ 
				target = oldTarget;
				oldTarget = 0;
			}
			else{
				if (motor.getTachoCount()>=range) target = 0;
				else if (motor.getTachoCount()<=0) target = range;
			}
		}
		else if (internalMode.equals("lockOnTarget")){
			if(targetFlag)oldTarget = target;
			targetFlag = false;
			if(motorPort=='A')target = motor.getTachoCount() + Robot.camera.target.getX();
			else if(motorPort=='B')target = motor.getTachoCount() + Robot.camera.target.getY();
		}
	}
	public void run(){			
			while(!mustStop){
				computeTarget();
				if(!(motor.isMoving()) & !mustPause) motor.rotateTo((int) target, true);
				else if(motor.isMoving() & mustPause) motor.flt(true);
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
			}
	}
}
