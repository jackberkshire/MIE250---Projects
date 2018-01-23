package solver;
import util.ElementSet;
/**
 *
 * @author Jack
 */
public class ChvatalSolver extends GreedySolver {
    
    public ChvatalSolver() {
        _name = "Chvatal";
    }
    
    //nextbest is the set that has the highest cost/elementscovered(not already coverd raio)
    @Override
    public ElementSet nextBestSet() {
        
        ElementSet bestSet = null;         //initialze variables
        int countBest = 0;
        double bestScore = Double.MAX_VALUE; //arbitrarly large number CHECK ME
        int count = 0;
        double score = 0;
        
        for (ElementSet s : _copyModel.getElements()) { //go through all sets in the model
            count = 0; //reset count and score for each set
            score = 0;
            for (Integer i : s.getSet()) {
                if(_uncoveredElements.contains(i)) { //get number of covered elements
                    count++;
                } 
            }
            if (count != 0) { //if it contains an element
                score  = s.getCost() / (double)count; //compute the ratio of cost per element covered
                if (score < bestScore) { //if score is better than best score 
                    countBest = count;
                    bestScore = score;
                    bestSet = s;
                }
            }
        }
        
        if (bestSet == null) //if no set was chosen return null
            return null;
        
        _copyModel.getElements().remove(bestSet); //remcoe the chosen set from the model
         GreedySolver.printChosenSet(bestSet); //print the chosen set
        
        return bestSet;
    }
    
}
