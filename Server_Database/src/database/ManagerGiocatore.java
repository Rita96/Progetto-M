/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import databaseinterface.DatabaseInterfaceGiocatore;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import torneo.Giocatore;

/**
 *
 * @author nautilus
 */
public class ManagerGiocatore extends UnicastRemoteObject implements DatabaseInterfaceGiocatore{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
    
    public ManagerGiocatore() throws RemoteException{
        
    }
    
    @Override
    public void putGiocatore(int numero, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO GIOCATORE (NUMERO, NOMESQUADRA, CITTASQUADRA)\n "
                    + "VALUES ( '" + numero + "' , '" + nomeSquadra.toUpperCase() + "' , '" + cittaSquadra.toUpperCase() +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void putGiocatore(int numero, String nomeGiocatore, String cognomeGiocatore, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO GIOCATORE\n "
                    + "VALUES ( '" + numero + "' , '" + nomeGiocatore.toUpperCase() + "' , '" + cognomeGiocatore.toUpperCase() + "' , '"  + nomeSquadra.toUpperCase() + "' , '" + cittaSquadra.toUpperCase() +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    @Override
    public void updateNumeroGiocatore(int numero, String nomeSquadra, String cittaSquadra, int nuovoNumero) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET NUMERO = '" + nuovoNumero + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateNomeGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovoNome) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET NOMEGIOCATORE = '" + nuovoNome.toUpperCase() + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateCognomeGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovoCognome) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET COGNOMEGIOCATORE = '" + nuovoCognome.toUpperCase() + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateNomeSquadraGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovoNomeSquadra) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET NOMESQUADRA = '" + nuovoNomeSquadra.toUpperCase() + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateCittaSquadraGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovaCittaSquadra) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET CITTASQUADRA = '" + nuovaCittaSquadra.toUpperCase() + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public Giocatore getGiocatore() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteGiocatore(int numero, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "DELETE FROM GIOCATORE\n "
                    + "WHERE NOMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    
}
