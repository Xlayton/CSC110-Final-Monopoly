package edu.neumont.csc110;

import java.util.ArrayList;
import java.util.Random;
import edu.neumont.csc110.game_pieces.Piece;
import edu.neumont.csc110.game_pieces.TitleDeed;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;
import interfaces.ConsoleUI;
import interfaces.MenuOption;

public class MonopolyGame {
	private final MonopolyBoard board;

	private ArrayList<Player> players;
	private ArrayList<String> playerNames;
	private Player currentPlayer;
	private int currentPlayerIndex;
	private boolean currentPlayerHasRolled;

	public MonopolyGame() {
		players = new ArrayList<>();
		playerNames = new ArrayList<>();
		initGame();
		currentPlayerIndex = 0;
		currentPlayer = players.get(currentPlayerIndex);
		currentPlayerHasRolled = false;
		board = new MonopolyBoard(players.toArray(new Player[0]));
	}

	public void run() {
		// prompt: trade, view, roll, jailed? get out of jail, has monopoly? make improvements,
		// mortgage/unmortgage, surrender
		// trade -
		// run trade class
		// view -
		// displays all the player's ownables
		// roll -
		// roll the dice and move around the board that many spaces
		// get out of jail -
		// prompt: pay $50, has not rolled 3 times? roll for doubles, has jailbreak? jailbreak
		// remove player from jail
		// make improvements -
		// has buildings to sell? prompt: buy, sell else just buy
		// mortgage/unmortgage -
		// yea
		// surrender
		// are you sure? y/n
		// y -
		// bankrupt player
		// n -
		// go back to main selection
		// land on square -
		// ownable? -
		// owned? pay rent else prompt if buy
		// return to main without roll
		// landedOn() -
		// return to main without roll
		// next turn

		boolean gameRunning = true;

		do {
			printPlayerLocation(currentPlayer);
			System.out.println(currentPlayer);

			TurnChoice choice = printMenu();
			if (choice == null) {
				nextTurn();
			} else {
				switch (choice) {
				case ESCAPE:
					break;
				case IMPROVE:
					break;
				case MORTGAGE:
					break;
				case ROLL:
					playerMove();
					break;
				case TRADE:
					break;
				case SURRENDER:
					if (ConsoleUI.promptForBool("Are you sure? y/n", "y", "n")) {
						bankruptPlayer(currentPlayer);
						if (players.size() == 1) {
							endGame();
							gameRunning = false;
						}
						nextTurn();
						continue;
					}
					break;
				}
			}
		} while (gameRunning);
	}

	private void playerMove() {
		int distance = currentPlayer.roll();
		System.out.println(currentPlayer.getName() + " rolled a " + distance);
		for (String s : board.movePiece(currentPlayer, distance)) {
			System.out.println(s);
		}
		currentPlayerHasRolled = true;
	}

	private void endGame() {}

	private void nextTurn() {
		currentPlayerIndex++;
		if (currentPlayerIndex == players.size()) {
			currentPlayerIndex = 0;
		}
		currentPlayer = players.get(currentPlayerIndex);
		currentPlayerHasRolled = false;
	}

	private void bankruptPlayer(Player toBankrupt) {
		bankruptPlayer(toBankrupt, null);
	}

	private void bankruptPlayer(Player toBankrupt, Player bankruptingPlayer) {
		for (OwnableSquare s : toBankrupt) {
			if (s instanceof TitleDeed) {
				for (int i = 0; i < ((TitleDeed) s).getBuildingCount(); i++) {
					toBankrupt.addBalance(((TitleDeed) s).sellBuilding());;
				}
			}
			if (bankruptingPlayer == null) {
				s.unmortgage();
				s.setOwnership(null);
				auction(s);
			} else {
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
				s.setOwnership(bankruptingPlayer);
				bankruptingPlayer.addBalance(bankruptingPlayer.getBalance());
			}
		}
		players.remove(toBankrupt);
	}

	private void auction(OwnableSquare s) {}

	private TurnChoice printMenu() {
		ArrayList<TurnChoice> menuOptions = TurnChoice.mutableValues();

		if (!currentPlayer.isJailed()) {
			menuOptions.remove(TurnChoice.ESCAPE);
		}

		if (!currentPlayer.hasMonopoly()) {
			menuOptions.remove(TurnChoice.IMPROVE);
		}
		
		if (currentPlayerHasRolled) {
			menuOptions.remove(TurnChoice.ROLL);
		}

		if (currentPlayerHasRolled) {
			return ConsoleUI.promptForMenuSelection(menuOptions.toArray(new TurnChoice[0]), "End Turn");
		} else {
			return ConsoleUI.promptForMenuSelection(menuOptions.toArray(new TurnChoice[0]), false);
		}
	}

	private void printPlayerLocation(Player player) {
		System.out.println(board.getPrintablePlayerLocation(currentPlayer));
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
		TRADE("Trade with Another Player"),
		ESCAPE("Get out of Jail"),
		IMPROVE("Buy or sell improvements"),
		MORTGAGE("Mortgage or unmortgage property"),
		SURRENDER("Give Up");

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
}
