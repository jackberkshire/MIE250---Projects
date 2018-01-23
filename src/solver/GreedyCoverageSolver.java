package solver;
import java.util.Arrays;
import util.ElementSet;
/**
 *
 * @author Jack
 */
public class GreedyCoverageSolver extends GreedySolver {
    
    public GreedyCoverageSolver() {
        _name = "Coverage";
    }
    
    //add set that will cover the most uncovered elements
    @Override
    public ElementSet nextBestSet() {
        
        ElementSet bestSet = null; //initiaze variables to 0 and null
        int countBest = 0;
        int count = 0;
        
        for (ElementSet s : _copyModel.getElements()) { //goes through all sets, sets nextbest to the one that covers the the most uncovered elements
            count = 0;                              //reset count for every new set                          
            for (Integer i : s.getSet()) {
                if (_uncoveredElements.contains(i)) //if the set contains an uncovered element increment count
                    count++;
            }
            if (count > countBest) { //if this count is better than bestCount, set bestset to this set, and best count to this count
                countBest = count;          
                bestSet = s;
            }
        }
        if (bestSet == null) //if we didnt pick a set return null
            return null;
   
        _copyModel.getElements().remove(bestSet); //take chosen set out of possible sets available
        GreedySolver.printChosenSet(bestSet); //print stuff about set
        return bestSet;
    }
    
    
}


