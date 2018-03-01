package models;

public class Card {
	private int type;
	private int action;
	private double value;
	private String description;

	public Card(String card) {
		String [] cards = card.split(":");
		
		this.type = Integer.parseInt(cards[0]);
		this.action = Integer.parseInt(cards[1]);
		this.value = Integer.parseInt(cards[2]);
		this.description = cards[3];
		
	}

	public int getType() {
		return type;
	}
	
	public int getAction() {
		return action;
	}

	public double getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return this.description;
	}
}
