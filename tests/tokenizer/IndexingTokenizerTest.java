/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenizer;

import java.util.ArrayList;
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
public class IndexingTokenizerTest {
    
    public IndexingTokenizerTest() {
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
     * Test of tokenize method, of class IndexingTokenizer.
     * tested to see if the tokenizer tokenized the string correctly
     */
    @Test
    public void testTokenize() {
        System.out.println("tokenize");
        String s = "hi my name jack-thomas-berkshire";
        IndexingTokenizer instance = new IndexingTokenizer();
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("hi");
        expResult.add("my");
        expResult.add("name");
        expResult.add("jack-thomas-berkshire");
        ArrayList<String> result = instance.tokenize(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class IndexingTokenizer.
     
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        IndexingTokenizer.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
       
    }
   */
    
}
