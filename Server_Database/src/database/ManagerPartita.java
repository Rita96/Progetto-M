/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import databaseinterface.DatabaseInterfacePartita;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import torneo.Partita;

/**
 *
 * @author nautilus
 */
public class ManagerPartita extends UnicastRemoteObject implements DatabaseInterfacePartita{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
        
    public ManagerPartita() throws RemoteException {
            
    }
    
    @Override
    public void putPartita(int idPartita, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "INSERT INTO PARTITA (IDPARTITA, NOMETORNEO, ANNOTORNEO)\n "
                    + "VALUES ( '" + idPartita + "' , '" + nomeTorneo.toUpperCase() + annoTorneo + "' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void putPartita(int idPartita, String squadraCasa, String squadraOspite, Date data, String andataritorno, String cfArbitro, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "INSERT INTO PARTITA\n "
                    + "VALUES ( '" + idPartita + "' , '" + squadraCasa.toUpperCase() + "' , '" + squadraOspite.toUpperCase() + "' , '" + data + "' , '" + andataritorno.toUpperCase() + "' , '" + cfArbitro.toUpperCase() + "' , '" + nomeTorneo.toUpperCase() + annoTorneo +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    @Override
    public void updateIdPartita(int idPartita, String nomeTorneo, int annoTorneo, int nuovoId) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET IDPARTITA = '" + nuovoId + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateNomeSquadraCasaPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovoNomeSquadraCasa) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET NOMESQUADRACASA = '" + nuovoNomeSquadraCasa.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateNomeSquadraOspitePartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovonomeSquadraOspite) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET NOMESQUADRAOSPITE = '" + nuovonomeSquadraOspite.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateGoalCasaPartita(int idPartita, String nomeTorneo, int annoTorneo, int nuovoGoalCasa) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET GOALCASA = '" + nuovoGoalCasa + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateGoalOspitePartita(int idPartita, String nomeTorneo, int annoTorneo, int nuovoGoalOspite) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET GOALOSPITE = '" + nuovoGoalOspite + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateDataPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovaData) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET DATA = '" + nuovaData + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateAndataRitornoPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovaAndataRitorno) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET ANDATARIToRNO = '" + nuovaAndataRitorno.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateCfArbitroPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovoCfArbitro) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET CFARBITRO = '" + nuovoCfArbitro.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateNomeTorneoPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovoNomeTorneo) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET NOMETORNEO = '" + nuovoNomeTorneo.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateAnnoTorneoPartita(int idPartita, String nomeTorneo, int annoTorneo, int nuovoAnnoTorneo) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET ANNOTORNEO = '" + nuovoAnnoTorneo + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void putPartita(int idPartita, String squadraCasa, String squadraOspite, Date data, String cfArbitro, String nomeTorneo, int annoTorneo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Partita getPartita() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePartita(int idPartita, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "DELETE FROM PARTITA\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
}
