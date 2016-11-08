import java.util.ArrayList;

import lejos.nxt.comm.RConsole;

public class Robot {
	static ArrayList<ArrayList<?>> devices = new ArrayList<ArrayList<?>>();//Cr�e un tableau qui contiendra toutes les instances d'actuateurs.
	static ArrayList<Distances> distances = new ArrayList<Distances>();//Cr�e un tableau qui contiendra toutes les instances de la classe Distances.
	static ArrayList<Buttons> buttons = new ArrayList<Buttons>();//Cr�e un tableau qui contiendra toutes les instances de la classe Buttons.
	static ArrayList<Motors> motors = new ArrayList<Motors>();//Cr�e un tableau qui contiendra toutes les instances de la classe Motors.
	static Buttons enterListening= new Buttons("ENTER");//Instancie une classe permettant de detecter quand on appuie sur ENTER
	static Buttons escapeListening= new Buttons("ESCAPE");//Instancie une classe permettant de detecter quand on appuie sur ESCAPE
	static Motors motorARunning= new Motors('A', 120, 80);//Instancie une classe permettant de g�rer le moteur A avec les param�tres angle de surveillance et vitesse de rotation.
	static Motors motorBRunning= new Motors('B', 60, 80);//Instancie une classe permettant de g�rer le moteur B avec les param�tres angle de surveillance et vitesse de rotation.
	static Distances distance = new Distances();//Instancie une classe permettant de g�rer le capteur infrarouge.
	static Scheduler scheduler;
	
	public static void main(String[] args){
		RConsole.openUSB(0);
		buttons.add(enterListening);
		buttons.add(escapeListening);
		distances.add(distance);
		motors.add(motorARunning);
		motors.add(motorBRunning);
		devices.add(motors);
		devices.add(distances);
		scheduler = new Scheduler();//Instancie une classe permettant de g�rer tous les actuateurs.
		scheduler.startThread();
	}
}
