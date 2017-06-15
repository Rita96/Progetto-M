/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Giulia
 */
public class ArbitroTest {
    
    private Arbitro test;
    
    public ArbitroTest() {
        test = new Arbitro("A", "A", "A", "A", false);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of logIn method, of class Arbitro.
     */
    @Test
    public void testLogIn() {
        System.out.println("ArbitroTest - logIn");
        String expResult = "CREDENZIALI CORRETTE";
        String result = test.logIn("A", "A");
        assertEquals(expResult, result);
    }
    /**
     * Test of logOut method, of class Arbitro.
     */
    @Test
    public void testLogOut() {
        System.out.println("ArbitroTest - logOut");
        String expResult = "Log out effettuato";
        String logIn = test.logIn("A", "A");
        String result = test.logOut("A", "A");
        assertEquals(expResult, result);
    }
}
