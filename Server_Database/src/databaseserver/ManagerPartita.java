/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import databaseinterface.DatabaseInterfacePartita;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import torneo.Arbitro;
import torneo.Cartellino;
import torneo.ColoreCartellino;
import torneo.Giocatore;
import torneo.Goal;
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
    
    /**
     * 
     * @param idPartita
     * @param nomeTorneo
     * @param annoTorneo
     * @throws RemoteException 
     */
    @Override
    public void putPartita(int idPartita, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "INSERT INTO PARTITA (IDPARTITA, NOMETORNEO, ANNOTORNEO)\n "
                    + "VALUES ( '" + idPartita + "' , '" + nomeTorneo + annoTorneo + "' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * 
     * @param idPartita
     * @param squadraCasa
     * @param squadraOspite
     * @param data
     * @param statoPartita
     * @param cfArbitro
     * @param nomeTorneo
     * @param annoTorneo
     * @throws RemoteException 
     */
    
    
    @Override
    public void putPartita(int idPartita, String squadraCasa, String squadraOspite, String cfArbitro, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "INSERT INTO PARTITA\n "
                    + "VALUES ( '" + idPartita + "' , '" + squadraCasa + "' , '" + squadraOspite + "' , '" + cfArbitro + "' , '" + nomeTorneo + annoTorneo +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    
    @Override
    public void putPartita(int idPartita, String squadraCasa, String squadraOspite, String statoPartita, String cfArbitro, String nomeTorneo, int annoTorneo, String cittaPartita) throws RemoteException {
        try{
            query= "INSERT INTO PARTITA\n "
                    + "VALUES ( '" + idPartita + "' , '" + squadraCasa + "' , '" + squadraOspite + "' , '" + statoPartita + "' , '" + cfArbitro + "' , '" + nomeTorneo + "' , '" + annoTorneo + "' , '" + cittaPartita + "' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * 
     * @param idPartita
     * @param nomeTorneo
     * @param annoTorneo
     * @param nuovoId
     * @throws RemoteException 
     */
    @Override
    public void updateIdPartita(int idPartita, String nomeTorneo, int annoTorneo, int nuovoId) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET IDPARTITA = '" + nuovoId + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * 
     * @param idPartita
     * @param nomeTorneo
     * @param annoTorneo
     * @param nuovoNomeSquadraCasa
     * @throws RemoteException 
     */
    @Override
    public void updateNomeSquadraCasaPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovoNomeSquadraCasa) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET NOMESQUADRACASA = '" + nuovoNomeSquadraCasa + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * 
     * @param idPartita
     * @param nomeTorneo
     * @param annoTorneo
     * @param nuovonomeSquadraOspite
     * @throws RemoteException 
     */
    @Override
    public void updateNomeSquadraOspitePartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovonomeSquadraOspite) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET NOMESQUADRAOSPITE = '" + nuovonomeSquadraOspite + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * 
     * @param idPartita
     * @param nomeTorneo
     * @param annoTorneo
     * @param nuovoGoalCasa
     * @throws RemoteException 
     */
    @Override
    public void updateGoalCasaPartita(int idPartita, String nomeTorneo, int annoTorneo, int nuovoGoalCasa) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET GOALCASA = '" + nuovoGoalCasa + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * 
     * @param idPartita
     * @param nomeTorneo
     * @param annoTorneo
     * @param nuovoGoalOspite
     * @throws RemoteException 
     */
    @Override
    public void updateGoalOspitePartita(int idPartita, String nomeTorneo, int annoTorneo, int nuovoGoalOspite) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET GOALOSPITE = '" + nuovoGoalOspite + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * 
     * @param idPartita
     * @param nomeTorneo
     * @param annoTorneo
     * @param nuovaData
     * @throws RemoteException 
     */
    @Override
    public void updateDataPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovaData) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET DATA = '" + nuovaData + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    /**
     * 
     * @param idPartita
     * @param nomeTorneo
     * @param annoTorneo
     * @param nuovaAndataRitorno
     * @throws RemoteException 
     */
    @Override
    public void updateStatoPartitaPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovoStatoPartita) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET ANDATARIToRNO = '" + nuovoStatoPartita + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * 
     * @param idPartita
     * @param nomeTorneo
     * @param annoTorneo
     * @param nuovoCfArbitro
     * @throws RemoteException 
     */
    @Override
    public void updateCfArbitroPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovoCfArbitro) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET CFARBITRO = '" + nuovoCfArbitro + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * 
     * @param idPartita
     * @param nomeTorneo
     * @param annoTorneo
     * @param nuovoNomeTorneo
     * @throws RemoteException 
     */
    @Override
    public void updateNomeTorneoPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovoNomeTorneo) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET NOMETORNEO = '" + nuovoNomeTorneo + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    /**
     * 
     * @param idPartita
     * @param nomeTorneo
     * @param annoTorneo
     * @param nuovoAnnoTorneo
     * @throws RemoteException 
     */
    @Override
    public void updateAnnoTorneoPartita(int idPartita, String nomeTorneo, int annoTorneo, int nuovoAnnoTorneo) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET ANNOTORNEO = '" + nuovoAnnoTorneo + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    
     @Override
    public void updateCittaPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovaCitta) throws RemoteException {
        try{
            query= "UPDATE PARTITA\n "
                    + "SET CITTAPARTITA = '" + nuovaCitta + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * 
     * @param idPartita
     * @return
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Goal> getGoalPartita(int idPartita) throws RemoteException {
        ArrayList<Goal> goal = new ArrayList<>();
        
        try{
            query = "SELECT * FROM GOAL JOIN GIOCATORE\n "
                    + "WHERE IDPARTITA = '" + idPartita +"' ;";;
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
     * 
     * @param idPartita
     * @return
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Cartellino> getCartellinoPartita(int idPartita) throws RemoteException {
        ArrayList<Cartellino> cartellino = new ArrayList<>();
        
        try{
            query = "SELECT * FROM CARTELLINO JOIN GIOCATORE\n "
                    + "WHERE IDPARTITA = '" + idPartita +"' ;";
            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
            resSet = statement.executeQuery();
           
            while(resSet.next()){
                Giocatore giocatore = new Giocatore(resSet.getString("NOMEGIOCATORE"), resSet.getString("COGNOMEGIOCATORE"), resSet.getInt("NUMEROGIOCATORE"));
                Cartellino addCartellino = new Cartellino(ColoreCartellino.valueOf(resSet.getString("COLORECARTELLINO")), giocatore);
                cartellino.add(addCartellino);
                resSet.next();
            }
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
        return cartellino;
    }
    
    /**
     * 
     * @param idPartita
     * @return
     * @throws RemoteException 
     */
    @Override
    public Arbitro getArbitroPartita(int idPartita) throws RemoteException {
        Arbitro arbitro = null;
        
        try{
            query = "SELECT * FROM PARTITA JOIN ARBITRO\n "
                    + "WHERE IDPARTITA = '" + idPartita +"' ;";
            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
            resSet = statement.executeQuery();
            arbitro = new Arbitro(resSet.getString("NOMEARBITRO"), resSet.getString("COGNOMEARBITRO"), resSet.getString("CFARBITRO"), resSet.getString("PASSWORD"), false);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
        return arbitro;
    }
    
    /**
     * 
     * @param idPartita
     * @param nomeTorneo
     * @param annoTorneo
     * @throws RemoteException 
     */
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
