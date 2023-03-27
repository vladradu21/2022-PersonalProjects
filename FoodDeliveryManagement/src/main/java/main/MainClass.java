package main;

import gui.Controller;
import gui.InputWindow;

public class MainClass {

	/**
	 * de aici incepe executia programului
	 * se intantiaza un view si un obiecte de tip controller cu acel view
	 * @param args
	 */
	public static void main(String[] args) {

		InputWindow view = new InputWindow();
		Controller controller = new Controller(view);
		view.setVisible(true);
	}
}