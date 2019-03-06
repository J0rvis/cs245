package assignment02;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * File: TTTPanel.java
 * 
 * @author Heather Amthauer and Hannah Borreson
 * 
 * @version 2/22/2017
 * 
 *          Description: This class makes the tic-tac-toe panel The user is
 *          allowed to select a one-player version or a two-player version.
 *          One-player version is not implemented Two-player version is turn
 *          based. First X marks a square then O moves.
 */

public class TTTPanel extends JPanel {
	private String turn; // keep track if it is X or O's turn
	private boolean onePlay; // keep track of one player or two player

	private JPanel topPanel; // panel to hold radio buttons and info
	private JRadioButton onePlayer; // for user to select if they want to play
									// against the computer
	private JRadioButton twoPlayer; // for user to select if they want to play
									// against another human
	private ButtonGroup group; // for mutual exclusion of player selection
	private JLabel info; // to communicate instructions to user

	private JPanel panel; // panel for buttons for squares

	private JButton upperR; // upper right corner
	private JButton upperM; // upper middle
	private JButton upperL; // upper left corner
	private JButton middleR; // middle right
	private JButton middleM; // middle of board
	private JButton middleL; // middle left
	private JButton lowerR; // lower right corner
	private JButton lowerM; // lower middle
	private JButton lowerL; // lower left corner

	private JButton reset; // button to reset board

	private Font font1 = new Font("Courier", Font.BOLD, 50);// so X and O are

	// variables and panels to gather name information and place it on boar
	private JPanel bigPanel; //north panel that holds namesPanel and topPanel
	private JPanel namesPanel; // new panel for names
	private JTextField inPlayer1; //playerX's text field
	private JTextField inPlayer2; //playerO's text field
	private JLabel isPlayer1; //shows text field is for playerX
	private JLabel isPlayer2; //shows text field is for playerO
	private JButton submit; //submit button submits player names
	private String playerX;//keeps track of playerX's name
	private String playerO; //keeps track of playerO's name

	// int 2d array for mapping out the board: 0 = empty, 2 = player x (user),
	// 1 = player O (computer)
	private int[][] board;

	// makes sure that the correct winner gets recognition
	private boolean win;

	// Colors that are easier to read
	private Color fuchsia; //info color
	private Color forestGreen; //playerO's name color

