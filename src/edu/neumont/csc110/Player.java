package edu.neumont.csc110;

import java.util.ArrayList;
import edu.neumont.csc110.game_pieces.Piece;
import edu.neumont.csc110.game_pieces.Property;
import edu.neumont.csc110.game_pieces_abstract.Building;

public class Player {
	private final String name;
	private final Piece piece;
	private final ArrayList<Property> properties;

	int balance;

	public Player(String name, Piece piece) {
		this(name, piece, 1500);
	}

	public Player(String name, Piece piece, int initBalance) {
		this.name = name;
		this.piece = piece;
		this.balance = initBalance;
		properties = new ArrayList<>();
	}

	public boolean isBankrupt() {
		return false;
	}

	public int roll() {
		return 0;
	}

	public void mortgage(Property toMortgage) {

	}

	public void buyBuilding(Property toBuyOn, Building toBuy, int numberOfBuildings) {

	}

	public void sellBuilding(Property toSellFrom, Building toSell, int numberOfBuildings) {

	}
}
