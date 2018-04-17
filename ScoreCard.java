/**
 * This class of the program takes the scores and the sets of the final hand
 * and prints out the upper and lower portions of the yahtzee score card.
 * CPSC 224-01, Spring 2018
 * Programming Assignment #6
 * 
 * @author Gage Gutmann
 * @version v2.0 3/23/18
 */
public class ScoreCard {
    
    
    /**
      * Prints the upper portion of the score card
      * @param scores the passed in array of die
     */
    public static void upperCard(int[] scores){
        for (int dieValue = 1; dieValue <= YahtzeeTester.SIDES; dieValue++){
            int currentCount = 0;
            for (int diePosition = 0; diePosition < scores.length; diePosition++){
                if (scores[diePosition] == dieValue){
                    currentCount++;
                }
            }
        System.out.print( "Score " + (dieValue * currentCount) + " on the ");
        System.out.println(dieValue + " line");
        }
    }
    
    
    /**
      * Prints the lower portion of the score card
      * @param scores the passed in array of die
     */
    public static void lowerCard(int[] scores){
        /*if(FindAKind.maxOfAKindFound(scores) >= 3){
            System.out.print("Score " + TrackPlay.totalAllDice(scores) + " on the ");
            System.out.println("3 of a Kind line");
        }else{
            System.out.println("Score 0 on the 3 of a Kind line");
        }

        if(FindAKind.maxOfAKindFound(scores) >= 4){
            System.out.print("Score " + TrackPlay.totalAllDice(scores) + " on the ");
            System.out.println("4 of a Kind line");
        }else{
            System.out.println("Score 0 on the 4 of a Kind line");
        }*/
        
        if(scores.length >= 3){ //Tailors to the number of sides on the die
            for(int i = 3; i <= scores.length - 1; i++){
                if(FindAKind.maxOfAKindFound(scores) >= i){
                    System.out.print("Score " + TrackPlay.totalAllDice(scores) + " on the ");
                    System.out.println( i +" of a Kind line");
                }else{
                    System.out.println("Score 0 on the " + i + " of a Kind line");
                }
            }
        }
        
       

        if(FindAKind.fullHouseFound(scores)){
            System.out.println("Score 25 on the Full House line");
        }else{
            System.out.println("Score 0 on the Full House line");
        }

        if(FindAKind.maxStraightFound(scores) >= (scores.length - 1)){
            System.out.println("Score 30 on the Small Straight line");
        }else{
            System.out.println("Score 0 on the Small Straight line");
        }

        if(FindAKind.maxStraightFound(scores) >= scores.length){
            System.out.println("Score 40 on the Large Straight line");
        }else{
            System.out.println("Score 0 on the Large Straight line");
        }

        if(FindAKind.maxOfAKindFound(scores) >= scores.length){
            System.out.println("Score 50 on the Yahtzee line");
        }else{
            System.out.println("Score 0 on the Yahtzee line");
        }

        System.out.print("Score " + TrackPlay.totalAllDice(scores) + " on the ");
        System.out.println("Chance line");

    }
    
}
