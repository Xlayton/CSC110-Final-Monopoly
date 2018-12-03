package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces.TitleDeed;
import edu.neumont.csc110.game_pieces.TitleDeed.Color;
import edu.neumont.csc110.game_pieces.Railroad;
import edu.neumont.csc110.game_pieces.Utility;
import edu.neumont.csc110.game_pieces.special_squares.ChanceSquare;
import edu.neumont.csc110.game_pieces.special_squares.CommunityChestSquare;
import edu.neumont.csc110.game_pieces.special_squares.FreeParkingSquare;
import edu.neumont.csc110.game_pieces.special_squares.GoSquare;
import edu.neumont.csc110.game_pieces.special_squares.GoToJailSquare;
import edu.neumont.csc110.game_pieces.special_squares.JailSquare;
import edu.neumont.csc110.game_pieces.special_squares.LuxuryTaxSquare;

public abstract class Square implements Comparable<Square> {
	private final String name;
	
	protected static final String SEPARATOR = "__________________________";
	protected static final int ROW_IN_LENGTH = 26;

	protected Square(String name) {
		this.name = name;
	}
/**
 * the name the player puts in for themselves
 * @param player - piece of player
 */
	public abstract void landedOn(Player player);
/**
 * shows the players name when it is their turn
 * @return - show name
 */
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

	public static final Square[] getSquares(MonopolyBoard board) {
		Square[] squares = new Square[40];

		squares[0] = new GoSquare();
		squares[1] =
				new TitleDeed("Mediterranean Avenue", Color.BROWN, 60, 2, 10, 30, 90, 160, 250, 50);
		squares[2] = new CommunityChestSquare(board);
		squares[3] = new TitleDeed("Baltic Avenue", Color.BROWN, 60, 4, 20, 60, 180, 320, 450, 50);
		squares[4] = new LuxuryTaxSquare("Income Tax", 200);
		squares[5] = new Railroad("Reading Railroad");
		squares[6] = new TitleDeed("Oriental Avenue", Color.CYAN, 100, 6, 30, 90, 270, 400, 550, 50);
		squares[7] = new ChanceSquare(board);
		squares[8] = new TitleDeed("Vermont Avenue", Color.CYAN, 100, 6, 30, 90, 270, 400, 550, 50);
		squares[9] =
				new TitleDeed("Connecticut Avenue", Color.CYAN, 120, 8, 40, 100, 300, 450, 600, 50);
		squares[10] = new JailSquare();
		squares[11] = new TitleDeed("St.Charles Place", Color.MAGENTA, 140, 10, 50, 150, 450, 625,
				750, 100);
		squares[12] = new Utility("Electric Company");
		squares[13] =
				new TitleDeed("States Avenue", Color.MAGENTA, 140, 10, 50, 150, 450, 625, 750, 100);
		squares[14] = new TitleDeed("Virginia Avenue", Color.MAGENTA, 160, 12, 60, 180, 500, 700,
				900, 100);
		squares[15] = new Railroad("Pennsylvania Railroad");
		squares[16] =
				new TitleDeed("St. James Place", Color.ORANGE, 180, 14, 70, 200, 550, 750, 950, 100);
		squares[17] = new CommunityChestSquare(board);
		squares[18] = new TitleDeed("Tennessee Avenue", Color.ORANGE, 190, 14, 70, 200, 550, 750,
				950, 100);
		squares[19] = new TitleDeed("New York Avenue", Color.ORANGE, 200, 16, 80, 220, 600, 800,
				1000, 100);
		squares[20] = new FreeParkingSquare();
		squares[21] =
				new TitleDeed("KentuckyAvenue", Color.RED, 220, 18, 90, 250, 700, 875, 1050, 150);
		squares[22] = new ChanceSquare(board);
		squares[23] =
				new TitleDeed("Indiana Avenue", Color.RED, 220, 18, 90, 250, 700, 875, 1050, 150);
		squares[24] =
				new TitleDeed("Illinois Avenue", Color.RED, 240, 20, 100, 300, 750, 925, 1100, 150);
		squares[25] = new Railroad("B. & O. Railroad");
		squares[26] = new TitleDeed("Atlantic Avenue", Color.YELLOW, 260, 22, 110, 330, 800, 975,
				1150, 150);
		squares[27] = new TitleDeed("Ventnor Avenue", Color.YELLOW, 260, 22, 110, 330, 800, 975,
				1150, 150);
		squares[28] = new Utility("Water Works");
		squares[29] = new TitleDeed("Marvin Gardens", Color.YELLOW, 280, 24, 120, 360, 850, 1025,
				1200, 150);
		squares[30] = new GoToJailSquare(board);
		squares[31] = new TitleDeed("Pacific Avenue", Color.GREEN, 300, 26, 130, 390, 900, 1100,
				1275, 200);
		squares[32] = new TitleDeed("North Carolina Avenue", Color.GREEN, 300, 26, 130, 390, 900,
				1100, 1275, 200);
		squares[33] = new CommunityChestSquare(board);
		squares[34] = new TitleDeed("Pennsylvania Avenue", Color.GREEN, 320, 28, 150, 450, 1000,
				1200, 1400, 200);
		squares[35] = new Railroad("Short Line");
		squares[36] = new ChanceSquare(board);
		squares[37] =
				new TitleDeed("Park Place", Color.BLUE, 350, 35, 175, 500, 1100, 1300, 1500, 200);
		squares[38] = new LuxuryTaxSquare("Luxuary Tax", 100);
		squares[39] =
				new TitleDeed("Boardwalk", Color.BLUE, 400, 50, 200, 600, 1400, 1700, 2000, 200);

		return squares;
	}
/**
 * 
 * @param text
 * @return
 */
	protected String makeRow(String text) {
		return makeRow(text, true);
	}
/**
 * 
 * @param text
 * @param withEdges
 * @return
 */
	protected String makeRow(String text, boolean withEdges) {
		StringBuilder result = new StringBuilder();
		result.append(withEdges ? "|" : " ");
		result.append(padWithSpaces(text, ROW_IN_LENGTH));
		result.append(withEdges ? "|" : " ");
		result.append("\n");
		return result.toString();
	}
/**
 * 
 * @param toPad
 * @param length
 * @return
 */
	protected String padWithSpaces(String toPad, int length) {
		while (toPad.length() < length) {
			toPad = toPad + " ";
			if (toPad.length() < length) {
				toPad = " " + toPad;
			}
		}
		return toPad;
	}
}
