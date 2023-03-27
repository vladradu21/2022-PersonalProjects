package model;

public class Order {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;

    public Order() { }

    /** Constructorul Order declara atributele obiectului
     * @param id id ul comenzii
     * @param clientId id ul clientului care realizeaza comanda
     * @param productId id ul produsului care este cumparat
     * @param quantity cantitate de produse cumparata
     */
    public Order(int id, int clientId, int productId, int quantity) {
        super();
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
