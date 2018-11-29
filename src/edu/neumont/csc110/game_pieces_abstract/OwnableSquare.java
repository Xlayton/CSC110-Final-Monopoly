package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.Player;

public abstract class OwnableSquare extends Square {
	protected final int price;
	
	protected Player owner;
	protected boolean isMortgaged;
	
	protected OwnableSquare(String name, int buyPrice) {
		super(name);
		price = buyPrice;
		owner = null;
		isMortgaged = false;
	}

	public void setOwnership(Player owner) {
		this.owner = owner;
	}

	public int mortgage() {
		isMortgaged = true;
		return price / 2;
	}

	public void unmortgage() {
		isMortgaged = false;
	}

	public boolean isOwned() {
		return getOwner() != null;
	}

	public boolean isMortgaged() {
		return isMortgaged;
	}

	public Player getOwner() {
		return owner;
	}

	public abstract int getRent(Player player);
	
	public int getPrice() {
		return price;
	}

	public int getUnmortgagePrice() {
		return (price / 2) + ((price / 2) / 10);
	}

	@Override
	public abstract void landedOn(Player player);
}
