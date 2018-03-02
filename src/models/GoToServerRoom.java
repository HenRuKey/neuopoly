package models;

import interfaces.Tileable;

public class GoToServerRoom implements Tileable {

	public GoToServerRoom()  {
		
	}
	//Set player to be in Server Room
	public void sendToServerRoom(Player player) {
		player.setServerRoom(true);
	}
	
}