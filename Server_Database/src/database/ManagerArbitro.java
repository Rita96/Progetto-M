/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import databaseinterface.DatabaseInterfaceArbitro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import torneo.Arbitro;

/**
 *
 * @author nautilus
 */
public class ManagerArbitro extends UnicastRemoteObject implements DatabaseInterfaceArbitro {
        
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
        
    public ManagerArbitro() throws RemoteException{
        
    }
    
    @Override
    public void putArbitro(Arbitro arbitro) throws RemoteException {
        try{
            query= "INSERT INTO ARBITRO\n "
                    + "VALUES ( '" + arbitro.getCodiceFiscale().toUpperCase() + "' , '" + arbitro.getNome() + "' , '" + arbitro.getCognome() + "' , '" + arbitro.getPassword() +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    @Override
    public void updateCfArbitro(String codiceFiscale, String nuovoCf) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET CFARBITRO = '" + nuovoCf.toUpperCase() + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateNomeArbitro(String codiceFiscale, String nuovoNome) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET NOMEARBITRO = '" + nuovoNome.toUpperCase() + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateCognomeArbitro(String codiceFiscale, String nuovoCognome) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET COGNOMEARBITRO = '" + nuovoCognome.toUpperCase() + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateUsernameArbitro(String codiceFiscale, String nuovoUsername) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET USERNAME = '" + nuovoUsername + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updatePasswordArbitro(String codiceFiscale, String nuovaPassword) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET PASSWORD = '" + nuovaPassword + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public Arbitro getArbitro() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteArbitro(Arbitro arbitro) throws RemoteException {
        try{
            query= "DELETE FROM ARBITRO\n "
                    + "WHERE CFARBITRO = '" + arbitro.getCodiceFiscale().toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
}
