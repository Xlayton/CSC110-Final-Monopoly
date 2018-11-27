package edu.neumont.csc110.game_pieces;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Square;

public class Utility extends Square {
	private Player owner;
	
	public Utility(String name) {
		super(name);
		owner = null;
	}
	
	public void setOwnership(Player owner) {
		this.owner = owner;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public boolean isOwned() {
		return getOwner() != null;
	}

	@Override
	public void landedOn(Player player) {
		if (owner != null && !player.equals(owner)) {
			player.subtractBalance(player.roll() * (owner.getUtilCount() > 1 ? 10 : 4));
		}
	}
}
