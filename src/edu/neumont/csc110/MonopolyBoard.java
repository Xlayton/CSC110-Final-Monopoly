package edu.neumont.csc110;

import edu.neumont.csc110.game_pieces.ChanceCard;
import edu.neumont.csc110.game_pieces.CommunityChestCard;
import edu.neumont.csc110.game_pieces.Piece;
import edu.neumont.csc110.game_pieces.Property;
import edu.neumont.csc110.game_pieces_abstract.Card;
import edu.neumont.csc110.game_pieces_abstract.Square;

/**
 * A monopoly board
 * 
 * @author Ryder James
 * @author Clayton Schrumpf
 * @author Maya Shackleford
 */
public class MonopolyBoard {
	private final Square[] squares;
	private final Card[] chanceCards, communityChestCards;
	private final Piece[] pieces;

	/**
	 * Constructs and initializes a new Monopoly board with a maximum of 8 players
	 */
	public MonopolyBoard() {
		squares = new Square[40];
		chanceCards = new ChanceCard[16];
		communityChestCards = new CommunityChestCard[16];
		pieces = new Piece[8];
	}

	public Square getPieceLocation(Piece piece) {
		return null;
	}
	
	/**
	 * Move a specified player's piece a number of spaces around the board
	 * 
	 * @param player - the player who owns the piece to move
	 * @param distance - how far to move it
	 */
	public void movePiece(Player player, int distance) {
		
	}

	public Square getLocation(String locationName) {
		for (Square square : squares) {
			if (square.getName().equalsIgnoreCase(locationName)) {
				return square;
			}
		}
		
		return null;
	}
	
	/**
	 * Draws a card from either the chance deck or the community chest deck
	 * 
	 * @param chance - if this should draw from the chance deck or not
	 * @return the drawn card, which is of type {@code ChanceCard} if chance was true, else is of
	 *         type {@code CommunityChestCard}
	 */
	public Card drawCard(boolean chance) {
		return null;
	}

	/**
	 * Gives a certain player ownership of a property, and deducts the price of that property from
	 * their balance
	 * 
	 * @param player - the player purchasing the property
	 * @param toBuy - the property being purchased
	 */
	public void buyProperty(Player player, Property toBuy) {

	}

	/**
	 * Moves a player's piece to the next instance of a certain location on the board
	 * 
	 * @param player - the player who owns the piece to move
	 * @param location - the location to move the piece to
	 * @param shouldPassGo - whether the player should collect $200 if their piece passes go
	 */
	public void moveTo(Player player, Square location, boolean shouldPassGo) {
		
	}
	
	public int getLocationIndex(Square location) {
		for (int i = 0; i < squares.length; i++) {
			if (location.equals(squares[i])) {
				return i;
			}
		}
		return -1;
	}
	
	public Square squareAtIndex(int index) {
		return squares[index];
	}
}
