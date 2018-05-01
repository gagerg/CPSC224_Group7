/*
 * PlayerChartButton.java
 * lets the player at any time view a chart of players, alive status, resources, and location.
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayerChartButton extends JLabel {
	/*
	* Constructor for the PlayerChartButton, which on click 
	*/
	public PlayerChartButton() {
		super(new ImageIcon("PlayerChartButton.png"));
		setBounds(320,480,160,90);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("player stats clicked");
				PlayerFrame pFrame = new PlayerFrame();
			}
		});
	}
	/*
	* PlayerFrame is a frame that contains a panel with all the resource/location information of all players.
	* when the button is clicked, this frame opens in a new popup window.
	*/
	private class PlayerFrame extends JFrame {
		public PlayerFrame() {
			super("Player Stats and Locations");
			setResizable(false);
			setVisible(true);
			//***********
			//Player table panel i will be go here similar to
			//Instructions i = new Instructions();
                        CurrentPlaying current = new CurrentPlaying();
			//***********
			add(current);
			pack();
		}
	}
}

