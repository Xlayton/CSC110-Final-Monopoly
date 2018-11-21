package edu.neumont.csc110.equipment.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.equipment_abstract.SpecialSquare;

public class TaxSquare extends SpecialSquare {
	private final int TAX_AMOUNT;
	
	protected TaxSquare(String name, int taxAmount) {
		super(name);
		TAX_AMOUNT = taxAmount;
	}

	@Override
	protected void applyEffect(Player player) {
		
	}
}
