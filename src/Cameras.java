import lejos.robotics.geometry.Rectangle2D;
import lejos.hardware.device.NXTCam;
import lejos.hardware.port.SensorPort;

public class Cameras implements Runnable{
	private NXTCam cameraSensor;
	private boolean mustPause;
	private boolean mustStop;
	public Rectangle2D target;
	public boolean targetIsInView;
	private Thread thread;
	
	public Cameras(){
		targetIsInView=false;
		cameraSensor = new NXTCam(SensorPort.S2);
		cameraSensor.enableTracking(true);
		cameraSensor.setTrackingMode(NXTCam.OBJECT_TRACKING);
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
				target = cameraSensor.getRectangle(0);
				targetIsInView=cameraSensor.getNumberOfObjects()!=0;
				try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
			}
			else try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}