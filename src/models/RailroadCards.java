package models;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class RailroadCards {

	Railroad[] railroads = new Railroad[4];
	
	public RailroadCards() {
		
		railroads[0] = new Railroad("Blue line");
		railroads[1] = new Railroad("Red line");
		railroads[2] = new Railroad("Green line");
		railroads[3] = new Railroad("Frontrunner");
		
		try {
			FileOutputStream fos = new FileOutputStream("Railroads.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(railroads);
			oos.close();
			fos.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
}
