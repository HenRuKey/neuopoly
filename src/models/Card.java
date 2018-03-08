package models;

import java.io.Serializable;

public class Card implements Serializable {

	private int type;
	private int action;
	private double value;
	private String description;
	
	//I am making this comment so that i can re push it 

	public Card(String card) {
		String [] cards = card.split(":");
		
		this.type = Integer.parseInt(cards[0]);
		this.action = Integer.parseInt(cards[1]);
		this.value = Integer.parseInt(cards[2]);
		this.description = cards[3];
		
	}
	
	public Card(int type, int action, double value, String description) {
		
		this.type = type;
		this.action = action;
		this.value = value;
		this.description = description;
		
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
