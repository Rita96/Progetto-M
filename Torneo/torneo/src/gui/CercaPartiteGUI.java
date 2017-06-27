/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author deboraquaini
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import torneo.Arbitro;
import torneo.EliminazioneDiretta;
import torneo.GeneratoreTorneo;
import torneo.Italiana;
import torneo.Partita;
import torneo.PartitaItaliana;
import torneo.Squadra;
import torneo.Torneo;

/**
 *
 * @author deboraquaini
 */
public class CercaPartiteGUI extends JFrame {
    
    private Torneo torneo;
    private GeneratoreTorneo gentorneo;
    private List<Arbitro> arbitri;
    private List<Squadra> squadre;
    private JPanel panelcampi;
    private JPanel panelbottoni;
    private JButton cerca;
    private JButton indietro;
    private JButton misto;
    private JLabel labelNOME;
    private JLabel labelTIPOLOGIA;
    private JTextField fieldNOME;
    private JTextField fieldTIPOLOGIA;
    private JTextArea sezionecomunicazioni;
    private JList list;
    private JScrollPane listscrollpane;
    
    /**
     * @param torneo Torneo selezionato
     * @param gentorneo Organizzatore dei tornei
     * @param squadre Squadre partecipanti al torneo
     * @param arbitri Lista degli arbitri registrati nel database
     */
    public CercaPartiteGUI(Torneo torneo, GeneratoreTorneo gentorneo, List<Squadra> squadre, List<Arbitro> arbitri) {
        this.torneo = torneo;
        this.gentorneo = gentorneo;
        this.arbitri = arbitri;
        this.squadre = squadre;
        this.setTitle("PARTITE TORNEO "+torneo.getNome());
        initComponents();
    }
    
    /**
     * Creazione degli elementi presenti nel frame
     */
    public void CreazioneElementi() {
        cerca = new JButton("Cerca Classifica");
        indietro = new JButton("Indietro");
        misto = new JButton("Misto");
        
        panelcampi = new JPanel();
        panelcampi.setLayout(new GridLayout(2, 1, 2, 2));
        panelcampi.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 100));
        panelbottoni = new JPanel();
        panelbottoni.setLayout(new GridLayout(1, 2, 2, 2));
        panelbottoni.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
        
        labelNOME = new JLabel("Torneo:");
        fieldNOME = new JTextField();
        fieldNOME.setText(torneo.getNome());
        fieldNOME.setEditable(false);
        labelTIPOLOGIA = new JLabel("Tipologia:");
        fieldTIPOLOGIA = new JTextField();
        fieldTIPOLOGIA.setEditable(false);
        
        if( torneo instanceof EliminazioneDiretta ) {
            fieldTIPOLOGIA.setText("ELIMINAZIONE DIRETTA");
        } else if( torneo instanceof Italiana ) {
            fieldTIPOLOGIA.setText("ALL' ITALIANA");
        }
        
        sezionecomunicazioni = new JTextArea();
        sezionecomunicazioni.setEditable(false);
        
        if(torneo instanceof Italiana) {
            for(int i=0; i<torneo.getPartite().size(); i++) {
                torneo.getPartite().get(i).ModificaTipo("italiana");
            }
            list = new JList(torneo.getPartite().toArray());
            list.setVisible(true);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setVisibleRowCount(torneo.getPartite().size());
            listscrollpane = new JScrollPane(list);
            listscrollpane.setVisible(true);
            listscrollpane.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        }
        
        if(torneo instanceof EliminazioneDiretta) {
            for(int i=0; i<torneo.getPartite().size(); i++) {
                torneo.getPartite().get(i).ModificaTipo("eliminazione diretta");
            }
            list = new JList(torneo.getPartite().toArray());
            list.setVisible(true);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setVisibleRowCount(torneo.getPartite().size());
            listscrollpane = new JScrollPane(list);
            listscrollpane.setVisible(true);
            listscrollpane.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        }
    }
    
    /**
     * Inserimento dei vari elementi negli appositi panels 
     */
    public void InserimentoElementi() {
        panelcampi.add(labelNOME, BorderLayout.WEST);
        panelcampi.add(fieldNOME, BorderLayout.EAST);
        panelcampi.add(labelTIPOLOGIA, BorderLayout.WEST);
        panelcampi.add(fieldTIPOLOGIA, BorderLayout.EAST);
        
        panelbottoni.add(cerca, BorderLayout.EAST);
        panelbottoni.add(misto, BorderLayout.WEST);
        panelbottoni.add(indietro, BorderLayout.WEST);
    }
    
    /**
     * Posizionamento dei panels nel frame 
     */
    public void PosizionamentoPanels() {
        add(panelcampi, BorderLayout.NORTH);
        add(listscrollpane, BorderLayout.CENTER);
        add(panelbottoni, BorderLayout.SOUTH);
        pack();
    }

    private void initComponents() {
        
        CreazioneElementi();
        
        //------------------------------------------------------------------------
        
        MouseListener m = new MouseAdapter() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                JList theList = (JList) e.getSource();
                if (e.getClickCount() == 2) {
                    int index = theList.locationToIndex(e.getPoint());
                    JFrame modificarisGUI = new ModificaRisultatiGUI(torneo.getPartite().get(index), torneo, squadre, arbitri, gentorneo);
                    modificarisGUI.setSize(1000, 675);
                    modificarisGUI.setLocation(400, 250);
                    modificarisGUI.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {  
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        
        list.addMouseListener(m);
        
        //------------------------------------------------------------------------
        
        ActionListener al = new ActionListener() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
            
        };
        indietro.addActionListener(al);
        
        //------------------------------------------------------------------------
        
        ActionListener cercalistener = new ActionListener() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame classificagui = new ClassificaGUI(torneo);
                classificagui.setSize(1000, 675);
                classificagui.setLocation(400, 250);
                classificagui.setVisible(true);
            }
            
        };
        cerca.addActionListener(cercalistener);
        
        //------------------------------------------------------------------------
        
        ActionListener mistolistener = new ActionListener() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                 if(torneo instanceof Italiana) {
                    ((Italiana)torneo).setItaliana();
                    Torneo torneo2 = ((Italiana)torneo).misto(); // eliminazione diretta
                    if(torneo2 == null){
                         JOptionPane.showMessageDialog(null, "Il torneo deve essere terminato", "Attenzione!", JOptionPane.ERROR_MESSAGE);
                         return;
                    }
                    for(Partita p : torneo2.getPartite()){
                        p.ModificaTipo("ELIMINAZIONE DIRETTA");
                        torneo.aggiungiPartita(p);
                    }
                    DefaultListModel NUOVOmodeltornei = new DefaultListModel();
                    for( Partita p : torneo.getPartite() ) {
                        NUOVOmodeltornei.addElement(p);
                    }
                    list.setModel(NUOVOmodeltornei);
                  
                } else if(torneo instanceof EliminazioneDiretta) {
                    JOptionPane.showMessageDialog(null, "E' possibile creare un torneo misto solo da un torneo all'italiana", "Attenzione!", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        };
        misto.addActionListener(mistolistener);
        
        //------------------------------------------------------------------------
        
        InserimentoElementi();
        PosizionamentoPanels();
        
    }
}
