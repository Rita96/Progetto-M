/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import torneo.Arbitro;

/**
 *
 * @author nautilus
 */
public interface DatabaseInterfaceArbitro extends Remote{
    
    void putArbitro(String codiceFiscale, String nomeArbitro, String cognomeArbitro, String password) throws RemoteException;
    
    void updateCfArbitro(String codiceFiscale, String nuovoCf) throws RemoteException;
    void updateNomeArbitro(String codiceFiscale, String nuovoNome) throws RemoteException;
    void updateCognomeArbitro(String codiceFiscale, String nuovoCognome) throws RemoteException;
    void updateUsernameArbitro(String codiceFiscale, String nuovoUsername) throws RemoteException;
    void updatePasswordArbitro(String codiceFiscale, String nuovaPassword) throws RemoteException;
    
    ArrayList<Arbitro> getArbitro() throws RemoteException;
    
    void deleteArbitro(Arbitro arbitro) throws RemoteException;
}
