/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paolacarulli
 */
public class Squadra {
    private String nome; 
    private String colore; 
    private String cittaProvenienza;
    private List<Giocatore> giocatori = new ArrayList<>();
    
    /**
     * 
     * @param nome
     * @param colore
     * @param cittaProvenienza
     * @param g giocatori che compongono la squadra
     * @param putInDatabase 
     */
    public Squadra(String nome, String colore, String cittaProvenienza, List<Giocatore> g, boolean putInDatabase){
        this.nome = nome;
        this.colore = colore; 
        this.cittaProvenienza = cittaProvenienza;

        for (Giocatore giocatore : g){
            this.giocatori.add(giocatore);
            if(putInDatabase)
            {   try {
                Test.q.makeGiocatoreTable().putGiocatore(giocatore.getNumero(), giocatore.getNome(), giocatore.getCognome(), nome, cittaProvenienza);
            } catch (RemoteException ex) {
                Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex);
            }}
        }
        
        if(putInDatabase){   
            try {
                Test.q.makeSquadraTable().putSquadra(nome, cittaProvenienza, colore);
            } catch (RemoteException ex) {
                Logger.getLogger(Squadra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String getNome(){
        return nome;
    }
    
    public String ModificaNome(String s) {
        try {
            Test.q.makeSquadraTable().updateNomeSquadra(nome, cittaProvenienza, s);
        } catch (RemoteException ex) {
            Logger.getLogger(Squadra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nome = s;
    }
    
    public String getColore(){
        return colore;
    }
    
    public String getCittaProvenienza(){
        return cittaProvenienza;
    }
    
    public List<Giocatore> getGiocatori(){
        return giocatori;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}
