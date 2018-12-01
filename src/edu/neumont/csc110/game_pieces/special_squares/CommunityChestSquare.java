package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class CommunityChestSquare extends SpecialSquare {
	private final MonopolyBoard board;

	public CommunityChestSquare(MonopolyBoard board) {
		super("Community Chest");
		this.board = board;
	}

	@Override
	public String applyEffect(Player toApply) {
		Card drawn = board.drawCard(false);
		drawn.applyEffect(toApply);
		return drawn.getFlavorText();
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
