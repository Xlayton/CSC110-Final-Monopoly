package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;
import edu.neumont.csc110.game_pieces_abstract.Square;

public class RelativeTravelCard extends Card {
	private final Square location;
	private final MonopolyBoard board;

	public RelativeTravelCard(String flavorText, boolean isChance, MonopolyBoard board, int spaces,
			Square location) {
		super(flavorText, isChance);
		this.board = board;
		this.location = location;
	}

	@Override
	public void applyEffect(Player toApply) {
		if (location == null) {
			board.movePiece(toApply, -3);
		}
	}

	public void moveToRailRoad(Player toApply) {
		Square pieceAt = board.getPieceLocation(toApply.getPiece());
		Square closestRail = null;
		int[] moveRailRoad = {5, 15, 25, 35};
		int workingnumber = 0;
		int lowestNumber = Integer.MAX_VALUE;
		int getPieceLocation = board.getLocationIndex(pieceAt);
		

		for (int i = 0; i < moveRailRoad.length; i++) {
			workingnumber = moveRailRoad[i] - getPieceLocation;
			if(workingnumber < lowestNumber) {
				lowestNumber = workingnumber;
				closestRail = board.squareAtIndex(moveRailRoad[i]);
			}
		}
		board.moveTo(toApply, closestRail, true);
	}
}
