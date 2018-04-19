/**
 * Screentester.java
 * runs a test to simulate game flow from one screen to the next
 * By Andrew Brodhead
 * V1.1 - edited 4/18 added player select screen
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class screenTester{
	public static void main(String[] args) {
		//set up frame
		JFrame frame = new JFrame("Race to Pluto!");
		frame.setSize(800,600);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		StartScreen start = new StartScreen();
		frame.add(start);
		frame.setVisible(true);
		
		//counter and tick are just for testing procedures, should not be part of main game flow
		int c = 0;
		while(!start.isStartClicked()) {
			if(tick(c)) System.out.println("waiting for user to start");
			c++;
		}
		
		//continue to player naming screen
		System.out.println("removing start");
		PlayersSelectScreen pselect = new PlayersSelectScreen();
		frame.remove(start);
		frame.add(pselect);
		frame.setVisible(true);
		
		while(!pselect.isOkClicked()) {
			if(tick(c)) System.out.println("waiting for user to 'OK' player names");
			c++;
		}
		System.out.println("removing pselect");
		
	}
	public static boolean tick(int counter) {
		//outputs a wait message every 1000000000 loop cycles
		return (counter%1000000000 == 0);
	}
	
}
