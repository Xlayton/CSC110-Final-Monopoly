package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.InsufficientFundsException;
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
 * what square the player will land on
 */
	public abstract String applyEffect(Player player) throws InsufficientFundsException;

	@Override
	public final String landedOn(Player player) throws InsufficientFundsException {
		return this.applyEffect(player);
	}
}
