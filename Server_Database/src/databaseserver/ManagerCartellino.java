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
 * Questa classe rappresenta ciò che verrà messo a disposizione nel registro per agire 
 * sulla tabella CARTELLINO del database
 * @author nautilus
 */
public class ManagerCartellino extends UnicastRemoteObject implements DatabaseInterfaceCartellino{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
    
    /**
     * Crea un nuovo oggetto ManagerCartellino dal qaule sarà possibile effettuare 
     * la chiamata da remoto dei metodi da esso contenuti
     * @throws RemoteException 
     */
    public ManagerCartellino() throws RemoteException{
        
    }
    
    /**
     * Inserisce una tupla nella tabella CARTELLINO contenente come valori
     * i parametri in ingresso al metodo 
     * @param idPartita
     * @param colore
     * @param minuto
     * @param numeroGiocatore
     * @param nomeSquadra
     * @param cittaSquadra
     * @param nomeTorneo
     * @param annoTorneo
     * @throws RemoteException 
     */
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
    
    /**
     * Aggiorna l'ID della partita di un cartellino dove:
     * @param idPartita è parte di chiave
     * @param colore è parte di chiave
     * @param minuto è parte di chiave
     * @param numeroGiocatore è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoIdPartita contiene il nuovo ID
     * @throws RemoteException 
     */
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
    
    /**
     * Aggiorna il colore di un cartellino dove:
     * @param idPartita è parte di chiave
     * @param colore è parte di chiave
     * @param minuto è parte di chiave
     * @param numeroGiocatore è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoColore contiene il nuovo colore
     * @throws RemoteException 
     */
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
    
    /**
     * Aggiorna il minuto di un cartellino dove:
     * @param idPartita è parte di chiave
     * @param colore è parte di chiave
     * @param minuto è parte di chiave
     * @param numeroGiocatore è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoMinuto contiene il nuovo minuto
     * @throws RemoteException 
     */
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
    
    /**
     * Aggiorna il numero del giocatore di un cartellino dove:
     * @param idPartita è parte di chiave
     * @param colore è parte di chiave
     * @param minuto è parte di chiave
     * @param numeroGiocatore è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoNumeroGiocatore contiene il nuovo numero
     * @throws RemoteException 
     */
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
    
    /**
     * Aggiorna il nome della squadra di un cartellino dove:
     * @param idPartita è parte di chiave
     * @param colore è parte di chiave
     * @param minuto è parte di chiave
     * @param numeroGiocatore è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoNomeSquadra contiene il nuovo nome
     * @throws RemoteException 
     */
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
    
    /**
     * Aggiorna la città della squadra di un cartellino dove:
     * @param idPartita è parte di chiave
     * @param colore è parte di chiave
     * @param minuto è parte di chiave
     * @param numeroGiocatore è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovaCittaSquadra contiene la nuova città
     * @throws RemoteException 
     */
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
    
    /**
     * Aggiorna il nome del torneo di un cartellino dove:
     * @param idPartita è parte di chiave
     * @param colore è parte di chiave
     * @param minuto è parte di chiave
     * @param numeroGiocatore è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoNomeTorneo contiene il nuovo nome
     * @throws RemoteException 
     */
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
    
    /**
     * Aggiorna l'anno del torneo di un cartellino dove:
     * @param idPartita è parte di chiave
     * @param colore è parte di chiave
     * @param minuto è parte di chiave
     * @param numeroGiocatore è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoAnnoTorneo contiene il nuovo anno
     * @throws RemoteException 
     */
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
    
    /**
     * Restituisce tutti i cartellini
     * @return
     * @throws RemoteException 
     */
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
    
    /**
     * Elimina un tupla in CARTELLINO dove i seguenti parametri sono parte di chiave:
     * @param idPartita
     * @param colore
     * @param minuto
     * @param numeroGiocatore
     * @param nomeSquadra
     * @param cittaSquadra
     * @param nomeTorneo
     * @param annoTorneo
     * @throws RemoteException 
     */
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
