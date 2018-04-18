/*
 * This program simulates one game of yahtzee for the
 * and displays all possible scores for the turn based on the final roll
 * CPSC 224-01 Spring 2018
 * @author Andrew Brodhead
 * Version v2.1 3/22/2018
 * 
 */
import java.util.*;
import java.io.*;


public class Yahtzee {
	/**
	 * main function that loops while user wants to continue playing turns
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException{
		
		//turn loop
		Scanner input = new Scanner(System.in);
		String playAgain = "y";
		while(playAgain.equals("y")) {
			Scanner configs = new Scanner(new File("yahtzeeConfig.txt"));
			int numSides = configs.nextInt();
			int numDice = configs.nextInt();
			int numRolls = configs.nextInt();
			System.out.println("you are playing with " + numDice + " " + numSides + "-sided dice");
			System.out.println("you get " + numRolls + " rolls per hand");
			System.out.print("\nenter 'y' if you would like to change the config ");
			if(input.nextLine().equals("y")){
				System.out.print("enter the number of sides on each die ");
				numSides = input.nextInt();
				System.out.print("enter the number of dice in play ");
				numDice = input.nextInt();
				System.out.print("enter the number of rolls per hand ");
				numRolls = input.nextInt();
				System.out.println();
				input.nextLine();
				PrintStream out = new PrintStream(new File("yahtzeeConfig.txt"));
				out.println(numSides);
				out.println(numDice);
				out.println(numRolls);
			} else {
				int scoresLeft = 7+numSides;
				boolean[] linesUsed = new boolean[scoresLeft];
				Scorecard sc = new Scorecard(numSides,linesUsed);
				while(scoresLeft > 0) {
					//play one turn
					Turn t = new Turn(numRolls,numDice,numSides);
					int[] roll = t.play();
					sc.calculate(roll);
					sc.display();
					System.out.print("enter line number to score this turn ");
					sc.select(input.nextInt());
					scoresLeft--;
				}
				//check if user wants to play another game
				System.out.println("\nFinal Score");
				sc.displayFinal();
				input.nextLine();
				System.out.print("\nEnter 'y' to play again ");
				playAgain = input.nextLine();
			}
		}
	}
	
}