/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*; 
import static database.DatabaseConnection.statement;
import databaseinterface.DatabaseInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nautilus
 */
public class DatabaseManagement extends UnicastRemoteObject implements DatabaseInterface{
    
    private  static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query

    
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
    public void putToreo(String nome, int annoTorneo) throws RemoteException {
        try{
            query= "INSERT INTO TORNEO\n"
                    + " VALUES ( '" + nome.toUpperCase() + "' , '" + annoTorneo +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void putLogin(String username, String password) throws RemoteException {
        try{
            query= "INSERT INTO LOGIN\n"
                    + " VALUES ( '" + username + "' , '" + password +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }    
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
    public void putCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "INSERT INTO CARTELLINO\n "
                    + "VALUES ( '" + idPartita + "','" + colore.toUpperCase() + "' , '" + minuto + "' , '" + numeroGiocatore + "' , '" + nomeSquadra.toUpperCase() + "' , '" + cittaSquadra.toUpperCase() + "' , '" + nomeTorneo.toUpperCase() + annoTorneo +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void putArbitro(String codiceFiscale) throws RemoteException {
        try{
            query= "INSERT INTO ARBITRO (CFARBITRO)\n "
                    + "VALUES ( '" + codiceFiscale.toUpperCase() +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void putArbitro(String codiceFiscale, String nome, String cognome, String username, String password) throws RemoteException {
        try{
            query= "INSERT INTO ARBITRO\n "
                    + "VALUES ( '" + codiceFiscale.toUpperCase() + "' , '" + nome.toUpperCase() + "' , '" + cognome.toUpperCase() + "' , '" + username + "' , '" + password +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void putOrganizzatore(int idOrganizzatore) throws RemoteException {
        try{
            query= "INSERT INTO ORGANIZZATORE (IDORGANIZZATORE)\n "
                    + "VALUES ( '" + idOrganizzatore + "' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void putOrganizzatore(int idOrganizzatore, String username, String password) throws RemoteException {
        try{
            query= "INSERT INTO ORGANIZZATORE\n "
                    + "VALUES ( '" + idOrganizzatore + "' , '" + username + "' , '" + password +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
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
    public void putPartita(int idPartita, String squadraCasa, String squadraOspite, Date data, String cfArbitro, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "INSERT INTO PARTITA (IDPARTITA, SQUADRACASA, SQUADRAOSPITE, DATA, CFARBITRO, NOMETORNEO, ANNOTORNEO)\n "
                    + "VALUES ( '" + idPartita + "' , '" + squadraCasa.toUpperCase() + "' , '" + squadraOspite.toUpperCase() + "' , '" + data + "' , '" + cfArbitro.toUpperCase() + "' , '" + nomeTorneo.toUpperCase() + annoTorneo +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void putGiocatore(int numero, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO GIOCATORE (NUMERO, NOMESQUADRA, CITTASQUADRA)\n "
                    + "VALUES ( '" + numero + "' , '" + nomeSquadra.toUpperCase() + "' , '" + cittaSquadra.toUpperCase() +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void putGiocatore(int numero, String nomeGiocatore, String cognomeGiocatore, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO GIOCATORE\n "
                    + "VALUES ( '" + numero + "' , '" + nomeGiocatore.toUpperCase() + "' , '" + cognomeGiocatore.toUpperCase() + "' , '"  + nomeSquadra.toUpperCase() + "' , '" + cittaSquadra.toUpperCase() +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void putSquadra(String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "INSERT INTO SQUADRA (NOMESQUADRA, CITTASQUADRA)\n "
                    + "VALUES ( '" +  nomeSquadra.toUpperCase() + "' , '" + cittaSquadra.toUpperCase() + "' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void putSquadra(String nomeSquadra, String cittaSquadra, String coloreSquadra) throws RemoteException {
        try{
            query= "INSERT INTO SQUADRA\n "
                    + "VALUES ( '" +  nomeSquadra.toUpperCase() + "' , '" + cittaSquadra.toUpperCase() + "' , '" + coloreSquadra.toUpperCase() +"' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void putTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, int annoTorneo) throws RemoteException {
         try{
            query= "INSERT INTO TORNEO_ITALIANA\n "
                    + "VALUES ( '" +  nomeSquadra.toUpperCase() + "' , '" + cittaSquadra.toUpperCase() + "' , '" + punti + "' , '" + nomeTorneo.toUpperCase() + "' , '" + annoTorneo + "' );";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }
    
    @Override
    public Map<Integer,List<String>> getGiocatore(String nomeSquadra, String cittaSquadra) throws RemoteException {
        
        Map<Integer, List<String>> giocatore = new HashMap<Integer, List<String>>();
        List<String> nomecognome = new ArrayList<String>();
        
        try{
            query= "SELECT NOMEGIOCATORE, COGNOMEGIOCATORE, NUMERO FROM GIOCATORE\n "
                    + "WHERE NOMESQUADRA = \"" +  nomeSquadra.toUpperCase() + "\" AND CITTASQUADRA = \"" + cittaSquadra.toUpperCase() + "\" ;";
            resSet = statement.executeQuery(query);
            rsmd = resSet.getMetaData();
            int columnNumber = rsmd.getColumnCount();
            
            do{
                nomecognome.add(resSet.getString(columnNumber-2));   //adding the value of the current row of the column 1
                nomecognome.add(resSet.getString(columnNumber-1));   //adding the value of the current row of the column 2
                giocatore.put(resSet.getInt(columnNumber), nomecognome); //adding the number
                resSet.next();   //moving to the next row
                
            }while(resSet.next());
            
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
        
        return giocatore;
    }

    @Override
    public ArrayList<String> getAllSquadreTorneo(String nomeTorneo, int annoTorneo) throws RemoteException { 
        
        ArrayList<String> squadre = new ArrayList<String>();
        int column = 1;
        
        try{
            query= "SELECT SQUADRACASA FROM PARTITA\n "
                    + "WHERE NOMETORNEO = \"" + nomeTorneo.toUpperCase() + "\" AND ANNOTORNEO = \"" + annoTorneo + "\" ;";
            resSet = statement.executeQuery(query);
            
            do{
               squadre.add(resSet.getString(column));   //getting the value in the current row of the column indicated
               resSet.next();   //moving to the next row
            }while(resSet.next());
 
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
        
        return squadre;
    }

    @Override
    public Map<String, String> getPartita(String nomeTorneo, int annoTorneo) throws RemoteException {
        
        Map<String, String> partita = new HashMap<>();
        int squadraCasaColumn = 2; //column of NOMESQUADRACASA
        int squadraOspiteColumn = 3; //column of NOMESQUADRACASA
        
        try{
             query= "SELECT * FROM PARTITA\n "
                    + "WHERE NOMETORNEO = \"" + nomeTorneo.toUpperCase() + "\" AND ANNOTORNEO = \"" + annoTorneo + "\" ;";
            resSet = statement.executeQuery(query);
            
            do{
               partita.put(resSet.getString(squadraCasaColumn), resSet.getString(squadraOspiteColumn));   //getting the value in the current row of the column indicated
               resSet.next();   //moving to the next row
            }while(resSet.next());
 
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
        return partita;
    }
    
    @Override
    public Map<String, String> getPartita(int idPartita, String nomeTorneo, int annoTorneo) throws RemoteException {
        
        Map<String, String> partita = new HashMap<>();
        int squadraCasaColumn = 2; //column of NOMESQUADRACASA
        int squadraOspiteColumn = 3; //column of NOMESQUADRACASA
        
        try{
            query= "SELECT * FROM PARTITA\n "
                    + "WHERE IDPARTITA = " + idPartita + "\" AND NOMETORNEO = \""  + nomeTorneo.toUpperCase() + "\" AND ANNOTORNEO = \"" + annoTorneo + "\" ;";
            resSet = statement.executeQuery(query);
            
            partita.put(resSet.getString(squadraCasaColumn), resSet.getString(squadraOspiteColumn));   //getting the value in the current row of the column indicated
 
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
        return partita;
    }
    /*@Override
    public ArrayList<Integer> getGoal(int idPartita, String nomeTorneo, int annoTorneo) throws RemoteException {
        
        ArrayList<Integer> goal = new ArrayList<Integer>();
        int column = 2;
        
        try{
             query= "SELECT * FROM CARTELLINO\n "
                    + "WHERE NOMETORNEO = \"" + nomeTorneo.toUpperCase() + "\" AND ANNOTORNEO = \"" + annoTorneo + "\" AND IDPARTITA = \"" + idPartita + "\" ;";
            resSet = statement.executeQuery(query);
            
            do{
               goal.add(resSet.getInt(column));   //getting the value in the current row of the column indicated
               resSet.next();   //moving to the next row
            }while(resSet.next());
 
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
        return goal;
    }*/

    /*@Override
    public ArrayList<Integer> getCartellini(int idPartita, String nomeTorneo, int annoTorneo) throws RemoteException {
        
        ArrayList<Integer> cartellino = new ArrayList<Integer>();
        int column = 3;
        
        try{
             query= "SELECT * FROM CARTELLINO\n "
                    + "WHERE NOMETORNEO = \"" + nomeTorneo.toUpperCase() + "\" AND ANNOTORNEO = \"" + annoTorneo + "\" AND IDPARTITA = \"" + idPartita + "\" ;";
            resSet = statement.executeQuery(query);
            
            do{
               cartellino.add(resSet.getInt(column));   //getting the value in the current row of the column indicated
               resSet.next();   //moving to the next row
            }while(resSet.next());
 
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
        return cartellino;
    }*/

   

    @Override
    public void deleteToreo(String nome, int annoTorneo) throws RemoteException {
        try{
            query= "DELETE FROM TORNEO\n "
                    + "WHERE NOMETORNEO = '" + nome.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void deleteLogin(String username, String password) throws RemoteException {
        try{
            query= "DELETE FROM LOGIN\n "
                    + "WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
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

    @Override
    public void deleteCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "DELETE FROM CARTELLINO\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORENO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void deleteArbitro(String codiceFiscale) throws RemoteException {
        try{
            query= "DELETE FROM ARBITRO\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void deleteOrganizzatore(int idOrganizzatore) throws RemoteException {
        try{
            query= "DELETE FROM ORGANIZZATORE\n "
                    + "WHERE IDORGANIZZATORE = '" + idOrganizzatore + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
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

    @Override
    public void deleteGiocatore(int numero, String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "DELETE FROM GIOCATORE\n "
                    + "WHERE NOMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void deleteSquadra(String nomeSquadra, String cittaSquadra) throws RemoteException {
        try{
            query= "DELETE FROM SQUADRA\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void deleteTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, int annoTorneo) throws RemoteException {
        try{
            query= "DELETE FROM TORNEO_ITALIANA\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND PUNTI = '" + punti + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo +"' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateNomeToreo(String nome, int annoTorneo, String nuovoNome) throws RemoteException {
        try{
            query= "UPDATE TORNEO\n "
                    + "SET NOMETORNEO = '" + nuovoNome.toUpperCase() + "'\n "
                    + "WHERE NOMETORNEO = '" + nome.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateAnnoToreo(String nome, int annoTorneo, int nuovoAnno) throws RemoteException {
        try{
            query= "UPDATE TORNEO\n "
                    + "SET ANNOTORNEO = '" + nuovoAnno + "'\n "
                    + "WHERE NOMETORNEO = '" + nome.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateUsernameLogin(String username, String password, String nuovoUsername) throws RemoteException {
        try{
            query= "UPDATE LOGIN\n "
                    + "SET USERNAME = '" + nuovoUsername + "'\n "
                    + "WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updatePasswordLogin(String username, String password, String nuovaPassword) throws RemoteException {
        try{
            query= "UPDATE LOGIN\n "
                    + "SET PASSWORD = '" + nuovaPassword + "'\n "
                    + "WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "' ;";
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
    public void updateAnnotorneoGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoAnnoTorneo) throws RemoteException {
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
    public void updateIdPartitaCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoIdPartita) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET IDPARTITA = '" + nuovoIdPartita + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateColoreCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovoColore) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET COLORECARTELLINO = '" + nuovoColore.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }  
    }

    @Override
    public void updateMinutoCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoMinuto) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET MINUTO = '" + nuovoMinuto + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateNumeroGiocatoreCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoNumeroGiocatore) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET NUMEROGIOCATORE = '" + nuovoNumeroGiocatore + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateNomeSquadraCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovoNomeSquadra) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET NOMESQUADRA = '" + nuovoNomeSquadra.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateCittaSquadraCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovaCittaSquadra) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET CITTASQUADRA = '" + nuovaCittaSquadra.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateNomeTorneoCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovoNomeTorneo) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET NOMETORNEO = '" + nuovoNomeTorneo.toUpperCase() + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateAnnoTorneoCartellino(int idPartita, String colore, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoAnnoTorneo) throws RemoteException {
        try{
            query= "UPDATE CARTELLINO\n "
                    + "SET ANNOTORNEO = '" + nuovoAnnoTorneo + "'\n "
                    + "WHERE IDPARTITA = '" + idPartita + "' AND COLORECARTELLINO = '" + colore.toUpperCase() + "' AND MINUTO = '" + minuto + "' AND NUMEROGIOCATORE = '" + numeroGiocatore + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateCfArbitro(String codiceFiscale, String nuovoCf) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET CFARBITRO = '" + nuovoCf.toUpperCase() + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateNomeArbitro(String codiceFiscale, String nuovoNome) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET NOMEARBITRO = '" + nuovoNome.toUpperCase() + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateCognomeArbitro(String codiceFiscale, String nuovoCognome) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET COGNOMEARBITRO = '" + nuovoCognome.toUpperCase() + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updateUsernameArbitro(String codiceFiscale, String nuovoUsername) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET USERNAME = '" + nuovoUsername + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        } 
    }

    @Override
    public void updatePasswordArbitro(String codiceFiscale, String nuovaPassword) throws RemoteException {
        try{
            query= "UPDATE ARBITRO\n "
                    + "SET PASSWORD = '" + nuovaPassword + "'\n "
                    + "WHERE CFARBITRO = '" + codiceFiscale.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateIdOrganizzatore(int idOrganizzatore, int nuovoId) throws RemoteException {
        try{
            query= "UPDATE ORGANIZZATORE\n "
                    + "SET IDORGANIZZATORE = '" + nuovoId + "'\n "
                    + "WHERE IDORGANIZZATORE = '" + idOrganizzatore + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateUsernameOrganizzatore(int idOrganizzatore, String nuovoUsername) throws RemoteException {
        try{
            query= "UPDATE ORGANIZZATORE\n "
                    + "SET USERNAME = '" + nuovoUsername + "'\n "
                    + "WHERE IDORGANIZZATORE = '" + idOrganizzatore + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updatePasswordOrganizzatore(int idOrganizzatore, String nuovaPassword) throws RemoteException {
        try{
            query= "UPDATE ORGANIZZATORE\n "
                    + "SET PASSWORD = '" + nuovaPassword + "'\n "
                    + "WHERE IDORGANIZZATORE = '" + idOrganizzatore + "' ;";
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
    public void updateNumeroGiocatore(int numero, String nomeSquadra, String cittaSquadra, int nuovoNumero) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET NUMERO = '" + nuovoNumero + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateNomeGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovoNome) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET NOMEGIOCATORE = '" + nuovoNome.toUpperCase() + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateCognomeGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovoCognome) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET COGNOMEGIOCATORE = '" + nuovoCognome.toUpperCase() + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateNomeSquadraGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovoNomeSquadra) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET NOMESQUADRA = '" + nuovoNomeSquadra.toUpperCase() + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateCittaSquadraGiocatore(int numero, String nomeSquadra, String cittaSquadra, String nuovaCittaSquadra) throws RemoteException {
        try{
            query= "UPDATE GIOCATORE\n "
                    + "SET CITTASQUADRA = '" + nuovaCittaSquadra.toUpperCase() + "'\n "
                    + "WHERE NUMERO = '" + numero + "' AND NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateNomeSquadra(String nomeSquadra, String cittaSquadra, String nuovoNome) throws RemoteException {
        try{
            query= "UPDATE SQUADRA\n "
                    + "SET NOMESQUADRA = '" + nuovoNome.toUpperCase() + "'\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateCittaSquadra(String nomeSquadra, String cittaSquadra, String nuovaCitta) throws RemoteException {
        try{
            query= "UPDATE SQUADRA\n "
                    + "SET CITTASQUADRA = '" + nuovaCitta.toUpperCase() + "'\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateColoreSquadra(String nomeSquadra, String cittaSquadra, String nuovoColore) throws RemoteException {
        try{
            query= "UPDATE SQUADRA\n "
                    + "SET COLORESQUADRA = '" + nuovoColore.toUpperCase() + "'\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updatePuntiTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, int nuoviPunti) throws RemoteException {
        try{
            query= "UPDATE TORNEO_ITALIANA\n "
                    + "SET PUNTI = '" + nuoviPunti + "'\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND PUNTI = '" + punti + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateNomeTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, String nuovoNome) throws RemoteException {
        try{
            query= "UPDATE TORNEO_ITALIANA\n "
                    + "SET NOMETORNEO = '" + nuovoNome.toUpperCase() + "'\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND PUNTI = '" + punti + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateAnnoTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, int nuovoAnno) throws RemoteException {
        try{
            query= "UPDATE TORNEO_ITALIANA\n "
                    + "SET ANNOTORNEO = '" + annoTorneo + "'\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND PUNTI = '" + punti + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateNomeSquadraTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, String nuovoNomeSquadra) throws RemoteException {
        try{
            query= "UPDATE TORNEO_ITALIANA\n "
                    + "SET NOMESQUADRA = '" + nuovoNomeSquadra.toUpperCase() + "'\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND PUNTI = '" + punti + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    @Override
    public void updateCittaSquadraTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, String nuovaCittaSquadra) throws RemoteException {
        try{
            query= "UPDATE TORNEO_ITALIANA\n "
                    + "SET CITTASQUADRA = '" + nuovaCittaSquadra.toUpperCase() + "'\n "
                    + "WHERE NOMESQUADRA = '" + nomeSquadra.toUpperCase() + "' AND CITTASQUADRA = '" + cittaSquadra.toUpperCase() + "' AND PUNTI = '" + punti + "' AND NOMETORNEO = '" + nomeTorneo.toUpperCase() + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
            PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
            posted.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("ERROR:" + ex);
        }
    }

    

}
