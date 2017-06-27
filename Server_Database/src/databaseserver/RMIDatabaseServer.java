/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author nautilus
 */
public class RMIDatabaseServer {
    
    public static void main(String[] args) throws MalformedURLException {
       /*
        creating a connection to the database
        */
       DatabaseConnection connection = new DatabaseConnection("java", "password");
       
       try{
           int port = 1099;
           /*
           creating a Registry that allows the server to
           publish a service and client to retrieve the proxy
           */
           Registry registry = LocateRegistry.createRegistry(port);
           System.setSecurityManager(new RMISecurityManager());
           System.setProperty("java.rmi.server.hostname","192.168.1.17"); //indirizzo da cambiare a seconda di quello in cui si mettono i registri
           
           ManagerArbitro DBArbitro = new ManagerArbitro();
           ManagerCartellino DBCartellino = new ManagerCartellino();
           ManagerGiocatore DBGiocatore = new ManagerGiocatore();
           ManagerGoal DBGoal = new ManagerGoal();
           ManagerLogin DBLogin = new ManagerLogin();
           ManagerPartita DBPartita = new ManagerPartita();
           ManagerSquadra DBSquadra = new ManagerSquadra();
           ManagerTorneo DBTorneo = new ManagerTorneo();
           ManagerTorneoItaliana DBTorneoItaliana = new ManagerTorneoItaliana();
           ManagerTorneoEliminazionediretta DBTorneoEliminazionediretta = new ManagerTorneoEliminazionediretta();
   
//           registry.rebind("DBArbitro", DBArbitro);
//           registry.rebind("DBCartellino", DBCartellino);
//           registry.rebind("DBGiocatore", DBGiocatore);
//           registry.rebind("DBGoal", DBGoal);
//           registry.rebind("DBLogin", DBLogin);
//           registry.rebind("DBPartita", DBPartita);
//           registry.rebind("DBSquadra", DBSquadra);
//           registry.rebind("DBTorneo", DBTorneo);
//           registry.rebind("DBTorneoItaliana", DBTorneoItaliana);
//           registry.rebind("DBTorneoEliminazionediretta", DBTorneoEliminazionediretta);
           
           Naming.rebind("rmi://localhost/DBArbitro", DBArbitro);
           Naming.rebind("rmi://localhost/DBCartellino", DBCartellino);
           Naming.rebind("rmi://localhost/DBGiocatore", DBGiocatore);
           Naming.rebind("rmi://localhost/DBGoal", DBGoal);
           Naming.rebind("rmi://localhost/DBLogin", DBLogin);
           Naming.rebind("rmi://localhost/DBPartita", DBPartita);
           Naming.rebind("rmi://localhost/DBSquadra", DBSquadra);
           Naming.rebind("rmi://localhost/DBTorneo", DBTorneo);
           Naming.rebind("rmi://localhost/DBTorneoItaliana", DBTorneoItaliana);
           Naming.rebind("rmi://localhost/DBTorneoEliminazionediretta", DBTorneoEliminazionediretta);
           
           System.out.println("\nServer is ready...");
           
       }catch(RemoteException ex){
           System.out.println("\nRMIDatabaseServer ERROR: " + ex);
       }
    }
    
}
