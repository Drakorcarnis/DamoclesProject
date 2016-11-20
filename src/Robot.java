public class Robot {
	static ButtonListener enterListening;
	static ButtonListener escapeListening;
	static Device motorA;
	static Device motorB;
	static Device distance;
	static Device camera;
	static Device scheduler;

	public static void main(String[] args){
		enterListening= new ButtonListener("ENTER");
		enterListening.isPressed = true;
		escapeListening= new ButtonListener("ESCAPE");
		motorA= new TurretMotor('A', 120, 80);
		motorB= new TurretMotor('B', 60, 80);
		distance = new DistanceSensor();
		camera = new Camera();
		scheduler = new Scheduler();
		scheduler.startThread();
	}
	
}
