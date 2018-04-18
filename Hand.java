import java.util.Arrays;

public class Hand {
	private int numDice;
	private Die[] dice;
	
	/**
	 * Constructor for Hand object
	 * @param numDice is number of dice in play
	 * @param numSides is number of sides per die
	 */
	public Hand(int numDice, int numSides) {
		this.numDice = numDice;
		dice = new Die[numDice];
		for(int i = 0; i < numDice; i ++) {
			dice[i] = new Die(numSides);
		}
	}
	
	/**
	 * Rolls all dice specified to not be kept
	 * @param keep is a 5-character string of y's or n's, each die in the corresponding
	 * 		n location is not rerolled, every one in a y location is rerolled
	 */
	public void roll(String keep) {
		char[] keptDice = keep.toCharArray();
		for(int i = 0; i < numDice; i++) {
			if(keptDice[i] == 'n')
				dice[i].roll();
		}
	}
	
	/**
	 * displays the contents of the hand unsorted
	 */
	public void display() {
		for(int i = 0; i < numDice; i++) {
			System.out.print(" " + dice[i].getValue());
		}
	}
	
	/**
	 * converts the dice values in hand to an int[] used by scorecard
	 * @return an int array of all the dice values
	 */
	public int[] returnSortedDice() {
		int[] sortedDice = new int[numDice];
		for(int i = 0; i < numDice; i++) {
			sortedDice[i] = dice[i].getValue();
		}
		Arrays.sort(sortedDice);
		return sortedDice;
	}
	
	/**
	 * displays the contents of the hand sorted
	 */
	public void displaySorted() {
		int[] sortedDice = returnSortedDice();
		for(int i = 0; i < numDice; i++) {
			System.out.print(" " + sortedDice[i]);
		}
	}

}