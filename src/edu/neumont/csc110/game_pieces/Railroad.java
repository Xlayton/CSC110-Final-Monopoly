package edu.neumont.csc110.game_pieces;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Square;

public class Railroad extends Square {

	private Player owner;
	
	protected Railroad(String name) {
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
			player.subtractBalance(25 * (int) Math.pow(2, owner.getRailroadCount() - 1));
		}
	}

}
