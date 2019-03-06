package assignment01;
import java.util.*;

/**
 * 
 * @author Hannah Borreson
 * 
 * @version 02/08/2017
 * 
 * 
 */

public class GameController {

	// DO NOT CHANGE OR ADD FIELDS BELOW.
	// YOU MUST USE THESE FIELDS TO IMPLEMENT YOUR GAME.

	private ArrayList<PlayingCard> deck;
	private ArrayList<PlayingCard>[] list;
	private ArrayList<PlayingCard> discardPile;
	private int playerScore;

	// WRITE YOUR CODE AND JAVADOC HERE:

	/**
	 * Creates a controller to play the Aces Up game. The controller creates a
	 * deck of cards as an arraylist, four empty arraylists for the four card
	 * lists of the game, and an arraylist for the discard pile.
	 */

	public GameController() {
		deck = new ArrayList<PlayingCard>();
		for (int i = 1; i <= 13; i++) {
			deck.add(new PlayingCard(i, 'S'));
			deck.add(new PlayingCard(i, 'C'));
			deck.add(new PlayingCard(i, 'H'));
			deck.add(new PlayingCard(i, 'D'));
		}
		Collections.shuffle(deck);

		discardPile = new ArrayList<PlayingCard>();
		list = new ArrayList[4];
		for (int i = 0; i < 4; i++) {
			list[i] = new ArrayList();
		}

		this.playerScore = 0;
	}

	/**
	 * Adds a card from the deck to the end of each of the card lists if the
	 * deck is not empty. Otherwise, do nothing.
	 */

	public void deal() {
		if (!deck.isEmpty()) {
			list[0].add(deck.remove(0));
			list[1].add(deck.remove(0));
			list[2].add(deck.remove(0));
			list[3].add(deck.remove(0));
		}
		System.out.println("Deck: " + deck.toString());
		System.out.println("list0: " + list[0].toString());
		System.out.println("list1: " + list[1].toString());
		System.out.println("list2: " + list[2].toString());
		System.out.println("list3: " + list[3].toString());
		System.out.println("discard: " + discardPile.toString());
		/*
		 * Since dealing, moving, and discarding is an action taken by the gamer
		 * we print out all arrays and arraylists for debugging purposes. This
		 * happens throughout the program to update the dubugger on the cards
		 * hidden in the different lists.
		 */
	}

	/**
	 * Moves the last card from the given list to the discard pile if the list
	 * is not empty and the last card has a rank that is greater than the last
	 * card on another list with the same suit. Add the discarded card’s rank to
	 * the score. Otherwise, do nothing.
	 * 
	 * @param listnum
	 *            Is one of the four list numbers ranging from 0-3
	 */

	public void discard(int listNum) {
		/*
		 * If this list isn't empty check each other list if it is empty and the
		 * suit and rank are valid to discard.
		 */
		if (!list[listNum].isEmpty() && ((!list[(listNum + 1) % 4].isEmpty()
				&& list[listNum].get(list[listNum].size() - 1).getSuit() == list[(listNum + 1) % 4]
						.get(list[(listNum + 1) % 4].size() - 1).getSuit()
				&& list[listNum].get(list[listNum].size() - 1)
						.compareTo(list[(listNum + 1) % 4].get(list[(listNum + 1) % 4].size() - 1)) == -1)
				|| (!list[(listNum + 2) % 4].isEmpty()
						&& list[listNum].get(list[listNum].size() - 1).getSuit() == list[(listNum + 2) % 4]
								.get(list[(listNum + 2) % 4].size() - 1).getSuit()
						&& list[listNum].get(list[listNum].size() - 1)
								.compareTo(list[(listNum + 2) % 4].get(list[(listNum + 2) % 4].size() - 1)) == -1)
				|| (!list[(listNum + 3) % 4].isEmpty()
						&& list[listNum].get(list[listNum].size() - 1).getSuit() == list[(listNum + 3) % 4]
								.get(list[(listNum + 3) % 4].size() - 1).getSuit()
						&& list[listNum].get(list[listNum].size() - 1)
								.compareTo(list[(listNum + 3) % 4].get(list[(listNum + 3) % 4].size() - 1)) == -1))) {
			playerScore += list[listNum].get(list[listNum].size() - 1).getRank();
			discardPile.add(list[listNum].remove(list[listNum].size() - 1));

			System.out.println("Deck: " + deck.toString());
			System.out.println("list0: " + list[0].toString());
			System.out.println("list1: " + list[1].toString());
			System.out.println("list2: " + list[2].toString());
			System.out.println("list3: " + list[3].toString());
			System.out.println("discard: " + discardPile.toString());
		}
		/*
		 * Can be discarded if there is a card of a lesser rank but same suit in
		 * a different list.
		 */
	}

