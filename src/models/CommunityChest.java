package models;

import java.io.Serializable;
import java.util.ArrayList;
import interfaces.Tileable;

public class CommunityChest implements Tileable, Serializable {

	//List of Community Chest Cards
	private CommunityChestCards deck = new CommunityChestCards();
	private ArrayList<Card> communityChest = new ArrayList<Card>();
	private final String TYPE;
	
	public CommunityChest() {
		TYPE="CommunityChest";
		for (Card card : deck.getCards()) {
			addCard(card);
		}
	}
	
	public void addCard(Card card) {
		communityChest.add(card);
	}
	
	public Card getCard(int index) {
		return communityChest.get(index);
	}
	
	public int size() {
		return communityChest.size();
	}
	
	@Override
	public String getTYPE()
	{
		return TYPE;
	}
	
}