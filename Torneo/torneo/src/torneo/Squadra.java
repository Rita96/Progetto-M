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
public class Squadra {
    private String nome; 
    private String colore; 
    private String cittaProvenienza;
    private List<Giocatore> giocatori = new ArrayList<>();
    
    public Squadra(String nome, String colore, String cittaProvenienza, List<Giocatore> g){
        this.nome = nome;
        this.colore = colore; 
        this.cittaProvenienza = cittaProvenienza;
        this.giocatori.addAll(g);
    }
    public String getNome(){
        return nome;
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
}
