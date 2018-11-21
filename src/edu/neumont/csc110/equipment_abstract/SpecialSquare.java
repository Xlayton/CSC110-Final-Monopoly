package edu.neumont.csc110.equipment_abstract;

import edu.neumont.csc110.Player;

public abstract class SpecialSquare extends Square {
	protected SpecialSquare(String name) {
		super(name);
	}

	protected abstract void applyEffect(Player player);
	
	@Override
	public final void landedOn(Player player) {
		this.applyEffect(player);
	}
}
