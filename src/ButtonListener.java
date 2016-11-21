import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

public class ButtonListener {
	public boolean isPressed = false;
	private Key button;
	
	public ButtonListener(String button){
		if(button.equals("ENTER")) this.button = Button.ENTER;
		if(button.equals("LEFT")) this.button = Button.LEFT;
		if(button.equals("RIGHT")) this.button = Button.RIGHT;
		if(button.equals("ESCAPE")) this.button = Button.ESCAPE;
		this.button.addKeyListener(new KeyListener() {
		      public void keyPressed(Key k) {isPressed = !isPressed;}
		      public void keyReleased(Key k) {}
		});  
	}
}
