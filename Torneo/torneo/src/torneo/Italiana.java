/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.util.ArrayList;
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
    private Map<Squadra, Integer> SquadraGoalTotali = new HashMap<>();
    
    public Italiana(String nome, List<Partita> p) {
        super(nome, p);
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
        int punteggioCasa = 0, punteggioOspite = 0, partiteGiocateCasa = 0, partiteGiocateOspite = 0, goalCasa = 0, goalOspite = 0;
        if(SquadraPunteggioRelativo.containsKey(p.getSquadraCasa())){ 
            punteggioCasa = SquadraPunteggioRelativo.get(p.getSquadraCasa());
            partiteGiocateCasa = SquadraPartiteGiocate.get(p.getSquadraCasa());
            goalCasa = SquadraGoalTotali.get(p.getSquadraCasa());
        }     
        if(SquadraPunteggioRelativo.containsKey(p.getSquadraOspite())){
            punteggioOspite = SquadraPunteggioRelativo.get(p.getSquadraOspite());
            partiteGiocateOspite = SquadraPartiteGiocate.get(p.getSquadraOspite());
            goalOspite = SquadraGoalTotali.get(p.getSquadraOspite());
        }
        
        
        switch (p.getStatoPartita()) {
            case TERMINATA:
                if(p.getGoalSquadraCasaRegolare() > p.getGoalSquadraOspiteRegolare()){
                    p.setPuntiSquadra(3, p.getSquadraCasa().getNome());
                    p.setPuntiSquadra(0, p.getSquadraOspite().getNome());
                    SquadraPunteggioRelativo.put(p.getSquadraCasa(), punteggioCasa+3);
                    SquadraPunteggioRelativo.put(p.getSquadraOspite(), punteggioOspite);
                }
                if(p.getGoalSquadraCasaRegolare() < p.getGoalSquadraOspiteRegolare()){
                    p.setPuntiSquadra(0, p.getSquadraCasa().getNome());
                    p.setPuntiSquadra(3, p.getSquadraOspite().getNome());
                    SquadraPunteggioRelativo.put(p.getSquadraCasa(), punteggioCasa);
                    SquadraPunteggioRelativo.put(p.getSquadraOspite(), punteggioOspite+3);
                }
                if(p.getGoalSquadraCasaRegolare() == p.getGoalSquadraOspiteRegolare()){
                    p.setPuntiSquadra(1, p.getSquadraCasa().getNome());
                    p.setPuntiSquadra(1, p.getSquadraOspite().getNome());
                    SquadraPunteggioRelativo.put(p.getSquadraCasa(), punteggioCasa+1);
                    SquadraPunteggioRelativo.put(p.getSquadraOspite(), punteggioOspite+1);
                }
                SquadraPartiteGiocate.put(p.getSquadraCasa(), partiteGiocateCasa+1);
                SquadraPartiteGiocate.put(p.getSquadraOspite(), partiteGiocateOspite+1);
                SquadraGoalTotali.put(p.getSquadraCasa(), p.getGoalSquadraCasaRegolare() + goalCasa);
                SquadraGoalTotali.put(p.getSquadraOspite(), p.getGoalSquadraOspiteRegolare() + goalOspite);
            break;
            case PROGRAMMATA:
                SquadraPunteggioRelativo.put(p.getSquadraCasa(), punteggioCasa);
                SquadraPunteggioRelativo.put(p.getSquadraOspite(), punteggioOspite);
                SquadraGoalTotali.put(p.getSquadraCasa(), goalCasa);
                SquadraGoalTotali.put(p.getSquadraOspite(), goalOspite);
                SquadraPartiteGiocate.put(p.getSquadraCasa(), partiteGiocateCasa);
                SquadraPartiteGiocate.put(p.getSquadraOspite(), partiteGiocateOspite);
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
        return f == i;
    }
    
    public Map<Squadra,Integer> getClassifica(){
        Map<Squadra, Integer> sortedMapDesc = sortByComparator(SquadraPunteggioRelativo, false);
//        if(finita()){
            List<Squadra> SquadrePunteggioMassimo = new ArrayList<>();
            int MaxPunteggio = 0;
            for (Map.Entry<Squadra, Integer> entry : sortedMapDesc.entrySet())
            {   if(entry.getValue()>MaxPunteggio){
                    MaxPunteggio = entry.getValue();
                }
            }
            for (Map.Entry<Squadra, Integer> entry : sortedMapDesc.entrySet())
            {   if(entry.getValue()==MaxPunteggio){
                    SquadrePunteggioMassimo.add(entry.getKey());
                }
            }
            if(SquadrePunteggioMassimo.size()>1){
                // se più squadre hanno lo stesso punteggio, faccio una classifica tra le partite solo di queste squadre
                Map<Squadra, Integer> PareggioSquadre = new HashMap<>();
                for(Partita p : this.getPartite()){
                    int vCasa = 0, vOspite = 0;
                    if(PareggioSquadre.containsKey(p.getSquadraCasa())){ 
                        vCasa = PareggioSquadre.get(p.getSquadraCasa());
                    }     
                    if(PareggioSquadre.containsKey(p.getSquadraOspite())){
                        vOspite = PareggioSquadre.get(p.getSquadraOspite());
                    }
                    if(SquadrePunteggioMassimo.contains(p.getSquadraCasa()) && SquadrePunteggioMassimo.contains(p.getSquadraOspite())){
                        if(p.getGoalSquadraCasaRegolare()>p.getGoalSquadraOspiteRegolare())
                        {   PareggioSquadre.put(p.getSquadraCasa(), vCasa+3);
                            PareggioSquadre.put(p.getSquadraOspite(), vOspite);
                        }
                        if(p.getGoalSquadraCasaRegolare()<p.getGoalSquadraOspiteRegolare()){
                            PareggioSquadre.put(p.getSquadraCasa(), vCasa);
                            PareggioSquadre.put(p.getSquadraOspite(), vOspite+3);
                        }
                        if(p.getGoalSquadraCasaRegolare() == p.getGoalSquadraOspiteRegolare()){
                            PareggioSquadre.put(p.getSquadraCasa(), vCasa+1);
                            PareggioSquadre.put(p.getSquadraOspite(), vOspite+1);
                        }
                        // se ci sarà un getPunteggio per la gui si potrà sostituire con quello
                    }
                }
                SquadrePunteggioMassimo.clear();
                MaxPunteggio = 0;
                for (Map.Entry<Squadra, Integer> entry : PareggioSquadre.entrySet())
                {   if(entry.getValue()>MaxPunteggio){
                    MaxPunteggio = entry.getValue();
                    }
                }
                for (Map.Entry<Squadra, Integer> entry : PareggioSquadre.entrySet())
                {   if(entry.getValue()==MaxPunteggio){
                    SquadrePunteggioMassimo.add(entry.getKey());
                    }
                }
                Map<Squadra, Integer> Ordinato = new HashMap<>();
                if(SquadrePunteggioMassimo.size()==1){
                    Map<Squadra, Integer> PareggioSquadreOrdinato = sortByComparator(PareggioSquadre, false);
                    for (Map.Entry<Squadra, Integer> entry : PareggioSquadreOrdinato.entrySet())
                    {   
                        Ordinato.put(entry.getKey(), sortedMapDesc.get(entry.getKey()));
                        sortedMapDesc.remove(entry.getKey());
                    }
                } else {
                    //da finire di implementare, conteggio Goal
                    Map<Squadra, Integer> SquadreGoalPunteggioMassimo = new HashMap<>();
                    for(Squadra s : SquadrePunteggioMassimo){
                        SquadreGoalPunteggioMassimo.put(s, SquadraGoalTotali.get(s));
                    }
                    Map<Squadra, Integer> SquadreGoalPunteggioMassimoOrdinato = sortByComparator(SquadreGoalPunteggioMassimo, false);
                    for (Map.Entry<Squadra, Integer> entry : SquadreGoalPunteggioMassimoOrdinato.entrySet())
                    {   
                        Ordinato.put(entry.getKey(), sortedMapDesc.get(entry.getKey()));
                        sortedMapDesc.remove(entry.getKey());
                    }
                }
                Ordinato.putAll(sortedMapDesc);
                sortedMapDesc.clear();
                sortedMapDesc.putAll(Ordinato);
            }
  //      }
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
    public String toString() {
        return "Torneo "+this.getNome()+" del tipo Italiana";
    }
    
}
