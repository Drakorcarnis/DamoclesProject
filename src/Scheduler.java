import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

public class Scheduler implements Runnable {
	//A manager to pause/start the pod.
	public Scheduler() {
		new Thread(this).start();
	}
	public void run(){
		//Loop that get the state oh the buttons and toggle the actuators' state accordingly.
		boolean oldvalue=false;
		boolean mustStop=false;
		boolean enterIsPressed;
		while(!mustStop){
			enterIsPressed=Robot.enterListening.isPressed;
			if(Robot.escapeListening.isPressed){
				Robot.turretMotors.mustStop=true;
				mustStop=true;
			}
			else if(enterIsPressed  != oldvalue & enterIsPressed==false){
				oldvalue = false;
				Robot.turretMotors.mustPause=true;
			}	
			else if(enterIsPressed != oldvalue & enterIsPressed){
				oldvalue = true;
				Robot.turretMotors.mustStop=false;
				Robot.turretMotors.mustPause=false;
				if(!Robot.turretMotors.thread.isAlive())Robot.turretMotors.thread.start();
			}
			if(enterIsPressed){
				LCD.drawInt(Robot.distance.getDistance(), 0, 0);
//				Sound.playTone(6000 - Robot.distance.getDistance()*30, 10);
			}
			try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
}