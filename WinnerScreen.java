/**
 * WinnerScreen.java
 * Displays a congratulatory message to the winner
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

public class WinnerScreen extends JLayeredPane{
	
	private JLabel backgroundPanel;
	private JPanel successPanel;
	private JTextField successMessage;
	private JPanel buttonPanel = new JPanel();
	private boolean menuClicked = false;
	private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
	private String name; 
	
	public WinnerScreen(Player currentPlayer) {
		this.name = currentPlayer.getName();
		backgroundPanel = new JLabel(new ImageIcon("backgroundPlanet" + currentPlayer.getLocation() + ".png"));
		backgroundPanel.setOpaque(true);
		backgroundPanel.setBounds(0,0,800,600);
		add(backgroundPanel, new Integer(0));
		displaySuccessMessage();
		buttonPanel.setOpaque(false);
		buttonPanel.setBounds(600,50,160,90);
		buttonPanel.add(new OkButton());
		add(buttonPanel, new Integer(2));
	}
	
	/**
	 * initializes and fills arrays of player name panels and the text fields
	 */
	private void displaySuccessMessage() {
		successPanel = new JPanel();
		successMessage = new JTextField(20);	
		successPanel.setOpaque(false);
		add(successPanel, new Integer(1));
		successPanel.add(successMessage);	
		successMessage.setEditable(false);
		successMessage.setBackground(Color.black);
		successMessage.setForeground(Color.yellow);
		successMessage.setFont(font);
		
		successMessage.setText(name + " is the winner!");
	}
	
	
	/*
	 * returns a boolean of whether the okay button has been clicked
	 * main program will check to see if it can move on to the next frame
	 */
	public boolean isMenuClicked() {
		return menuClicked;
	}
	
	/*
	 * a button class that creates an "OK" button. When clicked, toggles the okClicked flag,
	 * telling program to advance to next screen
	 */
	private class OkButton extends JLabel{
		public OkButton() {
			super(new ImageIcon("menuButton.png"));
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("Menu clicked");
					menuClicked = true;
				}
			});
		}
	}
	
}