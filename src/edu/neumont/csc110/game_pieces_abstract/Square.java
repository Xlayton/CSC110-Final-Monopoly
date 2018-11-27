package edu.neumont.csc110.game_pieces_abstract;

import edu.neumont.csc110.Player;

public abstract class Square implements Comparable<Square> {
	protected final String name;
	
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
}
