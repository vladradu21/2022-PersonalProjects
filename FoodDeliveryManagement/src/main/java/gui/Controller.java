package gui;

import bll.BaseProduct;
import bll.CompositeProduct;
import bll.DeliveryService;
import bll.MenuItem;
import model.Client;
import model.Employee;
import model.Order;
import model.User;
import org.jetbrains.annotations.NotNull;
import serialization.Deserialize;
import serialization.Serialize;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
/**
 * este clasa ce face legatura intre metode si obiectele descrise in model
 * contine toate clasele ce implementeaza ActionListener si metodele de ActionPerformed prin care se realizeaza fiecare operatie
 */
public class Controller {

    private InputWindow view;
    private AdministratorWindow administratorWindow;
    private ClientWindow clientWindow;
    private EmployeeWindow employeeWindow;
    private LogInWindow logInWindow;
    private ReportsWindow reportsWindow;
    private ArrayList<User> clientsList;
    private ArrayList<User> employeesList;
    private DeliveryService deliveryService;
    private List<MenuItem> sortedList;
    private List<MenuItem> basket;


    /**
     * constructorul clasei Controller instantiaza obiectul view
     * @param view
     */
    public Controller (@NotNull InputWindow view) {
        this.view = view;
        view.addAdministratorButtonListener(new AdministratorListener());
        view.addClinetButtonListener(new ClientListener());
        view.addEmployeeButtonListener(new EmployeeListener());

        administratorWindow = new AdministratorWindow();
        clientWindow = new ClientWindow(null);
        employeeWindow = new EmployeeWindow();
        logInWindow = new LogInWindow("Login Window");
        reportsWindow = new ReportsWindow();
        clientsList = Deserialize.deserializeList("clients.ser");
        employeesList = Deserialize.deserializeList("employee.ser");
        deliveryService = new DeliveryService();
        deliveryService.addObserver(employeeWindow);
        deliveryService.setMenuItemList(Deserialize.deserializeMenuItemsList());
        deliveryService.setOrderListMap(Deserialize.deserializeOrdersList());
    }

