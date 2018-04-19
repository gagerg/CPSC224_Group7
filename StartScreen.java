/**
 * StartScreen.java
 * StartScreen is a layered pane that contains buttons to start game and view instructions.
 * By Andrew Brodhead
 * V 1.1 - edited 4/18 added isStartClicked and changed start button trigger for program flow
 * 
 * 
 * important notes on background: Text is Agency FB, size 98pt, color R:255, G:240, B:60
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen extends JLayeredPane{
	private JLabel backgroundPanel;
	private JPanel buttonPanel;
	private ImageIcon backgroundImg = new ImageIcon("startbackground.png");
	private ImageIcon startButtonImg = new ImageIcon("startButton.png");
	private ImageIcon instructButtonImg = new ImageIcon("instructButton.png");
	private boolean startClicked = false;
	
	/*
	 * constructor for the Start screen panel
	 */
	public StartScreen() {
		backgroundPanel = new JLabel(backgroundImg);
		buttonPanel = new JPanel();
		
		buttonPanel.setBounds(320, 270, 160, 190);
		backgroundPanel.setBounds(0,0,800,600);
		
		buttonPanel.setOpaque(false);
		backgroundPanel.setOpaque(true);
		
		buttonPanel.add(new StartButton(startButtonImg));
		buttonPanel.add(new InstructionsButton(instructButtonImg));
		buttonPanel.add(new Button("Test"));
		add(backgroundPanel, new Integer(0));
		add(buttonPanel, new Integer(1));
	}
	
	/*
	 * returns whether or not the start button has been clicked by the user
	 */
	public boolean isStartClicked() {
		return startClicked;
	}
	
	/*
	 * a button used only by the start screen to initialize playing the game
	 * when clicked
	 */
	private class StartButton extends JLabel{
		public StartButton(ImageIcon img) {
			super(img);
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("start clicked");
					startClicked = true;
				}
			});
		}
		
		
	}
	
	/*
	 * a button that creates a new window to display instructions when clicked
	 */
	private class InstructionsButton extends JLabel{
		public InstructionsButton(ImageIcon img) {
			super(img);
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("instruct clicked");
					InstructionFrame instruct = new InstructionFrame();
				}
			});
		}
	}
}