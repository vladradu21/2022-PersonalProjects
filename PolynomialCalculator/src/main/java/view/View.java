package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JLabel primulPolinomLabel;
    public JTextField primulPolinomField;
    private JLabel alDoileaPolinomLabel;
    public JTextField alDoileaPolinomField;
    private JLabel rezultatLabel;
    public JTextPane rezultatText;

    private JButton addButton;
    private JButton subButton;
    private JButton mulButton;
    private JButton divButton;
    private JButton derivateButton;
    private JButton integrateButton;

    public View() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        primulPolinomLabel = new JLabel("    primul polinom =");
        primulPolinomField = new JTextField("1x^3-2x^2+6x^1-5x^0");
        alDoileaPolinomLabel = new JLabel("    al doilea polinom =");
        alDoileaPolinomField = new JTextField("1x^2-1x^0");
        rezultatLabel = new JLabel("    rezultatul este =");
        rezultatText = new JTextPane();

        panel1.add(primulPolinomLabel);
        panel1.add(primulPolinomField);
        panel1.add(alDoileaPolinomLabel);
        panel1.add(alDoileaPolinomField);
        panel1.add(rezultatLabel);
        panel1.add(rezultatText);
        panel1.setLayout(new GridLayout(3, 2));


        addButton = new JButton("adunare");
        subButton = new JButton("scadere");
        mulButton = new JButton("inmultire");
        divButton = new JButton("impartire");
        derivateButton = new JButton("derivare");
        integrateButton = new JButton("integrare");

        panel2.add(addButton);
        panel2.add(subButton);
        panel2.add(mulButton);
        panel2.add(divButton);
        panel2.add(derivateButton);
        panel2.add(integrateButton);
        panel2.setLayout(new GridLayout(3, 2, 5, 5));


        panel.add(panel1);
        panel.add(panel2);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.setContentPane(panel);
    }

    public String getPrimulPolinomField() {
        return primulPolinomField.getText();
    }

    public String getAlDoileaPolinomField() {
        return alDoileaPolinomField.getText();
    }

    public void addAdunareListener(ActionListener e1) {
        addButton.addActionListener(e1);
    }

    public void addScadereListener(ActionListener e2) {
        subButton.addActionListener(e2); }

    public void addInmultireListener(ActionListener e3) {
        mulButton.addActionListener(e3);
    }

    public void addImpartireListener(ActionListener e4) {
        divButton.addActionListener(e4);
    }

    public void addDerivareListener(ActionListener e5) {
        derivateButton.addActionListener(e5);
    }

    public void addIntegrareListener(ActionListener e6) {
        integrateButton.addActionListener(e6);
    }

}
