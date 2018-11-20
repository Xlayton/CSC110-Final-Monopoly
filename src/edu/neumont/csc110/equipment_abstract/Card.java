package edu.neumont.csc110.equipment_abstract;

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
	
	public abstract void getEffect();
}
