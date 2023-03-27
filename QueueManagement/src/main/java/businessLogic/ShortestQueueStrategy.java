package businessLogic;

import model.Server;
import model.Task;

import java.util.ArrayList;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public void addTask(ArrayList<Server> servers, Task t) {
        int minim = servers.get(0).getSize();
        Server serverul = servers.get(0);

        for(Server s : servers) {
            if(minim > s.getSize()){
                serverul = s;
                minim = s.getWaitingPeriod();
            }
        }
        try {
            serverul.addTask(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
