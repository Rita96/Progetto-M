/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import exception.EccezioneLogIn;
import exception.GiocatoreInesistenteException;
import exception.IncorrectValueException;
import exception.SquadraIncorrettaException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paolacarulli
 */
public class PartitaItaliana extends Partita{
    private int goalSquadraCasa = 0;
    private int goalSquadraOspite = 0;
    private int punteggioSquadraCasa;
    private int punteggioSquadraOspite;
    
    /**
     * Costruttore
     * @param ID
     * @param squadra1
     * @param squadra2
     * @param arbitro
     * @param citta
     * @param stato
     * @param nomeTorneo
     * @param annoTorneo
     * @param putInDatabase 
     */
    public PartitaItaliana(int ID, Squadra squadra1, Squadra squadra2, Arbitro arbitro, String citta, StatoPartita stato, String nomeTorneo, int annoTorneo, boolean putInDatabase) {
        super(ID, squadra1, squadra2, arbitro, citta, stato, nomeTorneo, annoTorneo, putInDatabase);
    }
    
    /**
     * 
     * @return goal della squadra di casa
     */
    public int getGoalSquadraCasa(){
        return goalSquadraCasa;
    }
    
    /**
     * 
     * @return goal della squadra ospite
     */
    public int getGoalSquadraOspite(){
        return goalSquadraOspite;
    }
    
    /**
     * imposta i punti di una determinata squadra
     * @param punti
     * @param nameSquadra 
     */
    public void setPuntiSquadra(int punti, String nameSquadra){
        if(this.getArbitro().getAutenticazione().equals(Autenticazione.AUTENTICATO)){
            if(nameSquadra.equals(this.getSquadraCasa().getNome()) && punti >= 0){
                this.punteggioSquadraCasa = punti;
            }
            else if(nameSquadra.equals(this.getSquadraOspite().getNome()) && punti >= 0){
                this.punteggioSquadraOspite = punti;
            }
            else{
                if(!(nameSquadra.equals(this.getSquadraCasa().getNome()) || nameSquadra.equals(this.getSquadraOspite().getNome()))){
                    throw new SquadraIncorrettaException("Nome della squadra scorretto");
                }
                if(punti < 0){
                    throw new IncorrectValueException("Valore scorretto, il valore del punteggio dovrebbe essere positivo");
                }
            }
        }
        else{
            if(this.getArbitro().getAutenticazione().equals(Autenticazione.NONAUTENTICATO)){
                System.out.println("Impossibile aggiungere i punti perchè l'arbitro non è autenticato");
            }
            else{
                System.out.println("L'arbitro deve prima fare il LogIn");
            }
        }
    }
    
