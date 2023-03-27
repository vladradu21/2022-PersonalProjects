package model;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server(int waitingPeriod) {
        //initialize queue and waitingPeriod
        tasks = new LinkedBlockingQueue<Task>();
        this.waitingPeriod = new AtomicInteger(waitingPeriod);
    }

    public void addTask(Task newTask) throws InterruptedException {
        //add task to queue, increment waitingPeriod
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    @Override
    public void run() {

        while (true) {
            if(!tasks.isEmpty()) {
                Task t = tasks.element();
                t.setServiceTime(t.getServiceTime()-1);

                if(t.getServiceTime() == 0) {
                    tasks.remove(t);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //ceva
            }
            waitingPeriod.decrementAndGet();
        }
    }

    public int getWaitingPeriod() {
        return waitingPeriod.intValue();
    }

    public void setWaitingPeriod(int waitingPeriod) {
        this.waitingPeriod = new AtomicInteger(waitingPeriod);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasksList() {
        ArrayList<Task> rezultat = new ArrayList<>();
        for(Task t : tasks)
            rezultat.add(t);

        return rezultat;
    }

    @Override
    public String toString() {
        String rezultat = "";
        for(Task t : tasks)
            rezultat += t.toString() + " ";
        return rezultat;
    }

}
