/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import exception.EccezioneLogIn;

/**
 *
 * @author paolacarulli
 */
public class Arbitro extends Utente{
    private String autenticazione = "NONAUTENTICATO"; // boolean o enum = topquality, no String 
    private String codiceFiscale;
    private String password;
    
    public Arbitro(String nome, String cognome, String codice, String password) {
        super(nome, cognome);
        this.codiceFiscale = codice;
        this.password = password;
    }
    public String getAutenticazione(){
        return autenticazione;
    }
    public String logIn(String cf, String p){
        try{
            if( cf.equals(codiceFiscale) && p.equals(password) ) {
                autenticazione = "AUTENTICATO";
                return "CREDENZIALI CORRETTE";
            }
            else if( cf == null || p == null ) {
                return "NULL";
            }
            else{
                autenticazione = "NONAUTENTICATO";
                throw new EccezioneLogIn("CREDENZIALI SCORRETTE");
            }
        }
        catch(EccezioneLogIn e){
            return e.getMessage();
        }
    }
    public String logOut(String cf, String p) {
        if( autenticazione.equals("AUTENTICATO") ) {
            autenticazione = "NONAUTENTICATO";
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
