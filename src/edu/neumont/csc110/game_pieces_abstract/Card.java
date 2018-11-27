package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.Player;

public abstract class Card {
	private final String flavorText;
	private final boolean isChance;
	
	protected Card(String flavorText, boolean isChance) {
		this.flavorText = flavorText;
		this.isChance = isChance;
	}
	
	public boolean isChance() {
		return isChance;
	}
	
	public String getFlavorText() {
		return flavorText;
	}
	
	public abstract void applyEffect(Player toApply);
	
	@Override
	public String toString() {
		return (isChance ? "Chance" : "Community Chest") + ": " + this.getFlavorText();
	}
}
