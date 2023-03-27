package gui;

import javax.swing.*;

public class NotificationWindow extends JFrame {
    private JTextField notification;

    public NotificationWindow(String text) {
        super("Notification Window");
        this.setSize(200, 200);
        notification = new JTextField(text);

        JPanel panel = new JPanel();
        panel.add(notification);

        this.setContentPane(panel);

    }
}
