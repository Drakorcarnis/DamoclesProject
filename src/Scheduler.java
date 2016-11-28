import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

public class Scheduler implements Runnable {
	
	public Scheduler() {
		new Thread(this).start();
	}
	public void run(){
		boolean oldvalue=false;
		boolean mustStop=false;
		boolean enterIsPressed;
		while(!mustStop){
			enterIsPressed=Robot.enterListening.isPressed;
			if(Robot.escapeListening.isPressed){
				Robot.motorA.mustStop=true;
				Robot.motorB.mustStop=true;
				mustStop=true;
			}
			else if(enterIsPressed  != oldvalue & enterIsPressed==false){
				oldvalue = false;
				Robot.motorA.mustPause=true;
				Robot.motorB.mustPause=true;
			}	
			else if(enterIsPressed != oldvalue & enterIsPressed){
				oldvalue = true;
				Robot.motorA.mustStop=false;
				Robot.motorB.mustStop=false;
				Robot.motorA.mustPause=false;
				Robot.motorB.mustPause=false;
				if(!Robot.motorA.thread.isAlive())Robot.motorA.thread.start();
				if(!Robot.motorB.thread.isAlive())Robot.motorB.thread.start();
			}
			if(enterIsPressed){
				LCD.drawInt(Robot.distance.getDistance(), 0, 0);
//				Sound.playTone(6000 - Robot.distance.getDistance()*30, 10);
			}
			try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
}