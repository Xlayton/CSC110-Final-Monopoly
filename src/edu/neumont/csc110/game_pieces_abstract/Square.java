package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces.Property;
import edu.neumont.csc110.game_pieces.Property.Color;
import edu.neumont.csc110.game_pieces.special_squares.ChanceSquare;
import edu.neumont.csc110.game_pieces.special_squares.CommunityChestSquare;
import edu.neumont.csc110.game_pieces.special_squares.FreeParkingSquare;
import edu.neumont.csc110.game_pieces.special_squares.GoSquare;
import edu.neumont.csc110.game_pieces.special_squares.GoToJailSquare;
import edu.neumont.csc110.game_pieces.special_squares.JailSquare;
import edu.neumont.csc110.game_pieces.special_squares.TaxSquare;

public abstract class Square implements Comparable<Square> {
	private final String name;
	
	protected Square(String name) {
		this.name = name;
	}
	
	public abstract void landedOn(Player player);
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object anotherSquare) {
		return this.compareTo((Square) anotherSquare) == 0;
	}
	
	@Override
	public int compareTo(Square anotherSquare) {
		return name.compareTo(anotherSquare.getName());
	}
	public static Square[] getSquares(MonopolyBoard board) {
		Square[] squares = new Square[40];
	
		squares[0] = new GoSquare();
		squares[1] = new Property("Mediterranean Avenue", Color.BROWN, 60);
		squares[2] = new CommunityChestSquare();
		squares[3] = new Property("Baltic Avenue", Color.BROWN, 60);
		squares[4] = new TaxSquare("Income Tax", 200);
		squares[5] = null;
		squares[6] = new Property("Oriental Avenue", Color.CYAN, 100);
		squares[7] = new ChanceSquare();
		squares[8] = new Property("Vermont Avenue", Color.CYAN, 100);
		squares[9] = new Property("Connecticut Avenue", Color.CYAN, 120);
		squares[10] = new JailSquare();
		squares[11] = new Property("St.Charles Place", Color.MAGENTA, 140);
		squares[12] = null;
		squares[13] = new Property("States Avenue", Color.MAGENTA, 140);
		squares[14] = new Property("Virginia Avenue", Color.MAGENTA, 160);
		squares[15] = null;
		squares[16] = new Property("St. James Place", Color.ORANGE, 180);
		squares[17] = new CommunityChestSquare();
		squares[18] = new Property("Tennessee Avenue", Color.ORANGE, 190);
		squares[19] = new Property("New York Avenue", Color.ORANGE, 200);
		squares[20] = new FreeParkingSquare();
		squares[21] = new Property("KentuckyAvenue", Color.RED, 220);
		squares[22] = new ChanceSquare();
		squares[23] = new Property("Indiana Avenue", Color.RED, 220);
		squares[24] = new Property("Illinois Avenue", Color.RED, 240);
		squares[25] = null;
		squares[26] = new Property("Atlantic Avenue", Color.YELLLOW, 260);
		squares[27] = new Property("Ventnor Avenue", Color.YELLLOW, 260);
		squares[28] = null;
		squares[29] = new Property("Marvin Gardens", Color.YELLLOW, 280);
		squares[30] = new GoToJailSquare(board);
		squares[31] = new Property("Pacific Avenue", Color.GREEN, 300);
		squares[32] = new Property("North Carolina Avenue", Color.GREEN, 300);
		squares[33] = new CommunityChestSquare();
		squares[34] = new Property("Pennsylvania Avenue", Color.GREEN, 320);
		squares[35] = null;
		squares[36] = new ChanceSquare();
		squares[37] = new Property("Park Place", Color.BLUE, 350);
		squares[38] = new TaxSquare("Luxuary Tax", 100);
		squares[39] = new Property("Boardwalk", Color.BLUE, 400);
		
		return squares;
	}
}
