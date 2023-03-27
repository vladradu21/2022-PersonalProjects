package businessLogic;

import gui.SimulationFrameOutput;
import model.Server;
import model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimulationManager implements Runnable {
    String result;
    private double averageServiceTime;

    //data read from gui
    private int timeLimit;
    private int minProcessingTime;
    private int maxProcessingTime;
    private int numberOfServers;
    private int numberOfClients;
    private int minArrivalTime;
    private int maxArrivalTime;
    private SelectionPolicy selectionPolicy;

    //entity responsible with queue management and client distribution
    private Scheduler scheduler;
    //frame for displaying simulation
    private SimulationFrameOutput frame;
    //pool of tasks (client shoping in the store)
    private LinkedList<Task> generatedTasks = new LinkedList<>();

    public SimulationManager(int nrOfClients, int nrOfQueues, int simulationInterval, int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime) {
        averageServiceTime = 0;
        this.numberOfClients = nrOfClients;
        this.numberOfServers = nrOfQueues;
        this.timeLimit = simulationInterval;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;

        scheduler = new Scheduler(numberOfServers, numberOfClients);
        this.selectionPolicy = SelectionPolicy.SHORTEST_TIME;
        frame = new SimulationFrameOutput();
        generateNRandomTasks();
    }

    private void generateNRandomTasks() {
        for (int i = 1; i <= numberOfClients; i++) {
            Task t = new Task(i, getRandomNumber(minArrivalTime, maxArrivalTime), getRandomNumber(minProcessingTime, maxProcessingTime));
            generatedTasks.add(t);
            averageServiceTime += t.getServiceTime();
        }
        averageServiceTime /= numberOfClients;
        Collections.sort(generatedTasks);
    }

    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public boolean queuesStatus() {
        boolean ok = false;
        for(Server s : scheduler.getServers()) {
            if(s.getSize() != 0)
                ok = true;
        }
        if(!generatedTasks.isEmpty())
            ok = true;
        return  ok;
    }

    @Override
    public void run() {
        int peakHour = 0;
        int totalSumWaitingTime = 0;
        int maxNrOfTasks = 0;
        int currentTime = 0;
        result = "";
        scheduler.startThreads(numberOfServers);

        while(currentTime < timeLimit && queuesStatus()) {
            int nrOfTask = 0;
            int sumWaitingTime = 0;
            writeFunction("time: " + currentTime + "\nWaiting clients: " + generatedTasks.toString() + "\n" );

            LinkedList <Task> aux = new LinkedList<>();
            for(Task t : generatedTasks) {
                if(t.getArrivalTime() == currentTime)
                    scheduler.dispatchTasks(t);
                else aux.add(t);
            }
            generatedTasks = aux;

            for(int i = 0; i < numberOfServers; i++) {
                if(scheduler.getServers().get(i).getSize() == 0) {
                    writeFunction("server " + i + ": closed\n");
                }
                else {
                    for(Task t : scheduler.getServers().get(i).getTasksList()) {
                        sumWaitingTime += t.getServiceTime();
                    }
                    sumWaitingTime /= scheduler.getServers().get(i).getSize();
                    nrOfTask += scheduler.getServers().get(i).getSize();
                    writeFunction("server " + i +": " + scheduler.getServers().get(i).toString() + "\n");
                }
            }
            totalSumWaitingTime += sumWaitingTime;

            if(maxNrOfTasks < nrOfTask) {
                maxNrOfTasks = nrOfTask;
                peakHour = currentTime;
            }
            frame.setVisible(true);
            currentTime++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.textArea.setText("");
        }
        totalSumWaitingTime /= (currentTime - 1);
        writeFunction("Average service time: " + averageServiceTime+ "\n");
        writeFunction("Peak hour: " + peakHour + "\n");
        writeFunction("Average waiting time: " + totalSumWaitingTime);
        writeInFile(result);
    }

    public void writeFunction(String sursa) {
        frame.textArea.append(sursa);
        result += sursa;
    }

    public void writeInFile(String rezultat) {
        try {
            FileWriter myFile = new FileWriter("myFile.txt");
            myFile.write(rezultat);
            myFile.close();
            System.out.println("file created!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
