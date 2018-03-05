package models;

import java.io.Serializable;
import java.util.ArrayList;
import interfaces.Tileable;

public class Chance implements Tileable, Serializable {

	//List of Chance Cards
	private ArrayList<Card> chance;
	
	public Chance() {
		
	}
	
	public void addCard(Card card) {
		chance.add(card);
	}
	
	public Card getCard(int index) {
		Card card = chance.get(index);
		return card;
	}
	
}