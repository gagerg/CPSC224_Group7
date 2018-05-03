/**
 * RunGame.java
 * runs a loop to manage game flow from one screen to the next
 * By Andrew Brodhead, Alexa Andrews, Gage Gutmann
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

public class RunGame {
	public static int numberPlayers; 
	public static String[] playerNames;
	public static boolean gameOver;
	public static Player[] players;
	public static boolean[] playersAlive;
	public static int currentPlayer;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Race to Pluto!");
		frame.setSize(800,600);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		while (true) {
		//set up frame
		StartScreen start = new StartScreen();
		frame.add(start);
		frame.setVisible(true);
		gameOver = false;
		currentPlayer = 0;
		
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
		System.out.println("removing pselect");
		frame.remove(pselect);
		while(!gameOver) {
				Player p = players[currentPlayer];
				Dice[] intialRoll = p.rollDice();
				RollScreen roll = new RollScreen(p, intialRoll);
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
				p.rollDice();
				GatherOrTravelScreen choice = new GatherOrTravelScreen(p, players);
				frame.add(choice);
				frame.setVisible(true);
				while(!choice.isButtonClicked()) {
					if(tick(c)) System.out.print(".");
					c++;
				}
				boolean gatherChosen;
				gatherChosen = choice.didUserGather();
				System.out.println(gatherChosen);
				frame.remove(choice);
				// for(int i = 0; i < 500000000; i++) {int b = 4-4;}
				if(gatherChosen) {
					System.out.println("user gathered");
					ResourcesSelectScreen gather;
					gather = new ResourcesSelectScreen(p);
					frame.add(gather);
					frame.setVisible(true);
					while(!gather.isResourceSelected()) {
						if(tick(c)) System.out.print(".");
						c++;
					}
					frame.remove(gather);
				} else {
					System.out.println("user travelled");
					TravelScreen travel = new TravelScreen(p, players);
					frame.add(travel);
					frame.setVisible(true);
					while(!travel.isOkClicked()) {
						if(tick(c)) System.out.print(".");
						c++;
					}
					int destination = travel.getDestination(); 
					frame.remove(travel);
					ConfirmTravelScreen confirmTravel = new ConfirmTravelScreen(p, destination);
					frame.add(confirmTravel);
					frame.setVisible(true);
					while(!confirmTravel.isOkClicked() && !confirmTravel.isRemainClicked()) {
						if(tick(c)) System.out.print(".");
						c++;
					}
					frame.remove(confirmTravel);
					if (confirmTravel.isOkClicked()) {
						if (confirmTravel.isTravelSuccessful()) {
							LandingSuccessScreen successScreen = new LandingSuccessScreen(p);
							frame.add(successScreen);
							frame.setVisible(true);
							while(!successScreen.isOkClicked()) {
								if(tick(c)) System.out.print(".");
								c++;
							}
							frame.remove(successScreen);
							if (p.getLocation() == 6) {
								gameOver = true; // if the player is on Pluto, game is over
								WinnerScreen winner = new WinnerScreen(p);
								frame.add(winner);
								frame.setVisible(true);
								while(!winner.isMenuClicked()) {
									if(tick(c)) System.out.print(".");
									c++;
								}
								frame.remove(winner);
							}
						} else {
							DeathScreen deathfully = new DeathScreen(p);
							frame.add(deathfully);
							frame.setVisible(true);
							while(!deathfully.isOkClicked()) {
								if(tick(c)) System.out.print(".");
								c++;
							}
							frame.remove(deathfully);
							int alive = 0;
							for (int i = 0; i < numberPlayers; i++) {
								if (players[i].getLocation() >= 0) {
									alive++;
									p = players[i]; // if only one player is alive we know who it is. otherwise, this
								}					// currentPlayer is used to determine the next player and p will be 
								gameOver = true;	// reset next while operation if more than one is alive
								WinnerScreen winner = new WinnerScreen(p);
								frame.add(winner);
								frame.setVisible(true);
								while(!winner.isMenuClicked()) {
									if(tick(c)) System.out.print(".");
									c++;
								}
								frame.remove(winner);
							}
						}
					}
				}
				
				// we are guaranteed there is at least one other player alive 
				if (!gameOver) {
					boolean playerHasChanged = false;
					// check players from current to end 
					if (currentPlayer < numberPlayers - 1) { // if we are not on the last player
						for (int i = currentPlayer + 1; i < numberPlayers; i++) {
							if (playersAlive[i] == true) { // check if the player is alive
								currentPlayer = i; 
								playerHasChanged = true;
								i = numberPlayers; // exit outer loop 
							}
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
	}
	
	/*
	 * This function is used to determine whether or not a time-burning while loop should output a period onto the console.
	 * This function has no purpose other than for debugging and outputting program status onto the console
	 * Takes in an integer counter that is used to determine whether or not 2,000,000,000 cycles of the while loop have been
	 * burned
	 * returns a boolean whether or not 2 billion cycles have passed.
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
	 * Takes in an array of players that are in the order they were entered by the user
	 * outputs a new array of players in a randomly shuffled order.
	 */
	public static void shufflePlayers(Player[] pArray) {
		List<Player> p = Arrays.asList(pArray);
		Collections.shuffle(p);
		pArray = p.toArray(new Player[players.length]);
	}
}
