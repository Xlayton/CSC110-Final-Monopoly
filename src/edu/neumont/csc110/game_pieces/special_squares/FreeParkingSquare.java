package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class FreeParkingSquare extends SpecialSquare {
	public FreeParkingSquare() {
		super("Free Parking");
	}

	@Override
	public void applyEffect(Player toApply) {
		return;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(makeRow(SEPARATOR, false));
		result.append(makeRow(""));
		result.append(makeRow("FREE PARKING"));
		result.append(makeRow(SEPARATOR));
		result.append(makeRow(""));
		result.append(makeRow(""));
		result.append(makeRow("  ______     "));
		result.append(makeRow(" /|_||_\\`.__ "));
		result.append(makeRow("(   _    _ _\\"));
		result.append(makeRow("=`-(_)--(_)-'"));
		result.append(makeRow(""));
		result.append(makeRow(SEPARATOR));

		return result.toString();
	}
}
