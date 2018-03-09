package models;

import java.io.Serializable;
import java.util.HashMap;

import interfaces.Tileable;

public class Board implements Serializable {

	//Game Board
	public HashMap<Integer, Tileable> board = new HashMap<>();
	private GoStipend go = new GoStipend();
	private ServerRoom serverRoom = new ServerRoom();
	public Bunker bunker = new Bunker();
	private GoToServerRoom goToServerRoom = new GoToServerRoom();
	private PropertyCards properties = new PropertyCards();
	private RailroadCards railroads = new RailroadCards();
	private UtilityCards utilities = new UtilityCards();
	private CommunityChest communityChest = new CommunityChest();
	private Chance chance = new Chance();
	private Tax books = new Tax("Books", 200);
	private Tax laptopFee = new Tax("Laptop Fee", 100);
	
	//Board Constructor
	public Board() {
		
		board.put(0, go);
		board.put(1, properties.properties[0]);
		board.put(2, communityChest);
		board.put(3, properties.properties[1]);
		board.put(4, books);
		board.put(5, railroads.railroads[0]);
		board.put(6, properties.properties[2]);
		board.put(7, chance);
		board.put(8, properties.properties[3]);
		board.put(9, properties.properties[4]);
		board.put(10, serverRoom);
		board.put(11, properties.properties[5]);
		board.put(12, utilities.utilities[0]);
		board.put(13, properties.properties[6]);
		board.put(14, properties.properties[7]);
		board.put(15, railroads.railroads[1]);
		board.put(16, properties.properties[8]);
		board.put(17, communityChest);
		board.put(18, properties.properties[9]);
		board.put(19, properties.properties[10]);
		board.put(20, bunker);
		board.put(21, properties.properties[11]);
		board.put(22, chance);
		board.put(23, properties.properties[12]);
		board.put(24, properties.properties[13]);
		board.put(25, railroads.railroads[2]);
		board.put(26, properties.properties[14]);
		board.put(27, properties.properties[15]);
		board.put(28, utilities.utilities[1]);
		board.put(29, properties.properties[16]);
		board.put(30, goToServerRoom);
		board.put(31, properties.properties[17]);
		board.put(32, properties.properties[18]);
		board.put(33, communityChest);
		board.put(34, properties.properties[19]);
		board.put(35, railroads.railroads[3]);
		board.put(36, chance);
		board.put(37, properties.properties[20]);
		board.put(38, laptopFee);
		board.put(39, properties.properties[21]);
		
	}

}