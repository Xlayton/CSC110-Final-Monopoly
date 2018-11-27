package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;
import edu.neumont.csc110.game_pieces_abstract.Square;

public class RelativeTravelCard extends Card {
	private final MonopolyBoard board;
	private final String location;

	public RelativeTravelCard(String flavorText, boolean isChance, MonopolyBoard board,
			String location) {
		super(flavorText, isChance);
		this.board = board;
		this.location = location;
	}

	@Override
	public void applyEffect(Player toApply) {
		switch (location) {
		case "spaces":
			board.movePiece(toApply, -3);
			break;
		case "railroad":
			moveToRailRoad(toApply);
			break;
		case "utility":
			moveToUtility(toApply);
      break;
		}
	}

	private void moveToRailRoad(Player toApply) {
		Square pieceAt = board.getPieceLocation(toApply.getPiece());
		Square closestRail = null;
		int[] moveRailRoad = { 5, 15, 25, 35 };
		int workingnumber = 0;
		int lowestNumber = Integer.MAX_VALUE;
		int getPieceLocation = board.getLocationIndex(pieceAt);

		for (int i = 0; i < moveRailRoad.length; i++) {
			if(moveRailRoad[i] < getPieceLocation) {
				continue;
			}
			workingnumber = moveRailRoad[i] - getPieceLocation;
			if (workingnumber < lowestNumber) {
				lowestNumber = workingnumber;
				closestRail = board.squareAtIndex(moveRailRoad[i]);
			}
		}
		board.moveTo(toApply, closestRail, true);
	}

	private void moveToUtility(Player toApply) {
		Square pieceAt = board.getPieceLocation(toApply.getPiece());
		Square closestUtil = null;
		int[] allUtil = { 12, 28 };
		int workingnumber = 0;
		int lowestNumber = Integer.MAX_VALUE;
		int getPieceLocation = board.getLocationIndex(pieceAt);

		for (int i = 0; i < allUtil.length; i++) {
			if(allUtil[i] < getPieceLocation) {
				continue;
			}
			workingnumber = allUtil[i] - getPieceLocation;
			if (workingnumber < lowestNumber) {
				lowestNumber = workingnumber;
				closestUtil = board.squareAtIndex(allUtil[i]);
			}
		}
		board.moveTo(toApply, closestUtil, true);
	}
}
