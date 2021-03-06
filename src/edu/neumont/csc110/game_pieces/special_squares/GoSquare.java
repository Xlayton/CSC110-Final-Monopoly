package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class GoSquare extends SpecialSquare {
	public GoSquare() {
		super("Go");
	}

	@Override
	public String applyEffect(Player toApply) {
		return "";
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(makeRow(SEPARATOR, false));
		result.append(makeRow(""));
		result.append(makeRow(""));
		result.append(makeRow("Collect $200"));
		result.append(makeRow("As soon as you pass Go"));
		result.append(makeRow(""));
		result.append(makeRow(""));
		result.append(makeRow(""));
		result.append(makeRow("GO"));
		result.append(makeRow(""));
		result.append(makeRow(""));
		result.append(makeRow(SEPARATOR));

		return result.toString();
	}
}
