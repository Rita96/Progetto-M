/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import torneo.EliminazioneDiretta;
import torneo.Squadra;

/**
 *
 * @author nautilus
 */
public interface DatabaseInterfaceTorneoEliminazionediretta extends Remote{
    void putTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo) throws RemoteException;
    void putTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, int eliminazione, String nomeTorneo, int annoTorneo) throws RemoteException;
    
    void updateFaseTorneoTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo, int nuoviPunti) throws RemoteException;
    void updateNomeTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo, String nuovoNome) throws RemoteException;
    void updateAnnoTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo, int nuovoAnno) throws RemoteException;
    void updateNomeSquadraTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo, String nuovoNomeSquadra) throws RemoteException;
    void updateCittaSquadraTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo, String nuovaCittaSquadra) throws RemoteException;
    void updatePassaggioFaseTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo, int nuovoPassaggioFase) throws RemoteException;
    
    ArrayList<String> getTorneoEliminazionediretta(String nomeTorneo, int annoTorneo) throws RemoteException;

    void deleteTorneoEliminazionediretta(String nomeSquadra, String cittaSquadra, int faseTorneo, String nomeTorneo, int annoTorneo) throws RemoteException;
     
}
