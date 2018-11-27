package edu.neumont.csc110;

import java.util.ArrayList;
import java.util.Random;
import edu.neumont.csc110.game_pieces.Piece;
import edu.neumont.csc110.game_pieces.Property;
import edu.neumont.csc110.game_pieces_abstract.Building;

public class Player {
	public static Player player;
	private final String name;
	private final Piece piece;
	private final ArrayList<Property> properties;
  
	private int houseCount;
	private int hotelCount;
	private int balance;
	private int jailBreakCount;
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
		if(amount > balance) {
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

	public void mortgage(Property toMortgage) {

	}

	public void buyBuilding(Property toBuyOn, Building toBuy, int numberOfBuildings) {

	}

	public void sellBuilding(Property toSellFrom, Building toSell, int numberOfBuildings) {

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
}
