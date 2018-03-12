package controllers;

import interfaces.Viewable;
import javafx.stage.Stage;
import models.Player;

public class GameManager {

	public static Viewable viewManager;
	private static int currentPlayer = -1;
	
	public static void initialize(Stage stage) {
		viewManager = new ViewManager(stage);
		viewManager.showMainMenu();
	}
	
	public static void takeTurn() {
		currentPlayer = currentPlayer == TurnLogic.game.getPlayerList().size() - 1 ? 0 : currentPlayer + 1;
		Player player = TurnLogic.game.getPlayerList().get(currentPlayer);
		viewManager.beginTurn(player);
	}
	
	public static int getCurrentPlayerIndex() {
		return currentPlayer;
	}
	
}
