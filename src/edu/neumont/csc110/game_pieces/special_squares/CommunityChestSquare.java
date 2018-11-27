package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class CommunityChestSquare extends SpecialSquare {
<<<<<<< HEAD
	private final MonopolyBoard board;
	protected CommunityChestSquare(MonopolyBoard board) {
=======
	public CommunityChestSquare() {
>>>>>>> master
		super("Community Chest");
		this.board = board;
	}

	@Override
	public void applyEffect(Player toApply) {
		board.drawCard(false);
	}
}
