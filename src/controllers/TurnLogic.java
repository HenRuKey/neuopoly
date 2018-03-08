package controllers;

import java.util.ArrayList;

import interfaces.Ownable;
import interfaces.Tileable;
import models.Board;
import models.Game;
import models.Player;
import models.Property;
import models.Utility;

public class TurnLogic
{
	public Game game=new Game();
	Board board=new Board();
	ArrayList<Player> players;
	Player currentPlayer;
	GameLogic gameLogic=new GameLogic();
	
	public void turn()
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
			int choice=currentPlayer.chooseOption();  
			
			switch(choice)
			{
			case 0://roll die
				game.getDie1().roll();
				int roll1=game.getDie1().getFaceValue();
				game.getDie2().roll();
				int roll2=game.getDie2().getFaceValue();
				
				currentPlayer.move(roll1+roll2);
				if(getCurrentType(currentPlayer).equals("Property"))
				{
					Ownable currentProperty=currentPlayer.getProperty();
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
				else if(currentPlayer.onTax())
				{
					int taxAmnt=currentPlayer.getTax().VALUE;
					currentPlayer.getAccount().removeFromBalance(taxAmnt);
					bunker.addToValue(taxAmnt);
				}
				else if(currentPlayer.onGo())
				{
					currentPlayer.getAccount().addToBalance(400);
				}
				else if(currentPlayer.onGoToServerRoom())
				{
					currentPlayer.sendToServerRoom();
				}
				break;
			case 1: //buy houses/hotels
				Property currentProperty=currentPlayer.chooseProperty();
				if(currentProperty.hasMonopoly())
				{
					if(currentPlayer CHOOSES HOUSE)
					{
						currentProperty.addHouse();
						currentPlayer.getAccount().removeFromBalance(currentProperty.getHouseCost());
					}
					else if (currentPlayer CHOOSES HOTEL)
					{
						currentProperty.addHotel();
						currentPlayer.getAccount().removeFromBalance(currentProperty.getHouseCost());
					}
				}
				
				break;
			case 2://trade
				
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
