package edu.neumont.csc110.game_pieces;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Square;

public class Property extends Square {
	public enum Color {
		BROWN,
		CYAN,
		MAGENTA,
		ORANGE,
		RED,
		YELLLOW,
		GREEN,
		BLUE;
		
		@Override
		public String toString() {
			StringBuilder result = new StringBuilder(String.valueOf(this).toLowerCase());
			result.replace(0, 1, Character.toUpperCase(result.charAt(0)) + "");
			return result.toString();
		}
	}

	private final Color color;
	private final int[] rents;
	private final int price, buildingCost;

	private Player owner;
	private int buildingCount;
	private boolean isMortgaged, monopolized;

	public Property(String name, Color color, int price, int baseRent, int oneHouse, int twoHouse,
			int threeHouse, int fourHouse, int hotel, int buildingCost) {
		super(name);
		this.color = color;
		this.price = price;
		this.buildingCost = buildingCost;

		rents = new int[6];
		rents[0] = baseRent;
		rents[1] = oneHouse;
		rents[2] = twoHouse;
		rents[3] = threeHouse;
		rents[4] = fourHouse;
		rents[5] = hotel;

		buildingCount = 0;
		isMortgaged = false;
		owner = null;
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

	public int mortgage() {
		isMortgaged = true;
		return price / 2;
	}

	public int getUnmortgagePrice() {
		return (price / 2) + ((price / 2) / 10);
	}

	public void unmortgage() {
		isMortgaged = false;
	}

	public void setMonopolized(boolean monopolized) {
		this.monopolized = monopolized;
	}

	public boolean isMortgaged() {
		return isMortgaged;
	}

	public int getRent() {
		if (isMortgaged()) {
			return 0;
		} else {
			if (buildingCount == 0 && monopolized) {
				return rents[0] * 2;
			} else {
				return rents[buildingCount];
			}
		}
	}

	public int getBuildingCost() {
		return buildingCost;
	}

	public void buyBuilding() throws IllegalArgumentException {
		if (buildingCount == 5) {
			throw new IllegalArgumentException("Cannot improve past a hotel");
		}
		if (buildingCount == 4) {
			Hotel.takeBuilding();
		} else {
			House.takeBuilding();
		}
		buildingCount++;
	}

	public int sellBuilding() throws IllegalArgumentException {
		if (buildingCount == 0) {
			throw new IllegalArgumentException("No buildings to sell");
		}

		if (buildingCount == 5) {
			Hotel.returnBuilding();
		} else {
			House.returnBuilding();
		}

		buildingCount--;

		return buildingCost / 2;
	}
	
	public Color getColor() {
		return color;
	}

	@Override
	public void landedOn(Player player) {
		if (isOwned() && !player.equals(owner)) {
			player.subtractBalance(getRent());
		} else if (!isOwned()) {
			return;
		}
	}
}
