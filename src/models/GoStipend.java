package models;

import java.io.Serializable;
import interfaces.Tileable;

public class GoStipend implements Tileable, Serializable {

	private final int value = 200;
	private final String TYPE;
	
	public GoStipend() {
		TYPE="GoStipend";
	}
  
	//Pay for passing Go
	public void payPlayer(Player player) {
		player.getAccount().setBalance(player.getAccount().getBalance() +value);
	}
  
	//Pay for landing on Go
	public void landOnGo(Player player) {
		player.getAccount().setBalance(player.getAccount().getBalance() +(value *2));
	}
	
	@Override
	public String getTYPE()
	{
		return TYPE;
	}
	
}