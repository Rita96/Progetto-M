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
public enum StatoPartita {
    REGOLAMENTARE("REGOLAMENTARE"), SUPPLEMENTARI("SUPPLEMENTARI"), RIGORI("RIGORI"), TERMINATA("TERMINATA"), PROGRAMMATA("PROGRAMMATA"); // I tempi di recupero di eliminazione diretta contano come regolamentare
    
    String name;
    
    /**
     * Costruttore
     * @param s contiene lo stato della partita
     */
        StatoPartita(String s) {
            name = s;
        }
        
        /**
         * permette di modificare lo stato
         * @param s
         * @return 
         */
        public String ModificaStato(String s) {
            return name = s;
        }
        
        /**
         * ritorna lo stato di una partita
         * @param s 
         * @return stato della partita
         */
        public StatoPartita getStato(String s) {
            return StatoPartita.valueOf(s);
        }
    
        /**
         * 
         * @return
         */
    @Override 
        public String toString(){ 
            return name; 
        }
}
