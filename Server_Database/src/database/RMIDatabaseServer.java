/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author nautilus
 */
public class RMIDatabaseServer {
    
    public static void main(String[] args) {
       /*
        MarioNOTE:
        creating a connection to the database
        */
       DatabaseConnection connection = new DatabaseConnection("java", "password");
       
       try{
           int port = 1099;
           /*
           MarioNOTE:
           creating a Registry that allows the server to
           publish a service and client to retrieve the proxy
           */
           Registry registry = LocateRegistry.createRegistry(port);
           DatabaseManagement DBManage = new DatabaseManagement();
           registry.rebind("MyDatabase", DBManage);
           
           System.out.println("\nServer is ready...");
           
       }catch(RemoteException ex){
           System.out.println("\nRMIDatabaseServer ERROR: " + ex);
       }
    }
    
}
