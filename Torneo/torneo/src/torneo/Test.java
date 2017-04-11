/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import exception.GiocatoreInesistenteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Giulia
 */
public class Test {
    public static void main(String[] args) {
        Giocatore g1s1 = new Giocatore("Prova", "Prova", 1);
        Giocatore g2s1 = new Giocatore("Prova2", "Prova2", 2);
        Giocatore g1s2 = new Giocatore("Prova3", "Porva3", 3);
        Giocatore g2s2 = new Giocatore("Prova4", "Prova4", 4);
        List<Giocatore> l1 = new ArrayList<>();
        l1.add(g1s1);
        l1.add(g2s1);
        List<Giocatore> l2 = new ArrayList<>();
        l2.add(g1s2);
        l2.add(g2s2);
        Giocatore g2s3 = new Giocatore("Prova", "prova", 5);
        Squadra s1 = new Squadra("S1", "blu", "Milano", l1);
        Squadra s2 = new Squadra("S2", "rosso", "Milano", l2);
        Arbitro a = new Arbitro("A1", "A2", "cod", "pass");
        Partita p = new Partita(s1,s2, a, "Napoli");
        a.logIn("cod", "pass");
        try{ 
            p.setCartellino(ColoreCartellino.ROSSO, g2s3);
        } catch (GiocatoreInesistenteException e){
            System.out.println(e.getMessage());
        }
        try{
            p.setPuntiSquadra(-2, "S3");
        } catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
    
}