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

/**
 *
 * @author giuliafumagalli
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException {
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
        BufferedReader br = new BufferedReader(new FileReader(new File("files/squadre.txt")));
        //Map<String, Squadra> listaSquadre =  new HashMap<>();
        List<Squadra> listaSquadre = new ArrayList<>();
        List<Giocatore> l = new ArrayList<>();
        while(br.ready()){
            String[] ls = br.readLine().split("\t");
            if(ls[0].equals("-")){
                Squadra s = new Squadra(ls[1], ls[2], ls[3], l);
                //listaSquadre.put(ls[1], s);
                listaSquadre.add(s);
                l.clear();
            } else {
                Giocatore g = new Giocatore(ls[0], ls[1], Integer.parseInt(ls[2]));
                l.add(g);
            }
        }
        BufferedReader br2 = new BufferedReader(new FileReader(new File("files/arbitri.txt")));
        //Map<String, Arbitro> listaArbitri =  new HashMap<>();
        List<Arbitro> listaArbitri = new ArrayList<>();
        while(br2.ready()){
            String[] ls = br2.readLine().split("\t");
            Arbitro a = new Arbitro(ls[0], ls[1], ls[2], ls[3]);
            //a.logIn(ls[2], ls[3]);
            //listaArbitri.put(ls[2], a);
            listaArbitri.add(a);
        }
        
        GeneratoreTorneo gen = new GeneratoreTorneo("ITALIANA", listaSquadre, listaArbitri);
        
        
        
//        BufferedReader br3 = new BufferedReader(new FileReader(new File("files/partite.txt")));
//        List<Partita> listaPartite = new ArrayList<>();
//        while(br3.ready()){
//            String[] ls = br3.readLine().split("\t");
//            if(ls[0].equals("-")){
//                Partita p = new Partita(listaSquadre.get(ls[1]), listaSquadre.get(ls[2]), listaArbitri.get(ls[3]), ls[4]);
//                p.setStatoPartita(StatoPartita.valueOf(ls[5]));
//                listaPartite.add(p);
//            } else {
//                listaPartite.get(listaPartite.size() - 1).setGoal(Integer.parseInt(ls[0]), ls[1], ls[2], Integer.parseInt(ls[3]));
//            }
//        }
//        
//        
//        Italiana torneoItaliana = new Italiana(listaPartite);
//        ClassificaItaliana classificaItaliana = new ClassificaItaliana(torneoItaliana);
//        System.out.println("\nClassifica Italiana \n" + classificaItaliana.toString());
//        
//        EliminazioneDiretta torneoElDiretta = new EliminazioneDiretta(listaPartite);
//        ClassificaEliminazioneDiretta classificaElDiretta = new ClassificaEliminazioneDiretta(torneoElDiretta);
//        System.out.println("Classifica Eliminazione Diretta");
//        for(Squadra s: classificaElDiretta.printRisultato()){
//            System.out.println(s.getNome());
//        }


    }
    
}