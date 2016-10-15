import lejos.nxt.Button;
import lejos.nxt.ButtonListener;

public class Buttons {
	public boolean isPressed = false;
	private Button button;
	
	public Buttons(String button){
		if(button.equals("ENTER")) this.button = Button.ENTER;
		if(button.equals("LEFT")) this.button = Button.LEFT;
		if(button.equals("RIGHT")) this.button = Button.RIGHT;
		if(button.equals("ESCAPE")) this.button = Button.ESCAPE;
		this.button.addButtonListener(new ButtonListener() {
		      public void buttonPressed(Button b) {isPressed = !isPressed;}
		      public void buttonReleased(Button b) {}
		});  
	}
}
