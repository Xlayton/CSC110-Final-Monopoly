package edu.neumont.csc110.game_pieces_abstract;

import java.util.ArrayList;
import edu.neumont.csc110.Player;

public abstract class Auction {
	public abstract void startAuction(OwnableSquare toAuction, ArrayList<Player> allPlayers,
			Player toStart);
}
