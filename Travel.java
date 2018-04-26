/* 
 * The class Travel is an item which calculates the
 * resources required for travel and the probability
 * of travel success for Space Travel Yahtzee 
 * Journey to Pluto
 * @authors Alexa Andrews, Gate Gutmann, Andrew Brodhead 
 * Date Created: April 14 2018
 * Last Modified: April 14 2018
*/

import java.util.Random; 

public class Travel {
	private final static int [][] REQUIREMENTS = {
										{0, 0, 0, 0, 0}, // no reqs to get to earth
										{70, 50, 95, 16, 49}, // reqs for Mars
										{50, 61, 75, 34, 56}, // reqs or Jupiter
										{20, 47, 68, 64, 78}, // reqs for Saturn
										{40, 68, 91, 56, 65}, // reqs for Uranus
										{90, 97, 74, 78, 84}, // reqs for Neptune
										{150, 102, 136, 92, 137} // reqs for Pluto
									};

    /* This method takes in a current and destination planet and returns an array 
     * representing the resources required for that travel 
    * @parameters an integer representing the current planet
    * 			  an integer representing the destination planet
    * @returns an integer array representing the amount of each resource required 
    * @throw - no exceptions are thrown by this function 
	*/
	public int[] getTravelRequirements(int current, int destination) {
		int [] requiredResources = REQUIREMENTS[current + 1]; // start with resources required for next planet
		for (int i = current + 2; i <= destination; i++) { // 
			for(int j = 0; j < 5; j++) {
				requiredResources[j] += REQUIREMENTS[i][j]; // add the resources 
			}
		}
		for (int k = 0; k < 5; k++) {
			int baseResources= requiredResources[k];
			baseResources = Math.round(baseResources * ((10 - (destination - current + 1)) / 10)); // subtract 10% resources    
		}																						  // per planet traveled
		return requiredResources;
	}

    /* This method takes in a current and destination planet as well as the 
     * player's roll. It uses this information to determine the percentage
     * probability of success 
    * @parameters An integer representing the current planet
    * 			  An integer representing the destination planet
    * 			  An integer representing the score on the roll
    * @returns an double .2 - 1 representing the probability of success 
    * @throw - no exceptions are thrown by this function 
	*/
	public double calculateSuccessRate(int current, int destination, int roll) {
		double baseProbability = .95 - .15*(destination - current - 1); // moving one planet has a base 95% chance, 
		double multiplier = roll / 1000;								//-15% for each additional planet 
		return baseProbability + baseProbability * multiplier;          // max score is 50 (yahtzee), max chance is 100%    
	}
	
	public boolean attemptTravel(double successRate) {
		Random numGenerator = new Random();
		double chance = numGenerator.nextDouble();  // returns a random integer 0 - .99 
		return successRate >= chance; // since chance is a random number 0 - .99 and can be anywhere in between with equal probability
	}								// there is a successRate% chance that it is less than success rate. 
}