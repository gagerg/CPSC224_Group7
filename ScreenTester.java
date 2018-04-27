/**
 * Screentester.java - JUST A TEST, not final main program
 * runs a test to simulate game flow from one screen to the next
 * By Andrew Brodhead
 * V1.2 - edited 4/18 added player select screen
 * 		- edited 4/19 added ok from  ^ button to continue to first turn
 * 		- edited 4/19 added RollScreen
 * 		- edited 4/20 added commented out function to shuffle Player array
 * 		- edited 4/25 finished rollscreen, added game loop and per-player loop
 */
import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;

public class ScreenTester{
	private static int numberPlayers; 
	private static String[] playerNames;
	private static boolean gameOver = false;
	private static Player[] players;
	private static boolean[] playersAlive;
	private int currentPlayer;
	
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
		players = new Player[numberPlayers];
		playersAlive = new boolean[numberPlayers];
		for(int i = 0; i < numberPlayers; i++) {
			players[i] = new Player(playerNames[i]);
			playersAlive[i] = true;
		}
		shufflePlayers(players);
		currentPlayer = 0;
		System.out.println("removing pselect");
		frame.remove(pselect);
		
		while(!gameOver) {
			Player p = players[currentPlayer];
			Dice[] initialRoll = p.rollDice();
			RollScreen roll = new RollScreen(p, initialRoll);
			frame.add(roll);
			frame.setVisible(true);
			//reroll two times
			for(int k = 0; k < 2; k++) {
				System.out.print("waiting for user to Reroll");
				while(!roll.isRerollClicked()) {
					if(tick(c)) System.out.print(".");
					c++;
				}
				roll.setRoll(p.rollDice(roll.getDiceToReroll()));
			}
			
			System.out.println("waiting for user to click okay");
			while(!roll.isOkClicked()) {
				if(tick(c)) System.out.print(".");
				c++;
			}
			frame.remove(roll);
			GatherOrTravelScreen choice = new GatherOrTravelScreen(p);
			frame.add(choice);
			frame.setVisible(true);
			while(!choice.isButtonClicked()) {
				if(tick(c)) System.out.print(".");
				c++;
			}
			boolean gatherChosen = choice.didUserGather();
			frame.remove(choice);
			if(gatherChosen) {
				System.out.println("user gathered");
				ResourcesSelectScreen gather = new ResourcesSelectScreen(p);
				frame.add(gather);
				frame.setVisible(true);
				while(true) {}
			} else {
				System.out.println("user travelled");
			}
			
			// we are guarenteed there is at least one other player alive 
			if (!gameOver) {
				boolean playerHasChanged = false;
				// check players from current to end 
				for (int i = currentPlayer + 1; i < numberPlayers; i++) {
					if (playersAlive[i] == true) { // check if the player is alive
						currentPlayer = i; 
						playerHasChanged = true;
						i = numberPlayers; // exit outer loop 
					}
				}
				// check the players from start to current
				if (!playerHasChanged) {
					for (int i = 0; i < currentPlayer; i++) {
						if (playersAlive[i] == true) {
							currentPlayer = i; 
							i = numberPlayers; // exit outer loop 
						}	
					}
				}
			}
		}
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
	
	/*
	 * This function takes in an reference to an array of Player objects and shuffles it randomly to determine turn order.
	 */
	public static void shufflePlayers(Player[] pArray) {
		List<Player> p = Arrays.asList(pArray);
		Collections.shuffle(p);
		pArray = p.toArray(new Player[players.length]);
	}
	
	
}