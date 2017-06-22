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
public class Goal {
    private int minuto;
    private Giocatore giocatore;
    
    /**
     * 
     * @param minuto
     * @param giocatore 
     */
    public Goal(int minuto, Giocatore giocatore){
        this.minuto = minuto;
        this.giocatore = giocatore;
    }
    
    public int getMinuto(){
        return minuto;
    }
    
    public Giocatore getGiocatore(){
        return giocatore;
    }
    
    @Override
    public String toString() {
        return "Goal di "+giocatore.getNome()+" "+giocatore.getCognome()+" ("+giocatore.getNumero()+") al minuto "+minuto;
    }
}
