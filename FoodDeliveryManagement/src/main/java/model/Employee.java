package model;

import java.io.Serializable;
import java.util.Objects;

public class Employee extends User implements Serializable {

    public Employee(String username, String password) {
        super(username, password);
    }

    @Override
    public String toString() {
        return "Employee: " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(username, employee.username) && Objects.equals(password, employee.password);
    }
}
