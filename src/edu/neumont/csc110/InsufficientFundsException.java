package edu.neumont.csc110;

public class InsufficientFundsException extends RuntimeException {
	private static final long serialVersionUID = 8881535800372883962L;

	private final Player bankruptingPlayer;
	private final int originalCost, amountOver;

	public InsufficientFundsException(String message, Player bankruptingPlayer, int originalCost, int amountOver) {
		super(message);
		this.bankruptingPlayer = bankruptingPlayer;
		this.originalCost = originalCost;
		this.amountOver = amountOver;
	}

	public Player getBankruptingPlayer() {
		return bankruptingPlayer;
	}

	public int getOriginalCost() {
		return originalCost;
	}
	
	public int getAmountOver() {
		return amountOver;
	}
}
