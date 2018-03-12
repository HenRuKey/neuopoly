package controllers;

import java.util.ArrayList;
import interfaces.Ownable;
import interfaces.Tileable;
import models.Board;
import models.Game;
import models.Player;
import models.Tax;
import models.Utility;

public class TurnLogic
{
	public static Game game=new Game();
	private static int[] lastRoll = new int[2];
	static Board board=new Board();
	static ArrayList<Player> players;
	static Player currentPlayer;
	static GameLogic gameLogic=new GameLogic();
	static int cardIndex = 0;
	
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
			boolean isUtil=false;
			if(currentProperty.getTYPE()=="Utility"){isUtil=true;}
			if(currentProperty.getOwner() != null && currentProperty.getOwner()!=currentPlayer)
			{
				int currentRent;
				if(!isUtil)
				{
					currentRent=currentProperty.getRent();
				}
				else
				{
					Utility currentProp=(Utility)currentProperty;
					currentRent=currentProp.getRent(lastRoll[0]+lastRoll[1]);
				}
				currentPlayer.getAccount().removeFromBalance(currentRent);
				currentProperty.getOwner().getAccount().addToBalance(currentRent);
			}
		}
		else if(getCurrentType(currentPlayer).equals("Tax"))
		{
			Tax currentTax=(Tax)getCurrentTile(currentPlayer);
			int taxAmnt=currentTax.VALUE;
			currentPlayer.getAccount().removeFromBalance(taxAmnt);
			board.bunker.addToValue(taxAmnt);
		}
		else if(getCurrentType(currentPlayer).equals("GoStipend"))
		{
			currentPlayer.getAccount().addToBalance(400);
		}
		else if(getCurrentType(currentPlayer).equals("GoToServerRoom"))
		{
			currentPlayer.setServerRoom(true);
		}
		else if(getCurrentType(currentPlayer).equals("Bunker"))
		{
			currentPlayer.getAccount().addToBalance(board.bunker.getValue());
			board.bunker.setValue(0);
		}
		else if(getCurrentType(currentPlayer).equals("Chance")) {
			/*
			Chance chance = (Chance) getCurrentTile(currentPlayer);
			try {
				Card card = chance.getCard(cardIndex++);	
				GameManager.viewManager.displayCard(card);
			}
			catch (ArrayIndexOutOfBoundsException ex) {
				cardIndex = 0;
				Card card = chance.getCard(cardIndex++);
				GameManager.viewManager.displayCard(card);
			}
			*/
			// Cards don't exist
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
