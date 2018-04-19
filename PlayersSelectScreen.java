/**
 * PlayersSelectScreen.java
 * a JLayered Pane that holds a header line and four panels that allow the user to enter up to four player names
 * by Andrew Brodhead
 * V 1.0
 * 
 * 
 * to do: add continue button, input validation for less than two names, ?limit size of player names?,
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayersSelectScreen extends JLayeredPane{
	
	private JLabel backgroundPanel;
	private ImageIcon backgroundImg = new ImageIcon("pselectbackground.png");
	private String[] pnames = {"player 1", "player 2", "", ""};
	//lower part of screen will be divided into four quartiles, each with a box to enter player name
	private JPanel[] playerNamePanels;
	private JTextField[] nameBoxes;
	private int numberPlayers = 2;
	private boolean okClicked = false;
	
	public PlayersSelectScreen() {
		backgroundPanel = new JLabel(backgroundImg);
		backgroundPanel.setOpaque(true);
		backgroundPanel.setBounds(0,0,800,600);
		add(backgroundPanel, new Integer(0));
		initNamePanels();
	}
	
	/**
	 * initializes and fills arrays of player name panels and the text fields
	 */
	private void initNamePanels() {
		playerNamePanels = new JPanel[4];
		nameBoxes = new JTextField[4];	
		for(int i = 0; i < 4; i++) {
			playerNamePanels[i] = new JPanel();
			nameBoxes[i] = new JTextField(20);
			playerNamePanels[i].setOpaque(false);
			add(playerNamePanels[i], new Integer(1));
			playerNamePanels[i].add(nameBoxes[i]);
			nameBoxes[i].setText(pnames[i]);	
		}
		
		playerNamePanels[0].setBounds(0, 200, 400, 150);
		playerNamePanels[1].setBounds(400, 200, 400, 150);
		playerNamePanels[2].setBounds(0, 350, 400, 150);
		playerNamePanels[3].setBounds(400, 350, 400, 150);
	}
	
	/*
	 * returns the array of names of the players as an array of Strings
	 */
	public String[] getNames() {
		return pnames;
	}
	
	/*
	 * returns the number of players with text boxes filled as an integer
	 * should eventually throw an exception if there are less than two players.
	 */
	public int getNumberPlayers() {
		return numberPlayers;
	}
	
	/*
	 * returns a boolen of whether the okay button has been clicked
	 * main program will check to see if it can move on to the next frame
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
}