/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import databaseinterface.DatabaseInterfaceGiocatore;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import torneo.Giocatore;

/**
 * Questa classe rappresenta ciò che verrà messo a disposizione nel registro per agire 
 * sulla tabella GIOCATORE del database
 * @author nautilus
 */
public class ManagerGiocatore extends UnicastRemoteObject implements DatabaseInterfaceGiocatore{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
    
    /**
     * Crea un nuovo oggetto ManagerGiocatore dal qaule sarà possibile effettuare 
     * la chiamata da remoto dei metodi da esso contenuti
     * @throws RemoteException 
     */
    public ManagerGiocatore() throws RemoteException{
        
    }
    
    /**
     * Inserisce una tupla nella tabella GIOCATORE contenente come valori
     * i parametri in ingresso al metodo 
     * @param numero
     * @param nomeSquadra
     * @param cittaSquadra
     * @throws RemoteException 
     */
    @Override
    public void putGiocatore(int numero, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO GIOCATORE (NUMERO, NOMESQUADRA, CITTASQUADRA)\n "
                    + "VALUES ( '" + numero + "' , '" + nomeSquadra + "' , '" + cittaSquadra +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Inserisce una tupla nella tabella GIOCATORE contenente come valori
     * i parametri in ingresso al metodo  
     * @param numero
     * @param nomeGiocatore
     * @param cognomeGiocatore
     * @param nomeSquadra
     * @param cittaSquadra
     * @throws RemoteException 
     */
    @Override
    public void putGiocatore(int numero, String nomeGiocatore, String cognomeGiocatore, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO GIOCATORE\n "
                    + "VALUES ( '" + numero + "' , '" + nomeGiocatore + "' , '" + cognomeGiocatore + "' , '"  + nomeSquadra + "' , '" + cittaSquadra +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna il numero del giocatore dove:
     * @param numero è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nuovoNumero contiene il nuovo numero
     * @throws RemoteException 
     */
    @Override
    public void updateNumeroGiocatore(int numero, String nomeSquadra, String cittaSquadra, int nuovoNumero) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET NUMERO = '" + nuovoNumero + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * Aggiorna il nome del giocatore dove:
     * @param numero è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nuovoNome contiene il nuovo nome
     * @throws RemoteException 
     */
    @Override
    public void updateNomeGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovoNome) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET NOMEGIOCATORE = '" + nuovoNome + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * Aggiorna il cognome del giocatore dove:
     * @param numero è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nuovoCognome contiene il nuovo cognome
     * @throws RemoteException 
     */
    @Override
    public void updateCognomeGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovoCognome) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET COGNOMEGIOCATORE = '" + nuovoCognome + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * Aggiorna il nome della squadra del giocatore dove:
     * @param numero è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nuovoNomeSquadra contiene il nuovo nome
     * @throws RemoteException 
     */
    @Override
    public void updateNomeSquadraGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovoNomeSquadra) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET NOMESQUADRA = '" + nuovoNomeSquadra + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * Aggiorna la città della squadra del giocatore dove:
     * @param numero è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nuovaCittaSquadra contiene la nuova città
     * @throws RemoteException 
     */
    @Override
    public void updateCittaSquadraGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovaCittaSquadra) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET CITTASQUADRA = '" + nuovaCittaSquadra + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * Restituisce tutti i giocatori
     * @return
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Giocatore> getGiocatore() throws RemoteException {
        ArrayList<Giocatore> giocatore = new ArrayList<>();
        
        try{
            query = "SELECT * FROM GIOCATORE;";
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
     * Elimina una tupla in GIOCATORE dove i seguenti parametri sono parte di chiave
     * @param numero
     * @param nomeSquadra
     * @param cittaSquadra
     * @throws RemoteException 
     */
    @Override
    public void deleteGiocatore(int numero, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "DELETE FROM GIOCATORE\n "
                    + "WHERE NOMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    
}
