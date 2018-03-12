package controllers;

import java.util.HashMap;
import interfaces.Controllable;
import interfaces.Ownable;
import interfaces.Tileable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import models.Card;
import models.Player;
import models.Property;

public class GameBoardVC implements Controllable {
	
	private static Pane[] tiles = new Pane[40];
	private HashMap<Player, Image> playerTokens = new HashMap<>();
	private TitledPane[] playerTabs;
	
	@FXML private GridPane gameBoard;
	@FXML private Accordion accordionPlayers;
	@FXML private Pane paneCard;
	@FXML private Pane paneMenu;
	@FXML private Pane paneMenuProperties;
	@FXML private Pane paneDieOne;
	@FXML private Pane paneDieTwo;
	@FXML private Button btnPurchase;
	@FXML private Button btnBuyHouse;
	@FXML private Button btnSellHouse;
	@FXML private Button btnMortgage;
	@FXML private Button btnEndTurn;
	@FXML private Button btnInteractProperty;
	@FXML private Button btnRoll;
	@FXML private Label lblCurrentPlayer;
	@FXML private ChoiceBox<Property> choiceBoxProperties;
	
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
		// Hide the drawn card template and menu
		paneMenu.setVisible(false);
		paneCard.setVisible(false);
		paneMenuProperties.setVisible(false);
	}
	
	public void populateHashMap() {
		if (playerTokens.size() == 0) {
			for (Player player : TurnLogic.game.getPlayerList()) {
				String tokenPath = "/views/assets/";
				switch(player.getToken()) {
				case BLUE:
					tokenPath += "bluePlayer.png";
					break;
				case GREEN:
					tokenPath += "greenPlayer.png";
					break;
				case GREY:
					tokenPath += "greyPlayer.png";
					break;
				case PURPLE:
					tokenPath += "purplePlayer.png";
					break;
				case RED:
					tokenPath += "redPlayer.png";
					break;
				case YELLOW:
					tokenPath += "yellowPlayer.png";
					break;
				}
				Image playerToken = new Image(tokenPath);
				playerTokens.put(player, playerToken);
			}
		}
	}
	
	public void beginTurn(Player player) {
		// Add current player's name
		lblCurrentPlayer.setText(TurnLogic.getCurrentPlayer().getName());
		// Enable roll button
		btnRoll.setDisable(false);
		// Populate player info
		updatePlayerInfo();
		// Clear previous dice roll
		paneDieOne.getChildren().clear();
		paneDieTwo.getChildren().clear();
	}
	
	public void updatePlayerInfo() {
		Player player = TurnLogic.getCurrentPlayer();
		TitledPane playerTab = playerTabs[GameManager.getCurrentPlayerIndex()];
		VBox playerData = (VBox) playerTab.getContent();
		playerData.getChildren().clear();
		Pane balance = new Pane();
		ListView<Ownable> properties = new ListView<>();
		balance.getChildren().add(new Label("Balance $" + player.getAccount().getBalance()));
		balance.setPadding(new Insets(25));
		ObservableList<Ownable> observableProperties = FXCollections.observableArrayList(player.getAccount().getProperty());
		properties.setItems(observableProperties);
		playerData.getChildren().add(balance);
		playerData.getChildren().add(properties);
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
	
	public void displayCard(Card card) {
		if (paneCard.getChildren().size() > 0) {
			paneCard.getChildren().clear();
		}
		Label cardData = new Label();
		cardData.setText(card.getDescription());
		cardData.setTextAlignment(TextAlignment.CENTER);
		Button btnClose = new Button("Close");
		btnClose.getStyleClass().add("close-button");
		btnClose.addEventHandler(ActionEvent.ACTION,
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneCard.setVisible(false);
					}
		});
		paneCard.getChildren().add(cardData);
		cardData.setVisible(true);
	}
	
	public void loadPlayers() {
		playerTabs = new TitledPane[TurnLogic.game.getPlayerList().size()];
		// Add players to accordion
		for (int i = 0; i < playerTabs.length; i++) {
			Player player = TurnLogic.game.getPlayerList().get(i);
			TitledPane playerTab = new TitledPane();
			playerTab.setText(player.getName());
			VBox playerData = new VBox();
			//playerData.setPadding(new Insets(20.0));
			playerTab.setContent(playerData);
			accordionPlayers.getPanes().add(playerTab);
			playerTabs[i] = playerTab;
		}
	}
	
	public void purchaseProperty(ActionEvent event) {
		Player player = TurnLogic.getCurrentPlayer();
		Property property = (Property) TurnLogic.game.getBoard().board.get(player.getPosition());
		GameLogic.buyProperty(player, property);
		btnPurchase.setDisable(true);
		updatePlayerInfo();
	}
	
	public void displayPurchaseOptions(ActionEvent event) {
		// Show pane
		paneMenu.setVisible(false);
		// Get current player and display options
		Player player = TurnLogic.getCurrentPlayer();
		paneMenuProperties.setVisible(true);
		ObservableList<Property> properties = FXCollections.observableArrayList(GameLogic.upgradableProperties(player));
		choiceBoxProperties.setItems(properties);
	}
	
	public void updatePlayerPositions() {
		for (Pane tile : tiles) {
			tile.getChildren().clear();
		}
		for (Player player : TurnLogic.game.getPlayerList()) {
			ImageView token = new ImageView(playerTokens.get(player));
			tiles[player.getPosition()].getChildren().add(token);
		}
	}
	
	/**
	 * Visually represents a die rolling to a specified face.
	 */
	public void rollDice(ActionEvent event) {
		Player player = TurnLogic.getCurrentPlayer();
		// Roll the dice
		TurnLogic.turn();
		updatePlayerInfo();
		int[] roll = TurnLogic.getLastRoll();
		int dieOne = roll[0];
		int dieTwo = roll[1];
		// Move current player
		updatePlayerPositions();
		// Display result of roll
		Image dieOneImage = new Image("views/assets/" + getDieImage(dieOne));
		Image dieTwoImage = new Image("views/assets/" + getDieImage(dieTwo));
		paneDieOne.getChildren().add(new ImageView(dieOneImage));
		paneDieTwo.getChildren().add(new ImageView(dieTwoImage));
		// Check roll and disable rolling again if not double sixes 
		btnRoll.setDisable(!((dieOne == 6) && (dieTwo == 6)));
		// Disable buttons of impossible functions
		Tileable currrentPosition = TurnLogic.game.getBoard().board.get(player.getPosition());
		boolean canPurchaseProperty = currrentPosition.getTYPE().equals("Property") && 
				!GameLogic.hasOwner((Property) currrentPosition);
		boolean canUpgradeProperty = GameLogic.upgradableProperties(player).size() > 0;
		boolean canDowngradeProperty = GameLogic.downgradableProperty(player).size() > 0;
		boolean canMortgage = GameLogic.mortgageableProperties(player).size() > 0;
		btnPurchase.setDisable(!canPurchaseProperty);
		btnBuyHouse.setDisable(!canUpgradeProperty);
		btnSellHouse.setDisable(!canDowngradeProperty);
		btnMortgage.setDisable(!canMortgage);
		// Make menu visible
		paneMenu.setVisible(true);
	}
	
	public void endTurn(ActionEvent event) {
		GameManager.takeTurn();
	}
	
	private String getDieImage(int face) {
		String image = "";
		switch (face) {
		case 1:
			image = "dieOne.jpg";
			break;
		case 2:
			image = "dieTwo.jpg";
			break;
		case 3:
			image = "dieThree.jpg";
			break;
		case 4:
			image = "dieFour.jpg";
			break;
		case 5:
			image = "dieFive.jpg";
			break;
		case 6:
			image = "dieSix.jpg";
			break;
		}
		return image;
	}
	
}
