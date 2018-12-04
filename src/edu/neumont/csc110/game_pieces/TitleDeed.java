package edu.neumont.csc110.game_pieces;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;
import edu.neumont.csc110.game_pieces_abstract.Square;
import interfaces.MenuOption;

public class TitleDeed extends OwnableSquare {
	public enum Color implements MenuOption {
		BROWN,
		CYAN,
		MAGENTA,
		ORANGE,
		RED,
		YELLOW,
		GREEN,
		BLUE;

		@Override
		public String getDesc() {
			return String.valueOf(this);
		}
	}

	private final Color color;
	private final int[] rents;
	private final int buildingCost;
	private final int monopolizedCount;

	private int buildingCount;

	public TitleDeed(String name, Color color, int price, int baseRent, int oneHouse, int twoHouse,
			int threeHouse, int fourHouse, int hotel, int buildingCost) {
		this(name, color, price, baseRent, oneHouse, twoHouse, threeHouse, fourHouse, hotel,
				buildingCost, false);
	}

	public TitleDeed(String name, Color color, int price, int baseRent, int oneHouse, int twoHouse,
			int threeHouse, int fourHouse, int hotel, int buildingCost, boolean twoMonopoly) {
		super(name, price);
		this.color = color;
		this.buildingCost = buildingCost;
		monopolizedCount = twoMonopoly ? 2 : 3;

		rents = new int[6];
		rents[0] = baseRent;
		rents[1] = oneHouse;
		rents[2] = twoHouse;
		rents[3] = threeHouse;
		rents[4] = fourHouse;
		rents[5] = hotel;

		buildingCount = 0;
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

	@Override
	public int getRent(Player player) {
		if (player == null) {
			if (buildingCount == 0 && (isOwned() && owner.isMonopolized(this))) {
				return rents[0] * 2;
			} else {
				return rents[buildingCount];
			}
		}

		if (isMortgaged || (isOwned() && player.equals(owner))) {
			return 0;
		} else {
			if (buildingCount == 0 && (isOwned() && owner.isMonopolized(this))) {
				return rents[0] * 2;
			} else {
				return rents[buildingCount];
			}
		}
	}

	public int getBuildingCost() {
		return buildingCost;
	}

	public int getBuildingCount() {
		return buildingCount;
	}

	public Color getColor() {
		return color;
	}

	public int getMonopolizedCount() {
		return monopolizedCount;
	}

	@Override
	public int compareTo(Square anotherSquare) {
		if (!(anotherSquare instanceof OwnableSquare)) {
			return 1;
		} else if (!(anotherSquare instanceof TitleDeed)) {
			return 1;
		} else {
			if (color == (((TitleDeed) anotherSquare).color)) {
				return super.compareTo(anotherSquare);
			} else {
				return color.compareTo((((TitleDeed) anotherSquare).color));
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(makeRow(SEPARATOR, false));
		result.append(makeRow(""));
		result.append(makeRow(color.toString()));
		result.append(makeRow(SEPARATOR));
		result.append(makeRow(""));
		result.append(makeRow(super.getName()));
		result.append(makeRow(""));
		result.append(makeRow(""));
		result.append(makeRow(""));
		result.append(makeRow(""));
		result.append(makeRow(isOwned() ? owner.getName() + ": $" + getRent(null) : "$" + price));
		result.append(makeRow(SEPARATOR));

		return result.toString();
	}
}
