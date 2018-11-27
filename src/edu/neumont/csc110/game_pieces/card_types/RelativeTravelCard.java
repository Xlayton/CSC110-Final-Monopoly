package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;
import edu.neumont.csc110.game_pieces_abstract.Square;

public class RelativeTravelCard extends Card {

	private String location;
	private final MonopolyBoard board;

	public RelativeTravelCard(String name, String flavorText, MonopolyBoard board, int spaces, Square location, int locaiton) {
	public RelativeTravelCard(String name, String flavorText) {
		super(name, flavorText);
		this.board = board;
	}

	@Override
	public void applyEffect(Player toApply) {
		if (location == "Go back 3 spaces") {
	        Player toApplyprevpos = toApply;
	        board.movePiece(toApply, -3);
	        Player toApplyprevpos1 = toApply;
		}
	}
	public void moveToRailRoad(Player toApply) {
		int[] moveRailRoad= {5, 15, 25, 35};
		int workingnumber = 0;
		int lowestNumber = Integer.MAX_VALUE;
		int nearestRail = 0;
		int getPieceLocation = 0;
		
		for (int i = 0; i<moveRailRoad.length; i++) {
			getPieceLocation = board.getPieceLocation(toApply.getPiece());
		}
	}
}
