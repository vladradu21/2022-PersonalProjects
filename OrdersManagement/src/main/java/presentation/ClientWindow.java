package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * reprezinta fereastra cu detalile clientilor, contine butoane texfielduri si tabelul in care sunt afisate datele
 */
public class ClientWindow extends JFrame {

    private JLabel clientIdLabel;
    private JTextField clientIdTextField;
    private JLabel nameClientLabel;
    private JTextField nameClientTextField;
    private JLabel addressLabel;
    private JTextField addressTexfield;
    private JLabel emailLabel;
    private JTextField emailTextField;

    private JButton addNewClient;
    private JButton editClient;
    private JButton deleteClient;
    private JButton viewAllClients;

    private JTable myTable = new JTable();

    /**
     * constructorul ferestrei se decalara panorui si textfielduri
     */
    public ClientWindow() {
        this.setSize(550,300);

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        clientIdLabel = new JLabel("Client ID:");
        clientIdTextField = new JTextField(5);
        nameClientLabel = new JLabel("Client name:");
        nameClientTextField = new JTextField(5);
        addressLabel = new JLabel("Address:");
        addressTexfield = new JTextField(5);
        emailLabel = new JLabel("Client email:");
        emailTextField = new JTextField(5);

        panel1.add(clientIdLabel);
        panel1.add(clientIdTextField);
        panel1.add(nameClientLabel);
        panel1.add(nameClientTextField);
        panel1.add(addressLabel);
        panel1.add(addressTexfield);
        panel1.add(emailLabel);
        panel1.add(emailTextField);
        panel1.setLayout(new GridLayout(4, 2));

        addNewClient = new JButton("Add New Client");
        editClient = new JButton("Edit Client");
        deleteClient = new JButton("Delete Client");
        viewAllClients = new JButton("View All Clients");

        panel2.add(addNewClient);
        panel2.add(editClient);
        panel2.add(deleteClient);
        panel2.add(viewAllClients);
        panel2.setLayout(new GridLayout(4,1));

        panel3.add(panel1);
        panel3.add(panel2);
        panel3.setLayout(new GridLayout(2, 1));
        panel.add(panel3);
        panel.add(new JScrollPane(myTable));
        panel.setLayout(new GridLayout(1, 2));
        this.setContentPane(panel);
    }

    /**
     * @return permite accesul la variabila de tip JTable
     */
    public JTable getMyTable() {
        return myTable;
    }

    public Integer getClientIdTextField() {
        return Integer.parseInt(clientIdTextField.getText());
    }

    public String getNameClientTextField() {
        return nameClientTextField.getText();
    }

    public String getAddressTexfield() {
        return addressTexfield.getText();
    }

    public String getEmailTextField() {
        return emailTextField.getText();
    }

    public void addNewClientButtonListener(ActionListener e) {
        addNewClient.addActionListener(e);
    }

    public void addEditClientButtonListener(ActionListener e) {
        editClient.addActionListener(e);
    }

    public void addDeleteClientButtonListener(ActionListener e) {
        deleteClient.addActionListener(e);
    }

    public void addViewAllClientsButtonListener(ActionListener e) {
        viewAllClients.addActionListener(e);
    }
}
