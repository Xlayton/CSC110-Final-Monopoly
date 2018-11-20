package edu.neumont.csc110.equipment;

public class Piece {
	private String art;
	
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
