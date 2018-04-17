import java.io.*;
import java.util.*;

/**
 *This is the testing class for the program. The yahtzee program has a player
 * role 5 dice and see what the numbers are. The goal is to try and get 
 * hands that match up as to get the highest score possible. This can be done
 * by being able to role a maximum of three times and picking which dice to 
 * re-roll to get the best score. After three roles or if the user is satisfied,
 * the program then prints the results and asks if they want to play again.
 * 
 * 
 * CPSC 224-01, Spring 2018
 * Programming Assignment #6
 * 
 * @author Gage Gutmann
 * @version v2.0 3/23/18
 */
public class YahtzeeTester {

    public static int DICE_IN_PLAY;
    public static int SIDES;

    /**The main class calls upon other methods from the different classes, 
     * creating an interface for the user to be able to play the game and 
     * reporting back the scores
     * @param args the command line arguments
     * @throws FileNotFoundException if file not found
     */
    public static void main(String[] args) throws FileNotFoundException{ 
        
       char playAgain = 'y';
        
       while(playAgain == 'y'){
           
        Scanner fileIn = new Scanner(new File("yahtzeeConfig.txt"));
        int[] fileInArray = new int[3];
        int count = 0; //counts the number of items in file
        while(fileIn.hasNextInt()){ //read the text file
            fileInArray[count] = fileIn.nextInt();
            count++;
        }
        fileIn.close();//Closes the text file
           
        System.out.println("You are playing with " + fileInArray[1] + " " + 
                           fileInArray[0]+ "-sided dice");
        System.out.println("You get " + fileInArray[2] + " rolls per hand");
        System.out.println();
        
        DICE_IN_PLAY = fileInArray[1]; //number of dice
        SIDES = fileInArray[0];//number of sides
        int totalTurns = fileInArray[2];
        Scanner input = new Scanner(System.in);
        System.out.print("Enter 'y' if you would like to change the configuration ");
        
        if(input.next().equals("y")){ //changes values and 
            PrintStream toFile = new PrintStream(new File("yahtzeeConfig.txt"));
            System.out.print("Enter the number of sides of each die ");
            SIDES = input.nextInt();
            toFile.println(SIDES);
            System.out.print("Enter the number of dice in play ");
            DICE_IN_PLAY = input.nextInt();
            toFile.println(DICE_IN_PLAY);
            System.out.print("Enter the number of rolls per hand ");
            totalTurns = input.nextInt();
            toFile.println(totalTurns);
            toFile.close();
        }
        
        int [] scores = new int[DICE_IN_PLAY];
        
        
        
        StringBuilder build1 = new StringBuilder();
        for(int i = 0; i < DICE_IN_PLAY; i++){
            build1.append("n");
        }
        
        StringBuilder build2 = new StringBuilder();
        for(int i = 0; i < DICE_IN_PLAY; i++){
            build2.append("y");
        }
        
           
        String keep = build1.toString();
        String keep2 = build2.toString();
        int turn = 1;
        while(turn < (totalTurns + 1) && !(keep.equals(keep2))){
            //roll dice not kept
            for (int dieNumber = 0; dieNumber < DICE_IN_PLAY; dieNumber++){
                if (keep.substring(dieNumber, dieNumber + 1).equals("n")){
                    scores[dieNumber] = TrackPlay.rollDie();
                }
            }
            //output roll
            System.out.print("Your roll was: ");
            for (int dieNumber = 0; dieNumber < DICE_IN_PLAY; dieNumber++){
                System.out.print(scores[dieNumber] + " ");
            }
            System.out.println();
            //if not the last roll of the hand prompt the user for dice to keep
            if (turn < totalTurns){
                System.out.print("enter dice to keep (y or n) ");
                keep = input.next();
            }
            turn++;
        }
           
        //start scoring
        //hand need to be sorted to check for straights
        TrackPlay.sortArray(scores, DICE_IN_PLAY);
        System.out.print("Here is your sorted hand: ");
        for (int dieNumber = 0; dieNumber < DICE_IN_PLAY; dieNumber++){
            System.out.print(scores[dieNumber] + " ");
        }
        System.out.println();
           
        ScoreCard.upperCard(scores);
        ScoreCard.lowerCard(scores);
           
        System.out.print("\nEnter 'y' to play again ");
        playAgain = input.next().charAt(0);
       }
        
    }
    
    
    
}
