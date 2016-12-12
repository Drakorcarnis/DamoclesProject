import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

public class Scheduler implements Runnable {
	//A manager to pause/start the pod.
	public Scheduler() {
		new Thread(this).start();
	}
	public void run(){
		//Loop that gets the state oh the buttons and toggle the actuators' state accordingly.
		boolean oldvalue=false;
		boolean oldvalue1=false;
		boolean mustStop=false;
		boolean enterIsPressed;
		boolean rightIsPressed;
		while(!mustStop){
			enterIsPressed=Robot.enterListening.isPressed;
			rightIsPressed=Robot.rightListening.isPressed;
			if(Robot.escapeListening.isPressed){
				Robot.turretMotors.mustStop=true;
				Robot.chariotMotors.mustStop=true;
				mustStop=true;
			}
			else{
				if(enterIsPressed  != oldvalue & enterIsPressed==false){
					oldvalue = false;
					Robot.turretMotors.mustPause=true;
				}	
				else if(enterIsPressed != oldvalue & enterIsPressed){
					oldvalue = true;
					Robot.turretMotors.mustStop=false;
					Robot.turretMotors.mustPause=false;
					if(!Robot.turretMotors.thread.isAlive())Robot.turretMotors.thread.start();
				}
				if(rightIsPressed  != oldvalue1 & rightIsPressed==false){
					oldvalue = false;
					Robot.chariotMotors.mustPause=true;
				}	
				else if(rightIsPressed != oldvalue1 & rightIsPressed){
					oldvalue = true;
					Robot.chariotMotors.mustStop=false;
					Robot.chariotMotors.mustPause=false;
					if(!Robot.chariotMotors.thread.isAlive())Robot.chariotMotors.thread.start();
				}
			}
			if(enterIsPressed){
				LCD.drawInt(Robot.distance.getDistance(), 0, 0);
//				Sound.playTone(6000 - Robot.distance.getDistance()*30, 10);
			}
			try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
}