/**
 * RollSCreen.java
 * a JLayered Pane that houses 6 dice buttons, each displaying the current roll values.
 * this screen gives the user the option to reroll up to two times, displays turns remaining and
 * allows user to finish if they decide not to reroll
 * by Andrew Brodhead
 * V 1.0
 * 
 * 
 * to do: pretty much everything
 * from player/turn/something class: need something[] getRoll(), String getName(), int getPlanet(),
 * from scorecard class: need getScore(something[] roll)
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
	
	public RollScreen(String playerName, int planetNumber) {
		this.playerName = playerName;
		backgroundPanel = new JLabel(new ImageIcon("planets/backgroundPlanet" + planetNumber + ".png"));
		backgroundPanel.setOpaque(true);
		backgroundPanel.setBounds(0,0,800,600);
		add(backgroundPanel, new Integer(0));
	}
	
	private class DicePanel extends JPanel {
		public DicePanel() {
			setOpaque(true);
			setBounds(100,200,400,100);
		}
	}
}