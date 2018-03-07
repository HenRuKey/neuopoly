package controllers;

import interfaces.Controllable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class SetUpVC implements Controllable {
	
	@FXML private ListView<String> listPlayers;
	@FXML private Button btnNext;
	
	@FXML
	protected void initialize() {
		
	}
	
	@FXML
	public void startGame() {
		GameManager.viewManager.showGameBoard();
	}
  
}
