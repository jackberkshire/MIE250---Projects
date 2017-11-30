/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score;

import index.Index;
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
public class TermScoringFunTest {
    
    public TermScoringFunTest() {
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
     * Test of init method, of class TermScoringFun.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        Index s = null;
        TermScoringFun instance = new TermScoringFunImpl();
        instance.init(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scoreToken method, of class TermScoringFun.
     */
    @Test
    public void testScoreToken() {
        System.out.println("scoreToken");
        String term = "";
        int freq = 0;
        TermScoringFun instance = new TermScoringFunImpl();
        double expResult = 0.0;
        double result = instance.scoreToken(term, freq);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TermScoringFunImpl implements TermScoringFun {

        public void init(Index s) {
        }

        public double scoreToken(String term, int freq) {
            return 0.0;
        }
    }
    
}
