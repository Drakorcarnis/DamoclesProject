
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.robotics.geometry.Rectangle2D;

public class TurretMotors implements Runnable{
	private String targetA;
	private String targetB;
	private String oldTargetA;
	private String oldTargetB;
	private int rangeA;
	private int rangeB;
	private int minA;
	private int minB;
	public NXTRegulatedMotor motorA;
	public NXTRegulatedMotor motorB;
	Rectangle2D rectangle;
	double x;
	double y;
	double width;
	double height;
	boolean targetFlag;
	Thread thread;
	boolean mustPause;
	boolean mustStop;
	
	TurretMotors(){
		motorA = Motor.A;
		motorB = Motor.B;
		targetFlag=true;
		thread = new Thread(this);
	}
	void setMotorA(int min, int range, int speed, int acceleration){
		minA = min;
		rangeA = range;
		motorA.setSpeed(speed);
		motorA.setAcceleration(acceleration);
	}
	void setMotorB(int min, int range, int speed, int acceleration){
		minB = min;
		rangeB = range;
		motorB.setSpeed(speed);
		motorB.setAcceleration(acceleration);
	}
	void computeTarget(){
		if ((rectangle=Robot.camera.getRectangle())!=null){
			width = rectangle.getWidth();
			height = rectangle.getHeight();
			if(targetFlag){
				oldTargetA = targetA;
				oldTargetB = targetB;
			}
			targetFlag = false;
			x=rectangle.getX();
			y=rectangle.getY();
			if((x+width/2)>85.0)targetA="backward";
			else if((x+width/2)<75.0)targetA="forward";
			else targetA="stop";
			if((y+width/2)>70.0)targetB="forward";
			else if((y+width/2)<60.0)targetB="backward";
			else targetB="stop";
		}
		else if (targetFlag==false){
				targetFlag=true;
				if(oldTargetA=="backward")targetA="backward";
				else if(oldTargetA=="forward")targetA="forward";
				if(oldTargetB=="backward")targetB="backward";
				else if(oldTargetB=="forward")targetB="forward";
				
		}
		else {
			if (motorA.getTachoCount()<=minA)targetA="forward";
			else if (motorA.getTachoCount()>=rangeA)targetA="backward";
			if (motorB.getTachoCount()<=minB)targetB="forward";
			else if (motorB.getTachoCount()>=rangeB)targetB="backward";
		}
	}
	public void run(){			
			while(!mustStop){
				computeTarget();
				if(!mustPause){
					if(targetA=="forward")motorA.forward();
					else if(targetA=="backward")motorA.backward();
					else motorA.stop();
					if(targetB=="forward")motorB.forward();
					else if(targetB=="backward")motorB.backward();
					else motorB.stop();
				}
				else{
					motorA.flt(true);
					motorB.flt(true);
				}
				
			}
	}
}
