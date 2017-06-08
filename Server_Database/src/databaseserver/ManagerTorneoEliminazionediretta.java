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
        
        //updateEliminazioneTorneoEliminazionediretta
        
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
        public String getTorneoEliminazionediretta() throws RemoteException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
