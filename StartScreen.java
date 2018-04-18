/**
 * StartScreen.java
 * StartScreen is a layered pane that contains buttons to start game and view instructions.
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen extends JLayeredPane{
	private JLabel backgroundPanel;
	private JPanel buttonPanel;
	private ImageIcon backgroundImg = new ImageIcon("background.png");
	private ImageIcon startButtonImg = new ImageIcon("startButton.png");
	private ImageIcon instructButtonImg = new ImageIcon("instructButton.png");
	
	public StartScreen() {
		backgroundPanel = new JLabel(backgroundImg);
		buttonPanel = new JPanel();
		
		buttonPanel.setBounds(320, 270, 160, 190);
		backgroundPanel.setBounds(0,0,800,600);
		
		buttonPanel.setOpaque(false);
		backgroundPanel.setOpaque(true);
		
		buttonPanel.add(new StartButton(startButtonImg));
		buttonPanel.add(new InstructionsButton(instructButtonImg));
		add(backgroundPanel, new Integer(0));
		add(buttonPanel, new Integer(1));
	}
	
	
	private class StartButton extends JLabel{
		public StartButton(ImageIcon img) {
			super(img);
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("start");
				}
			});
		}
		
		
	}
	
	
	private class InstructionsButton extends JLabel{
		public InstructionsButton(ImageIcon img) {
			super(img);
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("instruct");
					InstructionFrame instruct = new InstructionFrame();
				}
			});
		}
	}
}