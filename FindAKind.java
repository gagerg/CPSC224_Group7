/**
 * This class takes the final scores of the hand rolled and finds sets that are
 * worth extra on the lower score card, including multiple of a kind, a 
 * straight, and a full house.
 * CPSC 224-01, Spring 2018
 * Programming Assignment #6
 * 
 * @author Gage Gutmann
 * @version v2.0 3/23/18
 */
public class FindAKind {
    
     /**
      * Finds the maximum number of die of a specific value in the hand.
      * @param scores the passed in array of die
      * @return the max count of kind
     */
    public static int maxOfAKindFound(int[] scores){
        int maxCount = 0;
        int currentCount ;
        for (int dieValue = 1; dieValue <=6; dieValue++){
            currentCount = 0;
            for (int diePosition = 0; diePosition < 5; diePosition++){
                if (scores[diePosition] == dieValue){
                currentCount++;
                }
            }
            if (currentCount > maxCount){
                maxCount = currentCount;
            }
        }
        return maxCount;
    }
    
     /**
      * Finds the length of the longest straight
      * @param scores the passed in array of die
      * @return the length value of the longest straight
     */
    public static int maxStraightFound(int[] scores){
        int maxLength = 1;
        int curLength = 1;
        for(int counter = 0; counter < 4; counter++){
            if (scores[counter] + 1 == scores[counter + 1]){ //jump of 1
                curLength++;
            }else if (scores[counter] + 1 < scores[counter + 1]){ //jump of >= 2
                curLength = 1;
            }
            if (curLength > maxLength){
                maxLength = curLength;
            }
        }
        return maxLength;
    }
    
     /**
      * Checks to see if the hand contains a full house
      * @param scores the passed in array of die
      * @return true if a full house is found
     */
    public static boolean fullHouseFound(int[] scores){
        boolean foundFH = false;
        boolean found3K = false;
        boolean found2K = false;
        boolean found5K = false;
        boolean foundOneKind = false; //To make sure that the full house 
        boolean foundAnotherKind = false;//Accounts to more than just 5 die
        int currentCount;
        for (int dieValue = 1; dieValue <= YahtzeeTester.SIDES; dieValue++){
            currentCount = 0;
            for (int diePosition = 0; diePosition < scores.length; diePosition++){
                if (scores[diePosition] == dieValue){
                    currentCount++;
                }
            }
            if (currentCount == 2){
                found2K = true;
            }
            if (currentCount == 3){
                found3K = true;
            }
            
            if (currentCount == 5){
                found5K = true;
            }
            
            if((scores.length % 2 == 0)){
                if(currentCount == ((scores.length/2) - 1)){
                    foundOneKind = true;
                }
                
                if(currentCount == ((scores.length/2) + 1)){
                    foundAnotherKind = true;
                }
            }else{
                if(currentCount == (scores.length/2)){
                    foundOneKind = true;
                }
                
                if(currentCount == ((scores.length/2) + 1)){
                    foundAnotherKind = true;
                }
            }
        }
        if (found2K && found3K){
            foundFH = true;
        }
        
        if(found5K){ 
            foundFH = true;
        }
        
        if(foundOneKind && foundAnotherKind){
            foundFH = true;
        }
        
        return foundFH;
    }
    
    
    
    
    
}
