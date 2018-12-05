package edu.neumont.csc110;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import edu.neumont.csc110.game_pieces.Piece;
import edu.neumont.csc110.game_pieces.TitleDeed;
import edu.neumont.csc110.game_pieces.TitleDeed.Color;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;
import edu.neumont.csc110.game_pieces_abstract.Square;
import interfaces.ConsoleUI;
import interfaces.MenuOption;

public class MonopolyGame {
	private final MonopolyBoard board;

	private ArrayList<Player> players;
	private ArrayList<String> playerNames;
	private Player currentPlayer;
	private int currentPlayerIndex, doubleCount;
	private boolean currentPlayerHasRolled, jailedThisTurn;

	public MonopolyGame() {
		players = new ArrayList<>();
		playerNames = new ArrayList<>();
		initGame();
		currentPlayerIndex = 0;
		doubleCount = 0;
		currentPlayer = players.get(currentPlayerIndex);
		currentPlayerHasRolled = false;
		jailedThisTurn = false;
		board = new MonopolyBoard(players.toArray(new Player[0]));
	}

	public void run() {
		boolean gameRunning = true;

		initSecretPlayers();

		do {
			printPlayerLocation(currentPlayer);
			System.out.println(currentPlayer.getName() + "'s turn");
			System.out.println(currentPlayer);

			gameRunning = doSwitch();
		} while (gameRunning);
	}

	private boolean doSwitch() {
		try {
			TurnChoice choice = printMenu();
			if (choice == null) {
				surrender();
				if (players.size() == 1) {
					endGame();
					return false;
				}
			}
			switch (choice) {
			case ESCAPE:
				getOutOfJail();
				break;
			case IMPROVE:
				improve();
				break;
			case MORTGAGE:
				mortgage();
				break;
			case ROLL:
				playerMove();
				break;
			case END:
				nextTurn();
				break;
			case TRADE:
				trade();
				break;
			case INFO:
				propertyInfo();
				break;
			default:
				break;
			}
		} catch (InsufficientFundsException e) {
			System.out.println(currentPlayer.getName() + " can't afford to do that!");
			ArrayList<Color> monopolizedColors = new ArrayList<>();
			for (Color c : Color.values()) {
				if (currentPlayer.isMonopolized(c)) {
					monopolizedColors.add(c);
				}
			}

			int amountRaised = 0;
			int raisable = 0;

			raisable += currentPlayer.getBalance();
			for (OwnableSquare s : currentPlayer) {
				raisable += s.getPrice() / 2;
				if (s instanceof TitleDeed) {
					raisable +=
							(((TitleDeed) s).getBuildingCost() * ((TitleDeed) s).getBuildingCount())
									/ 2;
				}
			}

			while (raisable >= e.getAmountOver()) {
				System.out.println("You need $" + (e.getAmountOver() - amountRaised) + " more");
				mortgage();
				sellHouses(monopolizedColors);
			}

			if (amountRaised >= e.getAmountOver()) {
				currentPlayer.subtractBalance(e.getBankruptingPlayer(), amountRaised);
				return true;
			}

			System.out.println(currentPlayer.getName() + " is bankrupt, and loses!");
			if (e.getBankruptingPlayer() != null) {
				System.out
						.println("Assets are transferred to " + e.getBankruptingPlayer().getName());
			}
			bankruptPlayer(currentPlayer, e.getBankruptingPlayer());
			if (players.size() == 1) {
				endGame();
				return false;
			}
		}
		return true;
	}


	private void buyHouses(ArrayList<Color> monopolizedColors) {
		ArrayList<TitleDeed> colorProperties = new ArrayList<>();
		ArrayList<String> colorPropertiesName = new ArrayList<>();
		System.out.println("What colored property would you like to buy houses/hotels for?");
		Color choice =
				ConsoleUI.promptForMenuSelection(monopolizedColors.toArray(new Color[0]), false);
		for (OwnableSquare gettingProp : currentPlayer.getProperties()) {
			if (gettingProp instanceof TitleDeed) {
				if (((TitleDeed) gettingProp).getColor().equals(choice)) {
					colorProperties.add((TitleDeed) gettingProp);
					colorPropertiesName.add(gettingProp.getName());
				}
			}
		}
		System.out.println(
				"Properties on this color cost $" + colorProperties.get(0).getBuildingCost());

		int toBuildOn =
				ConsoleUI.promptForMenuSelection(colorPropertiesName.toArray(new String[0]), false)
						- 1;
		for (TitleDeed d : colorProperties) {
			if (!d.equals((TitleDeed) colorProperties.get(toBuildOn))) {
				if (d.getBuildingCount() >= colorProperties.get(toBuildOn).getBuildingCount()) {
					try {
						colorProperties.get(toBuildOn).buyBuilding();
						currentPlayer
								.subtractBalance(colorProperties.get(toBuildOn).getBuildingCost());
						System.out.println(
								"Added 1 house to " + colorProperties.get(toBuildOn).getName());
					} catch (IllegalArgumentException ex) {
						System.out.println(ex.getMessage());
					}
				} else {
					System.out.println("Buy property on a different square in the set first");
				}
			}
		}
	}



