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
    private String autenticazione; // boolean o enum = topquality, no String 
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
    public void logIn(String cf, String p){
        try{
            if(cf.equals(codiceFiscale) && p.equals(password)){
                autenticazione = "AUTENTICATO";
                System.out.println("Credenziali corrette");
            }
            else{
                autenticazione = "NONAUTENTICATO";
                throw new EccezioneLogIn("Credenziali sbagli");
            }
        }
        catch(EccezioneLogIn e){
            e.getMessage();
        }
    } 
}
