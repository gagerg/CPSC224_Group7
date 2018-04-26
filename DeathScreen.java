/**
 * DeathScreen.java
 * a screen telling the player how they died 
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

public class DeathScreen extends JLayeredPane{
	
	private JLabel backgroundPanel;
	private JPanel deathPanel;
	private JTextField deathMessage;
	private JPanel buttonPanel = new JPanel();
	private boolean okClicked = false;
	private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
	private String name; 
	
	public DeathScreen(Player currentPlayer) {
		this.name = currentPlayer.getName();
		displayDeathMessage();
		backgroundPanel.setOpaque(true);
		backgroundPanel.setBounds(0,0,800,600);
		add(backgroundPanel, new Integer(0));
		buttonPanel.setOpaque(false);
		buttonPanel.setBounds(600,50,160,90);
		buttonPanel.add(new OkButton());
		add(buttonPanel, new Integer(2));
	}
	
	/**
	 * initializes and fills arrays of player name panels and the text fields
	 */
	private void displayDeathMessage() {
		deathPanel = new JPanel();
		deathMessage = new JTextField(20);	
		deathPanel.setOpaque(false);
		add(deathPanel, new Integer(1));
		deathPanel.add(deathMessage);	
		deathMessage.setEditable(false);
		deathMessage.setBackground(Color.black);
		deathMessage.setForeground(Color.yellow);
		deathMessage.setFont(font);
		
		Random gen = new Random();
		int modeOfDeath = gen.nextInt(); 
		switch (modeOfDeath) {
			default:
					backgroundPanel = new JLabel(new ImageIcon("backgroundPlanet" + "explodingspaceship.png"));
					deathMessage.setText("Boom! Your spaceship explodes mid-journey! " + name 
										+ " has died, better luck next time!");
					break;
		}
	}
	
	
	/*
	 * returns a boolean of whether the okay button has been clicked
	 * main program will check to see if it can move on to the next frame
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/*
	 * a button class that creates an "OK" button. When clicked, toggles the okClicked flag,
	 * telling program to advance to next screen
	 */
	private class OkButton extends JLabel{
		public OkButton() {
			super(new ImageIcon("okButton.png"));
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("OK clicked");
					okClicked = true;
				}
			});
		}
	}
	
}