/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import static torneo.ColoreCartellino.*;

/**
 *
 * @author paolacarulli
 */
public class Cartellino {
    private ColoreCartellino colore;
    private String descrizione;
    private Giocatore giocatore;
    private int minuto;
    
    /**
     * Costruttore
     * @param colore
     * @param giocatore
     * @param minuto
     */
    public Cartellino(ColoreCartellino colore, Giocatore giocatore, int minuto){
        this.giocatore = giocatore;
        this.colore = colore;
        this.minuto = minuto;
        
        switch(colore){
            case GIALLO: descrizione = "Giocatore ammonito";
            break;
            case ROSSO: descrizione = "Giocatore espulso";        
        }
    }
    /** 
     * ritorna giocatore
     */
    public Giocatore getGiocatore() {
        return giocatore;
    }
    /** 
     * traduce in testo
     */
    @Override
    public String toString() {
        return "Cartellino "+colore+" al minuto "+minuto;
    }
}
