import Model.*;
public class Tester {
    public static void main(String[] args) {
       Motor motor1 = new Motor(Direction.FORWARD,1.0, 1.5);
       Motor motor2 = new Motor(Direction.FORWARD,1.0, 1.5);
       Motor motor3 = new Motor(Direction.FORWARD,1.0, 1.5);
       Motor motor4 = new Motor(Direction.FORWARD,1.0, 1.5);
       Robot robot = new Robot( motor1, motor2, motor3, motor4, 8.0, 8.0, 0, Heading.EAST, 256.0);
       System.out.println("Initial Robot Position: (" + robot.getRobotX() + " "+robot.getRobotAngle()+" "+robot.getRobotHeading()+")");        
       System.out.println("Forward Movement Test");
       robot.updatePosition(15);
       System.out.println("Robot Position: (" + robot.getRobotX() + ", " + robot.getRobotY() + " "+robot.getRobotAngle()+" "+robot.getRobotHeading()+")");
       robot.resetRobot();
       robot.setMotor1Dir(Direction.BACKWARDS);
       robot.setMotor4Dir(Direction.BACKWARDS);
       System.out.println("Strafe Left Movement Test");
       robot.updatePosition(15);
       System.out.println("Robot Position: (" + robot.getRobotX() + ", " + robot.getRobotY() + " "+robot.getRobotAngle()+" "+robot.getRobotHeading()+")");
       robot.resetRobot();
       System.out.println();
       System.out.println("Strafe Right Movement Test");
       robot.setMotor2Dir(Direction.BACKWARDS);
       robot.setMotor3Dir(Direction.BACKWARDS);
       robot.updatePosition(15);
       System.out.println("Robot Position: (" + robot.getRobotX() + ", " + robot.getRobotY() + " "+robot.getRobotAngle()+" "+robot.getRobotHeading()+")");
       robot.resetRobot();
       System.out.println();
       System.out.println("Left Turn Movement Test");
       robot.setMotor1Dir(Direction.BACKWARDS);
       robot.setMotor3Dir(Direction.BACKWARDS);
       robot.updatePosition(15);
       System.out.println("Robot Position: (" + robot.getRobotX() + ", " + robot.getRobotY() + " "+robot.getRobotAngle()+" "+robot.getRobotHeading()+")");
       robot.resetRobot();
       System.out.println();
       System.out.println("Right Turn Movement Test");
       robot.setMotor2Dir(Direction.BACKWARDS);
       robot.setMotor4Dir(Direction.BACKWARDS);
       robot.updatePosition(15);
       System.out.println("Robot Position: (" + robot.getRobotX() + ", " + robot.getRobotY() + " "+robot.getRobotAngle()+" "+robot.getRobotHeading()+")");
       robot.resetRobot();
       System.out.println();
       System.out.println("Pivot Left Movement Test");
       robot.setMotor1Power(0.0);
       robot.setMotor3Power(0.0);
       robot.updatePosition(15);
       System.out.println("Robot Position: (" + robot.getRobotX() + ", " + robot.getRobotY() + " "+robot.getRobotAngle()+" "+robot.getRobotHeading()+")");
       robot.resetRobot();
       System.out.println();
       System.out.println("Pivot Right Movement Test");
       robot.setMotor2Power(0.0);
       robot.setMotor4Power(0.0);
       robot.updatePosition(15);
       System.out.println("Robot Position: (" + robot.getRobotX() + ", " + robot.getRobotY() + " "+robot.getRobotAngle()+" "+robot.getRobotHeading()+")");
    }

}
