import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;

public class Buttons extends Thread{
	public int value = 0;
	public int getValue(){
		return value;
	}
	public void run(){		
		Button.ENTER.addButtonListener(new ButtonListener() {
		      public void buttonPressed(Button b) {
		    	value++;
		        LCD.drawString("value ="+value, 0, 0);
		        
		      }

		      public void buttonReleased(Button b) {
		      }
		    });  
		Button.ESCAPE.waitForPressAndRelease();
	}
}
