/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*; 
import static database.DatabaseConnection.statement;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author nautilus
 */
public class DatabaseManagement extends UnicastRemoteObject implements DatabaseInterface{
    
    private  static String query;       
    private static ResultSet resSet;    //object needed to execute queries
    
    public DatabaseManagement ()throws RemoteException{
        
    }
        
    @Override
    public String getQuery(){
        try{
            query= "SELECT * FROM PARTITA";
            resSet = statement.executeQuery(query);
            System.out.println("RISULTATO QUERY:");
        }catch(Exception ex){
            System.out.println("ERROR:" + ex);
        }
        return query;
    }

    @Override
    public void setToreo(String nome, Date data) throws RemoteException {
        try{
            query= "INSERT INTO TORNEO\n VALUES (" + nome.toUpperCase() + "," + data +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void setLogin(String username, String password) throws RemoteException {
        try{
            query= "INSERT INTO LOGIN\n VALUES (" + username + "," + password +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }    
    }

    @Override
    public void setGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO GOAL\n VALUES (" + idPartita + "," + numeroGiocatore + "," + nomeSquadra.toUpperCase() + "," + cittaSquadra.toUpperCase() +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO CARTELLINO\n VALUES (" + idPartita + "," + colore.toUpperCase() + "," + minuto + "," + numeroGiocatore + "," + nomeSquadra.toUpperCase() + "," + cittaSquadra.toUpperCase() +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setArbitro(String codiceFiscale) throws RemoteException {
        try{
            query= "INSERT INTO ARBITRO (CFARBITRO)\n VALUES (" + codiceFiscale.toUpperCase() +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setArbitro(String codiceFiscale, String nome, String cognome, String username, String password) throws RemoteException {
        try{
            query= "INSERT INTO ARBITRO\n VALUES (" + codiceFiscale.toUpperCase() + "," + nome.toUpperCase() + "," + cognome.toUpperCase() + "," + username + "," + password +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setOrganizzatore(int idOrganizzatore) throws RemoteException {
        try{
            query= "INSERT INTO ORGANIZZATORE (IDORGANIZZATORE)\n VALUES (" + idOrganizzatore + ");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setOrganizzatore(int idOrganizzatore, String username, String password) throws RemoteException {
        try{
            query= "INSERT INTO ORGANIZZATORE\n VALUES (" + idOrganizzatore + "," + username + "," + password +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setPartita(int idPartita) throws RemoteException {
        try{
            query= "INSERT INTO PARTITA (IDPARTITA)\n VALUES (" + idPartita + ");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setPartita(int idPartita, String squadraCasa, String squadraOspite, Date data, String andataritorno, String cfArbitro) throws RemoteException {
        try{
            query= "INSERT INTO PARTITA\n VALUES (" + idPartita + "," + squadraCasa.toUpperCase() + "," + squadraOspite.toUpperCase() + "," + data + "," + andataritorno.toUpperCase() + "," + cfArbitro.toUpperCase() +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setPartita(int idPartita, String squadraCasa, String squadraOspite, Date data, String cfArbitro) throws RemoteException {
        try{
            query= "INSERT INTO PARTITA (IDPARTITA, SQUADRACASA, SQUADRAOSPITE, DATA, CFARBITRO)\n VALUES (" + idPartita + "," + squadraCasa.toUpperCase() + "," + squadraOspite.toUpperCase() + "," + data + "," + cfArbitro.toUpperCase() +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setGiocatore(int numero, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO GIOCATORE (NUMERO, NOMESQUADRA, CITTASQUADRA)\n VALUES (" + numero + "," + nomeSquadra.toUpperCase() + "," + cittaSquadra.toUpperCase() +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setGiocatore(int numero, String nomeGiocatore, String cognomeGiocatore, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO GIOCATORE\n VALUES (" + numero + "," + nomeGiocatore.toUpperCase() + "," + cognomeGiocatore.toUpperCase() + ","  + nomeSquadra.toUpperCase() + "," + cittaSquadra.toUpperCase() +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setSquadra(String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO SQUADRA (NOMESQUADRA, CITTASQUADRA)\n VALUES (" +  nomeSquadra.toUpperCase() + "," + cittaSquadra.toUpperCase() + "," + ");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setSquadra(String nomeSquadra, String cittaSquadra, String coloreSquadra) throws RemoteException {
        try{
            query= "INSERT INTO SQUADRA\n VALUES (" +  nomeSquadra.toUpperCase() + "," + cittaSquadra.toUpperCase() + "," + coloreSquadra.toUpperCase() +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setSquadraCasa(String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO SQUADRACASA\n VALUES (" +  nomeSquadra.toUpperCase() + "," + cittaSquadra.toUpperCase() + "," + coloreSquadra.toUpperCase() +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setSquadraOspite(String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO SQUADRAOSPITE\n VALUES (" +  nomeSquadra.toUpperCase() + "," + cittaSquadra.toUpperCase() + "," +");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void setTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo) throws RemoteException {
         try{
            query= "INSERT INTO TORNEO_ITALIANA\n VALUES (" +  nomeSquadra.toUpperCase() + "," + cittaSquadra.toUpperCase() + "," + punti + "," + nomeTorneo.toUpperCase() + "," + annoTorneo.toUpperCase() + ");";
            resSet = statement.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

}
