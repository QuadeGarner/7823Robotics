package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Robot;
import Model.Direction;

public class ActionBlock extends JPanel {

    private final JComboBox<String> actionType;
    private final JTextField inputField;
    private final JComboBox<String> directionDropdown;
    private final JLabel inputLabel;
    private final Robot robot;
    private final FieldPanel field;
    private final JButton deleteButton;
    private Point initialClick;

    public ActionBlock(Robot robot, FieldPanel field) {
        this.robot = robot;
        this.field = field;

        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        setTransferHandler(new TransferHandler("actionBlock"));
        actionType = new JComboBox<>(new String[]{
                "Move",
                "Set All Motors Power",
                "Set Motor 1 Power",
                "Set Motor 2 Power",
                "Set Motor 3 Power",
                "Set Motor 4 Power",
                "Set All Motors Radius",
                "Set Motor 1 Direction",
                "Set Motor 2 Direction",
                "Set Motor 3 Direction",
                "Set Motor 4 Direction",
                "Set All Motors Direction",
                "Set Motor 1 Radius",
                "Set Motor 2 Radius",
                "Set Motor 3 Radius",
                "Set Motor 4 Radius"
        });
        deleteButton = new JButton("Delete");
        deleteButton.setForeground(Color.RED);
        // change to set size for the delete text
        deleteButton.setMargin(new Insets(2, 5, 2, 5));
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null) {
                parent.remove(this);
                parent.revalidate();
                parent.repaint();
            }
        });
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(60, 25));

        directionDropdown = new JComboBox<>(new String[]{"FORWARD", "BACKWARDS"});
        directionDropdown.setVisible(false);

        inputLabel = new JLabel("Input:");
        inputLabel.setVisible(true);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel.add(inputLabel);
        centerPanel.add(inputField);
        centerPanel.add(directionDropdown);
        centerPanel.add(deleteButton);

        add(actionType, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        actionType.addActionListener(e -> updateInputVisibility());
        updateInputVisibility();

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                JComponent component = (JComponent) e.getSource();
                TransferHandler handler = component.getTransferHandler();
                if(handler != null) {
                    handler.exportAsDrag(component, e, TransferHandler.COPY);
                }
            }
        });
        
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int offsetX = e.getX() - initialClick.x;
                int offsetY = e.getY() - initialClick.y;
        
                Point currentLocation = getLocation();
                int newX = currentLocation.x + offsetX;
                int newY = currentLocation.y + offsetY;
        
                // Ensure the block stays within the container bounds
                Container parent = getParent();
                if (parent != null) {
                    Dimension parentSize = parent.getSize();
                    newX = Math.max(0, Math.min(newX, parentSize.width - getWidth()));
                    newY = Math.max(0, Math.min(newY, parentSize.height - getHeight()));
                }
        
                setLocation(newX, newY);
            }
        });
    }
    public ActionBlock geActionBlock() {
        return new ActionBlock(robot, field);
    }
    private void updateInputVisibility() {
        String action = (String) actionType.getSelectedItem();
        boolean isDirection = action != null && action.contains("Direction");
        boolean isMoveOrPowerOrRadius = action != null &&
                (action.equals("Move") || action.contains("Power") || action.contains("Radius"));

        inputField.setVisible(isMoveOrPowerOrRadius);
        inputLabel.setVisible(isMoveOrPowerOrRadius);
        directionDropdown.setVisible(isDirection);

        if (isDirection) {
            inputLabel.setText("Direction:");
        } else if (action != null && action.equals("Move")) {
            inputLabel.setText("Seconds:");
        } else if (action != null && action.contains("Power")) {
            inputLabel.setText("Power:");
        } else if (action != null && action.contains("Radius")) {
            inputLabel.setText("Radius:");
        }
    }

    public void executeAction() {
        String action = (String) actionType.getSelectedItem();
    
        try {
            switch (action) {
                case "Move":
                    double time = Double.parseDouble(inputField.getText().trim());
                    robot.updatePosition(time);
                    field.repaint();
                    break;
    
                case "Set All Motors Power":
                case "Set Motor 1 Power":
                case "Set Motor 2 Power":
                case "Set Motor 3 Power":
                case "Set Motor 4 Power":
                    double power = Double.parseDouble(inputField.getText().trim());
                    if (power > 1.0 || power < -1.0) {
                        throw new IllegalArgumentException("Power must be between -1.0 and 1.0");
                    }
                    if (action.equals("Set All Motors Power")) robot.setAllRobotMotorsPower(power);
                    else if (action.equals("Set Motor 1 Power")) robot.setMotor1Power(power);
                    else if (action.equals("Set Motor 2 Power")) robot.setMotor2Power(power);
                    else if (action.equals("Set Motor 3 Power")) robot.setMotor3Power(power);
                    else if (action.equals("Set Motor 4 Power")) robot.setMotor4Power(power);
                    break;
    
                case "Set All Motors Radius":
                    double radius = Double.parseDouble(inputField.getText().trim());
                    robot.setAllRobotMotorsRadius(radius);
                    break;
                case "Set Motor 1 Radius":
                    robot.getMotor1().setRadius(Double.parseDouble(inputField.getText().trim()));
                    break;
                case "Set Motor 2 Radius":
                    robot.getMotor2().setRadius(Double.parseDouble(inputField.getText().trim()));
                    break;
                case "Set Motor 3 Radius":
                    robot.getMotor3().setRadius(Double.parseDouble(inputField.getText().trim()));
                    break;
                case "Set Motor 4 Radius":
                    robot.getMotor4().setRadius(Double.parseDouble(inputField.getText().trim()));
                    break;
    
                case "Set All Motors Direction":
                    robot.setAllRobotMotorsDir(Direction.valueOf(((String) directionDropdown.getSelectedItem()).toUpperCase()));
                    break;
                case "Set Motor 1 Direction":
                    robot.setMotor1Dir(Direction.valueOf(((String) directionDropdown.getSelectedItem()).toUpperCase()));
                    break;
                case "Set Motor 2 Direction":
                    robot.setMotor2Dir(Direction.valueOf(((String) directionDropdown.getSelectedItem()).toUpperCase()));
                    break;
                case "Set Motor 3 Direction":
                    robot.setMotor3Dir(Direction.valueOf(((String) directionDropdown.getSelectedItem()).toUpperCase()));
                    break;
                case "Set Motor 4 Direction":
                    robot.setMotor4Dir(Direction.valueOf(((String) directionDropdown.getSelectedItem()).toUpperCase()));
                    break;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}