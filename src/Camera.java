import lejos.robotics.geometry.Rectangle2D;
import lejos.hardware.device.NXTCam;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

class Camera{
//	Implements the management of the camera
	private NXTCam cameraSensor;
	Camera(){
		cameraSensor = new NXTCam(SensorPort.S2);
		cameraSensor.enableTracking(true);
		cameraSensor.setTrackingMode(NXTCam.OBJECT_TRACKING);
	}
	Rectangle2D getRectangle(){
		//Returns a java Rectangle representing the object in the field of view of the camera.
		if(cameraSensor.getNumberOfObjects()!=0)return cameraSensor.getRectangle(0);		
		return null;
	}
}