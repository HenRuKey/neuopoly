package models;

import java.io.Serializable;
import interfaces.Tileable;

public class GoToServerRoom implements Tileable, Serializable {

	public GoToServerRoom()  {
		
	}
	//Set player to be in Server Room
	public void sendToServerRoom(Player player) {
		player.setServerRoom(true);
	}
	
}