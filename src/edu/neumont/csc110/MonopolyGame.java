package edu.neumont.csc110;

import java.util.ArrayList;
import java.util.Random;
import edu.neumont.csc110.game_pieces.Piece;
import interfaces.ConsoleUI;
import interfaces.MenuOption;

public class MonopolyGame {
	private final MonopolyBoard board;

	private Player[] players;
	private ArrayList<String> playerNames;
	private Player currentPlayer;
	private int totalPlayers, currentPlayerIndex;

	public MonopolyGame() {
		playerNames = new ArrayList<>();
		initGame();
		board = new MonopolyBoard(players);
	}

	public void run() {
		// player turn
		// print player location
		// print player balance
		// prompt: trade, roll, jailed? get out of jail, has monopoly? make improvements,
		// 		mortgage/unmortgage, surrender
		// 	trade -
		// 		run trade class
		// 	roll -
		// 		roll the dice and move around the board that many spaces
		// 	get out of jail -
		// 		prompt: pay $50, has not rolled 3 times? roll for doubles, has jailbreak? jailbreak
		//			remove player from jail
		// 	make improvements -
		// 		has buildings to sell? prompt: buy, sell else just buy
		// 	mortgage/unmortgage - 
		//		yea
		// 	surrender
		//		are you sure? y/n
		// 			y -
		//				bankrupt player
		//			n -
		//				go back to main selection
		// land on square -
		//		ownable? - 
		// 			owned? pay rent else prompt if buy
		//			return to main without roll
		//		landedOn() - 
		//			return to main without roll
		// next turn
		
		printPlayerLocation(currentPlayer);
		System.out.println(currentPlayer);
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
		totalPlayers = ConsoleUI.promptForInt("Enter the number of players, up to 8", 2, 8);
		players = new Player[totalPlayers];
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
		int firstPlayer = new Random().nextInt(totalPlayers);
		System.out.println(playerNames.get(firstPlayer) + " is going first!");
		currentPlayerIndex = firstPlayer;
		currentPlayer = players[firstPlayer];
		ArrayList<PieceChoice> choices = new ArrayList<>();
		for (PieceChoice choice : PieceChoice.values()) {
			choices.add(choice);
		}
		for (int i = firstPlayer; i < totalPlayers; i++) {
			System.out.println("Pick a piece, " + playerNames.get(i));
			PieceChoice choice =
					ConsoleUI.promptForMenuSelection(choices.toArray(new PieceChoice[0]), false);
			players[i] = new Player(playerNames.get(i), new Piece(choice.getArt()));
			choices.remove(choice);
		}
		for (int i = 0; i < firstPlayer; i++) {
			System.out.println("Pick a piece, " + playerNames.get(i));
			PieceChoice choice =
					ConsoleUI.promptForMenuSelection(choices.toArray(new PieceChoice[0]), false);
			players[i] = new Player(playerNames.get(i), new Piece(choice.getArt()));
			choices.remove(choice);
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

	public static void main(String[] args) {
		new MonopolyGame();
	}
}
