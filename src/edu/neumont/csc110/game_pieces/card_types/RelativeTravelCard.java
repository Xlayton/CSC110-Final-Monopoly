package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;
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
		int[] railroadIndices = {board.getLocationIndex("Reading Railroad"),
				board.getLocationIndex("Pennsylvania Railroad"),
				board.getLocationIndex("B. & O. Railroad"), board.getLocationIndex("Short Line")};
		moveTo(toApply, railroadIndices);
	}

	private void moveToUtility(Player toApply) {
		int[] utilIndices =
				{board.getLocationIndex("Electric Company"), board.getLocationIndex("Water Works")};
		moveTo(toApply, utilIndices);
	}

	private void moveTo(Player toApply, int[] possibleLocations) {
		Square pieceAt = board.getPieceLocation(toApply.getPiece());
		OwnableSquare closest = null;
		int getPieceLocation = board.getLocationIndex(pieceAt);

		for (int i = 0; i <= possibleLocations.length; i++) {
			if (i < possibleLocations.length && possibleLocations[i] < getPieceLocation) {
				continue;
			}

			closest = (OwnableSquare) board
					.squareAtIndex(possibleLocations[i < possibleLocations.length ? i : 0]);
			break;
		}

		int utilCount = 0;
		if (closest.isOwned()) {
			closest.getOwner().addRailroad();
			utilCount = closest.getOwner().getUtilCount();
			if (utilCount < 2) {
				closest.getOwner().addUtil();
			}
		}
		board.moveTo(toApply, closest, true);
		if (closest.isOwned()) {
			closest.getOwner().removeRailroad();
			if (closest.getOwner().getUtilCount() > utilCount) {
				closest.getOwner().removeUtil();
			}
		}
	}
}
