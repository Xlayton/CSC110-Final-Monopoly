package edu.neumont.csc110.game_pieces.card_types;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.Card;
import edu.neumont.csc110.game_pieces_abstract.Square;

public class TravelCard extends Card {
	private final MonopolyBoard board;
	private final Square location;
	private final boolean shouldPassGo;
	
	protected TravelCard(String name, String flavorText, MonopolyBoard board, Square location, boolean passGo) {
		super(name, flavorText);
		
		this.board = board;
		this.location = location;
		this.shouldPassGo = passGo;
	}

	@Override
	public void applyEffect(Player toApply) {
		board.moveTo(toApply, location, shouldPassGo);
	}

}
