package edu.neumont.csc110.game_pieces;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;

public class Utility extends OwnableSquare {
	public Utility(String name) {
		super(name, 150);
	}

	@Override
	public void setOwnership(Player owner) {
		if (isOwned()) {
			owner.removeUtil();
		}
		this.owner = owner;
		owner.addUtil();
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
		if (getName().equals("Water Works")) {
			result.append(makeRow("    =()=  "));
			result.append(makeRow(",/'\\_||_  "));
			result.append(makeRow("( (___  `."));
			result.append(makeRow("`\\./  `=='"));
			result.append(makeRow("       |||"));
			result.append(makeRow("       |||"));
		} else {
			result.append(makeRow(" _____ "));
			result.append(makeRow("|__ __|"));
			result.append(makeRow("  |=|  "));
			result.append(makeRow("  / \\  "));
			result.append(makeRow(" (   ) "));
			result.append(makeRow("  `-'  "));
		}
		result.append(makeRow(isOwned()
				? owner.getName() + ": " + (owner.getUtilCount() == 1 ? 4 : 10) + " times dice roll"
				: "$" + price));
		result.append(makeRow(SEPARATOR));

		return result.toString();
	}
}
