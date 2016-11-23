
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.robotics.geometry.Rectangle2D;

public class TurretMotor implements Runnable{
	private int target;
	private int oldTarget;
	private int range;
	private int min;
	private int x=0;
	public NXTRegulatedMotor motor;
	private char motorPort;
	Rectangle2D rectangle;
	double value;
	boolean targetFlag;
	Thread thread;
	boolean mustPause;
	boolean mustStop;
	TurretMotor(char motorPort,int min, int range, int speed){
		this.motorPort = motorPort;
		if(motorPort=='A') motor = Motor.A;
		if(motorPort=='B') motor = Motor.B;
		this.range = range;
		this.min = min;
		motor.setAcceleration(3000);
		motor.setSpeed(speed);
		thread = new Thread(this);
	}
	int computeTarget(){
		if ((rectangle=Robot.camera.getRectangle())!=null){
			x++;
			if(targetFlag){
				oldTarget = target;
				motor.flt(true);
			}
			targetFlag = false;
			if(motorPort=='A'){
				LCD.drawInt(target, 0, 2);
				if((value=rectangle.getX())>55)return target=motor.getTachoCount() +(50-(int)(value))*7;
				else if(value<45)return target=motor.getTachoCount()+(50-(int)(value))*7;
				else return target=motor.getTachoCount();
			}	
			else if(motorPort=='B'){
				LCD.drawInt(target, 0, 3);
				if((value=rectangle.getY())>55)return target=motor.getTachoCount() +(50-(int)(value))/10;
				else if(value<45)return target=motor.getTachoCount()+(50-(int)(value))/10;
				return target=motor.getTachoCount();
			}
		}
		else{
			targetFlag=true;
			if(oldTarget!=min){ 
				target = oldTarget;
				oldTarget = min;
				return target;
			}
			else if (motor.getTachoCount()<=min)return target=range;
		}
		return target=min;
	}
	public void run(){			
			while(!mustStop){
				computeTarget();
				if(!(motor.isMoving()) & !mustPause) motor.rotateTo(target, true);
				else if(motor.isMoving() & mustPause) motor.flt(true);
			}
	}
}
