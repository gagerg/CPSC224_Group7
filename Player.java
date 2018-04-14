/* 
 * The class Player simulates a Player in the game of Yahtzee. In a full
 * game there could be multiple players and each will have a name. For now,
 * each has a ScoreCard and a collection of Dice. The number of dice and the
 * amount of sides for each Dice are determined when the Player is constructed. 
 * The player can roll their collection of dice all at once or excluding specific 
 * ones based on an input string and can take a turn in Yahtzee. 
 * Assignment 6
 * @author Alexa Andrews
 * Date Created: March 7 2018
 * Last Modified: March 22 2018
*/

import java.util.Arrays;
import java.util.Scanner;

public class Player {
	// private String name; // will be used later for the full game
	private ScoreCard score; 
    private Dice [] dice; 
    private int turns;
    
	/*
    * This is the constructor, it takes two parameter numSides and numDice
    * It creates an array of Dice size numDice all with sides numSides
    * @parameters int numSides the number of sides, int numDice the amount of Dice
 	* @returns nothing 
 	* @throw - no exceptions are thrown by this function 
 	*/
    public Player(int numDice, int numSides, int numTurns) { // String name) { 
    	score = new ScoreCard(numSides); // initialize the ScoreCard, for full game
    	turns = numTurns;
    	dice = new Dice [numDice];
    	for (int i = 0; i < numDice; i++) {
    		dice[i] = new Dice(numSides); // initialize all dice
    	}
    }
    
    /* 
    * This function simulates the rolling of the dice in the hand
    * It rolls based on the string keep character by character such that
    * if a character is not 'y' the corresponding indexed Dice is rolled
    * @parameters String keep to determine which Dice are not rolled
    * @returns nothing
    * @throw - no exceptions are thrown by this function
    */
    public void rollDice(String keep) {
    	
    	// checks index again keep, those lining
    	// up with a y are preserved
    	for (int i = 0; i < dice.length; i++) {
            if (keep.charAt(i) != 'y') 
                dice[i].roll();
        }
    	
        // output the roll
        System.out.print("Your roll was: ");
        for (int i = 0; i < dice.length; i++)
        {
        	System.out.print(dice[i].getValue() + " ");
        }
        System.out.println(); 
    }
    
    /* 
    * This function allows a user a turn in Yahtzee
    * It begins by rolling all dice
    * @parameters none
    * @returns nothing
    * @throw - no exceptions are thrown by this function
    */
    public void takeTurn(Scanner in) {
    	int currentTurn = 1;
    	char [] toKeep = new char[dice.length]; // keep string as long as amount of dice
    	char [] toKeepAll = new char[dice.length];
    	for (int i = 0; i < toKeep.length; i++) {
    		toKeep[i] = 'n';
    		toKeepAll[i] = 'y';
    	}
    	String keep = new String(toKeep);
    	String keepAll = new String(toKeepAll);
    	System.out.print("Enter any key to begin: ");
    	in.nextLine();
    	System.out.println();
    	while (currentTurn < (turns + 1) && (!keep.equals(keepAll))) { // if we are not out of turns
    		rollDice(keep);							 // and the user is not keeping all Dice
    		if (currentTurn < turns) { // if there are turns remaining
    			System.out.print("Enter the dice to keep (y or n) ");
    			keep = in.nextLine();
    			while (keep.length() != dice.length) { // ensure the given string is of proper length
    				System.out.println("Enter the dice to keep (y or n) "); 
    				keep = in.nextLine();
    			}
    		}	
    		currentTurn++; // rolling the Dice uses a turn
    	}
    	displayScore(); // show the score at the end
    	score.displayOpenLines();
    	score.selectScore(in);
    }
    
    public void displayFinalScore() {
    	score.finalScore();
    }
    
    // all dice must be rolled before this method is called
    /* 
    * This function displays the score of the current state
    * of the Dice. It first sorts the Dice and displays the 
    * sorted hand, then scores them using its ScoreCard. 
    * @parameters none
    * @returns nothing
    * @throw - no exceptions are thrown by this function
    */
    public void displayScore() { 
    	Arrays.sort(dice); // must sort for scoreDice to work properly
    	System.out.print("Here is your sorted hand : ");
    	for (int i = 0; i < dice.length; i++) {
    		System.out.print(dice[i].getValue() + " ");
    	}
    	System.out.println();
        score.scoreDice(dice);    
    }  	
}
