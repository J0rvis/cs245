package assignment02;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/** File: TTT.java
 *  @author: Heather Amthauer and Hannah Borreson
 *  
 *  @version 2/22/2017
 *  
 *	Description: This class is the main for the Tic-Tac-Toe game.
 *  It generates a TTTframe that will hold a JPanel for all of
 *	the game components.
 */

public class TTT  {
	/** The entry main() method */
	public static void main(String[] args) {
				//make a TTTFrame
				TTTFrame frame = new TTTFrame();
				//make it so the Frame closes when the x is clicked
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//make the frame 500 pixels by 500 pixels
				frame.setSize(500,500);
				//make the frame visible
				frame.setVisible(true);
		   }
}
	

	
