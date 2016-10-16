import java.util.ArrayList;

public class Scheduler implements Runnable {
	private Thread thread;
	private boolean enterIsPressed;
	private boolean escapeIsPressed;
	private boolean oldvalue = false;
	
	public Scheduler(){
		thread = new Thread(this);
	}
	
	public void stopThread(){
		thread.setDaemon(true);
	}
	
	public void startThread(){ 
		if (!thread.isAlive()) thread.start();
	}
	
	public void startActuators(){
		 for(int i = 0; i < Robot.actuators.size(); i++) {
			 for(int j = 0; j < ((ArrayList<?>)Robot.actuators.get(i)).size(); j++) {
				 if ((((ArrayList<?>)Robot.actuators.get(i)).get(j)) instanceof Motors) ((Motors) ((ArrayList<?>)Robot.actuators.get(i)).get(j)).startThread();
				 else if ((((ArrayList<?>)Robot.actuators.get(i)).get(j)) instanceof Distances) ((Distances) ((ArrayList<?>)Robot.actuators.get(i)).get(j)).startThread();
			 }
		 }
	}
	
	public void pauseActuators(){
		for(int i = 0; i < Robot.actuators.size(); i++) {
			 for(int j = 0; j < ((ArrayList<?>)Robot.actuators.get(i)).size(); j++) {
				 if ((((ArrayList<?>)Robot.actuators.get(i)).get(j)) instanceof Motors) ((Motors) ((ArrayList<?>)Robot.actuators.get(i)).get(j)).pauseThread();
				 else if ((((ArrayList<?>)Robot.actuators.get(i)).get(j)) instanceof Distances) ((Distances) ((ArrayList<?>)Robot.actuators.get(i)).get(j)).pauseThread();
			 }
		 }
	}
	
	public void stopActuators(){
		for(int i = 0; i < Robot.actuators.size(); i++) {
			 for(int j = 0; j < ((ArrayList<?>)Robot.actuators.get(i)).size(); j++) {
				 if ((((ArrayList<?>)Robot.actuators.get(i)).get(j)) instanceof Motors) ((Motors) ((ArrayList<?>)Robot.actuators.get(i)).get(j)).stopThread();
				 else if ((((ArrayList<?>)Robot.actuators.get(i)).get(j)) instanceof Distances) ((Distances) ((ArrayList<?>)Robot.actuators.get(i)).get(j)).stopThread();
			 }
		 }
	}
	
	public void run(){
		while(true){
			escapeIsPressed = Robot.escapeListening.isPressed;
			enterIsPressed = Robot.enterListening.isPressed;
			if(escapeIsPressed){		
				stopActuators();
				stopThread();
			}
			else if(enterIsPressed != oldvalue & enterIsPressed==false){
				oldvalue = false;
				pauseActuators();
			}	
			else if(enterIsPressed != oldvalue & enterIsPressed==true){
				oldvalue = true;
				startActuators();
			}
			try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
}
