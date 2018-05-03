/* 
 * The class Resource is an item which tracks a player's
 * overall resources and resources gained on a planet. It
 * also maintains the modifiers for each planet.
 * Journey to Pluto
 * @authors Alexa Andrews, Gate Gutmann, Andrew Brodhead 
 * Date Created: April 14 2018
 * Last Modified: April 25 2018
*/

public class Resources {
	private final static int [][] MULTIPLIERS = {
										{8, 5, 11, 7, 5}, // multipliers for earth
										{3, 11, 4, 3, 6}, // multipliers for Mars
										{11, 7, 2, 7, 4}, // multipliers or Jupiter
										{5, 7, 5, 11, 6}, // multipliers for Saturn
										{9, 6, 7, 2, 7}, // multipliers for Uranus
										{5, 2, 2, 6, 11}, // multipliers for Neptune
										{0, 0, 0, 0, 0} // no multipliers for Pluto
									};
	private int [] resources; 
	private int [] onPlanetResources;

	
	/*
    * Resources initializes its resource arrays to size 5 for 5 resources
    * @parameters none
	* @returns nothing 
	* @throw - no exceptions are thrown by this function 
	*/
	public Resources() {
		resources = new int [5];
		onPlanetResources = new int [5];
	}
	
    /* This method takes in a current planet and returns the resource modifiers
     * for that planet, taking into account any bonus the player has accrued  
    * @parameters an integer representing the current planet
    * @returns an int array representing the total multipliers for each resource
    * @throw - no exceptions are thrown by this function 
	*/
	public int [] getMultipliers(int planet) {
		int bonus;
		int [] totalMultipliers = new int [5];
		for (int resourceType = 0; resourceType < 5; resourceType++) { // 
			bonus = (int)Math.floor(onPlanetResources[resourceType] / (MULTIPLIERS[planet][resourceType] * 10)); // bonus is determined by planet's multiplier 
			totalMultipliers[resourceType] = bonus + MULTIPLIERS[planet][resourceType];							  // +1x for every 10x(Planet Multiplier) 
		}
		return totalMultipliers;
	}
	
    /* This method adds collected resources to the player's storage
     * It adds them both to the on-planet and total 
    * @parameters an integer representing the current planet
    * 			  an integer representing the player's score
    * 			  an integer representing the resource type chosen
    * @returns nothing  
    * @throw - no exceptions are thrown by this function 
	*/
	public void addResource(int resourceType, int score, int planet) {
		int totalOfResource = (MULTIPLIERS[planet][resourceType] + (int)Math.floor(onPlanetResources[resourceType] / 
				(MULTIPLIERS[planet][resourceType] * 10))) * score; // see displayPossibleResources for calculation info 
		resources[resourceType] += totalOfResource;
		onPlanetResources[resourceType] += totalOfResource; 
	}
	
	/* This method checks to ensure a player has enough resources to travel
	 * @parameters an integer array representing the necessary resources for travel
	 * @returns a boolean true if the player has sufficient resources, false if otherwise
	 * @throw - no exceptions are thrown by this function
	 */
	public boolean canTravel(int [] necessaryResources) {
		boolean allowTravel = true;
		for (int i = 0; i < 5; i++) {
			if (resources[i] < necessaryResources[i]) allowTravel = false;
		}
		return allowTravel;
	}
	
	/* This method deducts those resources required for travel
	 * from the player's total. It also zeroes out the on-planet resources
	 * It should already have been determined that the user has enough resources
	 * for travel before calling this method 
	 * since the player is changing planets.
	 * @parameters an integer array representing the necessary resources for travel
	 * @returns nothing
	 * @throw - InsufficientResourcesException if the player doesnt have enough resources to travel
	 */
	public void useResourcesForTravel(int [] usedResources) { 
		for (int i = 0; i < 5; i++) {
			resources[i] -= usedResources[i];
			onPlanetResources[i] = 0; // travel is occurring; zero out onPlanetResources 
		}	
	}
	
	/* This method gives its user the current resources 
	 * @parameters none
	 * @returns an integer array representing the current Resources 
	 * 			Resources are: parts > fuel > money > necessities > titanium
	 * @throw - no exceptions are thrown by this function
	 */
	public int [] getCurrentResources () {
		return resources;
	}
}
