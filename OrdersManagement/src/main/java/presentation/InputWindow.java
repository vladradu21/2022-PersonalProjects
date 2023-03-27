package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * reprezinta fereastra ce se deschide la momentul pornirii aplicatiei
 */
public class InputWindow extends JFrame {

    private JButton client;
    private JButton product;
    private JButton order;

    public InputWindow() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,300);

        JPanel panel = new JPanel();

        client = new JButton("Client options");
        product = new JButton("Product options");
        order = new JButton("Order options");

        panel.add(client);
        panel.add(product);
        panel.add(order);

        panel.setLayout(new GridLayout(3, 1));
        this.setContentPane(panel);
    }

    public void addClientButtonListener(ActionListener e) {
        client.addActionListener(e);
    }

    public void addProductButtonListener(ActionListener e) {
        product.addActionListener(e);
    }

    public void addOrderButtonListener(ActionListener e) {
        order.addActionListener(e);
    }

}
