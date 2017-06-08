/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import exception.EccezioneLogIn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Giulia fumagalli
 */
public class GeneratoreTorneo extends Utente {
    private Torneo torneo;
    private String cf;
    private String password;
    private String nomeTorneo;
    private String autenticazione = "NONAUTENTICATO";
    private List<Partita> partite = new ArrayList<>();
    private List<Torneo> tornei = new ArrayList<>();
    
    public GeneratoreTorneo(String nome, String cognome, String cf, String password){
        super(nome, cognome);
        this.cf = cf;
        this.password = password;
    }
    public String getAutenticazione() {
        return autenticazione;
    }
    public List<Torneo> getTorneiCreati() {
        return tornei;
    }

    public String getCf() {
        return cf;
    }

    public String getPassword() {
        return password;
    }
    public String logIn(String cf, String p){
        try{
            if( cf.equals(this.cf) && p.equals(password) ) {
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
    public void eliminazioneDiretta(String nomeTorneo, int anno, List<Squadra> squadre, List<Arbitro> arbitri){
        
        int sizeSquadre = squadre.size();
        if((sizeSquadre & (sizeSquadre - 1)) == 0){
            int sizeArbitri = arbitri.size();
            int k = 1;
        
            Integer[] indexSquadre = new Integer[sizeSquadre];
            for (int i = 0; i < indexSquadre.length; i++) {
                indexSquadre[i] = i;
            }
        
            Integer[] indexArbitri = new Integer[sizeArbitri];
            for (int i = 0; i < indexArbitri.length; i++) {
                indexArbitri[i] = i;
            }
            Collections.shuffle(Arrays.asList(indexSquadre));
            for(int i = 0; i < sizeSquadre; i += 2){
                Collections.shuffle(Arrays.asList(indexArbitri));
                int j = 0;
                partite.add(new Partita(k, squadre.get(indexSquadre[i]), squadre.get(indexSquadre[i+1]), arbitri.get(indexArbitri[j]), squadre.get(indexSquadre[i]).getCittaProvenienza(), StatoPartita.PROGRAMMATA, nomeTorneo, anno, true));
                j++;
                k++;
                partite.add(new Partita(k, squadre.get(indexSquadre[i+1]), squadre.get(indexSquadre[i]), arbitri.get(indexArbitri[j]), squadre.get(indexSquadre[i+1]).getCittaProvenienza(), StatoPartita.PROGRAMMATA, nomeTorneo, anno, true));
                j++;
                k++;
            }
        
            //for solo per controllare se funziona
            for(int i = 0; i<partite.size(); i++){
                System.out.println(partite.get(i).getSquadraCasa().getNome() + " " + partite.get(i).getSquadraOspite().getNome());
            }
        
            torneo = new EliminazioneDiretta(nomeTorneo, anno, partite, true);
            tornei.add(torneo);
        } else {
            System.out.println("Mi dispiace, non è possibile creare un torneo ad eliminazione diretta, serve che il numero delle squadre sia una potenza di due");
        }
    }
    
    public void italiana(String nomeTorneo, int anno, List<Squadra> squadre, List<Arbitro> arbitri){
        int sizeArbitri = arbitri.size();
        Integer[] indexArbitri = new Integer[sizeArbitri];
        for (int i = 0; i < indexArbitri.length; i++) {
            indexArbitri[i] = i;
        }
        int k = 1;
        for(Squadra s1 : squadre){
            for(Squadra s2 : squadre){
                    if(s1 != s2){
                        Collections.shuffle(Arrays.asList(indexArbitri));
                        partite.add(new Partita(k, s1, s2, arbitri.get(indexArbitri[0]), s1.getCittaProvenienza(), StatoPartita.PROGRAMMATA, nomeTorneo, anno, true));
                        k++;
                    }
            }
        }
        
        Collections.shuffle(partite);
        
        //for solo per controllare se funziona
        for(int i = 0; i<partite.size(); i++){
            System.out.println(partite.get(i).getSquadraCasa().getNome() + " " + partite.get(i).getSquadraOspite().getNome());
        }
        torneo = new Italiana(nomeTorneo, anno, partite, true);
        tornei.add(torneo);
    }
    public void RimuoviTorneo(Torneo t) {
        tornei.remove(t);
    }
}