    /**
     * clasa AdministratorListener deschide fereastra de login pentru userul de tip administrator
     */
    class AdministratorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            closeWindows();
            logInWindow = new LogInWindow("administrator login window");
            logInWindow.getCreateAccount().setVisible(false);
            logInWindow.addLoggInListener(new AdministratorLogInListener());
            logInWindow.setVisible(true);
        }
    }

    /**
     * clasa ClientListener deschide fereastra de login pentru userul de tip client
     */
    class ClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            closeWindows();
            logInWindow = new LogInWindow("client login window");
            logInWindow.addLoggInListener(new ClientLogInListener());
            logInWindow.addCreateAccountListener(new ClientCreateAccountListener());
            logInWindow.setVisible(true);
        }
    }

    /**
     * clasa EmployeeListener deschide fereastra de login pentru userul de tip employee
     */
    class EmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           closeWindows();
           logInWindow = new LogInWindow("employee login window");
           logInWindow.addLoggInListener(new EmployeeLogInListener());
           logInWindow.addCreateAccountListener(new EmployeeCreateAccountListener());
           logInWindow.setVisible(true);
        }
    }

    /**
     * in clasa AdministratorLogInListener se testeaza daca logarea a avut succes iar in acest caz se deschide fereastra principala pentru user
     */
    class AdministratorLogInListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            closeWindows();
            if(logInWindow.getUserNameTextField().equals("admin") && logInWindow.getPasswordTextField().equals("admin")) {
                administratorWindow = new AdministratorWindow();
                administratorWindow.addAddProductsButtonListener(new AdminAddProductsListener());
                administratorWindow.addDeleteProductsButtonListener(new AdminDeleteProductsListener());
                administratorWindow.addSelectRowButtonListener(new AdminSelectRowButtonListener());
                administratorWindow.addModifyProductsButtonListener(new AdminModifyProductsListener());
                administratorWindow.addCreateNewProductsButtonListener(new AdminCreateNewProductsListener());
                administratorWindow.addViewAllProductsButtonListener(new AdminViewAllProductsListener());
                administratorWindow.addImportCSVButtonListener(new AdminImportCSVFileListener());
                administratorWindow.addGenereateRaportsListener(new AdminGenerateReportsListener());
                administratorWindow.setVisible(true);
                System.out.println("Admin Loged in!");
            }
            else {
                System.out.println("Wrong inputs for admin user");
            }
        }
    }

    /**
     * in clasa ClientLogInListener se testeaza daca logarea a avut succes iar in acest caz se deschide fereastra principala pentru user
     */
    class ClientLogInListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            closeWindows();
            sortedList = deliveryService.getMenuItemList();
            basket = new ArrayList<>();
            Client c = new Client(logInWindow.getUserNameTextField(), logInWindow.getPasswordTextField());

            if(clientsList.contains(c)) {
                System.out.println("Client loged in!");
                clientWindow = new ClientWindow(c);
                clientWindow.addViewProductsButtonListener(new ClientViewProductsListener());
                clientWindow.addSearchProductsButtonListener(new ClientSearchProductsListener());
                clientWindow.addSelectItemButtonListener(new ClientSelectItemListener());
                clientWindow.addCreateOrderButtonListener(new ClientCreateOrderListener());
                clientWindow.addComboBox1ButtonListener(new ClientComboBox1Listener());
                clientWindow.setVisible(true);
            } else {
                System.out.println("No account with this data!");
            }
        }
    }

    /**
     * se adauga un cont nou pentru userul de tip Client
     */
    class ClientCreateAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Client c = new Client(logInWindow.getUserNameTextField(), logInWindow.getPasswordTextField());
            if(!clientsList.contains(c)) {
                clientsList.add(c);
                Serialize.serializeList(clientsList,"clients.ser");
                System.out.println("Client Account Created!");
            } else {
                System.out.println("this username is not available!");
            }
        }
    }

    /**
     * in clasa EmployeeLogInListener se testeaza daca logarea a avut succes iar in acest caz se deschide fereastra principala pentru user
     */
    class EmployeeLogInListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            closeWindows();
            Employee emp = new Employee(logInWindow.getUserNameTextField(), logInWindow.getPasswordTextField());

            if(employeesList.contains(emp)) {
                System.out.println("Employee loged in!");
                employeeWindow = new EmployeeWindow();
                deliveryService.getOrderListMap().forEach((key, value) -> employeeWindow.appendTextArea(key.toString3() + " " + value + "\n"));
                employeeWindow.setVisible(true);

                if(employeeWindow.isFlag()) {
                    NotificationWindow notificationWindow = new NotificationWindow("Order Made!");
                    notificationWindow.setVisible(true);
                    employeeWindow.setFlag(false);
                }
            } else {
                System.out.println("No account with this data!");
            }
        }
    }

    /**
     * se adauga un cont nou pentru userul de tip Employee
     */
    class EmployeeCreateAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Employee emp = new Employee(logInWindow.getUserNameTextField(), logInWindow.getPasswordTextField());
            if(!employeesList.contains(emp)) {
                employeesList.add(emp);
                Serialize.serializeList(employeesList, "employee.ser");
                System.out.println("Employee Account Created!");
            } else {
                System.out.println("this username is not available!");
            }
        }
    }

    /**
     * add new product to menuItemList, update the table
     */
    class AdminAddProductsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BaseProduct product = createProductFromTextFields();
            deliveryService.addItemToMenuItemList(product);
            administratorWindow.setDataToTable(deliveryService.getMenuItemList());
        }
    }

    /**
     * delete products from menuItemList, update the table
     */
    class AdminDeleteProductsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Del product");
            int[] rows = administratorWindow.getjTable().getSelectedRows();
            for(int i = 0; i < rows.length; i++) {
                deliveryService.deleteItemFromMenuItemList(rows[i] - i);
                administratorWindow.getDefaultTableModel().removeRow(rows[i] - i);
            }
        }
    }

    /**
     * select specified index of row from table
     */
    class AdminSelectRowButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = administratorWindow.getjTable().getSelectedRow();
            administratorWindow.setTitleTextField(administratorWindow.getjTable().getValueAt(row, 0).toString());
            administratorWindow.setRatingTextField(administratorWindow.getjTable().getValueAt(row, 1).toString());
            administratorWindow.setCaloriesTextField(administratorWindow.getjTable().getValueAt(row, 2).toString());
            administratorWindow.setProteinTextField(administratorWindow.getjTable().getValueAt(row, 3).toString());
            administratorWindow.setFatTextField(administratorWindow.getjTable().getValueAt(row, 4).toString());
            administratorWindow.setSodiumTextField(administratorWindow.getjTable().getValueAt(row, 4).toString());
            administratorWindow.setPriceTextField(administratorWindow.getjTable().getValueAt(row, 5).toString());
        }
    }

    /**
     * modify product from menuItemList, update the table
     */
    class AdminModifyProductsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Modify product");
            int row = administratorWindow.getjTable().getSelectedRow();
            administratorWindow.getDefaultTableModel().removeRow(row);

            administratorWindow.getDefaultTableModel().addRow(new Object[] {administratorWindow.getTitleTextField(), administratorWindow.getRatingTextField(),
            administratorWindow.getCaloriesTextField(), administratorWindow.getProteinTextField(), administratorWindow.getFatTextField(),
            administratorWindow.getSodiumTextField(), administratorWindow.getPriceTextField()});
        }
    }

    /**
     * create composite product from products allready belonging to the table
     */
    class AdminCreateNewProductsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CompositeProduct compositeProduct = new CompositeProduct();
            int[] rows = administratorWindow.getjTable().getSelectedRows();
            for(int row : rows) {
                compositeProduct.getItemArrayList().add(deliveryService.getMenuItemList().get(row));
            }

            deliveryService.getMenuItemList().add(compositeProduct); //adaug sir de produse
            administratorWindow.getDefaultTableModel().addRow(new Object[]{compositeProduct.getTitle(), compositeProduct.getRating(),
                    compositeProduct.getCalories(), compositeProduct.getProtein(), compositeProduct.getFat(),
                    compositeProduct.getSodium(), compositeProduct.getPrice()});
        }
    }

    /**
     * list all product to the jtable
     */
    class AdminViewAllProductsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            administratorWindow.setDataToTable(deliveryService.getMenuItemList());
        }
    }

    /**
     * import products from CSV file & serialize the created array.
     */
    class AdminImportCSVFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.importProducts();
            Serialize.serializeMenuItemsList(deliveryService.getMenuItemList());
        }
    }

    /**
     * create the window for displaying generated reports, add action listeners for the buttons
     */
    class AdminGenerateReportsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            closeWindows();
            reportsWindow = new ReportsWindow();
            reportsWindow.addGenerateReport1Listener(new GenerateReport1());
            reportsWindow.addGenerateReport2Listener(new GenerateReport2());
            reportsWindow.addGenerateReport3Listener(new GenerateReport3());
            reportsWindow.addGenerateReport4Listener(new GenerateReport4());
            reportsWindow.setVisible(true);
        }
    }

    /**
     * view products
     */
    class ClientViewProductsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientWindow.setDataToTable(deliveryService.getMenuItemList());
        }
    }

    /**
     * search products by multiple criteria
     */
    class ClientSearchProductsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (clientWindow.getComboBox1().getSelectedIndex()) {

                case 0 :
                    System.out.println("selecteaza ceva");
                    break;
                case 1 :
                    switch (clientWindow.getComboBox2().getSelectedIndex()) {
                        case 0:
                            System.out.println("rating =");
                            sortedList = sortedList.stream().filter(product -> product.getRating() == Double.parseDouble(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                            break;
                        case 1:
                            System.out.println("rating <");
                            sortedList = sortedList.stream().filter(product -> product.getRating() < Double.parseDouble(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                            break;
                        default:
                            System.out.println("rating >");
                            sortedList = sortedList.stream().filter(product -> product.getRating() > Double.parseDouble(clientWindow.getConditionTextfield())).collect(Collectors.toList());

                    }
                    break;
                case 2 :
                    switch (clientWindow.getComboBox2().getSelectedIndex()) {
                        case 0:
                            System.out.println("calories =");
                            sortedList = sortedList.stream().filter(product -> product.getCalories() == Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                            break;
                        case 1:
                            System.out.println("calories <");
                            sortedList = sortedList.stream().filter(product -> product.getCalories() < Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());

                            break;
                        default:
                            System.out.println("calories >");
                            sortedList = sortedList.stream().filter(product -> product.getCalories() > Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());

                    }
                    break;
                case 3 :
                    switch (clientWindow.getComboBox2().getSelectedIndex()) {
                        case 0:
                            System.out.println("proteins =");
                            sortedList = sortedList.stream().filter(product -> product.getProtein() == Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                            break;
                        case 1:
                            System.out.println("proteins <");
                            sortedList = sortedList.stream().filter(product -> product.getProtein() < Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                            break;
                        default:
                            System.out.println("proteins >");
                            sortedList = sortedList.stream().filter(product -> product.getProtein() > Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                    }
                    break;
                case 4 :
                    switch (clientWindow.getComboBox2().getSelectedIndex()) {
                        case 0:
                            System.out.println("fats =");
                            sortedList = sortedList.stream().filter(product -> product.getFat() == Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                            break;
                        case 1:
                            System.out.println("fats <");
                            sortedList = sortedList.stream().filter(product -> product.getFat() < Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                            break;
                        default:
                            System.out.println("fats >");
                            sortedList = sortedList.stream().filter(product -> product.getFat() > Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                    }
                    break;
                case 5 :
                    switch (clientWindow.getComboBox2().getSelectedIndex()) {
                        case 0:
                            System.out.println("sodium =");
                            sortedList = sortedList.stream().filter(product -> product.getSodium() == Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                            break;
                        case 1:
                            System.out.println("sodium <");
                            sortedList = sortedList.stream().filter(product -> product.getSodium() < Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                            break;
                        default:
                            System.out.println("sodium >");
                            sortedList = sortedList.stream().filter(product -> product.getSodium() > Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                    }
                    break;
                default:
                    switch (clientWindow.getComboBox2().getSelectedIndex()) {
                        case 0:
                            System.out.println("price =");
                            sortedList = sortedList.stream().filter(product -> product.getPrice() == Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                            break;
                        case 1:
                            System.out.println("price <");
                            sortedList = sortedList.stream().filter(product -> product.getPrice() < Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                            break;
                        default:
                            System.out.println("price >");
                            sortedList = sortedList.stream().filter(product -> product.getPrice() > Integer.parseInt(clientWindow.getConditionTextfield())).collect(Collectors.toList());
                    }
            }
            clientWindow.setDataToTable(sortedList);
        }
    }

    /**
     * select rows from defaultTable and add items to basket
     */
    class ClientSelectItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] rows = clientWindow.getTable1().getSelectedRows();
            for(int row : rows) {
                clientWindow.appendTextArea2(sortedList.get(row).toString());
                MenuItem item = sortedList.get(row);
                basket.add(item);
            }
        }
    }

    /**
     * comboBox listener to modify the filterd menu
     */
    class ClientComboBox1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(clientWindow.getComboBox1().getSelectedIndex() == 0) {
                sortedList = deliveryService.getMenuItemList();
                clientWindow.setDataToTable(sortedList);
            }
        }
    }

    /**
     * place order, create bill, update ordersMap
     */
    class ClientCreateOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Order order = new Order(clientWindow.getClient().getUsername(), LocalDate.now(), LocalTime.now());
            deliveryService.addOrder(order, basket);
            clientWindow.appendTextArea2("Order placed! total price: " + basket.stream().mapToInt(MenuItem::getPrice).sum() +"\n");

            String result = "";
            result += "Order placed! total price: " + basket.stream().mapToInt(MenuItem::getPrice).sum() +"\n";
            result += order.toString() + basket.toString();

            basket = new ArrayList<>();
            writeInFile(result, order.toString2());
            Serialize.serializeOrdersList(deliveryService.getOrderListMap());
        }
    }

    /**
     * generate first report
     */
    class GenerateReport1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("rap1");
            reportsWindow.setTextArea("");
            LocalTime startHour = reportsWindow.getStartHourTextfield();
            LocalTime endHour = reportsWindow.getEndHourTextfield();

            Map<Order, List<MenuItem>> filterdMap = deliveryService.getOrderListMap().entrySet()
                    .stream()
                    .filter(p -> (p.getKey().getTime().compareTo(startHour) >= 0) &&
                            (p.getKey().getTime().compareTo(endHour) <= 0))
                    .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

            filterdMap.forEach((key, value) -> reportsWindow.appendTextArea(key.toString4()));
        }
    }

    /**
     * generate second report
     */
    class GenerateReport2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("rap2");
            reportsWindow.setTextArea("");
            int specifiedNumbersOfTimes = reportsWindow.getProductsOrderedMoreTextField();

            List<MenuItem> itemsOrdered = new ArrayList<>();
            deliveryService.getOrderListMap().forEach((key, value) -> itemsOrdered.addAll(value));

            Map<String, Integer> frequencyMap = new HashMap<>();
            for(MenuItem item : itemsOrdered) {
                frequencyMap.merge(item.getTitle(), 1, Integer::sum);
            }

            Map<String, Integer> filterdFrequencyMap = frequencyMap.entrySet()
                    .stream()
                    .filter(p -> p.getValue() > specifiedNumbersOfTimes )
                    .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

            filterdFrequencyMap.forEach((key, value) -> reportsWindow.appendTextArea(key + " " + value + "\n"));
        }
    }

    class GenerateReport3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("rap3");
            reportsWindow.setTextArea("");
            int specifiedNrOfTimes = reportsWindow.getClientsWhoOrderTextfield();
            int specifiedPrice = reportsWindow.getValueOfOrderTextField();

            Map<Order, Integer> filterdByPrice = deliveryService.getOrderListMap().entrySet()
                    .stream()
                    .filter(p -> composePrice(p.getValue()) >= specifiedPrice)
                    .collect(Collectors.toMap(map -> map.getKey(), map -> composePrice(map.getValue())));

            List<String> clientsList = new ArrayList<>();
            filterdByPrice.forEach((key, value) -> clientsList.add(key.getUsername()));

            Map<String, Integer> frequencyMap = new HashMap<>();
            for(String name : clientsList) {
                frequencyMap.merge(name, 1, Integer::sum);
            }

            Map<String, Integer> filteredFrequencyMap = frequencyMap.entrySet()
                    .stream()
                    .filter(p -> p.getValue() > specifiedNrOfTimes)
                    .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

            filteredFrequencyMap.forEach((key, value) -> reportsWindow.appendTextArea(key + " " + value + "\n"));

            reportsWindow.appendTextArea("\n************************\n");
            filterdByPrice.forEach((key, value) -> reportsWindow.appendTextArea(key.toString5() + " " + value + "\n"));

        }
    }

    public int composePrice(List<MenuItem> list) {
        int price = 0;
        for(MenuItem item : list) {
            price += item.getPrice();
        }
        return price;
    }

    class GenerateReport4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("rap4");
            reportsWindow.setTextArea("");
            LocalDate specifiedData = reportsWindow.getOrderedWithinSpecifiedDayTextField();

            Map<Order, List<MenuItem>> filterdMapByDay = deliveryService.getOrderListMap().entrySet()
                    .stream()
                    .filter(p -> p.getKey().getDate().compareTo(specifiedData) == 0)
                    .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

            filterdMapByDay.forEach((key, value) -> System.out.println(key + " " + value + "\n"));

            List<MenuItem> productsList = new ArrayList<>();
            filterdMapByDay.forEach((key, value) -> productsList.addAll(value));

            Map<String, Integer> filterdMapByDayAndOrders = new HashMap<>();
            for(MenuItem item : productsList) {
                filterdMapByDayAndOrders.merge(item.getTitle(), 1, Integer::sum);
            }

            filterdMapByDayAndOrders.forEach((key, value) -> reportsWindow.appendTextArea(key + " " + value + "\n"));
        }
    }

    /**
     * @param rezultat IllegalArgumentException if
     * @param fileName not NULL
     */
    public void writeInFile(String rezultat, String fileName) {
        try {
            FileWriter myFile = new FileWriter(fileName);
            myFile.write(rezultat);
            myFile.close();
            System.out.println("file created!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWindows() {
        administratorWindow.setVisible(false);
        clientWindow.setVisible(false);
        employeeWindow.setVisible(false);
        logInWindow.setVisible(false);
        reportsWindow.setVisible(false);
    }

    public BaseProduct createProductFromTextFields() {
        BaseProduct product = new BaseProduct(administratorWindow.getTitleTextField(), administratorWindow.getRatingTextField(),
                administratorWindow.getCaloriesTextField(), administratorWindow.getProteinTextField(), administratorWindow.getFatTextField(),
                administratorWindow.getSodiumTextField(), administratorWindow.getPriceTextField());

        return product;
    }
}
