import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.MindsensorsDistanceSensorV2;
import lejos.hardware.sensor.SensorMode;

public class DistanceSensor extends Device{
	private MindsensorsDistanceSensorV2 distanceSensor;
	private SensorMode distanceMode;
	int distance;
	float[] sample;
	
	DistanceSensor(){
		distanceSensor = new MindsensorsDistanceSensorV2(SensorPort.S1);
		distanceSensor.powerOn();
		distanceMode = distanceSensor.getDistanceMode();
		sample=new float[1];
		distanceMode.fetchSample(sample,0);
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
