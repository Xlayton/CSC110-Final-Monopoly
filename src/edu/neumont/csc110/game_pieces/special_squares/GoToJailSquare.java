package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class GoToJailSquare extends SpecialSquare {
	private final int jailLocation;
	
	protected GoToJailSquare(String name) {
		super(name);
		jailLocation = 10;
	}

	@Override
	protected void applyEffect(Player toApply) {
		toApply.getPiece().setLocation(jailLocation);
	}
}
