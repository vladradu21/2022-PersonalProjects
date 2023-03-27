package gui;

import javax.swing.*;

public class SimulationFrameOutput extends JFrame {

    public JTextArea textArea;

    public SimulationFrameOutput() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350,200);

        JPanel panel = new JPanel();
        textArea = new JTextArea(8, 30);
        panel.add(textArea);

        this.setContentPane(panel);
    }
}
