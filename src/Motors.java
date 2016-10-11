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
		target = range;
	}
	public void stopmotor(){ motorMustRun = false;}
	public void startmotor(){ motorMustRun = true;}
	
	public void run(){
			motor.setSpeed(speed);
			while(true){
				if (motor.getTachoCount()<range & !(target == 0)) target = range;
				else if (motor.getTachoCount()>=range) target = 0;
				else if (motor.getTachoCount()<=0) target = range;
				if(!(motor.isMoving()) & motorMustRun) motor.rotateTo(target, true);
				else if(motor.isMoving() & !(motorMustRun)) motor.stop();
			}
	}
}
