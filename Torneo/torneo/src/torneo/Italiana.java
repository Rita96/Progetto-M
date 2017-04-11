/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.util.List;

/**
 *
 * @author paolacarulli
 */
public class Italiana extends Torneo {
    private ClassificaItaliana classifica;
    
    public Italiana(List<Partita> p) {
        super(p);
    }
    @Override
    public void printRisultato() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
