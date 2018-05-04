/*
 * TravelScreen.java
 * presents the user with a set of planet buttons and allows them to choose what planet they wish to travel to.
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableColumnModel;

public class TravelScreen extends JLayeredPane {
	private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 30);
	private Font font2 = new Font(Font.MONOSPACED, Font.PLAIN, 14);
	private JLabel backgroundPanel;
	private PlanetPanel pPanel;
	private Player p;
	private TablePanel tPanel;
	private boolean okClicked;
	private boolean planetClicked;
	private int destination = 0;
	private JTextField pDestination;
	private Travel t = new Travel();
	private String[] planetNames = {"Earth","Mars","Jupiter","Saturn","Uranus","Neptune","Pluto"};
	/*
	* constructor for TravelScreen class, takes in a player object for the current player whose turn it is and the array of all 
	* current players.
	*/
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
	/*
	* gets the destination from the button the user clicked.
	* returns and integer corresponding to a planet
	*/
	public int getDestination() {
		return destination;
	}
	/*
	* gets whether or not the player validly clicked to continue on
	* returns a boolean for the main program loop to check
	*/
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/*
	* A panel that houses all of the planet buttons so that they appear in an organized layout for the user
	*/
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
	
	/*
	* A panel that houses a set of Text field to display to the user their current resources and the required resources for
	* the selected planet.
	*/
	private class TablePanel extends JPanel {
		private String[] colNames = {"p","Parts", "Fuel", "Money", "Necessities", "Titanium"};
		JTextField[][] stats = new JTextField[colNames.length][3];
		JPanel upper = new JPanel();
		JPanel lower = new JPanel();
		JPanel title = new JPanel();
		int[] needed = t.getTravelRequirements(0, destination);
		public TablePanel() {
			setOpaque(false);
			setBounds(55,400,400,200);
			title.setOpaque(false);
			title.setBounds(55,300,300,100);
			upper.setOpaque(false);
			lower.setOpaque(false);
			upper.setBounds(55,400,300,100);
			upper.setBounds(55,500,300,100);
			add(title);
			add(upper);
			add(lower);
			stats[0][0] = new JTextField("---Planet---");	
			stats[0][1] = new JTextField("Required:");
			stats[0][2] = new JTextField("You have:");
			for(int i = 1; i < colNames.length;i++) {
				stats[i][0] = new JTextField(colNames[i]);
				stats[i][1] = new JTextField(6);
				stats[i][2] = new JTextField(""+p.getPlayerResources()[i-1]);
				System.out.print(p.getPlayerResources()[i-1]);
				
			}
			for(int j = 0; j < 3; j++) {
				for(int i = 0; i < colNames.length; i++) {
					stats[i][j].setBackground(Color.BLACK);
					stats[i][j].setForeground(Color.yellow);
					stats[i][j].setFont(font2);
					stats[i][j].setOpaque(true);
					stats[i][j].setBounds(0,50,600,45);
					stats[i][j].setEditable(false);
				}
			}
			for(int i = 0; i < colNames.length; i++) {
				title.add(stats[i][0]);
				upper.add(stats[i][1]);
				lower.add(stats[i][2]);
			}
		}
		/*
		* sets the textfield information to reflect the valid planet button that the user has clicked.
		* takes in an integer index of the planet the user chose.
		*/
		public void setPlanet(int planetNum) {
			stats[0][0].setText("-" + planetNames[planetNum]);
			Travel t = new Travel();
			for(int i = 1; i < colNames.length; i++) {
				stats[i][1].setText(""+(t.getTravelRequirements(p.getLocation(), planetNum)[i-1]));
			}
		}
	}
	
	/*
	* An image label with an attached mouseAction that, when clicked and is a valid planet for the player to travel to, 
	* sets the table to display required resources and allows the player to continue in travelling to the selected planet.
	*/
	private class PlanetButton extends JLabel{
		public PlanetButton(int planetNum) {
			super(new ImageIcon("planetIcon" + planetNum + ".png"));
			if(p.getLocation() >= planetNum)
				setIcon(new ImageIcon("InvalidplanetIcon" + planetNum + ".png"));
			else 
				addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					pDestination.setText("Travel to " + planetNames[planetNum] + "?");
					System.out.print("planet#" + planetNum);
					destination = planetNum;
					tPanel.setPlanet(planetNum);
					planetClicked = true; 
				}
			});
		}
	}
	
	/*
	* A button that allows the user, when a valid planet is selected, to continue onto the next frame.
	*/
	private class OkButton extends JLabel{
		public OkButton() {
			super(new ImageIcon("okButton.png"));
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (planetClicked) {
						System.out.println("OK clicked");
						okClicked = true;
					}
				}
			});
		}
	}
}
