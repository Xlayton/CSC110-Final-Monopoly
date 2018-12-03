package edu.neumont.csc110.game_pieces.special_squares;

import edu.neumont.csc110.MonopolyBoard;
import edu.neumont.csc110.Player;
import edu.neumont.csc110.game_pieces_abstract.SpecialSquare;

public class GoToJailSquare extends SpecialSquare {
	private final MonopolyBoard board;

	public GoToJailSquare(MonopolyBoard board) {
		super("Go to Jail");
		this.board = board;
	}

	@Override
	public String applyEffect(Player toApply) {
		board.moveTo(toApply, board.getLocation("Jail"), false);
		toApply.setJailed(true);
		return toApply.getName() + " sent directly to jail, without passing go.";
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(makeRow(SEPARATOR, false));
		result.append(makeRow("GO TO JAIL"));
		result.append(makeRow("               ,"));
		result.append(makeRow(" __  _.-\"` `'-. "));
		result.append(makeRow("/||\\'._ __{}_(  "));
		result.append(makeRow("||||  |'--.__\\  "));
		result.append(makeRow("|  L.(   -\\-|   "));
		result.append(makeRow("\\ .-' |   _ |   "));
		result.append(makeRow("| |   )\\___/    "));
		result.append(makeRow("|  \\-'`:._]     "));
		result.append(makeRow("\\__/;      '-.  "));
		result.append(makeRow(SEPARATOR));

		return result.toString();
	}
}
