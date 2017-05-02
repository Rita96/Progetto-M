/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author giuliafumagalli
 */
public class Italiana extends Torneo {
    private Map<Squadra, Integer> SquadraPunteggioRelativo = new HashMap<>();
    private Map<Squadra, Integer> SquadraPartiteGiocate = new HashMap<>();
    
    public Italiana(List<Partita> p) {
        super(p);
        if(p.size()>0){
            setItaliana();
        }
    }
    
    //metodo da invocare se la lista partite P nel costruttore non era vuota.
    public void setItaliana(){
        for(Partita p: this.getPartite()){
            this.aggiornaClassifica(p);
        }
    }
    
    public void aggiornaClassifica(Partita p){
        int vCasa = 0, vOspite = 0, gCasa = 0, gOspite = 0;
        if(SquadraPunteggioRelativo.containsKey(p.getSquadraCasa())){ 
            vCasa = SquadraPunteggioRelativo.get(p.getSquadraCasa());
            gCasa = SquadraPartiteGiocate.get(p.getSquadraCasa());
        }     
        if(SquadraPunteggioRelativo.containsKey(p.getSquadraOspite())){
            vOspite = SquadraPunteggioRelativo.get(p.getSquadraOspite());
            gOspite = SquadraPartiteGiocate.get(p.getSquadraOspite());
        }
        
        switch (p.getStatoPartita()) {
            case TERMINATA:
                if(p.getGoalSquadraCasaRegolare() > p.getGoalSquadraOspiteRegolare()){
                    p.setPuntiSquadra(3, p.getSquadraCasa().getNome());
                    p.setPuntiSquadra(0, p.getSquadraOspite().getNome());
                    SquadraPunteggioRelativo.put(p.getSquadraCasa(), vCasa+3);
                    SquadraPunteggioRelativo.put(p.getSquadraOspite(), vOspite);
                }
                if(p.getGoalSquadraCasaRegolare() < p.getGoalSquadraOspiteRegolare()){
                    p.setPuntiSquadra(0, p.getSquadraCasa().getNome());
                    p.setPuntiSquadra(3, p.getSquadraOspite().getNome());
                    SquadraPunteggioRelativo.put(p.getSquadraCasa(), vCasa);
                    SquadraPunteggioRelativo.put(p.getSquadraOspite(), vOspite+3);
                }
                if(p.getGoalSquadraCasaRegolare() == p.getGoalSquadraOspiteRegolare()){
                    p.setPuntiSquadra(1, p.getSquadraCasa().getNome());
                    p.setPuntiSquadra(1, p.getSquadraOspite().getNome());
                    SquadraPunteggioRelativo.put(p.getSquadraCasa(), vCasa+1);
                    SquadraPunteggioRelativo.put(p.getSquadraOspite(), vOspite+1);
                }
                SquadraPartiteGiocate.put(p.getSquadraCasa(), gCasa+1);
                SquadraPartiteGiocate.put(p.getSquadraOspite(), gOspite+1);
            break;
            case PROGRAMMATA:
                SquadraPunteggioRelativo.put(p.getSquadraCasa(), vCasa);
                SquadraPunteggioRelativo.put(p.getSquadraOspite(), vOspite);
            break;
            default:
            break;
        }
    }
    
    public boolean finita(){
        // SquadraPartiteGiocate deve essere per tutti (n-1)*2 (n Squadre)
        int i = 0;
        int f = 0;
        for (Map.Entry<Squadra, Integer> entry : SquadraPartiteGiocate.entrySet())
        {   i++;
            }
        for (Map.Entry<Squadra, Integer> entry : SquadraPartiteGiocate.entrySet())
        {   if(entry.getValue() == ((i-1)*2)){
                f++;
            }
        }
        if(f == i){
            return true;
        } else {
            return false;
        }
    }
    
    public Map<Squadra,Integer> getClassifica(){
        Map<Squadra, Integer> sortedMapDesc = sortByComparator(SquadraPunteggioRelativo, false);
        return sortedMapDesc;
    }
    
    private static Map<Squadra, Integer> sortByComparator(Map<Squadra, Integer> unsortMap, final boolean order){
        List<Map.Entry<Squadra, Integer>> list = new LinkedList<Map.Entry<Squadra, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<Squadra, Integer>>()
        {
            public int compare(Map.Entry<Squadra, Integer> o1,
                    Map.Entry<Squadra, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<Squadra, Integer> sortedMap = new LinkedHashMap<>();
        for(Map.Entry<Squadra, Integer> entry : list){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    
    
}
