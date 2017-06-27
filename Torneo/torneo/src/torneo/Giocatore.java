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
public class Giocatore extends Utente{
    private int numero;

    /**
     * Costruttore
     * @param nome
     * @param cognome
     * @param numero 
     */
    public Giocatore(String nome, String cognome, int numero) {
        super(nome, cognome);
        this.numero = numero;
    }
    /**
    ritorna numero 
     */
    public int getNumero(){
        return numero;
    }
    /**
    converte in testo 
     */
    @Override
    public String toString() {
        return this.getNome()+" "+this.getCognome()+" "+numero+"\n";
    }
}
