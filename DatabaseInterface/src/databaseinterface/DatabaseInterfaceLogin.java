/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author nautilus
 */
public interface DatabaseInterfaceLogin extends Remote{
    
    void putLogin(String username, String password) throws RemoteException;
    
    void updateUsernameLogin(String username, String password, String nuovoUsername) throws RemoteException;
    void updatePasswordLogin(String username, String password, String nuovaPassword) throws RemoteException;
    
    String getPasswordLogin(String username) throws RemoteException;

    void deleteLogin(String username, String password) throws RemoteException;
}
