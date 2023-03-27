package businessLogic;

import model.Server;
import model.Task;

import java.util.ArrayList;

public class Scheduler {

    public ArrayList<Server> serversList = new ArrayList<>();
    private ArrayList<Thread> threadsList = new ArrayList<>();
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        strategy = new TimeStrategy();

        for(int i = 1; i <= maxNoServers; i++) {
            Server s = new Server(0);
            Thread testThread = new Thread(s);

            this.serversList.add(s);
            this.threadsList.add(testThread);
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if(policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ShortestQueueStrategy();
        }

        if(policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new TimeStrategy();
        }
    }

    public void dispatchTasks(Task t) {
        strategy.addTask(serversList, t);
    }

    public ArrayList<Server> getServers() {
        return serversList;
    }

    public ArrayList<Thread> getThreadsList() {
        return threadsList;
    }

    public void startThreads(int nrOfThreads) {
        for(int i = 0; i < nrOfThreads; i++) {
            threadsList.get(i).start();
        }
    }
}
