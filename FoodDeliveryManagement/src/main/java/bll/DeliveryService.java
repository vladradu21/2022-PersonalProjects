package bll;

import model.Order;
import serialization.Serialize;
import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * clasa delivery service contite toate metodele cu lucru pe colectii
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing {

    private List<MenuItem> menuItemList;
    private Map<Order, List<MenuItem>> orderListMap;

    public DeliveryService() {
        this.menuItemList = new ArrayList<>();
        this.orderListMap = new Hashtable<>();
    }

    public void Invariant() {
        assert menuItemList.size() != 0;
        assert orderListMap.size() != 0;
    }

    /**
     * import CSV file of products to arrayList
     */
    @Override
    public void importProducts() {
        Invariant();
        menuItemList = new ArrayList<>();
        try {
            File input = new File("src/main/java/data/products.csv");
            InputStream inputStream = new FileInputStream(input);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            menuItemList = bufferedReader.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            bufferedReader.close();
            System.out.println("csv file imported!");

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * create product from row
     */
    private Function<String, MenuItem> mapToItem = (line) -> {

        String[] arr = line.split(",");// a CSV has comma separated lines

        MenuItem item =  new BaseProduct(arr[0].trim(), Double.parseDouble(arr[1]), Integer.parseInt(arr[2]),
                Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]), Integer.parseInt(arr[6]));

        return item;
    };


    /**
     * @param item = produsul pe care vrem sa il adaugam
     */
    public void addItemToMenuItemList (MenuItem item) {
        assert item != null;
        menuItemList.add(item);
        Serialize.serializeMenuItemsList(menuItemList);
        assert menuItemList != null;
    }

    /**
     * @param index = indexul dupa care facem stergerea
     */
    public void deleteItemFromMenuItemList (int index)  {
        assert index > 0;
        menuItemList.remove(index);
        Serialize.serializeMenuItemsList(menuItemList);
    }

    /**
     * se creeaza un map intre comanda si produse
     * @param order = comanda pe care vrem sa o adaugam
     * @param menuItems = produsele adaugate
     */
    public void addOrder(Order order, List<MenuItem> menuItems) {
        assert order != null;
        assert menuItems != null;
        orderListMap.put(order, menuItems);
        setChanged();
        notifyObservers();

        Serialize.serializeOrdersList(orderListMap);
        assert orderListMap != null;
    }

    /**
     * @return lista de produse
     */
    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public void setOrderListMap(Map<Order, List<MenuItem>> orderListMap) {
        this.orderListMap = orderListMap;
    }

    public Map<Order, List<MenuItem>> getOrderListMap() {
        return orderListMap;
    }
}
