package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class FreeParkingSquare extends SpecialSquare {
	protected FreeParkingSquare(String name) {
		super("FreeParking");
	}

	@Override
	protected void applyEffect(Player player) {
		return;
	}
}
