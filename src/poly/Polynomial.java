package poly;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashMap;

import util.Vector;

/** Implements a polynomial as a sum of terms.  If 5x^2 + 3xy is a polynomial,
 *  it has two Terms 5x^2 and 2xy, each stored in the member list _terms.
 * 
 * @author ssanner@mie.utoronto.ca
 *
 */
public class Polynomial {

	private ArrayList<Term> _terms; // The Polynomial is the sum of these Terms

	/** This constructor has been implemented for you.  It simply initializes an
	 *  empty term list.
	 * 
	 */
	public Polynomial() {
		_terms = new ArrayList<Term>();
	}
	
	/** This constructor has been implemented for you -- it parses a term 
	 *  representation from a String into the format required by this class.
	 * 
	 * @param s -- String to parse
	 * @throws PolyException if s is malformed
	 */
	public Polynomial(String s) throws PolyException {

		if (s == null || s.trim().equals(""))
			throw new PolyException("Empty Polynomial, cannot read");
		_terms = new ArrayList<Term>();
		String[] terms = s.split("\\+");
		for (String term : terms)
			_terms.add(new Term(term));
	}
	
	/** Produce a re-parseable representation of this Polynomial as a String.  This
	 *  has been done for you.
	 * 
	 */
	public String toString() {
		// Using "+" to append Strings involves a lot of String copies since Strings are 
		// immutable.  StringBuilder is much more efficient for append.
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (Term term : _terms) {
			sb.append((first ? "" : " + ") + term);
			first = false;
		}
		return sb.toString();
	}

	/** This method takes a file (e.g., new File("files/poly1.txt")) which on its
	 *  first line should contain a syntactically correct Polynomial as parsed by 
	 *  new Polynomial(String s) above and return that Polynomial.
	 *  
	 *  You need to implement this method... it requires file I/O!
	 * 
	 * @param file
	 * @return
	 * @throws PolyException if there were any errors reading or parsing the file
	 */
	public static Polynomial ReadPolynomial(File file) throws PolyException, FileNotFoundException, IOException {
		BufferedReader fin = new BufferedReader (new FileReader(file)); //make file read 
                Polynomial p = new Polynomial(fin.readLine()); //new polynomial with terms read from file
		return p; 
	}
	
	/** Returns all of the variables used in this Polynomial as a sorted set (TreeSet).
	 * 
	 * @return (TreeSet of Strings as defined above)
	 */
	public TreeSet<String> getAllVars() {
           TreeSet tree = new TreeSet<String>();        
            for (int i = 0; i < _terms.size(); i++) { //gets all vars from each term and adds them to the tree
                tree.addAll(_terms.get(i).getAllVars()); //adds all to treeset
            }
		return tree;
	}
	
	/** If Polynomial defines f(x,y) = 2xy^2 + xy and assignments is { x=2.0 y=3.0 } 
	 *  then this method returns 42.0, which is the evaluation of f(2.0,3.0).  
	 *  Incidentally, this is also the "Answer to the Ultimate Question of Life, the 
	 *  Universe, and Everything" in case you were wondering.
	 * 
	 * @param assignments
	 * @return
	 * @throws PolyException
	 */
	public double evaluate(Vector assignments) throws Exception {
                double sum = 0;
                
                //evaluate each term and sum them togeter
		for (int i = 0; i < _terms.size(); i++) {
                    sum = sum + _terms.get(i).evaluate(assignments);
                }
		return sum;
	}

	/** If Polynomial defines a function f(.) then this method returns the **symbolic**
	 *  partial derivative (which you can verify from calculus is still a Polynomial):
	 *  
	 *    partial f(1.0,2.0) / partial var.
	 * 
	 *  Specifically, if Polynomial defines f(x,y) = 2xy^2 + xy and var = "x"
	 *  then this method returns a **new** Polynomial 2y^2 + y and if var = "y" 
	 *  then it instead returns a **new** Polynomial 4xy + x.
	 * 
	 * @param var
	 * @return partial derivative of this w.r.t. var as a new Term
	 */
	public Polynomial differentiate(String var) {

		Polynomial pDif = new Polynomial();
                pDif._terms = new ArrayList<>(this._terms); //sets pdif contents equal to this contents
                
                //iterate over all terms of pdif and call differentiate on it
                for (int i = 0; i < pDif._terms.size(); i++) {
                    pDif._terms.set(i, pDif._terms.get(i).differentiate(var));    //sets pDif to the new differentiated term
                    if (pDif._terms.get(i) == null) {//if the term was = to 0 (we set it to null)
                        pDif._terms.remove(i); //and now we remove it 
                        i--; //must go back one now since we removed what we were already going on
                    }
                }
		return pDif;
	}
        /*
        * computes the gradient of a given polynomial
        * this is used only in minimizer
        */
        public HashMap<String, Polynomial> computeGradient() throws Exception {
            //takes partial derivative in term of each variable and stores it in 
            //hashmap
            HashMap<String, Polynomial> grad = new HashMap<String, Polynomial>();
            for (String var : getAllVars()) {
                grad.put(var, differentiate(var));
            }
            return grad;
        }
        
        /*
        * computes the norm of a gradient of a given polynomial
        */
	public static double computeGradientNorm(Vector gradEval) throws Exception {
            return gradEval.computeL2Norm(); //takes vector of evaluated gradient and computes norm of it
        }
        /*
        * evaluates the gradient of a polynomial at a certain point - returning in vector form
        */
        public static Vector computeGradientEval(HashMap<String, Polynomial> grad, Vector pt) throws Exception {
            Vector gradEval = new Vector(); //creates new vector (that we are going to store the evaluated partial deriv terms in
            
            for (String var : grad.keySet()) { //goes through each partial deriV and evaluates it
                gradEval.set(var, grad.get(var).evaluate(pt));
            }
            return gradEval;
        }

	/** Some examples testing the Polynomial and Term classes with expected output.
	 *  The functionality below will be tested standalone for grading.
	 *  
	 *  When initially developing the code, comment out lines below that you have
	 *  not implemented yet.  This will allow your code to compile for incremental
	 *  testing.
	 * 
	 * @param args
	 * @throws Exception if any errors occur (when implemented correctly, there should
	 *         be no Exceptions/errors below)
	 */
	public static void main(String[] args) throws Exception {
		Polynomial p  = new Polynomial("x^2 + y^2 + -4*x*y + 8");
		Polynomial p2 = new Polynomial(p.toString()); // See if we can reparse p.toString()
		Polynomial dp_dx = p.differentiate("x");
		Polynomial dp_dy = p.differentiate("y");

		// Build a point vector (HashMap) of numerical assignments for variables
		Vector x0 = new Vector();
		x0.set("x", 1.0);
		x0.set("y", 2.0);
		
		System.out.println("Polynomial: " + p);     // Should print "1.000*x^2 + 1.000*y^2 + -4.000*x*y + 8.000"
		System.out.println("Re-parsed:  " + p2);    // Should print "1.000*x^2 + 1.000*y^2 + -4.000*x*y + 8.000"
		System.out.println("dp/dx:      " + dp_dx); // Should print "2.000*x + -4.000*y"
		System.out.println("dp/dy:      " + dp_dy); // Should print "2.000*y + -4.000*x"
		System.out.println("Free vars:  " + p.getAllVars()); // Should print "[x, y]"
		System.out.println("p(x0)     = " + p.evaluate(x0));     // Should print "5.0"
		System.out.println("dp/dx(x0) = " + dp_dx.evaluate(x0)); // Should print "-6.0"
		System.out.println("dp/dy(x0) = " + dp_dy.evaluate(x0)); // Should print "0.0"
	}
}
