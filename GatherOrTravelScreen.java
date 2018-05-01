/*
 * GatherOrTravelScreen.java
 * presents the player with the options to gather resources or travel to another planet. Player can only travel if they have sufficient resources to
 * travel to a planet.
 * By Andrew Brodhead, Gage Gutmann, Alexa Andrew
 * V 1.0
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GatherOrTravelScreen extends JLayeredPane {
	private JLabel backgroundPanel;
	private boolean buttonClicked = false;
	private boolean userGathers;
	private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 30);
	Player p;
	/*
	* Constructor for the GatherOrTravelScreen, takes in a player whose turn it is and the array of all players.
	*/
	public GatherOrTravelScreen(Player p, Player[] pArray) {
		this.p = p;
		backgroundPanel = new JLabel(new ImageIcon("backgroundPlanet" + p.getLocation() + ".png"));
		backgroundPanel.setOpaque(true);
		backgroundPanel.setBounds(0,0,800,600);
		add(backgroundPanel, new Integer(0));
		JTextField pName = new JTextField(p.getName() + " - choose an action");
		pName.setBackground(Color.BLACK);
		pName.setForeground(Color.yellow);
		pName.setFont(font);
		pName.setOpaque(true);
		pName.setBounds(0,50,600,45);
		pName.setEditable(false);
		add(pName, new Integer(1));
		TravelButton t = new TravelButton();
		GatherButton g = new GatherButton();
		t.setBounds(100, 300, 160, 90);
		g.setBounds(540,300,160,90);
		PlayerChartButton viewPlayers = new PlayerChartButton();
		add(viewPlayers, new Integer(1));
		add(t, new Integer(1));
		add(g, new Integer(1));
		
	}
	
	/*
	 * returns whether or not a button has been clicked by the user so
	 * the program knows whether or not to progress to the next screen.
	 */
	public boolean isButtonClicked() {
		return buttonClicked;
	}
	/*
	 * returns whether or not the button the user clicked is the gather button
	 * so that the program knows that screen to progress to.
	 */
	public boolean didUserGather() {
		System.out.println(userGathers);
		return userGathers;
	}
	
	/*
	 * a button that creates a new window to progress to gather resources
	 */
	private class TravelButton extends JLabel{
		public TravelButton() {
			super(new ImageIcon("TravelButton.png"));
			if (!p.checkTravel()) {
				setIcon(new ImageIcon("InvalidTravel.png"));
			} else
				addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						System.out.println("travel clicked");
						buttonClicked = true;
						userGathers = false;
					}
			});
		}
	}
	
	/*
	 * a button that creates a new window to progress to gather resources
	 */
	private class GatherButton extends JLabel{
		public GatherButton() {
			super(new ImageIcon("GatherButton.png"));
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("gather clicked");
					buttonClicked = true;
					userGathers = true;
					System.out.println(userGathers);
				}
			});
		}
	}
}
