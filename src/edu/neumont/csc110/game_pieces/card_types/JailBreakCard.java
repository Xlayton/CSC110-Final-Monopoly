package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class JailBreakCard extends Card {
	
	/**
	 * Constructs a new instance of a get out of jail free card
	 * 
	 * @param isChance - whether this is a chance card or not
	 */
	public JailBreakCard(boolean isChance) {
		super("GET OUT OF JAIL FREE.\nThis card may be kept until needed or traded.", isChance);
	}

	/**
	 * Player gets out of jail
	 */
	@Override
	public void applyEffect(Player toApply) {
		toApply.giveJailBreak();
	}
}
