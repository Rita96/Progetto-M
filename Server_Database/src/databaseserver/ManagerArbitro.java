/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import databaseinterface.DatabaseInterfaceArbitro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import torneo.Arbitro;

/**
 * Questa classe rappresenta ciò che verrà messo a disposizione nel registro per agire 
 * sulla tabella ARBITRO del database
 * @author nautilus
 */
public class ManagerArbitro extends UnicastRemoteObject implements DatabaseInterfaceArbitro {
        
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
    
    /**
     * Crea un nuovo oggetto ManagerArbitro dal qaule sarà possibile effettuare 
     * la chiamata da remoto dei metodi da esso contenuti
     * @throws RemoteException 
     */
    public ManagerArbitro() throws RemoteException{
        
    }
    
    /**
     * Inserisce una tupla nella tabella ARBITRO contenente come valori
     * i parametri in ingresso al metodo 
     * @param codiceFiscale
     * @param nomeArbitro
     * @param cognomeArbitro
     * @param password
     * @throws RemoteException 
     */
    @Override
    public void putArbitro(String codiceFiscale, String nomeArbitro, String cognomeArbitro, String password) throws RemoteException {
        try{
            query= "INSERT INTO ARBITRO\n "
                    + "VALUES ( '" + codiceFiscale + "' , '" + nomeArbitro + "' , '" + cognomeArbitro + "' , '" + password +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna il codice fiscale di una arbitro dove:
     * @param codiceFiscale è parte di chiave
     * @param nuovoCf contiene il nuovo codice fiscale
     * @throws RemoteException 
     */
    @Override
    public void updateCfArbitro(String codiceFiscale, String nuovoCf) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET CFARBITRO = '" + nuovoCf + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna il nome di una arbitro dove:
     * @param codiceFiscale è parte di chiave
     * @param nuovoNome contiene il nuovo nome
     * @throws RemoteException 
     */
    @Override
    public void updateNomeArbitro(String codiceFiscale, String nuovoNome) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET NOMEARBITRO = '" + nuovoNome + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna il cognome di una arbitro dove:
     * @param codiceFiscale è parte di chiave
     * @param nuovoCognome contiene il nuovo cognome
     * @throws RemoteException 
     */
    @Override
    public void updateCognomeArbitro(String codiceFiscale, String nuovoCognome) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET COGNOMEARBITRO = '" + nuovoCognome + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna lo username di un arbitro dove:
     * @param codiceFiscale è parte di chiave
     * @param nuovoUsername contiene il nuovo username
     * @throws RemoteException 
     */
    @Override
    public void updateUsernameArbitro(String codiceFiscale, String nuovoUsername) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET USERNAME = '" + nuovoUsername + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    /**
     * Aggiorna la pasword di un arbitro dove:
     * @param codiceFiscale è parte di chiave
     * @param nuovaPassword  contiene la nuova password
     * @throws RemoteException 
     */
    @Override
    public void updatePasswordArbitro(String codiceFiscale, String nuovaPassword) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET PASSWORD = '" + nuovaPassword + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }
    
    /**
     * Restituisce tutti gli arbitri
     * @return
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Arbitro> getArbitro() throws RemoteException {
        ArrayList<Arbitro> arbitro = new ArrayList<>();
        
        try{
            query = "SELECT * FROM ARBITRO;";
            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
            resSet = statement.executeQuery();
           
            while(resSet.next()){
                Arbitro addArbitro = new Arbitro(resSet.getString("NOMEARBITRO"), resSet.getString("COGNOMEARBITRO"), resSet.getString("CFARBITRO"), resSet.getString("PASSWORD"), false);
                arbitro.add(addArbitro);
                resSet.next();
            }
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
        return arbitro;
    }
    
    /**
     * Elimina una tupla in ARBITRO dove i seguenti parametri sono parte di chiave:
     * @param arbitro
     * @throws RemoteException 
     */
    @Override
    public void deleteArbitro(Arbitro arbitro) throws RemoteException {
        try{
            query= "DELETE FROM ARBITRO\n "
                    + "WHERE CFARBITRO = '" + arbitro.getCodiceFiscale() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

}
