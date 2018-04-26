/* 
 * The class Dice simulates a single die for which a player can
 * select a number of sides. It uses a random number generator to 
 * roll this die amongst the given sides and the user can roll the die, 
 * retrieve the current roll, and retrieve the number of sides.
 * This class implements Comparable such that a collection of Dice
 * may be sorted based on the current roll on each die. 
 * Journey to Pluto
 * @authors Alexa Andrews, Gate Gutmann, Andrew Brodhead 
 * Date Created: March 7 2018
 * Last Modified: April 14, 2018
*/

import java.util.*;

public class Dice implements Comparable{
	private int sides; 
	private int faceUp;
	
	/*
    * This is the constructor, it takes a single parameter numSides
    * It creates a die with the number of sides given
    * @parameters int numSides the number of sides
	* @returns nothing 
	* @throw - no exceptions are thrown by this function 
	*/
	public Dice(int numSides) {
		sides = numSides;
	}
	
	/*
	* (non-Javadoc)
	* @see java.lang.Comparable#compareTo(java.lang.Object)
	* This function takes an Object, in this case of type Dice
	* and compares it to the implicit parameter based on the 
	* value of faceUp. 
	* @parameters An object to be compared to the implicit parameter
    * @returns an int representing which parameter is greater
    * @throw - no exceptions are thrown by this function 
	*/
    public int compareTo(Object otherObject) {
    	Dice other = (Dice) otherObject;
    	if (this.faceUp > other.faceUp) return 1;
    	if (this.faceUp == other.faceUp) return 0;
    	else return -1;
    }
	
    /* 
    * This function uses a random number generator to determine which face is up
    * It simulates the roll of an unweighted Die
    * @parameters none
    * @returns nothing
    * @throw - no exceptions are thrown by this function
    */
	public void roll() {
		Random gen = new Random();
		faceUp = gen.nextInt(sides) + 1;
	}

    /* 
     * This function retrieves the value of the face up 
     * which represents the current roll
     * @parameters none
     * @returns int of the current roll (faceUp)
     * @throw - no exceptions are thrown by this function
     */
	public int getValue() {
		return faceUp;
	}
	
    /* 
     * This function retrieves the number of sides
     * @parameters none
     * @returns int representing the number of sides
     * @throw - no exceptions are thrown by this function
     */
	public int getSides() {
		return sides;
	}
}
