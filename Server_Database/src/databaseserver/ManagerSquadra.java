/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import databaseinterface.DatabaseInterfaceSquadra;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import torneo.Giocatore;
import torneo.Squadra;

/**
 * Questa classe rappresenta ciò che verrà messo a disposizione nel registro per agire 
 * sulla tabella SQUADRA del database
 * @author nautilus
 */
public class ManagerSquadra extends UnicastRemoteObject implements DatabaseInterfaceSquadra{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
        
    /**
     * Crea un nuovo oggetto ManagerSquadra dal qaule sarà possibile effettuare 
     * la chiamata da remoto dei metodi da esso contenuti
     * @throws RemoteException 
     */
    public ManagerSquadra() throws RemoteException {
            
    }
    
    /**
     * Inserisce una tupla nella tabella SUQADRA contenente come valori
     * i parametri in ingresso al metodo 
     * @param nomeSquadra
     * @param cittaSquadra
     * @throws RemoteException 
     */
    @Override
    public void putSquadra(String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO SQUADRA (NOMESQUADRA, CITTASQUADRA)\n "
                    + "VALUES ( '" +  nomeSquadra + "' , '" + cittaSquadra + "' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Inserisce una tupla nella tabella SUQADRA contenente come valori
     * i parametri in ingresso al metodo 
     * @param nomeSquadra
     * @param cittaSquadra
     * @param coloreSquadra
     * @throws RemoteException 
     */
    @Override
    public void putSquadra(String nomeSquadra, String cittaSquadra, String coloreSquadra) throws RemoteException {
        try{
            query= "INSERT INTO SQUADRA\n "
                    + "VALUES ( '" +  nomeSquadra + "' , '" + cittaSquadra + "' , '" + coloreSquadra +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna il nome di una squadra dove:
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nuovoNome contiene il nuovo nome
     * @throws RemoteException 
     */
    @Override
    public void updateNomeSquadra(String nomeSquadra, String cittaSquadra, String nuovoNome) throws RemoteException {
        try{
            query= "UPDATE SQUADRA\n "
                    + "SET NOMESQUADRA = '" + nuovoNome + "'\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * Aggiorna la città di una squadra dove:
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nuovaCitta contiene la nuova città
     * @throws RemoteException 
     */
    @Override
    public void updateCittaSquadra(String nomeSquadra, String cittaSquadra, String nuovaCitta) throws RemoteException {
        try{
            query= "UPDATE SQUADRA\n "
                    + "SET CITTASQUADRA = '" + nuovaCitta + "'\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * Aggiorna il nome di una squadra dove:
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nuovoColore contiene il nuovo colore
     * @throws RemoteException 
     */
    @Override
    public void updateColoreSquadra(String nomeSquadra, String cittaSquadra, String nuovoColore) throws RemoteException {
        try{
            query= "UPDATE SQUADRA\n "
                    + "SET COLORESQUADRA = '" + nuovoColore + "'\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * Restituisce tutti i giocatori militanti in una squadra
     * @param nomeSquadra
     * @param cittaSquadra
     * @return
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Giocatore> getGiocatoreSquadra(String nomeSquadra, String cittaSquadra) throws RemoteException {
        ArrayList<Giocatore> giocatore = new ArrayList<>();
        
        try{
            query = "SELECT * FROM GIOCATORE\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra +"' ;" ;
            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
            resSet = statement.executeQuery();
           
            while(resSet.next()){
                Giocatore addGiocatore = new Giocatore(resSet.getString("NOMEGIOCATORE"), resSet.getString("COGNOMEGIOCATORE"), resSet.getInt("NUMEROGIOCATORE"));
                giocatore.add(addGiocatore);
                resSet.next();
            }
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
        return giocatore;
    }
    
    /**
     * Restituisce tutte le squadre
     * @return
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Squadra> getSquadra() throws RemoteException {
        ArrayList<Squadra> squadra = new ArrayList<>();
        
        try{
            query = "SELECT * FROM GIOCATORE;";
            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
            resSet = statement.executeQuery();
           
            while(resSet.next()){
                Squadra addSquadra = new Squadra(resSet.getString("NOMESQUADRA"), resSet.getString("CITTASQUADRA"), resSet.getString("COLORESQUADRA"), getGiocatoreSquadra(resSet.getString("NOMESQUADRA"), resSet.getString("CITTASQUADRA")), false);
                squadra.add(addSquadra);
                resSet.next();
            }
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
        return squadra;
    }
    
    /**
     * Elimina una tupla in SQUADRA dove i seguenti parametri sono parte di chiave:
     * @param nomeSquadra
     * @param cittaSquadra
     * @throws RemoteException 
     */
    @Override
    public void deleteSquadra(String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "DELETE FROM SQUADRA\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
}
