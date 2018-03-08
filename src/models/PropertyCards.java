package models;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import interfaces.Tileable;

public class PropertyCards implements Tileable, Serializable{

	Property[] properties = new Property[22];
	private final String TYPE;
	
	public PropertyCards() {
		TYPE="PropertyCards";
		properties[0] = new Property("Purple", "Bridges", 60, 30, 2, 250, "Houses $50 each, Hotel $50 + 4 Houses");
		properties[1] = new Property("Purple", "City Station", 60, 30, 4, 450, "Houses $50 each, Hotel $50 + 4 Houses");
		properties[2] = new Property("Light Blue", "Mercer", 100, 50, 6, 550, "Houses $50 each, Hotel $50 + 4 Houses");
		properties[3] = new Property("Light Blue", "Elevate", 100, 50, 6, 550, "Houses $50 each, Hotel $50 + 4 Houses");
		properties[4] = new Property("Light Blue", "Essex", 120, 60, 8, 600, "Houses $50 each, Hotel $50 + 4 Houses");
		properties[5] = new Property("Pink", "Commons' Ping Pong Table", 140, 70, 10, 750, "Houses $100 each, Hotel $100 + 4 Houses");
		properties[6] = new Property("Pink", "Commons' Pool Table", 140, 70, 10, 750, "Houses $100 each, Hotel $100 + 4 Houses");
		properties[7] = new Property("Pink", "Commons' Foosball", 160, 80, 12, 900, "Houses $100 each, Hotel $100 + 4 Houses");
		properties[8] = new Property("Orange", "City Center Apple Store", 180, 90, 14, 950, "Houses $100 each, Hotel $100 + 4 Houses");
		properties[9] = new Property("Orange", "City Center Microsoft Store", 180, 90, 14, 950, "Houses $100 each, Hotel $100 + 4 Houses");
		properties[10] = new Property("Orange", "City Center Tricked Out", 200, 100, 16, 1000, "Houses $100 each, Hotel $100 + 4 Houses");
		properties[11] = new Property("Red", "Gandolfo's", 220, 110, 18, 1050, "Houses $150 each, Hotel $150 + 4 Houses");
		properties[12] = new Property("Red", "Chick-fil-A", 220, 110, 18, 1050, "Houses $150 each, Hotel $150 + 4 Houses");
		properties[13] = new Property("Red", "McDonald's", 240, 120, 20, 1100, "Houses $150 each, Hotel $150 + 4 Houses");
		properties[14] = new Property("Yellow", "Salt Lake Public Library", 260, 130, 22, 1150, "Houses $150 each, Hotel $150 + 4 Houses");
		properties[15] = new Property("Yellow", "The Leonardo Museum", 260, 130, 22, 1150, "Houses $150 each, Hotel $150 + 4 Houses");
		properties[16] = new Property("Yellow", "Eccles Theater", 280, 140, 24, 1200, "Houses $150 each, Hotel $150 + 4 Houses");
		properties[17] = new Property("Green", "Starbucks", 300, 150, 26, 1275, "Houses $200 each, Hotel $200 + 4 Houses");
		properties[18] = new Property("Green", "Village Baker", 300, 150, 26, 1275, "Houses $200 each, Hotel $200 + 4 Houses");
		properties[19] = new Property("Green", "Three Pines Coffee", 320, 160, 28, 1400, "Houses $200 each, Hotel $200 + 4 Houses");
		properties[20] = new Property("Blue", "Commons", 350, 175, 35, 1500, "Houses $200 each, Hotel $200 + 4 Houses");
		properties[21] = new Property("Blue", "Neumont Market", 400, 200, 50, 2000, "Houses $200 each, Hotel $200 + 4 Houses");
		
		
		try {
			FileOutputStream fos = new FileOutputStream("Properties.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(properties);
			oos.close();
			fos.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	
		
	}
	
	
	@Override
	public String getTYPE()
	{
		return TYPE;
	}
	
	
}
