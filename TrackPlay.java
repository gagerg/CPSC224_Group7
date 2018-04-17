import java.util.*;


/**
 * This class of the program takes care of tracking the dice, including randomly
 * rolling the die, keeping track of the total score, and sorting 
 * the hand.
 * 
 * CPSC 224-01, Spring 2018
 * Programming Assignment #6
 * 
 * @author Gage Gutmann
 * @version v2.0 3/23/18
 */
public class TrackPlay {
    
   /**
      * Simulates the rolling of a single die
      * @return the die value
     */
    public static int rollDie(){
        Random randNum = new Random(); 
        int dice = randNum.nextInt(YahtzeeTester.SIDES)+ 1;//Accounts for sides
        return dice;
    }
    
    /**+
      * Calculates the sum of all the die
      * @param scores the passed in array of die values
      * @return the sum of the hand
     */
    public static int totalAllDice(int scores[]){
        int total = 0;
        for (int diePosition = 0; diePosition < scores.length; diePosition++){
            total += scores[diePosition];
        }
        return total;
    }
    
    /**
      * Sorts the array of die in ascending order
      * @param scores the passed in array of die values
      * @param size, the size of the array to be sorted
     */
    public static void sortArray(int[] scores, int size){
        boolean swap;
        int temp;

        do
        {
            swap = false;
            for (int count = 0; count < (size - 1); count++) {
                if (scores[count] > scores[count + 1]){
                    temp = scores[count];
                    scores[count] = scores[count + 1];
                    scores[count + 1] = temp;
                    swap = true;
                }
            }
        } while (swap);
    }
}
