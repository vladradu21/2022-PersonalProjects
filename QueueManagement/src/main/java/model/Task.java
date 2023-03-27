package model;

public class Task implements Comparable<Task>{
    private  int id;
    private int arrivalTime;
    private int serviceTime;

    public Task(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public String toString() {
        return "(" + getId() + ", " + getArrivalTime() + ", " + getServiceTime() + ")";
    }

    @Override
    public int compareTo(Task t) {
        return this.arrivalTime - t.arrivalTime;
    }
}
