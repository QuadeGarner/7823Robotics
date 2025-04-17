package Robot;

/**
 *Quade Garner - qmgarner
 *CIS171 20432 
 *Apr 17, 2025
 */
public class Robot {
	private double pose;
	private Motor motor1;
	private Motor motor2;
	private Motor motor3;
	private Motor motor4;
	private int RobotX;
	private int RobotY;
	private double RobotAngle;
	// Constuctors 
	public Robot() {}
	
	public Robot (double pose, Motor m1, Motor m2, Motor m3, Motor m4) {
		this.pose = pose;
		this.motor1 = m1;
		this.motor2 = m2;
		this.motor3 = m3;
		this.motor4 = m4;
		this.RobotX = 0;
		this.RobotY = 0;
		this.RobotAngle = 0.0;
	}
	public Robot (double pose, Motor m1, Motor m2, Motor m3, Motor m4, int x, int y, double angle) {
		this.pose = pose;
		this.motor1 = m1;
		this.motor2 = m2;
		this.motor3 = m3;
		this.motor4 = m4;
		this.RobotX = x;
		this.RobotY = y;
		this.RobotAngle = angle;
	}
	// getters and setter 
	public double getPose() {
		return pose;
	}

	public void setPose(double pose) {
		this.pose = pose;
	}

	public Motor getMotor1() {
		return motor1;
	}

	public void setMotor1(Motor motor1) {
		this.motor1 = motor1;
	}

	public Motor getMotor2() {
		return motor2;
	}

	public void setMotor2(Motor motor2) {
		this.motor2 = motor2;
	}

	public Motor getMotor3() {
		return motor3;
	}

	public void setMotor3(Motor motor3) {
		this.motor3 = motor3;
	}

	public Motor getMotor4() {
		return motor4;
	}

	public void setMotor4(Motor motor4) {
		this.motor4 = motor4;
	}
	public double getMotor1Power() {
		return motor1.getPower();
	}
	public void setMotor1Power(double val) {
		this.motor1.setPower(val);
	}
	public double getMotor2Power() {
		return motor2.getPower();
	}
	public void setMotor2Power(double val) {
		this.motor2.setPower(val);
	}
	public double getMotor3Power() {
		return motor3.getPower();
	}
	public void setMotor3Power(double val) {
		this.motor3.setPower(val);
	}
	public double getMotor4Power() {
		return motor4.getPower();
	}
	public void setMotor4Power(double val) {
		this.motor4.setPower(val);
	}
	public Direction getMotor1Dir() {
		return motor1.getDir();
	}
	public void setMotor1Dir(Direction dir) {
		this.motor1.setDir(dir);
	}
	public Direction getMotor2Dir() {
		return motor2.getDir();
	}
	public void setMotor2Dir(Direction dir) {
		this.motor2.setDir(dir);
	}
	public Direction getMotor3Dir() {
		return motor3.getDir();
	}
	public void setMotor3Dir(Direction dir) {
		this.motor3.setDir(dir);
	}
	public Direction getMotor4Dir() {
		return motor4.getDir();
	}
	public void setMotor4Dir(Direction dir) {
		this.motor4.setDir(dir);
	}
	public int getRobotX() {
		return RobotX;
	}
	public void setRobotX(int x) {
		this.RobotX = x;
	}
	public int getRobotY() {
		return RobotY;
	}
	public void setRobotY(int y) {
		this.RobotY = y;
	}

	public double getRobotAngle() {
		return RobotAngle;
	}

	public void setRobotAngle(double robotAngle) {
		RobotAngle = robotAngle;
	}
	//methods 
	public static void updatePosition(int time ) {
		
	}
}
