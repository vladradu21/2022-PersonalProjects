package bll.validators;

import model.Product;

public class ProductStockValidator implements Validator<Product>{

    @Override
    public void validate(Product product) {
        if(product.getStock() < 0) {
            throw new IllegalArgumentException("The product quantity limit is not respected!");
        }

    }
}
