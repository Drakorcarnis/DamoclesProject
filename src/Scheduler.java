import java.util.ArrayList;
import lejos.hardware.lcd.LCD;

public class Scheduler implements Runnable {
	private Thread thread;
	private boolean enterIsPressed;
	private boolean escapeIsPressed;
	private boolean oldvalue = false;
	public String mode;
	
	public Scheduler(){
		thread = new Thread(this);
		mode = "research";
	}
	
	public void stopThread(){
		thread.setDaemon(true);
	}
	
	public void startThread(){ 
		if (!thread.isAlive()) thread.start();
	}
	
	public void startDevices(){
		 for(int i = 0; i < Robot.devices.size(); i++) {
			 for(int j = 0; j < ((ArrayList<?>)Robot.devices.get(i)).size(); j++) {
				 if ((((ArrayList<?>)Robot.devices.get(i)).get(j)) instanceof Motors) ((Motors) ((ArrayList<?>)Robot.devices.get(i)).get(j)).startThread();
				 else if ((((ArrayList<?>)Robot.devices.get(i)).get(j)) instanceof Distances) ((Distances) ((ArrayList<?>)Robot.devices.get(i)).get(j)).startThread();
			 }
		 }
	}
	
	public void pauseDevices(){
		for(int i = 0; i < Robot.devices.size(); i++) {
			 for(int j = 0; j < ((ArrayList<?>)Robot.devices.get(i)).size(); j++) {
				 if ((((ArrayList<?>)Robot.devices.get(i)).get(j)) instanceof Motors) ((Motors) ((ArrayList<?>)Robot.devices.get(i)).get(j)).pauseThread();
				 else if ((((ArrayList<?>)Robot.devices.get(i)).get(j)) instanceof Distances) ((Distances) ((ArrayList<?>)Robot.devices.get(i)).get(j)).pauseThread();
			 }
		 }
	}
	
	public void stopDevices(){
		for(int i = 0; i < Robot.devices.size(); i++) {
			 for(int j = 0; j < ((ArrayList<?>)Robot.devices.get(i)).size(); j++) {
				 if ((((ArrayList<?>)Robot.devices.get(i)).get(j)) instanceof Motors) ((Motors) ((ArrayList<?>)Robot.devices.get(i)).get(j)).stopThread();
				 else if ((((ArrayList<?>)Robot.devices.get(i)).get(j)) instanceof Distances) ((Distances) ((ArrayList<?>)Robot.devices.get(i)).get(j)).stopThread();
			 }
		 }
	}
	
	public void run(){
		while(true){
			escapeIsPressed = Robot.escapeListening.isPressed;
			enterIsPressed = Robot.enterListening.isPressed;
			if(escapeIsPressed){		
				stopDevices();
				stopThread();
			}
			else if(enterIsPressed != oldvalue & enterIsPressed==false){
				oldvalue = false;
				pauseDevices();
			}	
			else if(enterIsPressed != oldvalue & enterIsPressed==true){
				oldvalue = true;
				startDevices();
			}
			if ((Robot.distance.distance<300)&(Robot.distance.distance>250)){
				Robot.motorARunning.internalMode = "lockOnTarget";
				Robot.motorBRunning.internalMode = "lockOnTarget";
			}
			else {
				Robot.motorARunning.internalMode = "research";
				Robot.motorBRunning.internalMode = "research";
			}
			LCD.drawString(Boolean.toString(enterIsPressed),0,1);
			try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
}
