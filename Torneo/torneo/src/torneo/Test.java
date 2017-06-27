/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import gui.SceltaTorneoGUI;
import exception.GiocatoreInesistenteException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author giuliafumagalli
 */
public class Test {
//    static DatabaseInterfaceFactory q;
    public static void main(String[] args) throws FileNotFoundException, IOException {
//        q = new DatabaseInterfaceFactory();
        List<Torneo> tornei = new ArrayList<>();
//        try {
//            tornei = Test.q.makeTorneoTable().getTorneo();
//        } catch (RemoteException ex) {
//            Logger.getLogger(Torneo.class.getName()).log(Level.SEVERE, null, ex);
//        }
        List<Partita> pa = new ArrayList<>();
        List<Partita> pb = new ArrayList<>();
        Arbitro arbitro1 = new Arbitro("c", "c", "c", "c", true);
        Giocatore g1 = new Giocatore("ciao", "ciao", 8);
        Giocatore g2 = new Giocatore("ciao", "ciao", 9);
        Giocatore g3 = new Giocatore("ciao", "ciao", 10);
        Giocatore g4 = new Giocatore("ciao", "ciao", 11);
        ArrayList<Giocatore> giocatori = new ArrayList<>();
        ArrayList<Giocatore> giocatori2 = new ArrayList<>();
        giocatori.add(g1);
        giocatori.add(g2);
        giocatori2.add(g3);
        giocatori2.add(g4);
        Squadra squadra1 = new Squadra("ciao", "ciao", "ciao", giocatori, true);
        Squadra squadra2 = new Squadra("ciao", "ciao", "ciao", giocatori2, true);
        Partita partita = new PartitaEliminazioneDiretta(1, squadra1, squadra2, arbitro1, "ciao", StatoPartita.PROGRAMMATA, "nome", 1990, true);
        Partita partita2 = new PartitaItaliana(2, squadra1, squadra2, arbitro1, "ciao", StatoPartita.PROGRAMMATA, "nome", 1990, true);
        pa.add(partita);
        pb.add(partita2);
        Torneo addTorneo = new EliminazioneDiretta("Prova", 2017, pa, true);
        Torneo torneo = new Italiana("ciao", 2000, pb, true);
        tornei.add(addTorneo);
        tornei.add(torneo);
        GeneratoreTorneo gentorneo = new GeneratoreTorneo("g", "g", "g", "g");
        
        JFrame sceltatorneo = new SceltaTorneoGUI(tornei, gentorneo);
        sceltatorneo.setSize(1000, 675);
        sceltatorneo.setLocation(400, 250);
        
                //----------------------------------------------------------------------
        // Inizio client-server
        
//        boolean connessione = false;
//        EchoServer server = new EchoServer(4020); 
//        
//        try 
//        {
//            connessione = server.startServer();
//            server.setMessaggio("Inserire parametri per generatore: (cf password)");
//            server.mandaMessaggio();
//            connessione = server.controllaSocket();
//            
//        } 
//        catch (IOException e) 
//        {
//            System.err.println(e.getMessage()); 
//        }
        
//        gentorneo.logIn(server.getParametri().get(0), server.getParametri().get(1));
//        
//        System.out.println("Stato di connessione:"+connessione);
//        
//        if(gentorneo.getAutenticazione().equals(Autenticazione.AUTENTICATO)){
//
//            
//            server.setMessaggio("Inserire numero per indicare scelta: 1: aggiungi arbitro 2: rimuovi arbitro");
//            server.mandaMessaggio();
//            connessione = server.controllaSocket();
//
//
//            while(connessione)
//            {
//                switch(server.getParametri().get(0))
//                {
//                    case "1": //Aggiungi arbitro
//                    {
//                        server.setMessaggio("Inserire parametri per definizione nuovo utente arbitro: (nome cognome username password)");
//                        server.mandaMessaggio();
//                        connessione = server.controllaSocket();
//                        Arbitro arbitro = new Arbitro(server.getParametri().get(0), server.getParametri().get(1), server.getParametri().get(2), server.getParametri().get(3), true);
//                        
//                    } break;
//
//                    case "2": //Rimuovi arbitro
//                    {
//                        server.setMessaggio("Inserire parametri per dell'utente arbitro da rimuovere: (nome cognome username password)");
//                        server.mandaMessaggio();
//                        connessione = server.controllaSocket();
//                        Arbitro arbitro = new Arbitro(server.getParametri().get(0), server.getParametri().get(1), server.getParametri().get(2), server.getParametri().get(3), false);
//                        try {
//                            Test.q.makeArbitroTable().deleteArbitro(arbitro);
//                        } catch (RemoteException ex) {
//                            Logger.getLogger(Arbitro.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    } break;
//
//                    default: 
//                    {
//                        server.setMessaggio("Scelta non contemplata.");
//                        server.mandaMessaggio();
//                    } break;
//                }
//            }
//
//            System.out.println("Il socket è stato chiuso.");
//        } else {
//            server.setMessaggio("Login errato, chiudere l'applicazione e ritentare");
//            server.mandaMessaggio();
//            server.chiudiSocket();
//        }
               
    }
   
}