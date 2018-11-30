package edu.neumont.csc110;

import java.util.ArrayList;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;
import edu.neumont.csc110.game_pieces_abstract.Trade;
import interfaces.ConsoleUI;

public class ConsoleTrade extends Trade{
	private final Player current, toTrade;
	private final OwnableSquare[] currentDeeds, toTradeDeeds;

	public ConsoleTrade(Player current, Player toTrade, OwnableSquare[] currentDeeds,
			OwnableSquare[] toStartDeeds) {
		this.current = current;
		this.toTrade = toTrade;
		this.currentDeeds = getOwnedLocations(current);
		this.toTradeDeeds = getOwnedLocations(toTrade);
	}
	
	@Override
	public void startTrade() {
		boolean isAgree = false;
		ArrayList<OwnableSquare> currentWant = new ArrayList<>();
		ArrayList<OwnableSquare> toTradeWant = new ArrayList<>();
		while(!isAgree) {
			currentWant = chooseDeedsToTrade(toTrade);
			toTradeWant = chooseDeedsToTrade(current);
		}
	}
	
	
	private ArrayList<OwnableSquare> chooseDeedsToTrade(Player toTrade) {
		boolean isOpen = true;
		ArrayList<String> placeNames = new ArrayList<>();
		ArrayList<OwnableSquare> locationsToChooseFrom = new ArrayList<>();
		ArrayList<OwnableSquare> chosenTradeItems = new ArrayList<>();
		int selection = placeNames.size() + 1;
		
		for (OwnableSquare s : getOwnedLocations(toTrade)) {
			placeNames.add(s.getName());
			locationsToChooseFrom.add(s);
		}
		while(isOpen) {
			selection = ConsoleUI.promptForMenuSelection(placeNames.toArray(new String[0]), "Done");
			if(selection == 0) {
				isOpen = false;
				break;
			} else {
				placeNames.remove(selection);
				chosenTradeItems.add(getOwnedLocations(toTrade)[selection]);
				locationsToChooseFrom.remove(selection);
			}
		}
		
		return chosenTradeItems;
	}



	
	
}
