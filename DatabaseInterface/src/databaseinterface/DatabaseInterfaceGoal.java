/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import torneo.Goal;

/**
 *
 * @author nautilus
 */
public interface DatabaseInterfaceGoal extends Remote{
    
    void putGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException;
    
    void updateIdPartitaGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoId) throws RemoteException;
    void updateMinutoGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoMinuto) throws RemoteException;
    void updateNumeroGiocatoreGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoNumero) throws RemoteException;
    void updateNomeSquadraGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovoNomeSquadra) throws RemoteException;
    void updateCittaSquadraGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovaCittaSquadra) throws RemoteException;
    void updateNomeTorneoGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, String nuovoNomeTorneo) throws RemoteException;
    void updateAnnoTorneoGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo, int nuovoAnnoTorneo) throws RemoteException;
    
    ArrayList<Goal> getGoal() throws RemoteException;
    
    void deleteGoal(int idPartita, int minuto, int numeroGiocatore, String nomeSquadra, String cittaSquadra, String nomeTorneo, int annoTorneo) throws RemoteException;
}
