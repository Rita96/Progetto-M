/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import exception.GiocatoreInesistenteException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;

/**
 *
 * @author giuliafumagalli
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //PRIMO TEST
//        Giocatore g1s1 = new Giocatore("Prova", "Prova", 1);
//        Giocatore g2s1 = new Giocatore("Prova2", "Prova2", 2);
//        Giocatore g1s2 = new Giocatore("Prova3", "Porva3", 3);
//        Giocatore g2s2 = new Giocatore("Prova4", "Prova4", 4);
//        List<Giocatore> l1 = new ArrayList<>();
//        l1.add(g1s1);
//        l1.add(g2s1);
//        List<Giocatore> l2 = new ArrayList<>();
//        l2.add(g1s2);
//        l2.add(g2s2);
//        Giocatore g2s3 = new Giocatore("Prova", "prova", 5);
//        Squadra s1 = new Squadra("S1", "blu", "Milano", l1);
//        Squadra s2 = new Squadra("S2", "rosso", "Milano", l2);
//        Arbitro a = new Arbitro("A1", "A2", "cod", "pass");
//        Partita p = new Partita(s1,s2, a, "Napoli");
//        a.logIn("cod", "pass");
//        try{ 
//            p.setCartellino(ColoreCartellino.ROSSO, g2s3);
//        } catch (GiocatoreInesistenteException e){
//            System.out.println(e.getMessage());
//        }
//        try{
//            p.setPuntiSquadra(-2, "S3");
//        } catch(RuntimeException e){
//            System.out.println(e.getMessage());
//        }

        // TEST DEL GENERATORE DEL TORNEO
//        BufferedReader br = new BufferedReader(new FileReader(new File("files/squadre.txt")));
//        List<Squadra> listaSquadre = new ArrayList<>();
//        List<Giocatore> l = new ArrayList<>();
//        while(br.ready()){
//            String[] ls = br.readLine().split("\t");
//            if(ls[0].equals("-")){
//                Squadra s = new Squadra(ls[1], ls[2], ls[3], l);
//                listaSquadre.add(s);
//                l.clear();
//            } else {
//                Giocatore g = new Giocatore(ls[0], ls[1], Integer.parseInt(ls[2]));
//                l.add(g);
//            }
//        }
//        BufferedReader br2 = new BufferedReader(new FileReader(new File("files/arbitri.txt")));
//        List<Arbitro> listaArbitri = new ArrayList<>();
//        while(br2.ready()){
//            String[] ls = br2.readLine().split("\t");
//            Arbitro a = new Arbitro(ls[0], ls[1], ls[2], ls[3]);
//            listaArbitri.add(a);
//        }
//        
//        GeneratoreTorneo gen = new GeneratoreTorneo("ITALIANA", listaSquadre, listaArbitri);
//        
        
//        BufferedReader br = new BufferedReader(new FileReader(new File("files/squadre.txt")));
//        Map<String, Squadra> listaSquadre =  new HashMap<>();
//        List<Giocatore> l = new ArrayList<>();
//        while(br.ready()){
//            String[] ls = br.readLine().split("\t");
//            if(ls[0].equals("-")){
//                Squadra s = new Squadra(ls[1], ls[2], ls[3], l);
//                listaSquadre.put(ls[1], s);
//                l.clear();
//            } else {
//                Giocatore g = new Giocatore(ls[0], ls[1], Integer.parseInt(ls[2]));
//                l.add(g);
//            }
//        }
//        BufferedReader br2 = new BufferedReader(new FileReader(new File("files/arbitri.txt")));
//        Map<String, Arbitro> listaArbitri =  new HashMap<>();
//        while(br2.ready()){
//            String[] ls = br2.readLine().split("\t");
//            Arbitro a = new Arbitro(ls[0], ls[1], ls[2], ls[3]);
//            a.logIn(ls[2], ls[3]);
//            listaArbitri.put(ls[2], a);
//        }
//        BufferedReader br3 = new BufferedReader(new FileReader(new File("files/partite.txt")));
//        List<Partita> listaPartite = new ArrayList<>();
//        while(br3.ready()){
//            String[] ls = br3.readLine().split("\t");
//            if(ls[0].equals("-")){
//                Partita p = new Partita(listaSquadre.get(ls[1]), listaSquadre.get(ls[2]), listaArbitri.get(ls[3]), ls[4], StatoPartita.valueOf(ls[5]));
//                p.setStatoPartita(StatoPartita.valueOf(ls[5]));
//                listaPartite.add(p);
//            } else {
//                listaPartite.get(listaPartite.size() - 1).setGoal(Integer.parseInt(ls[0]), ls[1], ls[2], Integer.parseInt(ls[3]));
//            }
//        }
//       
//        
//        
//        Italiana torneoItaliana = new Italiana("Calcio", listaPartite);
//        ClassificaItaliana classificaItaliana = new ClassificaItaliana(torneoItaliana);
//        System.out.println("\nClassifica Italiana \n" + classificaItaliana.toString());
//        
//        EliminazioneDiretta torneoElDiretta = new EliminazioneDiretta("nome", listaPartite);
//        ClassificaEliminazioneDiretta classificaElDiretta = new ClassificaEliminazioneDiretta(torneoElDiretta);
//        System.out.println("Classifica Eliminazione Diretta");
//        for(Squadra s: classificaElDiretta.printRisultato()){
//            System.out.println(s.getNome());
//        }
        Arbitro a = new Arbitro("a", "a", "a", "a");
        Arbitro b = new Arbitro("b", "b", "b", "b");
        Arbitro c = new Arbitro("c", "c", "c", "c");
        
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
        Squadra s3 = new Squadra("S3", "rosso", "Milano", l2);
        
        Partita p = new Partita(s1,s2, a, "Napoli", StatoPartita.PROGRAMMATA);
        
        List<Partita> pa = new ArrayList<>();
        pa.add(new Partita(s1, s2, a, "Milano", StatoPartita.REGOLAMENTARE));
        pa.add(new Partita(s1, s3, b, "Rimini", StatoPartita.SUPPLEMENTARI));
        pa.add(new Partita(s1, s2, c, "Aosta", StatoPartita.RIGORI));
        
        List<Partita> pb = new ArrayList<>();
        pb.add(new Partita(s3, s2, c, "Lecce", StatoPartita.TERMINATA));
        pb.add(new Partita(s1, s2, b, "Cagliari", StatoPartita.PROGRAMMATA));
        pb.add(new Partita(s1, s3, a, "Pavia", StatoPartita.RIGORI));

        EliminazioneDiretta torneoED = new EliminazioneDiretta("Calcio 1", pa);
        Italiana torneoIT = new Italiana("Calcio 2", pb);
        
        List<Torneo> tornei = new ArrayList<>();
        tornei.add(torneoED);
        tornei.add(torneoIT);
        
        JFrame sceltatorneo = new SceltaTorneoGUI(tornei);
        sceltatorneo.setSize(1000, 655);
        sceltatorneo.setLocation(400, 250);
    }
    
    
    
}