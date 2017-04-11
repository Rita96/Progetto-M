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
import java.util.*;

/**
 *
 * @author paolacarulli
 */
public class Partita {
    private Squadra squadra1;
    private Squadra squadra2;
    private StatoPartita stato;
    private Arbitro arbitro;
    private int punteggio1;
    private int punteggio2;
    private List<Cartellino> cartelliniSquadra1 = new ArrayList<>();
    private List<Cartellino> cartelliniSquadra2 = new ArrayList<>();
    private int goalSquadra1;
    private int goalSquadra2;
    private String cittaDoveSiSvolge;
    private boolean casaSquadra1 = false;
    
    public Partita(Squadra squadra1, Squadra squadra2, Arbitro arbitro, String citta){
        this.squadra1 = squadra1;
        this.squadra2 = squadra2;
        this.arbitro = arbitro;
        this.cittaDoveSiSvolge = citta;
    }
    public boolean getDoveGioca(){
        if(cittaDoveSiSvolge.equals(squadra1.getCittaProvenienza())){
            casaSquadra1 = true;
        }
        else{
            if(cittaDoveSiSvolge.equals(squadra2.getCittaProvenienza())){
                casaSquadra1 = false;
            }
            else{
                System.out.println("Citta inserita sbagliata");
            }
        }
        return casaSquadra1;
    }
    public Squadra getSquadra1(){
        return squadra1;
    }
    public Squadra getSquadra2(){
        return squadra2;
    }
    public String getCitta(){
        return cittaDoveSiSvolge;
    }
    public Arbitro getArbitro(){
        return arbitro;
    }
    public int getGoalSquadra1(){
        return goalSquadra1;
    }
    public int getGoalSquadra2(){
        return goalSquadra2;
    }
    public void setPuntiSquadra(int punti, String nameSquadra){
        if(arbitro.getAutenticazione().equals("AUTENTICATO")){
            if(nameSquadra.equals(squadra1.getNome()) && punti >= 0){
                this.punteggio1 = punti;
            }
            if(nameSquadra.equals(squadra2.getNome()) && punti >= 0){
                this.punteggio2 = punti;
            }
            else{
                if(!(nameSquadra.equals(squadra1.getNome()) || nameSquadra.equals(squadra2.getNome()))){
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
    public void setCartellino(ColoreCartellino colore, Giocatore giocatore){
        try{
            if(arbitro.getAutenticazione().equals("AUTENTICATO")){
                if(squadra1.getGiocatori().contains(giocatore)){
                    cartelliniSquadra1.add(new Cartellino(colore, giocatore));
                }
                if(squadra2.getGiocatori().contains(giocatore)){
                    cartelliniSquadra2.add(new Cartellino(colore, giocatore));
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
}
