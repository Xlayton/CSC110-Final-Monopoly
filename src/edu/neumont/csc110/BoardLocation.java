package edu.neumont.csc110;

import java.util.ArrayList;
import java.util.Iterator;
import edu.neumont.csc110.game_pieces.Piece;
import edu.neumont.csc110.game_pieces_abstract.Square;

public class BoardLocation implements Iterable<Piece> {
	private final Square square;
	
	private ArrayList<Piece> piecesOnSquare;
	
	public BoardLocation(Square square) {
		this.square = square;
		piecesOnSquare = new ArrayList<>();
	}
	
	public void putPiece(Piece toPut) {
		piecesOnSquare.add(toPut);
	}
	
	public void takePiece(Piece toTake) {
		piecesOnSquare.remove(toTake);
	}

	public boolean hasPiece(Piece toCheck) {
		return piecesOnSquare.contains(toCheck);
	}
	
	public Square getSquare() {
		return square;
	}

	@Override
	public Iterator<Piece> iterator() {
		return piecesOnSquare.iterator();
	}
	
	@Override
	public String toString() {
		String result = square.toString() + "\n";
		for (int i = 0; i < piecesOnSquare.size(); i++) {
			result += piecesOnSquare.get(i) + (i != piecesOnSquare.size() - 1 ? ", " : "");
		}
		return result;
	}
}
