/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import torneo.Giocatore;

/**
 *
 * @author nautilus
 */
public interface DatabaseInterfaceGiocatore extends Remote{
    
    void putGiocatore(int numero, String nomeSquadra, String cittaSquadra) throws RemoteException;
    void putGiocatore(int numero, String nomeGiocatore, String cognomeGiocatore, String nomeSquadra, String cittaSquadra) throws RemoteException;
    
    void updateNumeroGiocatore(int numero, String nomeSquadra, String cittaSquadra, int nuovoNumero) throws RemoteException;
    void updateNomeGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovoNome) throws RemoteException;
    void updateCognomeGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovoCognome) throws RemoteException;
    void updateNomeSquadraGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovoNomeSquadra) throws RemoteException;
    void updateCittaSquadraGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovaCittaSquadra) throws RemoteException;
    
    ArrayList<Giocatore> getGiocatore() throws RemoteException;
    
    void deleteGiocatore(int numero, String nomeSquadra, String cittaSquadra) throws RemoteException;
}
