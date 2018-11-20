package edu.neumont.csc110;

import edu.neumont.csc110.equipment.ChanceCard;
import edu.neumont.csc110.equipment.CommunityChestCard;
import edu.neumont.csc110.equipment.Piece;
import edu.neumont.csc110.equipment_abstract.Card;
import edu.neumont.csc110.equipment_abstract.Square;

public class MonopolyBoard {

	Square[] squares;
	Card[] chanceCards, communityChestCards;
	Piece[] pieces;
	
	public MonopolyBoard() {
		squares = new Square[36];
		chanceCards = new ChanceCard[16];
		communityChestCards = new CommunityChestCard[16];
		pieces = new Piece[8];
	}
	
	public void movePiece(Piece piece, int distance) {
		
	}
	
	public Card drawCard(boolean chance) {
		return null;
	}
	
	public void buySquare(Player player, Square toBuy) {
		
	}
	
	public void moveTo(Player player, Square location) {
		
	}
}
