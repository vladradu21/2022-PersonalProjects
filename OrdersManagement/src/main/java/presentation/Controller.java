package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import main.MainClass;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

/**
 * este clasa ce face legatura intre metode si obiectele descrise in model
 * contine toate clasele ce implementeaza ActionListener si metodele de ActionPerformed prin care se realizeaza fiecare operatie
 */
public class Controller {
    protected static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    private InputWindow view;
    private ClientWindow clientWindow;
    private ProductWindow productWindow;
    private OrderWindow orderWindow;

    private int idClient;
    private String nameClient;
    private String address;
    private String email;
    private int idProduct;
    private String nameProduct;
    private int stock;
    private double price;
    private int idOrder;
    private int quantity;

    public Controller (InputWindow view) {
        this.view = view;
        view.addClientButtonListener(new ClientListener());
        view.addProductButtonListener(new ProductListener());
        view.addOrderButtonListener(new OrderListener());

        clientWindow = new ClientWindow();
        productWindow = new ProductWindow();
        orderWindow = new OrderWindow();
    }

    class ClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientWindow.setVisible(false);
            productWindow.setVisible(false);
            orderWindow.setVisible(false);

            clientWindow = new ClientWindow();
            clientWindow.addNewClientButtonListener(new NewClientListener());
            clientWindow.addEditClientButtonListener(new EditClientListener());
            clientWindow.addDeleteClientButtonListener(new DeleteClientListener());
            clientWindow.addViewAllClientsButtonListener(new ViewAllClientsListener());
            clientWindow.setVisible(true);
        }
    }

    class ProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientWindow.setVisible(false);
            productWindow.setVisible(false);
            orderWindow.setVisible(false);

            productWindow = new ProductWindow();
            productWindow.addNewProductButtonListener(new NewProductListener());
            productWindow.addEditProductButtonListener(new EditProductListener());
            productWindow.addDeleteProductButtonListener(new DeleteProductListener());
            productWindow.addViewAllProductsButtonListener(new ViewAllProductsListener());
            productWindow.setVisible(true);
        }
    }

    class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientWindow.setVisible(false);
            productWindow.setVisible(false);
            orderWindow.setVisible(false);

            orderWindow = new OrderWindow();
            orderWindow.addCreateOrderButtonListener(new CreateOrderListener());
            orderWindow.addDeleteOrderButtonListener(new DeleteOrderListener());
            orderWindow.addViewAllOrdersButtonListener(new ViewAllOrdersListener());
            orderWindow.setVisible(true);
        }
    }

    class NewClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBLL clientBLL = new ClientBLL();
            getDataFromClientWindow();
            Client client = new Client(idClient, nameClient, address, email);
            if(clientBLL.findClientById(idClient) != null) {
                System.out.println("client already exists!");
            }
            else {
                clientBLL.insertClient(client);
            }
        }
    }

    class EditClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getDataFromClientWindow();
            ClientBLL clientBLL = new ClientBLL();
            Client client = new Client(idClient, nameClient, address, email);
           if(clientBLL.findClientById(idClient) != null) {
               clientBLL.updateClient(client);
           }
        }
    }

    class DeleteClientListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBLL clientBLL = new ClientBLL();
            getDataFromClientWindow();
            Client client = new Client(idClient, nameClient, address, email);

            if(clientBLL.findClientById(idClient) != null) {
                clientBLL.deleteClient(client);
            }
        }
    }

    class ViewAllClientsListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBLL clientBLL = new ClientBLL();
            List<Client> clientList = clientBLL.findAllClients();
            String[] columnsName = clientBLL.getColumnsName();

            try {
                Object[][] clientsData = clientBLL.getClientsFields(clientList);
                DefaultTableModel tableModel = new DefaultTableModel();
                for(String column : columnsName)
                    tableModel.addColumn(column);
                for(Object[] o : clientsData)
                    tableModel.addRow(o);
                clientWindow.getMyTable().setModel(tableModel);
            } catch (IntrospectionException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }

    class NewProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProductBLL productBLL = new ProductBLL();
            getDataFromProductWindow();
            Product product = new Product(idProduct, nameProduct, stock, price);
            if(productBLL.findProductById(idProduct) != null) {
                System.out.println("Product already exists!");
            } else {
                productBLL.insertProduct(product);
            }
        }
    }

    class EditProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProductBLL productBLL = new ProductBLL();
            getDataFromProductWindow();
            Product product = new Product(idProduct, nameProduct, stock, price);
            if(productBLL.findProductById(idProduct) != null) {
                productBLL.updateProduct(product);
            }
        }
    }

    class DeleteProductListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProductBLL productBLL = new ProductBLL();
            getDataFromProductWindow();
            Product product = new Product(idProduct, nameProduct, stock, price);
            if(productBLL.findProductById(idProduct) != null) {
                productBLL.deleteProduct(product);
            }
        }
    }

    class ViewAllProductsListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProductBLL productBLL = new ProductBLL();
            List<Product> productList = productBLL.findAllProducts();
            String[] productsColumnsName = productBLL.getColumnsName();
            try {
                Object[][] productsData = productBLL.getProductsFields(productList);
                DefaultTableModel tableModel = new DefaultTableModel();
                for(String column : productsColumnsName)
                    tableModel.addColumn(column);
                for(Object[] o : productsData)
                    tableModel.addRow(o);
                productWindow.getMyTable().setModel(tableModel);
            } catch (IntrospectionException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }

    class CreateOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            getDataFromOrderWindow();
            StringBuilder result = new StringBuilder();
            OrderBLL orderBLL =  new OrderBLL();
            Order order = new Order(idOrder, idClient, idProduct, quantity);

            if (orderBLL.findOrderByID(idOrder) != null) {
                System.out.println("This order already exists!");
            } else {
                ClientBLL clientBLL = new ClientBLL();
                ProductBLL productBLL = new ProductBLL();
                if(clientBLL.findClientById(idClient) != null && productBLL.findProductById(idProduct) != null) {
                    if(productBLL.findProductById(idProduct).getStock() >= quantity) {
                        orderBLL.insertOrder(order);

                        Product auxiliar = productBLL.findProductById(idProduct);
                        auxiliar.setStock(auxiliar.getStock() - quantity);
                        productBLL.updateProduct(auxiliar);

                        result.append("Order made!" +  "\n");
                        result.append("Client: " + clientBLL.findClientById(idClient).toString() + "\n");
                        result.append("Product: " + productBLL.findProductById(idProduct).toString() + "\n");
                        result.append("Stock: " + quantity + "\n");
                        orderWindow.getTextArea().setText(result.toString());
                        writeInFile(result.toString());
                    }
                    else {
                        System.out.println("Cantitatea prea mare!");
                    }
                }
            }
        }
    }

    class DeleteOrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            OrderBLL orderBLL = new OrderBLL();
            getDataFromOrderWindow();
            Order order = new Order(idOrder, idClient, idProduct, quantity);
            if(orderBLL.findOrderByID(idOrder) != null) {
                orderBLL.deleteOrder(order);
            }
        }
    }

    class ViewAllOrdersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderBLL orderBLL = new OrderBLL();
            List<Order> orderList = orderBLL.findAllOrders();
            String [] orderColumnsName = orderBLL.getColumnsName();
            try {
                Object[][] ordersData = orderBLL.getOrdersFields(orderList);
                DefaultTableModel tableModel = new DefaultTableModel();
                for(String column : orderColumnsName)
                    tableModel.addColumn(column);
                for(Object[] o : ordersData)
                    tableModel.addRow(o);
                orderWindow.getMyTable().setModel(tableModel);
            } catch (IntrospectionException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void writeInFile(String rezultat) {
        try {
            FileWriter myFile = new FileWriter("myFile.txt");
            myFile.write(rezultat);
            myFile.close();
            System.out.println("file created!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getDataFromClientWindow() {
        idClient = clientWindow.getClientIdTextField();
        nameClient = clientWindow.getNameClientTextField();
        address = clientWindow.getAddressTexfield();
        email = clientWindow.getEmailTextField();
    }

    public void getDataFromProductWindow() {
        idProduct = productWindow.getIdProductTextField();
        nameProduct = productWindow.getNameProductTextField();
        stock = productWindow.getStockTextField();
        price = productWindow.getPriceTextField();
    }

    public void getDataFromOrderWindow() {
        idOrder = orderWindow.getOrderIdTextField();
        idClient = orderWindow.getClientIdTextField();
        idProduct = orderWindow.getProductIdTextField();
        quantity = orderWindow.getProductQuantityTextField();
    }
}
