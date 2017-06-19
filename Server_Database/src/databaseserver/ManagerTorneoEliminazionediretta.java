/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import databaseinterface.DatabaseInterfaceTorneo;
import databaseinterface.DatabaseInterfaceTorneoEliminazionediretta;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import torneo.Arbitro;
import torneo.EliminazioneDiretta;
import torneo.Giocatore;
import torneo.Partita;
import torneo.Squadra;
import torneo.StatoPartita;

/**
 *
 * @author nautilus
 */
public class ManagerTorneoEliminazionediretta extends UnicastRemoteObject implements DatabaseInterfaceTorneoEliminazionediretta{
    
        private static String query;       //where the query is written
        private static ResultSet resSet;    //object needed to execute queries, and where the result of queries will be
        private static ResultSetMetaData rsmd;  //object needed mainly to know the number of columns given by a certain query
        
        public ManagerTorneoEliminazionediretta() throws RemoteException {
            
        }
        
        @Override
        public void putTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo) throws RemoteException {
            try{
                query= "INSERT INTO TORNEO_ELIMINAZIONEDIRETTA (NOMESQUADRA, CITTASQUADRA, FASETORNEO, NOMETORNEO, ANNOTORNEO)\n "
                        + "VALUES ( '" +  nomeSquadra + "' , '" + cittaSquadra + "' , '" + faseTorneo + "' , '" + nomeTorneo + "' , '" + annoTorneo + "' );";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            } 
        }
        
        @Override
        public void putTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, int passaggioFase, String nomeTorneo, int annoTorneo) throws RemoteException {
            try{
                query= "INSERT INTO TORNEO_ELIMINAZIONEDIRETTA\n "
                        + "VALUES ( '" +  nomeSquadra + "' , '" + cittaSquadra + "' , '" + faseTorneo + "' , '" + passaggioFase + "' , '" + nomeTorneo + "' , '" + annoTorneo + "' );";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            } 
        }
        
        @Override
        public void updateFaseTorneoTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo, int nuoviPunti) throws RemoteException {
            try{
                query= "UPDATE TORNEO_ELIMINAZIONEDIRETTA\n "
                        + "SET FASETORNEO = '" + nuoviPunti + "'\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND FASETORNEO = '" + faseTorneo + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            }
        }

        @Override
        public void updateNomeTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo, String nuovoNome) throws RemoteException {
            try{
                query= "UPDATE TORNEO_ELIMINAZIONEDIRETTA\n "
                        + "SET NOMETORNEO = '" + nuovoNome + "'\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND FASETORNEO = '" + faseTorneo + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            }
        }

        @Override
        public void updateAnnoTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo, int nuovoAnno) throws RemoteException {
            try{
                query= "UPDATE TORNEO_ELIMINAZIONEDIRETTA\n "
                        + "SET ANNOTORNEO = '" + annoTorneo + "'\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND FASETORNEO = '" + faseTorneo + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            }
        }

        @Override
        public void updateNomeSquadraTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo, String nuovoNomeSquadra) throws RemoteException {
            try{
                query= "UPDATE TORNEO_ELIMINAZIONEDIRETTA\n "
                        + "SET NOMESQUADRA = '" + nuovoNomeSquadra + "'\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND FASETORNEO = '" + faseTorneo + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            }
        }

        @Override
        public void updateCittaSquadraTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo, String nuovaCittaSquadra) throws RemoteException {
            try{
                query= "UPDATE TORNEO_ELIMINAZIONEDIRETTA\n "
                        + "SET CITTASQUADRA = '" + nuovaCittaSquadra + "'\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND FASETORNEO = '" + faseTorneo + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            }
        }
        
        @Override
        public void updatePassaggioFaseTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo, int nuovoPassaggioFase) throws RemoteException {
            try{
                query= "UPDATE TORNEO_ELIMINAZIONEDIRETTA\n "
                        + "SET PASSAGGIOFASE = '" + nuovoPassaggioFase + "'\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND FASETORNEO = '" + faseTorneo + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement posted = DatabaseConnection.connection.prepareStatement(query);
                posted.executeUpdate(query);
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            }
        }
        
        @Override
        public ArrayList<String> getTorneoEliminazionediretta(String nomeTorneo, int annoTorneo) throws RemoteException {
            ArrayList<String> squadreNelTorneo = new ArrayList<>();
        
            try{
                query = "SELECT * FROM TORNEO_ELIMINAZIONEDIRETTA\n "
                        + "WHERE NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo + "' ;";
                PreparedStatement statement = DatabaseConnection.connection.prepareStatement(query);
                resSet = statement.executeQuery();
                Squadra s = null;
                while(resSet.next()){
                    squadreNelTorneo.add(resSet.getString("NOMESQUADRA"));
                    resSet.next();
                }
            }catch(SQLException ex){
                System.out.println("ERROR:" + ex);
            } 
            return squadreNelTorneo;  
        }
        
        @Override
        public void deleteTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo) throws RemoteException {
            try{
                query= "DELETE FROM TORNEO_ELIMINAZIONEDIRETTA\n "
                        + "WHERE NOMESQUADRA = '" + nomeSquadra + "' AND CITTASQUADRA = '" + cittaSquadra + "' AND FASETORNEO = '" + faseTorneo + "' AND NOMETORNEO = '" + nomeTorneo + "' AND ANNOTORNEO = '" + annoTorneo +"' ;";
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
