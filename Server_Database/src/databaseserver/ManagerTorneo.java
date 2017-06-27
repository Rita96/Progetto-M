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
import torneo.*;

/**
 * Questa classe rappresenta ciò che verrà messo a disposizione nel registro per agire 
 * sulla tabella TORNEO del database
 * @author nautilus
 */
public class ManagerTorneo extends UnicastRemoteObject implements DatabaseInterfaceTorneo{
    
    private static String query;       //where the query is written
    private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
    private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
        
    /**
     * Crea un nuovo oggetto ManagerTorneo dal qaule sarà possibile effettuare 
     * la chiamata da remoto dei metodi da esso contenuti
     * @throws RemoteException 
     */
    public ManagerTorneo() throws RemoteException {
            
    }
    
    /**
     * Inserisce una tupla nella tabella TORNEO contenente come valori
     * i parametri in ingresso al metodo 
     * @param nome
     * @param annoTorneo
     * @throws RemoteException 
     */
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
    
    /**
     * Aggiorna il nome del torneo in una tupla dove:
     * @param nome è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoNome contiene il nuovo nome
     * @throws RemoteException 
     */
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
    
    /**
     * Aggiorna l'anno del torneo in una tupla dove:
     * @param nome è parte di chiave
     * @param annoTorneo è parte di chiave
     * @param nuovoAnno contiene il nuovo anno
     * @throws RemoteException 
     */
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
//    public ArrayList<Torneo>getTorneo() throws RemoteException{
//        //ArrayList<Torneo> torneoed = getTorneoEliminazionediretta();
//        ArrayList<Torneo> torneoit = getTorneoItaliana();
//        ArrayList<Torneo> torneo = new ArrayList<>();
//        
//        //torneo.addAll(torneoed);
//        torneo.addAll( torneoit);
//        
//        return torneo;
//    }
    
    /**
     * Restituisce tutti i goal fatti in un torneo
     * @param nomeTorneo
     * @param annoTorneo
     * @return
     * @throws RemoteException 
     */
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
    
