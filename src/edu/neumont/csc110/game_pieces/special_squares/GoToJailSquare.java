package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class GoToJailSquare extends SpecialSquare {
	private final int JAIL_LOCATION;
	private final MonopolyBoard board;

	protected GoToJailSquare(MonopolyBoard board, String name) {
		super(name);
		JAIL_LOCATION = 10;
		this.board = board;
	}

	@Override
	protected void applyEffect(Player toApply) {
		board.moveTo(toApply, board.getLocation("Jail"), false);
		toApply.setJailed(true);
	}
}
