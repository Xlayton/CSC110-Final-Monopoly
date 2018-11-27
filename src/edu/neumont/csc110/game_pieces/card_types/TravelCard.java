package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class TravelCard extends Card {

	protected TravelCard(String name, String flavorText, MonopolyBoard board, int spaces) {
		super(name, flavorText);
	}

	@Override
	public void applyEffect(Player toApply) {
		
	}

}
