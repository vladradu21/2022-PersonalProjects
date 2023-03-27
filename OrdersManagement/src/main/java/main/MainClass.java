package main;

import presentation.Controller;
import presentation.InputWindow;
import java.sql.SQLException;


public class MainClass {

	/** metoda main porneste executia programului
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {

	InputWindow view = new InputWindow();
        Controller controller = new Controller(view);
        view.setVisible(true);
	}
}