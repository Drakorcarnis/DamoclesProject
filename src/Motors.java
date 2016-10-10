import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class Motors extends Thread {
	private boolean motorMustRun = false;
	private int target;
	private int range;
	private int speed;
	private NXTRegulatedMotor motor;
	
	public Motors(char motor, int range, int speed){
		if(motor=='A') this.motor = Motor.A;
		if(motor=='B') this.motor = Motor.B;
		if(motor=='C') this.motor = Motor.C;
		this.range = range;
		this.speed = speed;	
		this.target = this.range;
	}
	public void stopmotor(){ this.motorMustRun = false;}
	public void startmotor(){ this.motorMustRun = true;}
	
	public void run(){
			this.motor.setSpeed(speed);
			while(true){
				if (this.motor.getTachoCount()<this.range & !(this.target == 0)) this.target = this.range;
				else if (this.motor.getTachoCount()>=this.range) this.target = 0;
				else if (this.motor.getTachoCount()<=0) this.target = this.range;
				if(!(this.motor.isMoving()) & this.motorMustRun) this.motor.rotateTo(this.target, true);
				else if(this.motor.isMoving() & !(this.motorMustRun)) this.motor.stop();
			}
	}
}
