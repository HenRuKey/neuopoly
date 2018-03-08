package models;

import java.io.Serializable;
import java.util.ArrayList;
import interfaces.Tileable;

public class CommunityChest implements Tileable, Serializable {

	//List of Community Chest Cards
	private ArrayList<Card> communityChest;
	private final String TYPE;
	
	public CommunityChest() {
		TYPE="CommunityChest";
	}
	
	public void addCard(Card card) {
		communityChest.add(card);
	}
	
	public Card getCard(int index) {
		Card card = communityChest.get(index);
		return card;
	}
	
	@Override
	public String getTYPE()
	{
		return TYPE;
	}
	
}