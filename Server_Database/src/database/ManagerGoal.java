/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import databaseinterface.DatabaseInterfaceGoal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import torneo.Goal;

/**
 *
 * @author nautilus
 */
public class ManagerGoal extends UnicastRemoteObject implements DatabaseInterfaceGoal{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
    
    public ManagerGoal() throws RemoteException{
        
    }
    
    @Override
    public void putGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "INSERT INTO GOAL\n "
                    + "VALUES ( '" + idPartita + "' , '" + numeroGiocatore + "' , '" + nomeSquadra.toUpperCase() + "' , '" + cittaSquadra.toUpperCase() + "' , '" + nomeTorneo.toUpperCase() + annoTorneo +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    @Override
    public void updateIdPartitaGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoId) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET IDPARTITA = '" + nuovoId + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateMinutoGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoMinuto) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET MINUTO = '" + nuovoMinuto + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateNumeroGiocatoreGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoNumero) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET NUMEROGIOCATORE = '" + nuovoNumero + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateNomeSquadraGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovoNomeSquadra) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET NOMESQUADRA = '" + nuovoNomeSquadra.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateCittaSquadraGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovaCittaSquadra) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET CITTASQUADRA = '" + nuovaCittaSquadra.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateNomeTorneoGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovoNomeTorneo) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET NOMETORNEO = '" + nuovoNomeTorneo.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateAnnoTorneoGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoAnnoTorneo) throws RemoteException {
        try{
            query= "UPDATE GOAL\n "
                    + "SET ANNOTORNEO = '" + nuovoAnnoTorneo + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public Goal getGoal() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "DELETE FROM GOAL\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND MINUTO = '" + minuto + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORENO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
}
