/* 
 * The class Yahtzee handles the configuration for a game of 1-player Yahtzee
 * It stores the number of sides, number of dice, and number of turns in
 * local variables and to a config file so that a user can have the same
 * configuration from game to game. It allows the user to opt to change the
 * configuration and, if they do so, stores the new configuration to the file. 
 * Assignment 6
 * @author Alexa Andrews
 * Date Created: March 7 2018
 * Last Modified: March 22 2018
*/

import java.io.*;
import java.util.*;

public class Yahtzee {
	private int numSides = 6;
	private int numDice = 5;
	private int numTurns = 3;
	
	
	/*
    * This method sets the fields to those stored in file yahtzeeConfig.txt
    * It then allows the user to opt to change these fields. If they choose
    * to do so, their inputs will override those in the yahtzeeConfig.txt file
    * @parameters Scanner because this method takes user input and having
    * more than one Scanner object results in errors. 
 	* @returns nothing 
 	* @throw - throws FileNotFoundException if yahtzeeConfig.txt does not exist
 	*/
	public void determineConfig (Scanner in) 
								throws FileNotFoundException {
		Scanner input = new Scanner(new File("yahtzeeConfig.txt"));
        numSides = input.nextInt();
        numDice = input.nextInt();
        numTurns = input.nextInt();
        
        System.out.println("You are playing with " + numDice + " " + numSides + "-sided dice.");
        System.out.println("You get " + numTurns + " rolls per hand.\n");
        System.out.print("Enter \"y\" if you would like to change the configuration: ");
        String change = in.nextLine();
        if(change.equals("y")) {
        	System.out.print("\nEnter the number of sides on each die: ");
        	numSides = in.nextInt();
        	System.out.print("\nEnter the number of dice in play: ");
        	numDice = in.nextInt();
        	System.out.print("\nEnter the number of rolls per hand: ");
        	numTurns = in.nextInt();
        	PrintStream output = new PrintStream(new File("yahtzeeConfig.txt"));
        	output.println(numSides);
        	output.println(numDice);
        	output.println(numTurns); 
            System.out.println("\nYou are playing with " + numDice + " " + numSides + "-sided dice.");
            System.out.println("You get " + numTurns + " rolls per hand.\n");
        }
        input.close();
	}
	
	/*
    * This function allows a user to get the number of sides
    * @parameters this function requires no parameters
 	* @returns int representing the number of sides
 	* @throw - no exceptions are thrown by this function 
 	*/
	public int getSides() {
		return numSides;
	}
	
	/*
    * This function allows a user to get the number of dice
    * @parameters this function requires no parameters
 	* @returns int representing the number of dice
 	* @throw - no exceptions are thrown by this function 
 	*/
	public int getDice() {
		return numDice;
	}
	
	/*
    * This function allows a user to get the number of turns
    * @parameters this function requires no parameters
 	* @returns int representing the number of turns
 	* @throw - no exceptions are thrown by this function 
 	*/
	public int getTurns() {
		return numTurns;
	}
}
