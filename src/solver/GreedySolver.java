package solver;
import java.util.SortedSet;
import java.util.*;
import model.SCPModel;
import util.ElementSet;
import java.lang.Math;

/** This is the main method that all solvers inherit from.  It is important
 *  to note how this solver calls nextBestSet() polymorphically!  Subclasses
 *  should *not* override solver(), they need only override nextBestSet().
 * 
 *  We'll assume models are well-defined here and do not specify Exceptions
 *  to throw.
 * 
 * @author ssanner@mie.utoronto.ca
 *
 */
public abstract class GreedySolver {
	
	protected String _name;           // name of algorithm type
	protected double _alpha;          // minimum required coverage level in range [0,1]
	protected SCPModel _model;        // the SCP model we're currently operating on
	protected double _objFn;          // objective function value (*total cost sum* of all sets used)
	protected double _coverage;       // actual coverage fraction achieved
	protected long _compTime;         // computation time (ms)
        protected TreeSet<ElementSet> _solnSets = new TreeSet<ElementSet>(); //the selected sets used by the algorithm
        protected TreeSet<Integer> _uncoveredElements = new TreeSet<Integer>(); //treeset with all the covered elements
        protected SCPModel _copyModel; //copy of the model
	
	// Basic setter (only one needed)
	public void setMinCoverage(double alpha) { _alpha = alpha; }
	public void setModel(SCPModel model) { _model = model; }
	
	// Basic getters
	public double getMinCoverage() { return _alpha; }
	public double getObjFn() { return _objFn; }
	public double getCoverage() { return _coverage; }
	public long getCompTime() { return _compTime; }
	public String getName() { return _name; }
        
        //prints out the chosen best set
        public static void printChosenSet(ElementSet chosenOne) {
            System.out.println("- Selected: Set ID: " + String.format("%3d", chosenOne.getID()) + "   Cost: " 
                    + String.format(" %5.2f   ", chosenOne.getCost()) + "Element IDs: " + chosenOne.getSet());
        }
        
        //check if there is an element in uncovered elements that is also in a set in the sets availible to be chosen
        public boolean canIncreaseCov() {
           for (ElementSet s : _copyModel.getElements()) { //goes through all sets and all integers in those sets
               for (Integer i : s.getSet()) {
                   if (_uncoveredElements.contains(i)) //if true coverage can increase
                       return true;
               }
           }
           return false;
        }
        
	
	/** Run the simple greedy heuristic -- add the next best set until either
	 *  (1) The coverage level is reached, or 
	 *  (2) There is no set that can increase the coverage.
	 */
	public void solve() {
		
		//Reset the solver
		reset();

                for(ElementSet s : _model.getElements()) //_uncoveredelements is the whole universe initially
                    _uncoveredElements.addAll(s.getSet());
                
                
		// Begin the greedy selection loop
		long start = System.currentTimeMillis();
		System.out.println("Running '" + getName() + "'...");
                
                //max uncovered elements is numelements - min amount of elements uncovered rounded up
                int maxUncovered = _model.getNumElements() - (int)Math.ceil(_alpha*_model.getNumElements());
                
                //while the # of uncovered elemenmts is greater than the max # of uncovered elements & we can increase the coverage
                while(_uncoveredElements.size() > maxUncovered && canIncreaseCov()) {
                    ElementSet bestSet = nextBestSet();
                    
                    if (bestSet == null)                     //if best set returns null break the loop
                        break;
                    
                    else {
                        _solnSets.add(bestSet); //if not null add it to the set of solutios sets
                    
                        for(ElementSet s : _solnSets) //take out all elements that the next best set cover
                            _uncoveredElements.removeAll(s.getSet());
                    }  
                }
		
		// Record final set coverage, compTime and print warning if applicable
		_coverage = (double)((_model.getNumElements() - _uncoveredElements.size()) / (double)_model.getNumElements()); //update coverage
                
                //reset the objevtive function
                _objFn = 0;
                
                //
                for (ElementSet s : _solnSets) { //objective function is the sum of all the costs in the set
                    _objFn = _objFn + s.getCost();
                }
                
		_compTime = System.currentTimeMillis() - start;
		if (_coverage < _alpha)
			System.out.format("\nWARNING: Impossible to reach %.2f%% coverage level.\n", 100*_alpha);
		System.out.println("Done.");
        }
        
        public void reset() {
            _solnSets.clear(); //empty the soltion set and uncovered elements (will be initialized to universe again)
            _uncoveredElements.clear();
            _copyModel = new SCPModel(_model); //copy the model, so that we can remove sets from it 
            _coverage = 0.0; //reset the coverage
        }
        
        
	
	/** Returns the next best set to add to the solution according to the heuristic being used.
	 * 
	 *  NOTE 1: This is the **only** method to be implemented in child classes.
	 *  
	 *  NOTE 2: If no set can improve the solution, returns null to allow the greedy algorithm to terminate.
	 *  
	 *  NOTE 3: This references an ElementSet class which is a tuple of (Set ID, Cost, Integer elements to cover)
	 *          which you must define.
	 * 
	 * @return
	 */
	public abstract ElementSet nextBestSet(); // Abstract b/c it must be implemented by subclasses
	
	/** Print the solution
	 * 
	 */
	public void print() {
		System.out.println("\n'" + getName() + "' results:");
		System.out.format("'" + getName() + "'   Time to solve: %dms\n", _compTime);
		System.out.format("'" + getName() + "'   Objective function value: %.2f\n", _objFn);
		System.out.format("'" + getName() + "'   Coverage level: %.2f%% (%.2f%% minimum)\n", 100*_coverage, 100*_alpha);
		System.out.format("'" + getName() + "'   Number of sets selected: %d\n", _solnSets.size());
		System.out.format("'" + getName() + "'   Sets selected: ");
		for (ElementSet s : _solnSets) {
			System.out.print(s.getID() + " ");
		}
		System.out.println("\n");
	}
	
	/** Print the solution performance metrics as a row
	 * 
	 */
	public void printRowMetrics() {
		System.out.format("%-25s%12d%15.4f%17.2f\n", getName(), _compTime, _objFn, 100*_coverage);
	}
}
