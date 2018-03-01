package models;

import java.util.ArrayList;

public class CommunityChest {

	private ArrayList<Card> communityChest;
	
	public CommunityChest() {
		
	}
	
	public void addCard(Card card) {
		communityChest.add(card);
	}
	
	public Card getCard(int index) {
		Card card = communityChest.get(index);
		return card;
	}
	
}