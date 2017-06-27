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
    
    /**
     * Costruttore
     * @param nome
     * @param cognome 
     */
    public Utente(String nome, String cognome){
        this.nome = nome;
        this.cognome = cognome;
    }
    
    /**
     * 
     * @return nome utente
     */
    public String getNome(){
        return nome;
    }
    
    /**
     * 
     * @return cognome utente
     */
    public String getCognome(){
        return cognome;
    }
    
     /**
     * permette di modificare l'arbitro
     */
    public String ModificaArbitro(String s) {
        return nome = s;
    }
    
     /**
     * converte in stringa
     */
    @Override
    public String toString() {
        return nome + " " + cognome;
    }
}
