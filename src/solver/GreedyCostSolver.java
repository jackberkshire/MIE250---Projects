package solver;
import util.ElementSet;
/**
 *
 * @author Jack
 */
public class GreedyCostSolver extends GreedySolver{
    
    public GreedyCostSolver() {
        _name = "Cost";
    }
    
    //nextbest is the one that has the least cost and covers at least one uncovered element
    @Override
    public ElementSet nextBestSet() {
        
         ElementSet bestSet = null; //initialize variables
         double cost = 0;
         double bestCost = Double.MAX_VALUE; //arbitrarily large number
         boolean found;
         
         for (ElementSet s : _copyModel.getElements()) {
             found = false;                         //reset every new set
             for (Integer i : s.getSet()) {
                 if (_uncoveredElements.contains(i)) { //if contains at least one element get cost
                     found = true;
                     cost = s.getCost();
                     break;
                 }
             }
             if (found == true && cost < bestCost) { //if contains element and has lower cost than bestcost is the next best
                 bestCost = cost;
                 bestSet = s;
             }     
         }
         
         if (bestSet == null) //if we dont pick a set return null
             return null;
         
         _copyModel.getElements().remove(bestSet); //remove chosen set from set of sets
         GreedySolver.printChosenSet(bestSet); //print out chosen set
        return bestSet;
    }
}
