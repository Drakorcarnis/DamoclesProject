import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

public class ButtonListener {
	public boolean isPressed;
	public ButtonListener(String button){
		isPressed = false;
		Key key = null;
		if(button.equals("ENTER")) key = Button.ENTER;
		if(button.equals("LEFT")) key = Button.LEFT;
		if(button.equals("RIGHT")) key = Button.RIGHT;
		if(button.equals("ESCAPE")) key = Button.ESCAPE;
		key.addKeyListener(new KeyListener() {
		      public void keyPressed(Key k) {isPressed = !isPressed;}
		      public void keyReleased(Key k) {}
		});  
	}
}
