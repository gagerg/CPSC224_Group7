/*
 * TravelScreen.java
 * presents the user with a set of planet buttons and allows them to choose what planet they wish to travel to.
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TravelScreen extends JLayeredPane {
	private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 30);
	private JLabel backgroundPanel;
	private PlanetPanel pPanel;
	private Player p;
	private TablePanel tPanel;
	private boolean okClicked;
	private boolean backClicked;
	private int destination = 0;
	private JTextField pDestination;
	private String[] planetNames = {"Earth","Mars","Jupiter","Saturn","Uranus","Neptune","Pluto"};
	public TravelScreen(Player p, Player[] pArray) {
		this.p = p;
		backgroundPanel = new JLabel(new ImageIcon("backgroundPlanet" + p.getLocation() + ".png"));
		backgroundPanel.setOpaque(true);
		backgroundPanel.setBounds(0,0,800,600);
		JTextField pName = new JTextField(p.getName() + " - Choose Planet");
		pName.setBackground(Color.BLACK);
		pName.setForeground(Color.yellow);
		pName.setFont(font);
		pName.setOpaque(true);
		pName.setBounds(0,50,600,45);
		pName.setEditable(false);
		pDestination = new JTextField("Destination: ");
		pDestination.setBackground(Color.BLACK);
		pDestination.setForeground(Color.yellow);
		pDestination.setFont(font);
		pDestination.setOpaque(true);
		pDestination.setBounds(0,95,600,45);
		pDestination.setEditable(false);
		pPanel = new PlanetPanel();
		tPanel = new TablePanel();
		OkButton okay = new OkButton();
		okay.setBounds(600,50,160,90);
		okay.setOpaque(true);
		add(pDestination, new Integer(1));
		add(okay, new Integer(2));
		add(tPanel, new Integer(1));
		add(pPanel, new Integer(1));
		add(backgroundPanel, new Integer(0));
		add(pName, new Integer(1));
		
	}
	
	private class PlanetPanel extends JPanel {
		public PlanetPanel() {
			setOpaque(false);
			//puts more space between each planet icon
			setLayout(new FlowLayout(FlowLayout.LEADING, 50,50));
			setBounds(55,90,690, 450);
			for(int i = 1; i < 7; i++) {
				add(new PlanetButton(i));
			}
		}
	}
	
	private class TablePanel extends JPanel {
		public TablePanel() {
			setOpaque(true);
			setBounds(55,400,690,200);
		}
	}
	private class PlanetButton extends JLabel{
		public PlanetButton(int planetNum) {
			super(new ImageIcon("planetIcon" + planetNum + ".png"));
			if(p.getLocation() + 2 >= planetNum)
				setIcon(new ImageIcon("InvalidplanetIcon" + planetNum + ".png"));
			else 
				addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					pDestination.setText("Travel to " + planetNames[planetNum] + "?");
					System.out.print("planet#" + planetNum);
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