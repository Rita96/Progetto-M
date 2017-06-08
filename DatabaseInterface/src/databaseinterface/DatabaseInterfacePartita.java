/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import torneo.Arbitro;
import torneo.Cartellino;
import torneo.Goal;
import torneo.Partita;

/**
 *
 * @author nautilus
 */
public interface DatabaseInterfacePartita extends Remote{
    
    void putPartita(int idPartita, String nomeTorneo, int annoTorneo) throws RemoteException;
    void putPartita(int idPartita, String squadraCasa, String squadraOspite, String statoPartita, String cfArbitro, String nomeTorneo, int annoTorneo, String cittaPartita) throws RemoteException;
    void putPartita(int idPartita, String squadraCasa, String squadraOspite, String cfArbitro, String nomeTorneo, int annoTorneo) throws RemoteException;
  
    void updateIdPartita(int idPartita, String nomeTorneo, int annoTorneo, int nuovoId) throws RemoteException;
    void updateNomeSquadraCasaPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovoNomeSquadraCasa) throws RemoteException;
    void updateNomeSquadraOspitePartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovonomeSquadraOspite) throws RemoteException;
    void updateGoalCasaPartita(int idPartita, String nomeTorneo, int annoTorneo, int nuovoGoalCasa) throws RemoteException;
    void updateGoalOspitePartita(int idPartita, String nomeTorneo, int annoTorneo, int nuovoGoalOspite) throws RemoteException;
    void updateDataPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovaData) throws RemoteException;
    void updateStatoPartitaPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovoStatoPartita) throws RemoteException;
    void updateCfArbitroPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovoCfArbitro) throws RemoteException;
    void updateNomeTorneoPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovoNomeTorneo) throws RemoteException;
    void updateAnnoTorneoPartita(int idPartita, String nomeTorneo, int annoTorneo, int nuovoAnnoTorneo) throws RemoteException;
    void updateCittaPartita(int idPartita, String nomeTorneo, int annoTorneo, String nuovaCitta) throws RemoteException;
    
    ArrayList<Goal> getGoalPartita(int idPartita) throws RemoteException;
    ArrayList<Arbitro> getArbitroPartita(int idPartita) throws RemoteException;
    ArrayList<Cartellino> getCartellinoPartita(int idPartita) throws RemoteException;
    
    void deletePartita(int idPartita, String nomeTorneo, int annoTorneo) throws RemoteException;
}
