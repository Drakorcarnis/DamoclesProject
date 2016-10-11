import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
public class Distance extends Thread {
	private OpticalDistanceSensor DistanceSensor ;
	public int distance;
	public Distance(){
	this.DistanceSensor= new OpticalDistanceSensor(SensorPort.S1);
	this.DistanceSensor.setSensorModule(OpticalDistanceSensor.GP2YA02);
	}
	public int getDistance(){
		return this.distance;
	}
	public void run(){
		while(true){
			this.distance = DistanceSensor.getDistance();
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			LCD.drawInt(this.distance, 0, 0);
		}
	}
}
