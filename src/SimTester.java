

import javax.swing.JFrame;

import Model.Direction;
import Model.Heading;
import Model.Field;
import Model.Motor;
import Model.Robot;
import View.FieldPanel; 

public class SimTester {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Robot Simulation");
        Motor motor1 = new Motor(Direction.FORWARD, 1.0, 2.0);
        Motor motor2 = new Motor(Direction.FORWARD, 1.0, 2.0);
        Motor motor3 = new Motor(Direction.FORWARD, 1.0, 2.0);
        Motor motor4 = new Motor(Direction.FORWARD, 1.0, 2.0);
        Robot robot = new Robot(motor1, motor2, motor3, motor4, 12.0, 12.0, 0.0f, Heading.EAST, 144.0);
        Field field = new Field(144, 144, robot); // Create a field with default dimensions

        FieldPanel fieldPanel = new FieldPanel(field, robot); // Create a field panel with the field and robot
        frame.add(fieldPanel); // Add the field panel to the frame
        frame.setSize(800, 800); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
        frame.setVisible(true); // Make the frame visible
    }
}
