

public class Scheduler extends Thread{
	private boolean enterIsPressed;
	private boolean escapeIsPressed;
	private boolean oldvalue = false;
	public Scheduler(){
		this.startThreads();
	}
	public void startThread(){ 
		super.start();
	}
	public void startThreads(){
		 for(int i = 0; i < Robot.buttons.size(); i++) ((Buttons) Robot.buttons.get(i)).startThread();
		 for(int i = 0; i < Robot.motors.size(); i++) ((Motors) Robot.motors.get(i)).startThread();
		 for(int i = 0; i < Robot.distance.size(); i++) ((Distance) Robot.distance.get(i)).startThread();

	}
	public void pauseThreads(){
		 for(int i = 0; i < Robot.buttons.size(); i++) ((Buttons) Robot.buttons.get(i)).pauseThread();
		 for(int i = 0; i < Robot.motors.size(); i++) ((Motors) Robot.motors.get(i)).pauseThread();
		 for(int i = 0; i < Robot.distance.size(); i++) ((Distance) Robot.distance.get(i)).pauseThread();
	}
	public void resumeThreads(){
		 for(int i = 0; i < Robot.buttons.size(); i++) ((Buttons) Robot.buttons.get(i)).resumeThread();
		 for(int i = 0; i < Robot.motors.size(); i++) ((Motors) Robot.motors.get(i)).resumeThread();
		 for(int i = 0; i < Robot.distance.size(); i++) ((Distance) Robot.distance.get(i)).resumeThread();
	}
	public void run(){
		while(true){
			escapeIsPressed = Robot.EscapeListening.getState();
			enterIsPressed = Robot.EnterListening.getState();
			if(escapeIsPressed){		
				return;
			}
			if(enterIsPressed != oldvalue & enterIsPressed==false){
				oldvalue = false;
					pauseThreads();
			}	
			if(enterIsPressed != oldvalue & enterIsPressed==true){
				oldvalue = true;
				resumeThreads();
			}	
		}
	}
	
}
