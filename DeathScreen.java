/**
 * DeathScreen.java
 * a screen telling the player how they died 
 * by Andrew Brodhead, Gage Gutmann, Alexa Andrews
 * V 1.1 - edited 4/19 added okay button and validation for number of players
 * V 1.2 - Added additional comments 5/3/18
 * 
 * 
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DeathScreen extends JLayeredPane{
	
	//Variable declaration
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
		deathMessage = new JTextField(44);	
		deathPanel.setOpaque(false);
		add(deathPanel, new Integer(1));
		deathPanel.add(deathMessage);	
		deathMessage.setEditable(false);
		deathMessage.setBackground(Color.black);
		deathMessage.setForeground(Color.yellow);
		deathMessage.setFont(font);
		deathMessage.setOpaque(true);
		deathPanel.setBounds(25, 50, 600, 150);
		
		Random gen = new Random();
		int modeOfDeath = gen.nextInt(); 
		switch (modeOfDeath) {
			default:
					backgroundPanel = new JLabel(new ImageIcon("explodingspaceship.jpg"));
					deathMessage.setText("Boom! Your spaceship explodes mid-journey!");
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
