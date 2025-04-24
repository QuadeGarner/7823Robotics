

import javax.swing.JFrame;

import Model.Direction;
import Model.Heading;
import Model.Field;
import Model.Motor;
import Model.Robot;
import View.FieldPanel;
import View.SidebarPanel; 

public class SimTester {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Robot Simulation");
        Motor motor1 = new Motor(Direction.FORWARD, 1.0, 1.48);
        Motor motor2 = new Motor(Direction.FORWARD, 1.0, 1.48);
        Motor motor3 = new Motor(Direction.FORWARD, 1.0, 1.48);
        Motor motor4 = new Motor(Direction.FORWARD, 1.0, 1.48);
        Robot robot = new Robot(motor1, motor2, motor3, motor4, 16.0, 16.0, 0.0f, Heading.EAST, 265.0);
        Field field = new Field(144, 144, robot); // Create a field with default dimensions

        FieldPanel fieldPanel = new FieldPanel(field, robot); // Create a field panel with the field and robot
        SidebarPanel sidebarPanel = new SidebarPanel(robot, fieldPanel); // Create a sidebar panel with the robot

        frame.add(fieldPanel); // Add the field panel to the frame
        frame.add(sidebarPanel, "East"); // Add the sidebar panel to the frame on the east side
        frame.setSize(800, 800); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
        frame.setVisible(true); // Make the frame visible
    }
}
