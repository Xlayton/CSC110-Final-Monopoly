package edu.neumont.csc110.game_pieces;

import java.util.ArrayList;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Building;
import edu.neumont.csc110.game_pieces_abstract.Square;

public class Property extends Square {
	public enum Color {
		BROWN, CYAN, MAGENTA, ORANGE, RED, YELLLOW, GREEN, BLUE;
	}

	private final Color color;
	private final int price;

	private final int[] rents;
	
	private Player owner;
	private ArrayList<Building> buildings;
	private int rent;
	private boolean isMortgaged;
	private boolean isOwned;

	public Property(String name, Color color, int price, int baseRent, int oneHouse, int twoHouse, int threeHouse,
			int fourHouse, int hotel, int buildingCost) {
		super(name);
		this.color = color;
		this.price = price;
		
		rents = new int[6];
		rents[0] = baseRent;
		rents[1] = oneHouse;
		rents[2] = twoHouse;
		rents[3] = threeHouse;
		rents[4] = fourHouse;
		rents[5] = hotel;
		
		isMortgaged = false;
		isOwned = false;
		owner = null;
		buildings = new ArrayList<>();
	}

	public void setOwnership(Player owner) {
		this.owner = owner;
	}

	public boolean isOwned() {
		return getOwner() != null;
	}

	public Player getOwner() {
		return owner;
	}

	@Override
	public void landedOn(Player player) {
		if(isOwned) {
			return; //TODO actually implement paying...
		} else {
			return; //TODO actually implement buying...
		}
	}
}
