/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*; 
import static database.DatabaseConnection.statement;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author nautilus
 */
public class DatabaseGestion extends UnicastRemoteObject implements DatabaseInterface{
    
    private  static String query;
    private static ResultSet resSet;
    
    public DatabaseGestion ()throws RemoteException{
        
    }
        
    @Override
    public String getQuery(){
        try{
            query= "SELECT * FROM PARTITA";
            resSet = statement.executeQuery(query);
            System.out.println("RISULTATO QUERY:");
        }catch(Exception ex){
            System.out.println("ERROR:" + ex);
        }
        return query;
    }
}
