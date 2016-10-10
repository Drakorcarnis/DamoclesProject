
public class Scheduler extends Thread{
	static Buttons ButtonListening= new Buttons();//Instancie une classe pour detecter quand on appuie sur un boutton
	static Motors MotorARunning= new Motors('A', 360, 36);//Instancie une classe pour gérer le moteur A et une vitesse d'1 tour/min
	static Motors MotorBRunning= new Motors('B', 75, 72);//Instancie une classe pour gérer le moteur B avec un angle de 75° et une vitesse de 2 tours/min
public Scheduler(){
	ButtonListening.setDaemon(true);//Là je demande que l'objet ButtonListening (qui est en fait un Thread) soit un "service", donc qu'il tourne en tache de fond quand il démarrera pour laisser le thread principal en paix.
	MotorARunning.setDaemon(true);
	MotorBRunning.setDaemon(true);
	ButtonListening.start();//Et là je démarre ces services.
	MotorARunning.start();
	MotorBRunning.start();//On a 3 threads qui tournent en parallèle, en plus du thread principal. C'est magique !
}
	public void run(){
		while(true){				
			if(ButtonListening.getValue()%2==0){
				MotorARunning.stopmotor();
				MotorBRunning.stopmotor();
			}	
			if(ButtonListening.getValue()%2==1){
				MotorARunning.startmotor();
				MotorBRunning.startmotor();
			}	
		}
	}
	
}
