package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class PayCard extends Card {
	private final int payAmount;
	
	public PayCard(String name, String flavorText, int amount) {
		super(name, flavorText);
		
		this.payAmount = amount;
	}

	@Override
	public void applyEffect(Player toApply) throws IllegalArgumentException {
		toApply.subtractBalance(payAmount);
	}
}
