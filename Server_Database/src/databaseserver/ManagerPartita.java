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
 * Questa classe rappresenta ciò che verrà messo a disposizione nel registro per agire 
 * sulla tabella PARTITA del database
 * @author nautilus
 */
public class ManagerPartita extends UnicastRemoteObject implements DatabaseInterfacePartita{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
        
    /**
     * Crea un nuovo oggetto ManagerPartita dal qaule sarà possibile effettuare 
     * la chiamata da remoto dei metodi da esso contenuti
     * @throws RemoteException 
     */
    public ManagerPartita() throws RemoteException {
            
    }
    
    /**
     * Inserisce una tupla nella tabella PARTITA contenente come valori
     * i parametri in ingresso al metodo 
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
     * Inserisce una tupla nella tabella PARTITA contenente come valori
     * i parametri in ingresso al metodo
     * @param idPartita
     * @param squadraCasa
     * @param squadraOspite
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
    
    /**
     * Inserisce una tupla nella tabella PARTITA contenente come valori
     * i parametri in ingresso al metodo
     * @param idPartita
     * @param squadraCasa
     * @param squadraOspite
     * @param statoPartita
     * @param cfArbitro
     * @param nomeTorneo
     * @param annoTorneo
     * @param cittaPartita
     * @throws RemoteException 
     */
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
     * Aggiorna l'ID della partita in una tupla dove:
     * @param idPartita è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoId contiene il nuovo ID
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
     * Aggiorna il nome della squadra che gioca in casa partita in una tupla dove:
     * @param idPartita è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoNomeSquadraCasa contiene il nuovo nome
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
     * Aggiorna il nome della squadra che gioca fuori casa partita in una tupla dove:
     * @param idPartita è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoNomeSquadraOspite contiene il nuovo nome
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
     * Aggiorna i goal della squadra che gioca in casa partita in una tupla dove:
     * @param idPartita è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoGoalCasa contiene i nuovi goal
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
     * Aggiorna i goal della squadra che gioca fuori casa partita in una tupla dove:
     * @param idPartita è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoGoalOspite contiene i nuovi goal
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
     * Aggiorna la data della partita in una tupla dove:
     * @param idPartita è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovaData contiene la nuova data
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
     * Aggiorna lo stato della partita in una tupla dove:
     * @param idPartita è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovaStatoPartita contiene il nuovo stato
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
     * Aggiorna il codice fiscale dell'arbitro della partita in una tupla dove:
     * @param idPartita è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoCfArbitro contiene il nuovo codice fiscale
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
     * Aggiorna il nome del torneo della partita in una tupla dove:
     * @param idPartita è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoNomeTorneo contiene il nuovo nome
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
     * Aggiorna l'anno del torneo della partita in una tupla dove:
     * @param idPartita è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoAnnoTorneo contiene il nuovo anno
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
    
    /**
     * Aggiorna la città dove si svolge la partita in una tupla dove:
     * @param idPartita è parte di chiave
     * @param nomeTorneo è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovaCitta contiene la nuova città
     * @throws RemoteException 
     */
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
     * Restituisce tutti i goal fatti in una partita
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
     * Restituisce tutti i cartellini ricevuti in una partita
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
     * Restituisce l'arbitro della partita
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
     * Elimina una tupla in PARTITA dove i seguenti parametri sono parte di chiave:
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
