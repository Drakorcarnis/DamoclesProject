
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
	double x;
	double y;
	double width;
	double height;
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
			width = rectangle.getWidth();
			height = rectangle.getHeight();
			if(targetFlag)oldTarget = target;
			targetFlag = false;
			if(motorPort=='A'){
				x=rectangle.getX();
				LCD.drawString(Double.toString(x), 0, 1);
				if((x+width/2)>85.0)return"backward";
				else if((x+width/2)<75.0)return"forward";
				else return"stop";
			}
			else if(motorPort=='B'){
				y=rectangle.getY();
				LCD.drawString(Double.toString(y), 0, 2);
				if((y+width/2)>70.0)return"forward";
				else if((y+width/2)<60.0)return"backward";
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
