/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.util.*;

/**
 *
 * @author paolacarulli
 */
public class EliminazioneDiretta extends Torneo {
    private ClassificaEliminazioneDiretta classifica;
    private List<Squadra> squadreNelTorneo = new ArrayList<>();
    private List<Squadra> squadreDaRimuovere = new ArrayList<>();
    
    public EliminazioneDiretta(List<Partita> p) {
        super(p);
        for(Partita pa : this.getPartite()){
            if(!(squadreNelTorneo.contains(pa.getSquadra1()))){
                squadreNelTorneo.add(pa.getSquadra1());
            }
            if(!(squadreNelTorneo.contains(pa.getSquadra2()))){
                squadreNelTorneo.add(pa.getSquadra2());
            }
        }
    }
    public void setVincitore(){
        for(Partita p: this.getPartite()){
            // CASO 1: GIOCANO IN CASA DELLA SQUADRA1 ALL'ANDATA
            if(p.getDoveGioca()){ // getDoveGioca ridà vero se si gioca in casa della squadra 1, altrimenti ridà falso
                if(p.getGoalSquadra1() > p.getGoalSquadra2()){ // La squadra 1 vince in casa, ora bisogna fare un controllo sulla partita in casa della squadra 2 per sapere chi vince
                    for(Partita pa: this.getPartite()){
                        if(!(pa.getDoveGioca()) && pa.getSquadra1().equals(p.getSquadra1()) && pa.getSquadra2().equals(p.getSquadra2())){ // Cerco la partita di ritorno, cioè con le stesse squadre, ma svoltasi in casa della squadra 2
                            if(pa.getGoalSquadra1() > pa.getGoalSquadra2()){ // Vince nuovamente la squadra1, quindi la squadra2 viene eliminata dal torneo
                                squadreDaRimuovere.add(p.getSquadra2());
                            }
                            else{
                                if(pa.getGoalSquadra1() < pa.getGoalSquadra2()){ // La squadra2 ha vinto in casa, quindi per capire chi eliminare dal torneo si guarda quella che ha fatto il maggior numero di goal fuori casa
                                    if(p.getGoalSquadra2() > pa.getGoalSquadra1()){ // La squadra2 ha fatto più goal fuori casa
                                        squadreDaRimuovere.add(p.getSquadra1());
                                    }
                                    else{ // Manca da aggiungere il caso in cui hanno fatto lo stesso numero di goal fuori casa, per semplicità la ometto per ora
                                        squadreDaRimuovere.add(p.getSquadra2());
                                    }
                                }
                            }
                        }
                    }  
                }
                else { // La squadra1 perde in casa, ora faccio un controllo sull'altra partita
                     for(Partita pa: this.getPartite()){
                        if(!(pa.getDoveGioca()) && pa.getSquadra1().equals(p.getSquadra1()) && pa.getSquadra2().equals(p.getSquadra2())){ // Cerco la partita di ritorno, cioè con le stesse squadre, ma svoltasi in casa della squadra 2
                            if(pa.getGoalSquadra1() < pa.getGoalSquadra2()){ // Vince nuovamente la squadra2, quindi la squadra1 viene eliminata dal torneo
                                squadreDaRimuovere.add(p.getSquadra1());
                            }
                            else{
                                if(pa.getGoalSquadra1() > pa.getGoalSquadra2()){ // La squadra2 ha perso in casa, quindi per capire chi eliminare dal torneo si guarda quella che ha fatto il maggior numero di goal fuori casa
                                    if(p.getGoalSquadra2() > pa.getGoalSquadra1()){ // La squadra2 ha fatto più goal fuori casa
                                        squadreDaRimuovere.add(p.getSquadra1());
                                    }
                                    else{ // Manca da aggiungere il caso in cui hanno fatto lo stesso numero di goal fuori casa, per semplicità la ometto per ora
                                        squadreDaRimuovere.add(p.getSquadra2());
                                    }
                                }
                            }
                        }
                    }  
                    
                }
            }
            else {
            // CASO 2: GIOCANO IN CASA DELLA SQUADRA2 ALL'ANDATA
            if(!(p.getDoveGioca())){ 
                if(p.getGoalSquadra2() > p.getGoalSquadra1()){ // La squadra 2 vince in casa, ora bisogna fare un controllo sulla partita in casa della squadra 1 per sapere chi vince
                    for(Partita pa: this.getPartite()){
                        if(pa.getDoveGioca() && pa.getSquadra1().equals(p.getSquadra1()) && pa.getSquadra2().equals(p.getSquadra2())){ // Cerco la partita di ritorno, cioè con le stesse squadre, ma svoltasi in casa della squadra 2
                            if(pa.getGoalSquadra2() > pa.getGoalSquadra1()){ // Vince nuovamente la squadra2, quindi la squadra1 viene eliminata dal torneo
                                squadreDaRimuovere.add(p.getSquadra1());
                            }
                            else{
                                if(pa.getGoalSquadra2() < pa.getGoalSquadra1()){ // La squadra1 ha vinto in casa, quindi per capire chi eliminare dal torneo si guarda quella che ha fatto il maggior numero di goal fuori casa
                                    if(p.getGoalSquadra1() > pa.getGoalSquadra2()){ // La squadra1 ha fatto più goal fuori casa
                                        squadreDaRimuovere.add(p.getSquadra2());
                                    }
                                    else{ // Manca da aggiungere il caso in cui hanno fatto lo stesso numero di goal fuori casa, per semplicità la ometto per ora
                                        squadreDaRimuovere.add(p.getSquadra1());
                                    }
                                }
                            }
                        }
                    }  
                }
                else { // La squadra2 perde in casa, ora faccio un controllo sull'altra partita
                     for(Partita pa: this.getPartite()){
                        if(pa.getDoveGioca() && pa.getSquadra1().equals(p.getSquadra1()) && pa.getSquadra2().equals(p.getSquadra2())){ // Cerco la partita di ritorno, cioè con le stesse squadre, ma svoltasi in casa della squadra 2
                            if(pa.getGoalSquadra1() > pa.getGoalSquadra2()){ // Perde nuovamente la squadra2, quindi la squadra2 viene eliminata dal torneo
                                squadreDaRimuovere.add(p.getSquadra2());
                            }
                            else{
                                if(pa.getGoalSquadra1() < pa.getGoalSquadra2()){ // La squadra2 vince fuori casa, quindi per capire chi eliminare dal torneo si guarda quella che ha fatto il maggior numero di goal fuori casa
                                    if(p.getGoalSquadra1() > pa.getGoalSquadra2()){ // La squadra1 ha fatto più goal fuori casa
                                        squadreDaRimuovere.add(p.getSquadra2());
                                    }
                                    else{ // Manca da aggiungere il caso in cui hanno fatto lo stesso numero di goal fuori casa, per semplicità la ometto per ora
                                        squadreDaRimuovere.add(p.getSquadra1());
                                    }
                                }
                            }
                        }
                    }  
                    
                }
            }
        }
        }
        
    }
    @Override
    public void printRisultato() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
