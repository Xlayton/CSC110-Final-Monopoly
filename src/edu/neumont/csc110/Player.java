package edu.neumont.csc110;

import java.util.ArrayList;
import java.util.Random;
import edu.neumont.csc110.game_pieces.Piece;
import edu.neumont.csc110.game_pieces.Property;
import edu.neumont.csc110.game_pieces_abstract.Building;

public class Player {
	private final String name;
	private final Piece piece;
	private final ArrayList<Property> properties;
	private int houseCount;
	private int hotelCount;
	
	private int balance;

	public Player(String name, Piece piece) {
		this(name, piece, 1500);
	}

	public Player(String name, Piece piece, int initBalance) {
		this.name = name;
		this.piece = piece;
		this.balance = initBalance;
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
}
