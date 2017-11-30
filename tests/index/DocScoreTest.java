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
public class DocScoreTest {
    
    public DocScoreTest() {
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
     * Test of getScore method, of class DocScore.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        DocScore instance = new SortedDocScore(9.0, 2, "hi");
        double expResult = 9.0;
        double result = instance.getScore();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDocID method, of class DocScore.
     */
    @Test
    public void testGetDocID() {
        System.out.println("getDocID");
        DocScore instance = new SortedDocScore(7.0, 5, "computer");
        int expResult = 5;
        int result = instance.getDocID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getContent method, of class DocScore.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        DocScore instance = new SortedDocScore(5.0, 5, "hi my name is jack");
        String expResult = "hi my name is jack";
        String result = instance.getContent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    public class DocScoreImpl extends DocScore {

        public DocScoreImpl() {
            super(null);
        }
    }
    
}
