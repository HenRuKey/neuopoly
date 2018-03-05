package models;

import java.io.Serializable;

//class to keep track of all players in game
public class Player implements Serializable {
	
	//all fields for player 
	private String name,token; //token may be changed to an enum
	private int position;
	private boolean inServerRoom;
	private Account account;
	
	//constructor for player, doesn't need to take position or inServerRoom because they will always default
		//to 0 and false respectively when a new player is made
	public Player(String n,String t)
	{
		setName(n);
		setToken(t);
		setPosition(0);
		setServerRoom(false);
		setAccount(new Account());//creates a default account to start with (no properties and starting money)
	}
	//getters and setters for all fields
	public void setName(String n)
	{
		name=n;
	}
	public String getName()
	{
		return name;
	}
	public void setToken(String t)
	{
		token=t;
	}
	public String getToken()
	{
		return token;
	}
	public void setPosition(int p)
	{
		position=p;
	}
	public int getPosition()
	{
		return position;
	}
	public boolean isServerRoom()
	{
		return inServerRoom;
	}
	public void setServerRoom(boolean sr)
	{
		inServerRoom=sr;
	}
	public Account getAccount()
	{
		return account;
	}
	public void setAccount(Account a)
	{
		account=a;
	}
	
	//toString override for all info
	@Override
	public String toString()
	{
		return "Name: "+getName()+"\nToken: "+getToken()+"\nPosition: "+getPosition()+
				"\nIn Server Room: "+isServerRoom();
	}
}

