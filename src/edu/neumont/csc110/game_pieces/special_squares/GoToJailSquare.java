package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class GoToJailSquare extends SpecialSquare {
	private final MonopolyBoard board;

	public GoToJailSquare(MonopolyBoard board) {
		super("Go to Jail");
		this.board = board;
	}

	@Override
	public void applyEffect(Player toApply) {
		board.moveTo(toApply, board.getLocation("Jail"), false);
		toApply.setJailed(true);
	}
}
