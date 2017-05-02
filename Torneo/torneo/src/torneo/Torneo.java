/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.util.*;

/**
 *
 * @author paolacarulli
 */
public  class Torneo {
    private List<Partita> partite = new ArrayList<>();
    
    public Torneo(List <Partita> p){
        this.partite.addAll(p);
    }
    public List<Partita> getPartite(){
        return partite;
    }
    public void aggiungiPartita(Partita p){
        this.partite.add(p);
    }
}
