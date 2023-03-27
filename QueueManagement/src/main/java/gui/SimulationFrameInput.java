package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationFrameInput extends JFrame {

    private JLabel noClientsLabel;
    private JTextField noClientsField;
    private JLabel noQueuesLabel;
    private JTextField noQueuesField;
    private JLabel simulationIntervalLabel;
    private JTextField simulationIntervalField;
    private JLabel minArrivalTimeLabel;
    private JTextField minArrivalTimeField;
    private JLabel maxArrivalTimeLabel;
    private JTextField maxArrivalTimeField;
    private JLabel minServiceTimeLabel;
    private JTextField minServiceTimeField;
    private JLabel maxServiceTimeLabel;
    private JTextField maxServiceTimeField;
    private JButton validateButton;

    public SimulationFrameInput() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350,400);

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        noClientsLabel = new JLabel("number of clients:");
        noClientsField = new JTextField("5");
        noQueuesLabel = new JLabel("number of queues:");
        noQueuesField = new JTextField("2");
        simulationIntervalLabel = new JLabel("simulation interval:");
        simulationIntervalField = new JTextField("60");
        minArrivalTimeLabel = new JLabel("minimum arrival time:");
        minArrivalTimeField = new JTextField("1");
        maxArrivalTimeLabel = new JLabel("maximum arrival time:");
        maxArrivalTimeField = new JTextField("10");
        minServiceTimeLabel = new JLabel("minimum service time:");
        minServiceTimeField = new JTextField("2");
        maxServiceTimeLabel = new JLabel("maximum serivce time:");
        maxServiceTimeField = new JTextField("6");
        validateButton = new JButton("validate data");

        panel1.add(noClientsLabel);
        panel1.add(noClientsField);
        panel1.add(noQueuesLabel);
        panel1.add(noQueuesField);
        panel1.add(simulationIntervalLabel);
        panel1.add(simulationIntervalField);
        panel1.add(minArrivalTimeLabel);
        panel1.add(minArrivalTimeField);
        panel1.add(maxArrivalTimeLabel);
        panel1.add(maxArrivalTimeField);
        panel1.add(minServiceTimeLabel);
        panel1.add(minServiceTimeField);
        panel1.add(maxServiceTimeLabel);
        panel1.add(maxServiceTimeField);
        panel1.setLayout(new GridLayout(7, 2));

        panel2.add(validateButton);

        panel.add(panel1);
        panel.add(panel2);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.setContentPane(panel);
    }

    public String getNoClientsField() {
        return noClientsField.getText();
    }

    public String getNoQueuesField() {
        return noQueuesField.getText();
    }

    public String getMinArivalTimeField() {
        return minArrivalTimeField.getText();
    }

    public String getSimulationIntervalField() {
        return simulationIntervalField.getText();
    }

    public String getMaxArrivalTimeField() {
        return maxArrivalTimeField.getText();
    }

    public String getMinServiceTimeField() {
        return minServiceTimeField.getText();
    }

    public String getMaxServiceTimeField() {
        return maxServiceTimeField.getText();
    }

    public void addValidateButtonListener(ActionListener e) {
        validateButton.addActionListener(e);
    }
}
