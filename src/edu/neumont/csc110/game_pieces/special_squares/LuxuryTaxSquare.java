package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class LuxuryTaxSquare extends SpecialSquare {
	private final int taxAmount;
/**
 * 
 * @param name - name of player
 * @param taxAmount - how much player is going to pay
 */
	public LuxuryTaxSquare(String name, int taxAmount) {
		super(name);
		this.taxAmount = taxAmount;
	}

	@Override
	public String applyEffect(Player player) {
		player.subtractBalance(taxAmount);
		return "Taxed " + player.getName() + " by " + taxAmount;
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
		result.append(makeRow("$" + taxAmount));
		result.append(makeRow(SEPARATOR));

		return result.toString();
	}
}
