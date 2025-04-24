package View;

import javax.swing.JPanel;
import Model.Field;
import Model.Robot;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class FieldPanel extends JPanel {
    private Field field;
    private double scale; // Scale factor for converting inches to pixels
    private final Robot robot;

    public FieldPanel(Field field, Robot robot) {
        this.field = field;
        this.robot = robot;
        setBackground(Color.WHITE);;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // update the field dimensions based on the current size of the panel
        updateScale();
        Graphics2D g2d = (Graphics2D) g;
        drawField(g2d);
        drawGrid(g2d);
        drawRobot(g2d);
    }

    private void updateScale() {
        int minSize = Math.min(getWidth(), getHeight());
        int fieldSizeInches = Math.max(field.getWidthInInches(), field.getHeightInInches());
        scale = (double) minSize / fieldSizeInches; // Scale factor based on the panel size and field size
    }

    private int toPixels(double inches){
        // Convert inches to pixels based on the current scale
        return (int) (inches * scale);
    }

    private void drawField(Graphics2D g2) {
        g2.setColor(Color.LIGHT_GRAY);
        int step = toPixels(12); // Every foot
        for (int i = 0; i <= field.getWidthInInches() / 12; i++) {
            int x = i * step;
            g2.drawLine(x, 0, x, toPixels(field.getHeightInInches()));
        }
        for (int i = 0; i <= field.getHeightInInches() / 12; i++) {
            int y = i * step;
            g2.drawLine(0, y, toPixels(field.getWidthInInches()), y);
        }

        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, toPixels(field.getWidthInInches()), toPixels(field.getHeightInInches()));
    }

    private void drawGrid(Graphics2D g2) {
        // Grid line color
        g2.setColor(new Color(200, 200, 200)); // Light gray for the grid

        // Grid spacing in inches (let's make each grid square 1 ft for simplicity)
        int gridSpacingInches = 12;  // Grid every foot (12 inches)

        // Calculate the number of grid lines to draw
        int gridStep = toPixels(gridSpacingInches);

        // Draw vertical grid lines
        for (int i = 0; i <= field.getWidthInInches() / gridSpacingInches; i++) {
            int x = i * gridStep;
            g2.drawLine(x, 0, x, toPixels(field.getHeightInInches()));
        }

        // Draw horizontal grid lines
        for (int i = 0; i <= field.getHeightInInches() / gridSpacingInches; i++) {
            int y = i * gridStep;
            g2.drawLine(0, y, toPixels(field.getWidthInInches()), y);
        }
    }

    private void drawRobot(Graphics2D g2d) {
        // Draw the robot
        double robotArea = robot.getRobotSize();
        double robotSideLength = Math.sqrt(robotArea);

        int robotSizePixels = toPixels(robotSideLength);
        int robotX = toPixels(robot.getRobotX());
        int robotY = toPixels(robot.getRobotY());

        int sizeOffset = robotSizePixels / 2;

        AffineTransform originalTransform = g2d.getTransform();

        g2d.translate(robotX, robotY);
        g2d.rotate(Math.toRadians(robot.getRobotAngle()));

        g2d.setColor(Color.BLUE);
        g2d.fillRect(-sizeOffset, -sizeOffset, robotSizePixels, robotSizePixels);

        g2d.setColor(Color.RED);
        g2d.drawLine(0, 0, sizeOffset, 0);

        g2d.setTransform(originalTransform);
    }
}
