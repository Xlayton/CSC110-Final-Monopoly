package edu.neumont.csc110.equipment;

import edu.neumont.csc110.equipment_abstract.Building;

public class House extends Building {
	private static int count = 32;
	
	@Override
	public boolean takeBuilding() {
		if (count == 0) {
			return false;
		}
		count--;
		return true;
	}
	
	@Override
	public void returnBuilding() {
		count++;
	}
}
