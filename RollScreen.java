/**
 * RollSCreen.java
 * a JLayered Pane that houses 6 dice buttons, each displaying the current roll values.
 * this screen gives the user the option to reroll up to two times, displays turns remaining and
 * allows user to finish if they decide not to reroll
 * by Andrew Brodhead
 * V 1.1 - edited 4/20, added dice icons to panel and started keep dice selection
 * 
 * 
 * to do: pretty much everything
 * from player/turn/something class: need something[] getRoll(), String getName(), int getPlanet(),
 * from scorecard class: need getScore(something[] roll), but only if the score is calculated from this class
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RollScreen extends JLayeredPane{
	//Ideally this function will take in a reference to a player object and just use getters/setters
	//background images will be named "backgroundPlanetN.png" where n = {earth,0;mars,1; etc..}
	private JLabel backgroundPanel;
	private String playerName;
	private Dice[] diceRoll;
	private boolean[] diceToReroll;
	private int numberRerolls = 2;
	private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 30);
	private ReRollButton reroll = new ReRollButton();
	JTextField turnsRemaining;
	private boolean rerollClicked = false;
	private boolean okClicked = false;
	private DicePanel dpanel;
	
	public RollScreen(Player p, Dice[] initRoll) {
		this.playerName = p.getName();
		//this line is for testing purposes, 
		diceToReroll = new boolean[6];
		diceRoll = initRoll;
		dpanel = new DicePanel();
		reroll.setBounds(600,50,160,90);
		reroll.setOpaque(true);
		JTextField pName = new JTextField(playerName + " - select dice to keep");
		pName.setBackground(Color.BLACK);
		pName.setForeground(Color.yellow);
		pName.setFont(font);
		pName.setOpaque(true);
		pName.setBounds(0,50,600,45);
		pName.setEditable(false);
		turnsRemaining = new JTextField("Turns Remaining: " + numberRerolls);
		turnsRemaining.setBackground(Color.BLACK);
		turnsRemaining.setForeground(Color.yellow);
		turnsRemaining.setFont(font);
		turnsRemaining.setOpaque(true);
		turnsRemaining.setBounds(0,95,600,45);
		turnsRemaining.setEditable(false);
		backgroundPanel = new JLabel(new ImageIcon("backgroundPlanet" + p.getLocation() + ".png"));
		backgroundPanel.setOpaque(true);
		backgroundPanel.setBounds(0,0,800,600);
		add(backgroundPanel, new Integer(0));
		add(dpanel, new Integer(1));
		add(pName, new Integer(1));
		add(reroll, new Integer(2));
		add(turnsRemaining, new Integer(2));
		}
	
	/*
	 * returns a boolean array, like the string of "ynyny" from Yahtzee program, to indicate what dice
	 * the user has selected for rerolling. Indecies that are true have been selected to reroll, while false should
	 * stay the same.
	 */
	public boolean[] getDiceToReroll() {
		return diceToReroll;
	}
	
	/*
	 * Sets diceRoll to the newly rolled array of dice so that they can be displayed as buttons
	 * for the player.
	 */
	public void setRoll(Dice[] roll) {
		diceRoll = roll;
		dpanel = new DicePanel();
		add(dpanel, new Integer(4-numberRerolls));
		rerollClicked = false;
		for(int i = 0; i < diceToReroll.length; i++) {
			diceToReroll[i] = false;
		}
	}
	/*
	 * the panel that houses the 6 buttons for each die.
	 */
	private class DicePanel extends JPanel {
		public DicePanel() {
			setOpaque(false);
			int[] roll = new int[6];
			for(int i = 0; i < diceRoll.length; i++) {
				roll[i] = diceRoll[i].getValue();
			}
			
			//puts more space between each die icon
			setLayout(new FlowLayout(FlowLayout.LEADING, 20,10));
			setBounds(55,300,750,150);
			
			for(int i = 0; i < roll.length; i++) {
				add(new DieButton(roll[i], i));
			}
		}
	}
	
	private void refreshButtons() {
		numberRerolls--;
		turnsRemaining.setText("Turns Remaining: " + numberRerolls);
		if(numberRerolls <= 0) {
			remove(reroll);
			OkButton ok = new OkButton();
			ok.setBounds(600,50,160,90);
			ok.setOpaque(true);
			add(ok, new Integer(1));
		}
	}
	
	public boolean isRerollClicked() {
		return rerollClicked;
	}
	
	/*
	 * returns a boolean of whether the okay button has been clicked
	 * main program will check to see if it can move on to the next frame
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	private class DieButton extends JLabel{
		public DieButton(int rollNum, int dieNumber) {
			super(new ImageIcon("die" + rollNum + ".png"));
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//if the die is currently selected, set icon to unselected die
					if(diceToReroll[dieNumber] && numberRerolls > 0)
						setIcon(new ImageIcon("die" + rollNum + ".png"));
					//if die is unselected, set icon to selected die
					else if(numberRerolls > 0)
						setIcon(new ImageIcon("clicked_die" + rollNum + ".png"));
					//toggle the corresponding boolean for this die to indicate it is selected/deselected
					diceToReroll[dieNumber] = !diceToReroll[dieNumber];
					System.out.print("die#" + dieNumber);
				}
			});
		}
	}
	
	private class ReRollButton extends JLabel{
		public ReRollButton() {
			super(new ImageIcon("ReRollButton.png"));
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("ReRollButton clicked");
					refreshButtons();
					rerollClicked = true;
				}
			});
		}
	}
	
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