/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.util.*;

/**
 *
 * @author giuliafumagalli
 */
public class ClassificaItaliana {
    private Italiana torneoItaliana;
    
    public ClassificaItaliana(Italiana torneoItaliana){
        this.torneoItaliana = torneoItaliana;
    }
    
    public Map<String, Integer> printRisultato(){
        Map<String, Integer> classifica = new HashMap<>();
        for(Map.Entry<Squadra, Integer> entry: torneoItaliana.getClassifica().entrySet()){
            classifica.put(entry.getKey().getNome(), entry.getValue());
        }
        return classifica;
    }
    
    //per comodità, poi in caso non serva si può anche togliere
    @Override
    public String toString(){
        String testo = "";
        for(Map.Entry<Squadra, Integer> entry : torneoItaliana.getClassifica().entrySet()){
            testo+=entry.getKey().getNome() + " - "+ entry.getValue() + "\n";
        }
        return testo;
    }
    

}

