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
     * 
     * @param s 
     */
        StatoPartita(String s) {
            name = s;
        }
    
        public String ModificaStato(String s) {
            return name = s;
        }
    
        public StatoPartita getStato(String s) {
            return StatoPartita.valueOf(s);
        }
    
    @Override 
        public String toString(){ 
            return name; 
        }
}
