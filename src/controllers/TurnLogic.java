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
	public Game game=new Game();
	Board board=new Board();
	ArrayList<Player> players;
	Player currentPlayer;
	GameLogic gameLogic=new GameLogic();
	
	public void turn(int choice)//pass the choice from UI into turn method
	{
		/*
		 * !!NOTICE!!
		 * All methods in this class that do not exist are placeholders until we create the methods for them
		 * 
		 */
		players=game.getPlayerList();
		for(int i=0;i<players.size();i++)
		{
			currentPlayer=players.get(i);
			
			//choose what they want to do from the UI (roll, buy houses/hotel, trade(?))
			
			switch(choice)
			{
			case 0://roll die
				game.getDie1().roll();
				int roll1=game.getDie1().getFaceValue();
				game.getDie2().roll();
				int roll2=game.getDie2().getFaceValue();
				
				currentPlayer.move(roll1+roll2);
				if(getCurrentType(currentPlayer).equals("Property")||getCurrentType(currentPlayer).equals("R.R.")||
						getCurrentType(currentPlayer).equals("Utility"))
				{
					Ownable currentProperty=(Ownable)getCurrentTile(currentPlayer);
					boolean isUtil=false;
					if(currentProperty.getTYPE()=="Utility"){isUtil=true;}
					if(currentProperty.getOwner()==null)
					{
						gameLogic.buyProperty(currentPlayer,(Property)currentProperty);
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
							currentRent=currentProp.getRent(roll1+roll2);
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
				break;
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
			}
		}
	}
	
	private String getCurrentType(Player currentP)
	{
		int pos=currentP.getPosition();
		Tileable tile=board.board.get(pos);
		return tile.getTYPE();
	}
	
	private Tileable getCurrentTile(Player currentP)
	{
		int pos=currentP.getPosition();
		Tileable tile=board.board.get(pos);
		return tile;
	}
}
