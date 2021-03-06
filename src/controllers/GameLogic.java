package controllers;

import java.util.ArrayList;

import javax.sound.midi.Receiver;

import models.Card;
import models.Player;
import models.Property;

public class GameLogic {

	//Checks if the property the player is on has been purchased yet
	public static boolean hasOwner(Property property) {
		return !(property.getOwner() == null);
	}
	
	// Buy Property
	public static void buyProperty(Player player, Property property) {
		if (player.getAccount().getBalance() > property.PRICE) {
			player.getAccount().setBalance(player.getAccount().getBalance() - property.PRICE);
			property.setOwner(player);
			player.getAccount().addProperty(property);
		} else if (player.getAccount().getBalance() < property.PRICE) {
			// say you don't have enough money
		}
	}

	// Makes an array of of the players owned properties
	public static Property[] currentPlayerProperties(Player player) {
		// Array of players currently owned properties
		Property[] propertyList = new Property[player.getAccount().getProperty().size()];
		for (int i = 0; i < player.getAccount().getProperty().size(); i++) {
			propertyList[i] = (Property) player.getAccount().getProperty().get(i);
		}
		return propertyList;
	}
	
	public static ArrayList<Property> mortgageableProperties(Player player) {
		Property[] propertyList = currentPlayerProperties(player);
		ArrayList<Property> tempList = new ArrayList<>();
		for(int i = 0; i < propertyList.length; i++) {
			if (propertyList[i].getHouses() > 0 || propertyList[i].getHotel() || propertyList[i].isMortagaged()) {
				//doesn't add to list if any of the if conditions are met
			}else {
				tempList.add(propertyList[i]);
			}
		}
		return tempList;
	}

	// Mortgage a specific property that the player owns
	public static void mortgageProperty(Player player, int pointer) {
		ArrayList<Property> mortgageableProperty = mortgageableProperties(player);
		// compares the selected property from the temp list to the players account and
		// sets the account property to mortgaged
		Property toBeMortgagedProperty = mortgageableProperty.get(pointer);
		for (int i = 0; i < player.getAccount().getProperty().size(); i++) {
			if (toBeMortgagedProperty.equals(player.getAccount().getProperty().get(i))) {
				((Property) player.getAccount().getProperty().get(i)).setMortgaged(true);
				player.getAccount().setBalance(player.getAccount().getBalance() + toBeMortgagedProperty.MORTGAGE_PRICE);
				break;
			}
		}
	}

	// Pay rent
	public static void payRent(Property property, Player currentPlayer, Player propertyOwner) {
		int rent = property.getRent();
		currentPlayer.getAccount().setBalance(currentPlayer.getAccount().getBalance() - rent);
		propertyOwner.getAccount().setBalance(propertyOwner.getAccount().getBalance() + rent);
	}

	// Current player's list of properties available to upgrade
	public static ArrayList<Property> upgradableProperties(Player player) {
		Property[] propertyList = currentPlayerProperties(player);
		ArrayList<Property> tempList = new ArrayList<>();
		for (int i = 0; i < propertyList.length; i++) {
			if (propertyList[i].getHotel()) {
				// property already has hotel so doesn't add to tempList
			} else {
				tempList.add(propertyList[i]);
			}
		}
		return tempList;
	}

	// Adds a house at a time or a hotel if the player has 4 houses already
	public static void upgradeProperty(Player player, int pointer) {
		ArrayList<Property> upgradableList = upgradableProperties(player);
		// add house unless you have max houses
		if (upgradableList.get(pointer).getHouses() < 4) {
			for (int i = 0; i < player.getAccount().getProperty().size(); i++) {
				if (player.getAccount().getProperty().get(i).getName().equals(upgradableList.get(pointer).getName())) {
					((Property) player.getAccount().getProperty().get(i)).addHouse();
					player.getAccount().setBalance(player.getAccount().getBalance()-upgradableList.get(pointer).getHouseCost());
				}
			}
			// add hotel if player has reached max houses
		} else {
			for (int i = 0; i < player.getAccount().getProperty().size(); i++) {
				if (player.getAccount().getProperty().get(i).getName().equals(upgradableList.get(pointer).getName())) {
					((Property) player.getAccount().getProperty().get(i)).addHotel();
					player.getAccount().setBalance(player.getAccount().getBalance()-upgradableList.get(pointer).getHouseCost());
				}
			}
		}
	}

