import java.util.*;

public class Scorecard{
	private int[] scores;
	private boolean[] linesUsed;
	private int lowerLines = 7;
	private int numSides;
	
	public Scorecard(int numSides,boolean[] linesUsed) {
		scores = new int[linesUsed.length];
		this.linesUsed = linesUsed;
		this.numSides = numSides;
	}
	/**
	 * removes the selected line from displayed lines each turn
	 * @param lineNumber is the index of line selected
	 */
	public void select(int lineNumber ) {
		linesUsed[lineNumber] = true;
	}
	
	/**
	 * calculates the scores for each line of the scorecard
	 */
	public void calculate(int[] hand) {
		//upper scorecard
	    for (int dieValue = 1; dieValue <= numSides; dieValue++)
	    {
	        int currentCount = 0;
	        for (int diePosition = 0; diePosition < hand.length; diePosition++)
	        {
	            if (hand[diePosition] == dieValue)
	                currentCount++;
	        }
	        if(!linesUsed[dieValue-1]) scores[dieValue-1] = dieValue * currentCount;
	    }
	  //lower scorecard
	    // 3 of a kind
	    if(!linesUsed[scores.length-7]) {
		    if (maxOfAKindFound(hand) >= 3)
		    	scores[scores.length-7] = totalAllDice(hand);
		    else 
		    	scores[scores.length-7] = 0;
	    }
	    // 4 of a kind
	    if(!linesUsed[scores.length-6]) {
		    if (maxOfAKindFound(hand) >= 4)
		    	scores[scores.length-6] = totalAllDice(hand);
		    else 
		    	scores[scores.length-6] = 0;
	    }
	    //full house
	    if(!linesUsed[scores.length-5]) {
		    if(fullHouseFound(hand) || maxOfAKindFound(hand)>=5)
		    	scores[scores.length-5] = 25;
		    else 
		    	scores[scores.length-5] = 0;
	    }
	    //small straight
	    if(!linesUsed[scores.length-4]) {
		    if(maxStraightFound(hand) >= 4)
		    	scores[scores.length-4] = 30;
		    else 
		    	scores[scores.length-4] = 0;
	    }
	    //large straight
	    if(!linesUsed[scores.length-3]) {
		    if(maxStraightFound(hand) >= 5)
		    	scores[scores.length-3] = 40;
		    else 
		    	scores[scores.length-3] = 0;
	    }
	    //Yahtzee, implemented as max of a kind is the number of dice
	    if(!linesUsed[scores.length-2]) {
		    if(maxOfAKindFound(hand) == hand.length)
		    	scores[scores.length-2] = 50;
		    else
		    	scores[scores.length-2] = 0;
	    }
	    if(!linesUsed[scores.length-1]) {
	    	scores[scores.length-1] = totalAllDice(hand);
	    }
	}
	
	/**
	 * displays the contents of the scorecard line by line
	 */
	public void display() {
		int counter = 0;
		for(int i = 0; i < numSides; i++) {
			if(!linesUsed[counter]) System.out.println("[" + counter + "]Score " + scores[i] + " on the " + (i+1) + " line");
			counter++;
		}
		if(!linesUsed[counter+0])System.out.println("[" + (counter+0) + "]Score " + scores[counter+0] + " on the 3 of a Kind line");
		if(!linesUsed[counter+1])System.out.println("[" + (counter+1) + "]Score " + scores[counter+1] + " on the 4 of a Kind line");
		if(!linesUsed[counter+2])System.out.println("[" + (counter+2) + "]Score " + scores[counter+2] + " on the Full House line");
		if(!linesUsed[counter+3])System.out.println("[" + (counter+3) + "]Score " + scores[counter+3] + " on the Small Straight line");
		if(!linesUsed[counter+4])System.out.println("[" + (counter+4) + "]Score " + scores[counter+4] + " on the Large Straight line");
		if(!linesUsed[counter+5])System.out.println("[" + (counter+5) + "]Score " + scores[counter+5] + " on the Yahtzee line");
		if(!linesUsed[counter+6])System.out.println("[" + (counter+6) + "]Score " + scores[counter+6] + " on the Chance line\n");
		
	}
	
	/**
	 * displays final scorecard at the end
	 */
	public void displayFinal() {
		int totalScore = 0;
		for(int i = 0; i < linesUsed.length;i++) {
			linesUsed[i] = false;
			totalScore+=scores[i];
		}
		display();
		System.out.println("Total Score: " + totalScore);
	}
	/**
	 * returns the length of the longest straight found in hand
	 * @param hand is sorted int array of dice values
	 * @return the highest number of dice in a straight
	 */
	private int maxStraightFound(int hand[]){
	    int maxLength = 1;
	    int curLength = 1;
	    for(int counter = 0; counter < hand.length-1; counter++)
	    {
	        if (hand[counter] + 1 == hand[counter + 1] ) //jump of 1
	            curLength++;
	        else if (hand[counter] + 1 < hand[counter + 1]) //jump of >= 2
	            curLength = 1;
	        if (curLength > maxLength)
	            maxLength = curLength;
	    }
	    return maxLength;
	}
	
	/**
	 * this function returns true if the hand is a full house or false if it does not
	 * @param hand is sorted int array of dice values
	 * @return true if the hand contains a full house, false otherwise
	 */
	private boolean fullHouseFound(int hand[]){
	    boolean foundFH = false;
	    boolean found3K = false;
	    boolean found2K = false;
	    int currentCount ;
	    for (int dieValue = 1; dieValue <=numSides; dieValue++)
	    {
	        currentCount = 0;
	        for (int diePosition = 0; diePosition < hand.length; diePosition++)
	        {
	            if (hand[diePosition] == dieValue)
	                currentCount++;
	        }
	        if (currentCount >= 3)
	            found3K = true;
	        else if (currentCount >= 2)
	            found2K = true;
	    }
	    if (found2K && found3K)
	        foundFH = true;
	    return foundFH;
	}
	
	/**
	 * this function returns the count of the die value occurring most in the hand
	 * but not the value itself
	 * @param hand is sorted int array of dice values
	 * @return the maximum number of a kind found in the hand
	 */
	private int maxOfAKindFound(int hand[]){
	    int maxCount = 0;
	    int currentCount ;
	    for (int dieValue = 1; dieValue <=numSides; dieValue++)
	    {
	        currentCount = 0;
	        for (int diePosition = 0; diePosition < hand.length; diePosition++)
	        {
	            if (hand[diePosition] == dieValue)
	                currentCount++;
	        }
	        if (currentCount > maxCount)
	            maxCount = currentCount;
	    }
	    return maxCount;
	}
	
	/**
	 * this function returns the total value of all dice in a hand
	 * @param hand is sorted int array of dice values
	 * @return the sum of all dice values
	 */
	private int totalAllDice(int hand[]){
	    int total = 0;
	    for (int diePosition = 0; diePosition < hand.length; diePosition++)
	    {
	        total += hand[diePosition];
	    }
	    return total;
	}
	
}