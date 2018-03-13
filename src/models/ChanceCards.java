package models;

public class ChanceCards {
	
	Card[] cards = new Card[10];
	
	/*
	 * Type 0 = community chest
	 * Type 1 = chance
	 * 
	 * Action 0 = gosfCard
	 * Action 1 = pay value
	 * Action 2 = receive value
	 * Action 3 = receive from each player
	 * Action 4 = pay each player
	 * Action 5 = move to  specific spot (value = location on board)
	 * Action 6 = move back a number of spaces (value = number of spaces)
	 * 
	 * Value = amount that changes
	 * 
	 * Description = What the card says
	 * 
	 * I am making this comment so i can push it
	 */
	
	public ChanceCards() {
		//cards[0] = new Card(1, 0, 0, "An I.S. student walks past the server room and lets you out, Get out of Server Room free card");
		cards[0] = new Card(1, 5, 0, "Go to collect your stipend");
		cards[1] = new Card(1, 5, 10, "You need to reconfigure the network Go to the Server Room");
		cards[2] = new Card(1, 5, 5, "Go to the blue line");
		cards[3] = new Card(1, 5, 35, "Take frontrunner");
		cards[4] = new Card(1, 6, 3, "You dropped your student ID, go back 3 spaces to retrieve it");
		cards[5] = new Card(1, 4, 50, "You try to buy the smash tournament, pay each player $50");
		cards[6] = new Card(1, 5, 39, "You get a little hungry, advance to Nuemont Market");
		cards[7] = new Card(1, 1, 100, "If you own houses or hotels, pay $100");
		cards[8] = new Card(1, 2, 25, "You code something amazing, collect $25 for your good work");
		cards[9] = new Card(1, 2, 20, "Tax returns, collect $20");
	}
	
	
	public Card[] getCards() {
		return this.cards;
	}
	
	
}
