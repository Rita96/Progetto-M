/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import exception.EccezioneLogIn;
import exception.GiocatoreInesistenteException;
import exception.IncorrectValueException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paolacarulli
 */
public class PartitaEliminazioneDiretta extends Partita{
    private int goalSquadraCasaRegolare = 0;
    private int goalSquadraOspiteRegolare = 0;
    private int goalSquadraCasaSupplementari = 0;
    private int goalSquadraOspiteSupplementari = 0;
    private int goalSquadraCasaRigori = 0;
    private int goalSquadraOspiteRigori = 0;

    public PartitaEliminazioneDiretta(int ID, Squadra squadra1, Squadra squadra2, Arbitro arbitro, String citta, StatoPartita stato, String nomeTorneo, int annoTorneo, boolean putInDatabase) {
        super(ID, squadra1, squadra2, arbitro, citta, stato, nomeTorneo, annoTorneo, putInDatabase);
    }
    public int getGoalSquadraCasaRegolare(){
        return goalSquadraCasaRegolare;
    }
    public int getGoalSquadraOspiteRegolare(){
        return goalSquadraOspiteRegolare;
    }
    public int getGoalSquadraCasaSupplementari(){
        return goalSquadraCasaSupplementari;
    }
    public int getGoalSquadraOspiteSupplementari(){
        return goalSquadraOspiteSupplementari;
    }
    public int getGoalSquadraCasaRigori(){
        return goalSquadraCasaRigori;
    }
    public int getGoalSquadraOspiteRigori(){
        return goalSquadraOspiteRigori;
    }
    public int getGoalSquadraCasa(){ 
        return this.goalSquadraCasaRegolare+goalSquadraCasaSupplementari+goalSquadraCasaRigori;
    }
    
