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
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author paolacarulli
 */
public class Partita { // Manca il metodo che faccia settare all'arbitro i goal realtime
    
    private String nomeTorneo;
    private int annoTorneo;
    private int ID;
    private Squadra squadraCasa;
    private Squadra squadraOspite;
    private StatoPartita stato;
    private Arbitro arbitro;
    private int punteggioSquadraCasa;
    private int punteggioSquadraOspite;
    private List<Cartellino> cartelliniSquadraCasa = new ArrayList<>();
    private List<Cartellino> cartelliniSquadraOspite = new ArrayList<>();
    private List<Goal> goalSquadraCasaList = new ArrayList<>(); // Manca da implementare il metodo in cui l'arbitro setta i goal fatti realtime dopo aver fatto login
    private List<Goal> goalSquadraOspiteList = new ArrayList<>();
    private int goalSquadraCasaRegolare = 0;
    private int goalSquadraOspiteRegolare = 0;
    private int goalSquadraCasaSupplementari = 0;
    private int goalSquadraOspiteSupplementari = 0;
    private int goalSquadraCasaRigori = 0;
    private int goalSquadraOspiteRigori = 0;
    private String cittaDoveSiSvolge;
    private String tipo = null;
    
    public Partita(int ID, Squadra squadra1, Squadra squadra2, Arbitro arbitro, String citta, StatoPartita stato, String nomeTorneo, int annoTorneo, boolean putInDatabase){
        this.nomeTorneo = nomeTorneo;
        this.annoTorneo = annoTorneo;
        this.ID = ID;
        this.squadraCasa = squadra1;
        this.squadraOspite = squadra2;
        this.arbitro = arbitro;
        this.cittaDoveSiSvolge = citta;
        this.stato = stato;
        if(putInDatabase){
            try {
                Test.q.makePartitaTable().putPartita(ID, squadraCasa.getNome(), squadraOspite.getNome(), stato.toString(), arbitro.getCodiceFiscale(), nomeTorneo, annoTorneo, citta);
            } catch (RemoteException ex) {
                Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //metodo per generare un id intero casuale della partita
    }
    public String ModificaTipo(String stringa) {
        return tipo = stringa;
    }
    public int getID() {
        return ID;
    }
    public String getTipo() {
        return tipo;
    }
    public StatoPartita getStatoPartita(){
        return stato;
    }
    public StatoPartita ModificaStatoPartita(StatoPartita statopartita) {
        return stato = statopartita;
    }
    public StatoPartita getStato(String s) {
        return StatoPartita.valueOf(s);
    }
    public List<Goal> getGoalSquadraCasaList(){
        return goalSquadraCasaList;
    }
    public List<Goal> getGoalSquadraOspiteList(){
        return goalSquadraOspiteList;
    }
    public Squadra getSquadraCasa(){
        return squadraCasa;
    }
    public Squadra getSquadraOspite(){
        return squadraOspite;
    }
    public String getCitta(){
        return cittaDoveSiSvolge;
    }
    public Arbitro getArbitro(){
        return arbitro;
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
    public List<Cartellino> getCartelliniSquadraCasa() {
        return cartelliniSquadraCasa;
    }
    public List<Cartellino> getCartelliniSquadraOspite() {
        return cartelliniSquadraOspite;
    }
    
    public String ModificaCitta(String citta) {
        try {
            Test.q.makePartitaTable().updateCittaPartita(ID, nomeTorneo, annoTorneo, citta);
        } catch (RemoteException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cittaDoveSiSvolge = citta;
    }
    
    public int getGoalSquadraCasa(){ 
        return goalSquadraCasaRegolare+goalSquadraCasaSupplementari+goalSquadraCasaRigori;
    }
    
    public int getGoalSquadraOspite() {
        return goalSquadraOspiteRegolare+goalSquadraOspiteSupplementari+goalSquadraOspiteRigori;
    }
    
    public void setPuntiSquadra(int punti, String nameSquadra){
        if(arbitro.getAutenticazione().equals("AUTENTICATO")){
            if(nameSquadra.equals(squadraCasa.getNome()) && punti >= 0){
                this.punteggioSquadraCasa = punti;
            }
            else if(nameSquadra.equals(squadraOspite.getNome()) && punti >= 0){
                this.punteggioSquadraOspite = punti;
            }
            else{
                if(!(nameSquadra.equals(squadraCasa.getNome()) || nameSquadra.equals(squadraOspite.getNome()))){
                    throw new SquadraIncorrettaException("Nome della squadra scorretto");
                }
                if(punti < 0){
                    throw new IncorrectValueException("Valore scorretto, il valore del punteggio dovrebbe essere positivo");
                }
            }
        }
        else{
            if(arbitro.getAutenticazione().equals("NONAUTENTICATO")){
                System.out.println("Impossibile aggiungere i punti perchè l'arbitro non è autenticato");
            }
            else{
                System.out.println("L'arbitro deve prima fare il LogIn");
            }
        }
    }
    public void setCartellino(ColoreCartellino colore, Giocatore giocatore, int minuto){
        try{
            if(arbitro.getAutenticazione().equals("AUTENTICATO")){
                if(squadraCasa.getGiocatori().contains(giocatore)){
                    cartelliniSquadraCasa.add(new Cartellino(colore, giocatore, minuto));
                    try {
                        Test.q.makeCartellinoTable().putCartellino(ID, colore.toString(), minuto, giocatore.getNumero(), squadraCasa.getNome(), squadraCasa.getCittaProvenienza(), nomeTorneo, annoTorneo);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Cartellino.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if(squadraOspite.getGiocatori().contains(giocatore)){
                    cartelliniSquadraOspite.add(new Cartellino(colore, giocatore, minuto));
                    try {
                        Test.q.makeCartellinoTable().putCartellino(ID, colore.toString(), minuto, giocatore.getNumero(), squadraOspite.getNome(), squadraOspite.getCittaProvenienza(), nomeTorneo, annoTorneo);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Cartellino.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    throw new GiocatoreInesistenteException("Il giocatore " + giocatore.getNome() + " " + giocatore.getCognome() + " non gioca in nessuna di queste squadre!");
                }
            }
            else{
                if(arbitro.getAutenticazione().equals("NONAUTENTICATO")){
                    System.out.println("Impossibile aggiungere il cartellino perchè l'arbitro non è autenticato");
                }
                else{
                    throw new EccezioneLogIn("LogIn non effettuato");
                }
            }
        }
        catch(EccezioneLogIn e){
            e.getMessage();
        }
    }
    public void setStatoPartita(StatoPartita s){
        try{
            if(arbitro.getAutenticazione().equals("AUTENTICATO")){
                stato = s;
                try {
                        Test.q.makePartitaTable().updateStatoPartitaPartita(ID, nomeTorneo, annoTorneo, s.toString());
                    } catch (RemoteException ex) {
                        Logger.getLogger(Cartellino.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            else{
                if(arbitro.getAutenticazione().equals("NONAUTENTICATO")){
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
    
    public void setGoal(int minuto, Giocatore giocatore){
        try{
            if(arbitro.getAutenticazione().equals("AUTENTICATO")){
                if( !this.getStatoPartita().equals("PROGRAMMATA") ) { // INSERITO CONTROLLO IN MODO CHE NON SI POSSANO AGGUNGERE GOAL IN STATO PROGRAMMATA
                    if(squadraCasa.getGiocatori().contains(giocatore)){
                        goalSquadraCasaList.add(new Goal(minuto, giocatore));
                        try {
                            Test.q.makeGoalTable().putGoal(ID, minuto, giocatore.getNumero(), squadraCasa.getNome(), squadraCasa.getCittaProvenienza(), nomeTorneo, annoTorneo);
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
                    else if(squadraOspite.getGiocatori().contains(giocatore)){
                        goalSquadraOspiteList.add(new Goal(minuto, giocatore));
                        try {
                            Test.q.makeGoalTable().putGoal(ID, minuto, giocatore.getNumero(), squadraOspite.getNome(), squadraOspite.getCittaProvenienza(), nomeTorneo, annoTorneo);
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
                if(arbitro.getAutenticazione().equals("NONAUTENTICATO")){
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
            if(arbitro.getAutenticazione().equals("AUTENTICATO")){
                for(Giocatore g : squadraCasa.getGiocatori()){
                    if(g.getNome().equals(Nome) && g.getCognome().equals(Cognome) && g.getNumero() == numero){
                        goalSquadraCasaList.add(new Goal(minuto, g));
                        try {
                            Test.q.makeGoalTable().putGoal(ID, minuto, g.getNumero(), squadraCasa.getNome(), squadraCasa.getCittaProvenienza(), nomeTorneo, annoTorneo);
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
                            Test.q.makePartitaTable().updateGoalCasaPartita(ID, nomeTorneo, annoTorneo, goalSquadraCasaRegolare+goalSquadraCasaSupplementari+goalSquadraCasaRigori);
                        } catch (RemoteException ex) {
                            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        if(!gioca)
                            gioca = true;
                        else
                            throw new IncorrectValueException("Errore, due giocatori con lo stesso nome, cognome e numero");
                    } 
                }
                for(Giocatore g : squadraOspite.getGiocatori()){
                    if(g.getNome().equals(Nome) && g.getCognome().equals(Cognome) && g.getNumero() == numero){
                        goalSquadraOspiteList.add(new Goal(minuto, g));
                        try {
                            Test.q.makeGoalTable().putGoal(ID, minuto, g.getNumero(), squadraOspite.getNome(), squadraOspite.getCittaProvenienza(), nomeTorneo, annoTorneo);
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
                            Test.q.makePartitaTable().updateGoalOspitePartita(ID, nomeTorneo, annoTorneo, goalSquadraOspiteRegolare+goalSquadraOspiteSupplementari+goalSquadraOspiteRigori);
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
                if(arbitro.getAutenticazione().equals("NONAUTENTICATO")){
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
    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
        try {
            Test.q.makePartitaTable().updateCfArbitroPartita(ID, nomeTorneo, annoTorneo, arbitro.getCodiceFiscale());
        } catch (RemoteException ex) {
            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public String toString() {
        int sommaCasa = goalSquadraCasaRegolare+goalSquadraCasaSupplementari+goalSquadraCasaRigori;
        int sommaOspite = goalSquadraOspiteRegolare+goalSquadraOspiteSupplementari+goalSquadraOspiteRigori;
        if( tipo.equals("italiana") ) {
            return squadraCasa+" ( goal = "+sommaCasa+" ) VS "+squadraOspite+" ( goal = "+sommaOspite+" ) - in stato "+stato+" arbitro "+arbitro.toString()+" in "+cittaDoveSiSvolge+" (PUNTI SQ CASA "+punteggioSquadraCasa+" - PUNTI SQ OSPITE "+punteggioSquadraOspite+")\n\n";
        }
        else if( tipo.equals("eliminazione diretta") ) {
            return squadraCasa+" ( goal = "+sommaCasa+" ) VS "+squadraOspite+" ( goal = "+sommaOspite+" ) - in stato "+stato+" arbitro "+arbitro.toString()+" in "+cittaDoveSiSvolge+"\n\n";
        }
        return null;
    }
}