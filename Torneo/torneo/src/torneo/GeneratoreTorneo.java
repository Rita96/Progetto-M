/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Giulia fumagalli
 */
public class GeneratoreTorneo {
    private Torneo torneo;
    private String nome;
    private List<Partita> partite = new ArrayList<>();
    private List<Squadra> squadre = new ArrayList<>();
    private List<Arbitro> arbitri = new ArrayList<>();
    
    public GeneratoreTorneo(String nome, String Tipo, List<Squadra> squadre, List<Arbitro> arbitri){
        this.squadre.addAll(squadre);
        this.arbitri.addAll(arbitri);
        this.nome = nome;
        if(Tipo.equals("ELIMINAZIONE DIRETTA")){
            this.eliminazioneDiretta();
        }
        if(Tipo.equals("ITALIANA")){
            this.italiana();
        }
    }
    
    public void eliminazioneDiretta(){
        int sizeSquadre = squadre.size();
        int sizeArbitri = arbitri.size();
        
        Integer[] indexSquadre = new Integer[sizeSquadre];
        for (int i = 0; i < indexSquadre.length; i++) {
            indexSquadre[i] = i;
        }
        
        Integer[] indexArbitri = new Integer[sizeArbitri];
        for (int i = 0; i < indexArbitri.length; i++) {
            indexArbitri[i] = i;
        }
        Collections.shuffle(Arrays.asList(indexSquadre));
        for(int i = 0; i< sizeSquadre; i += 2){
            Collections.shuffle(Arrays.asList(indexArbitri));
            int j = 0;
            partite.add(new Partita(squadre.get(indexSquadre[i]), squadre.get(indexSquadre[i+1]), arbitri.get(indexArbitri[j]), squadre.get(indexSquadre[i]).getCittaProvenienza(), StatoPartita.PROGRAMMATA));
            j++;
            partite.add(new Partita(squadre.get(indexSquadre[i+1]), squadre.get(indexSquadre[i]), arbitri.get(indexArbitri[j]), squadre.get(indexSquadre[i+1]).getCittaProvenienza(), StatoPartita.PROGRAMMATA));
            j++;
        }
        
        //for solo per controllare se funziona
        for(int i = 0; i<partite.size(); i++){
            System.out.println(partite.get(i).getSquadraCasa().getNome() + " " + partite.get(i).getSquadraOspite().getNome());
        }
        
        torneo = new EliminazioneDiretta(nome, partite);
    }
    
    public void italiana(){
        int sizeArbitri = arbitri.size();
        Integer[] indexArbitri = new Integer[sizeArbitri];
        for (int i = 0; i < indexArbitri.length; i++) {
            indexArbitri[i] = i;
        }
        for(Squadra s1 : this.squadre){
            for(Squadra s2 : this.squadre){
                if(s1 != s2){
                    Collections.shuffle(Arrays.asList(indexArbitri));
                    partite.add(new Partita(s1, s2, arbitri.get(indexArbitri[0]), s1.getCittaProvenienza(), StatoPartita.PROGRAMMATA));
                }
            }
        }
        
        Collections.shuffle(partite);
        
        //for solo per controllare se funziona
        for(int i = 0; i<partite.size(); i++){
            System.out.println(partite.get(i).getSquadraCasa().getNome() + " " + partite.get(i).getSquadraOspite().getNome());
        }
        torneo = new Italiana(nome, partite);
    }
}


