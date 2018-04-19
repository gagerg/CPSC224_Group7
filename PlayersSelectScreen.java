/**
 * PlayersSelectScreen.java
 * a JLayered Pane that holds a header line and four panels that allow the user to enter up to four player names
 * by Andrew Brodhead
 * V 1.1 - edited 4/19 added okay button and validation for number of players
 * 
 * 
 * to do: ?limit size of player names?
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayersSelectScreen extends JLayeredPane{
	
	private JLabel backgroundPanel;
	private ImageIcon backgroundImg = new ImageIcon("pselectbackground.png");
	private ImageIcon okButtonImg = new ImageIcon("okButton.png");
	private String[] defaultPnames = {"player 1", "player 2", "", ""};
	//lower part of screen will be divided into four quartiles, each with a box to enter player name
	private JPanel[] playerNamePanels;
	private JTextField[] nameBoxes;
	private JPanel buttonPanel = new JPanel();
	private boolean okClicked = false;
	
	public PlayersSelectScreen() {
		backgroundPanel = new JLabel(backgroundImg);
		backgroundPanel.setOpaque(true);
		backgroundPanel.setBounds(0,0,800,600);
		add(backgroundPanel, new Integer(0));
		initNamePanels();
		buttonPanel.setOpaque(false);
		buttonPanel.setBounds(600,50,160,90);
		buttonPanel.add(new OkButton(okButtonImg));
		add(buttonPanel, new Integer(2));
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
			nameBoxes[i].setText(defaultPnames[i]);	
			//nameBoxes[i].setOpaque(false); // for invisible text boxes
			nameBoxes[i].setBackground(Color.black);
			nameBoxes[i].setForeground(Color.yellow);
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
		String[] names = new String[4];
		for(int i = 0; i < 4; i++) {
			names[i] = nameBoxes[i].getText();
		}
		return names;
	}
	
	/*
	 * returns the number of players with text boxes filled as an integer
	 * should eventually throw an exception if there are less than two players.
	 */
	public int getNumberPlayers() {
		int playerCount = 0;
		String[] names = getNames();
		for(int i = 0; i < 4; i++) {
			if(names[i].length() > 0) playerCount++;
		}
		return playerCount;
	}
	
	/*
	 * returns a boolean of whether the okay button has been clicked
	 * main program will check to see if it can move on to the next frame
	 */
	public boolean isOkClicked() {
		if(okClicked){
			if(getNumberPlayers() < 2) {
				System.out.println("user clicked OK with too few players(" + getNumberPlayers() + ")");
				System.out.print("waiting for user to 'OK' player names");
				okClicked = false;
			} else System.out.println("user clicked okay with " + getNumberPlayers() + " players");
		}
		return okClicked;
	}
	
	/*
	 * a button class that creates an "OK" button. When clicked, toggles the okClicked flag,
	 * telling program to advance to next screen
	 */
	private class OkButton extends JLabel{
		public OkButton(ImageIcon img) {
			super(img);
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("OK clicked");
					okClicked = true;
				}
			});
		}
	}
}