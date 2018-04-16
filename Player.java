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
    private Resources resources;
    private Travel travels; 
    
	/*
    * This is the constructor, it takes two parameter numSides and numDice
    * It creates an array of Dice size numDice all with sides numSides
    * @parameters int numSides the number of sides, int numDice the amount of Dice
 	* @returns nothing 
 	* @throw - no exceptions are thrown by this function 
 	*/
    public Player(int numDice, int numSides) { // String name) { 
    	score = new Scores(); // initialize the Scores
    	resources = new Resources();
    	travels = new Travel(); 
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
    
    /* 
    * This function carries out the necessary operations for collecting resources
    * It first displays the resources and their amounts possible to the user
    * It then allows the user to select which resource they want
    * It then adds the appropriate amount of that resource to the player's stock
    * @parameters none
    * @returns nothing
    * @throw - no exceptions are thrown by this function
    */
    public void collectResources() {
    	resources.displayPossibleResources(currentScore, location); // shows all possibilities for resources
    	
    	int resourceType = 5; // take user input for what resource to get from the GUI in place of this line
    	
    	resources.addResource(resourceType, currentScore, location);
    }
    
    
    /* 
    * This function checks whether the player should even have the option to travel
    * The player only gets option to travel if they can at least travel to the next planet 
    * The availability is determined by the amount of resources the user possesses 
    * @parameters none
    * @returns boolean false if the player has insufficient resources to travel, true otherwise
    * @throw - no exceptions are thrown by this function
    */
    public boolean checkTravel() {
    	return resources.canTravel(travels.getTravelRequirements(location, location + 1));
    }
    
    
    /* 
    * This function checks which planets the player can travel to
    * The player only gets option to travel if they can at least travel to the next planet 
    * Further planets are calculated based on the total of the player's resources
    * The availability is determined by the amount of resources the user possesses 
    * This could be modified to work with the GUI to only allow players to travel to planets
    * which they have enough resources for but for now returns an array so another method
    * could do that. we will decide which route is best 
    * @parameters none
    * @returns boolean array with indices corresponding to planets including either which is always false
    * each array slot is false if the player has insufficient resources to travel to that planet, true otherwise
    * @throw - no exceptions are thrown by this function
    */
    public boolean[] availableForTravel() {
    	boolean [] locations = new boolean [7]; // array indices correspond to travel availability for the 7 planets 
    	for (int i = 0; i <= location; i++) {
    		locations[i] = false; // the planet may not travel backwards or to their current planet
    	}
    	locations[location + 1] = true; // we are on the planet select screen for this function so the player was able to travel
    	for (int destination = location + 2; destination < 7; destination++) {
    		locations[destination] = resources.canTravel(travels.getTravelRequirements(location, destination));
    		if (!locations[destination]) { // if they cannot travel to the current destination, they cannot 
    			for (++destination; destination < 7; destination ++) { // travel to any further planets 
    				locations[destination] = false;
    			}
    		}
    	}
    	return locations; 
    }
    
    
    /* 
    * This function allows the player to travel. It will need to take user input for the destination 
    * or have that changed to a parameter and get it elsewhere. 
    * It will calculate the success rate, and needs to display it to the user then take input
    * as to whether or not the user would like to proceed with travel. If this is not possible this 
    * method may need to be broken up so this part can be done elsewhere. 
    * If the user wishes to travel, their success rate will be used with a random number generator
    * to see if they make it. If they do, the appropriate resources will be taken from their stores. 
    * This could be modified to work with the GUI to show the success or failure of the travel
    * This returns a boolean so that the GUI work on the result can be done elsewhere if desired
    * @parameters none
    * @returns boolean true if travel is successful, false otherwise 
    * @throw - no exceptions are thrown by this function
    */
    public boolean commenceTravel() {
    	int destination = 6; // take in user input via GUI here for destination planet 
    	double successRate = travels.calculateSuccessRate(location, destination, currentScore);
    	boolean isSuccessful; 
    	System.out.println("Your success rate is " + successRate + ". Would you like to travel?");
    	// take user input via GUI as to whether user still wishes to travel
    	if (true) { // if user wishes to travel
    		isSuccessful = travels.attemptTravel(successRate);
    		if (isSuccessful) {
    			resources.useResourcesForTravel(travels.getTravelRequirements(location, destination));
    			// this method could use the GUI here to display a success message if desired
    		} // else {} this method could use the GUI to display a failure and death message 
    	}
    	return isSuccessful;
    }
}
