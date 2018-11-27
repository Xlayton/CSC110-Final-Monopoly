package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class JailBreakCard extends Card {
	public JailBreakCard(String name, String flavorText) {
		super(name, flavorText);
	}

	@Override
	public void applyEffect(Player toApply) {
		toApply.giveJailBreak();
	}
}
