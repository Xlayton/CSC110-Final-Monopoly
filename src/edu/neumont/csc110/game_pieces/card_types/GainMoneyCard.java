package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class GainMoneyCard extends Card {
	private final int gainAmount;

	/**
	 * 
	 * @param flavorText - description of card
	 * @param isChance - one of the cards from the deck
	 * @param gainAmount - adding money to players balance
	 */

	public GainMoneyCard(String flavorText, boolean isChance, int gainAmount) {
		super(flavorText, isChance);

		this.gainAmount = gainAmount;
	}

	@Override
	public void applyEffect(Player toApply) {
		toApply.addBalance(gainAmount);
	}

}
