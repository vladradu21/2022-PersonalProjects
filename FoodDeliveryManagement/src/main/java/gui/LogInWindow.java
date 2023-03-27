package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogInWindow extends JFrame {

    private JLabel userNameLabel;
    private JTextField userNameTextField;
    private JLabel passwordLabel;
    private JTextField passwordTextField;

    private JButton createAccount;
    private JButton loggIn;

    public LogInWindow(String titile) {
        super(titile);
        this.setSize(350, 200);
        JPanel panel = new JPanel();

        userNameLabel = new JLabel("User name:");
        userNameTextField = new JTextField(5);
        passwordLabel = new JLabel("Password:");
        passwordTextField = new JTextField(5);
        createAccount = new JButton("Create Account");
        loggIn = new JButton("Logg In");

        panel.add(userNameLabel);
        panel.add(userNameTextField);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(createAccount);
        panel.add(loggIn);

        panel.setLayout(new GridLayout(3, 2));
        this.setContentPane(panel);
    }

    public void addCreateAccountListener(ActionListener e) {
        createAccount.addActionListener(e);
    }

    public void addLoggInListener(ActionListener e) {
        loggIn.addActionListener(e);
    }

    public String getUserNameTextField() {
        return userNameTextField.getText();
    }

    public String getPasswordTextField() {
        return passwordTextField.getText();
    }

    public JButton getCreateAccount() {
        return createAccount;
    }
}
