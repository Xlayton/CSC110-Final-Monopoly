package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;
import edu.neumont.csc110.game_pieces_abstract.Square;

public class RelativeTravelCard extends Card {

	private String location;

	public RelativeTravelCard(String name, String flavorText, MonopolyBoard board, int spaces, Square location, int locaiton) {
		super(name, flavorText);
	}

	@Override
	public void applyEffect(Player toApply) {
		
		if (location == "Go back 3 spaces") {
	        Player toApplyprevpos = toApply;
	        toApply.getPiece().setLocation(toApply.getPiece().getLocation() - 3);
	        Player toApplyprevpos1 = toApply;
		}
	}
	public void moveToRailRoad(Player toApply) {
		int[] moveRailRoad= {5, 15, 25, 35};
		int workingnumber = 0;
		int lowestNumber = Integer.MAX_VALUE;
		int nearestRail = 0;
		
		for (int i = 0; i<moveRailRoad.length; i++) {
			if (toApply.getPiece().getLocation() >= moveRailRoad[i]) {
				continue;
			}
			workingnumber = moveRailRoad[i] - toApply.getPiece().getLocation();
			if (workingnumber < lowestNumber) {
				lowestNumber = workingnumber;
				nearestRail = moveRailRoad[i];
			}
			
		}
		toApply.getPiece().setLocation(nearestRail);
			
	}

}
