package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class PlayerPayCard extends Card {

	protected PlayerPayCard(String name, String flavorText) {
		super(name, flavorText);
	}

	@Override
	public void applyEffect(Player toApply) {
		
	}

}