	/**
	 * Constructor that initializes all the components for GUI
	 *
	 */
	public TTTPanel() {
		// creates a board to represent the TTT game buttons
		board = new int[3][3];

		// boolean win starts at false because no one has won yet
		win = false;
		// set the layout for the panel that will hold all the game components
		setLayout(new BorderLayout());
		// make radio buttons for player options
		onePlayer = new JRadioButton("One Player");
		twoPlayer = new JRadioButton("Two Player");

		// make the radio buttons mutual exclusive
		group = new ButtonGroup();
		group.add(onePlayer);
		group.add(twoPlayer);

		// Add item listeners to radio buttons so we can tell when they have
		// been selected
		ItemListener oneplay = new RadioOnePlayer();
		onePlayer.addItemListener(oneplay);
		ItemListener twoplay = new RadioTwoPlayer();
		twoPlayer.addItemListener(twoplay);

		// initialize top panel that will hold radio buttons
		topPanel = new JPanel();

		// set the layout so things will be added horizontally
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		topPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		// add radio buttons
		topPanel.add(onePlayer);
		topPanel.add(twoPlayer);

		// Initialize info and make the text fuchsia
		info = new JLabel("Select number of players");
		Color fuchsia = new Color(225, 0, 128);
		Color forestGreen = new Color(34, 139, 34);
		info.setForeground(fuchsia);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));

		// add the info
		topPanel.add(info);

		// Initialize the name panel
		namesPanel = new JPanel();
		namesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		namesPanel.setLayout(new BoxLayout(namesPanel, BoxLayout.LINE_AXIS));

		// red text asking for player X name is invisible until correct radio
		// button is clicked
		isPlayer1 = new JLabel(" Player X is: ");
		isPlayer1.setForeground(Color.red);
		namesPanel.add(isPlayer1);
		isPlayer1.setVisible(false);

		// editable text box for player X is invisible until correct radio
		// button is clicked
		inPlayer1 = new JTextField();
		namesPanel.add(inPlayer1);
		isPlayer1.setVisible(false);

		// green text asking for player O name is invisible until correct radio
		// button is clicked
		isPlayer2 = new JLabel(" Player O is: ");
		isPlayer2.setForeground(forestGreen);
		namesPanel.add(isPlayer2);
		isPlayer2.setVisible(false);

		// editable text box for player O is invisible until correct radio
		// button is clicked
		inPlayer2 = new JTextField();
		namesPanel.add(inPlayer2);
		inPlayer2.setVisible(false);

		// submits the names is invisible until correct radio button is clicked
		submit = new JButton("Submit");

		ActionListener submitName = new SubmitAction();
		submit.addActionListener(submitName);

		namesPanel.add(submit);
		submit.setVisible(false);

		
		bigPanel = new JPanel();
		bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.Y_AXIS));
		bigPanel.add(topPanel);
		
		add(bigPanel, BorderLayout.NORTH);
		bigPanel.setVisible(true);

		// initialize the reset button
		reset = new JButton("Reset");

		// action listener for reset
		ActionListener resetBoard = new ResetAction();
		reset.addActionListener(resetBoard);

		// add reset button to bottom of the TTT panel
		add(reset, BorderLayout.SOUTH);

		// action listeners for all the squares
		ActionListener takeSquare = new TakeSquareAction();

		// add square buttons in 3x3 grid
		panel = new JPanel();
		// this sets the layout to 3x3 with a 5 pixel border
		// that goes around the grid cells
		panel.setLayout(new GridLayout(3, 3, 5, 5));

		// for all buttons, set them to blank text
		// add action listener then
		// add to panel
		upperL = new JButton(" ");
		upperL.setFont(font1);
		upperL.addActionListener(takeSquare);
		panel.add(upperL);

		upperM = new JButton(" ");
		upperM.setFont(font1);
		upperM.addActionListener(takeSquare);
		panel.add(upperM);

		upperR = new JButton(" ");
		upperR.setFont(font1);
		upperR.addActionListener(takeSquare);
		panel.add(upperR);

		middleL = new JButton(" ");
		middleL.setFont(font1);
		middleL.addActionListener(takeSquare);
		panel.add(middleL);

		middleM = new JButton(" ");
		middleM.setFont(font1);
		middleM.addActionListener(takeSquare);
		panel.add(middleM);

		middleR = new JButton(" ");
		middleR.setFont(font1);
		middleR.addActionListener(takeSquare);
		panel.add(middleR);

		lowerL = new JButton(" ");
		lowerL.setFont(font1);
		lowerL.addActionListener(takeSquare);
		panel.add(lowerL);

		lowerM = new JButton(" ");
		lowerM.setFont(font1);
		lowerM.addActionListener(takeSquare);
		panel.add(lowerM);

		lowerR = new JButton(" ");
		lowerR.setFont(font1);
		lowerR.addActionListener(takeSquare);
		panel.add(lowerR);

		// add the grid panel to the center of the TTTPanel
		add(panel, BorderLayout.CENTER);
		disableAllButtons();// make sure the user selects one or two player
							// first
	}

	/**
	 * This class will reset the text for all the buttons to a blank and enable
	 * them all again so they can be clicked
	 */
	private class ResetAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			// set text to blank for all buttons and color to white
			upperL.setText(" ");
			upperL.setBackground(Color.WHITE);

			upperM.setText(" ");
			upperM.setBackground(Color.WHITE);

			upperR.setText(" ");
			upperR.setBackground(Color.WHITE);

			middleL.setText(" ");
			middleL.setBackground(Color.WHITE);

			middleM.setText(" ");
			middleM.setBackground(Color.WHITE);

			middleR.setText(" ");
			middleR.setBackground(Color.WHITE);

			lowerL.setText(" ");
			lowerL.setBackground(Color.WHITE);

			lowerM.setText(" ");
			lowerM.setBackground(Color.WHITE);

			lowerR.setText(" ");
			lowerR.setBackground(Color.WHITE);

			// clear all of the player options
			group.clearSelection();

			// enable player options
			onePlayer.setEnabled(true);
			twoPlayer.setEnabled(true);

			// set name text editable
			inPlayer1.setEditable(true);
			inPlayer2.setEditable(true);

			// remove from bigPanel
			bigPanel.remove(namesPanel);

			// cover up ask for player X name
			inPlayer1.setVisible(false);
			isPlayer1.setVisible(false);
			submit.setVisible(false);

			// cover up ask for player O name
			inPlayer2.setVisible(false);
			isPlayer2.setVisible(false);
			submit.setVisible(false);

			// new people have new names
			playerX = "";
			playerO = "";

			// reset text fields to blank and enable them for new names
			inPlayer1.setText("");
			inPlayer1.setEnabled(true);
			inPlayer2.setText("");
			inPlayer2.setEnabled(true);
			submit.setEnabled(true);
			// set info text
			info.setText("Select number of players.");

			// reset the int board for the player 1 logic
			resetBoard();

			// for a new game set win to false because no one has won yet
			win = false;

			// make it so the user has to select player option first disable
			// buttons
			disableAllButtons();

		}

	}

	/**
	 * RadioOnePlayer class sets the game up for one player
	 */
	private class RadioOnePlayer implements ItemListener {
		public void itemStateChanged(ItemEvent e) {

			// add player X ask
			bigPanel.add(namesPanel);

			// ask for names
			inPlayer1.setVisible(true);
			isPlayer1.setVisible(true);
			bigPanel.setVisible(true);
			submit.setVisible(true);

			// set onePlay
			onePlay = true;

			// set the turn to X
			turn = "X";

			// disable two player option
			twoPlayer.setEnabled(false);
			// you don't want people to switch versions.
			onePlayer.setEnabled(false);

			// set info to say it's Your turn then start the one player game
			info.setText("Please enter your name without using spaces. ");

		}
	}

	/**
	 * RadioTwoPlayer class sets the game up for two players It will be turn
	 * based. X is first.
	 */
	private class RadioTwoPlayer implements ItemListener {
		public void itemStateChanged(ItemEvent e) {

			// add player names ask
			bigPanel.add(namesPanel);

			// ask for player X name
			inPlayer1.setVisible(true);
			isPlayer1.setVisible(true);
			bigPanel.setVisible(true);
			submit.setVisible(true);

			// ask for player O name
			inPlayer2.setVisible(true);
			isPlayer2.setVisible(true);
			bigPanel.setVisible(true);
			submit.setVisible(true);

			// set the turn to X
			turn = "X";
			// set onePlay to false
			onePlay = false;

			// disable one player option
			onePlayer.setEnabled(false);

			info.setText("Please enter names without using spaces. ");

		}
	}

	/**
	 * TakeSquareAction class When square buttons are clicked - it will mark the
	 * square appropriately. Appropriate moves are based on if it is two player
	 * or one player.
	 */

	private class TakeSquareAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// two player option
			if (!onePlay) {
				// get the button that was pressed
				JButton b = (JButton) event.getSource();

				// X's turn
				if (turn.equals("X")) {
					// set the button text to X
					b.setText("x");
					b.setBackground(Color.RED);
					// disable the button
					b.setEnabled(false);

					if (b.equals(upperL)) {
						board[0][0] = 1;
					}
					if (b.equals(upperM)) {
						board[0][1] = 1;
					}
					if (b.equals(upperR)) {
						board[0][2] = 1;
					}
					if (b.equals(middleL)) {
						board[1][0] = 1;
					}
					if (b.equals(middleM)) {
						board[1][1] = 1;
					}
					if (b.equals(middleR)) {
						board[1][2] = 1;
					}
					if (b.equals(lowerL)) {
						board[2][0] = 1;
					}
					if (b.equals(lowerM)) {
						board[2][1] = 1;
					}
					if (b.equals(lowerR)) {
						board[2][2] = 1;
					}

					// check if it won the game
					if (checkForWin()) {
						info.setText("" + playerX + " wins :\'( ");
						disableAllButtons();
					} else if (checkForDraw()) {
						info.setText("Draw, you both suck.");
						disableAllButtons();
					} else {
						turn = "O";
						info.setText("It\'s " + playerO + "\'s turn.");
						debug();
					}
					// else check for a draw
					// if game is not over - it is O's turn

				}
				// O's turn
				else {
					// set the button text to O
					b.setText("O");
					b.setBackground(Color.GREEN);
					// disable the button
					b.setEnabled(false);
					// check if it won the game

					/*
					 * sets the element that corresponds to the pressed button
					 * to 2 in order to represent that it was taken by player x
					 */
					if (b.equals(upperL)) {
						board[0][0] = 2;
					}
					if (b.equals(upperM)) {
						board[0][1] = 2;
					}
					if (b.equals(upperR)) {
						board[0][2] = 2;
					}
					if (b.equals(middleL)) {
						board[1][0] = 2;
					}
					if (b.equals(middleM)) {
						board[1][1] = 2;
					}
					if (b.equals(middleR)) {
						board[1][2] = 2;
					}
					if (b.equals(lowerL)) {
						board[2][0] = 2;
					}
					if (b.equals(lowerM)) {
						board[2][1] = 2;
					}
					if (b.equals(lowerR)) {
						board[2][2] = 2;
					}

					// set the button text to O
					// disable the button
					// check if it won the game
					// else check for a draw
					// if game is not over - it is X's turn

					debug();
					if (checkForWin()) {
						info.setText("" + playerO + " wins :'( ");
						disableAllButtons();
					} else if (checkForDraw()) {
						info.setText("Draw, you both suck.");
						disableAllButtons();
					} else {
						turn = "X";
						info.setText("It\'s " + playerX + "\'s turn.");
					}
					// else check for a draw
					// if game is not over - it is O's turn

				}

			}

			// one player option
			else {
				JButton b = (JButton) event.getSource();

				b.setText("x");
				b.setBackground(Color.RED);
				// disable the button
				b.setEnabled(false);

				if (b.equals(upperL)) {
					board[0][0] = 1;
				} else if (b.equals(upperM)) {
					board[0][1] = 1;
				} else if (b.equals(upperR)) {
					board[0][2] = 1;
				} else if (b.equals(middleL)) {
					board[1][0] = 1;
				} else if (b.equals(middleM)) {
					board[1][1] = 1;
				} else if (b.equals(middleR)) {
					board[1][2] = 1;
				} else if (b.equals(lowerL)) {
					board[2][0] = 1;
				} else if (b.equals(lowerM)) {
					board[2][1] = 1;
				} else if (b.equals(lowerR)) {
					board[2][2] = 1;
				}
				debug();

				// check if it won the game
				if (checkForWin() && !win) {
					info.setText("" + playerX + " wins :\'( ");
					win = true;
					disableAllButtons();
					return;
				} else if (checkForDraw()) {
					info.setText("Draw, you both suck.");
					disableAllButtons();
				}

				// LOGIC
				// if corner is taken... take middle
				
				else if ((board[0][0] == 1 || board[0][2] == 1 || board[2][0] == 1 || board[2][2] == 1)
						&& board[1][1] == 0) {
					board[1][1] = 2;
					middleM.setText("O");
					middleM.setBackground(Color.GREEN);
					middleM.setEnabled(false);
					info.setText("It\'s " + playerX + "\'s turn");

					// if bot can win... then try to win...
					// takes wins for rows from left or column 2
				} else if (((board[0][0] == 2 && board[0][1] == 2) || (board[2][2] == 2 && board[1][2] == 2))
						&& board[0][2] == 0) {
					upperR.setText("O");
					upperR.setBackground(Color.GREEN);
					upperR.setEnabled(false);

					board[0][2] = 2;
					info.setText("It\'s " + playerX + "'s turn");

				} else if (((board[1][0] == 2 && board[1][1] == 2) || (board[0][2] == 2 && board[2][2] == 2))
						&& board[1][2] == 0) {
					middleR.setText("O");
					middleR.setBackground(Color.GREEN);
					middleR.setEnabled(false);

					board[1][2] = 2;
					info.setText("It\'s " + playerX + "'s turn");

				} else if (((board[2][0] == 2 && board[2][1] == 2) || (board[0][2] == 2 && board[1][2] == 2))
						&& board[2][2] == 0) {
					lowerR.setText("O");
					lowerR.setBackground(Color.GREEN);
					lowerR.setEnabled(false);

					board[2][2] = 2;
					info.setText("It\'s " + playerX + "'s turn");

					// takes wins in rows from right or column 0
				} else if (((board[0][1] == 2 && board[0][2] == 2) || (board[1][0] == 2 && board[2][0] == 2))
						&& board[0][0] == 0) {
					upperL.setText("O");
					upperL.setBackground(Color.GREEN);
					upperL.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[0][0] = 2;

				} else if (((board[1][1] == 2 && board[1][2] == 2) || (board[0][0] == 2 && board[2][0] == 2))
						&& board[1][0] == 0) {
					middleL.setText("O");
					middleL.setBackground(Color.GREEN);
					middleL.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[1][0] = 2;

				} else if (((board[2][1] == 2 && board[2][2] == 2) || (board[0][0] == 2 && board[1][0] == 2))
						&& board[2][0] == 0) {
					lowerL.setText("O");
					lowerL.setBackground(Color.GREEN);
					lowerL.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[2][0] = 2;

					// takes wins where bot has ends of rows or for column 1
				} else if (((board[0][0] == 2 && board[0][2] == 2) || (board[1][1] == 2 && board[2][1] == 2))
						&& board[0][1] == 0) {
					upperM.setText("O");
					upperM.setBackground(Color.GREEN);
					upperM.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[0][1] = 2;

				} else if (((board[1][0] == 2 && board[1][2] == 2) || (board[0][1] == 2 && board[2][1] == 2))
						&& board[1][1] == 0) {
					middleM.setText("O");
					middleM.setBackground(Color.GREEN);
					middleM.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[1][1] = 2;

				} else if (((board[2][0] == 2 && board[2][2] == 2) || (board[0][1] == 2 && board[1][1] == 2))
						&& board[2][1] == 0) {
					lowerM.setText("O");
					lowerM.setBackground(Color.GREEN);
					lowerM.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[2][1] = 2;

					// Major Defense... if they can win I take their block
					// blocks wins for rows from left or column 2
				} else if (((board[0][0] == 1 && board[0][1] == 1) || (board[2][2] == 1 && board[1][2] == 1))
						&& board[0][2] == 0) {
					upperR.setText("O");
					upperR.setBackground(Color.GREEN);
					upperR.setEnabled(false);

					board[0][2] = 2;
					info.setText("It\'s " + playerX + "'s turn");

				} else if (((board[1][0] == 1 && board[1][1] == 1) || (board[0][2] == 1 && board[2][2] == 1))
						&& board[1][2] == 0) {
					middleR.setText("O");
					middleR.setBackground(Color.GREEN);
					middleR.setEnabled(false);

					board[1][2] = 2;
					info.setText("It\'s " + playerX + "'s turn");

				} else if (((board[2][0] == 1 && board[2][1] == 1) || (board[0][2] == 1 && board[1][2] == 1))
						&& board[2][2] == 0) {
					lowerR.setText("O");
					lowerR.setBackground(Color.GREEN);
					lowerR.setEnabled(false);

					board[2][2] = 2;
					info.setText("It\'s " + playerX + "'s turn");

					// blocks wins in rows from right or column 0
				} else if (((board[0][1] == 1 && board[0][2] == 1) || (board[1][0] == 1 && board[2][0] == 1))
						&& board[0][0] == 0) {
					upperL.setText("O");
					upperL.setBackground(Color.GREEN);
					upperL.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[0][0] = 2;

				} else if (((board[1][1] == 1 && board[1][2] == 1) || (board[0][0] == 1 && board[2][0] == 1))
						&& board[1][0] == 0) {
					middleL.setText("O");
					middleL.setBackground(Color.GREEN);
					middleL.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[1][0] = 2;

				} else if (((board[2][1] == 1 && board[2][2] == 1) || (board[0][0] == 1 && board[1][0] == 1))
						&& board[2][0] == 0) {
					lowerL.setText("O");
					lowerL.setBackground(Color.GREEN);
					lowerL.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[2][0] = 2;

					// blocks wins where bot has ends of rows or for column 1
				} else if (((board[0][0] == 1 && board[0][2] == 1) || (board[1][1] == 1 && board[2][1] == 1))
						&& board[0][1] == 0) {
					upperM.setText("O");
					upperM.setBackground(Color.GREEN);
					upperM.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[0][1] = 2;

				} else if (((board[1][0] == 1 && board[1][2] == 1) || (board[0][1] == 1 && board[2][1] == 1))
						&& board[1][1] == 0) {
					middleM.setText("O");
					middleM.setBackground(Color.GREEN);
					middleM.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[1][1] = 2;

				} else if (((board[2][0] == 1 && board[2][2] == 1) || (board[0][1] == 1 && board[1][1] == 1))
						&& board[2][1] == 0) {
					lowerM.setText("O");
					lowerM.setBackground(Color.GREEN);
					lowerM.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[2][1] = 2;
					
					//take middle
				} else if (board[1][1] == 0) {
					middleM.setText("O");
					middleM.setBackground(Color.GREEN);
					middleM.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[1][1] = 2;

					// places moves in corners if possible
				} else if ((board[0][1] == 1 || board[1][0] == 1 || board[1][1] == 1) && board[0][0] == 0) {
					upperL.setText("O");
					upperL.setBackground(Color.GREEN);
					upperL.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[0][0] = 2;

				} else if ((board[0][1] == 1 || board[1][2] == 1 || board[1][1] == 1) && board[0][2] == 0) {
					upperR.setText("O");
					upperR.setBackground(Color.GREEN);
					upperR.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[0][2] = 2;

				} else if ((board[1][0] == 1 || board[2][1] == 1 || board[1][1] == 1) && board[2][0] == 0) {
					lowerL.setText("O");
					lowerL.setBackground(Color.GREEN);
					lowerL.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[2][0] = 2;

				} else if ((board[2][1] == 1 || board[1][2] == 1 || board[1][1] == 1) && board[2][2] == 0) {
					lowerR.setText("O");
					lowerR.setBackground(Color.GREEN);
					lowerR.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[2][2] = 2;
					
				//if they are at a corner or middle take a side	
				} else if((board[0][0] == 1 || board[0][2] == 1 || board[1][1] == 1) && board[0][1] == 0){
					upperM.setText("O");
					upperM.setBackground(Color.GREEN);
					upperM.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[0][1] = 2;
					
				} else if((board[0][0] == 1 || board[2][0] == 1 || board[1][1] == 1) && board[1][0] == 0){
					middleL.setText("O");
					middleL.setBackground(Color.GREEN);
					middleL.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[1][0] = 2;
					
				} else if((board[2][0] == 1 || board[2][2] == 1 || board[1][1] == 1) && board[2][1] == 0){
					lowerM.setText("O");
					lowerM.setBackground(Color.GREEN);
					lowerM.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[2][1] = 2;
					
				} else if((board[0][2] == 1 || board[2][2] == 1 || board[1][1] == 1) && board[1][2] == 0){
					middleR.setText("O");
					middleR.setBackground(Color.GREEN);
					middleR.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[2][1] = 2;
					
				//set to corners next to a player O button
				} else if ((board[0][1] == 2 || board[1][0] == 2 || board[1][1] == 2) && board[0][0] == 0) {
					upperL.setText("O");
					upperL.setBackground(Color.GREEN);
					upperL.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[0][0] = 2;

				} else if ((board[0][1] == 2 || board[1][2] == 2 || board[1][1] == 2) && board[0][2] == 0) {
					upperR.setText("O");
					upperR.setBackground(Color.GREEN);
					upperR.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[0][2] = 2;

				} else if ((board[0][1] == 2 || board[2][1] == 2 || board[1][1] == 2) && board[2][0] == 0) {
					lowerL.setText("O");
					lowerL.setBackground(Color.GREEN);
					lowerL.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[2][0] = 2;

				} else if ((board[2][1] == 2 || board[1][2] == 2 || board[1][1] == 2) && board[2][2] == 0) {
					lowerR.setText("O");
					lowerR.setBackground(Color.GREEN);
					lowerR.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[2][2] = 2;

					//if you can take a middle side take a middle side
				} else if(board[0][1] == 0){
					upperM.setText("O");
					upperM.setBackground(Color.GREEN);
					upperM.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[0][1] = 2;
					
				} else if(board[1][0] == 0){
					middleL.setText("O");
					middleL.setBackground(Color.GREEN);
					middleL.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[1][0] = 2;
					
				} else if(board[2][1] == 0){
					lowerM.setText("O");
					lowerM.setBackground(Color.GREEN);
					lowerM.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[2][1] = 2;
					
				} else if(board[1][2] == 0){
					middleR.setText("O");
					middleR.setBackground(Color.GREEN);
					middleR.setEnabled(false);

					info.setText("It\'s " + playerX + "'s turn");
					board[2][1] = 2;
				}

				// checking for a win and don't have to say who's turn because
				// it's a one player option
				if (checkForWin() && !win) {
					info.setText("Bot wins :'( ");
					win = true;
					disableAllButtons();
				} else if (checkForDraw()) {
					info.setText("Draw, you both suck.");
					disableAllButtons();
				} else {
					debug();

				}
			}

		}

	}

	/**
	 * checkForWin This goes through all the options for a three in a row win
	 * 
	 * @return true if there are three X's or three O's in a row, otherwise
	 *         false
	 */
	public boolean checkForWin() {
		if (((upperR.getText().equals(upperM.getText())) && (upperR.getText().equals(upperL.getText()))
				&& (!upperR.getText().equals(" ")))
				|| ((middleR.getText().equals(middleM.getText())) && (middleR.getText().equals(middleL.getText()))
						&& (!middleR.getText().equals(" ")))
				|| ((lowerR.getText().equals(lowerM.getText())) && (lowerR.getText().equals(lowerL.getText()))
						&& (!lowerR.getText().equals(" ")))
				|| ((upperR.getText().equals(middleR.getText())) && (upperR.getText().equals(lowerR.getText()))
						&& (!upperR.getText().equals(" ")))
				|| ((upperM.getText().equals(middleM.getText())) && (upperM.getText().equals(lowerM.getText()))
						&& (!upperM.getText().equals(" ")))
				|| ((upperL.getText().equals(middleL.getText())) && (upperL.getText().equals(lowerL.getText()))
						&& (!upperL.getText().equals(" ")))
				|| ((upperR.getText().equals(middleM.getText())) && (upperR.getText().equals(lowerL.getText()))
						&& (!upperR.getText().equals(" ")))
				|| ((upperL.getText().equals(middleM.getText())) && (upperL.getText().equals(lowerR.getText()))
						&& (!upperL.getText().equals(" ")))) {
			return true;
		} else
			return false;

	}

	/**
	 * checkForDraw This goes through and sees if all the squares are not blank.
	 * If all the squares are marked then it is a draw
	 * 
	 * @return true if none of the squares are blank.
	 */
	public boolean checkForDraw() {
		if (!upperR.getText().equals(" ") && !upperM.getText().equals(" ") && !upperL.getText().equals(" ")
				&& !middleR.getText().equals(" ") && !middleM.getText().equals(" ") && !middleL.getText().equals(" ")
				&& !lowerR.getText().equals(" ") && !lowerM.getText().equals(" ") && !lowerL.getText().equals(" ")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * disableAllButtons Sets all of the square buttons to false so the user
	 * cannot click them
	 */
	public void disableAllButtons() {
		upperR.setEnabled(false);
		upperM.setEnabled(false);
		upperL.setEnabled(false);
		middleR.setEnabled(false);
		middleM.setEnabled(false);
		middleL.setEnabled(false);
		lowerR.setEnabled(false);
		lowerM.setEnabled(false);
		lowerL.setEnabled(false);
	}

	/**
	 * startGame Enables all of the square buttons
	 */
	public void startGame() {
		upperR.setEnabled(true);
		upperM.setEnabled(true);
		upperL.setEnabled(true);
		middleR.setEnabled(true);
		middleM.setEnabled(true);
		middleL.setEnabled(true);
		lowerR.setEnabled(true);
		lowerM.setEnabled(true);
		lowerL.setEnabled(true);
		turn = "X";
		resetBoard();
	}

	/**
	 * SubmitAction makes sure that the submitted name is valid and existent
	 * before allowing the game to start.
	 */
	private class SubmitAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (inPlayer1.isVisible() && inPlayer2.isVisible()) {
				playerX = inPlayer1.getText();
				playerO = inPlayer2.getText();

				// makes it so players can't change name
				inPlayer1.setEditable(false);
				inPlayer2.setEditable(false);
				submit.setEnabled(false);
				info.setText("It's " + playerX + "\'s turn.");

				if (playerX.isEmpty() || playerX.contains(" ")) {
					JOptionPane.showMessageDialog(null, "The name entered for Player X is not a valid name. Try again.",
							"Warning", JOptionPane.ERROR_MESSAGE);
					playerX = "";
					playerO = "";

					/*
					 * makes them rewrite their name and allows them to do just
					 * that by setting name to editable
					 */
					inPlayer1.setText("");
					inPlayer1.setEditable(true);
					submit.setEnabled(true);
					info.setText("Please enter names without using spaces. ");

				}
				if (playerO.isEmpty() || playerO.contains(" ")) {
					JOptionPane.showMessageDialog(null, "The name entered for Player O is not a valid name. Try again.",
							"Warning", JOptionPane.ERROR_MESSAGE);
					playerX = "";
					playerO = "";

					/*
					 * makes them rewrite their name and allows them to do just
					 * that by setting name to editable
					 */
					inPlayer2.setText("");
					inPlayer2.setEditable(true);
					submit.setEnabled(true);
					info.setText("Please enter names without using spaces. ");

				}
				if (!playerX.isEmpty() && !playerX.contains(" ") && !playerO.isEmpty() && !playerO.contains(" ")) {
					resetBoard();
					startGame();
				}

			} else if (inPlayer1.isVisible() && !inPlayer2.isVisible()) {
				// names player
				playerX = inPlayer1.getText();

				// makes sure player can't change their name
				inPlayer1.setEditable(false);
				submit.setEnabled(false);
				info.setText("It's " + playerX + "\'s turn.");
				resetBoard();

				if (playerX.isEmpty() || playerX.contains(" ")) {
					JOptionPane.showMessageDialog(null, "The name entered for Player X is not a valid name. Try again.",
							"Warning", JOptionPane.ERROR_MESSAGE);
					playerX = "";
					playerO = "";

					/*
					 * makes them rewrite their name and allows them to do just
					 * that by setting name to editable
					 */
					inPlayer1.setText("");
					inPlayer1.setEditable(true);
					submit.setEnabled(true);
					info.setText("Please enter names without using spaces. ");

				}
				if (!playerX.isEmpty() && !playerX.contains(" ")) {
					resetBoard();
					startGame();
				}
			}

		}

	}

	/**
	 * The resetBoard method sets the 2d array 'board' to 0 at every element in
	 * order to represent that none of the buttons have been pressed yet.
	 */
	public void resetBoard() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				board[r][c] = 0;
			}
		}
	}

	/**
	 * This method prints out every element of the representational 2d 'board'
	 * array. This allows anyone testing the program to see that the 2d array
	 * was being updated appropriately at each move
	 */

	/*
	 * Normally this method would be removed along with the debug calls
	 * throughout this game but they were left for further debugging and
	 * assessments.
	 */
	public void debug() {
		System.out.print(String.format("[%d][%d][%d]%n[%d][%d][%d]%n[%d][%d][%d]%n%n", board[0][0], board[0][1],
				board[0][2], board[1][0], board[1][1], board[1][2], board[2][0], board[2][1], board[2][2]));
	}

}
