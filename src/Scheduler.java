import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

public class Scheduler extends Device {
	private boolean enterIsPressed;
	private boolean escapeIsPressed;
	private boolean oldvalue;
	
	public Scheduler() {
		oldvalue = false;
	}

	public void startDevices(){
		 Robot.motorA.startThread();
		 Robot.motorB.startThread();
		 Robot.distance.startThread();
		 Robot.camera.startThread();
	}
	
	public void pauseDevices(){
		Robot.motorA.pauseThread();
		Robot.motorB.pauseThread();
		Robot.distance.pauseThread();
		Robot.camera.pauseThread();
	}
	
	public void stopDevices(){
		Robot.motorA.stopThread();
		Robot.motorB.stopThread();
		Robot.distance.stopThread();
		Robot.camera.stopThread();
	}
	public void run(){
		while(!mustStop){
			escapeIsPressed = Robot.escapeListening.isPressed;
			enterIsPressed = Robot.enterListening.isPressed;
			if(escapeIsPressed){		
				stopDevices();
				stopThread();
			}
			else if(enterIsPressed != oldvalue & !enterIsPressed){
				oldvalue = false;
				pauseDevices();
			}	
			else if(enterIsPressed != oldvalue & enterIsPressed){
				oldvalue = true;
				startDevices();
			}
			if(enterIsPressed){
				LCD.drawInt(((DistanceSensor)Robot.distance).distance, 0, 0);
				Sound.playTone(6000 - ((DistanceSensor)Robot.distance).distance*30, 10);
			}
			LCD.drawString("A.Mode = "+((Camera)Robot.camera).targetIsInView, 0, 1);
			LCD.drawString("B.Mode = "+((Camera)Robot.camera).targetIsInView, 0, 2);
			LCD.drawString("A.target = "+Double.toString(((TurretMotor)Robot.motorA).target), 0, 3);
			LCD.drawString("B.target = "+Double.toString(((TurretMotor)Robot.motorB).target), 0, 4);
			try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
}
