package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.Player;

public abstract class SpecialSquare extends Square {
	protected SpecialSquare(String name) {
		super(name);
	}

	public abstract String applyEffect(Player player);

	@Override
	public final String landedOn(Player player) {
		return this.applyEffect(player);
	}
}
