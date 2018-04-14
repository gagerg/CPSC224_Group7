import java.util.*;

/* 
 * The class ScoreCard is an item which calculates player scores based on 
 * an array of Dice. It tracks player scores and lists for them where they can
 * score and what scores are possible based on their roll. 
 * It allows a player to select a score and records it for them.
 * It prints out a player's score in all categories (-1 for those unfilled)
 * Assignment 6
 * @author Alexa Andrews
 * Date Created: March 7 2018
 * Last Modified: March 22 2018
*/

public class ScoreCard {
	private int[]upperScores; // stores the score in top to bottom order as seen on the the Yahtzee ScoreCard
	private int[]lowerScores;
	private int[]currentScores; // stores the scores of the current roll 
	
	/*
    * This is the constructor, which creates a ScoreCard based on the 
    * number of sides on the dice initialized to -1 as well as an array to 
    * hold a player's currentScores. .
    * @parameters none
	* @returns nothing 
	* @throw - no exceptions are thrown by this function 
	*/
	public ScoreCard (int sidesOfDice) {
		upperScores = new int [sidesOfDice]; // the array size will be based on the number of sides on the dice
		lowerScores = new int [7];
		currentScores = new int [sidesOfDice + 7];
		for (int i = 0; i < upperScores.length; i++) {
			upperScores[i] = -1; // initialized to -1 to tell which 
		}                      // positions have not yet been scored
		for (int i = 0; i < 7; i++) {
			lowerScores[i] = -1;
		}
	}
	
	
	
    /* This method takes in an array of dice and prints to the screen the possible scores
    * for each Yahtzee category based on that roll
    * It stores the results in currentScores
    * @parameters An array of dice for which scores will be calculated
    * @returns nothing
    * @throw - no exceptions are thrown by this function 
	*/
	public void scoreDice(Dice [] dice) {
		//upper ScoreCard
		int line = 0; 
        for (int dieValue = 1; dieValue <= dice[0].getSides(); dieValue++, line++)
        {
            int currentCount = 0;
            for (int diePosition = 0; diePosition < dice.length; diePosition++)
            {
                if (dice[diePosition].getValue() == dieValue)
                    currentCount++;
            }
            System.out.println(line + ": Score " + dieValue * currentCount + " on the " + dieValue + " line");
            currentScores[line] = dieValue * currentCount; // track the scores for each line
        }
        
        int total = totalAllDice(dice); // used repetitively
        
        // lower ScoreCard 
        System.out.print(line + ": ");
        if (maxOfAKindFound(dice) >= 3) {
            currentScores[line] = total;
            System.out.println("Score " + total + " on the 3 of a Kind line");
        } else {
        	System.out.println("Score 0 on the 3 of a Kind line");
        	currentScores[line] = 0;
        }
        line++;
        
        System.out.print(line + ": ");
        if (maxOfAKindFound(dice) >= 4) {
        	currentScores[line] = total;
            System.out.println("Score " + total + " on the 4 of a Kind line");
        } else {
        	System.out.println("Score 0 on the 4 of a Kind line");
        	currentScores[line] = 0;
        }
        line++;
        
        System.out.print(line + ": ");
        if (fullHouseFound(dice)) {
            System.out.println("Score 25 on the Full House line");
            currentScores[line] = 25;
        } else {
        	System.out.println("Score 0 on the Full House line");
        	currentScores[line] = 0;
        }
        line++; 

        System.out.print(line + ": ");
        
        if (maxStraightFound(dice) >= 4) {
        	System.out.println("Score 30 on the Small Straight line");
        	currentScores[line] = 30;
		} else {
        	System.out.println("Score 0 on the Small Straight line");
			currentScores[line] = 0;
		}
        line++;
        
        System.out.print(line + ": ");
        
        if (maxStraightFound(dice) >= 5) {
        	System.out.println("Score 40 on the Large Straight line");
        	currentScores[line] = 40;
		} else {
        	System.out.println("Score 0 on the Large Straight line");
        	currentScores[line] = 0;
		}
        line++;

        System.out.print(line + ": ");
        if (maxOfAKindFound(dice) >= 5) {
        	System.out.println("Score 50 on the Yahtzee line");
        	currentScores[line] = 50;
		} else {
        	System.out.println("Score 0 on the Yahtzee line");
        	currentScores[line] = 0;
		}
        line++;
        
        System.out.print(line + ": ");
        currentScores[line] = total; 
        System.out.println("Score " + total + " on the Chance line");
	}
	
	/*
    * This function lists which lines are unfilled
    * @parameters this function requires no parameters
 	* @returns void
 	* @throw - no exceptions are thrown by this function 
 	*/
	public void displayOpenLines() {
		System.out.print("You may score on the following lines: ");
		int sides = upperScores.length;
		for (int i = 0; i < sides; i++) {
			if (upperScores[i] == -1) {
				System.out.print(i + " ");
			}
		}
		for (int i = 0; i < 7; i++) {
			if (lowerScores[i] == -1)
				System.out.print((i + sides) + " ");
		}
		System.out.println();	
	}
	
	/*
    * This function allows a user to select their score
    * It ensures the line selected is not already filled
    * and repeats if the user selects an unavailable line
    * @parameters this function takes a scanner parameter
    * because it uses user input
 	* @returns void
 	* @throw - no exceptions are thrown by this function 
 	*/
	public void selectScore(Scanner in) {
		System.out.println("For which line would you like to apply a score?");
		int line = in.nextInt();
		in.nextLine(); // clear the newline character 
		if (line < upperScores.length && upperScores[line] == -1) { // if on the upper ScoreCard
			upperScores[line] = currentScores[line];               // and available
		} else if (lowerScores[line - upperScores.length] == -1 ){ // spot is available
			lowerScores[line - upperScores.length] = currentScores[line];  
		} else {
			System.out.println("That line is already scored! Please select a different one");
			selectScore(in);
		}
	}
	
	/*
    * This function prints the current status of the ScoreCard
    * to the screen. If a field has not been scored it will
    * be displayed as -1. 
    * @parameters this function requires no parameters
 	* @returns nothing
 	* @throw - no exceptions are thrown by this function 
 	*/
	public void finalScore() {
		int total = 0;
		for (int i = 0; i < upperScores.length; i++) {
			System.out.println("Your score for " + (i + 1) + "s is " + upperScores[i]);
			total += upperScores[i];
		}
		int i = 0; 
		System.out.println("Your score for 3 of a kind is " + lowerScores[i]);
		total += lowerScores[i];
		i++;
		System.out.println("Your score for 4 of a kind is " + lowerScores[i]);
		total += lowerScores[i];
		i++;
		System.out.println("Your score for a full house is " + lowerScores[i]);
		total += lowerScores[i];
		i++;
		System.out.println("Your score for a small straight is " + lowerScores[i]);
		total += lowerScores[i];
		i++;
		System.out.println("Your score for a large straight is " + lowerScores[i]);
		total += lowerScores[i];
		i++;
		System.out.println("Your score for Yahtzee is " + lowerScores[i]);
		total += lowerScores[i];
		i++;
		System.out.println("Your score for chance is " + lowerScores[i]);
		total += lowerScores[i];
		System.out.println("Your total score is " + total);
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
