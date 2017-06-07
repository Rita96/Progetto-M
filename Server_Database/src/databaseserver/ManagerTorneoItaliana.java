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
        public void updatePuntiTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, int nuoviPunti) throws RemoteException {
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
        public void updateNomeTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, String nuovoNome) throws RemoteException {
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
        public void updateAnnoTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, int nuovoAnno) throws RemoteException {
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
        public void updateNomeSquadraTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, String nuovoNomeSquadra) throws RemoteException {
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
        public void updateCittaSquadraTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, String nuovaCittaSquadra) throws RemoteException {
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
        public String getTorneoItaliana() throws RemoteException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}