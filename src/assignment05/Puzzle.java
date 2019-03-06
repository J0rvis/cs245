package assignment05;
import java.util.Scanner;

/**
 * File: Puzzle.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 4/29/2017
 * 
 *          Description: The main purpose of the Puzzle.java file is to create a
 *          user friendly program that inserts PuzzleNodes into a queue list
 *          (BFS) and a stack (DFS) in order to solve an 3 x 3 slide puzzle. It
 *          includes two modes that can be activated through command line
 *          arguments. The verbose mode prints out the searched nodes and the
 *          solution moves. The normal mode just prints out the solution moves.
 * 
 */

public class Puzzle {
	private static String mode;

	/**
	 * Command-line interface. Prompts user for initial state of 3 X 3 board and
	 * runs either the verbose mode or normal mode according to what the user
	 * specifies.
	 * 
	 * @param args
	 *            is the string that will tell the program if it should run in
	 *            verbose mode or normal
	 */
	public static void main(String[] args) { // board [row][column]

		if (args.length < 1 || args[0].length() != 2) {
			System.err.print("Not enough information has been entered to satisfy the parameters of the problem.");
			System.exit(0);

		}

		// Make sure the user understands what a valid initial state is
		Scanner in = new Scanner(System.in);
		System.out.println("Puzzle needs an initial state of 9 numbers to create the starting puzzle board.");
		System.out.println();
		System.out.println(
				"The initial state should be a rearranged verson of the following list of integers: 012345678");
		System.out.println();
		System.out.println("(For example) If you enter: \"123450786\" then you will make the puzzle:");
		PuzzleNode example = new PuzzleNode("123450786");
		System.out.println(example.toString());

		// prompt user for initial state
		System.out.println("Please enter a valid initial state for the puzzle:");
		String str = in.nextLine();

		// check to make sure the numbers entered are valid
		if (!str.matches("[0-8]+") || str.length() < 9 || str.length() > 9 || !findDuplicate(str)) {
			System.out.println("Invalid initial state. Ending Program.");
			System.exit(0);
		}

		if (args[0].equalsIgnoreCase("-v")) {
			mode = "-v";
			searchByBreadth(str);
			searchByDepth(str);
		} else if (args[0].equalsIgnoreCase("-n")) {
			mode = "-n";
			searchByBreadth(str);
			searchByDepth(str);
		}

	}