    /**
     * Restituisce tutti i cartellini ricevuti in un torneo
     * @param nomeTorneo
     * @param annoTorneo
     * @return
     * @throws RemoteException 
     */
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
     * Elimina una tupla in TORNEO dove i seguenti parametri sono parte di chiave:
     * @param nome
     * @param annoTorneo
     * @throws RemoteException 
     */
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

// CODICE ATTUALMENTE INUTILE
//    private ArrayList<Giocatore> getGiocatoreSquadra(String nomeSquadra, String cittaSquadra) throws RemoteException {
//        ArrayList<Giocatore> giocatore = new ArrayList<>();
//        
//        try{
//            query = "SELECT * FROM GIOCATORE\n "
//                    + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra +"' ;" ;
//            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
//            resSet = statement.executeQuery();
//           
//            while(resSet.next()){
//                Giocatore addGiocatore = new Giocatore(resSet.getString("NOMEGIOCATORE"), resSet.getString("COGNOMEGIOCATORE"), resSet.getInt("NUMEROGIOCATORE"));
//                giocatore.add(addGiocatore);
//                resSet.next();
//            }
//        }catch(SQLException ex){
//            System.out.println("ERROR:" + ex);
//        } 
//        return giocatore;
//    }
//    
//    private ArrayList<Squadra> getSquadra() throws RemoteException {
//        ArrayList<Squadra> squadra = new ArrayList<>();
//        
//        try{
//            query = "SELECT * FROM SQUADRA;";
//            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
//            resSet = statement.executeQuery();
//           
//            while(resSet.next()){
//                Squadra addSquadra = new Squadra(resSet.getString("NOMESQUADRA"), resSet.getString("CITTASQUADRA"), resSet.getString("COLORESQUADRA"), getGiocatoreSquadra(resSet.getString("NOMESQUADRA"), resSet.getString("CITTASQUADRA")), false);
//                squadra.add(addSquadra);
//                resSet.next();
//            }
//        }catch(SQLException ex){
//            System.out.println("ERROR:" + ex);
//        } 
//        return squadra;
//    }
//    
//    private Arbitro getArbitroPartita(int idPartita) throws RemoteException {
//        Arbitro arbitro = null;
//        
//        try{
//            query = "SELECT * FROM PARTITA JOIN ARBITRO\n "
//                    + "WHERE IDPARTITA = '" + idPartita +"' ;";
//            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
//            resSet = statement.executeQuery();
//            arbitro = new Arbitro(resSet.getString("NOMEARBITRO"), resSet.getString("COGNOMEARBITRO"), resSet.getString("CFARBITRO"), resSet.getString("PASSWORD"), false);
//        }catch(SQLException ex){
//            System.out.println("ERROR:" + ex);
//        } 
//        return arbitro;
//    }
//    
//    //NON È UGUALE AL METODO CHE C'È IN ManagerTorneoEliminazionediretta
//    private ArrayList<Torneo> getTorneoEliminazionediretta() throws RemoteException {
//         ArrayList<Torneo> torneo = new ArrayList<>();
//        
//        try{
//            query = "SELECT * FROM TORNEO_ELIMINAZIONEDIRETTA;";
//            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
//            resSet = statement.executeQuery();
//           
//            while(resSet.next()){
//                Torneo addTorneo = new EliminazioneDiretta(resSet.getString("NOMETORNEO"), resSet.getInt("ANNOTORNEO"), getPartitaTorneoEliminazionediretta(resSet.getString("NOMETORNEO"), resSet.getInt("ANNOTORNEO")), false);
//                torneo.add(addTorneo);
//                resSet.next();
//            }
//        }catch(SQLException ex){
//            System.out.println("ERROR:" + ex);
//        } 
//        return torneo;    
//    }
//    
//    //NON È UGUALE AL METODO CHE C'È IN ManagerTorneoEliminazionediretta
//    private ArrayList<Partita> getPartitaTorneoEliminazionediretta(String nomeTorneo, int annoTorneo) throws RemoteException {
//        ArrayList<Squadra> squadra = getSquadra();
//        ArrayList<Partita> partita = new ArrayList<>();
//        Squadra squadraCasa = null;
//        Squadra squadraOspite = null;
//        
//        try{
//            query = "SELECT * FROM PARTITA\n "
//                    + "WHERE NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
//            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
//            resSet = statement.executeQuery();
//           
//            while(resSet.next()){
//                int i = 0;
//                boolean squadraCasaFound = false, squadraOspiteFound = false;
//                
//                while(!squadraCasaFound || !squadraOspiteFound){
//                    Squadra squadraConfronto = squadra.get(i);
//                    if(squadraConfronto.getNome().equals(resSet.getString("NOMESQUADRACASA"))){
//                        squadraCasa = squadraConfronto;
//                        squadraCasaFound = true;
//                    }
//                    if(squadraConfronto.getNome().equals(resSet.getString("NOMESQUADRAOSPITE"))){
//                        squadraOspite = squadraConfronto;
//                        squadraOspiteFound = true;
//                    }
//                    i++;
//                }
//                Partita addPartita = new PartitaEliminazioneDiretta(resSet.getInt("IDPARTITA"), squadraCasa, squadraOspite, getArbitroPartita(resSet.getInt("IDPARTITA")), resSet.getString("CITTAPARTITA"), StatoPartita.valueOf(resSet.getString("STATOPARTITA")), nomeTorneo, annoTorneo, false);
//                partita.add(addPartita);
//                resSet.next();
//            }
//        }catch(SQLException ex){
//            System.out.println("ERROR:" + ex);
//        } 
//        
//        return partita;
//    }
//    
//     //NON È UGUALE AL METODO CHE C'È IN ManagerTorneoItaliana
//    private ArrayList<Torneo> getTorneoItaliana() throws RemoteException {
//         ArrayList<Torneo> torneo = new ArrayList<>();
//        
//        try{
//            query = "SELECT * FROM TORNEO_ELIMINAZIONEDIRETTA;";
//            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
//            resSet = statement.executeQuery();
//           
//            while(resSet.next()){
//                Torneo addTorneo = new Italiana(resSet.getString("NOMETORNEO"), resSet.getInt("ANNOTORNEO"), getPartitaTorneoEliminazionediretta(resSet.getString("NOMETORNEO"), resSet.getInt("ANNOTORNEO")), false);
//                torneo.add(addTorneo);
//                resSet.next();
//            }
//        }catch(SQLException ex){
//            System.out.println("ERROR:" + ex);
//        } 
//        return torneo;    
//    }
//    
//    //NON È UGUALE AL METODO CHE C'È IN ManagerTorneoItaliana
//    private ArrayList<Partita> getPartitaItaliana(String nomeTorneo, int annoTorneo) throws RemoteException {
//        ArrayList<Squadra> squadra = getSquadra();
//        ArrayList<Partita> partita = new ArrayList<>();
//        Squadra squadraCasa = null;
//        Squadra squadraOspite = null;
//        
//        try{
//            query = "SELECT * FROM PARTITA\n "
//                    + "WHERE NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
//            PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
//            resSet = statement.executeQuery();
//           
//            while(resSet.next()){
//                int i = 0;
//                boolean squadraCasaFound = false, squadraOspiteFound = false;
//                
//                while(!squadraCasaFound || !squadraOspiteFound){
//                    Squadra squadraConfronto = squadra.get(i);
//                    if(squadraConfronto.getNome().equals(resSet.getString("NOMESQUADRACASA"))){
//                        squadraCasa = squadraConfronto;
//                        squadraCasaFound = true;
//                    }
//                    if(squadraConfronto.getNome().equals(resSet.getString("NOMESQUADRAOSPITE"))){
//                        squadraOspite = squadraConfronto;
//                        squadraOspiteFound = true;
//                    }
//                    i++;
//                }
//                Partita addPartita = new PartitaItaliana(resSet.getInt("IDPARTITA"), squadraCasa, squadraOspite, getArbitroPartita(resSet.getInt("IDPARTITA")), resSet.getString("CITTAPARTITA"), StatoPartita.valueOf(resSet.getString("STATOPARTITA")), nomeTorneo, annoTorneo, false);
//                partita.add(addPartita);
//                resSet.next();
//            }
//        }catch(SQLException ex){
//            System.out.println("ERROR:" + ex);
//        } 
//        
//        return partita;
//    }
    
}
