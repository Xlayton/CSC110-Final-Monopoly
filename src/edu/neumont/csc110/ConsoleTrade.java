package edu.neumont.csc110;

import java.util.ArrayList;
import java.util.Arrays;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;
import edu.neumont.csc110.game_pieces_abstract.Trade;
import interfaces.ConsoleUI;
import interfaces.MenuOption;

public class ConsoleTrade extends Trade {
	private final Player initiatedTrade, toTradeWith;

	private ArrayList<OwnableSquare> requestedItems, offeredItems;
	private int balanceRequested, balanceOffered;

	public ConsoleTrade(Player startingTrade, Player toTradeWith) {
		this.initiatedTrade = startingTrade;
		this.toTradeWith = toTradeWith;
		this.requestedItems = new ArrayList<>();
		this.offeredItems = new ArrayList<>();
	}

	@Override
	public void startTrade() {
		boolean isAgree = false;
		requestedItems = chooseDeedsToTrade(toTradeWith);
		offeredItems = chooseDeedsToTrade(initiatedTrade);
		while (!isAgree) {
			switch (getFinalDecision(requestedItems, offeredItems)) {
			case ACCEPT:
				commenceTrade(requestedItems, offeredItems);
				isAgree = true;
				break;
			case COUNTER_OFFER:
				new ConsoleTrade(toTradeWith, initiatedTrade).startTrade();
				return;
			case DECLINE:
				requestedItems.clear();
				offeredItems.clear();
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
		placeNames.add("Add Money");
		while (isOpen) {
			System.out.println("Pick a property from " + pickingProperty.getName());
			selection =
					ConsoleUI.promptForMenuSelection(placeNames.toArray(new String[0]), "Done") - 1;
			if (selection == -1) {
				isOpen = false;
				break;
			} else if (selection == placeNames.size() - 1) {
				boolean isValid = false;
				while (!isValid) {
					System.out.println(pickingProperty.toString());
					int amount = ConsoleUI.promptForInt("Enter Amount");
					try {
						pickingProperty.subtractBalance(amount);
						pickingProperty.addBalance(amount);
					} catch (InsufficientFundsException ex) {
						System.out.println("Player doesn't have that much money");
						continue;
					}
					if (pickingProperty.equals((Player) initiatedTrade)) {
						balanceOffered += amount;
					} else {
						balanceRequested += amount;
					}
					isValid = true;
				}
			} else {
				chosenTradeItems.add(locationsToChooseFrom.get(selection));
				placeNames.remove(selection);
				locationsToChooseFrom.remove(selection);
			}
		}

		return chosenTradeItems;
	}

	private void commenceTrade(ArrayList<OwnableSquare> requestedProperties,
			ArrayList<OwnableSquare> giveToTrade) {
		initiatedTrade.addProperties(requestedProperties.toArray(new OwnableSquare[0]));
		initiatedTrade.removeProperties(giveToTrade.toArray(new OwnableSquare[0]));
		initiatedTrade.addBalance(balanceRequested);
		initiatedTrade.subtractBalance(toTradeWith, balanceOffered);
		toTradeWith.addProperties(giveToTrade.toArray(new OwnableSquare[0]));
		toTradeWith.removeProperties(requestedProperties.toArray(new OwnableSquare[0]));
		toTradeWith.subtractBalance(initiatedTrade, balanceRequested);
		toTradeWith.addBalance(balanceOffered);
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
		System.out.println(initiatedTrade.getName() + " will receive: "
				+ Arrays.toString(desiredPropertyNames) + " and $" + balanceRequested);
		System.out.println(toTradeWith.getName() + " will receive: "
				+ Arrays.toString(givingProperties) + " and $" + balanceOffered);
		System.out.println(toTradeWith.getName() + ", do you like these terms?");
		return ConsoleUI.promptForMenuSelection(TradeOption.values(), false);
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
