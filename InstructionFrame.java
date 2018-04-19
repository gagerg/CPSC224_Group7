/**
 * InstructionFrame.java
 * creates a new popup window in which instructions will be housed
 * By Andrew Brodhead
 * V 1.0
 */
import javax.swing.JFrame;

public class InstructionFrame extends JFrame {
	public InstructionFrame() {
		super("Instructions");
		setSize(300,300);
		setResizable(false);
		setVisible(true);
	}
}