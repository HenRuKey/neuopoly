package controllers;

import interfaces.Viewable;
import javafx.stage.Stage;

public class GameManager {

	public static Viewable viewManager;
	
	
	public static void initialize(Stage stage) {
		viewManager = new ViewManager(stage);
		viewManager.showMainMenu();
	}
	
}
