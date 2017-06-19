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
    
    public Torneo(String nome, int anno, List <Partita> p, boolean putInDatabase){
        this.nome = nome;
        this.anno = anno;
        this.partite.addAll(p);
        if(putInDatabase){
            try {
                Test.q.makeTorneoTable().putTorneo(nome, anno);
            } catch (RemoteException ex) {
                Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
       
    public List<Partita> getPartite(){
        return partite;
    }
    public List<Arbitro> getArbitri() {
        return arbitri;
    }
    public void aggiungiPartita(Partita p){
        this.partite.add(p);
    }
    public String getNome() {
        return nome;
    }
}
