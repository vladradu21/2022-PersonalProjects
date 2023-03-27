package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * reprezinta fereastra cu detalile comenzilor, contine butoane texfielduri si tabelul in care sunt afisate datele
 */
public class OrderWindow extends JFrame{

    private JLabel orderIdLabel;
    private JTextField orderIdTextField;
    private JLabel clientIdLabel;
    private JTextField clientIdTextField;
    private JLabel productIdLabel;
    private JTextField productIdTextField;
    private JLabel productQuatityLabel;
    private JTextField productQuantityTextField;
    private JButton createOrder;
    private JButton deleteOrder;
    private JButton viewAllOrders;
    private JTextArea textArea;

    private JTable myTable = new JTable();

    public OrderWindow() {

        this.setSize(550,300);

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        orderIdLabel = new JLabel("Order ID:");
        orderIdTextField = new JTextField(5);
        clientIdLabel = new JLabel("Client ID:");
        clientIdTextField = new JTextField(5);
        productIdLabel = new JLabel("Product ID:");
        productIdTextField = new JTextField(5);
        productQuatityLabel = new JLabel("Quantity:");
        productQuantityTextField = new JTextField(5);

        panel1.add(orderIdLabel);
        panel1.add(orderIdTextField);
        panel1.add(clientIdLabel);
        panel1.add(clientIdTextField);
        panel1.add(productIdLabel);
        panel1.add(productIdTextField);
        panel1.add(productQuatityLabel);
        panel1.add(productQuantityTextField);
        panel1.setLayout(new GridLayout(5, 2));

        createOrder = new JButton("Create Order");
        deleteOrder = new JButton("Delete Order");
        viewAllOrders = new JButton("View All Orders");

        panel2.add(createOrder);
        panel2.add(deleteOrder);
        panel2.add(viewAllOrders);
        panel2.setLayout(new GridLayout(4, 1));

        panel3.add(panel1);
        panel3.add(panel2);
        panel3.setLayout(new GridLayout(2, 1));

        textArea = new JTextArea(5, 5);
        panel4.add(myTable);
        panel4.add(textArea);
        panel4.setLayout(new GridLayout(2,1));

        panel.add(panel3);
        panel.add(panel4);
        panel.setLayout(new GridLayout(1, 2));
        this.setContentPane(panel);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JTable getMyTable() {
        return myTable;
    }

    public void addCreateOrderButtonListener(ActionListener e) {
        createOrder.addActionListener(e);
    }

    public void addDeleteOrderButtonListener(ActionListener e) {deleteOrder.addActionListener(e);
    }

    public void addViewAllOrdersButtonListener(ActionListener e) {
        viewAllOrders.addActionListener(e);
    }

    public Integer getOrderIdTextField() {
        return Integer.parseInt(orderIdTextField.getText());
    }

    public Integer getClientIdTextField() {
        return Integer.parseInt(clientIdTextField.getText());
    }

    public Integer getProductIdTextField() {
        return Integer.parseInt(productIdTextField.getText());
    }

    public Integer getProductQuantityTextField() {
        return Integer.parseInt(productQuantityTextField.getText());
    }
}
