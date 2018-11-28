package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces.Property;
import edu.neumont.csc110.game_pieces.Property.Color;
import edu.neumont.csc110.game_pieces.Railroad;
import edu.neumont.csc110.game_pieces.Utility;
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
		squares[1] =
				new Property("Mediterranean Avenue", Color.BROWN, 60, 2, 10, 30, 90, 160, 250, 50);
		squares[2] = new CommunityChestSquare(board);
		squares[3] = new Property("Baltic Avenue", Color.BROWN, 60, 4, 20, 60, 180, 320, 450, 50);
		squares[4] = new TaxSquare("Income Tax", 200);
		squares[5] = new Railroad("Reading Railroad");
		squares[6] = new Property("Oriental Avenue", Color.CYAN, 100, 6, 30, 90, 270, 400, 550, 50);
		squares[7] = new ChanceSquare(board);
		squares[8] = new Property("Vermont Avenue", Color.CYAN, 100, 6, 30, 90, 270, 400, 550, 50);
		squares[9] =
				new Property("Connecticut Avenue", Color.CYAN, 120, 8, 40, 100, 300, 450, 600, 50);
		squares[10] = new JailSquare();
		squares[11] = new Property("St.Charles Place", Color.MAGENTA, 140, 10, 50, 150, 450, 625,
				750, 100);
		squares[12] = new Utility("Electric Company");
		squares[13] =
				new Property("States Avenue", Color.MAGENTA, 140, 10, 50, 150, 450, 625, 750, 100);
		squares[14] = new Property("Virginia Avenue", Color.MAGENTA, 160, 12, 60, 180, 500, 700,
				900, 100);
		squares[15] = new Railroad("Pennsylvania Railroad");
		squares[16] =
				new Property("St. James Place", Color.ORANGE, 180, 14, 70, 200, 550, 750, 950, 100);
		squares[17] = new CommunityChestSquare(board);
		squares[18] = new Property("Tennessee Avenue", Color.ORANGE, 190, 14, 70, 200, 550, 750,
				950, 100);
		squares[19] = new Property("New York Avenue", Color.ORANGE, 200, 16, 80, 220, 600, 800,
				1000, 100);
		squares[20] = new FreeParkingSquare();
		squares[21] =
				new Property("KentuckyAvenue", Color.RED, 220, 18, 90, 250, 700, 875, 1050, 150);
		squares[22] = new ChanceSquare(board);
		squares[23] =
				new Property("Indiana Avenue", Color.RED, 220, 18, 90, 250, 700, 875, 1050, 150);
		squares[24] =
				new Property("Illinois Avenue", Color.RED, 240, 20, 100, 300, 750, 925, 1100, 150);
		squares[25] = new Railroad("B. & O. Railroad");
		squares[26] = new Property("Atlantic Avenue", Color.YELLLOW, 260, 22, 110, 330, 800, 975,
				1150, 150);
		squares[27] = new Property("Ventnor Avenue", Color.YELLLOW, 260, 22, 110, 330, 800, 975,
				1150, 150);
		squares[28] = new Utility("Water Works");
		squares[29] = new Property("Marvin Gardens", Color.YELLLOW, 280, 24, 120, 360, 850, 1025,
				1200, 150);
		squares[30] = new GoToJailSquare(board);
		squares[31] = new Property("Pacific Avenue", Color.GREEN, 300, 26, 130, 390, 900, 1100,
				1275, 200);
		squares[32] = new Property("North Carolina Avenue", Color.GREEN, 300, 26, 130, 390, 900,
				1100, 1275, 200);
		squares[33] = new CommunityChestSquare(board);
		squares[34] = new Property("Pennsylvania Avenue", Color.GREEN, 320, 28, 150, 450, 1000,
				1200, 1400, 200);
		squares[35] = new Railroad("Short Line");
		squares[36] = new ChanceSquare(board);
		squares[37] =
				new Property("Park Place", Color.BLUE, 350, 35, 175, 500, 1100, 1300, 1500, 200);
		squares[38] = new TaxSquare("Luxuary Tax", 100);
		squares[39] =
				new Property("Boardwalk", Color.BLUE, 400, 50, 200, 600, 1400, 1700, 2000, 200);

		return squares;
	}
}
