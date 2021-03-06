 /**
  * PlayersSelectScreen.java
  * a JLayered Pane that holds a header line and four panels that allow the user to enter up to four player names
  * by Andrew Brodhead, Gage Gutmann, Alexa Andrew
  * V 1.1 - edited 4/19 added okay button and validation for number of players
  * 
  */
 import java.util.*;
 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;

 
 public class ConfirmTravelScreen extends JLayeredPane{
 	
 	private JLabel backgroundPanel;
 	private JPanel[] travelInfoPanels;
 	private JTextField[] travelInfoBoxes;
 	// private JPanel buttonPanel = new JPanel();
 	private boolean okClicked = false;
 	private boolean remainClicked = false;
 	private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
 	private int destination; 
 	private Player currentPlayer;
 	private boolean travelSuccess; 
 	/*
	* Constructor for the Confirm Travel Screen, takes in a player object whose turn it is and the destination the player chose to travel to.
	*/
 	public ConfirmTravelScreen(Player currentPlayer, int destination) {
 		this.currentPlayer = currentPlayer;
 		this.destination = destination; 
 		
 		backgroundPanel = new JLabel(new ImageIcon("backgroundPlanet" + currentPlayer.getLocation() + ".png"));
 		backgroundPanel.setOpaque(true);
 		backgroundPanel.setBounds(0,0,800,600);
 		add(backgroundPanel, new Integer(0));
 		initInformationDisplay();
 		RemainButton remain = new RemainButton();
		OkButton okay = new OkButton();
		remain.setBounds(600, 500, 200, 90);
		okay.setBounds(600,50,160,90);
		PlayerChartButton viewPlayers = new PlayerChartButton();
		add(viewPlayers, new Integer(1));
		add(okay, new Integer(1));
		add(remain, new Integer(1));
 	}
 	
 	/**
 	 * initializes and fills arrays of player name panels and the text fields
 	 */
 	private void initInformationDisplay() {
 		travelInfoPanels = new JPanel[3];
 		travelInfoBoxes = new JTextField[3];	
 		travelInfoBoxes[0] = new JTextField(20);
 		travelInfoBoxes[1] = new JTextField(10);
 		travelInfoBoxes[2] = new JTextField(30);
 		for(int i = 0; i < 3; i++) {
 			travelInfoPanels[i] = new JPanel();
 			travelInfoPanels[i].setOpaque(false);
 			add(travelInfoPanels[i], new Integer(1));
 			travelInfoPanels[i].add(travelInfoBoxes[i]);	
 			travelInfoBoxes[i].setOpaque(true);
 			travelInfoBoxes[i].setEditable(false);
 			travelInfoBoxes[i].setBackground(Color.black);
 			travelInfoBoxes[i].setForeground(Color.yellow);
 			travelInfoBoxes[i].setFont(font);
 		}
 		
 		String planet = currentPlayer.getPlanet(destination);
 		travelInfoBoxes[0].setText("Travel to " + planet);
 		travelInfoBoxes[1].setText("Score: " + currentPlayer.getScore());
 		int probabilityOfSurvival = (int) Math.round(currentPlayer.tryTravel(destination) * 100.0);
 		travelInfoBoxes[2].setText("Probability of survival: " + probabilityOfSurvival + "%");
 		
 		
 		travelInfoPanels[0].setBounds(0, 200, 400, 150);
 		travelInfoPanels[1].setBounds(400, 200, 400, 150);
 		travelInfoPanels[2].setBounds(0, 350, 600, 200);
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
 					travelSuccess = currentPlayer.commenceTravel(currentPlayer.tryTravel(destination), destination);
 					okClicked = true;
 				}
 			});
 		}
 	}
 	
 	
 	// returns whether travel is successful. should only be used if ok is clicked 
 	public boolean isTravelSuccessful() {
 		return travelSuccess; 
 	}
 	
 	/*
 	 * returns a boolean of whether the remain button has been clicked
 	 * main program will check to see if it can move on to the next frame
 	 */
 	public boolean isRemainClicked() {
 		return remainClicked;
 	}
 	
 	/*
 	 * a button class that creates an "Remain at planet" button. When clicked, toggles the okClicked flag,
 	 * telling program to advance to next screen
 	 */
 	private class RemainButton extends JLabel{
 		public RemainButton() {
 			super(new ImageIcon("remainButton.png"));
 			setBounds(500, 500, 160, 90);
 			addMouseListener(new MouseAdapter() {
 				public void mouseClicked(MouseEvent e) {
 					System.out.println("Remain clicked");
 					remainClicked = true;
 				}
 			});
 		}
 	}
 	
 	
 }
