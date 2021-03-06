/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author nautilus
 */
public interface DatabaseInterfaceTorneoItaliana extends Remote{
    
    void putTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, int annoTorneo) throws RemoteException;
    
    void updatePuntiTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, int nuoviPunti) throws RemoteException;
    void updateNomeTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, String nuovoNome) throws RemoteException;
    void updateAnnoTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, int nuovoAnno) throws RemoteException;
    void updateNomeSquadraTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, String nuovoNomeSquadra) throws RemoteException;
    void updateCittaSquadraTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, String annoTorneo, String nuovaCittaSquadra) throws RemoteException;
    
    String getTorneoItaliana() throws RemoteException;
    
    void deleteTorneoItaliana(String nomeSquadra, String cittaSquadra, int punti, String nomeTorneo, int annoTorneo) throws RemoteException;
    
}
