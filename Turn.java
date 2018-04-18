import java.util.Scanner;

public class Turn{

	private int rolls;
	private int numSides;
	private int numDice;
	private Hand h;
	
	/**
	 * Constructor for Turn object
	 * @param rollsPerTurn is the number of rolls(initial roll and re-rolls) per turn
	 * @param numDice is the number of dice in play
	 * @param numSides is the number of sides per die
	 */
	public Turn(int rollsPerTurn, int numDice, int numSides) {
		rolls = rollsPerTurn;
		this.numSides = numSides;
		this.numDice = numDice;
		h = new Hand(numDice, numSides);
	}
	
	/**
	 * plays through one turn of n rolls
	 */
	public int[] play() {
		Scanner input = new Scanner(System.in);
		StringBuilder initialKeep = new StringBuilder();
		StringBuilder terminalKeep = new StringBuilder();
		for(int i=0; i<numDice;i++) {
			initialKeep.append('n');
			terminalKeep.append('y');
		}
		String keep = initialKeep.toString();
		
		h.roll(keep);
		System.out.print("Your roll was: ");
		h.display();
		//loop while there are still rolls remaining
		while(rolls > 1 && !keep.equals(terminalKeep.toString())) {
			rolls--;
			System.out.print("\nenter dice to keep (y or n) ");
			keep = input.nextLine();
			h.roll(keep);
			System.out.print("Your roll was: ");
			h.display();
		}
		System.out.print("\nHere is your sorted hand: ");
		h.displaySorted();
		System.out.println();
		return h.returnSortedDice();
		
	}
}