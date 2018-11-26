package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class GoToJailSquare extends SpecialSquare {
	private final int JAIL_LOCATION;

	protected GoToJailSquare(String name) {
		super(name);
		JAIL_LOCATION = 10;
	}

	@Override
	protected void applyEffect(Player toApply) {
		toApply.getPiece().setLocation(JAIL_LOCATION);
	}
}
