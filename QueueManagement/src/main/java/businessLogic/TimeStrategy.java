package businessLogic;

import model.Server;
import model.Task;
import java.util.ArrayList;

public class TimeStrategy implements Strategy {
    @Override
    public void addTask(ArrayList<Server> servers, Task t) {
        int minim = servers.get(0).getWaitingPeriod();
        Server serv = servers.get(0);

        for(Server s : servers) {
            if(minim > s.getWaitingPeriod()) {
                serv =  s;
                minim = s.getWaitingPeriod();
            }
        }
        try {
            serv.addTask(t);
            serv.setWaitingPeriod(serv.getWaitingPeriod() + t.getServiceTime());
        } catch (InterruptedException e) {
            //ceva
        }
    }
}