	/**
	 * Creates a PuzzleNode with the given string and inserts it into a queue.
	 * Expands possible movements of blank space as it attempts to find a
	 * solution within a node depth of 10. Based off of the Binary Search Tree
	 * method called printLevelOrder() in the sense that it walks through the
	 * queue by level rather than depth. Contains the modes for verbose BSF and
	 * normal BFS.
	 * 
	 * @param str
	 *            string used to create initial state of the 3 X 3 board
	 * 
	 **/
	public static void searchByBreadth(String str) {
		String moves = "";

		if (mode.equals("-v")) {
			System.out.println();
			System.out.println("BFS*******************************");
			System.out.println("Positions tried from start to finish.");

			PuzzleNode test = new PuzzleNode(str);
			PuzzleNode original = new PuzzleNode(str);
			QueueList<PuzzleNode> q = new QueueList<PuzzleNode>();

			q.enqueue(original);
			PuzzleNode node = null;
			boolean solved = false;
			while (!q.isEmpty()) {
				node = q.dequeue();
				System.out.println(node.toString());

				if (isSolved(node)) {
					solved = true;
					break;
				}

				// go up if you can
				// depth must be less than ten to avoid an infinite loop
				if (node.hasUp() && node.getDepth() < 10) {
					// make copy of board so it's not just a reference
					// search for the zero position on board

					int[][] copy = copyBoard(node);
					int[] index = searchZero(node);

					copy[index[0]][index[1]] = copy[index[0] - 1][index[1]];
					copy[index[0] - 1][index[1]] = 0;
					String move = "U ";

					test = new PuzzleNode(copy, node, move, node.getDepth() + 1);
					q.enqueue(test);

				}

				if (node.hasRight() && node.getDepth() < 10) {
					int[][] copy = copyBoard(node);
					int[] index = searchZero(node);

					copy[index[0]][index[1]] = copy[index[0]][index[1] + 1];
					copy[index[0]][index[1] + 1] = 0;
					String move = "R ";

					test = new PuzzleNode(copy, node, move, node.getDepth() + 1);
					q.enqueue(test);

				}
				if (node.hasDown() && node.getDepth() < 10) {
					int[][] copy = copyBoard(node);
					int[] index = searchZero(node);

					copy[index[0]][index[1]] = copy[index[0] + 1][index[1]];
					copy[index[0] + 1][index[1]] = 0;
					String move = "D ";

					test = new PuzzleNode(copy, node, move, node.getDepth() + 1);
					q.enqueue(test);

				}
				if (node.hasLeft() && node.getDepth() < 10) {
					int[][] copy = copyBoard(node);
					int[] index = searchZero(node);

					copy[index[0]][index[1]] = copy[index[0]][index[1] - 1];
					copy[index[0]][index[1] - 1] = 0;
					String move = "L ";

					test = new PuzzleNode(copy, node, move, node.getDepth() + 1);
					q.enqueue(test);

				}

			}

			if (solved) {
				System.out.println("Path to goal.");
				moves = "";
				while (node.hasParent()) {
					moves = node.getMoves() + " " + moves;
					node = node.getParent();
				}

				System.out.println("Moves to from start to goal: " + moves.replaceAll("start", ""));
			} else {
				moves = "0";
				System.out.println("Couldn't find solution. ");
			}

		} else if (mode.equals("-n")) {
			System.out.println();
			System.out.println("BFS*******************************");
			PuzzleNode test = new PuzzleNode(str);
			PuzzleNode original = new PuzzleNode(str);
			QueueList<PuzzleNode> q = new QueueList<PuzzleNode>();

			q.enqueue(original);
			PuzzleNode node = null;
			boolean solved = false;
			while (!q.isEmpty()) {
				node = q.dequeue();

				if (isSolved(node)) {
					solved = true;
					break;
				}

				// go up if you can
				if (node.hasUp() && node.getDepth() < 10) {
					int[][] copy = copyBoard(node);
					int[] index = searchZero(node);

					copy[index[0]][index[1]] = copy[index[0] - 1][index[1]];
					copy[index[0] - 1][index[1]] = 0;
					String move = "U ";

					test = new PuzzleNode(copy, node, move, node.getDepth() + 1);
					q.enqueue(test);

				}
				if (node.hasRight() && node.getDepth() < 10) {
					int[][] copy = copyBoard(node);
					int[] index = searchZero(node);

					copy[index[0]][index[1]] = copy[index[0]][index[1] + 1];
					copy[index[0]][index[1] + 1] = 0;
					String move = "R ";

					test = new PuzzleNode(copy, node, move, node.getDepth() + 1);
					q.enqueue(test);

				}
				if (node.hasDown() && node.getDepth() < 10) {
					int[][] copy = copyBoard(node);
					int[] index = searchZero(node);

					copy[index[0]][index[1]] = copy[index[0] + 1][index[1]];
					copy[index[0] + 1][index[1]] = 0;
					String move = "D ";

					test = new PuzzleNode(copy, node, move, node.getDepth() + 1);
					q.enqueue(test);

				}
				if (node.hasLeft() && node.getDepth() < 10) {
					int[][] copy = copyBoard(node);
					int[] index = searchZero(node);

					copy[index[0]][index[1]] = copy[index[0]][index[1] - 1];
					copy[index[0]][index[1] - 1] = 0;
					String move = "L ";

					test = new PuzzleNode(copy, node, move, node.getDepth() + 1);
					q.enqueue(test);

				}

			}

			if (solved) {
				System.out.println("Path to Goal.");
				moves = "";
				while (node.hasParent()) {
					moves = node.getMoves() + " " + moves;
					node = node.getParent();
				}

				System.out.println("Moves to from start to goal: " + moves.replaceAll("start", ""));
			} else {
				moves = "0";
				System.out.println("Couldn't find solution. ");
			}

		}

	}

