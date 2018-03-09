package controllers;

import java.util.ArrayList;

import interfaces.Ownable;
import interfaces.Tileable;
import models.Board;
import models.Game;
import models.Player;
import models.Property;
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
	
	public static void turn(int choice)//pass the choice from UI into turn method
	{
		switch(choice)
		{
		case 0://roll die
			rollDice();
			landOnSpace();
			break;
		/*
		case 1: //buy houses/hotels
			Property currentProperty=currentPlayer.chooseProperty();//get choice from UI
			if(currentProperty.hasMonopoly())
			{
				gameLogic.upgradeProperty(currentPlayer,int pointer);
			}
			
			break;
		case 2://sell houses/hotels
			currentProperty=currentPlayer.chooseProperty();
			gameLogic.downgradeProperty(currentPlayer,int pointer);
			break;
		*/
		}
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
			if(currentProperty.getTYPE() == "Property" && currentProperty.getOwner()==null)
			{
				GameLogic.buyProperty(currentPlayer,(Property)currentProperty);
			}
			else if(currentProperty.getOwner()!=currentPlayer)
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
