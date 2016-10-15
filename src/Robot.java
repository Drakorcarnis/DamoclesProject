
import java.util.ArrayList;

public class Robot {
	static ArrayList<ArrayList<?>> actuators = new ArrayList<ArrayList<?>>();//Crée un tableau qui contiendra toutes les instances d'actuateurs.
	static ArrayList<Distances> distances = new ArrayList<Distances>();//Crée un tableau qui contiendra toutes les instances de la classe Distances.
	static ArrayList<Buttons> buttons = new ArrayList<Buttons>();//Crée un tableau qui contiendra toutes les instances de la classe Buttons.
	static ArrayList<Motors> motors = new ArrayList<Motors>();//Crée un tableau qui contiendra toutes les instances de la classe Motors.
	static Buttons EnterListening= new Buttons("ENTER");//Instancie une classe permettant de detecter quand on appuie sur ENTER
	static Buttons EscapeListening= new Buttons("ESCAPE");//Instancie une classe permettant de detecter quand on appuie sur ESCAPE
	static Motors MotorARunning= new Motors('A', 120, 80);//Instancie une classe permettant de gérer le moteur A avec les paramètres angle de surveillance et vitesse de rotation.
	static Motors MotorBRunning= new Motors('B', 60, 80);//Instancie une classe permettant de gérer le moteur B avec les paramètres angle de surveillance et vitesse de rotation.
	static Distances Distance = new Distances();//Instancie une classe permettant de gérer le capteur infrarouge.
	static Scheduler Scheduler;
	
	public static void main(String[] args) throws Exception{
		buttons.add(EnterListening);
		buttons.add(EscapeListening);
		distances.add(Distance);
		motors.add(MotorARunning);
		motors.add(MotorBRunning);
		actuators.add(motors);
		actuators.add(distances);
		Scheduler = new Scheduler();//Instancie une classe permettant de gérer tous les actuateurs.
		Scheduler.startThread();
	}
}
