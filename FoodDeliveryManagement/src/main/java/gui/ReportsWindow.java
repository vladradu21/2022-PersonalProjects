package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReportsWindow extends JFrame {


    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JLabel startHourLabel;
    private JTextField startHourTextfield;
    private JLabel endHourLabel;
    private JTextField endHourTextfield;
    private JLabel productsOrderedMoreLabel;
    private JTextField productsOrderedMoreTextField;
    private JLabel clientsWhoOrderMoreLabel;
    private JTextField clientsWhoOrderTextfield;
    private JLabel valueOfOrderLabel;
    private JTextField valueOfOrderTextField;
    private JLabel orderedWithinSpecifiedDayLabel;
    private JTextField orderedWithinSpecifiedDayTextField;
    private JButton generateReport1;
    private JButton generateReport2;
    private JButton generateReport3;
    private JButton generateReport4;



    public ReportsWindow() {
        super("reports Window");
        this.setSize(600,400);

        textArea = new JTextArea(50, 50);
        scrollPane = new JScrollPane(textArea);
        startHourLabel = new JLabel("Start Hour");
        startHourTextfield = new JTextField(5);
        endHourLabel = new JLabel("End Hour");
        endHourTextfield = new JTextField(5);
        productsOrderedMoreLabel = new JLabel("ProductsOrderedMore");
        productsOrderedMoreTextField = new JTextField(5);
        clientsWhoOrderMoreLabel = new JLabel("client who ordered more");
        clientsWhoOrderTextfield = new JTextField(5);
        valueOfOrderLabel = new JLabel("& value of order is bigger than");
        valueOfOrderTextField = new JTextField(5);
        orderedWithinSpecifiedDayLabel = new JLabel("orderedWithinSpecifiedDay");
        orderedWithinSpecifiedDayTextField = new JTextField(5);
        generateReport1 = new JButton("Generate Report1!");
        generateReport2 = new JButton("Generate Report2!");
        generateReport3 = new JButton("Generate Report3!");
        generateReport4 = new JButton("Generate Report4!");

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();

        panel1.add(startHourLabel);
        panel1.add(startHourTextfield);
        panel1.add(endHourLabel);
        panel1.add(endHourTextfield);
        panel1.setLayout(new GridLayout(1, 4));

        panel2.add(productsOrderedMoreLabel);
        panel2.add(productsOrderedMoreTextField);
        panel2.setLayout(new GridLayout(1, 2));

        panel3.add(clientsWhoOrderMoreLabel);
        panel3.add(clientsWhoOrderTextfield);
        panel3.add(valueOfOrderLabel);
        panel3.add(valueOfOrderTextField);
        panel3.setLayout(new GridLayout(1, 4));

        panel4.add(orderedWithinSpecifiedDayLabel);
        panel4.add(orderedWithinSpecifiedDayTextField);
        panel4.setLayout(new GridLayout(1, 2));

        panel6.add(generateReport1);
        panel6.add(generateReport2);
        panel6.add(generateReport3);
        panel6.add(generateReport4);

        panel5.add(panel1);
        panel5.add(panel2);
        panel5.add(panel3);
        panel5.add(panel4);
        panel5.add(panel6);
        panel5.setLayout(new GridLayout(5,1));

        panel.add(scrollPane);
        panel.add(panel5);
        panel.setLayout(new GridLayout(2, 1));

        this.setContentPane(panel);
    }

    public void addGenerateReport1Listener(ActionListener e) {
        generateReport1.addActionListener(e);
    }

    public void addGenerateReport2Listener(ActionListener e) {
        generateReport2.addActionListener(e);
    }

    public void addGenerateReport3Listener(ActionListener e) {
        generateReport3.addActionListener(e);
    }

    public void addGenerateReport4Listener(ActionListener e) {
        generateReport4.addActionListener(e);
    }

    public LocalTime getStartHourTextfield() {
        return LocalTime.parse(startHourTextfield.getText());
    }

    public LocalTime  getEndHourTextfield() {
        return LocalTime.parse(endHourTextfield.getText());
    }

    public int getProductsOrderedMoreTextField() {
        return Integer.parseInt(productsOrderedMoreTextField.getText());
    }

    public int getClientsWhoOrderTextfield() {
        return Integer.parseInt(clientsWhoOrderTextfield.getText());
    }

    public int getValueOfOrderTextField() {
        return Integer.parseInt(valueOfOrderTextField.getText());
    }

    public LocalDate getOrderedWithinSpecifiedDayTextField() {
        return LocalDate.parse(orderedWithinSpecifiedDayTextField.getText());
    }

    public void setTextArea(String text) {
        this.textArea.setText(text);
    }

    public void appendTextArea(String text) {
        this.textArea.append(text);
    }
}
