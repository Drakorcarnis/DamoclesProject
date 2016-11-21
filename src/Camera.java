import lejos.robotics.geometry.Rectangle2D;
import lejos.hardware.device.NXTCam;
import lejos.hardware.port.SensorPort;

class Camera{
	private NXTCam cameraSensor;
	Camera(){
		cameraSensor = new NXTCam(SensorPort.S2);
		cameraSensor.enableTracking(true);
		cameraSensor.setTrackingMode(NXTCam.OBJECT_TRACKING);
	}
	boolean isDetected(){return cameraSensor.getNumberOfObjects()!=0;}
	Rectangle2D getRectangle(){return cameraSensor.getRectangle(0);}
}