package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class RelativePayCard extends Card {

	private final int houseCost, hotelCost;
	
	public RelativePayCard(String name, String flavorText, int houseCost, int hotelCost) {
		super(name, flavorText);
		this.houseCost = houseCost;
		this.hotelCost = hotelCost;
	}

	@Override
	public void applyEffect(Player toApply) throws IllegalArgumentException {
		toApply.subtractBalance((toApply.getHouseCount() * houseCost) + (toApply.getHotelCount() * hotelCost));
	}
	
}
