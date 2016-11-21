
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class TurretMotor implements Runnable{
	private int target;
	private int oldTarget;
	private int range;
	public NXTRegulatedMotor motor;
	private char motorPort;
	double value;
	boolean targetFlag;
	Thread thread;
	boolean mustPause;
	boolean mustStop;
	TurretMotor(char motorPort, int range, int speed){
		this.motorPort = motorPort;
		if(motorPort=='A') motor = Motor.A;
		if(motorPort=='B') motor = Motor.B;
		this.range = range;
		motor.setAcceleration(3000);
		motor.setSpeed(speed);
		thread = new Thread(this);
	}
	int computeTarget(){
		if (Robot.camera.isDetected()){
			if(targetFlag)oldTarget = target;
			targetFlag = false;
			if(motorPort=='A'){
				if((value=Robot.camera.getRectangle().getX())>55)return target=motor.getTachoCount() + (int)(value)-50;
				else if(value<45)return target=(motor.getTachoCount()-50+(int)(value));
				else return target=motor.getTachoCount();
			}	
			else if(motorPort=='B'){
				if((value=Robot.camera.getRectangle().getY())>55)return target=motor.getTachoCount() + (int)(value)-50;
				else if(value<45)return target=motor.getTachoCount()-50+(int)(value);
				else return target=motor.getTachoCount();
			}
		}
		else{
			targetFlag=true;
			if(oldTarget!=0){ 
				target = oldTarget;
				oldTarget = 0;
				return target;
			}
			else if (motor.getTachoCount()<=0)return target=range;
		}
		return target=0;
	}
	public void run(){			
			while(!mustStop){
				if(!(motor.isMoving()) & !mustPause) motor.rotateTo(computeTarget(), true);
				else if(motor.isMoving() & mustPause) motor.flt(true);
			}
	}
}
