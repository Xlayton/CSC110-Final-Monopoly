package edu.neumont.csc110.equipment_abstract;

public abstract class Square {
	protected final String name;
	
	protected Square(String name) {
		this.name = name;
	}
	
	public abstract void landedOn();
	
	public String getName() {
		return name;
	}
}
