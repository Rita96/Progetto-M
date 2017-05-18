/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
}
