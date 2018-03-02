package models;

import interfaces.Tileable;

public class Tax implements Tileable
{
	private String type; 
	//type to store income, sales tax, etc
	public final int VALUE;
	//value, aka how much the tax costs you
	//VALUE is public because it is final and can't change, so it can be viewed from other classes
	//without the need of a getter/setter
	
	public Tax(String t, int v)
	{
		setType(t);
		VALUE=v;
	}
	
	public void setType(String t)
	{
		type=t;
	}
	public String getType()
	{
		return type;
	}
	//getters and setters are not needed for VALUE because it is public final
}