    /**
     * imposta un goal segnato da un gioicatore
     * @param minuto
     * @param giocatore 
     */
    public void setGoal(int minuto, Giocatore giocatore){
        try{
            if(getArbitro().getAutenticazione().equals(Autenticazione.AUTENTICATO)){
                if( !this.getStatoPartita().equals(StatoPartita.PROGRAMMATA) ) { // INSERITO CONTROLLO IN MODO CHE NON SI POSSANO AGGUNGERE GOAL IN STATO PROGRAMMATA
                    if(getSquadraCasa().getGiocatori().contains(giocatore)){
                        getGoalSquadraCasaList().add(new Goal(minuto, giocatore));
//                        try {
//                            Test.q.makeGoalTable().putGoal(getID(), minuto, giocatore.getNumero(), getSquadraCasa().getNome(), getSquadraCasa().getCittaProvenienza(), getNomeTorneo(), getAnnoTorneo());
//                        } catch (RemoteException ex) {
//                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                        switch (this.getStatoPartita()) {
                            case REGOLAMENTARE:
                                goalSquadraCasa++;
                            break;
                            case SUPPLEMENTARI:
                                goalSquadraCasa++;
                            break;
                            case RIGORI:
                                goalSquadraCasa++;
                            break;
                            case TERMINATA:
                                goalSquadraCasa++;
                            default:
                            break;
                        }
                    }
                    else if(getSquadraOspite().getGiocatori().contains(giocatore)){
                        getGoalSquadraOspiteList().add(new Goal(minuto, giocatore));
//                        try {
//                            Test.q.makeGoalTable().putGoal(getID(), minuto, giocatore.getNumero(), getSquadraOspite().getNome(), getSquadraOspite().getCittaProvenienza(), getNomeTorneo(), getAnnoTorneo());
//                        } catch (RemoteException ex) {
//                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                        switch (this.getStatoPartita()) {
                            case REGOLAMENTARE:
                                goalSquadraOspite++;
                            break;
                            case SUPPLEMENTARI:
                                goalSquadraCasa++;
                            break;
                            case RIGORI:
                                goalSquadraCasa++;
                            break;
                            case TERMINATA:
                                goalSquadraCasa++;
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
                if(getArbitro().getAutenticazione().equals(Autenticazione.NONAUTENTICATO)){
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
    
    /**
     *  imposta un goal segnato da un giocatore
     * @param minuto
     * @param Nome
     * @param Cognome
     * @param numero 
     */
    public void setGoal(int minuto, String Nome, String Cognome, int numero){
        try{
            boolean gioca = false;
            if(getArbitro().getAutenticazione().equals(Autenticazione.AUTENTICATO)){
                for(Giocatore g : getSquadraCasa().getGiocatori()){
                    if(g.getNome().equals(Nome) && g.getCognome().equals(Cognome) && g.getNumero() == numero){
                        getGoalSquadraCasaList().add(new Goal(minuto, g));
//                        try {
//                            Test.q.makeGoalTable().putGoal(getID(), minuto, g.getNumero(), getSquadraCasa().getNome(), getSquadraCasa().getCittaProvenienza(), getNomeTorneo(), getAnnoTorneo());
//                        } catch (RemoteException ex) {
//                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                        switch (this.getStatoPartita()) {
                            case REGOLAMENTARE:
                                goalSquadraCasa++;
                            break;
                            case TERMINATA:
                                goalSquadraCasa++;
                            default:
                            break;
                        }
//                        try {
//                            Test.q.makePartitaTable().updateGoalCasaPartita(getID(), getNomeTorneo(), getAnnoTorneo(), goalSquadraCasa);
//                        } catch (RemoteException ex) {
//                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                        
                        if(!gioca)
                            gioca = true;
                        else
                            throw new IncorrectValueException("Errore, due giocatori con lo stesso nome, cognome e numero");
                    } 
                }
                for(Giocatore g : getSquadraOspite().getGiocatori()){
                    if(g.getNome().equals(Nome) && g.getCognome().equals(Cognome) && g.getNumero() == numero){
                        getGoalSquadraOspiteList().add(new Goal(minuto, g));
//                        try {
//                            Test.q.makeGoalTable().putGoal(getID(), minuto, g.getNumero(), getSquadraOspite().getNome(), getSquadraOspite().getCittaProvenienza(), getNomeTorneo(), getAnnoTorneo());
//                        } catch (RemoteException ex) {
//                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                        switch (this.getStatoPartita()) {
                            case REGOLAMENTARE:
                                goalSquadraOspite++;
                            break;
                            case TERMINATA:
                                goalSquadraOspite++;
                            default:
                            break;}
//                        try {
//                            Test.q.makePartitaTable().updateGoalOspitePartita(getID(), getNomeTorneo(), getAnnoTorneo(), goalSquadraOspite);
//                        } catch (RemoteException ex) {
//                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                    
                        if(!gioca)
                            gioca = true;
                        else
                            throw new IncorrectValueException("Errore, due giocatori con lo stesso nome, cognome e numero");
                    } 
                }
                
                if(!gioca)
                    throw new GiocatoreInesistenteException("Il giocatore " + Nome + " " + Cognome + " non gioca in nessuna di queste squadre!");
            } else{
                if(getArbitro().getAutenticazione().equals(Autenticazione.NONAUTENTICATO)){
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
    
    /**
     * 
     * @return 
     */
    public String toString(){
        return getSquadraCasa()+" ( goal = "+goalSquadraCasa+" ) VS "+getSquadraOspite()+" ( goal = "+goalSquadraOspite+" ) - in stato "+getStatoPartita()+" arbitro "+getArbitro().toString()+" in "+getCittaDoveSiSvolge()+" (PUNTI SQ CASA "+punteggioSquadraCasa+" - PUNTI SQ OSPITE "+punteggioSquadraOspite+")\n\n";
    }

}
