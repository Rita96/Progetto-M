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
    private List<Squadra> squadreNelTorneo = new ArrayList<>();
    private List<Squadra> squadreDaRimuovere = new ArrayList<>();
    
    public EliminazioneDiretta(String nome, List<Partita> p) {
        super(nome, p);
        for(Partita pa : this.getPartite()){
            if(!(squadreNelTorneo.contains(pa.getSquadraCasa()))){
                squadreNelTorneo.add(pa.getSquadraCasa());
            }
            if(!(squadreNelTorneo.contains(pa.getSquadraOspite()))){
                squadreNelTorneo.add(pa.getSquadraOspite());
            }
        }
        
        setEliminazioneDiretta();
    }
    public List<Squadra> getSquadreNelTorneo(){
        return squadreNelTorneo;
    }
    
    public void setEliminazioneDiretta(){
        for(Partita p: this.getPartite()){
            this.andata(p);
        }
    }
    
    public void andata(Partita p){
        if(p.getGoalSquadraCasaRegolare() > p.getGoalSquadraOspiteRegolare()){ // All'andata vince la squadra di casa
            this.ritorno(p.getSquadraCasa(), p.getSquadraOspite(), p.getSquadraCasa(), p.getGoalSquadraCasaRegolare(), p.getGoalSquadraOspiteRegolare());
        }
        if(p.getGoalSquadraCasaRegolare() < p.getGoalSquadraOspiteRegolare()){ // All'andata vince la squadra ospite
            this.ritorno(p.getSquadraCasa(), p.getSquadraOspite(), p.getSquadraOspite(), p.getGoalSquadraOspiteRegolare(), p.getGoalSquadraCasaRegolare());
        }
        if(p.getGoalSquadraCasaRegolare() == p.getGoalSquadraOspiteRegolare()){ // Pareggiano all'andata
            this.ritorno(p.getSquadraCasa(), p.getSquadraOspite(), p.getGoalSquadraCasaRegolare(), p.getGoalSquadraOspiteRegolare());
        }
        if(squadreNelTorneo.size() == 2){
            finale(p);
        }
    }
    public void ritorno(Squadra squadraCasaAndata, Squadra squadraCasaRitorno, Squadra squadraVincitriceAndata, int goalSquadraVincenteAndata, int goalSquadraPerdenteAndata){ // Caso in cui non c'è pareggio all'andata
        for(Partita p: this.getPartite()){
            if(p.getSquadraCasa().equals(squadraCasaRitorno) && p.getSquadraOspite().equals(squadraCasaAndata)){ // Cerco la partita di ritorno
                if(p.getStatoPartita().equals(StatoPartita.TERMINATA)){
                    if(p.getGoalSquadraCasaRegolare() > p.getGoalSquadraOspiteRegolare() && squadraVincitriceAndata.equals(p.getSquadraCasa())){ // Vince sempre la squadra che era ospite all'andata e in casa al ritorno
                        squadreDaRimuovere.add(squadraCasaAndata); // Rimuovo la squadra che era in casa all'andata
                    }
                    if(p.getGoalSquadraCasaRegolare() < p.getGoalSquadraOspiteRegolare() && squadraVincitriceAndata.equals(p.getSquadraOspite())){ // Vince sempre la squadra che era in casa all'andata e ospite al ritorno
                        squadreDaRimuovere.add(squadraCasaRitorno); // Rimuovo la squadra che è in casa al ritorno
                    }
                    if(p.getGoalSquadraCasaRegolare() > p.getGoalSquadraOspiteRegolare() && squadraVincitriceAndata.equals(p.getSquadraOspite())){ // Vince all'andata la squadra che era in casa all'andata e al ritorno quella che è in casa al ritorno
                        if(goalSquadraPerdenteAndata > p.getGoalSquadraOspiteRegolare()){ // Faccio un controllo sui goal fatti quando erano rispettivamente ospiti
                            squadreDaRimuovere.add(squadraCasaAndata); // Dato che la squadra in casa al ritorno ha fatto più goal da ospite, elimino l'altra
                        }
                        if(goalSquadraPerdenteAndata < p.getGoalSquadraOspiteRegolare()){ // Fa più goal da ospite la squadra che era in casa all'andata
                            squadreDaRimuovere.add(squadraCasaRitorno);
                        } 
                        if(goalSquadraPerdenteAndata == p.getGoalSquadraOspiteRegolare()){
                            this.supplementari(p);
                        }
                    }
                    if(p.getGoalSquadraCasaRegolare() < p.getGoalSquadraOspiteRegolare() && squadraVincitriceAndata.equals(p.getSquadraCasa())){ // All'andata vince la squadra che era ospite all'andata, al ritorno vince la squadra che era ospite al ritorno
                        if(goalSquadraVincenteAndata > p.getGoalSquadraOspiteRegolare()){ // vince la squadra che era in casa al ritorno
                            squadreDaRimuovere.add(squadraCasaAndata);
                        }
                        if(goalSquadraVincenteAndata < p.getGoalSquadraOspiteRegolare()){ // vince la squadra che era in casa all'andata
                            squadreDaRimuovere.add(squadraCasaRitorno);
                        } 
                        if(goalSquadraVincenteAndata == p.getGoalSquadraOspiteRegolare()){
                            this.supplementari(p);
                        }
                    }
                }
            }
        }
        squadreNelTorneo.removeAll(squadreDaRimuovere);
    }
    public void ritorno(Squadra squadraCasaAndata, Squadra squadraCasaRitorno, int goalSquadraCasaAndata, int goalSquadraOspiteAndata){ // Caso in cui all'andata pareggiano
        for(Partita p: this.getPartite()){
            if(p.getStatoPartita().equals(StatoPartita.TERMINATA)){
                if(p.getSquadraCasa().equals(squadraCasaRitorno) && p.getSquadraOspite().equals(squadraCasaAndata)){ // Cerco la partita di ritorno
                    if(p.getGoalSquadraCasaRegolare() > p.getGoalSquadraOspiteRegolare()){ // Vince la squadra che era ospite all'andata 
                        squadreDaRimuovere.add(squadraCasaAndata); // Rimuovo la squadra che era in casa all'andata
                    }
                    if(p.getGoalSquadraCasaRegolare() < p.getGoalSquadraOspiteRegolare()){ // Vince  la squadra che era in casa all'andata 
                        squadreDaRimuovere.add(squadraCasaRitorno); // Rimuovo la squadra che è in casa al ritorno
                    }
                    if(p.getGoalSquadraCasaRegolare() == p.getGoalSquadraOspiteRegolare()){ // Pareggiano anche al ritorno
                        if(goalSquadraOspiteAndata > p.getGoalSquadraOspiteRegolare()){ // Controllo sui goal da ospite
                            squadreDaRimuovere.add(squadraCasaAndata);
                        }
                        if(goalSquadraOspiteAndata < p.getGoalSquadraOspiteRegolare()){ // Fa più goal da ospite la squadra che era in casa all'andata
                            squadreDaRimuovere.add(squadraCasaRitorno);
                        } 
                        if(goalSquadraOspiteAndata == p.getGoalSquadraOspiteRegolare()){
                            this.supplementari(p);
                        }
                    }
                }
            }
        }
        squadreNelTorneo.removeAll(squadreDaRimuovere);
    }
    public void supplementari(Partita p){
        if(p.getGoalSquadraCasaSupplementari() > p.getGoalSquadraOspiteSupplementari()){ // Ai supplementari fa più goal la squadra che è in casa al ritorno
            squadreDaRimuovere.add(p.getSquadraOspite());
        }
        if(p.getGoalSquadraCasaSupplementari()<p.getGoalSquadraOspiteSupplementari()){ // Ai supplementari fa più goal la squadra che è ospite al ritorno
            squadreDaRimuovere.add(p.getSquadraCasa());
        }
        if(p.getGoalSquadraCasaSupplementari()==p.getGoalSquadraOspiteSupplementari()){ // Pareggiano anche ai supplementari
            this.rigori(p);
        }

    }
    public void rigori(Partita p){
        if(p.getGoalSquadraCasaRigori() > p.getGoalSquadraOspiteRigori()){ // Ai rigori fa più goal la squadra che è in casa al ritorno
            squadreDaRimuovere.add(p.getSquadraOspite());
        }
        if(p.getGoalSquadraCasaRigori()<p.getGoalSquadraOspiteRigori()){ // Ai rigori fa più goal la squadra che è ospite al ritorno
            squadreDaRimuovere.add(p.getSquadraCasa());
        }
    }
    
    public void finale(Partita p){
        if(p.getGoalSquadraCasaRegolare() > p.getGoalSquadraOspiteRegolare()){ // Alla finale vince la squadra che è in casa
            squadreDaRimuovere.add(p.getSquadraOspite());
        }
        if(p.getGoalSquadraCasaRegolare() < p.getGoalSquadraOspiteRegolare()){ // Alla finale vince la squadra che è ospite
            squadreDaRimuovere.add(p.getSquadraCasa());
        }
        if(p.getGoalSquadraCasaRegolare() == p.getGoalSquadraOspiteRegolare()){
            this.supplementari(p);
        }
        squadreNelTorneo.removeAll(squadreDaRimuovere);
    }
    public String toString() {
        return "Torneo "+this.getNome()+" del tipo Eliminazione Diretta";
    }
    
}
