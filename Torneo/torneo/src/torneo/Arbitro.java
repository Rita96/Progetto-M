/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import databaseinterface.DatabaseInterfaceArbitro;
import exception.EccezioneLogIn;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paolacarulli
 */
public class Arbitro extends Utente{
    private Autenticazione autenticazione = Autenticazione.NONAUTENTICATO; 
    private String codiceFiscale;
    private String password;
    
    /**
     * 
     * @param nome
     * @param cognome
     * @param codice codice fiscale dell'arbitro che fa da username
     * @param password
     * @param putInDatabase 
     */
    public Arbitro(String nome, String cognome, String codice, String password, boolean putInDatabase) {
        super(nome, cognome);
        this.codiceFiscale = codice;
        this.password = password;
        
        //saving in DB
        if(putInDatabase){
            try {
                Test.q.makeArbitroTable().putArbitro(codice, nome, cognome, password);
            } catch (RemoteException ex) {
                Logger.getLogger(Arbitro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Autenticazione getAutenticazione(){
        return autenticazione;
    }
    /**
     * metodo login per l'arbitro
     * @param cf
     * @param p
     * @return 
     */
    public String logIn(String cf, String p){
        try{
            if( cf.equals(codiceFiscale) && p.equals(password) ) {
                autenticazione = Autenticazione.AUTENTICATO;
                return "CREDENZIALI CORRETTE";
            }
            else if( cf == null || p == null ) {
                return "NULL";
            }
            else{
                autenticazione = Autenticazione.NONAUTENTICATO;
                throw new EccezioneLogIn("CREDENZIALI SCORRETTE");
            }
        }
        catch(EccezioneLogIn e){
            return e.getMessage();
        }
    }
    /**
     * metodo logout per l'arbitro
     * @param cf
     * @param p
     * @return 
     */
    public String logOut(String cf, String p) {
        if( autenticazione.equals("AUTENTICATO") ) {
            autenticazione = Autenticazione.NONAUTENTICATO;
            return "Log out effettuato";
        } else if( autenticazione.equals("NONAUTENTICATO") ) {
            return "Log out già effetuato!";
        }
        return null;
    }

    public String getPassword() {
        return password;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    
}
