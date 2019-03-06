package assignment01;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author Hannah Borreson
 * 
 * @version 02/08/2017
 * 
 * 
 */

public class GamePanel extends JPanel {

	// DO NOT CHANGE, REMOVE OR ADD ANY CODE GIVEN IN THIS CLASS
	// EXCEPT THE FILEPREFIX IF NECESSARY

	private JButton[] discardButton;
	private JButton[] moveButton;
	private JButton newGameButton;
	private JButton dealButton;
	private JLabel scoreLabel1, scoreLabel2;
	private JPanel controlPanel, cardPanel;
	private GameController game;
	private JLabel[][] iconArray;
	private String fileprefix;

	/**
	 * This constructor puts the game's graphics together so that it can
	 * resemble a playing panel. It creates the needed JButtons for the moves,
	 * discards, and deal4 along with other buttons. It sets up the JLabels in
	 * order to create playing card icons used for the visual side of the game.
	 */

	public GamePanel(GameController controller) {

		game = controller;
		game.startNewGame();

		fileprefix = "images\\";

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		controlPanel = new JPanel(new GridLayout(4, 3));

		discardButton = new JButton[4];
		moveButton = new JButton[4];

		// Each button gets a listener object associated with it
		// If the user clicks on the button, the listener invokes
		// the actionPerformed method below.
		ButtonListener listener = new ButtonListener();

		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				newGameButton = new JButton("NEW GAME");
				newGameButton.setBackground(Color.LIGHT_GRAY);
				newGameButton.setForeground(Color.RED);
				newGameButton.addActionListener(listener);
				controlPanel.add(newGameButton);
				break;
			case 1:
				dealButton = new JButton("DEAL 4");
				dealButton.setBackground(Color.LIGHT_GRAY);
				dealButton.addActionListener(listener);
				controlPanel.add(dealButton);
				break;
			case 2:
				scoreLabel1 = new JLabel("  SCORE:");
				controlPanel.add(scoreLabel1);
				break;
			case 3:
				scoreLabel2 = new JLabel("  0");
				controlPanel.add(scoreLabel2);
			}

			discardButton[i] = new JButton("DISCARD");
			discardButton[i].setBackground(Color.LIGHT_GRAY);
			discardButton[i].addActionListener(listener);
			controlPanel.add(discardButton[i]);

			moveButton[i] = new JButton("MOVE");
			moveButton[i].addActionListener(listener);
			controlPanel.add(moveButton[i]);
		}

		add(controlPanel);

		cardPanel = new JPanel(new GridLayout(4, 13));

		iconArray = new JLabel[4][13];
		/*
		 * Iterates through the created playing cards in order to place the
		 * correct card image to a JLabel.
		 */
		for (int listNum = 0; listNum < 4; listNum++) {
			for (int index = 0; index < 13; index++) {
				PlayingCard c = game.getCard(listNum, index);
				if (c != null) {
					JLabel cardImage = new JLabel(new ImageIcon(fileprefix + c.getImageFileName()));
					iconArray[listNum][index] = cardImage;
					cardPanel.add(cardImage);
				} else {
					JLabel cardImage = new JLabel(new ImageIcon(""));
					iconArray[listNum][index] = cardImage;
					cardPanel.add(cardImage);
				}
			}
		}

		add(cardPanel);

	}

	/**
	 * This method changes the panel according to the card dealt, moved, or
	 * discarded. JLabels with icons on them are used to display the card
	 * picture in the game. This method also responds with a message to the
	 * gamer on if they have won or have moves left.
	 * 
	 */
	private void updatePanel() {

		/*
		 * Each card is a JLabel with an icon (picture) displayed The iconArray
		 * keeps track of the references to all of the icons used for the game
		 * display.
		 */

		for (int listNum = 0; listNum < 4; listNum++) {
			for (int index = 0; index < 13; index++) {
				PlayingCard c = game.getCard(listNum, index);
				if (c == null)
					iconArray[listNum][index].setIcon(null);
				else
					iconArray[listNum][index].setIcon(new ImageIcon(fileprefix + c.getImageFileName()));
			}
		}

		scoreLabel2.setText("  " + game.getScore());
		// Checks if the gamer won and responds positively if they did.
		if (game.win()) {
			JOptionPane.showMessageDialog(this, "You won! Game is over. Start a new game.", "WINNER!",
					JOptionPane.PLAIN_MESSAGE);
		}
		/*
		 * Check if the game is over and sends a message to the gamer that their
		 * game is done as they have no more moves left.
		 */
		if (!game.moreMoves() && !game.win()) {
			JOptionPane.showMessageDialog(this, "You are out of cards and moves. Game is over. Start a new game.",
					"Game over", JOptionPane.PLAIN_MESSAGE);
		}

	}

	private class ButtonListener implements ActionListener {
		/**
		 * A method that allows gamers to actually click the created buttons and
		 * recognises which button it is using '.getSource'.
		 */
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == dealButton)
				game.deal();
			else if (event.getSource() == newGameButton)
				game.startNewGame();
			else {
				for (int listNum = 0; listNum < 4; listNum++) {
					if (event.getSource() == discardButton[listNum])
						game.discard(listNum);
					else if (event.getSource() == moveButton[listNum])
						game.move(listNum);
				}
			}
			updatePanel();

		}

	}

}
