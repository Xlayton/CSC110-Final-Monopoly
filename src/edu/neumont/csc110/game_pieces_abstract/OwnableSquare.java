package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.InsufficientFundsException;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces.Railroad;
import edu.neumont.csc110.game_pieces.TitleDeed;
import edu.neumont.csc110.game_pieces.Utility;

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
	 * 
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
	 * 
	 * @return -
	 */
	public boolean isOwned() {
		return getOwner() != null;
	}

	/**
	 * property that is mortgaged
	 * 
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
	 * 
	 * @return - making the original price of the property
	 */
	public int getUnmortgagePrice() {
		return (price / 2) + ((price / 2) / 10);
	}

	public OwnableSquare copySquare() {
		return this;
	}

	@Override
	public String landedOn(Player player) throws InsufficientFundsException {
		if (isOwned() && !player.equals(owner) && !isMortgaged()) {
			player.subtractBalance(owner, getRent(player));
			owner.addBalance(getRent(player));
			return player.getName() + " paid $" + getRent(player) + " to " + owner.getName();
		} else if (isOwned() && player.equals(owner)) {
			return "You own this square.";
		} else if (isMortgaged) {
			return "This square is mortgaged";
		} else {
			return "";
		}
	}

	@Override
	public int compareTo(Square anotherSquare) {
		if (!(anotherSquare instanceof OwnableSquare)) {
			return 1;
		} else if (anotherSquare instanceof TitleDeed) {
			if (!(this instanceof TitleDeed)) {
				return -1;
			} else {
				return getName().compareTo(anotherSquare.getName());
			}
		} else if (anotherSquare instanceof Railroad) {
			if (this instanceof TitleDeed) {
				return 1;
			} else if (this instanceof Utility) {
				return -1;
			} else {
				return getName().compareTo(anotherSquare.getName());
			}
		} else {
			if (this instanceof TitleDeed || this instanceof Railroad) {
				return 1;
			} else {
				return getName().compareTo(anotherSquare.getName());
			}
		}
	}
}
