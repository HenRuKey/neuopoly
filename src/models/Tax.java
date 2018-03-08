package models;

import java.io.Serializable;
import interfaces.Tileable;

public class Tax implements Tileable, Serializable {
	
	private String name; 
	//type to store income, sales tax, etc
	public final int VALUE;
	//value, aka how much the tax costs you
	//VALUE is public because it is final and can't change, so it can be viewed from other classes
	//without the need of a getter/setter
	private final String TYPE;
	
	public Tax(String t, int v)
	{
		TYPE="Tax";
		setName(t);
		VALUE=v;
	}
	
	public void setName(String t)
	{
		name=t;
	}
	public String getName()
	{
		return name;
	}
	
	@Override
	public String getTYPE()
	{
		return TYPE;
	}
	//getters and setters are not needed for VALUE because it is public final
}
