package model;

import java.io.Serializable;

/**
 * clasa user este superclasa pentru client, employee si admin
 * obiectele de tip User sunt serializabile
 */
public class User implements Serializable {

    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{username= " + username + ", password= " + password + '}';
    }
}
