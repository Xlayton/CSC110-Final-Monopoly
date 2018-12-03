package edu.neumont.csc110;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import edu.neumont.csc110.game_pieces_abstract.Card;

public class Deck implements Iterable<Card> {
	private final ArrayList<Card> my_cards;
/**
 * 
 * @param initialCards - 
 */
	public Deck(Card... initialCards) {
		my_cards = new ArrayList<>();
		for (Card c : initialCards) {
			my_cards.add(c);
		}
	}
/**
 * 
 * @param card - 
 */
	public void add(Card card) {
		my_cards.add(card);
	}

	public void add(Card... cards) {
		for (Card card : cards) {
			add(card);
		}
	}

	public void remove(Card toRemove) {
		my_cards.remove(toRemove);
	}

	public void remove(Card... toRemove) {
		for (Card card : toRemove) {
			remove(card);
		}
	}

	public Card draw() {
		Card result = my_cards.get(my_cards.size() - 1);
		my_cards.remove(result);
		return result;
	}

	public Deck draw(int numberToDraw) {
		if (numberToDraw < 1) {
			throw new IllegalArgumentException("Must draw at least 1 card!");
		}

		Deck deck = new Deck();

		for (int i = 0; i < numberToDraw; i++) {
			deck.add(this.draw());
		}

		return deck;
	}

	public boolean isEmpty() {
		return (my_cards.size() == 0);
	}

	public void shuffle() {
		for (int i = 0; i < my_cards.size(); i++) {
			Card temp = my_cards.get(i);
			int randIndex = new Random().nextInt(my_cards.size());

			my_cards.set(i, my_cards.get(randIndex));
			my_cards.set(randIndex, temp);
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(my_cards.toArray(new Card[0]));
	}

	@Override
	public Iterator<Card> iterator() {
		return my_cards.iterator();
	}
}
