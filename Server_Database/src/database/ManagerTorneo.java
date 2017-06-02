/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import databaseinterface.DatabaseInterfaceTorneo;
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
public class ManagerTorneo extends UnicastRemoteObject implements DatabaseInterfaceTorneo{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
        
    /**
     *
     * @throws RemoteException
     */
    public ManagerTorneo() throws RemoteException {
            
    }

    @Override
    public void putTorneo(String nome, int annoTorneo) throws RemoteException {
        try{
            query= "INSERT INTO TORNEO\n"
                    + " VALUES ( '" + nome.toUpperCase() + "' , '" + annoTorneo +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateNomeTorneo(String nome, int annoTorneo, String nuovoNome) throws RemoteException {
        try{
            query= "UPDATE TORNEO\n "
                    + "SET NOMETORNEO = '" + nuovoNome.toUpperCase() + "'\n "
                    + "WHERE NOMETORNEO = '" + nome.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateAnnoTorneo(String nome, int annoTorneo, int nuovoAnno) throws RemoteException {
        try{
            query= "UPDATE TORNEO\n "
                    + "SET ANNOTORNEO = '" + nuovoAnno + "'\n "
                    + "WHERE NOMETORNEO = '" + nome.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public String getTorneo() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTorneo(String nome, int annoTorneo) throws RemoteException {
        try{
            query= "DELETE FROM TORNEO\n "
                    + "WHERE NOMETORNEO = '" + nome.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
}
