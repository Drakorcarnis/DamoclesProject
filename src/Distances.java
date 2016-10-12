import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.addon.OpticalDistanceSensor;
public class Distances implements Runnable{
	private OpticalDistanceSensor DistanceSensor ;
	public int distance;
	private boolean runFlag;
	private Thread thread;
	
	public Distances(){
	DistanceSensor= new OpticalDistanceSensor(SensorPort.S1);
	DistanceSensor.setSensorModule(OpticalDistanceSensor.GP2YA02);
	thread = new Thread(this);
	}
	public int getDistance(){
		return distance;
	}
	public void startThread(){ 
		thread.start();
	}
	public void pauseThread(){ 
		runFlag = false;
		}
	public void resumeThread(){ 
		runFlag = true;
		}
	public void run(){
		while(true){
			if(runFlag){
				distance = DistanceSensor.getDistance();
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				LCD.drawInt(distance, 0, 0);
				Sound.playTone(6000 - distance, 10);
			}
		}
	}
}
