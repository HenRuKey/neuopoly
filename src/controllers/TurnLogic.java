package controllers;

import java.util.ArrayList;
import java.util.Random;

import interfaces.Ownable;
import interfaces.Tileable;
import models.Board;
import models.Card;
import models.Chance;
import models.CommunityChest;
import models.Game;
import models.Player;
import models.Property;
import models.Tax;

public class TurnLogic
{
	public static Game game=new Game();
	private static int[] lastRoll = new int[2];
	static Board board=new Board();
	static ArrayList<Player> players;
	static Player currentPlayer;
	static GameLogic gameLogic=new GameLogic();
	static int cardIndex = 0;
	static CommunityChest communityChest = new CommunityChest();
	static Chance chance = new Chance();
	
	public static void turn()
	{
		rollDice();
		landOnSpace();
	}
	
	public static void rollDice() {
		game.getDie1().roll();
		int roll1=game.getDie1().getFaceValue();
		game.getDie2().roll();
		int roll2=game.getDie2().getFaceValue();
		lastRoll[0] = roll1;
		lastRoll[1] = roll2;
		currentPlayer.move(roll1+roll2);
	}
	
	public static void landOnSpace() {
		if(getCurrentType(currentPlayer).equals("Property")||getCurrentType(currentPlayer).equals("R.R.")||
				getCurrentType(currentPlayer).equals("Utility"))
		{
			Ownable currentProperty=(Ownable)getCurrentTile(currentPlayer);
			if(currentProperty.getOwner() != null && currentProperty.getOwner()!=currentPlayer)
			{
				GameLogic.payRent((Property) currentProperty, currentPlayer, currentProperty.getOwner());
				GameManager.viewManager.alertPlayer("Rent!", "Pay $" + currentProperty.getRent());
			}
		}
		else if(getCurrentType(currentPlayer).equals("Tax"))
		{
			Tax currentTax=(Tax)getCurrentTile(currentPlayer);
			int taxAmnt=currentTax.VALUE;
			currentPlayer.getAccount().removeFromBalance(taxAmnt);
			board.bunker.addToValue(taxAmnt);
			GameManager.viewManager.alertPlayer("You've Been Taxed!", "Pay $" + taxAmnt);
		}
		else if(getCurrentType(currentPlayer).equals("GoStipend"))
		{
			currentPlayer.getAccount().addToBalance(400);
			GameManager.viewManager.alertPlayer("Passed Go", "Collect $400");
		}
		else if(getCurrentType(currentPlayer).equals("GoToServerRoom"))
		{
			currentPlayer.setServerRoom(true);
			GameManager.viewManager.alertPlayer("Go to Server Room", "You've got a lot of work to do.");
		}
		else if(getCurrentType(currentPlayer).equals("Bunker"))
		{
			currentPlayer.getAccount().addToBalance(board.bunker.getValue());
			board.bunker.setValue(0);
		}
		else if(getCurrentType(currentPlayer).equals("Chance") || getCurrentType(currentPlayer).equals("CommunityChest")) {
			Random random = new Random();
			Card card = getCurrentType(currentPlayer).equals("Chance") ? 
					chance.getCard(random.nextInt(chance.size())) : communityChest.getCard(random.nextInt(communityChest.size()));
			GameLogic.useCard(card, currentPlayer);
			GameManager.viewManager.updatePlayerAccount();
		}
	}
	
	private static String getCurrentType(Player currentP)
	{
		int pos=currentP.getPosition();
		Tileable tile = board.board.get(pos);
		return tile.getTYPE();
	}
	
	private static Tileable getCurrentTile(Player currentP)
	{
		int pos=currentP.getPosition();
		Tileable tile=board.board.get(pos);
		return tile;
	}
	
	public static int[] getLastRoll() {
		return new int[] {
			lastRoll[0],
			lastRoll[1]
		};
	}
	
	
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public static void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}
}
