import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

public class Scheduler implements Runnable {
	private Thread thread;
	private boolean enterIsPressed;
	private boolean escapeIsPressed;
	private boolean oldvalue;
	public String mode;
	
	public Scheduler(){
		thread = new Thread(this);
		mode = "research";
		oldvalue = false;
	}
	
	public void stopThread(){
		thread.setDaemon(true);
	}
	
	public void startThread(){ 
		if (!thread.isAlive()) thread.start();
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
		while(true){
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
				LCD.drawInt(Robot.distance.distance, 0, 0);
				Sound.playTone(6000 - Robot.distance.distance*30, 10);
			}
//			if (Robot.camera.target != null){
//				Robot.motorA.internalMode = "lockOnTarget";
//				Robot.motorB.internalMode = "lockOnTarget";
//			}
//			else {
				Robot.motorA.internalMode = "research";
				Robot.motorB.internalMode = "research";
//			}
			try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
}
