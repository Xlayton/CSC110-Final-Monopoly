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
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(makeRow(SEPARATOR, false));
		result.append(makeRow(""));
		result.append(makeRow("JUST VISITING"));
		result.append(makeRow(SEPARATOR));
		result.append(makeRow("  ||<(.)>||<(.)>||  "));
		result.append(makeRow(" _||     ||     ||_ "));
		result.append(makeRow("(__D     ||     C__)"));
		result.append(makeRow("(__D     ||     C__)"));
		result.append(makeRow("(__D     ||     C__)"));
		result.append(makeRow("(__D     ||     C__)"));
		result.append(makeRow("IN JAIL"));
		result.append(makeRow(SEPARATOR));

		return result.toString();
	}
}
