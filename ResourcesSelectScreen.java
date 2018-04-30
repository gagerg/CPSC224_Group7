import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ResourcesSelectScreen extends JLayeredPane{

	private JLabel backgroundPanel;
	private JPanel buttonPanel = new JPanel(); 
	private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
	private JButton [] playerResourceButtons;
	private JTextField scoreDisplay;
	
	private Player currentPlayer;
	private int score;
	private int [] multipliers;
	private int [] possibleResources;
	private boolean resourceSelected;
	private int resource; 
	
	public ResourcesSelectScreen(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		this.score = currentPlayer.getScore();
		this.multipliers = currentPlayer.getMultipliers(); 
		this.possibleResources = currentPlayer.getPossibleResources();
		
		backgroundPanel = new JLabel(new ImageIcon("backgroundPlanet" + currentPlayer.getLocation() + ".png"));
		backgroundPanel.setOpaque(true);
		backgroundPanel.setBounds(0,0,800,600);
		add(backgroundPanel, new Integer(0));
		initPlayerResourceButtons();
		
		buttonPanel.setOpaque(false);
		buttonPanel.setBounds(200, 0, 400, 600);
		add(buttonPanel, new Integer(2));
		PlayerChartButton viewPlayers = new PlayerChartButton();
		add(viewPlayers, new Integer(1));
	}

		/**
	 * initializes and fills array of resource select buttons as well as score  display
	 */
	private void initPlayerResourceButtons() {
		playerResourceButtons = new JButton[5];
		
		scoreDisplay = new JTextField(10);
		scoreDisplay.setText("Score: " + score);
		scoreDisplay.setBackground(Color.BLACK);
		scoreDisplay.setForeground(Color.yellow);
		scoreDisplay.setFont(font);
		scoreDisplay.setOpaque(true);
		scoreDisplay.setBounds(0, 200, 800, 200);
		scoreDisplay.setEditable(false);
		
		
		playerResourceButtons[0] = new JButton("Parts: " + score + " X " + multipliers[0] + " = " + possibleResources[0]);
		playerResourceButtons[1] = new JButton("Fuel: " + score + " X " + multipliers[1] + " = " + possibleResources[1]);
		playerResourceButtons[2] = new JButton("Money: " + score + " X " + multipliers[2] + " = " + possibleResources[2]);
		playerResourceButtons[3] = new JButton("Necessities: " + score + " X " + multipliers[3] + " = " + possibleResources[3]);
		playerResourceButtons[4] = new JButton("Titanium: " + score + " X " + multipliers[4] + " = " + possibleResources[4]);
		
//		playerResourceButtons[0].setBounds(0, 150, 400, 200);
//		playerResourceButtons[1].setBounds(400, 150, 400, 200);
//		playerResourceButtons[2].setBounds(0, 275, 400, 200);
//		playerResourceButtons[3].setBounds(400, 275, 400, 200);
//		playerResourceButtons[4].setBounds(0, 400, 400, 200);
		
		ResourceAction partsAction = new ResourceAction(0);
		ResourceAction fuelAction = new ResourceAction(1);
		ResourceAction moneyAction = new ResourceAction(2);
		ResourceAction necessitiesAction = new ResourceAction(3);
		ResourceAction titaniumAction = new ResourceAction(4);
		
		playerResourceButtons[0].addActionListener(partsAction);
		playerResourceButtons[1].addActionListener(fuelAction);
		playerResourceButtons[2].addActionListener(moneyAction);
		playerResourceButtons[3].addActionListener(necessitiesAction);
		playerResourceButtons[4].addActionListener(titaniumAction);
	
		buttonPanel.add(scoreDisplay); 
		for (int i = 0; i < 5; i++) {
			playerResourceButtons[i].setFont(font);
			buttonPanel.add(playerResourceButtons[i]);
		}
		
	}
	private class ResourceAction implements ActionListener {
		private int aResource; 
		
		public ResourceAction(int resource) {
			aResource = resource;
		}
		
		public void actionPerformed(ActionEvent event) 
		{
			resourceSelected = true;
			resource = aResource;
			currentPlayer.collectResources(aResource);
			System.out.println("Collected resource " + aResource);
		}
	}
	
	public boolean isResourceSelected() {
		return resourceSelected;
	}
	
	public int getResource() {
		return resource;
	}
	

}
