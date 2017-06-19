/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Giulia
 */
public class GeneratoreTorneoTest {
    
    public GeneratoreTorneoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getAutenticazione method, of class GeneratoreTorneo.
     */
    @Test
    public void testGetAutenticazione() {
        System.out.println("getAutenticazione");
        GeneratoreTorneo instance = null;
        String expResult = "";
        String result = instance.getAutenticazione();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTorneiCreati method, of class GeneratoreTorneo.
     */
    @Test
    public void testGetTorneiCreati() {
        System.out.println("getTorneiCreati");
        GeneratoreTorneo instance = null;
        List<Torneo> expResult = null;
        List<Torneo> result = instance.getTorneiCreati();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCf method, of class GeneratoreTorneo.
     */
    @Test
    public void testGetCf() {
        System.out.println("getCf");
        GeneratoreTorneo instance = null;
        String expResult = "";
        String result = instance.getCf();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class GeneratoreTorneo.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        GeneratoreTorneo instance = null;
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logIn method, of class GeneratoreTorneo.
     */
    @Test
    public void testLogIn() {
        System.out.println("logIn");
        String cf = "";
        String p = "";
        GeneratoreTorneo instance = null;
        String expResult = "";
        String result = instance.logIn(cf, p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logOut method, of class GeneratoreTorneo.
     */
    @Test
    public void testLogOut() {
        System.out.println("logOut");
        String cf = "";
        String p = "";
        GeneratoreTorneo instance = null;
        String expResult = "";
        String result = instance.logOut(cf, p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminazioneDiretta method, of class GeneratoreTorneo.
     */
    @Test
    public void testEliminazioneDiretta() {
        System.out.println("eliminazioneDiretta");
        String nomeTorneo = "";
        int anno = 0;
        List<Squadra> squadre = null;
        List<Arbitro> arbitri = null;
        GeneratoreTorneo instance = null;
        instance.eliminazioneDiretta(nomeTorneo, anno, squadre, arbitri);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of italiana method, of class GeneratoreTorneo.
     */
    @Test
    public void testItaliana() {
        System.out.println("italiana");
        String nomeTorneo = "";
        int anno = 0;
        List<Squadra> squadre = null;
        List<Arbitro> arbitri = null;
        GeneratoreTorneo instance = null;
        instance.italiana(nomeTorneo, anno, squadre, arbitri);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RimuoviTorneo method, of class GeneratoreTorneo.
     */
    @Test
    public void testRimuoviTorneo() {
        System.out.println("RimuoviTorneo");
        Torneo t = null;
        GeneratoreTorneo instance = null;
        instance.RimuoviTorneo(t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
