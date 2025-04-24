package Model;

/**
 *Quade Garner - qmgarner
 *CIS171 20432 
 *Apr 17, 2025
 */
public class Robot {
    // Motors in the order: LEFT_TOP, RIGHT_TOP, LEFT_BOTTOM, RIGHT_BOTTOM
    private Motor motor1;
    private Motor motor2;
    private Motor motor3;
    private Motor motor4;
    // Center of the robot
    private double robotX;
    private double robotY;
    private float robotAngle;
    private double robotSize;
    // Direction the robot is facing
    private Heading robotHeading;

    // Constructors

    public Robot( Motor m1, Motor m2, Motor m3, Motor m4, double x, double y, float angle, Heading heading, double robotSize) {
        this.motor1 = m1;
        this.motor2 = m2;
        this.motor3 = m3;
        this.motor4 = m4;
        this.robotX = x;
        this.robotY = y;
        this.robotAngle = angle;
        this.robotHeading = heading != null ? heading : Heading.NORTH; // Default to NORTH if null
        this.robotSize = robotSize;
    }

    // Getters and setters
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

    public double getRobotX() {
        return robotX;
    }

    public void setRobotX(double x) {
        this.robotX = x;
    }

    public double getRobotY() {
        return robotY;
    }

    public void setRobotY(double y) {
        this.robotY = y;
    }

    public float getRobotAngle() {
        return robotAngle;
    }

    public void setRobotAngle(float robotAngle) {
        this.robotAngle = robotAngle;
    }

    public double getRobotSize() {
        return robotSize;
    }

    public void setRobotSize(double robotSize) {
        this.robotSize = robotSize;
    }

    public Heading getRobotHeading() {
        return robotHeading;
    }

