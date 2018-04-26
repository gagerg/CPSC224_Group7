/**
 * InstructionFrame.java
 * creates a new popup window in which instructions will be housed
 * By Andrew Brodhead,
 * V 1.0
 */
import javax.swing.JFrame;

public class InstructionFrame extends JFrame {
	public InstructionFrame() {
		super("Instructions");
		setResizable(false);
		setVisible(true);
		Instructions i = new Instructions();
		add(i);
		pack();
	}
}