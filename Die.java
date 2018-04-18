import java.util.Random;

public class Die implements Comparable{
	private int sides;
	private int value;
	private Random r;
	
	/**
	 * constructor for Die object
	 * @param numSides is number of sides on this die
	 */
	public Die(int numSides) {
		sides = numSides;
		r = new Random();
	}
	
	/**
	 * sets the die value to a random number between 1 and numSides
	 */
	public void roll() {
		value = r.nextInt(sides)+1;
	}
	
	/**
	 * outputs the value of the die
	 * @return value of the die
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Allows die objects to be compared to each other so that they can be used in Arrays.sort()
	 */
	public int compareTo(Object otherObject) {
		Die d = (Die) otherObject;
		if(d.getValue() == this.getValue()) return 0;
		else if(d.getValue() > this.getValue()) return -1;
		else return 1;
	}
}