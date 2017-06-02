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
           
//           DatabaseManagement DBManage = new DatabaseManagement();
//           registry.rebind("DBProgettoM", DBManage);
           
           ManagerArbitro DBArbitro = new ManagerArbitro();
           ManagerCartellino DBCartellino = new ManagerCartellino();
           ManagerGiocatore DBGiocatore = new ManagerGiocatore();
           ManagerGoal DBGoal = new ManagerGoal();
           ManagerLogin DBLogin = new ManagerLogin();
           ManagerOrganizzatore DBOrganizzatore = new ManagerOrganizzatore();
           ManagerPartita DBPartita = new ManagerPartita();
           ManagerSquadra DBSquadra = new ManagerSquadra();
           ManagerTorneo DBTorneo = new ManagerTorneo();
           ManagerTorneoItaliana DBTorneoItaliana = new ManagerTorneoItaliana();
           
           registry.rebind("DBArbitro", DBArbitro);
           registry.rebind("DBCartellino", DBCartellino);
           registry.rebind("DBGiocatore", DBGiocatore);
           registry.rebind("DBGoal", DBGoal);
           registry.rebind("DBLogin", DBLogin);
           registry.rebind("DBOrganizzatore", DBOrganizzatore);
           registry.rebind("DBPartita", DBPartita);
           registry.rebind("DBSquadra", DBSquadra);
           registry.rebind("DBTorneo", DBTorneo);
           registry.rebind("DBTorneoItaliana", DBTorneoItaliana);
           
           
           
           System.out.println("\nServer is ready...");
           
       }catch(RemoteException ex){
           System.out.println("\nRMIDatabaseServer ERROR: " + ex);
       }
    }
    
}
