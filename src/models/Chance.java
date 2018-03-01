package models;

import java.util.ArrayList;

public class Chance {


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