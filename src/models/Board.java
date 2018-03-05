package models;

import java.io.Serializable;
import java.util.HashMap;

import interfaces.Tileable;

public class Board implements Serializable {

	//Game Board
	public static HashMap<Integer, Tileable> board = new HashMap<>();
	
	//Board Constructor
	public Board() {
		
	}

}