	/**
	 * Gets the card in the specified list number at the given index. If the
	 * given list is empty, this method returns null. If the given index is
	 * invalid for the given list, this method returns null.
	 * 
	 * @param listnum
	 *            Is one of the four list numbers ranging from 0-3
	 * @param index
	 *            Is the point in the array that the specified card is at
	 */
	public PlayingCard getCard(int listNum, int index) {
		if (index < 0 || index > list[listNum].size() - 1) {
			return null;
		} else if (list[listNum].isEmpty()) {
			return null;
		} else {
			PlayingCard getMe = list[listNum].get(index);
			return getMe;
		}
	}

	/** Gets the current score. */
	public int getScore() {
		return playerScore;

	}

	/**
	 * This method wasn't included in the game requirements but was a helper
	 * method I created to simplify more moves. It iterates through the lists
	 * and checks to see if the last card in the list can be discarded accordig
	 * to the 'discard' rules listed in the homework requirements. If it can be
	 * discarded the method returns true and if not it returns false.
	 */

	public boolean canDiscard() {
		for (int i = 0; i < 4; i++) {
			if (!list[i].isEmpty() && ((!list[(i + 1) % 4].isEmpty()
					&& list[i].get(list[i].size() - 1).getSuit() == list[(i + 1) % 4].get(list[(i + 1) % 4].size() - 1)
							.getSuit()
					&& list[i].get(list[i].size() - 1)
							.compareTo(list[(i + 1) % 4].get(list[(i + 1) % 4].size() - 1)) == -1)
					|| (!list[(i + 2) % 4].isEmpty()
							&& list[i].get(list[i].size() - 1).getSuit() == list[(i + 2) % 4]
									.get(list[(i + 2) % 4].size() - 1).getSuit()
							&& list[i].get(list[i].size() - 1)
									.compareTo(list[(i + 2) % 4].get(list[(i + 2) % 4].size() - 1)) == -1)
					|| (!list[(i + 3) % 4].isEmpty()
							&& list[i].get(list[i].size() - 1).getSuit() == list[(i + 3) % 4]
									.get(list[(i + 3) % 4].size() - 1).getSuit()
							&& list[i].get(list[i].size() - 1)
									.compareTo(list[(i + 3) % 4].get(list[(i + 3) % 4].size() - 1)) == -1))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ckecks to see if there are more legal moves in the game to be made.
	 * Returns true if there are more legal moves that can be made. If there are
	 * more cards in the deck, then there are more legal moves. If there are no
	 * more cards in the deck, but if one of the four lists is empty, there are
	 * more legal moves. If there are no more cards in the deck, but there is a
	 * last card on a list that can be discarded, then there are more legal
	 * moves. Otherwise return false if there are no more legal moves.
	 */

	public boolean moreMoves() {

		if (!deck.isEmpty()) {
			return true;
		} else if (deck.isEmpty()
				&& (list[0].isEmpty() || list[1].isEmpty() || list[2].isEmpty() || list[3].isEmpty())) {
			return true;

		} else if (deck.isEmpty() && canDiscard()) {
			return true;
		}
		return false;

	}

	/**
	 * Move the last card from the given list (if the list is not empty) to an
	 * empty list if one exists. Otherwise, do nothing.
	 * 
	 * @param listnum
	 *            Is one of the four list numbers ranging from 0-3
	 */

	public void move(int listNum) {
		if (!list[listNum].isEmpty()) {
			if (listNum != 0 && list[0].isEmpty()) {
				list[0].add(list[listNum].get(list[listNum].size() - 1));
				list[listNum].remove(list[listNum].size() - 1);
				System.out.println("Deck: " + deck.toString());
				System.out.println("list0: " + list[0].toString());
				System.out.println("list1: " + list[1].toString());
				System.out.println("list2: " + list[2].toString());
				System.out.println("list3: " + list[3].toString());
				System.out.println("discard: " + discardPile.toString());

			} else if (listNum != 1 && list[1].isEmpty()) {
				list[1].add(list[listNum].get(list[listNum].size() - 1));
				list[listNum].remove(list[listNum].size() - 1);
				System.out.println("Deck: " + deck.toString());
				System.out.println("list0: " + list[0].toString());
				System.out.println("list1: " + list[1].toString());
				System.out.println("list2: " + list[2].toString());
				System.out.println("list3: " + list[3].toString());
				System.out.println("discard: " + discardPile.toString());

			} else if (listNum != 2 && list[2].isEmpty()) {
				list[2].add(list[listNum].get(list[listNum].size() - 1));
				list[listNum].remove(list[listNum].size() - 1);
				System.out.println("Deck: " + deck.toString());
				System.out.println("list0: " + list[0].toString());
				System.out.println("list1: " + list[1].toString());
				System.out.println("list2: " + list[2].toString());
				System.out.println("list3: " + list[3].toString());
				System.out.println("discard: " + discardPile.toString());

			} else if (listNum != 3 && list[3].isEmpty()) {
				list[3].add(list[listNum].get(list[listNum].size() - 1));
				list[listNum].remove(list[listNum].size() - 1);
				System.out.println("Deck: " + deck.toString());
				System.out.println("list0: " + list[0].toString());
				System.out.println("list1: " + list[1].toString());
				System.out.println("list2: " + list[2].toString());
				System.out.println("list3: " + list[3].toString());
				System.out.println("discard: " + discardPile.toString());
			}
		}
	}

	/**
	 * Moves all cards from the lists and discard pile back into the deck of
	 * cards, shuffles the deck and starts a new game.
	 */

	public void startNewGame() {
		while (!list[0].isEmpty()) {
			deck.add(list[0].remove(0));
		}
		while (!list[1].isEmpty()) {
			deck.add(list[1].remove(0));
		}
		while (!list[2].isEmpty()) {
			deck.add(list[2].remove(0));
		}
		while (!list[3].isEmpty()) {
			deck.add(list[3].remove(0));
		}
		while (!discardPile.isEmpty()) {
			deck.add(discardPile.remove(0));
		}
		Collections.shuffle(deck);
		System.out.println("Deck: " + deck.toString());
		System.out.println("list0: " + list[0].toString());
		System.out.println("list1: " + list[1].toString());
		System.out.println("list2: " + list[2].toString());
		System.out.println("list3: " + list[3].toString());
		System.out.println("discard: " + discardPile.toString());
		playerScore = 0;
		/* Even though starting a new game isn't an in-game action the deck,
		 lists, and discard pile are still printed out to make sure that the
		 lists and decks are returned to a default so a new game can be
		 started.*/
	}

	/**
	 * Returns all true if the deck is empty and all the other lists have one
	 * card and that card is an ace
	 */
	public boolean win() {
		if (deck.isEmpty() && list[1].size() == 1 && list[2].size() == 1 && list[3].size() == 1 && list[4].size() == 1
				&& list[1].get(1).getRank() == 1 && list[2].get(1).getRank() == 1 && list[3].get(1).getRank() == 1
				&& list[4].get(1).getRank() == 1) {
			return true;
		}

		return false;

	}

}