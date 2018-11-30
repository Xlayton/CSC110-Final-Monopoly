package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.Player;

public abstract class Trade {
	
	public OwnableSquare[] getOwnedLocations(Player toGet) {
		return toGet.getTitleDeeds();
	}
	
	public abstract void startTrade();
	
}