package edu.neumont.csc110.game_pieces;

public class Hotel {
	private static int count = 12;

	/**
	 * 
	 * @return -
	 */
	public static boolean takeBuilding() {
		if (count == 0) {
			return false;
		}
		House.returnBuilding();
		House.returnBuilding();
		House.returnBuilding();
		House.returnBuilding();
		count--;
		return true;
	}

	public static void returnBuilding() {
		for (int i = 0; i < 4; i++) {
			if (!House.takeBuilding()) {
				throw new IllegalArgumentException("No houses left to take!");
			}
		}
		count++;
	}
}
