import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.MindsensorsDistanceSensorV2;
import lejos.hardware.sensor.SensorMode;

public class Distances implements Runnable{
	private MindsensorsDistanceSensorV2 distanceSensor;
	private boolean mustPause;
	private boolean mustStop;
	private Thread thread;
	private SensorMode distanceMode;
	public int distance;
	float[] sample;
	
	public Distances(){
		distanceSensor = new MindsensorsDistanceSensorV2(SensorPort.S1);
		distanceSensor.powerOn();
		distanceMode = distanceSensor.getDistanceMode();
		sample=new float[1];
		distanceMode.fetchSample(sample,0);
		thread = new Thread(this);
	}
	

	public void stopThread(){
		mustPause = true;
		mustStop = true;
	}
	
	public void pauseThread(){
		mustPause = true;
	}
	
	public void startThread(){ 
		mustStop = false;
		mustPause = false;
		if (!thread.isAlive()) thread.start();
	}
	
	public void run(){
		while(!mustStop){
			if(!mustPause){
				distanceMode.fetchSample(sample,0);
				distance = (int)(sample[0]*10);				
				try {Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}				
			}
			else try {Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
