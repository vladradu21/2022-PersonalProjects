package gui;

import bll.MenuItem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class AdministratorWindow extends JFrame {

    private JLabel titleLabel;
    private JTextField titleTextField;
    private JLabel ratingLabel;
    private JTextField ratingTextField;
    private JLabel caloriesLabel;
    private JTextField caloriesTextField;
    private JLabel proteinLabel;
    private JTextField proteinTextField;
    private JLabel fatLabel;
    private JTextField fatTextField;
    private JLabel sodiumLabel;
    private JTextField sodiumTextField;
    private JLabel priceLabel;
    private JTextField priceTextField;

    private JButton addProducts;
    private JButton deleteProducts;
    private JButton selectRow;
    private JButton modifyProducts;
    private JButton createNewProducts;
    private JButton viewAllProducts;
    private JButton importCSV;
    private JButton generateRaports;

    private JScrollPane scrollPane;
    private JTable jTable;
    private DefaultTableModel defaultTableModel;

    public AdministratorWindow() {
        super("admin window");
        this.setSize(1100,450);

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel3 = new JPanel();

        titleLabel = new JLabel("Title");
        titleTextField = new JTextField(5);
        ratingLabel = new JLabel("Rating");
        ratingTextField = new JTextField(5);
        caloriesLabel = new JLabel("Calories");
        caloriesTextField = new JTextField(5);
        proteinLabel = new JLabel("Protein");
        proteinTextField = new JTextField(5);
        fatLabel = new JLabel("Fat");
        fatTextField = new JTextField(5);
        sodiumLabel = new JLabel("Sodium");
        sodiumTextField = new JTextField(5);
        priceLabel = new JLabel("Price");
        priceTextField = new JTextField(5);

        addProducts = new JButton("Add Product");
        deleteProducts = new JButton("Delete Products");
        selectRow = new JButton("Select Row");
        modifyProducts = new JButton("Modify Products");
        panel3.add(selectRow);
        panel3.add(modifyProducts);
        panel3.setLayout(new GridLayout(1, 2));
        createNewProducts = new JButton("Create Composed Product");
        viewAllProducts = new JButton("View All Products");
        importCSV = new JButton("Import CSV file");
        generateRaports = new JButton("Generate Raports");

        panel1.add(titleLabel);
        panel1.add(titleTextField);
        panel1.add(ratingLabel);
        panel1.add(ratingTextField);
        panel1.add(caloriesLabel);
        panel1.add(caloriesTextField);
        panel1.add(proteinLabel);
        panel1.add(proteinTextField);
        panel1.add(fatLabel);
        panel1.add(fatTextField);
        panel1.add(sodiumLabel);
        panel1.add(sodiumTextField);
        panel1.add(priceLabel);
        panel1.add(priceTextField);
        panel1.add(addProducts);
        panel1.add(deleteProducts);
        panel1.add(panel3);
        panel1.add(createNewProducts);
        panel1.add(viewAllProducts);
        panel1.add(importCSV);
        panel1.add(generateRaports);
        panel1.setLayout(new GridLayout(11, 2));

        jTable = new JTable();
        scrollPane = new JScrollPane(jTable);
        panel.add(scrollPane);
        panel.add(panel1);
        panel.setLayout(new GridLayout(1, 2));

        this.setContentPane(panel);
    }

    public void setDataToTable(List<MenuItem> menuItemList) {
        defaultTableModel = new DefaultTableModel();
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};

        for(String column : columnNames) {
            defaultTableModel.addColumn(column);
        }

        Object[] data;
        for(MenuItem item : menuItemList) {
            data = new Object[]{item.getTitle(), item.getRating(), item.getCalories(), item.getProtein(), item.getFat(), item.getSodium(), item.getPrice()};
            defaultTableModel.addRow(data);
        }

        jTable.setModel(defaultTableModel);
    }

    public void addAddProductsButtonListener(ActionListener e) {
        addProducts.addActionListener(e);
    }

    public void addDeleteProductsButtonListener(ActionListener e) {
        deleteProducts.addActionListener(e);
    }

    public void addSelectRowButtonListener(ActionListener e) {
        selectRow.addActionListener(e);
    }

    public void addModifyProductsButtonListener(ActionListener e) {
        modifyProducts.addActionListener(e);
    }

    public void addCreateNewProductsButtonListener(ActionListener e) {
        createNewProducts.addActionListener(e);
    }

    public void addViewAllProductsButtonListener(ActionListener e) {
        viewAllProducts.addActionListener(e);
    }

    public void addImportCSVButtonListener(ActionListener e) {
        importCSV.addActionListener(e);
    }

    public void addGenereateRaportsListener(ActionListener e) {
        generateRaports.addActionListener(e);
    }

    public JTable getjTable() {
        return jTable;
    }

    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

    public String getTitleTextField() {
        return titleTextField.getText();
    }

    public Double getRatingTextField() {
        return Double.parseDouble(ratingTextField.getText());
    }

    public Integer getCaloriesTextField() {
        return Integer.parseInt(caloriesTextField.getText());
    }

    public Integer getProteinTextField() {
        return Integer.parseInt(proteinTextField.getText());
    }

    public Integer getFatTextField() {
        return Integer.parseInt(fatTextField.getText());
    }

    public Integer getSodiumTextField() {
        return Integer.parseInt(sodiumTextField.getText());
    }

    public Integer getPriceTextField() {
        return Integer.parseInt(priceTextField.getText());
    }

    public void setTitleTextField(String text) {
        this.titleTextField.setText(text);
    }

    public void setRatingTextField(String text) {
        this.ratingTextField.setText(text);
    }

    public void setCaloriesTextField(String text) {
        this.caloriesTextField.setText(text);
    }

    public void setProteinTextField(String text) {
        this.proteinTextField.setText(text);
    }

    public void setFatTextField(String text) {
        this.fatTextField.setText(text);
    }

    public void setSodiumTextField(String text) {
        this.sodiumTextField.setText(text);
    }

    public void setPriceTextField(String text) {
        this.priceTextField.setText(text);
    }
}
