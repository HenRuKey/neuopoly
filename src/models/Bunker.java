package models;

import java.io.Serializable;
import interfaces.Tileable;

public class Bunker implements Tileable, Serializable {

	private int value;
	
	public Bunker() {
		
	}
	//Give the current player the pool of money stored in the Bunker
	public void payPlayer(Player player) {
		player.getAccount().setBalance(player.getAccount().getBalance()+ getValue());
		setValue(0);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}