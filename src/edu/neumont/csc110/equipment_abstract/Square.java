package edu.neumont.csc110.equipment_abstract;

import edu.neumont.csc110.Player;

public abstract class Square {
	protected final String name;
	
	protected Square(String name) {
		this.name = name;
	}
	
	public abstract void landedOn(Player player);
	
	public String getName() {
		return name;
	}
}
