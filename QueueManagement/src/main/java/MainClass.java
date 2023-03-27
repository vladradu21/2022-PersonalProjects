import businessLogic.SimulationManager;
import gui.Controller;
import gui.SimulationFrameInput;

public class MainClass {

    public static void main(String[] args) {
        SimulationFrameInput view = new SimulationFrameInput();
        Controller controller = new Controller(view);
        view.setVisible(true);
    }
}
