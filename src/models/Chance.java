package models;

import java.io.Serializable;
import java.util.ArrayList;

import com.sun.glass.ui.Size;

import interfaces.Tileable;

public class Chance implements Tileable, Serializable {

	//List of Chance Cards
	private ChanceCards deck = new ChanceCards();
	private ArrayList<Card> chance = new ArrayList<Card>();
	private final String TYPE;
	
	public Chance() {
		TYPE="Chance";
		for (Card card : deck.getCards()) {
			addCard(card);
		}
	}
	
	public void addCard(Card card) {
		chance.add(card);
	}
	
	public Card getCard(int index) {
		Card card = chance.get(index);
		return card;
	}
	
	public int size() {
		return chance.size();
	}
	
	@Override
	public String getTYPE()
	{
		return TYPE;
	}
}