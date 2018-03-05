package controllers;

import java.io.IOException;

import interfaces.Stageable;
import interfaces.Viewable;
import javafx.stage.Stage;
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
	
	/**
	 * 
	 * @param stage
	 */
	public ViewManager(Stage stage) {
		this.stage = stage;
		initialize();
	}
	
	/**
	 * 
	 */
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
	public void updatePlayerPosition(Player player) {
		
	}

	@Override
	public void updatePlayerAccount(Player player) {

		
	}

	@Override
	public void rollDie(int rolledFace) {

		
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
		stage.setScene(gameBoard.getScene());
	}
		

}
