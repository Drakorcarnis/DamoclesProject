import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.motor.Motor;

public class ChariotMotors implements Runnable{
	public NXTRegulatedMotor motorC;
	public NXTRegulatedMotor motorD;
	ChariotMotors(){
		motorC = Motor.C;
		motorC = Motor.D;
		motorC.setAcceleration(500);
		motorC.setSpeed(500);
		motorD.setAcceleration(500);
		motorD.setSpeed(500);
	}
	public void run(){}
}
