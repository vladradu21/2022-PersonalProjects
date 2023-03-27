package serialization;

import bll.MenuItem;
import model.Order;
import model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * clasa care serializeaza fiecare colectie de obiecte
 */
public class Serialize {

    /**
     * serializea listele de conturi pentru utilizatorii de tip client si employee
     * @param userArrayList =lista de clienti sau angajati
     * @param fileName =numele fiserului pe care dorim sa il construiasca
     */
    public static void serializeList(ArrayList<User> userArrayList, String fileName) {
        ObjectOutputStream objOut = null;
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/java/data/" + fileName);
            objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(userArrayList);
            System.out.println("Data saved in " + fileName + " file!");
            objOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * serializeaza colectia de produse
     * @param menuItems =lista de produse based si composite products
     */
    public static void serializeMenuItemsList(List<MenuItem> menuItems) {
        ObjectOutputStream objOut = null;
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/java/data/products.ser");
            objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(menuItems);
            System.out.println("Data saved in products.ser file");
            objOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * serializeaza colectia de comenzi de tipul client, lista de produse
     * @param orderListMap =colectia de obiecte de tip map intre client si lista de produse
     */
    public static void serializeOrdersList(Map<Order, List<MenuItem>> orderListMap) {
        try{
            FileOutputStream fileOut = new FileOutputStream("src/main/java/data/orders.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(orderListMap);
            out.close();
            fileOut.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
