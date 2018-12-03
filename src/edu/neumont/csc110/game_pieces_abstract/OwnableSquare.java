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
/**
 * 
 * @param owner - person who owns the property
 */
	public void setOwnership(Player owner) {
		this.owner = owner;
	}
/**
 * it is evaluating the mortgage price
 * @return - the price is half
 */
	public int mortgage() {
		isMortgaged = true;
		return price / 2;
	}
/**
 * a property is not mortgaged 
 */
	public void unmortgage() {
		isMortgaged = false;
	}
/**
 * property is not yet owned by player
 * @return - 
 */
	public boolean isOwned() {
		return getOwner() != null;
	}
/**
 * property that is mortgaged
 * @return - show that it is mortgaged
 */
	public boolean isMortgaged() {
		return isMortgaged;
	}
/**
 * 
 * @return - show owner
 */
	public Player getOwner() {
		return owner;
	}
/**
 * 
 * @param player - the players piece
 * @return - get the price of rent from property
 */
	public abstract int getRent(Player player);
	
	public int getPrice() {
		return price;
	}
/**
 * getting the original price of a property
 * @return - making the original price of the property
 */
	public int getUnmortgagePrice() {
		return (price / 2) + ((price / 2) / 10);
	}

	@Override
	public abstract void landedOn(Player player);
}
