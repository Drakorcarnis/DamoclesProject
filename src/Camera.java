import lejos.robotics.geometry.Rectangle2D;
import lejos.hardware.device.NXTCam;
import lejos.hardware.port.SensorPort;

class Camera extends Device{
	private NXTCam cameraSensor;
	Rectangle2D target;
	boolean targetIsInView;
	
	
	Camera(){
		targetIsInView=false;
		cameraSensor = new NXTCam(SensorPort.S2);
		cameraSensor.enableTracking(true);
		cameraSensor.setTrackingMode(NXTCam.OBJECT_TRACKING);
		
	}
	public void run(){
		while(!mustStop){
			if(!mustPause){
				target = cameraSensor.getRectangle(0);
				targetIsInView=cameraSensor.getNumberOfObjects()!=0;
				try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
			}
			else try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}