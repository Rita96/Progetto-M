/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

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
public class EliminazioneDirettaTest {
  
    private EliminazioneDiretta test;
    private ClassificaEliminazioneDiretta classifica;
    private Arbitro a;
    private Squadra S1, S2, S3, S4;
    private Partita partita1, partita2, partita3, partita4, partita5, partita6;
    
    
    public EliminazioneDirettaTest() {
        List<Partita> partite = new ArrayList<>();
        List<Giocatore> listaGiocatoriS1 = new ArrayList<>();
        listaGiocatoriS1.add(new Giocatore("A", "A", 1));
        listaGiocatoriS1.add(new Giocatore("B", "B", 2));
        List<Giocatore> listaGiocatoriS2 = new ArrayList<>();
        listaGiocatoriS2.add(new Giocatore("C", "C", 3));
        listaGiocatoriS2.add(new Giocatore("D", "D", 4));
        S1 = new Squadra("S1", "blu", "Cagliari", listaGiocatoriS1, false);
        S2 = new Squadra("S2", "rosso", "Milano", listaGiocatoriS2, false);
        List<Giocatore> listaGiocatoriS3 = new ArrayList<>();
        listaGiocatoriS3.add(new Giocatore("E", "E", 5));
        listaGiocatoriS3.add(new Giocatore("F", "F", 6));
        List<Giocatore> listaGiocatoriS4 = new ArrayList<>();
        listaGiocatoriS4.add(new Giocatore("G", "G", 7));
        listaGiocatoriS4.add(new Giocatore("H", "H", 8));
        S3 = new Squadra("S3", "blu", "Roma", listaGiocatoriS3, false);
        S4 = new Squadra("S4", "rosso", "Torino", listaGiocatoriS4, false);
        a = new Arbitro("A", "A", "A", "A", false);
        a.logIn("A", "A");
        partita1 = new Partita(1, S1, S2, a, "Cagliari", StatoPartita.PROGRAMMATA, "Calcio", 2017, false);
        partita2 = new Partita(2, S2, S1, a, "Milano", StatoPartita.PROGRAMMATA, "Calcio", 2017, false);
        partita3 = new Partita(3, S3, S4, a, "Roma", StatoPartita.PROGRAMMATA, "Calcio", 2017, false);
        partita4 = new Partita(4, S4, S3, a, "Torino", StatoPartita.PROGRAMMATA, "Calcio", 2017, false);
        partita5 = new Partita(5, S1, S3, a, "Cagliari", StatoPartita.PROGRAMMATA, "Calcio", 2017, false);
        partita6 = new Partita(6, S3, S1, a, "Roma", StatoPartita.PROGRAMMATA, "Calcio", 2017, false);
        partite.add(partita1);
        partite.add(partita2);
        partite.add(partita3);
        partite.add(partita4);
        partite.add(partita5);
        partite.add(partita6);
        test = new EliminazioneDiretta("Calcio", 2017, partite, false);
        classifica = new ClassificaEliminazioneDiretta(test);
        partita1.setStatoPartita(StatoPartita.REGOLAMENTARE);
        partita1.setGoal(0, "A", "A", 1);
        partita1.setStatoPartita(StatoPartita.RIGORI);
        partita1.setGoal(95, "A", "A", 1);
        partita1.setStatoPartita(StatoPartita.TERMINATA);
        partita2.setStatoPartita(StatoPartita.REGOLAMENTARE);
        partita2.setGoal(0, "C", "C", 3);
        partita2.setStatoPartita(StatoPartita.SUPPLEMENTARI);
        partita2.setGoal(95, "A", "A", 1);
        partita2.setGoal(97, "A", "A", 1);
        partita2.setStatoPartita(StatoPartita.TERMINATA);
        partita3.setStatoPartita(StatoPartita.REGOLAMENTARE);
        partita3.setGoal(0, "E", "E", 5);
        partita3.setStatoPartita(StatoPartita.RIGORI);
        partita3.setGoal(95, "E", "E", 5);
        partita3.setStatoPartita(StatoPartita.TERMINATA);
        partita4.setStatoPartita(StatoPartita.REGOLAMENTARE);
        partita4.setGoal(0, "E", "E", 5);
        partita4.setStatoPartita(StatoPartita.RIGORI);
        partita4.setGoal(95, "F", "F", 6);
        partita4.setStatoPartita(StatoPartita.TERMINATA);
        partita5.setStatoPartita(StatoPartita.REGOLAMENTARE);
        partita5.setGoal(0, "A", "A", 1);
        partita5.setStatoPartita(StatoPartita.RIGORI);
        partita5.setGoal(95, "A", "A", 1);
        partita5.setStatoPartita(StatoPartita.TERMINATA);
        partita6.setStatoPartita(StatoPartita.REGOLAMENTARE);
        partita6.setGoal(0, "A", "A", 1);
        partita6.setStatoPartita(StatoPartita.RIGORI);
        partita6.setGoal(95, "A", "A", 1);
        partita6.setStatoPartita(StatoPartita.TERMINATA);
    }
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setEliminazioneDiretta method, of class EliminazioneDiretta.
     */
    @Test
    public void testSetEliminazioneDiretta() {
        System.out.println("EliminazioneDirettaTest - setEliminazioneDiretta");
        System.out.println(classifica.toString());
    }

    /**
     * Test of ritorno method, of class EliminazioneDiretta.
     */
    @Test
    public void testAndataRitorno() {
        System.out.println("EliminazioneDiretta - Andata&Ritorno");
        List<Squadra> expResultSquadreNelTorneo = new ArrayList<>();
        expResultSquadreNelTorneo.add(S1);
        test.andata(partita1);
        test.andata(partita2);
        test.andata(partita3);
        test.andata(partita4);
        test.andata(partita5);
        test.andata(partita6);
        assertEquals(expResultSquadreNelTorneo, test.getSquadreNelTorneo());        
    }
    
}