	public static ArrayList<Property> downgradableProperty(Player player) {
		Property[] propertyList = currentPlayerProperties(player);
		ArrayList <Property> tempList = new ArrayList<>();
		for (int i = 0; i < propertyList.length; i++) {
			if (propertyList[i].getHotel() || propertyList[i].getHouses() > 0) {
				tempList.add(propertyList[i]);
			}
		}
		return tempList;
	}
	
	// Sells hotel/houses one at a time
	public static void downgradeProperty(Player player, int pointer) {
		ArrayList<Property> downgradableList = downgradableProperty(player);
		//sell hotel if a hotel exists
		if (downgradableList.get(pointer).getHotel()) {
			for (int i = 0; i < player.getAccount().getProperty().size(); i++) {
				if (player.getAccount().getProperty().get(i).getName().equals(downgradableList.get(pointer).getName())) {
					((Property)player.getAccount().getProperty().get(i)).sellHotel();
					player.getAccount().setBalance(player.getAccount().getBalance() + (downgradableList.get(pointer).getHouseCost()/2));
				}
			}
		}else {
			for (int i = 0; i < player.getAccount().getProperty().size(); i++) {
				if (player.getAccount().getProperty().get(i).getName().equals(downgradableList.get(pointer).getName())) {
					((Property)player.getAccount().getProperty().get(i)).sellHouse();
					player.getAccount().setBalance(player.getAccount().getBalance() + (downgradableList.get(pointer).getHouseCost()/2));
				}
			}
		}
	}
	
	public static void useCard(Card card, Player player) {
		GameManager.viewManager.displayCard(card);
		int action = card.getAction();
		switch(action) {
		case 1:
			// pay value
			player.getAccount().removeFromBalance((int) card.getValue());
			GameManager.viewManager.alertPlayer("Removed Balance", "Removed " + card.getValue() + " from account.");
			break;
		case 2:
			// receive value
			player.getAccount().addToBalance((int) card.getValue());
			GameManager.viewManager.alertPlayer("Added Balance", "Added " + card.getValue() + " to account.");
			break;
		case 3:
			// receive from each player
			for (Player p : TurnLogic.game.getPlayerList()) {
				if (!p.equals(player)) {
					p.getAccount().removeFromBalance((int) card.getValue());
				}
				else {
					p.getAccount().addToBalance((int) card.getValue() * TurnLogic.game.getPlayerList().size() - 1);
				}
			}
			GameManager.viewManager.alertPlayer("Balance Distributed", "Player has been funded");
			break;
		case 4:
			// Pay each player
			for (Player p : TurnLogic.game.getPlayerList()) {
				if (!p.equals(player)) {
					p.getAccount().addToBalance((int) card.getValue());
				}
				else {
					p.getAccount().removeFromBalance((int) card.getValue() * TurnLogic.game.getPlayerList().size() - 1);
				}
			}
			GameManager.viewManager.alertPlayer("Balance Distributed", "Player has funded");
			break;
		case 5:
			// Move to specific spot
			player.setPosition((int) card.getValue());
			break;
		case 6:
			// Move back spaces
			player.setPosition(player.getPosition() < (int) card.getValue() ? 
					39 - (int) (card.getValue() - player.getPosition()) : player.getPosition() - (int) card.getValue());
			GameManager.viewManager.alertPlayer("Moved Positions", "Player has been moved back " + card.getValue() + " spaces.");
			break;
		}
	}

}