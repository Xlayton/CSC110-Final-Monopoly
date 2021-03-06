package edu.neumont.csc110;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import edu.neumont.csc110.game_pieces.Piece;
import edu.neumont.csc110.game_pieces.TitleDeed;
import edu.neumont.csc110.game_pieces.TitleDeed.Color;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;

public class Player implements Iterable<OwnableSquare> {
	private final String name;
	private final Piece piece;
	private final ArrayList<OwnableSquare> properties;

	private int houseCount, hotelCount, jailBreakCount, railroadCount, utilityCount, balance,
			escapeAttempts, falseRollOne, falseRollTwo;
	private boolean isJailed, isAuctioning;
	private boolean falseRoll;

	public Player(String name, Piece piece) {
		this(name, piece, 1500);
	}

	public Player(String name, Piece piece, int initBalance) {
		this.name = name;
		this.piece = piece;
		this.balance = initBalance;
		this.jailBreakCount = 0;
		this.escapeAttempts = 0;
		properties = new ArrayList<>();
	}

	public void subtractBalance(int amount) throws InsufficientFundsException {
		subtractBalance(null, amount);
	}

	public void subtractBalance(Player payTo, int amount) throws InsufficientFundsException {
		if (amount > balance) {
			throw new InsufficientFundsException("Insufficient funds", payTo, amount,
					Math.abs(balance - amount));
		}
		balance -= amount;
	}

	public void addBalance(int amount) {
		balance += amount;
	}

	public ArrayList<OwnableSquare> copyProperties() {
		ArrayList<OwnableSquare> results = new ArrayList<>();
		for (OwnableSquare property : properties) {
			results.add(property);
		}
		return results;
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

	public void removeRailroad() {
		railroadCount--;
	}

	public void addRailroad() {
		railroadCount++;
	}

	public void removeUtil() {
		utilityCount--;
	}

	public void addUtil() {
		utilityCount++;
	}

	public void addEscapeAttempt() {
		escapeAttempts++;
	}

	public void resetEscapeAttempts() {
		this.escapeAttempts = 0;
	}

	public void setJailed(boolean isJailed) {
		this.isJailed = isJailed;
	}

	public void giveJailBreak() {
		jailBreakCount++;
	}

	public boolean jailBreak() {
		if (hasJailBreak()) {
			jailBreakCount--;
			isJailed = false;
			return true;
		}
		return false;
	}

	public void setFalseRoll(boolean falseRoll) {
		this.falseRoll = falseRoll;
	}

	public void setFalseRoll(int i, int j) {
		falseRollOne = i;
		falseRollTwo = j;
		falseRoll = true;
	}

	public int[] roll() {
		if (!falseRoll) {
			return new int[] {(new Random().nextInt(6) + 1), (new Random().nextInt(6) + 1)};
		} else if (falseRollOne > 0 && falseRollTwo > 0){
			return new int[] {falseRollOne, falseRollTwo};
		} else {
			int roll = new Random().nextInt(6) + 1;
			return new int[] {roll, roll};
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

	public boolean hasJailBreak() {
		return jailBreakCount > 0;
	}

	public boolean isJailed() {
		return isJailed;
	}

	public boolean isBankrupt() {
		return false;
	}

	public boolean isMonopolized(TitleDeed square) {
		return isMonopolized(square.getColor());
	}

	public boolean isMonopolized(Color color) {
		int monopolyCounter = 0;
		for (OwnableSquare property : properties) {
			if (property instanceof TitleDeed) {
				TitleDeed deed = (TitleDeed) property;
				if (deed.getColor().equals(color)) {
					monopolyCounter++;
					if (monopolyCounter == deed.getMonopolizedCount()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean hasMonopoly() {
		for (Color c : Color.values()) {
			if (isMonopolized(c)) {
				return true;
			}
		}
		return false;
	}

	public void addProperties(OwnableSquare... newDeeds) {
		for (OwnableSquare deed : newDeeds) {
			properties.add(deed);
		}
	}

	public void removeProperties(OwnableSquare... oldDeeds) {
		for (OwnableSquare deed : oldDeeds) {
			properties.remove(deed);
		}
	}

	public double getRailroadCount() {
		return railroadCount;
	}

	public int getUtilCount() {
		return utilityCount;
	}

	public int getBalance() {
		return balance;
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

	public OwnableSquare[] getProperties() {
		properties.sort(null);
		return properties.toArray(new OwnableSquare[0]);
	}

	public String getName() {
		return name;
	}

	public int getEscapeAttempts() {
		return escapeAttempts;
	}

	public boolean isAuctioning() {
		return isAuctioning;
	}

	@Override
	public boolean equals(Object anotherPlayer) {
		return name.equals(((Player) anotherPlayer).name);
	}

	@Override
	public String toString() {
		return name + ", $" + balance;
	}

	@Override
	public Iterator<OwnableSquare> iterator() {
		return properties.iterator();
	}
}
