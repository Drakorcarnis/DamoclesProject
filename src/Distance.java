import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
public class Distance extends Thread {
	private OpticalDistanceSensor DistanceSensor ;
	public Distance(){
	this.DistanceSensor= new OpticalDistanceSensor(SensorPort.S1);
	this.DistanceSensor.setSensorModule(OpticalDistanceSensor.GP2YA02);
	}
	public int getDistance(){
		return DistanceSensor.getDistance();
	}

}
