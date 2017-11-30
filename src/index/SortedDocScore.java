package index;

import java.util.TreeSet;


public class SortedDocScore extends DocScore implements Comparable {

    public SortedDocScore(Double score, int id, String content) {
        super(score, id, content);
    }
    
    public SortedDocScore(DocScore ds) {
        super(ds);
    }

    @Override
    public int compareTo(Object o) {
        
        if (!(o instanceof DocScore)) //comes before o
            return -1;
        
        DocScore ds = (DocScore)o;
        
        if (this._score > ds._score) //comes before o
            return -1;
        
        else if (this._score < ds._score) //comes after o
            return 1;
        
        else if (this._content.compareTo(ds._content) != 0) { //alphbeticaly first goes first
                return this._content.compareTo(ds._content);
        }
        
        return 0; //are identical
    }
    
    public static void main(String args[]) {
        
        String oi = "a";
        String oi2= "b";
        String oi3 = "c";
        
        SortedDocScore ds1 = new SortedDocScore(1.2, 1, oi);
        SortedDocScore ds2 = new SortedDocScore(1.3, 2, oi);
        SortedDocScore ds3 = new SortedDocScore(1.2, 3, oi2);
        SortedDocScore ds4 = new SortedDocScore(1.5, 4, oi3);
        
        int i = ds1.compareTo(ds2); //1 should be after 2 (1)
        int j = ds2.compareTo(ds1);//2 should be before 1 (-1)
        System.out.println(i);
        System.out.println(j);
        int k = ds3.compareTo(ds1);//3 should be after 1 (1)
        System.out.println(k);
        
        TreeSet<DocScore> tree = new TreeSet<DocScore>();
        tree.add(ds1);
        tree.add(ds2);
        tree.add(ds3);
        tree.add(ds4);
        
        System.out.println(tree);
        
    }
}
