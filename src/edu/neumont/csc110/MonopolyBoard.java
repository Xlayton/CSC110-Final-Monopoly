package edu.neumont.csc110;

import java.util.ArrayList;
import edu.neumont.csc110.game_pieces.ChanceCardList;
import edu.neumont.csc110.game_pieces.CommunityChestCardList;
import edu.neumont.csc110.game_pieces.Piece;
import edu.neumont.csc110.game_pieces_abstract.Card;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;
import edu.neumont.csc110.game_pieces_abstract.Square;

/**
 * A monopoly board
 * 
 * @author Ryder James
 * @author Clayton Schrumpf
 * @author Maya Shackleford
 */
public class MonopolyBoard {
	private final BoardLocation[] locations;
	private final Deck chanceDeck, communityChestDeck;
	private final Player[] players;

	/**
	 * Constructs and initializes a new Monopoly board with a maximum of 8 players
	 * 
	 * @param players - the players of the game
	 */
	public MonopolyBoard(Player[] players) {
		this.players = players;
		locations = new BoardLocation[40];
		for (int i = 0; i < locations.length; i++) {
			locations[i] = new BoardLocation(Square.getSquares(this)[i]);
		}
		for (Player p : players) {
			locations[0].putPiece(p.getPiece());
		}
		(chanceDeck = new Deck(ChanceCardList.getChanceCards(this, players))).shuffle();;
		(communityChestDeck =
				new Deck(CommunityChestCardList.getCommunityChestCards(this, players))).shuffle();;
	}

	/**
	 * Move a specified player's piece a number of spaces around the board
	 * 
	 * @param player - the player who owns the piece to move
	 * @param distance - how far to move it
	 * 
	 * @return all of the effects that applied to the player
	 */
	public ArrayList<String> movePiece(Player player, int distance) {
		int currentLocation = getLocationIndex(getPieceLocation(player.getPiece()));
		int targetLocation = currentLocation + distance;
		if (targetLocation >= locations.length) {
			targetLocation -= locations.length;
		}
		return moveTo(player, locations[targetLocation].getSquare(), true);
	}

	/**
	 * Moves a player's piece to the next instance of a certain location on the board
	 * 
	 * @param player - the player who owns the piece to move
	 * @param location - the location to move the piece to
	 * @param shouldPassGo - whether the player should collect $200 if their piece passes go
	 * 
	 * @return all of the effects that applied to the player
	 */
	public ArrayList<String> moveTo(Player player, Square location, boolean shouldPassGo) {
		ArrayList<String> effects = new ArrayList<>();
		int currentIndex = -1;

		for (int i = 0; i < locations.length; i++) {
			if (locations[i].hasPiece(player.getPiece())) {
				locations[i].takePiece(player.getPiece());
				currentIndex = i;
				break;
			}
		}

		if (currentIndex < 0) {
			return effects;
		}

		for (int i = 0; i < locations.length; i++) {
			if (locations[i].getSquare().equals(location)) {
				locations[i].putPiece(player.getPiece());
				effects.add(player.getName() + " landed on " + location.getName());
				effects.add(locations[i].getSquare().landedOn(player));
				if (i < currentIndex && shouldPassGo) {
					effects.add(player.getName() + " passed or landed on go and gains $200");
					player.addBalance(200);
				}
				break;
			}
		}

		return effects;
	}

	/**
	 * Gives a certain player ownership of a property, and deducts the price of that property from
	 * their balance
	 * 
	 * @param player - the player purchasing the property
	 * @param toBuy - the property being purchased
	 */
	public void buyProperty(Player player, OwnableSquare toBuy) {

	}

	public Square getLocation(String locationName) {
		for (BoardLocation location : locations) {
			if (location.getSquare().getName().equalsIgnoreCase(locationName)) {
				return location.getSquare();
			}
		}

		return null;
	}

	public Square getPieceLocation(Piece piece) {
		for (BoardLocation location : locations) {
			if (location.hasPiece(piece)) {
				return location.getSquare();
			}
		}

		return null;
	}

	public Square squareAtIndex(int index) {
		return locations[index].getSquare();
	}

	public int getLocationIndex(String locationName) {
		for (int i = 0; i < locations.length; i++) {
			if (locations[i].getSquare().getName().equalsIgnoreCase(locationName)) {
				return i;
			}
		}
		return -1;
	}

	public int getLocationIndex(Square location) {
		for (int i = 0; i < locations.length; i++) {
			if (locations[i].getSquare().equals(location)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Draws a card from either the chance deck or the community chest deck
	 * 
	 * @param chance - if this should draw from the chance deck or not
	 * @return the drawn card, which is of type {@code ChanceCard} if chance was true, else is of
	 *         type {@code CommunityChestCard}
	 */
	public Card drawCard(boolean chance) {
		Deck toDrawFrom = (chance ? chanceDeck : communityChestDeck);
		if (toDrawFrom.isEmpty()) {
			toDrawFrom.add(chance ? ChanceCardList.getChanceCards(this, players)
					: CommunityChestCardList.getCommunityChestCards(this, players));
			toDrawFrom.shuffle();
		}
		return toDrawFrom.draw();
	}

	public String getPrintablePlayerLocation(Player player) {
		StringBuilder result = new StringBuilder();

		int pIndex = getLocationIndex(getPieceLocation(player.getPiece()));

		// if (pIndex == 0) {
		// result.append(locations[locations.length - 1] + "\n");
		// } else {
		// result.append(locations[pIndex - 1] + "\n");
		// }

		result.append(locations[pIndex] + "\n");

		// if (pIndex == locations.length - 1) {
		// result.append(locations[0] + "\n");
		// } else {
		// result.append(locations[pIndex + 1] + "\n");
		// }

		return result.toString();
	}
}
