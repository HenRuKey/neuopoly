package models;

import java.io.Serializable;
import interfaces.Tileable;

public class Bunker implements Tileable, Serializable {

	private int value;
	
	public Bunker() {
		
	}
	//Give the current player the pool of money stored in the Bunker
	public void payPlayer(Player player) {
		player.getAccount().addToBalance(getValue());
		setValue(0);
	}

	public void addToValue(int v)
	{
		value+=v;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}