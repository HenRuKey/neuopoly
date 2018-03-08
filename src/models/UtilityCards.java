package models;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import interfaces.Tileable;

public class UtilityCards implements Tileable, Serializable{
	
	Utility[] utilities = new Utility[2];
	private final String TYPE;
	public UtilityCards() {
		TYPE="UtilityCards";
		
		utilities[0] = new Utility("Tuition");
		utilities[1] = new Utility("Video Games");
		
		try {
			FileOutputStream fos = new FileOutputStream("Utilities.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(utilities);
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
