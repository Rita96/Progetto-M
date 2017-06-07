/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import databaseinterface.DatabaseInterfaceLogin;
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
public class ManagerLogin extends UnicastRemoteObject implements DatabaseInterfaceLogin{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
    
    public ManagerLogin() throws RemoteException{
        
    }
    
    @Override
    public void putLogin(String username, String password) throws RemoteException {
        try{
            query= "INSERT INTO LOGIN\n"
                    + " VALUES ( '" + username + "' , '" + password +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }    
    }
    
    @Override
    public void updateUsernameLogin(String username, String password, String nuovoUsername) throws RemoteException {
        try{
            query= "UPDATE LOGIN\n "
                    + "SET USERNAME = '" + nuovoUsername + "'\n "
                    + "WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updatePasswordLogin(String username, String password, String nuovaPassword) throws RemoteException {
        try{
            query= "UPDATE LOGIN\n "
                    + "SET PASSWORD = '" + nuovaPassword + "'\n "
                    + "WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public String getPasswordLogin(String username) throws RemoteException {
        String password=null;
        try{
            query = "SELECT PASSWORD FROM LOGIN\n "
                    + "WHERE USERNAME ='" + username + "'; "; 
            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
            resSet = statement.executeQuery();
            password = resSet.getString("PASSWORD");
        }catch( SQLException ex){
            System.out.println("ERROR:" + ex);
        }
        return password;
    }

    @Override
    public void deleteLogin(String username, String password) throws RemoteException {
        try{
            query= "DELETE FROM LOGIN\n "
                    + "WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
}
