package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.Player;

public abstract class Card {
	private final String name, flavorText;
	
	protected Card(String name, String flavorText) {
		this.name = name;
		this.flavorText = flavorText;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFlavorText() {
		return flavorText;
	}
	
	public abstract void applyEffect(Player toApply);
	
	@Override
	public String toString() {
		return this.getName() + ": " + this.getFlavorText();
	}
}
