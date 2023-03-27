package gui;

import businessLogic.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private SimulationFrameInput view;

    public Controller (SimulationFrameInput view) {
        this.view = view;
        view.addValidateButtonListener(new ValidateListener());
    }

    class ValidateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int noClients = Integer.parseInt(view.getNoClientsField());
            int noQueues = Integer.parseInt(view.getNoQueuesField());
            int simulationInterval = Integer.parseInt(view.getSimulationIntervalField());
            int minArrivalTime = Integer.parseInt(view.getMinArivalTimeField());
            int maxArrivalTime = Integer.parseInt(view.getMaxArrivalTimeField());
            int minServiceTime = Integer.parseInt(view.getMinServiceTimeField());
            int maxServiceTime = Integer.parseInt(view.getMaxServiceTimeField());

            SimulationManager manager = new SimulationManager(noClients, noQueues, simulationInterval, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);
            Thread t = new Thread(manager);
            t.start();

        }
    }
}
