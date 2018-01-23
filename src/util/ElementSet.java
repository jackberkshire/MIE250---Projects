package util;
import java.util.*;
/**
 *
 * @author Jack
 */
public class ElementSet implements Comparable {
        private int _setID;
        private double _setCost;
        private TreeSet<Integer> _set;
                
      //constructor  
       public ElementSet(int ID, double cost, Collection set) {
           _setID = ID;
           _setCost = cost;
           _set = new TreeSet<Integer>(set);  
       }
       
       /*
         returns 1 if should come after o
         return -1 if should come before o
         return o if equals() says they are equal
       */
       @Override
       public int compareTo(Object o) {
           if (!(o instanceof ElementSet)) //if not of the same instance
                return -1;
           
           ElementSet e = (ElementSet)o; //casting
           
           if (this._setID != e._setID) //compare ID if not equal
               return (this._setID - e._setID);
        
           return 0;
       }
       
       //getters
       public int getID() {
           return _setID;
       }
       
       public double getCost() {
           return _setCost;
       }
       
       public TreeSet<Integer> getSet() {
           return _set;
       }
}
