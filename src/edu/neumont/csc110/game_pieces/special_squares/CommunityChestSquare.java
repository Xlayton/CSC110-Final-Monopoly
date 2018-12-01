package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class CommunityChestSquare extends SpecialSquare {
	private static int chestSquareCount = 0;
	
	private final MonopolyBoard board;
	private final int count;

	public CommunityChestSquare(MonopolyBoard board) {
		super("Community Chest");
		this.board = board;
		count = ++chestSquareCount;
	}

	@Override
	public String applyEffect(Player toApply) {
		Card drawn = board.drawCard(false);
		drawn.applyEffect(toApply);
		return drawn.toString();
	}
	
	@Override
	public boolean equals(Object anotherChestSquare) {
		if (!(anotherChestSquare instanceof CommunityChestSquare)) {
			return false;
		}
		return this.count == ((CommunityChestSquare) anotherChestSquare).count;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(makeRow(SEPARATOR, false));
		result.append(makeRow("COMMUNITY CHEST"));
		result.append(makeRow(SEPARATOR));
		result.append(makeRow("  __________  "));
		result.append(makeRow(" /\\____;;___\\ "));
		result.append(makeRow("| /         / "));
		result.append(makeRow("`.         .  "));
		result.append(makeRow(" |\\         \\ "));
		result.append(makeRow(" | |---------|"));
		result.append(makeRow(" \\ |         |"));
		result.append(makeRow("  \\|_________|"));
		result.append(makeRow(SEPARATOR));

		return result.toString();
	}
}
