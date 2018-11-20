package edu.neumont.csc110.equipment;

import java.awt.Color;
import java.util.ArrayList;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.equipment_abstract.Building;
import edu.neumont.csc110.equipment_abstract.Square;

public class Property extends Square {

	private final Color color;
	private final int price;
	
	private Player owner;
	private ArrayList<Building> buildings;
	private int rent;
	private boolean isMortgaged;
	
	public Property(String name, Color color, int price) {
		super(name);
		this.color = color;
		this.price = price;
		isMortgaged = false;
		owner = null;
		buildings = new ArrayList<>();
	}
	
	public boolean isOwned() {
		return getOwner() == null;
	}
	
	public Player getOwner() {
		return owner;
	}

	@Override
	public void landedOn() {
		
	}
}
