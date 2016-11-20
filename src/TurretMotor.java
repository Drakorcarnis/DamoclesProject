import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class TurretMotor extends Device {
	public double target;
	private double oldTarget;
	private int range;
	public NXTRegulatedMotor motor;
	private boolean targetFlag;
	private char motorPort;
	double x;
	double y;
	
	TurretMotor(char motorPort, int range, int speed){
		this.motorPort = motorPort;
		if(motorPort=='A') this.motor = Motor.A;
		if(motorPort=='B') this.motor = Motor.B;
		this.range = range;
		this.motor.setAcceleration(3000);
		this.motor.setSpeed(speed);
		target = range;
		oldTarget=0;
	}
	void computeTarget(){
		if (((Camera)Robot.camera).targetIsInView){
			if(targetFlag)oldTarget = target;
			targetFlag = false;
			if(motorPort=='A'){
				if((x=((Camera)Robot.camera).target.getX())>55)target = motor.getTachoCount() + x-50;
				else if((x=((Camera)Robot.camera).target.getX())<45)target = motor.getTachoCount() +50-x;
				else motor.flt(true);
			}	
			else if(motorPort=='B'){
				if((x=((Camera)Robot.camera).target.getY())>55)target = motor.getTachoCount() + x-50;
				else if((x=((Camera)Robot.camera).target.getY())<45)target = motor.getTachoCount() +50-x;
				else motor.flt(true);
			}	
		}
		else{
			targetFlag = true;
			if(oldTarget!=0){ 
				target = oldTarget;
				oldTarget = 0;
			}
			else{
				if (motor.getTachoCount()>=range) target = 0;
				else if (motor.getTachoCount()<=0) target = range;
				else target = 0;
			}
		}
		
	}
	public void run(){			
			while(!mustStop){
				computeTarget();
				if(!(motor.isMoving()) & !mustPause) motor.rotateTo((int) target, true);
				else if(motor.isMoving() & mustPause) motor.flt(true);
				try {Thread.sleep(30);} catch (InterruptedException e) {e.printStackTrace();}
			}
	}
}