    public void setRobotHeading(Heading robotHeading) {
        this.robotHeading = robotHeading;
    }
    public void setAllRobotMotorsPower(double power) {
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);
    }
    public void setAllRobotMotorsDir(Direction dir) {
        motor1.setDir(dir);
        motor2.setDir(dir);
        motor3.setDir(dir);
        motor4.setDir(dir);
    }
    public void setAllRobotMotorsRadius(double radius) {
        motor1.setRadius(radius);
        motor2.setRadius(radius);
        motor3.setRadius(radius);
        motor4.setRadius(radius);
    }
	public void  resetRobotPosition(double x, double y, float angle) {
		this.robotX = x;
		this.robotY = y;
		this.robotAngle = angle;
		this.robotHeading = Heading.EAST;
	}
	public void  resetRobot() {
		robotX = 8.0;
		robotY = 8.0;
		robotAngle = 0;
		robotHeading = Heading.EAST;
		motor1.setPower(1.0);
		motor2.setPower(1.0);
		motor3.setPower(1.0);
		motor4.setPower(1.0);
		motor1.setDir(Direction.FORWARD);
		motor2.setDir(Direction.FORWARD);
		motor3.setDir(Direction.FORWARD);
		motor4.setDir(Direction.FORWARD);
	}

    // Methods
    public void updatePosition(int time) {
        strafe(time);
        turnInPlace(time);
        pivot(time);

        double[] distances = new double[4];
        Motor[] motors = {motor1, motor2, motor3, motor4};

        for (int i = 0; i < 4; i++) {
            Motor m = motors[i];
            double speed = m.getPower();
            if (m.getDir() == Direction.BACKWARDS) {
                speed *= -1;
            }
            double distancePerSecond = speed * m.getCircumfice();
            distances[i] = distancePerSecond * time;
        }
        double avgForward = (distances[0] + distances[1] + distances[2] + distances[3]) / 4.0;
        double angleRad = Math.toRadians(robotAngle);
        double dx = avgForward * Math.cos(angleRad);
        double dy = avgForward * Math.sin(angleRad);

        robotX += dx;
        robotY += dy;

        robotAngle %= 360;

        if (robotAngle < 0) {
            robotAngle += 360;
        }
        if (robotAngle >= 22.5 && robotAngle < 67.5) {
            robotHeading = Heading.NORTHEAST;
        } else if (robotAngle >= 67.5 && robotAngle < 112.5) {
            robotHeading = Heading.NORTH;
        } else if (robotAngle >= 112.5 && robotAngle < 157.5) {
            robotHeading = Heading.NORTHWEST;
        } else if (robotAngle >= 157.5 && robotAngle < 202.5) {
            robotHeading = Heading.WEST;
        } else if (robotAngle >= 202.5 && robotAngle < 247.5) {
            robotHeading = Heading.SOUTHWEST;
        } else if (robotAngle >= 247.5 && robotAngle < 292.5) {
            robotHeading = Heading.SOUTH;
        } else if (robotAngle >= 292.5 && robotAngle < 337.5) {
            robotHeading = Heading.SOUTHEAST;
        } else {
            robotHeading = Heading.EAST;
        }
    }

    private void turnInPlace(int time) {
        double leftAvg = ((motor1.getPower() * (motor1.getDir() == Direction.FORWARD ? 1 : -1)) +
                (motor3.getPower() * (motor3.getDir() == Direction.FORWARD ? 1 : -1))) / 2;
        double rightAvg = ((motor2.getPower() * (motor2.getDir() == Direction.FORWARD ? 1 : -1)) +
                (motor4.getPower() * (motor4.getDir() == Direction.FORWARD ? 1 : -1))) / 2;
        if (Math.signum(leftAvg) != Math.signum(rightAvg)) {
            double rotationSpeed = (rightAvg - leftAvg) * motor1.getCircumfice() / robotSize;
            robotAngle += Math.toDegrees(rotationSpeed * time);
            robotAngle %= 360;
            if (robotAngle < 0) {
                robotAngle += 360;
            }
        }
    }

    private void pivot(int time) {
        boolean leftStationary = motor1.getPower() == 0 && motor3.getPower() == 0;
        boolean rightStationary = motor2.getPower() == 0 && motor4.getPower() == 0;
    
        double activePower = 0;
        int directionMultiplier = 1;
    
        if (leftStationary) {
            // Right motors moving → pivot left (counterclockwise)
            activePower = ((motor2.getPower() * (motor2.getDir() == Direction.FORWARD ? 1 : -1)) +
                           (motor4.getPower() * (motor4.getDir() == Direction.FORWARD ? 1 : -1))) / 2;
            directionMultiplier = -1; // counterclockwise
        } else if (rightStationary) {
            // Left motors moving → pivot right (clockwise)
            activePower = ((motor1.getPower() * (motor1.getDir() == Direction.FORWARD ? 1 : -1)) +
                           (motor3.getPower() * (motor3.getDir() == Direction.FORWARD ? 1 : -1))) / 2;
            directionMultiplier = 1; // clockwise
        } else {
            return; // not a pivot
        }
    
        double rotation = (activePower * motor1.getCircumfice() * time) / (robotSize / 2.0);
        robotAngle += directionMultiplier * Math.toDegrees(rotation);
        robotAngle %= 360;
        if (robotAngle < 0) {
            robotAngle += 360;
        }
    }
    

    private void strafe(int time) {
        // Calculate strafe power based on motor layout
        double strafePower = ((motor1.getPower() * (motor1.getDir() == Direction.FORWARD ? 1 : -1)) +
                              (motor4.getPower() * (motor4.getDir() == Direction.FORWARD ? 1 : -1)) -
                              (motor2.getPower() * (motor2.getDir() == Direction.FORWARD ? 1 : -1)) -
                              (motor3.getPower() * (motor3.getDir() == Direction.FORWARD ? 1 : -1))) / 4.0;
    
        // Total distance to move in strafe direction
        double distance = strafePower * motor1.getCircumfice() * time;
    
        // Strafing direction is perpendicular to heading
        double angleRad = Math.toRadians(robotAngle + 90);
    
        double dx = distance * Math.cos(angleRad);
        double dy = distance * Math.sin(angleRad);
    
        robotX += dx;
        robotY += dy;
    }
    
	public String toString() {
		return String.format("X: %.2f, Y: %.2f, Angle: %.2f°, Heading: %s", 
			robotX, robotY, robotAngle, robotHeading);
	}
	
}