	/**
	 * Creates a PuzzleNode with the given string and inserts it into a stack.
	 * Expands possible movements of blank space as it attempts to find a
	 * solution within a node depth of 10. Based off of the Binary Search Tree
	 * method called printByDepth() in the sense that it walks through the stack
	 * by depth rather than level. Contains the modes for verbose DSF and normal
	 * DFS.
	 * 
	 * @param str
	 *            string used to create initial state of the 3 X 3 board
	 * 
	 **/

	public static void searchByDepth(String str) {
		String moves = "";
		// VERBOSE NODE
		if (mode.equals("-v")) {
			System.out.println();
			System.out.println();
			System.out.println("DFS*******************************");
			System.out.println("Positions tried from start to finish.");

			// make r
			PuzzleNode test = new PuzzleNode(str);
			PuzzleNode original = new PuzzleNode(str);
			PuzzleNode n = null;
			boolean solved = false;

			StackList<PuzzleNode> s = new StackList<PuzzleNode>();
			s.push(original);

			while (!s.isEmpty()) {
				n = s.pop();
				System.out.println(n.toString() + " ");

				if (isSolved(n)) {
					solved = true;
					break;
				}

				if (n.hasLeft() && n.getDepth() < 10) {
					int[][] copy = copyBoard(n);
					int[] index = searchZero(n);

					copy[index[0]][index[1]] = copy[index[0]][index[1] - 1];
					copy[index[0]][index[1] - 1] = 0;
					String move = "L ";

					test = new PuzzleNode(copy, n, move, n.getDepth() + 1);

				}

				if (n.hasDown() && n.getDepth() < 10) {
					int[][] copy = copyBoard(n);
					int[] index = searchZero(n);

					copy[index[0]][index[1]] = copy[index[0] + 1][index[1]];
					copy[index[0] + 1][index[1]] = 0;
					String move = "D ";

					test = new PuzzleNode(copy, n, move, n.getDepth() + 1);
					s.push(test);

				}

				if (n.hasRight() && n.getDepth() < 10) {
					int[][] copy = copyBoard(n);
					int[] index = searchZero(n);

					copy[index[0]][index[1]] = copy[index[0]][index[1] + 1];
					copy[index[0]][index[1] + 1] = 0;
					String move = "R ";

					test = new PuzzleNode(copy, n, move, n.getDepth() + 1);
					s.push(test);

				}

				if (n.hasUp() && n.getDepth() < 10) {
					int[][] copy = copyBoard(n);
					int[] index = searchZero(n);

					copy[index[0]][index[1]] = copy[index[0] - 1][index[1]];
					copy[index[0] - 1][index[1]] = 0;
					String move = "U ";

					test = new PuzzleNode(copy, n, move, n.getDepth() + 1);
					s.push(test);

				}

			}

			if (solved) {
				System.out.println("Path to Goal.");
				moves = "";
				while (n.hasParent()) {
					moves = n.getMoves() + " " + moves;
					n = n.getParent();
				}

				System.out.println("Moves to from start to goal: " + moves);
			} else {
				moves = "0";
				System.out.println("Couldn't find solution. ");
			}

		}

		// NORMAL MODE
		else if (mode.equals("-n")) {
			// DFS uses a stack
			System.out.println();
			System.out.println();
			System.out.println("DFS*******************************");
			

			// make r
			PuzzleNode test = new PuzzleNode(str);
			PuzzleNode original = new PuzzleNode(str);
			PuzzleNode n = null;
			boolean solved = false;

			StackList<PuzzleNode> s = new StackList<PuzzleNode>();
			s.push(original);

			while (!s.isEmpty()) {
				n = s.pop();

				if (isSolved(n)) {
					solved = true;
					break;
				}

				if (n.hasLeft() && n.getDepth() < 10) {
					int[][] copy = copyBoard(n);
					int[] index = searchZero(n);

					copy[index[0]][index[1]] = copy[index[0]][index[1] - 1];
					copy[index[0]][index[1] - 1] = 0;
					String move = "L ";

					test = new PuzzleNode(copy, n, move, n.getDepth() + 1);

				}

				if (n.hasDown() && n.getDepth() < 10) {
					int[][] copy = copyBoard(n);
					int[] index = searchZero(n);

					copy[index[0]][index[1]] = copy[index[0] + 1][index[1]];
					copy[index[0] + 1][index[1]] = 0;
					String move = "D ";

					test = new PuzzleNode(copy, n, move, n.getDepth() + 1);
					s.push(test);

				}

				if (n.hasRight() && n.getDepth() < 10) {
					int[][] copy = copyBoard(n);
					int[] index = searchZero(n);

					copy[index[0]][index[1]] = copy[index[0]][index[1] + 1];
					copy[index[0]][index[1] + 1] = 0;
					String move = "R ";

					test = new PuzzleNode(copy, n, move, n.getDepth() + 1);
					s.push(test);

				}

				if (n.hasUp() && n.getDepth() < 10) {
					int[][] copy = copyBoard(n);
					int[] index = searchZero(n);

					copy[index[0]][index[1]] = copy[index[0] - 1][index[1]];
					copy[index[0] - 1][index[1]] = 0;
					String move = "U ";

					test = new PuzzleNode(copy, n, move, n.getDepth() + 1);
					s.push(test);

				}
			}

			if (solved) {
				System.out.println("Path to Goal.");

				moves = "";
				while (n.hasParent()) {
					moves = n.getMoves() + " " + moves;
					n = n.getParent();
				}

				System.out.println("Moves to from start to goal: " + moves);
			} else {
				moves = "0";
				System.out.println("Couldn't find solution. ");
			}
		}

	}

