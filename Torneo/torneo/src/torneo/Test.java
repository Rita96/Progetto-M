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
    static DatabaseInterfaceFactory q;
    public static void main(String[] args) throws FileNotFoundException, IOException {
        q = new DatabaseInterfaceFactory();
        List<Torneo> tornei = new ArrayList<>();
//        try {
//            tornei = Test.q.makeTorneoTable().getTorneo();
//        } catch (RemoteException ex) {
//            Logger.getLogger(Torneo.class.getName()).log(Level.SEVERE, null, ex);
//        }
        List<Partita> p = new ArrayList<>();
        Torneo addTorneo = new EliminazioneDiretta("Prova",2017, p, true);
        tornei.add(addTorneo);
        GeneratoreTorneo gentorneo = new GeneratoreTorneo("g", "g", "g", "g");
        
        JFrame sceltatorneo = new SceltaTorneoGUI(tornei, gentorneo);
        sceltatorneo.setSize(1000, 655);
        sceltatorneo.setLocation(400, 250);
        
                //----------------------------------------------------------------------
        // Inizio client-server
        
        boolean connessione = false;
        EchoServer server = new EchoServer(4020); 
        
        try 
        {
            connessione = server.startServer();
            server.setMessaggio("Inserire parametri per generatore: (cf password)");
            server.mandaMessaggio();
            connessione = server.controllaSocket();
            
        } 
        catch (IOException e) 
        {
            System.err.println(e.getMessage()); 
        }
        
        gentorneo.logIn(server.getParametri().get(0), server.getParametri().get(1));
        
        System.out.println("Stato di connessione:"+connessione);
        
        if(gentorneo.getAutenticazione().equals(Autenticazione.AUTENTICATO)){

            
            server.setMessaggio("Inserire numero per indicare scelta: 1: aggiungi arbitro 2: rimuovi arbitro");
            server.mandaMessaggio();
            connessione = server.controllaSocket();


            while(connessione)
            {
                switch(server.getParametri().get(0))
                {
                    case "1": //Aggiungi arbitro
                    {
                        server.setMessaggio("Inserire parametri per definizione nuovo utente arbitro: (nome cognome username password)");
                        server.mandaMessaggio();
                        connessione = server.controllaSocket();
                        Arbitro arbitro = new Arbitro(server.getParametri().get(0), server.getParametri().get(1), server.getParametri().get(2), server.getParametri().get(3), true);
                        
                    } break;

                    case "2": //Rimuovi arbitro
                    {
                        server.setMessaggio("Inserire parametri per dell'utente arbitro da rimuovere: (nome cognome username password)");
                        server.mandaMessaggio();
                        connessione = server.controllaSocket();
                        Arbitro arbitro = new Arbitro(server.getParametri().get(0), server.getParametri().get(1), server.getParametri().get(2), server.getParametri().get(3), false);
                        try {
                            Test.q.makeArbitroTable().deleteArbitro(arbitro);
                        } catch (RemoteException ex) {
                            Logger.getLogger(Arbitro.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } break;

                    default: 
                    {
                        server.setMessaggio("Scelta non contemplata.");
                        server.mandaMessaggio();
                    } break;
                }
            }

            System.out.println("Il socket è stato chiuso.");
        } else {
            server.setMessaggio("Login errato, chiudere l'applicazione e ritentare");
            server.mandaMessaggio();
            server.chiudiSocket();
        }
               
    }
   
}