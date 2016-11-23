import lejos.robotics.geometry.Rectangle2D;
import lejos.hardware.device.NXTCam;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

class Camera{
	private NXTCam cameraSensor;
	Camera(){
		cameraSensor = new NXTCam(SensorPort.S2);
		cameraSensor.enableTracking(true);
		cameraSensor.setTrackingMode(NXTCam.OBJECT_TRACKING);
	}
	Rectangle2D getRectangle(){
		LCD.drawString(Boolean.toString(cameraSensor.getNumberOfObjects()!=0),0 , 1);
		if(cameraSensor.getNumberOfObjects()!=0)return cameraSensor.getRectangle(0);		
		return null;
	}
}