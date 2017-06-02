/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import databaseinterface.DatabaseInterfaceCartellino;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.*;
import torneo.Cartellino;
import torneo.Partita;

/**
 *
 * @author nautilus
 */
public class ManagerCartellino extends UnicastRemoteObject implements DatabaseInterfaceCartellino{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
    
    public ManagerCartellino() throws RemoteException{
        
    }
    
    @Override
    public void putCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "INSERT INTO CARTELLINO\n "
                    + "VALUES ( '" + idPartita + "','" + colore.toUpperCase() + "' , '" + minuto + "' , '" + numeroGiocatore + "' , '" + nomeSquadra.toUpperCase() + "' , '" + cittaSquadra.toUpperCase() + "' , '" + nomeTorneo.toUpperCase() + annoTorneo +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    @Override
    public void updateIdPartitaCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoIdPartita) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET IDPARTITA = '" + nuovoIdPartita + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateColoreCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovoColore) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET COLORECARTELLINO = '" + nuovoColore.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }  
    }

    @Override
    public void updateMinutoCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoMinuto) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET MINUTO = '" + nuovoMinuto + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateNumeroGiocatoreCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoNumeroGiocatore) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET NUMEROGIOCATORE = '" + nuovoNumeroGiocatore + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateNomeSquadraCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovoNomeSquadra) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET NOMESQUADRA = '" + nuovoNomeSquadra.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateCittaSquadraCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovaCittaSquadra) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET CITTASQUADRA = '" + nuovaCittaSquadra.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateNomeTorneoCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovoNomeTorneo) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET NOMETORNEO = '" + nuovoNomeTorneo.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateAnnoTorneoCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoAnnoTorneo) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET ANNOTORNEO = '" + nuovoAnnoTorneo + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    @Override
    public Cartellino getCartellino() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void deleteCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "DELETE FROM CARTELLINO\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORENO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
}
