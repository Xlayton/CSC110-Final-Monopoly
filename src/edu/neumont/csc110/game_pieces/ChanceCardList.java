package edu.neumont.csc110.game_pieces;

import edu.neumont.csc110.game_pieces.card_types.GainMoneyCard;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class ChanceCardList {
	public static Card[] getChanceCards() {
		Card[] cards = new Card[16];
		
		cards[0] = new GainMoneyCard("Bank pays you dividends of $50.", true, 50);
		
		return cards;
	}
}

