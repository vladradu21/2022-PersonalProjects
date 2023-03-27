package serialization;
import bll.MenuItem;
import model.Order;
import model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * clasa de deserializare a fiecarei colectii
 */
public class Deserialize {

    /**
     * deserializeaza lista de produse din fiserul de tip .ser
     * @return = lista de produse
     */
    public static ArrayList<MenuItem> deserializeMenuItemsList() {
        ObjectInputStream objIn = null;
        ArrayList<MenuItem> menuItemArrayList;
        try {
            FileInputStream fileIn = new FileInputStream("src/main/java/data/products.ser");
            objIn = new ObjectInputStream(fileIn);
            menuItemArrayList =  (ArrayList<MenuItem>) objIn.readObject();
            objIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Client class not found!");
            e.printStackTrace();
            return null;
        }

        return menuItemArrayList;
    }

    /**
     * deserialzeaza listele de conturi pentru utilizatorii de tip client si employee
     * @param fileName
     * @return
     */
    public static ArrayList<User> deserializeList(String fileName) {
        ObjectInputStream objIn;
        ArrayList<User> users;
        try {
            FileInputStream fileIn = new FileInputStream("src/main/java/data/" + fileName);
            objIn = new ObjectInputStream(fileIn);
            users = (ArrayList<User>) objIn.readObject();
            objIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("class not found!");
            e.printStackTrace();
            return null;
        }
        return users;
    }

    /**
     * deserializea colectia de comenzi compusa din client si lista de produse
     * @return = o colectie de tip map dintre client si lista de produse
     */
    public static Map<Order, List<MenuItem>> deserializeOrdersList(){
        Map<Order, List<MenuItem>> orders;
        File file;
        try{
            FileInputStream fileIn = new FileInputStream("src/main/java/data/orders.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            orders = (Map<Order, List<MenuItem>>) in.readObject();
            in.close();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            orders = new Hashtable<>();
        }
        return orders;
    }

}
