package bll;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * clasa compositeProduct are ca atribute o lista de produse baseProduct
 */
public class CompositeProduct extends MenuItem implements Serializable {

    public ArrayList<MenuItem> itemArrayList = new ArrayList<>();

    @Override
    public String getTitle() {
        String result = "";
        for(MenuItem item : itemArrayList) {
            result += item.getTitle() + " | ";
        }
        return result;
    }

    @Override
    public double getRating() {
        double rating = 0;
        for(MenuItem item : itemArrayList) {
            rating += item.getRating();
        }
        return rating/itemArrayList.size();
    }

    @Override
    public int getCalories() {
        int calories = 0;
        for(MenuItem item : itemArrayList){
            calories += item.getCalories();
        }
        return calories;
    }

    @Override
    public int getProtein() {
        int protein = 0;
        for(MenuItem item : itemArrayList){
            protein += item.getProtein();
        }
        return protein;
    }

    @Override
    public int getFat() {
        int fat = 0;
        for(MenuItem item : itemArrayList){
            fat += item.getFat();
        }
        return fat;
    }

    @Override
    public int getSodium() {
        int sodium = 0;
        for(MenuItem item : itemArrayList){
            sodium += item.getSodium();
        }
        return sodium;
    }

    @Override
    public int getPrice() {
        int price = 0;
        for(MenuItem item : itemArrayList){
            price += item.getPrice();
        }
        return price;
    }

    public ArrayList<MenuItem> getItemArrayList() {
        return itemArrayList;
    }
}
