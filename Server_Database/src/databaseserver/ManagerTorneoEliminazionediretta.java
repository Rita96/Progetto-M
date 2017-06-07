/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import databaseinterface.DatabaseInterfaceTorneo;
import databaseinterface.DatabaseInterfaceTorneoEliminazionediretta;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author nautilus
 */
public class ManagerTorneoEliminazionediretta extends UnicastRemoteObject implements DatabaseInterfaceTorneoEliminazionediretta{
    public ManagerTorneoEliminazionediretta() throws RemoteException{
        
    }
}
