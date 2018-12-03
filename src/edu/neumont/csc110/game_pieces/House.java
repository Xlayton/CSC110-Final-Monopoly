package edu.neumont.csc110.game_pieces;

public class House {
	private static int count = 32;
/**
 * 
 * @return - 
 */
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
