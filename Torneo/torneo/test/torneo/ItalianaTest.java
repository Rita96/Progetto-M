/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Giulia
 */
public class ItalianaTest {
   
    private Italiana test;
    private ClassificaItaliana classifica;
    private Arbitro a;
    private Squadra S1, S2;
    private Partita partita1, partita2;
    
    
    public ItalianaTest() {
        List<Partita> partite = new ArrayList<>();
        List<Giocatore> listaGiocatoriS1 = new ArrayList<>();
        listaGiocatoriS1.add(new Giocatore("A", "A", 1));
        listaGiocatoriS1.add(new Giocatore("B", "B", 2));
        List<Giocatore> listaGiocatoriS2 = new ArrayList<>();
        listaGiocatoriS2.add(new Giocatore("C", "C", 3));
        listaGiocatoriS2.add(new Giocatore("D", "D", 4));
        S1 = new Squadra("S1", "blu", "Cagliari", listaGiocatoriS1, false);
        S2 = new Squadra("S2", "rosso", "Milano", listaGiocatoriS2, false);
        a = new Arbitro("A", "A", "A", "A", false);
        a.logIn("A", "A");
        partita1 = new Partita(1, S1, S2, a, "Cagliari", StatoPartita.PROGRAMMATA, "Calcio", 2017, false);
        partita2 = new Partita(2, S2, S1, a, "Milano", StatoPartita.PROGRAMMATA, "Calcio", 2017, false);
        partite.add(partita1);
        partite.add(partita2);
        test = new Italiana("Calcio", 2017, partite, false);
        classifica = new ClassificaItaliana(test);
        partita1.setStatoPartita(StatoPartita.REGOLAMENTARE);
        partita1.setGoal(0, "A", "A", 1);
        partita1.setStatoPartita(StatoPartita.RIGORI);
        partita1.setGoal(95, "A", "A", 1);
        partita1.setStatoPartita(StatoPartita.TERMINATA);
        partita2.setStatoPartita(StatoPartita.REGOLAMENTARE);
        partita2.setGoal(0, "C", "C", 3);
        partita2.setStatoPartita(StatoPartita.SUPPLEMENTARI);
        partita2.setGoal(95, "A", "A", 1);
        partita2.setStatoPartita(StatoPartita.TERMINATA);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setItaliana method, of class Italiana.
     */
    @Test
    public void testSetItaliana() {
        System.out.println("ItalianaTest - setItaliana");
        System.out.println(classifica.toString());
    }
    
    /**
     * Test of aggiornaClassifica method, of class Italiana.
     */
    @Test
    public void testAggiornaClassifica() {
        System.out.println("ItalianaTest - aggiornaClassifica");
        // aggiorno la prima partita
        int expResultS1 = test.getClassifica().get(S1) + 3;
        int expResultS2 = test.getClassifica().get(S2);
        test.aggiornaClassifica(partita1);
        int resultS1 = test.getClassifica().get(S1);
        int resultS2 = test.getClassifica().get(S2);
        assertEquals(expResultS1, resultS1);
        assertEquals(expResultS2, resultS2);
        
        // aggiorno la seconda partita
        expResultS1 = test.getClassifica().get(S1) + 1;
        expResultS2 = test.getClassifica().get(S2) + 1;
        test.aggiornaClassifica(partita2);
        resultS1 = test.getClassifica().get(S1);
        resultS2 = test.getClassifica().get(S2);
        assertEquals(expResultS1, resultS1);
        assertEquals(expResultS2, resultS2);
    }

    /**
     * Test of finita method, of class Italiana.
     */
    @Test
    public void testFinita() {
        System.out.println("ItalianaTest - finita");
        // dato che esegue finita prima di AggiornaClassifica
        test.aggiornaClassifica(partita1);
        test.aggiornaClassifica(partita2);
        boolean expResult = true;
        boolean result = test.finita();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClassifica method, of class Italiana.
     */
    @Test
    public void testGetClassifica() {
        System.out.println("ItalianaTest - getClassifica");
        Map<Squadra, Integer> expResult = new HashMap<>();
        expResult.put(S2, 1);
        expResult.put(S1, 4);
        // dato che esegue getClassifica prima di AggiornaClassifica
        test.aggiornaClassifica(partita1);
        test.aggiornaClassifica(partita2);
        Map<Squadra, Integer> result = test.getClassifica();
        assertEquals(expResult, result);
    }
}
