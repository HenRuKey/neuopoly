package controllers;

import interfaces.Controllable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuVC implements Controllable {
	
	@FXML private Button btnStart;
	@FXML private Button btnInstructions;
	
	public void displayInstructions(ActionEvent event) {
		GameManager.viewManager.showInstructionsWindow();
	}
	
	public void startGame(ActionEvent event) {
		GameManager.viewManager.showSetUpMenu();
	}
  
}
