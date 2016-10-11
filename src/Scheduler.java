import java.util.ArrayList;

public class Scheduler extends Thread{
	private boolean enterIsPressed;
	private boolean oldvalue = false;
public Scheduler(ArrayList<Buttons> buttons, ArrayList<Motors> motors,  ArrayList<Distance> distance){
	
	 for(int i = 0; i < buttons.size(); i++){
		 Robot.buttons.get(i).setDaemon(true);
		 Robot.buttons.get(i).start();
	 }
	 for(int i = 0; i < motors.size(); i++){
		 Robot.motors.get(i).setDaemon(true);
		 Robot.motors.get(i).start();
	 }
	 for(int i = 0; i < distance.size(); i++){
		 Robot.distance.get(i).setDaemon(true);
		 Robot.distance.get(i).start();
	 }
}
	public void run(){
		while(true){				
			this.enterIsPressed = Robot.EnterListening.getState();
			if(this.enterIsPressed != this.oldvalue & this.enterIsPressed==false){
				this.oldvalue = this.enterIsPressed;
				for(int i = 0; i < Robot.motors.size(); i++){
					Robot.motors.get(i).stopmotor();
				 }
			}	
			if(this.enterIsPressed != this.oldvalue & this.enterIsPressed==true){
				this.oldvalue = this.enterIsPressed;
				for(int i = 0; i < Robot.motors.size(); i++){
					Robot.motors.get(i).startmotor();
				 }
			}	
		}
	}
	
}
