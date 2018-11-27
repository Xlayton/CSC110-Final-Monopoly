package edu.neumont.csc110.game_pieces;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces.card_types.*;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class ChanceCardList {
	public static Card[] getChanceCards(MonopolyBoard board, Player[] players) {
		Card[] cards = new Card[16];

		cards[0] = new GainMoneyCard("Bank pays you dividends of $50.", true, 50);
		cards[1] = new GainMoneyCard("Your building loan matures. Collect $150.", true, 150);
		cards[2] = new PayCard("Speeding fine $15.", true, 15);
		cards[3] = new PlayerPayCard(
				"You have been elected chairman of the board. Pay each player $50.", true, false,
				players, 50);
		cards[4] = new RelativePayCard(
				"Make general repairs on all your property:\nFor each house pay $25,\nFor each hotel pay $100.",
				true, 25, 100);
		cards[5] = new JailBreakCard(true);
		cards[6] = new RelativeTravelCard("Go back 3 spaces.", true, board, "spaces");
		cards[7] = new RelativeTravelCard("Advance to the nearest railroad.\n"
				+ "If unowned, you may buy it from the bank.\n"
				+ "If owned, pay owner twice the rental to which they are otherwise entitled.",
				true, board, "railroad");
		cards[8] = new RelativeTravelCard("Advance to the nearest railroad.\n"
				+ "If unowned, you may buy it from the bank.\n"
				+ "If owned, pay owner twice the rental to which they are otherwise entitled.",
				true, board, "railroad");
		cards[9] = new RelativeTravelCard(
				"Advance to the nearest utility.\n" + "If unowned, you may buy it from the bank.\n"
						+ "If owned, throw dice and pay owner ten times the amount thrown.",
				true, board, "utility");
		cards[10] = new TravelCard("Advance to Go. (Collect $200)", true, board,
				board.getLocation("Go"), true);
		cards[11] = new TravelCard(
				"Go to jail. Go to directly to jail, do not pass go, do not collect $200.", true,
				board, board.getLocation("Jail"), false);
		cards[12] = new TravelCard("Advance to boardwalk.", true, board,
				board.getLocation("Boardwalk"), true);
		cards[13] = new TravelCard("Advance to St. Charles Place. If you pass go, collect $200.",
				true, board, board.getLocation("St. Charles Place"), true);
		cards[14] = new TravelCard("Take a trip to Reading Railroad. If you pass go, collect $200.",
				true, board, board.getLocation("Reading Railroad"), true);
		cards[15] = new TravelCard("Advance to Illinois Avenue. If you pass go, collect $200.",
				true, board, board.getLocation("Illinois Avenue"), true);

		return cards;
	}
}

