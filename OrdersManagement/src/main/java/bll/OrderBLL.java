package bll;

import dao.OrderDAO;
import model.Order;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * clasa OrderBLL are metode ce implementeaza operatile de baza pe baze de date construite in clasa AbstractDAO
 */
public class OrderBLL {
    private OrderDAO orderDAO;

    /**
     * constror clasa OrderBLL instantiaza obiectul de tip DAO
     */
    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    public Order findOrderByID(int orderId) {
        Order result = orderDAO.findById(orderId);
        return result;
    }

    public Order insertOrder(Order order){
        return orderDAO.insert(order);
    }

    public Order deleteOrder(Order order) {
        return orderDAO.delete(order);
    }

    public List<Order> findAllOrders(){
        List<Order> orderList = orderDAO.findAll();
        if(orderList == null) {
            throw new NoSuchElementException("Orders not found!");
        }
        return orderList;
    }

    public String [] getColumnsName() {
        return orderDAO.getColumns();
    }

    public Object[][] getOrdersFields(List<Order> orderList) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        return orderDAO.getFields(orderList);
    }
}
