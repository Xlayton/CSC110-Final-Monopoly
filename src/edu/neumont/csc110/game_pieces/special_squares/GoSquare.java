package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class GoSquare extends SpecialSquare {
	public GoSquare() {
		super("Go");
	}

	@Override
	public void applyEffect(Player toApply) {
		toApply.addBalance(200);
	}
}
