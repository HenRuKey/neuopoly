package models;
public class CommunityChestCards {
	
	CommunityChest communityChest = new CommunityChest();
	Card[] cards = new Card[11];
	
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
	 * I am making this comment so that i can re push it
	 */
	
	public CommunityChestCards() {
		cards[0] = new Card(0, 0, 0, "You wrote the Server Room code on your Arm, Get out of Server Room free card");
		cards[1] = new Card(0, 1, 100, "Pay for groceries, pay $100");
		cards[2] = new Card(0, 2, 200, "You get your stipend early, collect $200");
		cards[3] = new Card(0, 2, 10, "You got second place at the talent show, collect $10");
		cards[4] = new Card(0, 2, 45, "You invested with your last stipend and you sold your shares for $45");
		cards[5] = new Card(0, 1, 50, "You dropped your text book in water and ruined it, pay $50 to replace it");
		cards[6] = new Card(0, 3, 50, "You won poker night, collect $50 from each player");
		cards[7] = new Card(0, 5, 10, "You forgot to setup the firewall Go to the Server Room to fix the mistake");
		cards[8] = new Card(0, 1, 150, "You make your monthly payment for school, pay $150");
		cards[9] = new Card(0, 2, 25, "You code something amazing, collect $25 for your good work");
		cards[10] = new Card(0, 2, 20, "Tax returns, collect $20");
		
		for (int i = 0; i < cards.length; i++) {
			communityChest.addCard(cards[i]);
		}
		
	}

}
