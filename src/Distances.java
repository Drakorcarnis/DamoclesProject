import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.addon.OpticalDistanceSensor;

public class Distances implements Runnable{
	private OpticalDistanceSensor distanceSensor;
	public int distance;
	private boolean runFlag;
	private Thread thread;
	
	public Distances(){
		distanceSensor= new OpticalDistanceSensor(SensorPort.S1);
		distanceSensor.setSensorModule(OpticalDistanceSensor.GP2YA02);
	thread = new Thread(this);
	}
	

	public void stopThread(){
		thread.setDaemon(true);
		runFlag = false;
	}
	
	public void pauseThread(){
		runFlag = false;
	}
	
	public void startThread(){ 
		if (!thread.isAlive()) thread.start();
		runFlag = true;
	}
	
	public void run(){
		while(true){
			if(runFlag){
				distance = distanceSensor.getDistance();
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				LCD.drawInt(distance, 0, 0);
				Sound.playTone(6000 - distance, 10);
			}
			else try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
