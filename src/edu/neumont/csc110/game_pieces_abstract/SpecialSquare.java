package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.Player;

public abstract class SpecialSquare extends Square {
	/**
	 * 
	 * @param name - name of player
	 */
	protected SpecialSquare(String name) {
		super(name);
	}
/**
 * 
 * @param player - piece of the player
 */
	public abstract void applyEffect(Player player);
/**
 * what square the player will land on
 */
	@Override
	public final void landedOn(Player player) {
		this.applyEffect(player);
	}
}
