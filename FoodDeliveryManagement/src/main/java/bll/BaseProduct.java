package bll;

import java.io.Serializable;

/**
 * clasa baseProduct contine atributele unui produs
 */
public class BaseProduct extends MenuItem implements Serializable {

    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public int getProtein() {
        return protein;
    }

    @Override
    public int getFat() {
        return fat;
    }

    @Override
    public int getSodium() {
        return sodium;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "BP:" + "title='" + title + ", rating=" + rating + ", calories=" + calories + ", protein=" + protein
                + ", fat=" + fat + ", sodium=" + sodium + ", price=" + price;
    }
}
