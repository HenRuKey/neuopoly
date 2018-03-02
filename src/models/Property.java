package models;

import interfaces.Ownable;
import interfaces.Tileable;

public class Property implements Tileable, Ownable 
{
	private String name;
	public final int PRICE, MORTGAGE_PRICE;
	private byte houses;
	private boolean hotel;
	private int hotelRent;
	private boolean isMortgaged;
	private int baseRent;
	private int rent;
	private final String TYPE;
	private int houseCost;
	
	private static int totalHouses;
	private static int totalHotels;
	
	//Purple:Bridges:60:30:2:250:Houses $50 each, Hotel $50 + 4 Houses
	public Property(String t,String n,int p,int mp,int r,int hr)
	{
		TYPE=t;
		setName(n);
		PRICE=p;
		MORTGAGE_PRICE=mp;
		rent=r;
		hotelRent=hr;
		switch(TYPE)
		{
		case "Purple":houseCost=50;break;
		case "Light Blue":houseCost=50;break;
		case "Pink":houseCost=100;break;
		case "Orange":houseCost=100;break;
		case "Red":houseCost=150;break;
		case "Yellow":houseCost=150;break;
		case "Green":houseCost=200;break;
		case "Blue":houseCost=200;break;
		case "R.R":break;
		}
	}
	
	public void updateRent()
	{
		if(isMortgaged)
		{
			rent=0;
		}
		else if(hotel)
		{
			rent=hotelRent;
		}
		else
		{
			switch(houses)
			{
				case 0:rent=baseRent;break;
				case 1:rent=baseRent*5;break;
				case 2:rent=baseRent*15;break;
				case 3:rent=baseRent*45;break;
				case 4:rent=baseRent*80;break;
			}
		}
	}
	
	public void setHouseCost(int hc)
	{
		houseCost=hc;
	}
	public int getHouseCost()
	{
		return houseCost;
	}
	public void setHotelRent(int h)
	{
		hotelRent=h;
	}
	public void setName(String n)
	{
		name=n;
	}
	public String getName()
	{
		return name;
	}
	public void setHouses(byte h)
	{
		houses=h;
	}
	public byte getHouses()
	{
		return houses;
	}
	public void setHotel(boolean h)
	{
		hotel=h;
	}
	public boolean getHotel()
	{
		return hotel;
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
	public int getRent()
	{
		updateRent();
		return rent;
	}
}
