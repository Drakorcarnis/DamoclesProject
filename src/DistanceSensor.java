import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.MindsensorsDistanceSensorV2;
import lejos.hardware.sensor.SensorMode;

public class DistanceSensor{
	//Implements the management of the distance sensor
	private MindsensorsDistanceSensorV2 distanceSensor;
	private  SensorMode distanceMode;
	private float[] sample;
	
	DistanceSensor(){
		distanceSensor = new MindsensorsDistanceSensorV2(SensorPort.S1);
		distanceSensor.powerOn();
		distanceMode = distanceSensor.getDistanceMode();
		sample=new float[1];
	}
	 int getDistance(){
		//Returns the distance measured by the distance sensor.
		distanceMode.fetchSample(sample,0);
		return (int)(sample[0]*10);
	}
}