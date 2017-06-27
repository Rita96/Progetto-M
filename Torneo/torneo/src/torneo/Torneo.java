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
public class Torneo {
    protected String nome;
    protected int anno;
    private List<Partita> partite = new ArrayList<>();
    private List<Arbitro> arbitri = new ArrayList<>();
    
    /**
     * Costruttore
     * @param nome
     * @param anno
     * @param p partite che compongono il torneo
     * @param putInDatabase 
     */
    public Torneo(String nome, int anno, List <Partita> p, boolean putInDatabase){
        this.nome = nome;
        this.anno = anno;
        this.partite.addAll(p);
        if(putInDatabase){
//            try {
//                Test.q.makeTorneoTable().putTorneo(nome, anno);
//            } catch (RemoteException ex) {
//                Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }
    
    /**
     * 
     * @return partite del torneo
     */
    public List<Partita> getPartite(){
        return partite;
    }
    
    /**
     * 
     * @return arbitri del torneo
     */
    public List<Arbitro> getArbitri() {
        return arbitri;
    }
    
     /**
     * permette di aggiungere partite
     */
    public void aggiungiPartita(Partita p){
        this.partite.add(p);
    }
    
    /**
     * 
     * @return nome del torneo
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * 
     * @return anno del torneo
     */
    public Integer getAnno() {
        return anno;
    }
}
