package models;

import java.io.Serializable;
import interfaces.Tileable;

public class ServerRoom implements Tileable, Serializable {
	private final String TYPE;
	public ServerRoom() {
		TYPE="ServerRoom";
	}
	@Override
	public String getTYPE()
	{
		return TYPE;
	}
}