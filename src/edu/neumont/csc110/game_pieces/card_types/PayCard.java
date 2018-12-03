package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class PayCard extends Card {
	
	private final int payAmount;
/**
 * 
 * @param flavorText - description of card
 * @param isChance - card from the chance deck
 * @param amount - how much the player is giving
 */
	public PayCard(String flavorText, boolean isChance, int amount) {
		super(flavorText, isChance);

		this.payAmount = amount;
	}
/**
 * Subtracts from the players balance
 */
	@Override
	public void applyEffect(Player toApply) throws IllegalArgumentException {
		toApply.subtractBalance(payAmount);
	}
}
