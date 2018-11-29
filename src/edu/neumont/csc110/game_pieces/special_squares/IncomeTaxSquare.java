package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class IncomeTaxSquare extends SpecialSquare {
	public IncomeTaxSquare(String name) {
		super(name);
	}

	@Override
	public void applyEffect(Player player) {
		if ((player.getWorth() / 10) < 200) {
			player.subtractBalance(player.getWorth() / 10);
		} else {
			player.subtractBalance(200);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(makeRow(SEPARATOR, false));
		result.append(makeRow(""));
		result.append(makeRow(super.getName()));
		result.append(makeRow(SEPARATOR));
		result.append(makeRow("  /\\  "));
		result.append(makeRow(" /  \\ "));
		result.append(makeRow("/ /\\ \\"));
		result.append(makeRow("\\ \\/ /"));
		result.append(makeRow(" \\  / "));
		result.append(makeRow("  \\/  "));
		result.append(makeRow("$" + 200 + " or 10% of your worth"));
		result.append(makeRow(SEPARATOR));

		return result.toString();
	}
}