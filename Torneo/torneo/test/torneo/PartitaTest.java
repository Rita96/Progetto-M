/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import exception.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Giulia
 */
public class PartitaTest {
    
    private Partita test;
    private Arbitro arbitro;
    private Giocatore giocatoreA;
    
    public PartitaTest() {
        List<Giocatore> listaGiocatoriS1 = new ArrayList<>();
        giocatoreA = new Giocatore("A", "A", 1);
        listaGiocatoriS1.add(giocatoreA);
        listaGiocatoriS1.add(new Giocatore("B", "B", 2));
        List<Giocatore> listaGiocatoriS2 = new ArrayList<>();
        listaGiocatoriS2.add(new Giocatore("C", "C", 3));
        listaGiocatoriS2.add(new Giocatore("D", "D", 4));
        arbitro = new Arbitro("A", "A", "A", "A", false);
        arbitro.logIn("A", "A");
        test = new Partita(1, new Squadra("S1", "blu", "Cagliari", listaGiocatoriS1, false), new Squadra("S2", "rosso", "Milano", listaGiocatoriS2, false), arbitro, "Cagliari", StatoPartita.valueOf("REGOLAMENTARE"), "Calcio", 2017, false);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setPuntiSquadra method, of class Partita.
     */
    @Test
    public void testSetPuntiSquadra() {
        System.out.println("PartitaTest - setPuntiSquadra");
        try{
            test.setPuntiSquadra(3, "S1");
        }
        catch(SquadraIncorrettaException | IncorrectValueException e){
            fail("PartitaTest - Fallita - " + e.getMessage());
        }

    }

    /**
     * Test of setCartellino method, of class Partita.
     */
    @Test
    public void testSetCartellino() {
        System.out.println("PartitaTest - setCartellino");
        // giocatore per avviare l'eccezione
        //Giocatore giocatoreB = new Giocatore("G", "B", 3);
        try{
            test.setCartellino(ColoreCartellino.valueOf("ROSSO"), giocatoreA, 0);
        }
        catch(GiocatoreInesistenteException e){
            fail("PartitaTest - Fallita - " + e.getMessage());
        }
    }
    
    /**
     * Test of setGoal method, of class Partita.
     */
    @Test
    public void testSetGoal_giocatore() {
        System.out.println("PartitaTest - setGoal - Giocatore");
        int minuto = 0;
        // giocatore per avviare l'eccezione
        //Giocatore giocatoreB = new Giocatore("G", "B", 3);
        try{
            test.setGoal(minuto, giocatoreA);
        }
        catch(GiocatoreInesistenteException e){
            fail("setGoal - Giocatore - Fallita - " + e.getMessage());
        }
    }

    /**
     * Test of setGoal method, of class Partita.
     */
    @Test
    public void testSetGoal_Nome() {
        System.out.println("PartitaTest - setGoal - Nome");
        int minuto = 0;
        try{
            test.setGoal(minuto, "A", "A", 1);
        }
        catch(GiocatoreInesistenteException e){
            fail("setGoal - Nome - Fallita - " + e.getMessage());
        }
    }
}
