package edu.neumont.csc110.game_pieces;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces.card_types.GainMoneyCard;
import edu.neumont.csc110.game_pieces.card_types.JailBreakCard;
import edu.neumont.csc110.game_pieces.card_types.PayCard;
import edu.neumont.csc110.game_pieces.card_types.PlayerPayCard;
import edu.neumont.csc110.game_pieces.card_types.RelativePayCard;
import edu.neumont.csc110.game_pieces.card_types.TravelCard;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class CommunityChestCardList {
/**
 * all the cards for community chest that a player has to pick
 * @param board - the board of the game
 * @param players - the people playing
 * @return - goes back through the deck
 */
	public static Card[] getCommunityChestCards(MonopolyBoard board, Player[] players) {
		Card[] cards = new Card[16];

		cards[0] = new GainMoneyCard("Holiday fund matures. Receive $100.", false, 100);
		cards[1] = new GainMoneyCard("You inherit $100.", false, 100);
		cards[2] = new GainMoneyCard("From sale of stock you get $50.", false, 50);
		cards[3] = new GainMoneyCard("You have won second prize in a beauty contest. Collect $10.",
				false, 10);
		cards[4] = new GainMoneyCard("Receive $25 consultancy fee.", false, 25);
		cards[5] = new GainMoneyCard("Income tax refund. Collect $20.", false, 20);
		cards[6] = new GainMoneyCard("Life insurance matures. Collect $100.", false, 100);
		cards[7] = new GainMoneyCard("Bank error in your favor. Collect $200.", false, 200);
		cards[8] = new PlayerPayCard("It is your birthday. Collect $10 from every player.", false,
				false, players, 10);
		cards[9] = new PayCard("Hospital Fees. Pay $100.", false, 100);
		cards[10] = new PayCard("School Fees. Pay $50.", false, 50);
		cards[11] = new PayCard("Doctor's Fees. Pay $50.", false, 50);
		cards[12] = new RelativePayCard(
				"You are assessed for street repairs:\nPay $40 per house and\n$115 per hotel you own.",
				false, 40, 115);
		cards[13] = new JailBreakCard(true);
		cards[14] = new TravelCard("Advance to Go. (Collect $200)", false, board,
				board.getLocation("Go"), true);
		cards[15] = new TravelCard(
				"Go to jail. Go to directly to jail, do not pass go, do not collect $200.", false,
				board, board.getLocation("Jail"), false);

		return cards;
	}

}
