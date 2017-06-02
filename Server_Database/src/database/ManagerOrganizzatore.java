/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import databaseinterface.DatabaseInterfaceOrganizzatore;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author nautilus
 */
public class ManagerOrganizzatore extends UnicastRemoteObject implements DatabaseInterfaceOrganizzatore{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
    
    public ManagerOrganizzatore() throws RemoteException{
        
    }
    
    @Override
    public void putOrganizzatore(int idOrganizzatore) throws RemoteException {
        try{
            query= "INSERT INTO ORGANIZZATORE (IDORGANIZZATORE)\n "
                    + "VALUES ( '" + idOrganizzatore + "' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void putOrganizzatore(int idOrganizzatore, String username, String password) throws RemoteException {
        try{
            query= "INSERT INTO ORGANIZZATORE\n "
                    + "VALUES ( '" + idOrganizzatore + "' , '" + username + "' , '" + password +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    @Override
    public void updateIdOrganizzatore(int idOrganizzatore, int nuovoId) throws RemoteException {
        try{
            query= "UPDATE ORGANIZZATORE\n "
                    + "SET IDORGANIZZATORE = '" + nuovoId + "'\n "
                    + "WHERE IDORGANIZZATORE = '" + idOrganizzatore + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateUsernameOrganizzatore(int idOrganizzatore, String nuovoUsername) throws RemoteException {
        try{
            query= "UPDATE ORGANIZZATORE\n "
                    + "SET USERNAME = '" + nuovoUsername + "'\n "
                    + "WHERE IDORGANIZZATORE = '" + idOrganizzatore + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updatePasswordOrganizzatore(int idOrganizzatore, String nuovaPassword) throws RemoteException {
        try{
            query= "UPDATE ORGANIZZATORE\n "
                    + "SET PASSWORD = '" + nuovaPassword + "'\n "
                    + "WHERE IDORGANIZZATORE = '" + idOrganizzatore + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    @Override
    public String getOrganizzatore() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void deleteOrganizzatore(int idOrganizzatore) throws RemoteException {
        try{
            query= "DELETE FROM ORGANIZZATORE\n "
                    + "WHERE IDORGANIZZATORE = '" + idOrganizzatore + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

}
