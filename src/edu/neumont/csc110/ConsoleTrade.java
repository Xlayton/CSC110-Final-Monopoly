package edu.neumont.csc110;

import java.util.ArrayList;
import java.util.Arrays;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;
import edu.neumont.csc110.game_pieces_abstract.Trade;
import interfaces.ConsoleUI;
import interfaces.MenuOption;

public class ConsoleTrade extends Trade {
	private final Player current, toTrade;
	private ArrayList<OwnableSquare> currentWant, toTradeWant;

	public ConsoleTrade(Player current, Player toTrade) {
		this.current = current;
		this.toTrade = toTrade;
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
				boolean isOpen = true;
				while (isOpen) {
					Player toChange = getChangeDecision();
					if (toChange.equals(current)) {
						switch (getCounterDecision()) {
						case DELETE:
							counterOfferDelete(currentWant);
							break;
						case ADD:
							counterOfferAdd(currentWant, current.getProperties());
							break;
						}
					} else {
						switch (getCounterDecision()) {
						case DELETE:
							counterOfferDelete(toTradeWant);
							break;
						case ADD:
							counterOfferAdd(toTradeWant, current.getProperties());
							break;
						}
					}
				}
				break;
			case DECLINE:
				System.out.println(current.getName() + " " + current.getProperties().toString());
				currentWant.clear();
				toTradeWant.clear();
				isAgree = true;
				break;
			}
		}
	}



	private ArrayList<OwnableSquare> chooseDeedsToTrade(Player pickingProperty) {
		boolean isOpen = true;
		ArrayList<String> placeNames = new ArrayList<>();
		ArrayList<OwnableSquare> locationsToChooseFrom = new ArrayList<>();
		ArrayList<OwnableSquare> chosenTradeItems = new ArrayList<>();
		int selection = placeNames.size() + 1;

		for (OwnableSquare s : pickingProperty.copyProperties()) {
			placeNames.add(s.copySquare().getName());
			locationsToChooseFrom.add(s.copySquare());
		}
		while (isOpen) {
			System.out.println("Pick a property from " + pickingProperty.getName());
			selection = ConsoleUI.promptForMenuSelection(placeNames.toArray(new String[0]), "Done");
			if (selection == 0) {
				isOpen = false;
				break;
			} else {
				chosenTradeItems.add(locationsToChooseFrom.get(selection - 1));
				placeNames.remove(selection - 1);
				locationsToChooseFrom.remove(selection - 1);
			}
		}

		return chosenTradeItems;
	}

	private void commenceTrade(ArrayList<OwnableSquare> requestedProperties,
			ArrayList<OwnableSquare> giveToTrade) {
		current.addProperties(requestedProperties.toArray(new OwnableSquare[0]));
		current.removeProperties(giveToTrade.toArray(new OwnableSquare[0]));
		toTrade.addProperties(giveToTrade.toArray(new OwnableSquare[0]));
		toTrade.removeProperties(requestedProperties.toArray(new OwnableSquare[0]));

	}

	private ArrayList<OwnableSquare> counterOfferDelete(ArrayList<OwnableSquare> deletingFrom) {
		boolean isGoing = true;
		ArrayList<String> propertyNames = new ArrayList<>();
		for (OwnableSquare e : deletingFrom) {
			propertyNames.add(e.getName());
		}
		while (isGoing) {
			System.out.println("Choose a property to delete: ");
			int selection = ConsoleUI.promptForMenuSelection(
					propertyNames.toArray(new String[propertyNames.size()]), "Done");
			System.out.println("What do you want to get rid of?");
			if (selection == 0) {
				isGoing = false;
				break;
			} else {
				propertyNames.remove(selection);
				deletingFrom.remove(selection);
			}
		}
		return deletingFrom;
	}

	private ArrayList<OwnableSquare> counterOfferAdd(ArrayList<OwnableSquare> addingTo,
			OwnableSquare[] ownableSquares) {
		ArrayList<OwnableSquare> otherOwnedProperties = new ArrayList<>();
		ArrayList<String> otherPropertyNames = new ArrayList<>();
		boolean isChoosing = true;

		for (OwnableSquare e : ownableSquares) {
			if (!addingTo.contains(e)) {
				otherOwnedProperties.add(e);
				otherPropertyNames.add(e.getName());
			}
		}

		while (isChoosing) {
			System.out.println("Choose properties to add: ");
			int selection = ConsoleUI.promptForMenuSelection(
					otherPropertyNames.toArray(new String[otherPropertyNames.size()]), "Done");
			if (selection == 0) {
				isChoosing = false;
				break;
			} else {
				addingTo.add(otherOwnedProperties.get(selection));
				otherOwnedProperties.remove(selection);
			}
		}

		return addingTo;

	}

	private TradeOption getFinalDecision(ArrayList<OwnableSquare> desiredProperties,
			ArrayList<OwnableSquare> giveToTrade) {
		String[] desiredPropertyNames = new String[desiredProperties.size()];
		for (int i = 0; i < desiredPropertyNames.length; i++) {
			desiredPropertyNames[i] = desiredProperties.get(i).getName();
		}
		String[] givingProperties = new String[giveToTrade.size()];
		for (int i = 0; i < givingProperties.length; i++) {
			givingProperties[i] = giveToTrade.get(i).getName();
		}
		System.out.println(current.getName() + " will give: " + Arrays.toString(desiredPropertyNames));
		System.out.println(toTrade.getName() + " will give: " + Arrays.toString(givingProperties));
		System.out.println(current.getName() + ", do you like these terms?");
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
