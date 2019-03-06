package assignment01;
import javax.swing.*;

/**
 *   Program to play a game of Aces Up
 */

/**
 * 
 * @author Hannah Borreson
 * 
 * @version 02/08/2017
 * 
 * 
 */
public class AcesUp {

	// DO NOT CHANGE, REMOVE OR ADD ANY CODE GIVEN IN THIS CLASS
	/**
	 * This method is the main method of the game and makes sure that the game
	 * is visible, named, put together, and exists on close. 
	 */

	public static void main(String[] args) {

		JFrame frame = new JFrame("Aces Up");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GameController controller = new GameController();
		frame.getContentPane().add(new GamePanel(controller));

		frame.pack();
		frame.setSize(1000, 500);
		frame.setVisible(true);

	}

}