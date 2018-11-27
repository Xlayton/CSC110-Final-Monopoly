package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class TaxSquare extends SpecialSquare {
	private final int taxAmount;
	
	protected TaxSquare(String name, int taxAmount) {
		super(name);
		this.taxAmount = taxAmount;
	}

	@Override
	protected void applyEffect(Player player) {
		player.subtractBalance(taxAmount);
	}
}
