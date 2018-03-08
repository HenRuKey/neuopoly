package models;

import java.io.Serializable;
import interfaces.Tileable;

public class GoToServerRoom implements Tileable, Serializable {

	private final String TYPE;
	public GoToServerRoom()  {
		TYPE="GoToServerRoom";
	}
	//Set player to be in Server Room
	public void sendToServerRoom(Player player) {
		player.setServerRoom(true);
	}
	
	@Override
	public String getTYPE()
	{
		return TYPE;
	}
	
}