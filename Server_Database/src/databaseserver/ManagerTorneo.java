/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import databaseinterface.DatabaseInterfaceTorneo;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import torneo.Cartellino;
import torneo.ColoreCartellino;
import torneo.Giocatore;
import torneo.Goal;
import torneo.Torneo;

/**
 *
 * @author nautilus
 */
public class ManagerTorneo extends UnicastRemoteObject implements DatabaseInterfaceTorneo{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
        
    /**
     *
     * @throws RemoteException
     */
    public ManagerTorneo() throws RemoteException {
            
    }

    @Override
    public void putTorneo(String nome, int annoTorneo) throws RemoteException {
        try{
            query= "INSERT INTO TORNEO\n"
                    + " VALUES ( '" + nome + "' , '" + annoTorneo +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateNomeTorneo(String nome, int annoTorneo, String nuovoNome) throws RemoteException {
        try{
            query= "UPDATE TORNEO\n "
                    + "SET NOMETORNEO = '" + nuovoNome + "'\n "
                    + "WHERE NOMETORNEO = '" + nome + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateAnnoTorneo(String nome, int annoTorneo, int nuovoAnno) throws RemoteException {
        try{
            query= "UPDATE TORNEO\n "
                    + "SET ANNOTORNEO = '" + nuovoAnno + "'\n "
                    + "WHERE NOMETORNEO = '" + nome + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

//    @Override
//    public ArrayList<Torneo> getTorneo() throws RemoteException {
//        
//    }
    
    @Override
    public ArrayList<Goal> getGoalTorneo(String nomeTorneo, int annoTorneo) throws RemoteException {
        ArrayList<Goal> goal = new ArrayList<>();
        
        try{
            query = "SELECT * FROM GOAL JOIN GIOCATORE\n "
                    + "WHERE NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "'\n"
                    + "ORDER BY NOMESQUADRA, NUMEROGIOCATORE;";
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
    
    public ArrayList<Cartellino> getCartellinoTorneo(String nomeTorneo, int annoTorneo) throws RemoteException {
        ArrayList<Cartellino> cartellino = new ArrayList<>();
        
        try{
            query = "SELECT * FROM CARTELLINO JOIN GIOCATORE\n "
                    + "WHERE NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "'\n "
                    + "ORDER BY NOMESQUADRA, NUMEROGIOCATORE;";
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
    
    @Override
    public void deleteTorneo(String nome, int annoTorneo) throws RemoteException {
        try{
            query= "DELETE FROM TORNEO\n "
                    + "WHERE NOMETORNEO = '" + nome + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
}