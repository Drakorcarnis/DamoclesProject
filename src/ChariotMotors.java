import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.motor.Motor;

public class ChariotMotors implements Runnable{
	//Implements the management of the 2 motors of the chariot.
	public NXTRegulatedMotor motorC;
	public NXTRegulatedMotor motorD;
	public boolean mustStop;
	public boolean mustPause;
	public Thread thread;
	
	ChariotMotors(){
		motorC = Motor.C;
		motorD = Motor.D;
		motorC.setAcceleration(500);
		motorC.setSpeed(500);
		motorD.setAcceleration(500);
		motorD.setSpeed(500);
		thread = new Thread(this);
	}
	public void run(){
		while(!mustStop){
			if(!mustPause){
				motorC.forward();
				motorD.forward();
				try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
			}
			if(!mustPause){
				motorC.backward();
				motorD.backward();
				try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
			
	}
}
