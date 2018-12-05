package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.InsufficientFundsException;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class RelativePayCard extends Card {
	private final int houseCost, hotelCost;

	/**
	 * 
	 * @param flavorText - description of card
	 * @param isChance - the card deck for chance
	 * @param houseCost - cost of rent for a house
	 * @param hotelCost - cost of rent for a hotel
	 */
	public RelativePayCard(String flavorText, boolean isChance, int houseCost, int hotelCost) {
		super(flavorText, isChance);
		this.houseCost = houseCost;
		this.hotelCost = hotelCost;
	}

	@Override
	public void applyEffect(Player toApply) throws InsufficientFundsException {
		toApply.subtractBalance(
				(toApply.getHouseCount() * houseCost) + (toApply.getHotelCount() * hotelCost));
	}
}
