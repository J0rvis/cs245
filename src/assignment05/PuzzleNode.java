package assignment05;
import java.util.ArrayList;

/**
 * File: PuzzleNode.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 4/29/2017
 * 
 *          Description: The main purpose of the PuzzleNode.java file is to create a
 *          node to be used to solve a 3 X 3 sliding puzzle. Each PuzzleNode has
 *          an int [][] that shows the board's current state, the parent node
 *          that led to the current one, the move that made the node different
 *          from its parent, and the depth of the node (how many moves from its
 *          initial state).
 * 
 */
public class PuzzleNode {
	private int[][] board = new int[3][3]; // make a 3X3 board
	private PuzzleNode parent;
	private String moves;
	private int depth;

	/**
	 * Constructor.
	 *
	 * @param it
	 *            is the item the PuzzleNode will contain
	 **/
	public PuzzleNode(String it) {
		makeBoard(it);
		parent = null;
		moves = "start";
		depth = 0;
	}

	/**
	 * Constructor.
	 *
	 * @param board2
	 *            is the PuzzleNode's current state
	 * @param parent2
	 *            is the node that came before the current PuzzleNode
	 * @param moves2
	 *            is the move that turned the parent node into the current node
	 * @param depth2
	 *            is the depth (or number of nodes) from the current node to its
	 *            initial state
	 **/
	public PuzzleNode(int[][] board2, PuzzleNode parent2, String moves2, int depth2) {
		board = board2;
		parent = parent2;
		moves = moves2;
		depth = depth2;
	}

	/**
	 * Constructor.
	 *
	 **/
	public PuzzleNode() {
		parent = null;
		board = new int[3][3];
		moves = "start";
		depth = 0;
	}

	// getters/ setters

	/**
	 * returns the int [][] board of the current node
	 **/
	public int[][] getBoard() {
		return board;
	}

	/**
	 * Sets the current node's board as specified
	 * 
	 * @param replacement
	 *            int [][] that is to be the specified node's board
	 **/
	public void setBoard(int[][] replacement) {
		board = replacement;
	}

	/**
	 * returns the move of the current node
	 **/
	public String getMoves() {
		return moves;
	}

	/**
	 * Sets the specified node's move
	 * 
	 * @param replacement
	 *            is the new move of the specified node
	 **/
	public void setMoves(String replacement) {
		moves = replacement;
	}

	/**
	 * returns the depth of a node
	 **/
	public int getDepth() {
		return depth;
	}

	/**
	 * Sets the depth of a node
	 * 
	 * @param replacement
	 *            replaces current depth of node with specified depth
	 **/
	public void setDepth(int replacement) {
		depth = replacement;
	}

	/**
	 * returns the current PuzzleNode's parent
	 **/
	public PuzzleNode getParent() {
		return parent;
	}

	/**
	 * Checks to make sure the current node has a parent
	 **/
	public boolean hasParent() {
		return parent != null;
	}

	/**
	 * Sets the parent of the current node
	 * 
	 * @param replacement
	 *            sets parent PuzzleNode to specified PuzzleNode
	 **/

	public void setParent(PuzzleNode replacement) {
		parent = replacement;
	}

	/**
	 * creates an int [][] out of a string
	 * 
	 * @param it
	 *            string to be turned into a int [][] board
	 **/
	public void makeBoard(String it) {
		String[] strArray = it.replaceAll(" ", "").replaceAll(",", "").split("(?!^)");
		int[] intArray = new int[strArray.length];
		for (int i = 0; i < strArray.length; i++) {
			intArray[i] = Integer.parseInt(strArray[i]);
		}

		board[0][0] = intArray[0];
		board[0][1] = intArray[1];
		board[0][2] = intArray[2];

		board[1][0] = intArray[3];
		board[1][1] = intArray[4];
		board[1][2] = intArray[5];

		board[2][0] = intArray[6];
		board[2][1] = intArray[7];
		board[2][2] = intArray[8];

	}