	/**
	 * Checks the specified PuzzleNode's board to see if it is the same as the
	 * solution board.
	 * 
	 * @param node
	 *            PuzzleNode to be checked for a win
	 * 
	 **/

	public static boolean isSolved(PuzzleNode node) { // works
		int[] copy = node.toArray();
		int[] win = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
		for (int i = 0; i < win.length; i++) {
			if (copy[i] != win[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Copies the specified PuzzleNode's int [][] board to avoid just making a
	 * reference to a node's board. (As any changes made to the reference board
	 * may change the original. Using this method avoids this issue.)
	 * 
	 * @param node
	 *            PuzzleNode that contains the board to be copied
	 * 
	 **/
	public static int[][] copyBoard(PuzzleNode node) {

		int[][] copy = new int[3][3];
		int[][] board = node.getBoard();
		for (int j = 0; j < board.length; j++) {
			for (int k = 0; k < board[0].length; k++) {
				copy[j][k] = board[j][k];
			}
		}
		return copy;

	}

	/**
	 * Checks the specified PuzzleNode's board to find the coordinates of the
	 * int '0' or blank space. Returns the 'coordinates' in the form of int[0] =
	 * row and int [1] = row.
	 * 
	 * @param node
	 *            PuzzleNode to be checked for the int 0
	 * 
	 **/
	public static int[] searchZero(PuzzleNode node) {
		int[][] board = node.getBoard();
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
	 * Used to make sure that the initial state that the user inputs doesn't
	 * contain duplicate numbers as that would make it an invalid initial state.
	 * 
	 * @param s
	 *            String that is iterated through to find duplicate chars
	 * 
	 **/
	public static boolean findDuplicate(String s) {
		for (int i = 0; i < s.length(); i++) {

			for (int j = 0; j < s.length(); j++) {

				if (s.charAt(i) == s.charAt(j) && i != j) {
					return false;
				}
			}
		}
		return true;
	}
}
