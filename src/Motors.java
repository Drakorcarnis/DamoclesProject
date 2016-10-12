import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class Motors implements Runnable {
	private int target;
	private int range;
	private int speed;
	private boolean runFlag;
	private NXTRegulatedMotor motor;
	private Thread thread;
	
	public Motors(char motor, int range, int speed){
		if(motor=='A') this.motor = Motor.A;
		if(motor=='B') this.motor = Motor.B;
		if(motor=='C') this.motor = Motor.C;
		this.range = range;
		this.speed = speed;	
		target = range;
		thread = new Thread(this);
	}
	public void startThread(){ 
		thread.start();
	}
	public void pauseThread(){ 
		runFlag = false;
		}
	public void resumeThread(){ 
		runFlag = true;
		}
	
	public void run(){
			motor.setSpeed(speed);
			while(true){
				if (motor.getTachoCount()<range & !(target == 0)) target = range;
				else if (motor.getTachoCount()>=range) target = 0;
				else if (motor.getTachoCount()<=0) target = range;
				if(!(motor.isMoving()) & runFlag) motor.rotateTo(target, true);
				else if(motor.isMoving() & !(runFlag)) motor.stop();
			}
	}
}
