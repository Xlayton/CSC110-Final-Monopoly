package edu.neumont.csc110;

import java.util.ArrayList;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;
import edu.neumont.csc110.game_pieces_abstract.Trade;
import interfaces.ConsoleUI;
import interfaces.MenuOption;

public class ConsoleTrade extends Trade {
	private final Player current, toTrade;
	private final OwnableSquare[] currentDeeds, toTradeDeeds;
	private ArrayList<OwnableSquare> currentWant, toTradeWant;

	public ConsoleTrade(Player current, Player toTrade, OwnableSquare[] currentDeeds,
			OwnableSquare[] toStartDeeds) {
		this.current = current;
		this.toTrade = toTrade;
		this.currentDeeds = getOwnedLocations(current);
		this.toTradeDeeds = getOwnedLocations(toTrade);
		this.currentWant = new ArrayList<>();
		this.toTradeWant = new ArrayList<>();
	}

	@Override
	public void startTrade() {
		boolean isAgree = false;
		currentWant = chooseDeedsToTrade(toTrade);
		toTradeWant = chooseDeedsToTrade(current);
		while (!isAgree) {
			switch (getFinalDecision(currentWant, toTradeWant)) {
			case ACCEPT:
				commenceTrade(currentWant, toTradeWant);
				isAgree = true;
				break;
			case COUNTER_OFFER:
				Player toChange = getChangeDecision();
				if (toChange.equals(current)) {
					switch (getCounterDecision()) {
					case DELETE:
						counterOfferDelete(currentWant);
						break;
					case ADD:
						counterOfferAdd(currentWant);
						break;
					}
				} else {
					switch (getCounterDecision()) {
					case DELETE:
						counterOfferDelete(toTradeWant);
						break;
					case ADD:
						counterOfferAdd(toTradeWant);
						break;
					}
				}
				break;
			case DECLINE:
				isAgree = true;
			}
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
		while (isOpen) {
			selection = ConsoleUI.promptForMenuSelection(placeNames.toArray(new String[0]), "Done");
			if (selection == 0) {
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

	// TODO ADD FUNCTIONALITY
	private void commenceTrade(ArrayList<OwnableSquare> currentWant,
			ArrayList<OwnableSquare> giveToTrade) {


	}
	//TODO ADD FUNCTIONALITY
	private ArrayList<OwnableSquare> counterOfferDelete(ArrayList<OwnableSquare> deletingFrom) {

		
		
		return null;
	}
	//TODO ADD FUNCTIONALITY
	private ArrayList<OwnableSquare> counterOfferAdd(ArrayList<OwnableSquare> addingTo) {

		ArrayList<OwnableSquare> otherOwnedProperites = new ArrayList<>();
		return null;
	}

	private TradeOption getFinalDecision(ArrayList<OwnableSquare> currentWant,
			ArrayList<OwnableSquare> giveToTrade) {
		System.out.println(current.getName() + " will give: " + currentWant);
		System.out.println(toTrade.getName() + " will give: " + giveToTrade);
		System.out.println("Do you like these terms?");
		return ConsoleUI.promptForMenuSelection(TradeOption.values());
	}

	private CounterOption getCounterDecision() {
		return ConsoleUI.promptForMenuSelection(CounterOption.values());
	}

	private Player getChangeDecision() {
		String[] playerNames = new String[] {current.getName(), toTrade.getName()};
		System.out.println("Whos offerings do you want to modify?");
		int selection = ConsoleUI.promptForMenuSelection(playerNames);
		if (playerNames[selection].equals(current.getName())) {
			return current;
		} else {
			return toTrade;
		}
	}

	private enum CounterOption implements MenuOption {
		DELETE("Delete"),
		ADD("Add More");

		private String desc;

		private CounterOption(String desc) {
			this.desc = desc;
		}

		@Override
		public String getDesc() {
			return desc;
		}
	}

	private enum TradeOption implements MenuOption {
		ACCEPT("Accept"),
		COUNTER_OFFER("Counter Offer"),
		DECLINE("Decline");

		private String desc;

		private TradeOption(String desc) {
			this.desc = desc;
		}

		@Override
		public String getDesc() {
			return desc;
		}
	}



}
