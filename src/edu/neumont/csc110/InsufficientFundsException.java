package edu.neumont.csc110;

public class InsufficientFundsException extends RuntimeException {
	private static final long serialVersionUID = 8881535800372883962L;

	private final Player bankruptingPlayer;
	private final int amountOver;

	public InsufficientFundsException(String message, Player bankruptingPlayer, int amountOver) {
		this.bankruptingPlayer = bankruptingPlayer;
		this.amountOver = amountOver;
	}

	public Player getBankruptingPlayer() {
		return bankruptingPlayer;
	}

	public int getAmountOver() {
		return amountOver;
	}
}
