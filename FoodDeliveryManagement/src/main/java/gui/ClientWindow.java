package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import bll.MenuItem;
import model.User;

/**
 * reprezinta fereastra cu detalile clientilor, contine butoane texfielduri si tabelul in care sunt afisate datele
 */
public class ClientWindow extends JFrame {

    private JTable table1;
    private DefaultTableModel table1Model;
    private JScrollPane scrollPane;
    private JButton viewProducts;
    private JButton searchProducts;
    private JTextField conditionTextfield;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton selectItem;
    private JButton createOreder;
    private JTextArea textArea2;
    private JScrollPane scrollPane2;
    private User client;

    public ClientWindow(User client) {
        super("client window");
        this.client = client;
        this.setSize(800,450);

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        table1 = new JTable();
        table1.setRowSelectionAllowed(true);
        table1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPane = new JScrollPane(table1);
        viewProducts = new JButton("View Products");
        searchProducts = new JButton("Search");
        conditionTextfield = new JTextField(5);
        String s[] = {"null", "rating", "calories", "proteins", "fats", "sodium", "price"};
        comboBox1 = new JComboBox<>(s);
        String ss[] = {"=", "<", ">"};
        comboBox2 = new JComboBox<>(ss);
        selectItem = new JButton("Select Item");
        createOreder = new JButton("Create Order");
        textArea2 = new JTextArea(50, 50);
        scrollPane2 = new JScrollPane(textArea2);

        panel2.add(searchProducts);
        panel2.add(conditionTextfield);
        panel2.add(comboBox1);
        panel2.add(comboBox2);
        panel2.setLayout(new GridLayout(1, 4));

        panel4.add(selectItem);
        panel4.add(createOreder);
        panel4.setLayout(new GridLayout(1, 2));

        panel1.add(viewProducts);
        panel1.add(panel2);
        panel1.add(panel4);
        panel1.setLayout(new GridLayout(3, 1));

        panel3.add(panel1);
        panel3.add(scrollPane2);
        panel3.setLayout(new GridLayout(2, 1));

        panel.add(scrollPane);
        panel.add(panel3);
        panel.setLayout(new GridLayout(1, 2));


        this.setContentPane(panel);
    }

    public void setDataToTable(List<MenuItem> menuItemList) {
        table1Model= new DefaultTableModel();
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};

        for(String column : columnNames) {
            table1Model.addColumn(column);
        }

        Object[] data;
        for(MenuItem item : menuItemList) {
            data = new Object[]{item.getTitle(), item.getRating(), item.getCalories(), item.getProtein(), item.getFat(), item.getSodium(), item.getPrice()};
            table1Model.addRow(data);
        }

        table1.setModel(table1Model);
    }

    public void addViewProductsButtonListener(ActionListener e){
        viewProducts.addActionListener(e);
    }

    public void addSearchProductsButtonListener(ActionListener e){
        searchProducts.addActionListener(e);
    }

    public void addSelectItemButtonListener(ActionListener e) {
        selectItem.addActionListener(e);
    }

    public void addCreateOrderButtonListener(ActionListener e) {
        createOreder.addActionListener(e);
    }

    public void addComboBox1ButtonListener(ActionListener e) {
        comboBox1.addActionListener(e);
    }

    public JTable getTable1() {
        return table1;
    }

    public DefaultTableModel getTable1Model() {
        return table1Model;
    }

    public void setTextArea2(String text) {
        this.textArea2.setText(text);
    }

    public void appendTextArea2(String text) {
        this.textArea2.append(text + "\n");
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public JComboBox getComboBox2() {
        return comboBox2;
    }

    public String getConditionTextfield() {
        return conditionTextfield.getText();
    }

    public User getClient() {
        return client;
    }
}
