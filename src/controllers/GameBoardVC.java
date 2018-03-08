package controllers;
import interfaces.Controllable;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import models.Card;
import models.Player;

public class GameBoardVC implements Controllable {
	
	private static Pane[] tiles = new Pane[40];
	
	@FXML private GridPane gameBoard;
	@FXML private Accordion accordionPlayers;
	@FXML private Pane paneCard;
	@FXML private Pane paneMenu;
	@FXML private Button btnPurchase;
	@FXML private Button btnBuyHouse;
	@FXML private Button btnSellHouse;
	@FXML private Button btnMortgage;
	@FXML private Button btnEndTurn;
	
	@FXML
	protected void initialize() {
		// Add tiles to the board
		int counter = 0;
		for (int i = 10; i >= 0; i--) {
			createAddTile(i, 10, counter);
			counter++;
		}
		for (int j = 9; j >= 0; j--) {
			createAddTile(0, j, counter);
			counter++;
		}
		for (int i = 1; i < 11; i++) {
			createAddTile(i, 0, counter);
			counter++;
		}
		for (int j = 1; j < 10; j++) {
			createAddTile(10, j, counter);
			counter++;
		}
		// Add players to accordion
		for (Player player : TurnLogic.game.getPlayerList()) {
			TitledPane playerTab = new TitledPane();
			playerTab.setText(player.getName());
			Pane properties = new Pane();
			playerTab.setContent(properties);
			accordionPlayers.getPanes().add(playerTab);
		}
		// Hide the drawn card template
		paneCard.setVisible(false);
	}
	
	/**
	 * Add a tile to the game board
	 * @param x horizontal position
	 * @param y vertical position
	 * @param count ordinal representation of the tile
	 */
	private void createAddTile(int x, int y, int count) {
		Pane tile = new Pane();
		tile.getStyleClass().add("tile");
		tiles[count] = tile;
		gameBoard.add(tile, x, y);
	}
	
	public void dislpayCard(Card card) {
		if (paneCard.getChildren().size() > 0) {
			paneCard.getChildren().clear();
		}
		Label cardData = new Label();
		cardData.setText(card.getDescription());
		cardData.setTextAlignment(TextAlignment.CENTER);
		paneCard.getChildren().add(cardData);
		cardData.setVisible(true);
	}

}
