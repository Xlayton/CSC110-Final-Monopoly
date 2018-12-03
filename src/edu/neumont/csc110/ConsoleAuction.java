package edu.neumont.csc110;

import java.util.ArrayList;
import edu.neumont.csc110.game_pieces.Railroad;
import edu.neumont.csc110.game_pieces_abstract.Auction;
import edu.neumont.csc110.game_pieces_abstract.OwnableSquare;
import interfaces.ConsoleUI;
import interfaces.MenuOption;

public class ConsoleAuction extends Auction {
	
	ArrayList<Player> playersInAuction = new ArrayList<>();

	@Override
	public void startAuction(OwnableSquare toAuction, ArrayList<Player> allPlayers,
			Player toStart) {

		int turnPlayerIndex = 0;
		int workingPrice = 0;
		int originalPrice = toAuction.getPrice();

		for (int i = 0; i < allPlayers.size(); i++) {
			playersInAuction.add(allPlayers.get(i));
			if (allPlayers.get(i).equals((Player) toStart)) {
				turnPlayerIndex = i;
			}
		}

		while (playersInAuction.size() > 1) {
			System.out.println(playersInAuction.get(turnPlayerIndex) + " it's your turn!");
			String[] options = new String[] {"Leave Auction", "Bid" };
			int selection = ConsoleUI.promptForMenuSelection(options,false);
			switch(selection) {
			case 1:
				playersInAuction.remove(turnPlayerIndex);
				turnPlayerIndex++;
				turnPlayerIndex %= playersInAuction.size();
				continue;
			case 2:
				workingPrice = doTurn(playersInAuction.get(turnPlayerIndex), workingPrice, originalPrice);
				turnPlayerIndex++;
				turnPlayerIndex %= playersInAuction.size();
				break;
			}
		}
		buyProperty(playersInAuction.get(0), toAuction, workingPrice);
	}

	private int doTurn(Player turnOf, int workingPrice, int originalPrice) {
		boolean isValid = false;
		int toBid = 0;
		
		System.out.println("Original Price: " + originalPrice + "\nCurrent Bid Price: " + workingPrice);
		while(!isValid) {
			try {
				toBid = ConsoleUI.promptForInt("Enter amount to bid" , 1, (turnOf.getBalance() - workingPrice));
				turnOf.subtractBalance(toBid);
			} catch (IllegalArgumentException ex) {
				if(ex.getMessage().equals("Min must be less than max!")) {
					System.out.println("You can no longer afford to bid");
					playersInAuction.remove(turnOf);
					return workingPrice;
				} else {
					System.out.println("You cannot afford to bid that much.");
					continue;
				}
			}
			return (workingPrice + toBid);
		}
		return (workingPrice+toBid);
	}
	
	private void buyProperty(Player toGive, OwnableSquare toBuy, int finalPrice) {
		toGive.addProperties(toBuy);
		toGive.subtractBalance(finalPrice);
	}
}
