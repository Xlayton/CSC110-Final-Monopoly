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
}
