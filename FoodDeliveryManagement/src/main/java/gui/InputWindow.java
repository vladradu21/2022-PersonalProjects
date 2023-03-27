package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputWindow extends JFrame {

    private JButton administrator;
    private JButton client;
    private JButton employee;

    public InputWindow() {
        super("input window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,300);

        JPanel panel = new JPanel();


        administrator = new JButton("Administrator options");
        client = new JButton("Client options");
        employee = new JButton("Employee options");

        panel.add(administrator);
        panel.add(client);
        panel.add(employee);

        panel.setLayout(new GridLayout(3, 1));
        this.setContentPane(panel);
    }

    public void addAdministratorButtonListener(ActionListener e) {
        administrator.addActionListener(e);
    }

    public void addClinetButtonListener(ActionListener e) {
        client.addActionListener(e);
    }

    public void addEmployeeButtonListener(ActionListener e) {
        employee.addActionListener(e);
    }

}
