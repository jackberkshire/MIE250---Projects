package model;
import util.ElementSet;
import java.util.*;

/**
 *
 * @author Jack
 */
public class SCPModel {
    private TreeSet<ElementSet> _model;
    
    //construcor
    public SCPModel() {
        _model = new TreeSet<ElementSet>();
    }
    
    //copy constructor
    public SCPModel(SCPModel model) {
        _model = new TreeSet<ElementSet>();
        for (ElementSet s : model.getElements()) {
            addSetToCover(s.getID(), s.getCost(), s.getSet());
        }
    }
    
    //make a new element set and add this set to model
    public void addSetToCover(int ID, double cost, Collection set) { 
        ElementSet newSet = new ElementSet(ID, cost, set);
        _model.add(newSet);
    }
    
    
    //tostring method to match output to solution code output
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nWeighted SCP model:\n");
        sb.append("---------------------\n");
        sb.append("Number of elements (n): " + getNumElements() + "\n");
        sb.append("Number of sets (m): " + _model.size()+ "\n");
        
        sb.append("\nSet details:\n");
        sb.append("----------------------------------------------------------\n");
        double cost;
        for (ElementSet set : _model) {
            cost = set.getCost();
            sb.append("Set ID: " +String.format("%3d",set.getID()));
            sb.append("   Cost: " + String.format(" %5.2f   ", cost));
            sb.append("Element IDs: " + set.getSet() + "\n");
        }
        
        return sb.toString();
    }
    
    //gets # of elements in the element set
    public int getNumElements() {
        TreeSet<Integer> indivNums = new TreeSet<Integer>();
        for (ElementSet set : _model)
            indivNums.addAll(set.getSet());
            
        return indivNums.size();
    }
    
    //gets the elements in the model
    public TreeSet<ElementSet> getElements() {
        return _model;
    }
    
    
    //testing if copy constructer works and if model add set to cover works
    public static void main(String[] args) {
        SCPModel model = new SCPModel();
        model.addSetToCover(6, 2.0, Arrays.asList(new Integer[] {4,8}));
		model.addSetToCover(5, 2.0, Arrays.asList(new Integer[] {2,6,10}));
		model.addSetToCover(4, 5.0, Arrays.asList(new Integer[] {2,4,6,8,10}));
		model.addSetToCover(3, 2.0, Arrays.asList(new Integer[] {5,7,9}));
		model.addSetToCover(2, 2.0, Arrays.asList(new Integer[] {1,5,9}));
		model.addSetToCover(1, 3.0, Arrays.asList(new Integer[] {1,3,5,7,9}));
        System.out.println(model);
        
        SCPModel copy = new SCPModel(model);
        System.out.println(copy);
    }
}
