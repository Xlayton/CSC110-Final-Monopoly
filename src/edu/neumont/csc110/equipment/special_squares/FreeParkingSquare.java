package edu.neumont.csc110.equipment.special_squares;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.equipment_abstract.SpecialSquare;

public class FreeParkingSquare extends SpecialSquare {
	protected FreeParkingSquare(String name) {
		super(name);
	}

	@Override
	protected void applyEffect(Player player) {
		return;
	}
}
