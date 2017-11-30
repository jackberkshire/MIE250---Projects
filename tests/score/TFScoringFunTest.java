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
public class TFScoringFunTest {
    
    public TFScoringFunTest() {
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
     * Test of init method, of class TFScoringFun.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        Index s = null;
        TFScoringFun instance = new TFScoringFun();
        instance.init(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scoreToken method, of class TFScoringFun.
     */
    @Test
    public void testScoreToken() {
        System.out.println("scoreToken");
        String term = "";
        int freq = 0;
        TFScoringFun instance = new TFScoringFun();
        double expResult = 0.0;
        double result = instance.scoreToken(term, freq);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
