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
	private String name;
	private Scores score; 
    private Dice [] dice; 
    private Resources resources;
    private Travel travels;
    private int location = 0;  // which planet the player is on. 0 is earth, 1 is Mars ... 6 is Pluto. -1 is dead 
    private int currentScore; // holds dice roll. reset each turn 
 
    private static final int NUM_TURNS = 3;
    private static final int NUM_DICE = 6;
    private static final int NUM_SIDES = 8;
    
    
	/*
    * This is the constructor, it takes two parameter numSides and numDice
    * It creates an array of Dice size numDice all with sides numSides
    * @parameters int numSides the number of sides, int numDice the amount of Dice
 	* @returns nothing 
 	* @throw - no exceptions are thrown by this function 
 	*/
    public Player(String aName) { 
    	score = new Scores(); // initialize the Scores
    	resources = new Resources();
    	travels = new Travel(); 
    	name = aName;
    	currentScore = -1; 
    	dice = new Dice [NUM_DICE];
    	for (int i = 0; i < NUM_DICE; i++) {
    		dice[i] = new Dice(NUM_SIDES); // initialize all dice
    	}
    }
    
    /* 
    * This function simulates the rolling of the dice in the hand
    * It rolls based on the array keep such that the corresponding indexed 
    * Dice is rolled if keep is 0 at that index 
    * @parameters boolean array keep to determine which Dice are not rolled
    * @returns the newly rerolled Dice array 
    * @throw - no exceptions are thrown by this function
    */
    public Dice[] rollDice(boolean[] keep) { // 1 = keep, 0 = roll 
    	
    	// checks index again keep, those lining
    	// up with a 1 are preserved
    	for (int i = 0; i < dice.length; i++) {
            if (!keep[i]) // if we don't keep it we roll it 
                dice[i].roll();
        }
	return dice;
    }
   
    /* 
    * This function simulates the rolling of the dice in the hand
    * It rolls all dice when no parameters are provided 
    * @parameters none
    * @returns the newly rerolled Dice array 
    * @throw - no exceptions are thrown by this function
    */
    public Dice[] rollDice() { // 1 = keep, 0 = roll 
    	for (int i = 0; i < dice.length; i++) {
                dice[i].roll();
        }
	return dice;
    }	
	
	
   public int [] getPlayerResources() {
   	return resources.getCurrentResources();
   }
	
    
    // all dice must be rolled before this method is called
    /* 
    * This function first sorts the Dice then scores them using its Scores.
    * It saves the highest score in currentScore 
    * @parameters none
    * @returns the highest Score 
    * @throw - no exceptions are thrown by this function
    */
    public int getScore() { 
    	if (currentScore == -1) calculateScore(); // if we haven't scored the dice yet, score them now
        return currentScore;
    }  	
    
    private void calculateScore() {
    	Arrays.sort(dice); // must sort for scoreDice to work properly
        currentScore = score.scoreDice(dice);  
    }
    
    /* 
    * This function returns the multipliers for each resource
    * so the GUI can display them 
    * @parameters none 
    * @returns int [] containing the multipliers for each resource
    * @throw - no exceptions are thrown by this function
    */
    public int [] getMultipliers() {
    	return resources.getMultipliers(location); // shows all possibilities for resources
    }
    
    /* 
    * This function aids in collection. It calculates the total
    * of each resource a player can attain based on their score and
    * the multipliers for that planet including any player bonus
    * This function returns the total for each resource
    * so the GUI can display them 
    * @parameters none 
    * @returns int [] containing the total possible for each resource
    * @throw - no exceptions are thrown by this function
    */
    public int [] getPossibleResources() {
    	int [] multipliers = resources.getMultipliers(location); // shows all possibilities for resources
    	int [] totalResources = new int [5];
    	for (int resourceType = 0; resourceType < 5; resourceType++) {
    		totalResources[resourceType] = multipliers[resourceType] * currentScore;  
    	}
    	return totalResources;
    }
    
    /* 
    * This function adds resources of a given type to a player's stores 
    * @parameters int resourceType the user wishes to collect 
    * @returns nothing
    * @throw - no exceptions are thrown by this function
    */
    public void collectResources(int resourceType) {
    	resources.addResource(resourceType, currentScore, location);
    	currentScore = -1; 
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
    * This function in used prior to travel. It will take the destination as a parameter. 
    * It will calculate the success rate so the user can determine if they wish to travel
    * @parameters an int destination planet
    * @returns double representing the chance of success 
    * @throw - no exceptions are thrown by this function
    */
    public double tryTravel(int destination) {
    	return travels.calculateSuccessRate(location, destination, currentScore);
    }	
    
    
    /*
    * If the user wishes to travel, their success rate will be used with a random number generator
    * to see if they make it. If they do, the appropriate resources will be taken from their stores.
    * This returns a boolean true if travel is successful, false otherwise
    * @parameters double representing the success rate, and an int representing destination planet
    * @returns boolean true if travel is successful, false otherwise 
    * @throw - no exceptions are thrown by this function
    */
    public boolean commenceTravel(double successRate, int destination) {
    	boolean isSuccessful = travels.attemptTravel(successRate);
    		if (isSuccessful) {
    			resources.useResourcesForTravel(travels.getTravelRequirements(location, destination));
    			location = destination; 
    			currentScore = -1; // reset currentScore 
    		} else {
    			location = -1; 
    		}
    	return isSuccessful;
    }

    /*
     * This method returns the player's name
     * @parameters none
     * @returns String representing the player's name
     * @throw - no exceptions are thrown by this function 
     */
	public String getName() {
		return name;
	}
	
    /*
     * This method returns the player's location
     * -1 is dead, 0 is Earth... 6 is Pluto 
     * @parameters none
     * @returns int representing the player's planet
     * @throw - no exceptions are thrown by this function 
     */
	public int getLocation() {
		return location;
	}
	
    /*
     * This method returns the player's location as a string
     * -1 is dead, 0 is Earth... 6 is Pluto 
     * @parameters none
     * @returns String representing the player's planet
     * @throw - no exceptions are thrown by this function 
     */
	public String getPlanet() {
		String planetName;
		switch (location) {
			case 0: planetName = "Earth";
					break;
			case 1: planetName = "Mars";
					break;
			case 2: planetName = "Jupiter";
					break;
			case 3: planetName = "Saturn";
					break;
			case 4: planetName = "Uranus";
					break;
			case 5: planetName = "Neptune";
					break;
			case 6: planetName = "Pluto";
					break;
			default: planetName = "Dead";
					break; 
		}
		return planetName;
	}
	
    /*
     * This method returns the given int as a string
     * -1 is dead, 0 is Earth... 6 is Pluto 
     * @parameters int planet
     * @returns String representing the planet
     * @throw - no exceptions are thrown by this function 
     */
	public String getPlanet(int planet) {
		String planetName;
		switch (planet) {
			case 0: planetName = "Earth";
					break;
			case 1: planetName = "Mars";
					break;
			case 2: planetName = "Jupiter";
					break;
			case 3: planetName = "Saturn";
					break;
			case 4: planetName = "Uranus";
					break;
			case 5: planetName = "Neptune";
					break;
			case 6: planetName = "Pluto";
					break;
			default: planetName = "Dead";
					break; 
		}
		return planetName;
	}
}
