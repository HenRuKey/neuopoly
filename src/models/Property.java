package models;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Ownable;
import interfaces.Tileable;

public class Property implements Tileable, Ownable, Serializable {
	/**
	 * 
	 */
	// this class is for all PROPERTIES, not including utilities and railroads.
	public final int PRICE, MORTGAGE_PRICE;
	public final String TYPE;
	// final fields are public
	private String name, description;
	private byte houses;
	private boolean hotel, isMortgaged;
	private int hotelRent, baseRent, rent, houseCost;
	private Player owner;

	// totalHouses and totalHotels are static because there is a limited amount of
	// houses/hotels
	private static int totalHouses = 32;
	private static int totalHotels = 12;

	// Purple:Bridges:60:30:2:250:Houses $50 each, Hotel $50 + 4 Houses
	public Property(String t, String n, int p, int mp, int r, int hr, String d) {
		TYPE = t;
		setName(n);
		PRICE = p;
		MORTGAGE_PRICE = mp;
		rent = r;
		hotelRent = hr;
		description = d;
		owner = null;
		// depending what side of the board the property is, determines price of
		// houses/hotels
		switch (TYPE) {
		case "Purple":
			houseCost = 50;
			break;
		case "Light Blue":
			houseCost = 50;
			break;
		case "Pink":
			houseCost = 100;
			break;
		case "Orange":
			houseCost = 100;
			break;
		case "Red":
			houseCost = 150;
			break;
		case "Yellow":
			houseCost = 150;
			break;
		case "Green":
			houseCost = 200;
			break;
		case "Blue":
			houseCost = 200;
			break;
		}
	}

	// updates the price of the rent depending on number of houses/hotels or if it's
	// mortgaged
	public void updateRent() {
		if (isMortgaged) {
			rent = 0;
		} else if (hotel) {
			rent = hotelRent;
		} else {
			// all houses affect the rent by a common multiplier
			switch (houses) {
			case 0:
				rent = hasMonopoly() ? baseRent * 2 : baseRent;
				break;
			case 1:
				rent = baseRent * 5;
				break;
			case 2:
				rent = baseRent * 15;
				break;
			case 3:
				rent = baseRent * 45;
				break;
			case 4:
				rent = baseRent * 80;
				break;
			}
		}
	}

	public boolean hasMonopoly() {
		int counter = 0;
		ArrayList<Ownable> props = owner.getAccount().getProperty();
		for (int i = 0; i < props.size(); i++) {
			if (props.get(i).getTYPE() == this.TYPE) {
				counter++;
			}
		}
		return counter == 4;
	}

	public void addHouse() {
		// only able to add house if there are houses available
		if (totalHouses > 0) {
			// adds a house to the property and removes one from the totals
			houses++;
			totalHouses--;
		}
	}

	public void addHotel() {
		// only adds hotel if there are four houses
		if (houses == 4) {
			// resets the houses to 0 and adjusts totals
			houses = 0;
			totalHouses += 4;
			hotel = true;
			totalHotels--;
		}
	}

	// all setters and getters
	@Override
	public String getTYPE() {
		return TYPE;
	}

	public void setHouseCost(int hc) {
		houseCost = hc;
	}

	public int getHouseCost() {
		return houseCost;
	}

	public void setHotelRent(int h) {
		hotelRent = h;
	}

	public void setName(String n) {
		name = n;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setHouses(byte h) {
		houses = h;
	}

	public byte getHouses() {
		return houses;
	}

	public void setHotel(boolean h) {
		hotel = h;
	}

	public boolean getHotel() {
		return hotel;
	}

	public void setMortgaged(boolean m) {
		isMortgaged = m;
	}

	public boolean isMortagaged() {
		return isMortgaged;
	}

	public void setRent(int r) {
		rent = r;
	}

	@Override
	public int getRent() {
		updateRent();
		return rent;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return name + " [" + TYPE + "]\nPrice: " + PRICE + "\nMortgage Price: " + MORTGAGE_PRICE + "\nRent: $"
				+ baseRent + "\nWith 1 House: $" + baseRent * 5 + "\nWith 2 Houses: $" + baseRent * 15
				+ "\nWith 3 Houses: $" + baseRent * 45 + "\nWith 4 Houses: $" + baseRent * 80 + "\nWith HOTEL: $"
				+ hotelRent + "\n" + description;
	}
}
