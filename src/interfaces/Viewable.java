package interfaces;

import models.Card;
import models.Player;

public interface Viewable {

	/**
	 * Displays a window to the user containing options
	 * to start the game or read instructions. This window 
	 * will also prompt for the number of players, each player's
	 * name, and the game piece they want to use.
	 */
	public void showMainMenu();
	
	/**
	 * Displays a window containing the game instructions.
	 */
	public void showInstructionsWindow();
	
	
	/**
	 * Updates the physical position of the player on the 
	 * game board.
	 * @param player a player who has moved to a new tile
	 */
	public void updatePlayerPosition(Player player);
	
	/**
	 * Updates the visual representation of a player's account,
	 * including monetary changes, property acquisitions, and 
	 * card availability.
	 * @param player a player whose account has been altered
	 */
	public void updatePlayerAccount(Player player);
	

	/**
	 * Display the window containing the game board, players,
	 * and player data.
	 */
	void showGameBoard();

	/**
	 * Display the set up window that will get player count, player
	 * names, and player pieces.
	 */
	void showSetUpMenu();
	
	
	/**
	 * Display the card a player has drawn from the deck.
	 * @param card player's card
	 */
	void displayCard(Card card);

	
	
	public void beginTurn(Player player);

}
