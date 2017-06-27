/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paolacarulli
 */
public class EliminazioneDiretta extends Torneo {
    private List<Squadra> squadreNelTorneo = new ArrayList<>();
    private List<Squadra> squadreDaRimuovere = new ArrayList<>();
    
    /**
     * Costruttore
     * @param nome nome torneo
     * @param anno anno torneo
     * @param p partite che compongono il torneo
     * @param putInDatabase 
     */
    public EliminazioneDiretta(String nome, int anno, List<Partita> p, boolean putInDatabase) {
        super(nome, anno, p, putInDatabase);
        if(putInDatabase){
            if(p instanceof PartitaEliminazioneDiretta){
            for(Partita pa : this.getPartite()){
                if(!(squadreNelTorneo.contains(pa.getSquadraCasa()))){
                    squadreNelTorneo.add(pa.getSquadraCasa());
//                    try {
//                        Test.q.makeTorneoEliminazionedirettaTable().putTorneoEliminazionediretta(pa.getSquadraCasa().getNome(), pa.getSquadraCasa().getCittaProvenienza(), 0, nome, anno);
//                    } catch (RemoteException ex) {
//                        Logger.getLogger(EliminazioneDiretta.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                }
                if(!(squadreNelTorneo.contains(pa.getSquadraOspite()))){
                    squadreNelTorneo.add(pa.getSquadraOspite());
//                    try {
//                        Test.q.makeTorneoEliminazionedirettaTable().putTorneoEliminazionediretta(pa.getSquadraOspite().getNome(), pa.getSquadraOspite().getCittaProvenienza(), 0, nome, anno);
//                    } catch (RemoteException ex) {
//                        Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                }
            }
            }
            for(Squadra s : squadreNelTorneo){
//                try {
//                    Test.q.makeTorneoEliminazionedirettaTable().updateFaseTorneoTorneoEliminazionediretta(s.getNome(), s.getCittaProvenienza(), 0, nome, anno, squadreNelTorneo.size());
//                } catch (RemoteException ex) {
//                    Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
            setEliminazioneDiretta();
        } else {
            List<String> squadre = new ArrayList<>();
//            try {
//                squadre = Test.q.makeTorneoEliminazionedirettaTable().getTorneoEliminazionediretta(nome, anno);
//            } catch (RemoteException ex) {
//                Logger.getLogger(EliminazioneDiretta.class.getName()).log(Level.SEVERE, null, ex);
//            }
            for(Partita pa : this.getPartite()){
                if((squadre.contains(pa.getSquadraCasa().getNome())) && !(squadreNelTorneo.contains(pa.getSquadraCasa()))){
                    squadreNelTorneo.add(pa.getSquadraCasa());
                } 
                if((squadre.contains(pa.getSquadraOspite().getNome())) && !(squadreNelTorneo.contains(pa.getSquadraOspite()))){
                    squadreNelTorneo.add(pa.getSquadraOspite());
                }
            }
        }
    }
    /**
     * 
     * @return squadre presenti nel torneo in una data fase
     */
    public List<Squadra> getSquadreNelTorneo(){
        return squadreNelTorneo;
    }
    /**
     * 
     */
    public void setEliminazioneDiretta(){
        for(Partita p: this.getPartite()){
            this.andata((PartitaEliminazioneDiretta)p);
        }
    }
    /**
     * metodo per controllare quale delle due squadre ha vinto la partita di andata.
     * In base al risultato di andata si chiama il metodo ritorno adeguato per controllare quale delle
     * due squadre ha passato il turno.
     * @param p partita
     */
    public void andata(PartitaEliminazioneDiretta p){
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
    /**
     * se all'andata una delle due squadre risulta vincitrice si chiama questo metodo ritorno.
     * se la partita finisce nei tempi regolamentari, la squadra che deve essere eliminata dal torneo viene eliminata.
     * se la partita finisce con un risultato tale per cui si debba andare ai supplementari, viene 
     * invocato il metodo supplementari.
     * @param squadraCasaAndata
     * @param squadraCasaRitorno
     * @param squadraVincitriceAndata
     * @param goalSquadraVincenteAndata
     * @param goalSquadraPerdenteAndata 
     */
    public void ritorno(Squadra squadraCasaAndata, Squadra squadraCasaRitorno, Squadra squadraVincitriceAndata, int goalSquadraVincenteAndata, int goalSquadraPerdenteAndata){ // Caso in cui non c'è pareggio all'andata
        
        for(Partita p: this.getPartite()){
            if(p.getSquadraCasa().equals(squadraCasaRitorno) && p.getSquadraOspite().equals(squadraCasaAndata)){ // Cerco la partita di ritorno
                if(p.getStatoPartita().equals(StatoPartita.TERMINATA)){
                    if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaRegolare() > ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare() && squadraVincitriceAndata.equals(p.getSquadraCasa())){ // Vince sempre la squadra che era ospite all'andata e in casa al ritorno
                        squadreDaRimuovere.add(squadraCasaAndata); // Rimuovo la squadra che era in casa all'andata
                    }
                    if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaRegolare() < ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare() && squadraVincitriceAndata.equals(p.getSquadraOspite())){ // Vince sempre la squadra che era in casa all'andata e ospite al ritorno
                        squadreDaRimuovere.add(squadraCasaRitorno); // Rimuovo la squadra che è in casa al ritorno
                    }
                    if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaRegolare() > ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare() && squadraVincitriceAndata.equals(p.getSquadraOspite())){ // Vince all'andata la squadra che era in casa all'andata e al ritorno quella che è in casa al ritorno
                        if(goalSquadraPerdenteAndata > ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){ // Faccio un controllo sui goal fatti quando erano rispettivamente ospiti
                            squadreDaRimuovere.add(squadraCasaAndata); // Dato che la squadra in casa al ritorno ha fatto più goal da ospite, elimino l'altra
                        }
                        if(goalSquadraPerdenteAndata < ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){ // Fa più goal da ospite la squadra che era in casa all'andata
                            squadreDaRimuovere.add(squadraCasaRitorno);
                        } 
                        if(goalSquadraPerdenteAndata == ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){
                            this.supplementari(p);
                        }
                    }
                    if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaRegolare() < ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare() && squadraVincitriceAndata.equals(p.getSquadraCasa())){ // All'andata vince la squadra che era ospite all'andata, al ritorno vince la squadra che era ospite al ritorno
                        if(goalSquadraVincenteAndata > ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){ // vince la squadra che era in casa al ritorno
                            squadreDaRimuovere.add(squadraCasaAndata);
                        }
                        if(goalSquadraVincenteAndata < ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){ // vince la squadra che era in casa all'andata
                            squadreDaRimuovere.add(squadraCasaRitorno);
                        } 
                        if(goalSquadraVincenteAndata == ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){
                            this.supplementari(p);
                        }
                    }
                }
            }
        }
        int fase = squadreNelTorneo.size();
        squadreNelTorneo.removeAll(squadreDaRimuovere);
        for(Squadra s : squadreNelTorneo){
//            try {
//                Test.q.makeTorneoEliminazionedirettaTable().updateFaseTorneoTorneoEliminazionediretta(s.getNome(), s.getCittaProvenienza(), fase, nome, anno, squadreNelTorneo.size());
//                Test.q.makeTorneoEliminazionedirettaTable().updatePassaggioFaseTorneoEliminazionediretta(s.getNome(), s.getCittaProvenienza(), squadreNelTorneo.size(), nome, anno, 1);
//            } catch (RemoteException ex) {
//                Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        
        for(Squadra s : squadreDaRimuovere){
//            try {
//                Test.q.makeTorneoEliminazionedirettaTable().updatePassaggioFaseTorneoEliminazionediretta(s.getNome(), s.getCittaProvenienza(), fase, nome, anno, 0);
//            } catch (RemoteException ex) {
//                Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        squadreDaRimuovere.clear();
    }
     /**
     * se all'andata le squadre pareggiano si richiama questo metodo.
     * se la partita finisce nei tempi regolamentari, la squadra che deve essere eliminata dal torneo viene eliminata.
     * se la partita finisce con un risultato tale per cui si debba andare ai supplementari, viene 
     * invocato il metodo supplementari.
     * @param squadraCasaAndata
     * @param squadraCasaRitorno
     * @param squadraVincitriceAndata
     * @param goalSquadraVincenteAndata
     * @param goalSquadraPerdenteAndata 
     */
    public void ritorno(Squadra squadraCasaAndata, Squadra squadraCasaRitorno, int goalSquadraCasaAndata, int goalSquadraOspiteAndata){ // Caso in cui all'andata pareggiano
        for(Partita p: this.getPartite()){
            if(p.getStatoPartita().equals(StatoPartita.TERMINATA)){
                if(p.getSquadraCasa().equals(squadraCasaRitorno) && p.getSquadraOspite().equals(squadraCasaAndata)){ // Cerco la partita di ritorno
                    if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaRegolare() > ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){ // Vince la squadra che era ospite all'andata 
                        squadreDaRimuovere.add(squadraCasaAndata); // Rimuovo la squadra che era in casa all'andata
                    }
                    if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaRegolare() < ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){ // Vince  la squadra che era in casa all'andata 
                        squadreDaRimuovere.add(squadraCasaRitorno); // Rimuovo la squadra che è in casa al ritorno
                    }
                    if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaRegolare() == ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){ // Pareggiano anche al ritorno
                        if(goalSquadraOspiteAndata > ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){ // Controllo sui goal da ospite
                            squadreDaRimuovere.add(squadraCasaAndata);
                        }
                        if(goalSquadraOspiteAndata < ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){ // Fa più goal da ospite la squadra che era in casa all'andata
                            squadreDaRimuovere.add(squadraCasaRitorno);
                        } 
                        if(goalSquadraOspiteAndata == ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){
                            this.supplementari(p);
                        }
                    }
                }
            }
        }
        int fase = squadreNelTorneo.size();
        squadreNelTorneo.removeAll(squadreDaRimuovere);
        for(Squadra s : squadreNelTorneo){
//            try {
//                Test.q.makeTorneoEliminazionedirettaTable().updateFaseTorneoTorneoEliminazionediretta(s.getNome(), s.getCittaProvenienza(), fase, nome, anno, squadreNelTorneo.size());
//                Test.q.makeTorneoEliminazionedirettaTable().updatePassaggioFaseTorneoEliminazionediretta(s.getNome(), s.getCittaProvenienza(), squadreNelTorneo.size(), nome, anno, 1);
//            } catch (RemoteException ex) {
//                Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        
        for(Squadra s : squadreDaRimuovere){
//            try {
//                Test.q.makeTorneoEliminazionedirettaTable().updatePassaggioFaseTorneoEliminazionediretta(s.getNome(), s.getCittaProvenienza(), fase, nome, anno, 0);
//            } catch (RemoteException ex) {
//                Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        squadreDaRimuovere.clear();
    }
     /**
     * se le squadre hanno pareggiato sia all'andata che al ritorno e hanno fatto lo stesso numero di goal da ospite si chiama questo metodo
     * @param Partita
     */
    public void supplementari(Partita p){
        if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaSupplementari() > ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteSupplementari()){ // Ai supplementari fa più goal la squadra che è in casa al ritorno
            squadreDaRimuovere.add(p.getSquadraOspite());
        }
        if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaSupplementari()<((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteSupplementari()){ // Ai supplementari fa più goal la squadra che è ospite al ritorno
            squadreDaRimuovere.add(p.getSquadraCasa());
        }
        if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaSupplementari()==((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteSupplementari()){ // Pareggiano anche ai supplementari
            this.rigori(p);
        }

    }
     /**
     * se le squadre hanno pareggiato sia all'andata che al ritorno e hanno fatto lo stesso numero di goal da ospite e hanno pareggiato nei supplementari si chiama questo metodo
     * @param Partita
     */
    public void rigori(Partita p){
        if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaRigori() > ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRigori()){ // Ai rigori fa più goal la squadra che è in casa al ritorno
            squadreDaRimuovere.add(p.getSquadraOspite());
        }
        if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaRigori()<((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRigori()){ // Ai rigori fa più goal la squadra che è ospite al ritorno
            squadreDaRimuovere.add(p.getSquadraCasa());
        }
    }
    /**
     * se le squadre rimaste sono solo 2, si chiama questo metodo
     * @param Partita
     */
    public void finale(Partita p){
        if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaRegolare() > ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){ // Alla finale vince la squadra che è in casa
            squadreDaRimuovere.add(p.getSquadraOspite());
        }
        if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaRegolare() < ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){ // Alla finale vince la squadra che è ospite
            squadreDaRimuovere.add(p.getSquadraCasa());
        }
        if(((PartitaEliminazioneDiretta)p).getGoalSquadraCasaRegolare() == ((PartitaEliminazioneDiretta)p).getGoalSquadraOspiteRegolare()){
            this.supplementari(p);
        }
        squadreNelTorneo.removeAll(squadreDaRimuovere);
    }
    public String toString() {
        return "Torneo "+this.getNome()+" del tipo Eliminazione Diretta";
    }
    
}
