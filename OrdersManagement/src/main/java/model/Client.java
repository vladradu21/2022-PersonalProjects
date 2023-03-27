package model;

public class Client {
    private int id;
    private String name;
    private String address;
    private String email;

    public Client(){ }

    /** constructorul Client declara atributele obiectului
     * @param id reprezinta idul clientului
     * @param name numele clientului
     * @param address adresa clientului
     * @param email adresa de email a clientului
     */
    public Client(int id, String name, String address, String email) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "( " + id + ", " + name + ", " + address + ", " + email + " )";
    }
}



