package gui;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class EmployeeWindow extends JFrame implements Observer {

    private JTextArea textArea;
    private JScrollPane jScrollPane;
    public static boolean flag = false;

    public EmployeeWindow() {
        super("employee window");
        this.setSize(1200, 600);

        textArea = new JTextArea(30, 100);
        jScrollPane = new JScrollPane(textArea);
        JPanel panel = new JPanel();
        panel.add(jScrollPane);
        this.setContentPane(panel);
    }

    public void setTextArea(String text) {
        this.textArea.setText(text);
    }

    public void appendTextArea(String text) {
        this.textArea.append(text);
    }

    @Override
    public void update(Observable o, Object arg) {
        flag = true;
    }

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        EmployeeWindow.flag = flag;
    }
}
