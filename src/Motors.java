import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class Motors implements Runnable {
	private int target;
	private int oldTarget;
	private int range;
	private boolean mustPause;
	private boolean mustStop;
	public String internalMode;
	public NXTRegulatedMotor motor;
	public int lockTarget;
	private Thread thread;
	private boolean targetFlag;
	
	public Motors(char motor, int range, int speed){
		if(motor=='A') this.motor = Motor.A;
		if(motor=='B') this.motor = Motor.B;
		if(motor=='C') this.motor = Motor.C;
		this.range = range;
		this.motor.setAcceleration(3000);
		this.motor.setSpeed(speed);
		target = range;
		thread = new Thread(this);
	}

	public void stopThread(){
		mustPause = true;
		mustStop = true;
	}
	
	public void pauseThread(){ 
		mustPause = true;
	}
	
	public void startThread(){ 
		if (!thread.isAlive()) thread.start();
		mustStop = false;
		mustPause = false;
	}
	
	public void computeTarget(){
		if (internalMode.equals("research")){
			if(oldTarget!=0){ 
				target = oldTarget;
				oldTarget = 0;
			}
			else{
				if (motor.getTachoCount()>=range) target = 0;
				else if (motor.getTachoCount()<=0) target = range;
			}
			targetFlag = true;
		}
		else if (internalMode.equals("lockOnTarget")&targetFlag){
			oldTarget = target;
			motor.stop(true);
			targetFlag = false;
		}
	}
	public void run(){			
			while(!mustStop){
				computeTarget();
				if (internalMode.equals("research")){
					if(!(motor.isMoving()) & !mustPause) motor.rotateTo(target, true);
					else if(motor.isMoving() & mustPause) motor.flt(true);
				}
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
			}
	}
}
