package edu.neumont.csc110;

import java.util.ArrayList;
import java.util.Random;
import edu.neumont.csc110.game_pieces.Piece;
import edu.neumont.csc110.game_pieces.TitleDeed;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;

public class Player {
	public static Player player;
	private final String name;
	private final Piece piece;
	private final ArrayList<OwnableSquare> properties;

	private int houseCount, hotelCount, jailBreakCount, railroadCount, utilityCount, balance;
	private boolean isJailed;

	public Player(String name, Piece piece) {
		this(name, piece, 1500);
	}

	public Player(String name, Piece piece, int initBalance) {
		this.name = name;
		this.piece = piece;
		this.balance = initBalance;
		this.jailBreakCount = 0;
		properties = new ArrayList<>();
	}

	public void subtractBalance(int amount) {
		if (amount > balance) {
			throw new IllegalArgumentException("Insufficient funds");
		}
		balance -= amount;
	}

	public void addBalance(int amount) {
		balance += amount;
	}

	public boolean isBankrupt() {
		return false;
	}

	public boolean hasJailBreak() {
		return jailBreakCount > 0;
	}

	public boolean jailBreak() {
		if (hasJailBreak()) {
			jailBreakCount--;
			return true;
		}
		return false;
	}

	public void giveJailBreak() {
		jailBreakCount++;
	}

	public int roll() {
		return (new Random().nextInt(6) + 1) + (new Random().nextInt(6) + 1);
	}

	public void mortgage(OwnableSquare toMortgage) {
		this.addBalance(toMortgage.mortgage());
	}

	public void buyBuilding(TitleDeed toBuyOn, int numberOfBuildings) {
		for (int i = 0; i < numberOfBuildings; i++) {
			try {
				this.subtractBalance(toBuyOn.getBuildingCost());
				toBuyOn.buyBuilding();
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
				break;
			}
		}
	}

	public void sellBuilding(TitleDeed toSellFrom, int numberOfBuildings) {
		for (int i = 0; i < numberOfBuildings; i++) {
			try {
				this.addBalance(toSellFrom.sellBuilding());
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
				break;
			}
		}
	}

	public int getHouseCount() {
		return houseCount;
	}

	public int getHotelCount() {
		return hotelCount;
	}

	public Piece getPiece() {
		return piece;
	}

	public boolean isJailed() {
		return isJailed;
	}

	public void setJailed(boolean isJailed) {
		this.isJailed = isJailed;
	}

	public void getProperties() {
		return;
	}

	public double getRailroadCount() {
		return railroadCount;
	}

	public int getUtilCount() {
		return utilityCount;
	}

	public void removeRailroad() {
		railroadCount--;
	}

	public void addRailroad() {
		railroadCount++;
	}

	public String getName() {
		return name;
	}

	public int getWorth() {
		int worth = balance;

		for (OwnableSquare property : properties) {
			worth += property.getPrice();
			if (property instanceof TitleDeed) {
				worth += (((TitleDeed) property).getBuildingCount()
						* ((TitleDeed) property).getBuildingCost());
			}
		}

		return worth;
	}
}
