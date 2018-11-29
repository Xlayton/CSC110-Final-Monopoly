package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class ChanceSquare extends SpecialSquare {
	private final MonopolyBoard board;

	public ChanceSquare(MonopolyBoard board) {
		super("Chance");
		this.board = board;
	}

	@Override
	public void applyEffect(Player toApply) {
		board.drawCard(true);
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(makeRow(SEPARATOR, false));
		result.append(makeRow(""));
		result.append(makeRow("CHANCE"));
		result.append(makeRow(SEPARATOR));
		result.append(makeRow(""));
		result.append(makeRow(" ___ "));
		result.append(makeRow("/   \\"));
		result.append(makeRow("`   |"));
		result.append(makeRow("   / "));
		result.append(makeRow("  |  "));
		result.append(makeRow("  .  "));
		result.append(makeRow(SEPARATOR));

		return result.toString();
	}
}
