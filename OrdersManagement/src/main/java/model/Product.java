package model;

public class Product {
    private int id;
    private String name;
    private int stock;
    private double price;

    public Product() { }

    /** Constructorul Product declara atributele obiectului
     * @param id id ul produsului
     * @param name numele produsului
     * @param stock stockul pe care il are produsul
     * @param price pretul produsului, numar real
     */
    public Product(int id, String name, int stock, double price) {
        super();
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "( " + id + ", " + name + ", " + stock + ", " + price + " )";
    }
}
