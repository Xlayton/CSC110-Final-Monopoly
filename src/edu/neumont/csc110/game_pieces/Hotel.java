package edu.neumont.csc110.game_pieces;

public class Hotel {
	private static int count = 12;

	public static boolean takeBuilding() {
		if (count == 0) {
			return false;
		}
		count--;
		return true;
	}

	public static void returnBuilding() {
		count++;
	}
}
