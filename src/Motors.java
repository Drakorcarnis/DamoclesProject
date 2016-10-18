import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class Motors implements Runnable {
	private int target;
	private int range;
	private boolean runFlag;
	private NXTRegulatedMotor motor;
	private Thread thread;
	
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
		thread.setDaemon(true);
		runFlag = false;
	}
	
	public void pauseThread(){ 
		runFlag = false;
	}
	
	public void startThread(){ 
		thread.setDaemon(false);
		if (!thread.isAlive()) thread.start();
		runFlag = true;
	}
	
	public void run(){			
			while(true){
				if (motor.getTachoCount()>=range) target = 0;
				else if (motor.getTachoCount()<=0) target = range;
				if(!(motor.isMoving()) & runFlag) motor.rotateTo(target, true);
				else if(motor.isMoving() & !(runFlag)) motor.flt(true);
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
			}
	}
}
