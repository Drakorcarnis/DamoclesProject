import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.MindsensorsDistanceSensorV2;
import lejos.hardware.sensor.SensorMode;

public class DistanceSensor{
	private MindsensorsDistanceSensorV2 distanceSensor;
	private  SensorMode distanceMode;
	private float[] sample;
	
	DistanceSensor(){
		distanceSensor = new MindsensorsDistanceSensorV2(SensorPort.S1);
		distanceSensor.powerOn();
		distanceMode = distanceSensor.getDistanceMode();
		sample=new float[1];
		distanceMode.fetchSample(sample,0);
	}
	 int getDistance(){
		distanceMode.fetchSample(sample,0);
		return (int)(sample[0]*10);
	}
}