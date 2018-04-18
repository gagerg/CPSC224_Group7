/* 
 * The class Resource is an item which tracks a player's
 * overall resources and resources gained on a planet. It
 * also maintains the modifiers for each planet.
 * Journey to Pluto
 * @authors Alexa Andrews, Gage Gutmann, Andrew Brodhead 
 * Date Created: April 14 2018
 * Last Modified: April 14 2018
*/

public class Resources {
	private final static int [][] MULTIPLIERS = {
										{2, 5, 5, 4, 2}, // multipliers for earth
										{3, 3, 1, 3, 3}, // multipliers for Mars
										{2, 7, 2, 7, 4}, // multipliers or Jupiter
										{5, 7, 5, 4, 6}, // multipliers for Saturn
										{9, 6, 7, 2, 7}, // multipliers for Uranus
										{5, 2, 3, 6, 2}, // multipliers for Neptune
										{0, 0, 0, 0, 0} // no multipliers for Pluto // Shouldnt need this
									};
	private int [] resources; 
	private final int [] onPlanetResources;
        private final int [] requiredEarth = {150, 450, 400, 200, 100}; //Might have to fix these if to high
        private final int [] requiredMars = {350, 300, 200, 150, 200};
        private final int [] requiredJupiter = {200, 300, 100, 150, 50};
        private final int [] requiredSaturn = {500, 375, 300, 350, 200};
        private final int [] requiredUranus = {350, 400, 450, 250, 300}; //Hehe
        private final int [] requiredNeptune = {400, 300, 200, 100, 350};
        private final int [] requiredPluto = {500, 500, 500, 500, 500};
        
        
	
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
	
    /* This method takes in a score and current planet to display what resources
     * are available in which amounts
    * @parameters an integer representing the current planet
    * 			  an integer representing the score from rolling the dice
    * @returns nothing
    * @throw - no exceptions are thrown by this function 
	*/
	public void displayPossibleResources(int score, int planet) {
		int bonus;
		int totalMultiplier; // screen output  
		int totalOfResource; // screen output 
		for (int resourceType = 0; resourceType < 5; resourceType++) { // 
			bonus = (int)Math.floor(onPlanetResources[resourceType] / (MULTIPLIERS[planet][resourceType] * 10)); // bonus is determined by planet's multiplier 
			totalMultiplier = bonus + MULTIPLIERS[planet][resourceType];							  // +1x for every 10x(Planet Multiplier) 
			totalOfResource = (MULTIPLIERS[planet][resourceType] + bonus)* score;
			switch(resourceType) { // change this to reflect the appropriate output to the GUI 
				case 0: 
					System.out.println("Parts: " + score + " x " + totalMultiplier + " = " + totalOfResource);
					break;
				case 1: 
					System.out.println("Fuel: " + score + " x " + totalMultiplier + " = " + totalOfResource);
					break;
				case 2: 
					System.out.println("Money: " + score + " x " + totalMultiplier + " = " + totalOfResource);
					break;
				case 3: 
					System.out.println("Necessities: " + score + " x " + totalMultiplier + " = " + totalOfResource);
					break;
				case 4: 
					System.out.println("Titanium: " + score + " x " + totalMultiplier + " = " + totalOfResource);
					break;
			}
		}
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
	 * @throw - no excepions are thrown by this function
	 */
	public boolean canTravel(int [] necessaryResources) {
		boolean allowTravel = true;
		for (int i = 0; i < 5; i++) {
			if (resources[i] < necessaryResources[i]) allowTravel = false;
		}
		return allowTravel;
	}
	
	/* This method checks to ensure a player has enough resources to travel the deducts
	 * those resources from the player's total. It also zeroes out the on-planet resources
	 * since the player is changing planets 
	 * @parameters an integer array representing the necessary resources for travel
	 * @returns nothing
	 * @throw - InsufficientResourcesException if the player doesnt have enough resources to travel
	 */
	public void useResourcesForTravel(int [] usedResources) throws Exception { // I'm not good with exceptions
		if (!canTravel(usedResources)) 
		for (int i = 0; i < 5; i++) {
			resources[i] -= usedResources[i];
			onPlanetResources[i] = 0; // travel is occurring; zero out onPlanetResources 
		}
		
		
	}
}
