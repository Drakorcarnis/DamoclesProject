import lejos.hardware.port.SensorPort;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.MindsensorsDistanceSensorV2;
import lejos.hardware.sensor.SensorMode;

public class Distances implements Runnable{
	private MindsensorsDistanceSensorV2 distanceSensor;
	public int distance;
	private boolean runFlag;
	private Thread thread;
	SensorMode distanceMode;
	float[] sample;
	
	public Distances(){
		distanceSensor = new MindsensorsDistanceSensorV2(SensorPort.S1);
		distanceSensor.powerOn();distanceMode = distanceSensor.getDistanceMode();
		float[] sample=new float[1];
		distanceMode.fetchSample(sample,0);
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
//				distance = distanceSensor.getDistance();
				distanceMode.fetchSample(sample,0);
				LCD.drawString(Float.toString(sample[0]), 4, 4);
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				Sound.playTone(6000 - distance, 10);
			}
			else try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
