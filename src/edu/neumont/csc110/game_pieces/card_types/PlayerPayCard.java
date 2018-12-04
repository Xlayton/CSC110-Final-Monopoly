package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class PlayerPayCard extends Card {
	private final Player[] allPlayers;
	private final boolean payCurrent;

	private int amtChange;
/**
 * 
 * @param flavorText - description of card
 * @param isChance - chance card deck
 * @param payingOthers - gives money to other players
 * @param allPlayers - all the players in the game
 * @param amtChange - amount the players balance change to
 */
	public PlayerPayCard(String flavorText, boolean isChance, boolean payingOthers,
			Player[] allPlayers, int amtChange) {
		super(flavorText, isChance);
		this.payCurrent = !payingOthers;
		this.allPlayers = allPlayers;
		this.amtChange = amtChange;
	}
/**
 * player is paying the other player
 */
	@Override
	public void applyEffect(Player toApply) throws IllegalArgumentException {
		if (payCurrent) {
			payCurrent(toApply);
		} else {
			payOthers(toApply);
		}
	}
/**
 * 
 * @param toApply - is the player piece
 * @throws IllegalArgumentException - stops subtracting the money from balance
 */
	private void payOthers(Player toApply) throws IllegalArgumentException {
		for (Player p : allPlayers) {
			if (p != toApply) {
				toApply.subtractBalance(amtChange);
				p.addBalance(amtChange);
			}
		}
	}
/**
 * 	
 * @param toApply - is the player piece
 * @throws IllegalArgumentException - subtracts balance and adds to other player
 */
	private void payCurrent(Player toApply) throws IllegalArgumentException {
		for (Player p : allPlayers) {
			if (p != toApply) {
				toApply.addBalance(amtChange);
				p.subtractBalance(amtChange);
			}
		}
	}
}
