/**
 * Screentester.java
 * runs a test to play around with gui layered elements
 * Andrew Brodhead
 * V1.0
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class screenTester{
	public static void main(String[] args) {
		JFrame frame = new JFrame("Race to Pluto!");
		frame.setSize(800,600);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		StartScreen start = new StartScreen();
		frame.add(start);
		frame.setVisible(true);
		
	}
}
