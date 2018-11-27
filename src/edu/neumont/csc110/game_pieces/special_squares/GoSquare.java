package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class GoSquare extends SpecialSquare {
	protected GoSquare(String name) {
		super("Go");
	}

	@Override
	protected void applyEffect(Player toApply) {
		toApply.addBalance(200);
	}
}
