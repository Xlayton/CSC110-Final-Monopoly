package edu.neumont.csc110.game_pieces;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class CommunityChestCard extends Card {

	public CommunityChestCard(String name, String flavorText) {
		super(name, flavorText);
	}

	@Override
	public void applyEffect(Player toApply) {
	}

}
