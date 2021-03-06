package edu.neumont.csc110.game_pieces;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;

public class Railroad extends OwnableSquare {
	/**
	 * 
	 * @param name - name of players
	 */
	public Railroad(String name) {
		super(name, 200);
	}

	/**
	 * @param owner - the person who owns the property
	 */
	@Override
	public void setOwnership(Player owner) {
		if (isOwned()) {
			owner.removeRailroad();
		}
		this.owner = owner;
		owner.addRailroad();
	}

	@Override
	public int getRent(Player player) {
		if (player == null) {
			return 25 * (int) Math.pow(2, owner.getRailroadCount() - 1);
		}
		if (isMortgaged || !isOwned() || (isOwned() && player.equals(owner))) {
			return 0;
		} else {
			return 25 * (int) Math.pow(2, owner.getRailroadCount() - 1);
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(makeRow(SEPARATOR, false));
		result.append(makeRow(""));
		result.append(makeRow(super.getName()));
		result.append(makeRow(SEPARATOR));
		result.append(makeRow("____       "));
		result.append(makeRow("|DD|____T_ "));
		result.append(makeRow("|_ |_____|<"));
		result.append(makeRow("  @-@-@-oo\\"));
		result.append(makeRow(""));
		result.append(makeRow(""));
		result.append(makeRow(isOwned() ? owner.getName() + ": $" + getRent(null) : "$" + price));
		result.append(makeRow(SEPARATOR));

		return result.toString();
	}
}