	/**
	 * Turns specified int [][] board into an int [] to be used for easier
	 * comparisons.
	 **/
	public int[] toArray() {// for linear algorithms
		ArrayList<String> list = new ArrayList<String>();
		int[] intArray = new int[9];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				list.add(Integer.toString(board[i][j]));
			}
		}
		for (int k = 0; k < list.size(); k++) {
			intArray[k] = Integer.parseInt(list.get(k));
		}
		return intArray;
	}

	/**
	 * Copies the specified PuzzleNode's int [][] board to avoid just making a
	 * reference to a node's board. (As any changes made to the reference board
	 * may change the original. Using this method avoids this issue.)
	 * 
	 **/
	public int[][] copyBoard() {
		int[][] copy = new int[3][3];
		for (int j = 0; j < board.length; j++) {
			for (int k = 0; k < board[0].length; k++) {
				copy[j][k] = board[j][k];
			}
		}
		return copy;

	}

	/**
	 * Checks to see if the current PuzzleNode's int [][] board's 0 or blank
	 * space can be moved up without going out of bounds.
	 **/
	public boolean hasUp() { // [row][column]
		int[] index = findZero();
		if (index[0] != 0) { // hasUp
			return true;
		}
		return false;

	}

	/**
	 * Checks to see if the current PuzzleNode's int [][] board's 0 or blank
	 * space can be moved down without going out of bounds.
	 **/
	public boolean hasDown() {
		int[] index = findZero();
		if (index[0] != 2) {
			return true;
		}
		return false;

	}

	/**
	 * Checks to see if the current PuzzleNode's int [][] board's 0 or blank
	 * space can be moved left without going out of bounds.
	 **/
	public boolean hasLeft() {
		int[] index = findZero();
		if (index[1] != 0) {
			return true;
		}
		return false;

	}

	/**
	 * Checks to see if the current PuzzleNode's int [][] board's 0 or blank
	 * space can be moved right without going out of bounds.
	 **/
	public boolean hasRight() {
		int[] index = findZero();
		if (index[1] != 2) {
			return true;
		}
		return false;

	}

	/**
	 * Checks the specified PuzzleNode's board to find the coordinates of the
	 * int '0' or blank space. Returns the 'coordinates' in the form of int[0] =
	 * row and int [1] = row.
	 * 
	 * 
	 **/

	public int[] findZero() {
		int index[] = new int[2];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					index[0] = i;
					index[1] = j;
					return index;
				}
			}
		}
		return index;
	}

	/**
	 * Turns the current node into a comprehensible string containing the int
	 * [][] board, depth and moves.
	 * 
	 **/
	public String toString() {
		String b = String.format("%n ") + "Board: " + String.format("%n %d %d %d %n %d %d %d %n %d %d %d", board[0][0],
				board[0][1], board[0][2], board[1][0], board[1][1], board[1][2], board[2][0], board[2][1], board[2][2]);
		String d = "depth = " + depth;
		String m = "move = " + moves;

		return b + String.format("%n") + d + String.format("%n") + m + String.format("%n");

	}

	/**
	 * Turns the current node into a comprehensible string containing the int
	 * [][] board, depth and moves. Used for personal debugging because this
	 * format was easier to use for visualizing the int[][] board as being an
	 * actual slide puzzle. Formatting based off of a debugger from making a Tic
	 * Tac Toe board.
	 * 
	 **/
	// My original toString which I kept because I prefer this formatting (which
	// I learned from Tic Tac Toe homework)
	public String formatToString() {
		String b = String.format("%n%n ") + "Board: "
				+ String.format("%n[%d][%d][%d]%n[%d][%d][%d]%n[%d][%d][%d]", board[0][0], board[0][1], board[0][2],
						board[1][0], board[1][1], board[1][2], board[2][0], board[2][1], board[2][2]);
		String d = "Depth = " + depth;
		String m = "Move = " + moves;

		return b + String.format("%n") + d + String.format("%n") + m;

	}

}
