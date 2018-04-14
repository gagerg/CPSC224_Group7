/* 
 * The class Player simulates a Player in the game of Yahtzee. In a full
 * game there could be multiple players and each will have a name. For now,
 * each has a Scores and a collection of Dice. The number of dice and the
 * amount of sides for each Dice are determined when the Player is constructed. 
 * The player can roll their collection of dice all at once or excluding specific 
 * ones based on an input string and can take a turn in Yahtzee. 
 * Journey to Pluto
 * @authors Alexa Andrews, Gate Gutmann, Andrew Brodhead 
 * Date Created: March 7 2018
 * Last Modified: April 14 2018
*/

import java.util.Arrays;
import java.util.Scanner;

public class Player {
	// private String name; // will be used later for the full game
	private Scores score; 
    private Dice [] dice; 
    private int location = 0;  // which planet the player is on. 0 is earth, 1 is Mars ... 6 is Pluto
    private static final int NUM_TURNS = 3;
    private int currentScore; // holds dice roll. reset each turn 
    
	/*
    * This is the constructor, it takes two parameter numSides and numDice
    * It creates an array of Dice size numDice all with sides numSides
    * @parameters int numSides the number of sides, int numDice the amount of Dice
 	* @returns nothing 
 	* @throw - no exceptions are thrown by this function 
 	*/
    public Player(int numDice, int numSides) { // String name) { 
    	score = new Scores(); // initialize the Scores, for full game
    	dice = new Dice [numDice];
    	for (int i = 0; i < numDice; i++) {
    		dice[i] = new Dice(numSides); // initialize all dice
    	}
    }
    
    /* 
    * This function simulates the rolling of the dice in the hand
    * It rolls based on the array keep such that the corresponding indexed 
    * Dice is rolled if keep is 0 at that index 
    * @parameters boolean array keep to determine which Dice are not rolled
    * @returns nothing
    * @throw - no exceptions are thrown by this function
    */
    public void rollDice(boolean[] keep) { // 1 = keep, 0 = roll 
    	
    	// checks index again keep, those lining
    	// up with a 1 are preserved
    	for (int i = 0; i < dice.length; i++) {
            if (!keep[i]) // if we don't keep it we roll it 
                dice[i].roll();
        }
    }
    
    /* 
    * This function allows a user a turn of
    * three rolls to determine their score
    * @parameters none
    * @returns nothing
    * @throw - no exceptions are thrown by this function
    */
    public void takeTurn(Scanner in) {
    	int currentTurn = 1;
    	boolean [] keep = new boolean[dice.length]; // keep string as long as amount of dice
    	boolean [] toKeepAll = new boolean[dice.length];
    	for (int i = 0; i < dice.length; i++) {
    		keep[i] = false;
    		toKeepAll[i] = true;
    	}
    	

    	
    	while (currentTurn < (NUM_TURNS + 1) && !(Arrays.equals(keep, toKeepAll))) { // if we are not out of turns
    		rollDice(keep);							 							// and the user is not keeping all Dice
    		if (currentTurn < NUM_TURNS) { // if there are turns remaining
    			System.out.print("Enter the dice to keep (y or n) ");
    			keep = in.nextLine(); // convert to take user input through GUI 
    		}	
    		currentTurn++; // rolling the Dice uses a turn
    	}
    	getScore(); // save the score at the end
    }
   
    
    // all dice must be rolled before this method is called
    /* 
    * This function first sorts the Dice then scores them using its Scores.
    * It saves the highest score in currentScore 
    * @parameters none
    * @returns nothing
    * @throw - no exceptions are thrown by this function
    */
    public void getScore() { 
    	Arrays.sort(dice); // must sort for scoreDice to work properly
        currentScore = score.scoreDice(dice);    
    }  	
}
