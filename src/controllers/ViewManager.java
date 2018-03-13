package controllers;

import java.io.IOException;

import interfaces.Stageable;
import interfaces.Viewable;
import javafx.stage.Stage;
import models.Card;
import models.Player;
import views.GameBoardView;
import views.Instructions;
import views.MainMenu;
import views.SetUpView;

public class ViewManager implements Viewable {

	private Stage stage;
	private Stageable mainMenu;
	private Stageable instructions;
	private Stageable gameBoard;	
	private Stageable setUp;

	public ViewManager(Stage stage) {
		this.stage = stage;
		initialize();
	}
	
	private void initialize() {
		try {
			mainMenu = new MainMenu();
			instructions = new Instructions();
			gameBoard = new GameBoardView();
			setUp = new SetUpView();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void showMainMenu() {
		stage.setScene(mainMenu.getScene());
		if (!stage.isShowing()) {
			stage.show();
		}
	}

	@Override
	public void updatePlayerPosition() {
		GameBoardVC vc = (GameBoardVC) gameBoard.getController();
		vc.updatePlayerPositions();
	}

	public void alertPlayer(String title, String message) {
		GameBoardVC vc = (GameBoardVC) gameBoard.getController();
		vc.alertPlayer(title, message);
	}

	@Override
	public void showSetUpMenu() {
		stage.setScene(setUp.getScene());
	}

	@Override
	public void showInstructionsWindow() {
		Stage instructionsWindow = new Stage();
		instructionsWindow.setResizable(false);
		instructionsWindow.setScene(instructions.getScene());
		instructionsWindow.show();
	}

	@Override
	public void showGameBoard() {
		GameBoardVC vc = (GameBoardVC) gameBoard.getController();
		vc.loadPlayers();
		vc.populateHashMap();
		vc.updatePlayerPositions();
		stage.setScene(gameBoard.getScene());
		GameManager.takeTurn();
	}

	@Override
	public void displayCard(Card card) {
		GameBoardVC vc = (GameBoardVC) gameBoard.getController();
		vc.displayCard(card);
	}

	@Override
	public void beginTurn(Player player) {
		TurnLogic.setCurrentPlayer(player);
		GameBoardVC vc = (GameBoardVC) gameBoard.getController();
		vc.beginTurn(player);
	}

	@Override
	public void updatePlayerAccount() {
		GameBoardVC vc = (GameBoardVC) gameBoard.getController();
		vc.updatePlayerInfo();
	}

}
