package models;

import java.util.ArrayList;

import enums.Token;

public class Game {
	
	private Board board = new Board();
	private Die die1, die2 = new Die();
	private ArrayList<Player> playerList = new ArrayList<>();
	
	public void numberOfPlayers(int numberOfPlayers) {
		for (int i = 0; i < numberOfPlayers; i++) {
			playerList.add(new Player("Player " + (i+1), Token.values()[i]));
		}
	}

	public Board getBoard() {
		return board;
	}

	public Die getDie1() {
		return die1;
	}

	public Die getDie2() {
		return die2;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

}