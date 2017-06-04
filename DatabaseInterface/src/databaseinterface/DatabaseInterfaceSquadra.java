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
import torneo.Squadra;

/**
 *
 * @author nautilus
 */
public interface DatabaseInterfaceSquadra extends Remote{
    
    void putSquadra(String nomeSquadra, String cittaSquadra) throws RemoteException;
    void putSquadra(String nomeSquadra, String cittaSquadra, String coloreSquadra) throws RemoteException;
    
    void updateNomeSquadra(String nomeSquadra, String cittaSquadra, String nuovoNome) throws RemoteException;
    void updateCittaSquadra(String nomeSquadra, String cittaSquadra, String nuovaCitta) throws RemoteException;
    void updateColoreSquadra(String nomeSquadra, String cittaSquadra, String nuovoColore) throws RemoteException;
    
    ArrayList<Giocatore> getGiocatoreSquadra(String nomeSquadra, String cittaSquadra) throws RemoteException;
    ArrayList<Squadra> getSquadra() throws RemoteException;
    
    void deleteSquadra(String nomeSquadra, String cittaSquadra) throws RemoteException;
}
