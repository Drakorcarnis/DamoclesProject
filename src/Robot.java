public class Robot {
	static Buttons enterListening;
	static Buttons escapeListening;
	static Motors motorA;
	static Motors motorB;
	static Distances distance;
	static Cameras camera;
	static Scheduler scheduler;
	
	public static void main(String[] args){
		enterListening= new Buttons("ENTER");
		enterListening.isPressed = true;
		escapeListening= new Buttons("ESCAPE");
		motorA= new Motors('A', 120, 80);
		motorB= new Motors('B', 60, 80);
		distance = new Distances();
		camera = new Cameras();
		scheduler = new Scheduler();
		scheduler.startThread();
	}
}
