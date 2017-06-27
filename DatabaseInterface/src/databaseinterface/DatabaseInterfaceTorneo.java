/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import torneo.Cartellino;
import torneo.Goal;
import torneo.Partita;
import torneo.Torneo;

/**
 *
 * @author nautilus
 */
public interface DatabaseInterfaceTorneo extends Remote{
    
    void putTorneo(String nome, int annoTorneo) throws RemoteException;
    
    void updateNomeTorneo(String nome, int annoTorneo, String nuovoNome) throws RemoteException;
    void updateAnnoTorneo(String nome, int annoTorneo, int nuovoAnno) throws RemoteException;
    
    ArrayList<Goal> getGoalTorneo(String nomeTorneo, int annoTorneo) throws RemoteException;
    ArrayList<Cartellino> getCartellinoTorneo(String nomeTorneo, int annoTorneo) throws RemoteException;
    //ArrayList<Torneo> getTorneo() throws RemoteException;
    
    void deleteTorneo(String nome, int annoTorneo) throws RemoteException;
}
