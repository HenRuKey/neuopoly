package controllers;

import enums.Token;
import interfaces.Controllable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import models.Game;

public class SetUpVC implements Controllable {
	
	@FXML private Button btnNext;
	@FXML private Spinner<Integer> spinNumber;
	
	@FXML
	protected void initialize() {
		IntegerSpinnerValueFactory factory = new IntegerSpinnerValueFactory(2, Token.values().length);
		spinNumber.setValueFactory(factory);
	}
	
	@FXML
	public void startGame() {
		TurnLogic.game.numberOfPlayers(spinNumber.getValue());
		GameManager.viewManager.showGameBoard();
	}
  
}
