package bll;

import bll.validators.ProductStockValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * clasa ProductBLL are metode ce implementeaza operatile de baza pe baze de date construite in clasa AbstractDAO
 */
public class ProductBLL {

    private List<Validator<Product>> validatorList;
    private ProductDAO productDAO;

    /**
     * constror clasa ProductBLL marcheaza validatorii si instantiaza obiectul de tip DAO
     */
    public ProductBLL() {
        validatorList = new ArrayList<Validator<Product>>();
        validatorList.add(new ProductStockValidator());
        productDAO = new ProductDAO();
    }

    public Product findProductById(int id) {
        Product product = productDAO.findById(id);
        return  product;
    }

    public List<Product> findAllProducts() {
        List<Product> productList = productDAO.findAll();
        if(productList == null) {
            throw new NoSuchElementException("Products not found!");
        }
        return productList;
    }

    public Object[][] getProductsFields (List<Product> productList) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Object[][] products = productDAO.getFields(productList);
        return products;
    }

    public String[] getColumnsName() {
        String [] columns = productDAO.getColumns();
        return columns;
    }

    public Product insertProduct(Product p) {
        return productDAO.insert(p);
    }

    public Product updateProduct(Product p) {
        return productDAO.update(p);
    }

    public Product deleteProduct(Product p) {
        return productDAO.delete(p);
    }
}
