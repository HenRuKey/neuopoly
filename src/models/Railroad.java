package models;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Ownable;
import interfaces.Tileable;

public class Railroad implements Tileable, Ownable, Serializable 
{
	public final int PRICE,MORTGAGE_PRICE;
	public final String TYPE;
	//final fields are public
	private String name;
	private boolean isMortgaged;
	private Player owner;
	private int rent;
	
	//only need to pass the name of the railroad b/c all other properties are default
	public Railroad(String n)
	{
		TYPE="R.R";
		setName(n);
		PRICE=200;
		MORTGAGE_PRICE=100;
		owner=null;
		rent=50;
	}
	
	//updates the price of the rent depending on number of railroads owned or if it's mortgaged 
	public void updateRent()
	{
		if(isMortgaged)
		{
			rent=0;
		}
		else
		{
			switch(numOfRailroads())
			{
			case 1:rent=25;break;
			case 2:rent=50;break;
			case 3:rent=100;break;
			case 4:rent=200;break;
			}
		}
	}
	
	public int numOfRailroads()
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
	@Override
	public int getRent()
	{
		updateRent();
		return rent;
	}
	
	@Override
	public Player getOwner()
	{
		return owner;
	}
	
	public void setOwner(Player o)
	{
		owner=o;
	}
	
	@Override
	public String toString()
	{
		return name+" ["+TYPE+"]\nPrice: "+PRICE+"\nMortgage Price: "+MORTGAGE_PRICE+"\nRent with 1 owned: $25"
				+ "\nRent with 2 owned: $50\nRent with 3 owned: $100\nRent with 4 owned: $200";
	}
}
