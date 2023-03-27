package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Order implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String username;
    private LocalDate date;
    private LocalTime time;

    public Order(String username, LocalDate date, LocalTime time) {
        this.username = username;
        this.date = date;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "order" + username + date + time;
    }

    public String toString2() {
        return "order" + username;
    }

    public String toString3() {
        return "\nordered by: " + username + " date: " + date + " time: " + time + "\n";
    }

    public String toString4() {
        return "ordered by: " + username + " date: " + date + " time: " + time + "\n";
    }

    public String toString5() {
        return "ordered by: " + username + " date: " + date + " time: " + time;
    }
}
