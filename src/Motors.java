import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class Motors extends Thread {
	private boolean isrunning = false;
	private int range;
	private int speed;
	private NXTRegulatedMotor motor;
	public Motors(char motor, int range, int speed){
		if(motor=='A') this.motor = Motor.A;
		if(motor=='B') this.motor = Motor.B;
		if(motor=='C') this.motor = Motor.C;
		this.range = range;
		this.speed = speed;	
	}
	public void stopmotor(){ this.isrunning = false;}
	public void startmotor(){ this.isrunning = true;}
	
	public void run(){
			this.motor.setSpeed(speed);
			while(true){
				if(this.isrunning){
					this.motor.rotateTo(range);
					this.motor.rotateTo(0);	
				}
				else this.motor.stop();
				
			}
	}
}
