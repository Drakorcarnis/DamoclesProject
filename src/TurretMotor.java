
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.robotics.geometry.Rectangle2D;

public class TurretMotor implements Runnable{
	private String target;
	private String oldTarget;
	private int range;
	private int min;
	public NXTRegulatedMotor motor;
	private char motorPort;
	Rectangle2D rectangle;
	double value;
	boolean targetFlag;
	Thread thread;
	boolean mustPause;
	boolean mustStop;
	TurretMotor(char motorPort,int min, int range, int speed, int acceleration){
		this.motorPort = motorPort;
		if(motorPort=='A') motor = Motor.A;
		if(motorPort=='B') motor = Motor.B;
		this.range = range;
		this.min = min;
		targetFlag=true;
		motor.setAcceleration(acceleration);
		motor.setSpeed(speed);
		thread = new Thread(this);
	}
	String computeTarget(){
		if ((rectangle=Robot.camera.getRectangle())!=null){
			if(targetFlag)oldTarget = target;
			targetFlag = false;
			if(motorPort=='A'){
				value=rectangle.getX();
				if(value<70)return"forward";
				else if(value>80)return"backward";
				else return"stop";
			}
			else if(motorPort=='B'){
				value=rectangle.getY();
				if(value<40)return"forward";
				else if(value>50)return"backward";
				else return"stop";
			}
		}
		else if (targetFlag==false){
				targetFlag=true;
				if(oldTarget=="backward")return"backward";
				if(oldTarget=="forward")return"forward";	
		}
		else if (motor.getTachoCount()<=min)return"forward";
		else if (motor.getTachoCount()>=range)return"backward";
		return target;
	}
	public void run(){			
			while(!mustStop){
				target=computeTarget();
				if(!mustPause){
					if(target=="forward")motor.forward();
					else if(target=="backward")motor.backward();
					else motor.stop();
				}
				else motor.flt(true);
			}
	}
}
