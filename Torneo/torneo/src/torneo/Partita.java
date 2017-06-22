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
public abstract class Partita { // Manca il metodo che faccia settare all'arbitro i goal realtime
    
    private String nomeTorneo;
    private int annoTorneo;
    private int ID;
    private Squadra squadraCasa;
    private Squadra squadraOspite;
    private StatoPartita stato;
    private Arbitro arbitro;
    private List<Cartellino> cartelliniSquadraCasa = new ArrayList<>();
    private List<Cartellino> cartelliniSquadraOspite = new ArrayList<>();
    private List<Goal> goalSquadraCasaList = new ArrayList<>(); // Manca da implementare il metodo in cui l'arbitro setta i goal fatti realtime dopo aver fatto login
    private List<Goal> goalSquadraOspiteList = new ArrayList<>();
    private String cittaDoveSiSvolge;
    private String tipo = null;
    
    /**
     * 
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
    public String getCittaDoveSiSvolge(){
        return cittaDoveSiSvolge;
    }
    public String getNomeTorneo(){
        return nomeTorneo;
    }
    public int getAnnoTorneo(){
        return annoTorneo;
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
    /**
     * 
     * @param s 
     */
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
                        
                    
    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
        try {
            Test.q.makePartitaTable().updateCfArbitroPartita(ID, nomeTorneo, annoTorneo, arbitro.getCodiceFiscale());
        } catch (RemoteException ex) {
            Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public abstract void setGoal(int minuto, Giocatore giocatore);
    public abstract void setGoal(int minuto, String Nome, String Cognome, int numero);
    
    @Override
    public abstract String toString();

        }
