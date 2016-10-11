import lejos.nxt.Button;

import java.util.ArrayList;

public class Robot {
	static ArrayList<Distance> distance = new ArrayList<Distance>();
	static ArrayList<Buttons> buttons = new ArrayList<Buttons>();
	static ArrayList<Motors> motors = new ArrayList<Motors>();
	static Buttons EnterListening= new Buttons("ENTER");//Instancie une classe pour detecter quand on appuie sur un boutton
	static Buttons EscapeListening= new Buttons("ESCAPE");
	static Motors MotorARunning= new Motors('A', 120, 80);//Instancie une classe pour gérer le moteur A et une vitesse d'1 tour/min
	static Motors MotorBRunning= new Motors('B', 60, 80);//Instancie une classe pour gérer le moteur B avec un angle de 75° et une vitesse de 2 tours/min
	static Distance Distance = new Distance();
	static Scheduler Scheduler;
	
	public static void main(String[] args) throws Exception
{
		buttons.add(EnterListening);
		buttons.add(EscapeListening);
		distance.add(Distance);
		motors.add(MotorARunning);
		motors.add(MotorBRunning);
		Scheduler = new Scheduler();
		Scheduler.startThread();
		Button.RIGHT.waitForPressAndRelease();
	}	
}
