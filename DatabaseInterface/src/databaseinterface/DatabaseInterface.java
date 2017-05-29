/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.*;

/**
 *
 * @author nautilus
 */
public interface DatabaseInterface extends Remote{
    /*
    MarioNOTE:
    here will be located all the methods that can be run by the remote client,
    furthermore the methods are defined in DatabaseManagement.
    
    This class needs to be imported in the client library
    */
    String getQuery() throws RemoteException; // method used as sample, will not be really used
    
    Map<Integer,List<String>> getGiocatore(String nomeSquadra, String cittaSquadra) throws RemoteException;
    
    ArrayList<String> getSquadra(String nomeTorneo, int annoTorneo) throws RemoteException;
    
    Map<String, String> getPartita(String nomeTorneo, int annoTorneo) throws RemoteException;
    Map<String, String> getPartita(int idPartita, String nomeTorneo, int annoTorneo) throws RemoteException;

    //ArrayList<Integer> getGoal(int idPartita, String nomeTorneo, int annoTorneo) throws RemoteException;
    
    //ArrayList<Integer> getCartellini(int idPartita, String nomeTorneo, int annoTorneo) throws RemoteException;
    
    void putToreo(String nome, int annoTorneo) throws RemoteException;
    
    void putLogin(String username, String password) throws RemoteException;
    
    void putGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException;
    
    void putCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException;
    
    void putArbitro(String codiceFiscale) throws RemoteException;
    void putArbitro(String codiceFiscale, String nome, String cognome, String username, String password ) throws RemoteException;
    
    void putOrganizzatore(int idOrganizzatore) throws RemoteException;
    void putOrganizzatore(int idOrganizzatore, String username, String password) throws RemoteException;
    
    void putPartita(int idPartita, String nomeTorneo, int annoTorneo) throws RemoteException;
    void putPartita(int idPartita, String squadraCasa, String squadraOspite, Date data, String andataritorno, String cfArbitro, String nomeTorneo, int annoTorneo) throws RemoteException;
    void putPartita(int idPartita, String squadraCasa, String squadraOspite, Date data, String cfArbitro, String nomeTorneo, int annoTorneo) throws RemoteException;
    
    void putGiocatore(int numero, String nomeSquadra, String cittaSquadra) throws RemoteException;
    void putGiocatore(int numero, String nomeGiocatore, String cognomeGiocatore, String nomeSquadra, String cittaSquadra) throws RemoteException;
    
    void putSquadra(String nomeSquadra, String cittaSquadra) throws RemoteException;
    void putSquadra(String nomeSquadra, String cittaSquadra, String coloreSquadra) throws RemoteException;
    
    void putSquadraCasa(String nomeSquadra, String cittaSquadra) throws RemoteException;
    
    void putSquadraOspite(String nomeSquadra, String cittaSquadra) throws RemoteException;
    
    void putTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo) throws RemoteException;
    
    //void putTorneoEliminazioneDiretta() throws RemoteException;
    //void putTorneoEliminazioneDiretta() throws RemoteException;
}
