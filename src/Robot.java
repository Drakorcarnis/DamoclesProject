import lejos.nxt.Button;
import java.util.ArrayList;

public class Robot {
	static ArrayList<Buttons> buttons = new ArrayList<Buttons>();
	static ArrayList<Motors> motors = new ArrayList<Motors>();
	public static Buttons EnterListening= new Buttons("ENTER");//Instancie une classe pour detecter quand on appuie sur un boutton
	static Motors MotorARunning= new Motors('A', 80, 40);//Instancie une classe pour gérer le moteur A et une vitesse d'1 tour/min
	static Motors MotorBRunning= new Motors('B', 80, 40);//Instancie une classe pour gérer le moteur B avec un angle de 75° et une vitesse de 2 tours/min
	static Scheduler Scheduler;
	public static void main(String[] args) throws Exception
{
		buttons.add(EnterListening);
		motors.add(MotorARunning);
		motors.add(MotorBRunning);
		Scheduler = new Scheduler(buttons, motors);
		Scheduler.setDaemon(true);
		Scheduler.start();
		Button.ESCAPE.waitForPressAndRelease();
	}	
}
