package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class JailSquare extends SpecialSquare {
	public JailSquare() {
		super("Jail");
	}

	@Override
	public void applyEffect(Player toApply) {
		return;
	}
}