    public int getGoalSquadraOspite() {
        return this.goalSquadraOspiteRegolare+goalSquadraOspiteSupplementari+goalSquadraOspiteRigori;
    }
    public void setGoal(int minuto, Giocatore giocatore){
        try{
            if(getArbitro().getAutenticazione().equals("AUTENTICATO")){
                if( !this.getStatoPartita().equals("PROGRAMMATA") ) { // INSERITO CONTROLLO IN MODO CHE NON SI POSSANO AGGUNGERE GOAL IN STATO PROGRAMMATA
                    if(getSquadraCasa().getGiocatori().contains(giocatore)){
                        getGoalSquadraCasaList().add(new Goal(minuto, giocatore));
                        try {
                            Test.q.makeGoalTable().putGoal(getID(), minuto, giocatore.getNumero(), getSquadraCasa().getNome(), getSquadraCasa().getCittaProvenienza(), getNomeTorneo(), getAnnoTorneo());
                        } catch (RemoteException ex) {
                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        switch (this.getStatoPartita()) {
                            case REGOLAMENTARE:
                                goalSquadraCasaRegolare++;
                            break;
                            case SUPPLEMENTARI:
                                goalSquadraCasaSupplementari++;
                            break;
                            case RIGORI:
                                goalSquadraCasaRigori++;
                            break;
                            case TERMINATA:
                                goalSquadraCasaRegolare++;
                            default:
                            break;
                        }
                    }
                    else if(getSquadraOspite().getGiocatori().contains(giocatore)){
                        getGoalSquadraOspiteList().add(new Goal(minuto, giocatore));
                        try {
                            Test.q.makeGoalTable().putGoal(getID(), minuto, giocatore.getNumero(), getSquadraOspite().getNome(), getSquadraOspite().getCittaProvenienza(), getNomeTorneo(), getAnnoTorneo());
                        } catch (RemoteException ex) {
                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        switch (this.getStatoPartita()) {
                            case REGOLAMENTARE:
                                goalSquadraOspiteRegolare++;
                            break;
                            case SUPPLEMENTARI:
                                goalSquadraOspiteSupplementari++;
                            break;
                            case RIGORI:
                                goalSquadraOspiteRigori++;
                            break;
                            case TERMINATA:
                                goalSquadraCasaRegolare++;
                            default:
                            break;
                        }
                    }
                    else{
                        throw new GiocatoreInesistenteException("Il giocatore " + giocatore.getNome() + " " + giocatore.getCognome() + " non gioca in nessuna di queste squadre!");
                    }
                }
            }
            else{
                if(getArbitro().getAutenticazione().equals("NONAUTENTICATO")){
                    System.out.println("Impossibile settare lo stato perchè l'arbitro non è autenticato");
                }
                else{
                    throw new EccezioneLogIn("LogIn non effettuato");
                }
            }
        }
        catch(EccezioneLogIn ex){
            ex.getMessage();
        }
    }
    
    public void setGoal(int minuto, String Nome, String Cognome, int numero){
        try{
            boolean gioca = false;
            if(getArbitro().getAutenticazione().equals("AUTENTICATO")){
                for(Giocatore g : getSquadraCasa().getGiocatori()){
                    if(g.getNome().equals(Nome) && g.getCognome().equals(Cognome) && g.getNumero() == numero){
                        getGoalSquadraCasaList().add(new Goal(minuto, g));
                        try {
                            Test.q.makeGoalTable().putGoal(getID(), minuto, g.getNumero(), getSquadraCasa().getNome(), getSquadraCasa().getCittaProvenienza(), getNomeTorneo(), getAnnoTorneo());
                        } catch (RemoteException ex) {
                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        switch (this.getStatoPartita()) {
                            case REGOLAMENTARE:
                                goalSquadraCasaRegolare++;
                            break;
                            case SUPPLEMENTARI:
                                goalSquadraCasaSupplementari++;
                            break;
                            case RIGORI:
                                goalSquadraCasaRigori++;
                            break;
                            case TERMINATA:
                                goalSquadraCasaRegolare++;
                            default:
                            break;
                        }
                        try {
                            Test.q.makePartitaTable().updateGoalCasaPartita(getID(), getNomeTorneo(), getAnnoTorneo(), goalSquadraCasaRegolare+goalSquadraCasaSupplementari+goalSquadraCasaRigori);
                        } catch (RemoteException ex) {
                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        if(!gioca)
                            gioca = true;
                        else
                            throw new IncorrectValueException("Errore, due giocatori con lo stesso nome, cognome e numero");
                    } 
                }
                for(Giocatore g : getSquadraOspite().getGiocatori()){
                    if(g.getNome().equals(Nome) && g.getCognome().equals(Cognome) && g.getNumero() == numero){
                        getGoalSquadraOspiteList().add(new Goal(minuto, g));
                        try {
                            Test.q.makeGoalTable().putGoal(getID(), minuto, g.getNumero(), getSquadraOspite().getNome(), getSquadraOspite().getCittaProvenienza(), getNomeTorneo(), getAnnoTorneo());
                        } catch (RemoteException ex) {
                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        switch (this.getStatoPartita()) {
                            case REGOLAMENTARE:
                                goalSquadraOspiteRegolare++;
                            break;
                            case SUPPLEMENTARI:
                                goalSquadraOspiteSupplementari++;
                            break;
                            case RIGORI:
                                goalSquadraOspiteRigori++;
                            break;
                            case TERMINATA:
                                goalSquadraOspiteRegolare++;
                            default:
                            break;}
                        try {
                            Test.q.makePartitaTable().updateGoalOspitePartita(getID(), getNomeTorneo(), getAnnoTorneo(), goalSquadraOspiteRegolare+goalSquadraOspiteSupplementari+goalSquadraOspiteRigori);
                        } catch (RemoteException ex) {
                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                        if(!gioca)
                            gioca = true;
                        else
                            throw new IncorrectValueException("Errore, due giocatori con lo stesso nome, cognome e numero");
                    } 
                }
                
                if(!gioca)
                    throw new GiocatoreInesistenteException("Il giocatore " + Nome + " " + Cognome + " non gioca in nessuna di queste squadre!");
            } else{
                if(getArbitro().getAutenticazione().equals("NONAUTENTICATO")){
                    System.out.println("Impossibile settare lo stato perchè l'arbitro non è autenticato");
                }
                else{
                    throw new EccezioneLogIn("LogIn non effettuato");
                }
            }
        }
        catch(EccezioneLogIn ex){
            ex.getMessage();
        }
    }
    @Override
    public String toString() {
        int sommaCasa = goalSquadraCasaRegolare+goalSquadraCasaSupplementari+goalSquadraCasaRigori;
        int sommaOspite = goalSquadraOspiteRegolare+goalSquadraOspiteSupplementari+goalSquadraOspiteRigori;
        return getSquadraCasa()+" ( goal = "+sommaCasa+" ) VS "+getSquadraOspite()+" ( goal = "+sommaOspite+" ) - in stato "+getStatoPartita()+" arbitro "+getArbitro().toString()+" in "+getCittaDoveSiSvolge()+"\n\n";
        
    }
}