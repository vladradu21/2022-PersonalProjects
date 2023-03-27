package model;

import java.util.Objects;

public class Client extends User implements java.io.Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    public Client(String username, String password) {
        super(username, password);
    }

    @Override
    public String toString() {
        return "Client: " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(username, client.username) && Objects.equals(password, client.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}

