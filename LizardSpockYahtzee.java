/* 
 * The class LizardSpockYahtzee uses the Player class to
 * allow the user to play a single hand of Yahtzee. The user 
 * can designate the number of dice, turns, and sides of dice 
 * assisted by the Yahtzee class. 
 * Assignment 6
 * @author Alexa Andrews
 * Date Created: March 7 2018
 * Last Modified: March 22 2018
*/

import java.io.*;
import java.util.*;

public class LizardSpockYahtzee {

	public static void main(String[] args) {
		Scanner in_main = new Scanner(System.in);
		Yahtzee game = new Yahtzee(); 
		try {
			game.determineConfig(in_main);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} finally { // if file is found plays with user defined config, otherwise uses default yahtzee config
			Player justLittleMe = new Player(game.getDice(), game.getSides(), game.getTurns());
			int rounds = 7 + game.getSides();
			while (rounds > 0) {
				justLittleMe.takeTurn(in_main);
				rounds--;
			}
			justLittleMe.displayFinalScore();
			in_main.close();
		}
	}
}
