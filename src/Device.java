
public abstract class Device implements Runnable{
	private Thread thread;
	boolean mustPause;
	boolean mustStop;
	Device(){
		thread = new Thread(this);
	}
	void stopThread(){
		mustPause = true;
		mustStop = true;
	}
	
	void pauseThread(){
		mustPause = true;
	}
	
	void startThread(){ 
		mustStop = false;
		mustPause = false;
		if (!thread.isAlive()) thread.start();
	}
	public abstract void run();
}
