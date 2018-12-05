package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.InsufficientFundsException;
import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class ChanceSquare extends SpecialSquare {
	private static int chanceSquareCount = 0;

	private final MonopolyBoard board;
	private final int count;

	/**
	 * 
	 * @param board - board for the game
	 */
	public ChanceSquare(MonopolyBoard board) {
		super("Chance");
		this.board = board;
		count = ++chanceSquareCount;
	}

	@Override
	public String applyEffect(Player toApply) throws InsufficientFundsException {
		Card drawn = board.drawCard(true);
		drawn.applyEffect(toApply);
		return drawn.toString();
	}

	@Override
	public boolean equals(Object anotherChanceSquare) {
		if (!(anotherChanceSquare instanceof ChanceSquare)) {
			return false;
		}
		return this.count == ((ChanceSquare) anotherChanceSquare).count;
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
