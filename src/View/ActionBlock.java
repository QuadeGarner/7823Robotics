package View;

import javax.swing.*;
import java.awt.*;
import Model.Robot;
import Model.Direction;

public class ActionBlock extends JPanel {

    private final JComboBox<String> actionType;
    private final JTextField inputField;
    private final JComboBox<String> directionDropdown;
    private final JLabel inputLabel;
    private final Robot robot;
    private final FieldPanel field;

    public ActionBlock(Robot robot, FieldPanel field) {
        this.robot = robot;
        this.field = field;

        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

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

        add(actionType, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        actionType.addActionListener(e -> updateInputVisibility());
        updateInputVisibility();
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
                    int time = Integer.parseInt(inputField.getText().trim());
                    robot.updatePosition(time);
                    field.repaint();
                    break;
                case "Set All Motors Power":
                    double power = Double.parseDouble(inputField.getText().trim());
                    robot.setAllRobotMotorsPower(power);
                    break;
                case "Set Motor 1 Power":
                    robot.setMotor1Power(Double.parseDouble(inputField.getText().trim()));
                    break;
                case "Set Motor 2 Power":
                    robot.setMotor2Power(Double.parseDouble(inputField.getText().trim()));
                    break;
                case "Set Motor 3 Power":
                    robot.setMotor3Power(Double.parseDouble(inputField.getText().trim()));
                    break;
                case "Set Motor 4 Power":
                    robot.setMotor4Power(Double.parseDouble(inputField.getText().trim()));
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}