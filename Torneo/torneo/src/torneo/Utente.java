/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

/**
 *
 * @author paolacarulli
 */
public class Utente {
    private String nome; 
    private String cognome;
    
    public Utente(String nome, String cognome){
        this.nome = nome;
        this.cognome = cognome;
    }
    public String getNome(){
        return nome;
    }
    public String getCognome(){
        return cognome;
    }
    public String ModificaArbitro(String s) {
        return nome = s;
    }
    @Override
    public String toString() {
        return nome + " " + cognome;
    }
}
