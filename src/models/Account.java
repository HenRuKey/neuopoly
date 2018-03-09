package models;

import java.io.Serializable;
import java.util.ArrayList;
import interfaces.Ownable;

public class Account implements Serializable {
	
	private int balance;
	private ArrayList<Ownable> property;
	private byte gosrf;
	public Account()
	{
		setBalance(1500);
		setGosrf((byte)0);
	}
	
	public void addProperty(Ownable p)
	{
		property.add(p);
	}
	public void removeProperty(Ownable p)
	{
		property.remove(p);
	}
	
	public int getBalance()
	{
		return balance;
	}
	public void setBalance(int b)
	{
		balance=b;
	}
	
	public void addToBalance(int b)
	{
		balance+=b;
	}
	
	public void removeFromBalance(int b)
	{
		balance-=b;
	}
	
	public ArrayList<Ownable> getProperty()
	{
		if (property == null) {
			property = new ArrayList<>();
		}
		return property;
	}
	public void setProperty(ArrayList<Ownable> p)
	{
		property=p;
	}
	public byte getGosrf()
	{
		return gosrf;
	}
	public void setGosrf(byte g)
	{
		gosrf=g;
	}
}
