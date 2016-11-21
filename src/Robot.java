public class Robot {
	static ButtonListener enterListening;
	static ButtonListener escapeListening;
	static TurretMotor motorA;
	static TurretMotor motorB;
	static DistanceSensor distance;
	static Scheduler scheduler;
	static Camera camera;

	public static void main(String[] args){
		enterListening= new ButtonListener("ENTER");
		enterListening.isPressed = true;
		escapeListening= new ButtonListener("ESCAPE");
		distance = new DistanceSensor();
		camera = new Camera();
		motorA= new TurretMotor('A', 120, 80);
		motorB= new TurretMotor('B', 60, 80);
		scheduler = new Scheduler();
	}
}
