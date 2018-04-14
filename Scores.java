import java.util.*;

/* 
 * The class Scores is an item which calculates the
 * highest possible score from a hand of dice
 * Journey to Pluto
 * @authors Alexa Andrews, Gate Gutmann, Andrew Brodhead 
 * Date Created: March 7 2018
 * Last Modified: April 14 2018
*/

public class Scores {
	private final static int NUM_SIDES = 8;
	
	/*
    * Scores uses a default constructor.
    * @parameters none
	* @returns nothing 
	* @throw - no exceptions are thrown by this function 
	*/
	public Scores () {}
	
    /* This method takes in an array of dice and returns the greatest possible score
    * @parameters An array of dice for which scores will be calculated
    * @returns an int representing the highest possible score 
    * @throw - no exceptions are thrown by this function 
	*/
	public int scoreDice(Dice [] dice) {
		//upper Scores
		int max = 0; // track the maximum score
        for (int dieValue = 1; dieValue <= NUM_SIDES; dieValue++)
        {
            int currentCount = 0;
            for (int diePosition = 0; diePosition < dice.length; diePosition++)
            {
                if (dice[diePosition].getValue() == dieValue)
                    currentCount++;
            }
            if (max < dieValue * currentCount) max = dieValue * currentCount; // if current is greater than max, it is new max 
        }
        
        int total = totalAllDice(dice); // used repetitively
        
        // lower Scores 
        
        if (maxOfAKindFound(dice) >= 3) {
            if (total > max) max = total; // 3 of a kind gives the total as a score. if total is greater than
            							// the current maximum, it becomes the new maximum
        }
        
        // there is no need for four of a kind here since it includes three of a kind and is the same score
        
        if (fullHouseFound(dice)) {
            if (max < 25) max = 25; // full house grants 25 points
        }
        
        if (maxStraightFound(dice) >= 4) {
        	if (max < 30) max = 30; // small straight is 30 points
		} 
        
        if (maxStraightFound(dice) >= 5) {
        	if (max < 40) max = 40; // large straight is 40 points 
        }
        	
        if (maxOfAKindFound(dice) >= 5) {
        	if (max < 50) max = 50; // yahtzee is 50 points 
        }
        	
        if (total > max) max = total; // chance  
        
        return max;
	}
	
	/* this function returns the count of the die value occurring most in the hand
	* but not the value itself. It is a helper for scoreDice
	*  @parameters An array of dice in which the max of a kind will be found
    * @returns an int representing the max amount of any one kind
    * @throw - no exceptions are thrown by this function 
	*/ 
	private int maxOfAKindFound(Dice [] dice) {
	      int maxCount = 0;
	      int currentCount ;
	      for (int dieValue = 1; dieValue <= dice[0].getSides(); dieValue++)
	      {
	          currentCount = 0;
	          for (int diePosition = 0; diePosition < dice.length; diePosition++)
	          {
	              if (dice[diePosition].getValue() == dieValue)
	                  currentCount++;
	          }
	          if (currentCount > maxCount)
	              maxCount = currentCount;
	      }
	      return maxCount;
	  }
	
	 /* 
	  * this function returns the total value of all dice in a hand 
	  * It is a helper for scoreDice
	  * @parameters An array of dice to be totaled
      * @returns an int representing the total value of the dice
      * @throw - no exceptions are thrown by this function 
	  */ 
	  private int totalAllDice(Dice[] dice) {
	      int total = 0;
	      for (int diePosition = 0; diePosition < dice.length; diePosition++)
	      {
	          total += dice[diePosition].getValue();
	      }
	      return total;
	  }
	  
	  /*
	  * this function returns the length of the longest straight 
	  * found in a hand. It is a helper for scoreDice
	  * @parameters An array of dice in which the max straight will be found
      * @returns an int representing the length of the longest straight
      * @throw - no exceptions are thrown by this function
	  */
	  private int maxStraightFound(Dice [] dice) {
		    int maxLength = 1;
		    int curLength = 1;
		    for(int counter = 0; counter < 4; counter++)
		    {
		        if (dice[counter].getValue() + 1 == dice[counter + 1].getValue()) //jump of 1
		            curLength++;
		        else if (dice[counter].getValue() + 1 < dice[counter + 1].getValue()) //jump of >= 2
		            curLength = 1;
		        if (curLength > maxLength)
		            maxLength = curLength;
		    }
		    return maxLength;
		}
	  
	    /*
	    * this function returns true if the hand is a full house or false if it 
	    * does not. It is a helper for scoreDice
	    * @parameters An array of dice to see if there is a full house in them
	    * @returns a boolean true if there is a full house, false otherwise
	    * @throw - no exceptions are thrown by this function
	    */
		boolean fullHouseFound(Dice [] dice) {
		    boolean found3K = false;
		    boolean found2K = false;
		    int currentCount ;
		    // go through each possible die value to see how many in the hand have it
		    for (int dieValue = 1; dieValue <= dice[0].getSides(); dieValue++)
		    {
		        currentCount = 0;
		        // loop through the given hand
		        for (int diePosition = 0; diePosition < dice.length; diePosition++)
		        {
		            if (dice[diePosition].getValue() == dieValue)
		                currentCount++; // how many dice have the value of outer for loop
		        }   
		        if (currentCount == 2) // if there are two of a kind
		            found2K = true;
		        if (currentCount >= 5) // if there are 5 or more of a kind, full house
		        	return true;
		        if (currentCount == 3 || currentCount == 4) {// if there are three of a kind
		            if (!found3K) { // if we have not yet found 3 of a kind, we have now
		            	found3K = true;
		            } else { // we have found 3 of a kind previously and now have 2 of a kind
		            	return true; // as well, so a full house has been found
		            }
		        }
		    }

		    return found2K && found3K; // if there are two and three of a kind
		}	
}
