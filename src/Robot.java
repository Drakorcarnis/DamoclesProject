public class Robot {
	static ButtonListener enterListening;
	static ButtonListener escapeListening;
	static ButtonListener rightListening;
	static TurretMotor motorA;
	static TurretMotor motorB;
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
		motorA= new TurretMotor('A',0, 3000, 1000, 2000);
		motorB= new TurretMotor('B',-20, 20, 50, 1000);
		scheduler = new Scheduler();
	}
}
