public class Robot {
	static ButtonListener enterListening;
	static ButtonListener escapeListening;
	static ButtonListener rightListening;
	static TurretMotors turretMotors;
	static DistanceSensor distance;
	static Scheduler scheduler;
	static Camera camera;

	public static void main(String[] args){
		enterListening= new ButtonListener("ENTER");
		enterListening.isPressed = true;
		escapeListening= new ButtonListener("ESCAPE");
		rightListening= new ButtonListener("RIGHT");
		distance = new DistanceSensor();
		camera = new Camera();
		turretMotors = new TurretMotors();
		turretMotors.setMotorA(-1500, 1500, 1000, 2000);
		turretMotors.setMotorB(-300, 300, 500, 2000);
		scheduler = new Scheduler();
	}
}
