package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class PlayerPayCard extends Card {
	private final Player[] allPlayers;
	private final boolean payCurrent;

	private int amtChange;

	public PlayerPayCard(String flavorText, boolean isChance, boolean payingOthers,
			Player[] allPlayers, int amtChange) {
		super(flavorText, isChance);
		this.payCurrent = !payingOthers;
		this.allPlayers = allPlayers;
		this.amtChange = amtChange;
	}

	@Override
	public void applyEffect(Player toApply) {
		if (payCurrent) {
			payCurrent(toApply);
		} else {
			payOthers(toApply);
		}
	}

	private void payOthers(Player toApply) throws IllegalArgumentException {
		for (Player p : allPlayers) {
			if (p != toApply) {
				toApply.subtractBalance(amtChange);
				p.addBalance(amtChange);
			}
		}
	}

	private void payCurrent(Player toApply) throws IllegalArgumentException {
		for (Player p : allPlayers) {
			if (p != toApply) {
				toApply.addBalance(amtChange);
				p.subtractBalance(amtChange);
			}
		}
	}
}