	private void sellHouses(ArrayList<Color> monopolizedColors) {
		ArrayList<TitleDeed> colorProperties = new ArrayList<>();
		ArrayList<String> colorPropertiesName = new ArrayList<>();
		System.out.println("What colored property would you like to sell houses/hotels for?");
		Color choice =
				ConsoleUI.promptForMenuSelection(monopolizedColors.toArray(new Color[0]), false);
		for (OwnableSquare gettingProp : currentPlayer.getProperties()) {
			if (gettingProp instanceof TitleDeed) {
				if (((TitleDeed) gettingProp).getColor().equals(choice)) {
					colorProperties.add((TitleDeed) gettingProp);
					colorPropertiesName.add(gettingProp.getName());
				}
			}
		}

		try {
			int toSellFrom = ConsoleUI
					.promptForMenuSelection(colorPropertiesName.toArray(new String[0]), false) - 1;
			for (TitleDeed d : colorProperties) {
				if (!d.equals((TitleDeed) colorProperties.get(toSellFrom))) {
					if (d.getBuildingCount() >= colorProperties.get(toSellFrom)
							.getBuildingCount()) {
						try {
							colorProperties.get(toSellFrom).sellBuilding();
							currentPlayer
									.addBalance(colorProperties.get(toSellFrom).sellBuilding());
							System.out.println("Sold 1 house from "
									+ colorProperties.get(toSellFrom).getName());
						} catch (IllegalArgumentException ex) {
							System.out.println(ex.getMessage());
						}
					} else {
						System.out
								.println("Sell a property on a different square in the set first");
					}
				}
			}
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void improve() {
		ArrayList<Color> monopolizedColors = new ArrayList<>();
		for (Color c : Color.values()) {
			if (currentPlayer.isMonopolized(c)) {
				monopolizedColors.add(c);
			}
		}
		boolean buying = ConsoleUI.promptForBool("Buy or sell", "Buy", "Sell");
		if (buying) {
			buyHouses(monopolizedColors);
		} else {
			sellHouses(monopolizedColors);
		}
	}

	private void mortgage() throws InsufficientFundsException {
		ArrayList<String> properties = new ArrayList<>();
		for (OwnableSquare s : currentPlayer.getProperties()) {
			String str = s.getName() + " (";
			str += s.isMortgaged() ? "unmortgage: pay $" : "mortgage: gain $";
			str += s.isMortgaged() ? s.getUnmortgagePrice() : (s.getPrice() / 2);
			str += ")";
			properties.add(str);
		}
		System.out.println("Select a property to mortgage");
		int property = ConsoleUI.promptForMenuSelection(properties.toArray(new String[0]));
		if (property != 0) {
			if (currentPlayer.getProperties()[property - 1].isMortgaged()) {
				if (currentPlayer.getBalance() >= currentPlayer.getProperties()[property - 1]
						.getUnmortgagePrice()) {
					currentPlayer.subtractBalance(
							currentPlayer.getProperties()[property - 1].getUnmortgagePrice());
					currentPlayer.getProperties()[property - 1].unmortgage();
				} else {
					System.out.println("You can't afford to unmortgage that!");
				}
			} else {
				currentPlayer.addBalance(currentPlayer.getProperties()[property - 1].mortgage());
			}
		}
	}

	private void surrender() {
		if (ConsoleUI.promptForBool("Are you sure? y/n", "y", "n")) {
			bankruptPlayer(currentPlayer);
			nextTurn();
		}
	}

	private void propertyInfo() {
		if (currentPlayer.getProperties().length == 0) {
			System.out.println("You don't own any properties");
		}
		for (OwnableSquare property : currentPlayer.getProperties()) {
			System.out.print(property.getName());
			if (property instanceof TitleDeed) {
				TitleDeed deed = (TitleDeed) property;
				System.out.println(" (" + deed.getColor() + ")");
				System.out.print(
						currentPlayer.isMonopolized(deed.getColor()) ? "-MONOPOLIZED\n" : "");
			} else {
				System.out.println();
			}
			System.out.println(" -Rent: "
					+ (property.isMortgaged() ? "Mortgaged" : "$" + property.getRent(null)));
			if (property instanceof TitleDeed) {
				TitleDeed deed = (TitleDeed) property;
				System.out.println(
						" -Houses: " + (deed.getBuildingCount() < 5 ? deed.getBuildingCount() : 0));
				System.out.println(" -Hotels: " + (deed.getBuildingCount() == 5 ? 1 : 0));
				System.out.println(" -Cost to Improve: $" + deed.getBuildingCost());
			}
		}
	}

	private void trade() {
		Player tradingPlayer;
		HashMap<Integer, Player> tradingPlayers = new HashMap<>();
		ArrayList<String> names = new ArrayList<>();
		for (int i = 0, j = 1; i < players.size(); i++, j++) {
			if (players.get(i).equals(currentPlayer)) {
				j--;
				continue;
			}
			tradingPlayers.put(j, players.get(i));
			names.add(players.get(i).getName());
		}
		System.out.println("Enter player to trade with");
		int choice = ConsoleUI.promptForMenuSelection(names.toArray(new String[0]), "Back");
		if (choice == 0) {
			return;
		} else {
			tradingPlayer = tradingPlayers.get(choice);
			new ConsoleTrade(currentPlayer, tradingPlayer).startTrade();
		}
	}

	private TurnChoice printMenu() {
		ArrayList<TurnChoice> menuOptions = TurnChoice.mutableValues();

		if (!currentPlayer.isJailed()) {
			menuOptions.remove(TurnChoice.ESCAPE);
		} else {
			menuOptions.remove(TurnChoice.ROLL);
			if (!jailedThisTurn) {
				menuOptions.remove(TurnChoice.END);
			}
		}

		if (!currentPlayer.hasMonopoly()) {
			menuOptions.remove(TurnChoice.IMPROVE);
		}

		if (currentPlayerHasRolled) {
			menuOptions.remove(TurnChoice.ROLL);
		} else if (!currentPlayerHasRolled) {
			menuOptions.remove(TurnChoice.END);
		}

		return ConsoleUI.promptForMenuSelection(menuOptions.toArray(new TurnChoice[0]), "Give Up");
	}

	private EscapeChoice printEscapeMenu() {
		ArrayList<EscapeChoice> menuOptions = EscapeChoice.mutableValues();

		if (jailedThisTurn || currentPlayerHasRolled) {
			menuOptions.remove(EscapeChoice.ROLL);
		}

		if (!currentPlayer.hasJailBreak()) {
			menuOptions.remove(EscapeChoice.CARD);
		}

		if (currentPlayer.getBalance() < 50) {
			menuOptions.remove(EscapeChoice.PAY);
		}

		return ConsoleUI.promptForMenuSelection(menuOptions.toArray(new EscapeChoice[0]),
				"Return to Main");
	}

	private void printPlayerLocation(Player player) {
		System.out.println(board.getPrintablePlayerLocation(currentPlayer));
	}

	private void playerMove() throws InsufficientFundsException {
		int[] rolls = currentPlayer.roll();
		System.out
				.print(currentPlayer.getName() + " rolled " + rolls[0] + " and " + rolls[1] + ".");
		if (rolls[0] == rolls[1]) {
			System.out.println(" Doubles!");
			doubleCount++;
			if (doubleCount == 3) {
				System.out.println("3 doubles? Jailtime!");
				currentPlayer.setJailed(true);
				for (String s : board.moveTo(currentPlayer, board.getLocation("Jail"), false)) {
					System.out.println(s);
				}
				doubleCount = 0;
				currentPlayerHasRolled = true;
				jailedThisTurn = true;
				return;
			}
		} else {
			System.out.println();
			currentPlayerHasRolled = true;
			doubleCount = 0;
		}
		for (String s : board.movePiece(currentPlayer, rolls[0] + rolls[1])) {
			System.out.println(s);
		}
		if (board.getPieceLocation(currentPlayer.getPiece()) instanceof OwnableSquare) {
			OwnableSquare toBuy = (OwnableSquare) board.getPieceLocation(currentPlayer.getPiece());
			if (!toBuy.isOwned()) {
				if (currentPlayer.getBalance() >= toBuy.getPrice() && ConsoleUI.promptForBool(
						"Buy " + toBuy.getName() + " for $" + toBuy.getPrice() + "? Y/N", "Y",
						"N")) {
					currentPlayer.subtractBalance(toBuy.getPrice());
					currentPlayer.addProperties(toBuy);
					toBuy.setOwnership(currentPlayer);
				} else {
					new ConsoleAuction().startAuction(toBuy, players, currentPlayer);;
				}
			}
		}
	}

	private void getOutOfJail() throws InsufficientFundsException {
		EscapeChoice choice = printEscapeMenu();

		if (choice == null) {
			return;
		}

		switch (choice) {
		case CARD:
			currentPlayer.jailBreak();
			currentPlayer.resetEscapeAttempts();
			break;
		case PAY:
			currentPlayer.subtractBalance(50);
			currentPlayer.setJailed(false);
			currentPlayer.resetEscapeAttempts();
			break;
		case ROLL:
			int[] rolls = currentPlayer.roll();
			currentPlayerHasRolled = true;
			System.out.println("Rolled " + rolls[0] + " and " + rolls[1]);
			if (rolls[0] == rolls[1]) {
				currentPlayer.setJailed(false);
				System.out.println("You did it! Moving " + (rolls[0] + rolls[1]));
				for (String s : board.movePiece(currentPlayer, rolls[0] + rolls[1])) {
					System.out.println(s);
				} ;
				currentPlayer.resetEscapeAttempts();
				return;
			} else {
				currentPlayer.addEscapeAttempt();
				System.out.println("You failed. You have " + (3 - currentPlayer.getEscapeAttempts())
						+ " attempts remaining.");
				return;
			}
		}
		System.out.println("You're out of jail!");
	}

	private void nextTurn() {
		currentPlayerIndex++;
		currentPlayerIndex %= players.size();
		currentPlayer = players.get(currentPlayerIndex);
		currentPlayerHasRolled = false;
		jailedThisTurn = false;
	}

	private void endGame() {
		System.out.println(
				players.get(0).getName() + " wins! Congratulations! The rest of you are bad.");
	}

	private void bankruptPlayer(Player toBankrupt) {
		bankruptPlayer(toBankrupt, null);
	}

	private void bankruptPlayer(Player toBankrupt, Player bankruptingPlayer) {
		players.remove(toBankrupt);
		currentPlayerIndex %= players.size();
		currentPlayer = players.get(currentPlayerIndex);
		currentPlayerHasRolled = false;
		for (OwnableSquare s : toBankrupt) {
			if (s instanceof TitleDeed) {
				for (int i = 0; i < ((TitleDeed) s).getBuildingCount(); i++) {
					toBankrupt.addBalance(((TitleDeed) s).sellBuilding());
				}
			}
			if (bankruptingPlayer == null) {
				s.unmortgage();
				s.setOwnership(null);
				new ConsoleAuction().startAuction(s, players,
						players.get((currentPlayerIndex + 1) % players.size()));;
			} else {
				try {
					if (s.isMortgaged()) {
						System.out.println(bankruptingPlayer.getName()
								+ " must pay 10% tax on mortgaged property " + s.getName());
						bankruptingPlayer.subtractBalance(s.getPrice() / 10);
						if (ConsoleUI.promptForBool(
								"Unmortgage property now? It will cost you an additional $"
										+ s.getPrice() + ". y/n",
								"y", "n")) {
							bankruptingPlayer.subtractBalance(s.getPrice());
							s.unmortgage();
						}
					}
				} catch (InsufficientFundsException e) {
					System.out.println("You can't afford to do that!");
				}
				s.setOwnership(bankruptingPlayer);
			}
		}
		bankruptingPlayer.addBalance(toBankrupt.getBalance());
	}

	private void initGame() {
		System.out.println("Welcome to Console Monopoly!");
		System.out.println("The greatest console based finance game you've ever lost!");
		initPlayers();
	}

	private void initPlayers() {
		int totalPlayers = ConsoleUI.promptForInt("Enter the number of players, up to 8", 2, 8);
		for (int i = 0; i < totalPlayers; i++) {
			String name = ConsoleUI.promptForInput("Enter your name, player " + (i + 1));
			if (playerNames.contains(name)) {
				System.out.println(name + " is already playing!");
				i--;
				continue;
			}
			playerNames.add(name);
		}
		initPieces();
	}

	private void initSecretPlayers() {
		for (Player p : players) {
			if (p.getName().startsWith("!")) {
				String pName = p.getName().substring(1);
				if (pName.equalsIgnoreCase("Monopoly")) {
					p.addProperties((OwnableSquare) board.getLocation("Park Place"),
							(OwnableSquare) board.getLocation("Boardwalk"));
					((OwnableSquare) board.getLocation("Park Place")).setOwnership(p);
					((OwnableSquare) board.getLocation("Boardwalk")).setOwnership(p);
				} else if (pName.equalsIgnoreCase("Jailbait")) {
					p.setJailed(true);
					p.giveJailBreak();
					p.setFalseRoll(true);
					board.moveTo(p, board.getLocation("Jail"), false);
				} else if (pName.equalsIgnoreCase("Broke")) {
					try {
						p.subtractBalance(1499);
					} catch (InsufficientFundsException e) {
						e.printStackTrace();
					}
				} else if (pName.equalsIgnoreCase("Great Britain") || pName.equals("Olga")) {
					for (Square s : board) {
						if (s instanceof OwnableSquare) {
							OwnableSquare deed = (OwnableSquare) s;
							p.addProperties(deed);
							deed.setOwnership(p);
							try {
								if (deed instanceof TitleDeed) {
									((TitleDeed) deed).buyBuilding();
									((TitleDeed) deed).buyBuilding();
									((TitleDeed) deed).buyBuilding();
									((TitleDeed) deed).buyBuilding();
									((TitleDeed) deed).buyBuilding();
								}
							} catch (IllegalArgumentException e) {
							}
						}
					}
				}
			}
		}
	}

	private void initPieces() {
		System.out.println("Rolling to see who gets to pick their piece first...");
		int firstPlayer = new Random().nextInt(playerNames.size());
		System.out.println(playerNames.get(firstPlayer) + " is going first!");
		ArrayList<PieceChoice> choices = new ArrayList<>();
		for (PieceChoice choice : PieceChoice.values()) {
			choices.add(choice);
		}
		for (int i = firstPlayer; i < playerNames.size(); i++) {
			System.out.println("Pick a piece, " + playerNames.get(i));
			PieceChoice choice =
					ConsoleUI.promptForMenuSelection(choices.toArray(new PieceChoice[0]), false);
			players.add(new Player(playerNames.get(i), new Piece(choice.getArt())));
			choices.remove(choice);
			System.out.println(playerNames.get(i) + " chose " + choice.getArt() + "\n");
		}
		for (int i = 0; i < firstPlayer; i++) {
			System.out.println("Pick a piece, " + playerNames.get(i));
			PieceChoice choice =
					ConsoleUI.promptForMenuSelection(choices.toArray(new PieceChoice[0]), false);
			players.add(new Player(playerNames.get(i), new Piece(choice.getArt())));
			choices.remove(choice);
			System.out.println(playerNames.get(i) + " chose " + choice.getArt() + "\n");
		}
	}

	private enum PieceChoice implements MenuOption {
		EYE("(<O>)"),
		SQUEEZY("|>_<|"),
		FATHER("[dad]"),
		PATRICK("/._.\\"),
		NOT_BAD("*@&$!"),
		MINION("~(o)~"),
		DESPACITO("(;-;)"),
		O("|.O.|");

		private final String art;

		private PieceChoice(String art) {
			this.art = art;
		}

		public String getArt() {
			return art;
		}

		public String getDesc() {
			return String.valueOf(this).replace("_", " ") + ": " + art;
		}
	}

	private enum TurnChoice implements MenuOption {
		ROLL("Roll to Move"),
		END("End Turn"),
		ESCAPE("Get Out of Jail"),
		INFO("View Properties"),
		TRADE("Trade with Another Player"),
		IMPROVE("Buy or Sell Improvements"),
		MORTGAGE("Mortgage or Unmortgage Property"),;

		private final String desc;

		private TurnChoice(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static ArrayList<TurnChoice> mutableValues() {
			ArrayList<TurnChoice> values = new ArrayList<>();
			for (TurnChoice t : TurnChoice.values()) {
				values.add(t);
			}
			return values;
		}
	}

	private enum EscapeChoice implements MenuOption {
		ROLL("Roll Doubles to Escape"),
		PAY("Pay $50 to Get Out"),
		CARD("Use a Get Out of Jail Free Card");

		private final String desc;

		private EscapeChoice(String desc) {
			this.desc = desc;
		}

		@Override
		public String getDesc() {
			return desc;
		}

		public static ArrayList<EscapeChoice> mutableValues() {
			ArrayList<EscapeChoice> values = new ArrayList<>();
			for (EscapeChoice e : EscapeChoice.values()) {
				values.add(e);
			}
			return values;
		}
	}
}
