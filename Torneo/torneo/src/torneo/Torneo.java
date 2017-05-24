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
    private String nome;
    private List<Partita> partite = new ArrayList<>();
    private List<Arbitro> arbitri = new ArrayList<>();
    
    public Torneo(String nome, List <Partita> p){
        this.nome = nome;
        this.partite.addAll(p);
    }
    public List<Partita> getPartite(){
        return partite;
    }
    public List getArbitri() {
        return arbitri;
    }
    public void aggiungiPartita(Partita p){
        this.partite.add(p);
    }
    public String getNome() {
        return nome;
    }
}
