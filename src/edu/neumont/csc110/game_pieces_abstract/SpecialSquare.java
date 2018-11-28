package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.Player;

public abstract class SpecialSquare extends Square {
	protected SpecialSquare(String name) {
		super(name);
	}

	public abstract void applyEffect(Player player);

	@Override
	public final void landedOn(Player player) {
		this.applyEffect(player);
	}
}
