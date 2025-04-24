package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Robot;

public class SidebarPanel extends JPanel {

    private final JPanel blockContainer;
    private final JButton addBlockButton;
    private final JButton runButton;
    private final java.util.List<ActionBlock> blocks;
    private final Robot robot;
    private final FieldPanel field;

    public SidebarPanel(Robot robot, FieldPanel field) {
        this.field = field;
        this.robot = robot;
        this.blocks = new ArrayList<>();

        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        blockContainer = new JPanel();
        blockContainer.setLayout(new BoxLayout(blockContainer, BoxLayout.Y_AXIS));
        blockContainer.setBackground(Color.GRAY);

        JScrollPane scrollPane = new JScrollPane(blockContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        addBlockButton = new JButton("Add Block");
        runButton = new JButton("Run All");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(addBlockButton);
        buttonPanel.add(runButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addNewBlock();

        addBlockButton.addActionListener(e -> addNewBlock());

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ActionBlock block : blocks) {
                    block.executeAction();
                }
            }
        });
    }

    private void addNewBlock() {
        ActionBlock newBlock = new ActionBlock(robot, field);
        blocks.add(newBlock);
        blockContainer.add(newBlock);
        blockContainer.revalidate();
        blockContainer.repaint();
    }
}
