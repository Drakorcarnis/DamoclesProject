
import lejos.nxt.Button;

public class Robot {
	static Scheduler Scheduler = new Scheduler();//Instancie une classe pour detecter quand on appuie sur un boutton
	
	public static void main(String[] args) {
		Scheduler.setDaemon(true);//Là je demande que l'objet ButtonListening (qui est en fait un Thread) soit un "service", donc qu'il tourne en tache de fond quand il démarrera pour laisser le thread principal en paix.	
		Scheduler.start();//Et là je démarre ces services.
		Button.ESCAPE.waitForPressAndRelease();//Ca c'est pour stopper le programme grâce à un mécanisme de wait. Si on ne met pas de "wait"(attend que j'appuie sur tel boutton), le programme s'arrêterai tout de suite vu que le thread principal serait inoccupé.
	}	
}
