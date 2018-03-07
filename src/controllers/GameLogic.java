package controllers;

import java.util.ArrayList;

import models.Player;
import models.Property;

public class GameLogic {

	// Buy Property
	public void buyProperty(Player player, Property property) {
		if (player.getAccount().getBalance() > property.PRICE) {
			player.getAccount().setBalance(player.getAccount().getBalance() - property.PRICE);
		} else if (player.getAccount().getBalance() < property.PRICE) {
			// say you dont have enough money
		}
	}

	// Makes an array of of the players owned properties
	public Property[] currentPlayerProperties(Player player) {
		// Array of players currently owned properties
		Property[] propertyList = new Property[player.getAccount().getProperty().size()];
		for (int i = 0; i < player.getAccount().getProperty().size(); i++) {
			propertyList[i] = (Property) player.getAccount().getProperty().get(i);
		}
		return propertyList;
	}

	// Mortgage a specific property that the player owns
	public void mortgageProperty(Player player, int pointer) {
		Property[] propertyList = currentPlayerProperties(player);

		// compares the selectecd property from the temp list to the players account and
		// sets the account property to mortgaged
		Property toBeMortgagedProperty = propertyList[pointer];
		for (int i = 0; i < player.getAccount().getProperty().size(); i++) {
			if (toBeMortgagedProperty.equals(player.getAccount().getProperty().get(i))) {
				((Property) player.getAccount().getProperty().get(i)).setMortgaged(true);
				player.getAccount().setBalance(player.getAccount().getBalance() + toBeMortgagedProperty.MORTGAGE_PRICE);
				break;
			}
		}
	}

	// Pay rent
	public void payRent(Property property, Player currentPlayer, Player propertyOwner) {
		int rent = property.getRent();
		currentPlayer.getAccount().setBalance(currentPlayer.getAccount().getBalance() - rent);
		propertyOwner.getAccount().setBalance(propertyOwner.getAccount().getBalance() + rent);
	}

	// Current player's list of properties available to upgrade
	public ArrayList<Property> propertiesAvailableToUpgrade(Player player) {
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

	public void upgradeProperty(Player player, int pointer) {
		ArrayList<Property> upgradableList = propertiesAvailableToUpgrade(player);
		if (upgradableList.get(pointer).getHouses() < 4) {
			upgradableList.get(pointer).addHouse();
		} else {
			upgradableList.get(pointer).addHotel();
		}
	}

}