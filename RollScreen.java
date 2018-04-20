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
	private int[] diceRoll;
	private boolean[] diceToReroll;
	private int numberRerolls = 2;
	
	public RollScreen(String playerName, int planetNumber) {
		this.playerName = playerName;
		//this line is for testing purposes, 
		int[] b = {1,6,2,4,7,4};
		diceToReroll = new boolean[6];
		DicePanel dpanel = new DicePanel(b);
		backgroundPanel = new JLabel(new ImageIcon("planets/backgroundPlanet" + planetNumber + ".png"));
		backgroundPanel.setOpaque(true);
		backgroundPanel.setBounds(0,0,800,600);
		add(backgroundPanel, new Integer(0));
		add(dpanel, new Integer(1));
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
	 * the panel that houses the 6 buttons for each die.
	 */
	private class DicePanel extends JPanel {
		public DicePanel(int[] roll) {
			setOpaque(false);
			//puts more space between each die icon
			setLayout(new FlowLayout(FlowLayout.LEADING, 20,10));
			setBounds(55,300,750,150);
			
			for(int i = 0; i < roll.length; i++) {
				add(new DieButton(roll[i], i));
			}
		}
	}
	
	private class DieButton extends JLabel{
		public DieButton(int rollNum, int dieNumber) {
			super(new ImageIcon("dice/die" + rollNum + ".png"));
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//if the die is currently selected, set icon to unselected die
					if(diceToReroll[dieNumber] && numberRerolls > 0)
						setIcon(new ImageIcon("dice/die" + rollNum + ".png"));
					//if die is unselected, set icon to selected die
					else if(numberRerolls > 0)
						setIcon(new ImageIcon("dice/clicked_die" + rollNum + ".png"));
					//toggle the corresponding boolean for this die to indicate it is selected/deselected
					diceToReroll[dieNumber] = !diceToReroll[dieNumber];
					System.out.println("die #" + dieNumber + " has been clicked");
				}
			});
		}
	}
	
}