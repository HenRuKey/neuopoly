package controllers;

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

	//Makes an array of of the players owned properties
	public void currentPlayerProperties(Player player) {
		//Array of players currently owned properties
		Property[] propertyList = new Property[player.getAccount().getProperty().size()];
		for (int i = 0; i < player.getAccount().getProperty().size(); i++) {
			propertyList[i] = (Property) player.getAccount().getProperty().get(i);
		}
	}

	//Mortgage a specific property that the player owns
	public void mortgageProperty(Player player, int pointer) {
		//Array of players currently owned properties
		Property[] propertyList = new Property[player.getAccount().getProperty().size()];
		for (int i = 0; i < player.getAccount().getProperty().size(); i++) {
			propertyList[i] = (Property) player.getAccount().getProperty().get(i);
		}
		//compares the selectecd property from the temp list to the players account and sets the account property to mortgaged
		Property toBeMortgagedProperty = propertyList[pointer];
		for (int i = 0; i < player.getAccount().getProperty().size(); i++) {
			if (toBeMortgagedProperty.equals(player.getAccount().getProperty().get(i))) {
				((Property)player.getAccount().getProperty().get(i)).setMortgaged(true);
				break;
			}
		}
	}
	
}