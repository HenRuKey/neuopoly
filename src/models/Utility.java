package models;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Ownable;
import interfaces.Tileable;

public class Utility implements Tileable, Ownable, Serializable 
{
	public final int PRICE,MORTGAGE_PRICE;
	public final String TYPE;
	//final fields are public
	private String name;
	private boolean isMortgaged;
	private Player owner;
	private int rent;
	
	//only need to pass the name of the utility b/c all other properties are default
	public Utility(String n)
	{
		TYPE="Utility";
		setName(n);
		PRICE=150;
		MORTGAGE_PRICE=75;
		owner=null;
		rent=0;
	}
	
	//updates the price of the rent depending on number of railroads owned or if it's mortgaged 
	public void updateRent(int roll)
	{
		if(isMortgaged)
		{
			rent=0;
		}
		else
		{
			switch(numOfUtils())
			{
			case 1:rent=roll*4;break;
			case 2:rent=roll*10;break;
			}
		}
	}
	
	public int numOfUtils()
	{
		int counter=0;
		ArrayList<Ownable> props=owner.getAccount().getProperty();
		for(int i=0;i<props.size();i++) 
		{
			if(props.get(i).getTYPE()==this.TYPE)
			{
				counter++;
			}
		}
		return counter;
	}
	
	//all setters and getters
	@Override
	public String getTYPE()
	{
		return TYPE;
	}
	public void setName(String n)
	{
		name=n;
	}
	@Override
	public String getName()
	{
		return name;
	}
	public void setMortgaged(boolean m)
	{
		isMortgaged=m;
	}
	public boolean isMortagaged()
	{
		return isMortgaged;
	}
	public void setRent(int r)
	{
		rent=r;
	}
	
	//this method is required because it is an Ownable, however isn't useful because we need the roll 
	@Override
	public int getRent()
	{
		return 0;
	}
	//this method is the one we actually need to call because we need the roll
	public int getRent(int roll)
	{
		updateRent(roll);
		return rent;
	}
	
	@Override
	public String toString()
	{
		return name+" ["+TYPE+"]\nPrice: "+PRICE+"\nMortgage Price: "+MORTGAGE_PRICE+
				"\nRent with 1 owned: roll * 4"
				+ "\nRent with 2 owned: roll * 10";
	}

}
