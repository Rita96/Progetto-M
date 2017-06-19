/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import databaseinterface.DatabaseInterfaceTorneoItaliana;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import torneo.Arbitro;
import torneo.Giocatore;
import torneo.Italiana;
import torneo.Partita;
import torneo.Squadra;
import torneo.StatoPartita;

/**
 *
 * @author nautilus
 */
public class ManagerTorneoItaliana extends UnicastRemoteObject implements DatabaseInterfaceTorneoItaliana{
       
        private static String query;       //where the query is written
        private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
        private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
        
        public ManagerTorneoItaliana() throws RemoteException {
            
        }
        
        @Override
        public void putTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, int annoTorneo) throws RemoteException {
            try{
                query= "INSERT INTO TORNEO_ITALIANA\n "
                        + "VALUES ( '" +  nomeSquadra + "' , '" + cittaSquadra + "' , '" + punti + "' , '" + nomeTorneo + "' , '" + annoTorneo + "' );";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            } 
        }
        
        @Override
        public void updatePuntiTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, int annoTorneo, int nuoviPunti) throws RemoteException {
            try{
                query= "UPDATE TORNEO_ITALIANA\n "
                        + "SET PUNTI = '" + nuoviPunti + "'\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND PUNTI = '" + punti + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            }
        }

        @Override
        public void updateNomeTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, int annoTorneo, String nuovoNome) throws RemoteException {
            try{
                query= "UPDATE TORNEO_ITALIANA\n "
                        + "SET NOMETORNEO = '" + nuovoNome + "'\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND PUNTI = '" + punti + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            }
        }

        @Override
        public void updateAnnoTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, int annoTorneo, int nuovoAnno) throws RemoteException {
            try{
                query= "UPDATE TORNEO_ITALIANA\n "
                        + "SET ANNOTORNEO = '" + annoTorneo + "'\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND PUNTI = '" + punti + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            }
        }

        @Override
        public void updateNomeSquadraTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, int annoTorneo, String nuovoNomeSquadra) throws RemoteException {
            try{
                query= "UPDATE TORNEO_ITALIANA\n "
                        + "SET NOMESQUADRA = '" + nuovoNomeSquadra + "'\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND PUNTI = '" + punti + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            }
        }

        @Override
        public void updateCittaSquadraTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, int annoTorneo, String nuovaCittaSquadra) throws RemoteException {
            try{
                query= "UPDATE TORNEO_ITALIANA\n "
                        + "SET CITTASQUADRA = '" + nuovaCittaSquadra + "'\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND PUNTI = '" + punti + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            }
        }
        
        @Override
        public Map<String,Integer> getTorneoItaliana(String nomeTorneo, int annoTorneo) throws RemoteException {
            Map<String, Integer> SquadraPunteggioRelativo = new HashMap<>();
        
            try{
                query = "SELECT * FROM TORNEO_ITALIANA\n "
                        + "WHERE NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
                resSet = statement.executeQuery();

                while(resSet.next()){
                    SquadraPunteggioRelativo.put(resSet.getString("NOMESQUADRA"), resSet.getInt("PUNTI"));
                    resSet.next();
                }
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            } 
            return SquadraPunteggioRelativo;    
        }
        
        @Override
        public void deleteTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, int annoTorneo) throws RemoteException {
            try{
                query= "DELETE FROM TORNEO_ITALIANA\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND PUNTI = '" + punti + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo +"' ;";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            } 
        }
        
        private ArrayList<Partita> getPartitaTorneo(String nomeTorneo, int annoTorneo) throws RemoteException {
            ArrayList<Squadra> squadra = getSquadra();
            ArrayList<Partita> partita = new ArrayList<>();
            Squadra squadraCasa = null;
            Squadra squadraOspite = null;

            try{
                query = "SELECT * FROM PARTITA\n "
                        + "WHERE NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
                resSet = statement.executeQuery();

                while(resSet.next()){
                    int i = 0;
                    boolean squadraCasaFound = false, squadraOspiteFound = false;

                    while(!squadraCasaFound || !squadraOspiteFound){
                        Squadra squadraConfronto = squadra.get(i);
                        if(squadraConfronto.getNome().equals(resSet.getString("NOMESQUADRACASA"))){
                            squadraCasa = squadraConfronto;
                            squadraCasaFound = true;
                        }
                        if(squadraConfronto.getNome().equals(resSet.getString("NOMESQUADRAOSPITE"))){
                            squadraOspite = squadraConfronto;
                            squadraOspiteFound = true;
                        }
                        i++;
                    }
                    Partita addPartita = new Partita(resSet.getInt("IDPARTITA"), squadraCasa, squadraOspite, getArbitroPartita(resSet.getInt("IDPARTITA")), resSet.getString("CITTAPARTITA"), StatoPartita.valueOf(resSet.getString("STATOPARTITA")), nomeTorneo, annoTorneo, false);
                    partita.add(addPartita);
                    resSet.next();
                }
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            } 
        
            return partita;
        }
    
        
        private ArrayList<Giocatore> getGiocatoreSquadra(String nomeSquadra, String cittaSquadra) throws RemoteException {
            ArrayList<Giocatore> giocatore = new ArrayList<>();

            try{
                query = "SELECT * FROM GIOCATORE\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra +"' ;" ;
                PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
                resSet = statement.executeQuery();

                while(resSet.next()){
                    Giocatore addGiocatore = new Giocatore(resSet.getString("NOMEGIOCATORE"), resSet.getString("COGNOMEGIOCATORE"), resSet.getInt("NUMEROGIOCATORE"));
                    giocatore.add(addGiocatore);
                    resSet.next();
                }
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            } 
            return giocatore;
        }
    
        private ArrayList<Squadra> getSquadra() throws RemoteException {
            ArrayList<Squadra> squadra = new ArrayList<>();

            try{
                query = "SELECT * FROM SQUADRA;";
                PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
                resSet = statement.executeQuery();

                while(resSet.next()){
                    Squadra addSquadra = new Squadra(resSet.getString("NOMESQUADRA"), resSet.getString("CITTASQUADRA"), resSet.getString("COLORESQUADRA"), getGiocatoreSquadra(resSet.getString("NOMESQUADRA"), resSet.getString("CITTASQUADRA")), false);
                    squadra.add(addSquadra);
                    resSet.next();
                }
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            } 
            return squadra;
        }

        private Arbitro getArbitroPartita(int idPartita) throws RemoteException {
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
}