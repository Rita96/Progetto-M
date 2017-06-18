/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import databaseinterface.DatabaseInterfaceCartellino;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.*;
import torneo.Cartellino;
import torneo.ColoreCartellino;
import torneo.Giocatore;
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
                    + "VALUES ( '" + idPartita + "','" + colore.toUpperCase() + "' , '" + minuto + "' , '" + numeroGiocatore + "' , '" + nomeSquadra + "' , '" + cittaSquadra + "' , '" + nomeTorneo + annoTorneo +"' );";
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
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
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
                    + "SET COLORECARTELLINO = '" + nuovoColore + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
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
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
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
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
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
                    + "SET NOMESQUADRA = '" + nuovoNomeSquadra + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
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
                    + "SET CITTASQUADRA = '" + nuovaCittaSquadra + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
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
                    + "SET NOMETORNEO = '" + nuovoNomeTorneo + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
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
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    @Override
    public ArrayList<Cartellino> getCartellino() throws RemoteException {
        ArrayList<Cartellino> cartellino = new ArrayList<>();
        
        try{
            query = "SELECT * FROM CARTELLINO JOIN GIOCATORE;";
            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
            resSet = statement.executeQuery();
           
            while(resSet.next()){
                Giocatore giocatore = new Giocatore(resSet.getString("NOMEGIOCATORE"), resSet.getString("COGNOMEGIOCATORE"), resSet.getInt("NUMEROGIOCATORE"));
                Cartellino addCartellino = new Cartellino(ColoreCartellino.valueOf(resSet.getString("COLORECARTELLINO")), giocatore, resSet.getInt("MINUTO"));
                cartellino.add(addCartellino);
                resSet.next();
            }
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
        return cartellino;
    }
    
    @Override
    public void deleteCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "DELETE FROM CARTELLINO\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORENO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
}
