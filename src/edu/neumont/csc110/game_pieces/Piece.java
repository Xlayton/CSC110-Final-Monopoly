package edu.neumont.csc110.game_pieces;

public class Piece {
	private final String art;
/**
 * 
 * @param art - art of the players piece
 */
	public Piece(String art) {
		this.art = art;
	}

	public String getArt() {
		return art;
	}

	@Override
	public String toString() {
		return this.getArt();
	}
}
