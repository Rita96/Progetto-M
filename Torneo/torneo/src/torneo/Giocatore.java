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

    public Giocatore(String nome, String cognome, int numero) {
        super(nome, cognome);
        this.numero = numero;
    }
    public int getNumero(){
        return numero;
    }
}
