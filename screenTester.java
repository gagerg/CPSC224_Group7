/**
 * Screentester.java
 * runs a test to simulate game flow from one screen to the next
 * By Andrew Brodhead
 * V1.2 - edited 4/18 added player select screen
 * 		- edited 4/19 added ok from  ^ button to continue to first turn
 * 		- edited 4/19 added RollScreen
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class screenTester{
	private static int numberPlayers; 
	private static String[] playerNames;
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
		System.out.print("waiting for user to start");
		while(!start.isStartClicked()) {
			if(tick(c)) System.out.print(".");
			c++;
		}
		
		//continue to player naming screen
		System.out.println("removing start");
		PlayersSelectScreen pselect = new PlayersSelectScreen();
		frame.remove(start);
		frame.add(pselect);
		frame.setVisible(true);
		
		System.out.print("waiting for user to 'OK' player names");
		while(!pselect.isOkClicked()) {
			if(tick(c)) System.out.print(".");
			c++;
		}
		
		//number of players has been entered, proceed to first player turn
		playerNames = pselect.getNames();
		numberPlayers = pselect.getNumberPlayers();
		
		//At this point game should construct an array of Player objects, initialize them with
		//player names, and determine turn order(somehow). If we decide to do roll for turn order, those
		//buttons will be in pselect name. Otherwise, it might just randomly decide by randomly sorting the
		//player array.
		System.out.println("removing pselect");
		frame.remove(pselect);
		
		//test player, creates a rollScreen with playername Gregor on planet 3(Saturn)
		RollScreen roll = new RollScreen("Gregor", 3);
		frame.add(roll);
		frame.setVisible(true);
	}
	/*
	 * This function won't be in the actual game.java file, just used for outputting test messages between
	 * events.
	 */
	public static boolean tick(int counter) {
		//outputs a wait message every 200000000 loop cycles, kind of cpu expensive.
		//final project should use one of many java timer implementations.
		//necessary because events happen like key presses in CPEN231 lab
		if(counter%200000000 == 1000) {
			counter = 0;
			return true;
		}
		else return false;
	}
	
}
