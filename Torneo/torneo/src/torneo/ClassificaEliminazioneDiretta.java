/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.util.*;

/**
 *
 * @author paolacarulli
 */
public class ClassificaEliminazioneDiretta {
    private EliminazioneDiretta torneoElDir;
    
    /**
     * Costruttore
     * @param torneoElDir 
     */
    public ClassificaEliminazioneDiretta(EliminazioneDiretta torneoElDir){
        this.torneoElDir = torneoElDir;
    }
    /**
     * 
     * @return lista di squadre ancora presenti nel torneo 
     */
    public List<Squadra> printRisultato(){
        return torneoElDir.getSquadreNelTorneo();
    }
}
