/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import torneo.Arbitro;
import torneo.GeneratoreTorneo;
import torneo.Goal;
import torneo.Partita;
import torneo.Squadra;
import torneo.Torneo;

/**
 *
 * @author deboraquaini
 */
public class SceltaTorneoGUI extends JFrame {

    private GeneratoreTorneo gentorneo;
    private List<Torneo> tornei;
    
    private JScrollPane scrollpaneTORNEI;
    private JScrollPane scrollpaneTORNEICREATI;
    
    private JList listTORNEI;
    private DefaultListModel listmodelTORNEI;
    private JList listTORNEICREATI;
    private DefaultListModel listmodelTORNEICREATI;
    
    private JPanel panelLIST;
    private JPanel panelBOTTONI;
    
    private JButton identificazionearbitro;
    private JButton generatorneo;
    private JButton aggiorna;
    private JButton eliminatorneo; // elimina solo dalla lista di tornei che rimane
    
    public SceltaTorneoGUI(List<Torneo> tornei, GeneratoreTorneo gentorneo) {
        this.gentorneo = gentorneo;
        this.tornei = tornei;
        setTitle("TORNEI");
        setSize(1000, 565);
        initComponents();
    }

    private void initComponents() {
        
        identificazionearbitro = new JButton("Identificazione arbitro");
        generatorneo = new JButton("Genera torneo");
        aggiorna = new JButton("Aggiorna liste");
        eliminatorneo = new JButton("Elimina torneo");
        
        panelLIST = new JPanel();
        panelLIST.setLayout(new GridLayout(1, 2, 0, 0));
        panelLIST.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
        panelBOTTONI = new JPanel();
        panelBOTTONI.setLayout(new GridLayout(1, 4, 0, 0));
        panelBOTTONI.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
        
        listmodelTORNEI = new DefaultListModel();
        for( Torneo t : tornei ) {
            listmodelTORNEI.addElement(t.toString());
        }
        listTORNEI = new JList(listmodelTORNEI);
        scrollpaneTORNEI = new JScrollPane(listTORNEI);
        scrollpaneTORNEI.setBorder(BorderFactory.createTitledBorder("TORNEI DISPONIBILI GIA' CREATI DA TEST - NON ELIMINABILI"));
        scrollpaneTORNEI.setVisible(true);

        listmodelTORNEICREATI = new DefaultListModel();
        for( Torneo t : gentorneo.getTorneiCreati() ) {
            listmodelTORNEICREATI.addElement(t.toString());
        }
        listTORNEICREATI = new JList(listmodelTORNEICREATI);
        scrollpaneTORNEICREATI = new JScrollPane(listTORNEICREATI);
        scrollpaneTORNEICREATI.setBorder(BorderFactory.createTitledBorder("TORNEI CREATI DALL'ORGANIZZATORE - ELIMINABILI"));
        scrollpaneTORNEICREATI.setVisible(true);
        
        //--------------------------------------------------------------------------
        
        ActionListener IDlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Arbitro> arbitri = new ArrayList<>();
                for(int i = 0; i < tornei.size(); i++) {
                    for( Partita p : tornei.get(i).getPartite() ) {
                        if(!(arbitri.contains(p.getArbitro())))
                            arbitri.add(p.getArbitro());
                    }
                }
                JFrame arbitrogui = new ArbitroGUI(arbitri);
                arbitrogui.setSize(1000, 655);
                arbitrogui.setLocation(400, 250);
                arbitrogui.setVisible(true);
            }
            
        };
        identificazionearbitro.addActionListener(IDlistener);
        
        //---------------------------------------------------------------------------
        MouseListener Partitelistener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if( e.getClickCount() == 2 ) {
                    int index = listTORNEI.getSelectedIndex();
                    Torneo t = tornei.get(index);
                    List<Arbitro> arbitri = new ArrayList<>();
                    for( Partita p : t.getPartite() ) {
                        if(!(arbitri.contains(p.getArbitro()))) {
                            arbitri.add(p.getArbitro());
                        }
                    }
                    JFrame cercapartitegui = new CercaPartiteGUI(t);
                    cercapartitegui.setSize(1000, 655);
                    cercapartitegui.setLocation(400, 250);
                    cercapartitegui.setVisible(true);
                }
            }
        };
        listTORNEI.addMouseListener(Partitelistener);
        
        //---------------------------------------------------------------------------
        MouseListener Partitelistener2 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if( e.getClickCount() == 2 ) {
                    int index = listTORNEICREATI.getSelectedIndex();
                    Torneo t = gentorneo.getTorneiCreati().get(index);
                    List<Arbitro> arbitri = new ArrayList<>();
                    for( Partita p : t.getPartite() ) {
                        if(!(arbitri.contains(p.getArbitro()))) {
                            arbitri.add(p.getArbitro());
                        }
                    }
                    JFrame cercapartitegui = new CercaPartiteGUI(t);
                    cercapartitegui.setSize(1000, 655);
                    cercapartitegui.setLocation(400, 250);
                    cercapartitegui.setVisible(true);
                }
            }
        };
        listTORNEICREATI.addMouseListener(Partitelistener2);
        
        //---------------------------------------------------------------------------
        
        ActionListener GENERATORNEOlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Squadra> squadre = new ArrayList<>();
                List<Arbitro> arbitri = new ArrayList<>();
                for(int i = 0; i < tornei.size(); i++) {
                    for( Partita p : tornei.get(i).getPartite() ) {
                        if(!squadre.contains(p.getSquadraCasa()))
                            squadre.add(p.getSquadraCasa());
                        if(!squadre.contains(p.getSquadraOspite()))
                            squadre.add(p.getSquadraOspite());
                        if(!(arbitri.contains(p.getArbitro())))
                            arbitri.add(p.getArbitro());
                    }
                }
                JFrame gentorneogui = new GeneratoreTorneoGUI(gentorneo, squadre, arbitri);
                gentorneogui.setSize(1000, 655);
                gentorneogui.setLocation(400, 250);
                gentorneogui.setVisible(true);
            }
        };
        generatorneo.addActionListener(GENERATORNEOlistener);
        
        //---------------------------------------------------------------------------
        
        ActionListener AGGIORNAlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel NUOVOmodeltornei = new DefaultListModel();
                for( Torneo t : tornei ) {
                    NUOVOmodeltornei.addElement(t);
                }
                listTORNEI.setModel(NUOVOmodeltornei);

                DefaultListModel NUOVOmodeltorneicreati = new DefaultListModel();
                for( Torneo t : gentorneo.getTorneiCreati() ) {
                    NUOVOmodeltorneicreati.addElement(t);
                }
                listTORNEICREATI.setModel(NUOVOmodeltorneicreati);
            }
        };
        aggiorna.addActionListener(AGGIORNAlistener);
        
        //---------------------------------------------------------------------------
        
        ActionListener ELIMINAlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( listTORNEICREATI.isSelectionEmpty() == true && listmodelTORNEICREATI.isEmpty() == true ) {
                        JOptionPane.showMessageDialog(null, "NESSUN TORNEO SELEZIONATO!", "Attenzione", JOptionPane.ERROR_MESSAGE);
                } else {
                    int i = listTORNEICREATI.getSelectedIndex();
                    Torneo t = gentorneo.getTorneiCreati().get(i);
                    gentorneo.RimuoviTorneo(t);
                    DefaultListModel NUOVOmodeltorneicreati = new DefaultListModel();
                    for( Torneo o : gentorneo.getTorneiCreati() ) {
                        NUOVOmodeltorneicreati.addElement(o);
                    }
                    listTORNEICREATI.setModel(NUOVOmodeltorneicreati);
                    }
            }
        };
        eliminatorneo.addActionListener(ELIMINAlistener);
        
        //---------------------------------------------------------------------------
        
        panelLIST.add(scrollpaneTORNEI, BorderLayout.WEST);
        panelLIST.add(scrollpaneTORNEICREATI, BorderLayout.EAST);
        panelBOTTONI.add(identificazionearbitro, BorderLayout.EAST);
        panelBOTTONI.add(aggiorna, BorderLayout.CENTER);
        panelBOTTONI.add(eliminatorneo, BorderLayout.CENTER);
        panelBOTTONI.add(generatorneo, BorderLayout.WEST);
        add(panelLIST, BorderLayout.CENTER);
        add(panelBOTTONI, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
