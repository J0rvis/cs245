package assignment01;
import javax.swing.JOptionPane;

/**
 * 
 * @author Hannah Borreson
 * 
 * @version 02/08/2017
 * 
 * 
 */

public class PlayingCard implements Comparable<PlayingCard> {
	/*
	 * Created two variables that I will repeatedly use throughout the following
	 * methods. One is an int that keeps track of the card rank and the other is
	 * a char that keeps track of the card suit.
	 */
	private int cardRank;
	private char cardSuit;

	/**
	 * Constructs a single playing card given the card rank and suit in the
	 * parameters. If either rank or suit is invalid, a warning is output and an
	 * ace of spades is created.
	 */

	public PlayingCard(int cardRank, char cardSuit) {
		if (cardRank >= 1 && cardRank <= 13
				&& (cardSuit != 'C' || cardSuit != 'S' || cardSuit != 'H' || cardSuit != 'D')) {
			this.cardRank = cardRank;
			this.cardSuit = cardSuit;
		} else {
			System.err.print("This card rank doesn't exist.");
			cardRank = 1;
			cardSuit = 'S';
		}

	}

	/**
	 * Returns a positive integer if this card has a lower rank than the other
	 * card, a negative integer if this card has a higher rank than the other
	 * card, or 0 if this card's rank is the same as the other card's rank.
	 */

	@Override
	public int compareTo(PlayingCard othercard) {
		if (cardRank < othercard.getRank()) {
			return 1;
		} else if (cardRank > othercard.getRank()) {
			return -1;
		} else {
			return 0;
		}

	}

	/**
	 * Gets the image file name for this card. The image file name for a card
	 * consists of its rank followed by its suit followed by the suffix ".png".
	 * For example: the Ace of Spades would have a file name of "1S.png", the 7
	 * of Diamonds would have a file name of "7D.png" and the Jack of Hearts
	 * would have a file name of "11H.png".
	 */

	public String getImageFileName() {
		return "" + getRank() + getSuit() + ".png";

	}

	/**
	 * Gets the rank of this card as an integer between 1 and 13 (1 is Ace, 11
	 * is Jack, 12 is Queen, 13 is King).
	 */

	public int getRank() {
		int rank = this.cardRank;
		return rank;

	}

	/**
	 * Gets the suit of this card in the type char. ('C' is Clubs, 'S' is
	 * Spades, 'H' is Hearts, 'D' is Diamonds).
	 */

	public char getSuit() {
		char suit = this.cardSuit;
		return suit;
	}

	/**
	 * Returns the string representation of this card including its rank and
	 * suit.
	 */
	public String toString() {
		/*
		 * 'Integer.toString()' turns the collected rank into a String so that
		 * it can be paired with the suit as a string.
		 */
		return Integer.toString(getRank()) + getSuit();
	}

	// WRITE YOUR FIELDS, METHODS AND JAVADOC COMMENTS HERE:

}
