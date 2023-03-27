package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * reprezinta fereastra cu detalile produselor, contine butoane texfielduri si tabelul in care sunt afisate datele
 */
public class ProductWindow extends JFrame{

    private JLabel idProductLabel;
    private JTextField idProductTextField;
    private JLabel nameProductLabel;
    private JTextField nameProductTextField;
    private JLabel stockLabel;
    private JTextField stockTextField;
    private JLabel priceLabel;
    private JTextField priceTextField;

    private JButton addNewProduct;
    private JButton editProduct;
    private JButton deleteProduct;
    private JButton viewAllProducts;

    private JTable myTable = new JTable();

    public ProductWindow() {

        this.setSize(550,300);

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        idProductLabel = new JLabel("Product id:");
        idProductTextField = new JTextField(5);
        nameProductLabel = new JLabel("Product name;");
        nameProductTextField = new JTextField(5);
        stockLabel = new JLabel("Stock of product:");
        stockTextField = new JTextField(5);
        priceLabel = new JLabel("Product price:");
        priceTextField = new JTextField(5);

        panel1.add(idProductLabel);
        panel1.add(idProductTextField);
        panel1.add(nameProductLabel);
        panel1.add(nameProductTextField);
        panel1.add(stockLabel);
        panel1.add(stockTextField);
        panel1.add(priceLabel);
        panel1.add(priceTextField);
        panel1.setLayout(new GridLayout(4, 2));

        addNewProduct = new JButton("Add New Product");
        editProduct = new JButton("Edit Product");
        deleteProduct = new JButton("Delete Product");
        viewAllProducts = new JButton("View All Products");

        panel2.add(addNewProduct);
        panel2.add(editProduct);
        panel2.add(deleteProduct);
        panel2.add(viewAllProducts);
        panel2.setLayout(new GridLayout(4, 1));

        panel3.add(panel1);
        panel3.add(panel2);
        panel3.setLayout(new GridLayout(2, 1));

        panel.add(panel3);
        panel.add(new JScrollPane(myTable));
        panel.setLayout(new GridLayout(1, 2));
        this.setContentPane(panel);
    }

    public JTable getMyTable() {
        return myTable;
    }
    public Integer getIdProductTextField() {
        return Integer.parseInt(idProductTextField.getText());
    }

    public String getNameProductTextField() {
        return nameProductTextField.getText();
    }

    public Integer getStockTextField() {
        return Integer.parseInt(stockTextField.getText());
    }

    public Double getPriceTextField() {
        return Double.parseDouble(priceTextField.getText());
    }

    public void addNewProductButtonListener(ActionListener e) {
        addNewProduct.addActionListener(e);
    }

    public void addEditProductButtonListener(ActionListener e) {
        editProduct.addActionListener(e);
    }

    public void addDeleteProductButtonListener(ActionListener e) {
        deleteProduct.addActionListener(e);
    }

    public void addViewAllProductsButtonListener(ActionListener e) {
        viewAllProducts.addActionListener(e);
    }
}
