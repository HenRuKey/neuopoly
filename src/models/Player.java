package models;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import controllers.TurnLogic;
import enums.Token;

//class to keep track of all players in game
public class Player implements Serializable {
	
	//all fields for player 
	private String name;
	private Token token; //token may be changed to an enum
	private int position;
	private boolean inServerRoom;
	private Account account;
	
	//constructor for player, doesn't need to take position or inServerRoom because they will always default
		//to 0 and false respectively when a new player is made
	public Player(String n,Token t)
	{
		setName(n);
		setToken(t);
		setPosition(0);
		setServerRoom(false);
		setAccount(new Account());//creates a default account to start with (no properties and starting money)
	}
	
	public void move(int num)
	{
		int newPosition = num + position > 39 ? num + position - 39 : num + position;
		setPosition(newPosition);
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
	public void setToken(Token t)
	{
		token=t;
	}
	public Token getToken()
	{
		return token;
	}
	public int getPosition()
	{
		return position;
	}
	public void setPosition(int p) 
	{
		position = p;
	}
	public boolean isServerRoom()
	{
		return inServerRoom;
	}
	public void setServerRoom(boolean sr)
	{
		inServerRoom=sr;
		if (inServerRoom) {
			setPosition(10);
		}
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

