/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import databaseinterface.DatabaseInterfaceGoal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import torneo.Giocatore;
import torneo.Goal;

/**
 * Questa classe rappresenta ciò che verrà messo a disposizione nel registro per agire 
 * sulla tabella GOAL del database
 * @author nautilus
 */
public class ManagerGoal extends UnicastRemoteObject implements DatabaseInterfaceGoal{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
    
    /**
     * Crea un nuovo oggetto ManagerGoal dal qaule sarà possibile effettuare 
     * la chiamata da remoto dei metodi da esso contenuti
     * @throws RemoteException 
     */
    public ManagerGoal() throws RemoteException{
        
    }
    
    /**
     * Inserisce una tupla nella tabella GOAL contenente come valori
     * i parametri in ingresso al metodo 
     * @param idPartita
     * @param minuto
     * @param numeroGiocatore
     * @param nomeSquadra
     * @param cittaSquadra
     * @param nomeTorneo
     * @param annoTorneo
     * @throws RemoteException 
     */
    @Override
    public void putGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "INSERT INTO GOAL\n "
                    + "VALUES ( '" + idPartita + "' , '" + numeroGiocatore + "' , '" + nomeSquadra + "' , '" + cittaSquadra + "' , '" + nomeTorneo + annoTorneo +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna l'ID partita del goal in una tupla dove:
     * @param idPartita è parte di chiave
     * @param minuto è parte di chiave
     * @param numeroGiocatore è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoId contiene il nuovo ID
     * @throws RemoteException 
     */
    @Override
    public void updateIdPartitaGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoId) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET IDPARTITA = '" + nuovoId + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna il minuto del goal in una tupla dove:
     * @param idPartita è parte di chiave
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
    public void updateMinutoGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoMinuto) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET MINUTO = '" + nuovoMinuto + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna il numero del giocatore del goal in una tupla dove:
     * @param idPartita è parte di chiave
     * @param minuto è parte di chiave
     * @param numeroGiocatore è parte di chiave
     * @param nomeSquadra è parte di chiave
     * @param cittaSquadra è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoNumero contiene il nuovo numero
     * @throws RemoteException 
     */
    @Override
    public void updateNumeroGiocatoreGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoNumero) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET NUMEROGIOCATORE = '" + nuovoNumero + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna il nome della squadra del goal in una tupla dove:
     * @param idPartita è parte di chiave
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
    public void updateNomeSquadraGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovoNomeSquadra) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET NOMESQUADRA = '" + nuovoNomeSquadra + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna la città della squadra del goal in una tupla dove:
     * @param idPartita è parte di chiave
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
    public void updateCittaSquadraGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovaCittaSquadra) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET CITTASQUADRA = '" + nuovaCittaSquadra + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna il nome del torneo del goal in una tupla dove:
     * @param idPartita è parte di chiave
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
    public void updateNomeTorneoGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovoNomeTorneo) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET NOMETORNEO = '" + nuovoNomeTorneo + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna l'anno del torneo del goal in una tupla dove:
     * @param idPartita è parte di chiave
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
    public void updateAnnoTorneoGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoAnnoTorneo) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET ANNOTORNEO = '" + nuovoAnnoTorneo + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Restituisce tutti i goal
     * @return
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Goal> getGoal() throws RemoteException {
        ArrayList<Goal> goal = new ArrayList<>();
        
        try{
            query = "SELECT * FROM GOAL JOIN GIOCATORE;";
            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
            resSet = statement.executeQuery();
           
            while(resSet.next()){
                Giocatore giocatore = new Giocatore(resSet.getString("NOMEGIOCATORE"), resSet.getString("COGNOMEGIOCATORE"), resSet.getInt("NUMEROGIOCATORE"));
                Goal addGoal = new Goal(resSet.getInt("MINUTO"), giocatore);
                goal.add(addGoal);
                resSet.next();
            }
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
        return goal;    
    }
    
    /**
     * Elimina una tupla in GOAL dove i seguenti parametri sono parte di chiave:
     * @param idPartita
     * @param minuto
     * @param numeroGiocatore
     * @param nomeSquadra
     * @param cittaSquadra
     * @param nomeTorneo
     * @param annoTorneo
     * @throws RemoteException 
     */
    @Override
    public void deleteGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "DELETE FROM GOAL\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORENO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
}
