/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack
 */
public class SortedDocScoreTest {
    
    public SortedDocScoreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compareTo method, of class SortedDocScore.
     * tested to see if would order scores correctly
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object o = new SortedDocScore(2.0, 1, "jack");
        SortedDocScore instance = new SortedDocScore(6.5, 4,"hi");
        int expResult = -1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
     /**
     * Test of compareTo method, of class SortedDocScore.
     * tested to see if would order alphabetically
     */
    @Test
    public void testCompareToAgain() {
        System.out.println("compareTo");
        Object o = new SortedDocScore(6.5, 50, "abba");
        SortedDocScore instance = new SortedDocScore(6.5, 4,"abbaa");
        int expResult = 1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }
}

    /**
     * Test of main method, of class SortedDocScore.
     
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        SortedDocScore.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        
    }
    * */
  
