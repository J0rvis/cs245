package assignment02;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/** File: TTTFrame.java
 *  @author: Heather Amthauer and Hannah Borreson
 *  
 *  @version 2/22/2017
 *	Description: This class makes the JFrame that will hold
 *	the TTTPanel
 */
public class TTTFrame extends JFrame{
	public TTTFrame(){
		//set the title do the frame has 
		setTitle("Tic-Tac-Toe");
		//create a TTTPanel
		TTTPanel panel = new TTTPanel();
		//add the Panel to the Frame
		add(panel);
		pack();
	}
}


