package interfaces;

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
	 * Visually represents a die rolling to a specified face.
	 * @param rolledFace randomly generated result of a die roll
	 */
	public void rollDie(int rolledFace);

}
