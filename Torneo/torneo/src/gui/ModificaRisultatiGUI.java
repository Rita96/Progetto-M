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
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import torneo.Autenticazione;
import torneo.EliminazioneDiretta;
import torneo.GeneratoreTorneo;
import torneo.Giocatore;
import torneo.Goal;
import torneo.Italiana;
import torneo.Partita;
import torneo.PartitaEliminazioneDiretta;
import torneo.PartitaItaliana;
import torneo.Squadra;
import torneo.StatoPartita;
import torneo.Torneo;

/**
 *
 * @author deboraquaini
 */
public class ModificaRisultatiGUI extends JFrame {
    
    private Partita partita;
    private Torneo torneo;
    private GeneratoreTorneo gentorneo;
    private List<Arbitro> a = new ArrayList<>();
    private List<Squadra> squadre = new ArrayList<>();
    
    private JButton setNOMESQCASA;
    private JButton setNOMESQOSPITE;
    private JButton setGOALSQCASA;
    private JButton setGOALSQOSPITE;
    private JButton setCITTA;
    private JButton setARBITRO;
    private JButton setSTATOPARTITA;
    private JButton setPUNTICASA;
    private JButton setPUNTIOSPITE;
    
    private JTextArea NumeroGoalCasa;
    private JTextArea NumeroGoalOspite;
    
    private JLabel NOMESQCASA;
    private JTextField NOMESQCASAfield;
    
    private JLabel NOMESQOSPITE;
    private JTextField NOMESQOSPITEfield;
    
    private JScrollPane GOALSQCASAfield;
    
    private JScrollPane GOALSQOSPITEfield;
    
    private JLabel CITTA;
    private JTextField CITTAfield;
    
    private JLabel ARBITRO;
    private JTextField ARBITROfield;
    
    private JLabel STATOPARTITA;
    private JTextField STATOPARTITAfield;
    
    private JLabel labelGIOCATORIcasa;
    private JLabel labelGIOCATORIospite;
    
    private JButton OKbutton;
    private JButton BACKbutton;
    private JButton AGGIORNAbutton;
    
    private JPanel panelMODIFICHE;
    private JPanel panelGOALGIOCATORI;
    private JPanel panelBOTTONI;
    
    private JScrollPane listscrollpaneCASA;
    private JScrollPane listscrollpaneOSPITE;
    
    private JList jlistCASA;
    private JList jlistOSPITE;
    private JList jlistGOALCASA;
    private JList jlistGOALOSPITE;
    
    /**
     * @param partita Partita selezionata
     * @param torneo Torneo contenente la partita
     * @param squadre Squadre partecipanti al torneo
     * @param arbitri Lista degli arbitri registrati nel database
     * @param gentorneo Organizzatore dei tornei
     */
    public ModificaRisultatiGUI(Partita partita, Torneo torneo, List<Squadra> squadre, List<Arbitro> arbitri, GeneratoreTorneo gentorneo) {
        this.partita= partita;
        this.torneo = torneo;
        this.gentorneo = gentorneo;
        this.a = arbitri;
        this.squadre = squadre;
        if( torneo instanceof EliminazioneDiretta ) {
            ((EliminazioneDiretta) torneo).andata((PartitaEliminazioneDiretta) partita);
        } else if( torneo instanceof Italiana ) {
            ((Italiana) torneo).setItaliana();
        }
        setTitle("Descrizione partita "+partita.getSquadraCasa().getNome()+" VS "+partita.getSquadraOspite().getNome());
        initComponents();
    }

    /**
     * Creazione degli elementi presenti nel frame
     */
    public void CreazioneElementi() {
        OKbutton = new JButton("OK");
        BACKbutton = new JButton("Indietro");
        AGGIORNAbutton = new JButton("Aggiorna");
        
        if( partita instanceof PartitaEliminazioneDiretta ) {
            NumeroGoalCasa = new JTextArea("goal squadra casa\nregolari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraCasaRegolare()+"\tsupplementari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraCasaSupplementari()+"\trigori: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraCasaRigori());
            NumeroGoalCasa.setEditable(false);
            NumeroGoalOspite = new JTextArea("goal squadra ospite\nregolari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraOspiteRegolare()+"\tsupplementari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraOspiteSupplementari()+"\trigori: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraOspiteRigori());
            NumeroGoalOspite.setEditable(false);
        } else if( partita instanceof PartitaItaliana ) {
            NumeroGoalCasa = new JTextArea("goal squadra casa\ntotali: "+((PartitaItaliana) partita).getGoalSquadraCasa());
            NumeroGoalCasa.setEditable(false);
            NumeroGoalOspite = new JTextArea("goal squadra ospite\ntotali: "+((PartitaItaliana) partita).getGoalSquadraOspite());
            NumeroGoalOspite.setEditable(false);
        }
      
        panelMODIFICHE = new JPanel();
        panelMODIFICHE.setLayout(new GridLayout(3, 2, 2, 2));
        panelMODIFICHE.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        panelGOALGIOCATORI = new JPanel();
        panelGOALGIOCATORI.setLayout(new GridLayout(2, 2, 2, 2));
        panelGOALGIOCATORI.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 100));
        panelBOTTONI = new JPanel();
        panelBOTTONI.setLayout(new GridLayout(6, 3, 2, 2));
        panelBOTTONI.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));

        setNOMESQCASA = new JButton("MODIFICA");
        setNOMESQOSPITE = new JButton("MODIFICA");
        setGOALSQCASA = new JButton("AGGIUNGI GOAL SQUADRA CASA");
        setGOALSQOSPITE = new JButton("AGGIUNGI GOAL SQUADRA OSPITE");
        setCITTA = new JButton("MODIFCA");
        setARBITRO = new JButton("MODIFICA");
        setSTATOPARTITA = new JButton("MODIFICA");
        setPUNTICASA = new JButton("SET PUNTEGGIO SQUADRA CASA");
        setPUNTIOSPITE = new JButton("SET PUNTEGGIO SQUADRA OSPITE");

        NOMESQCASA = new JLabel("NOME SQUADRA CASA");
        NOMESQCASAfield = new JTextField(10);
        NOMESQCASAfield.setText(partita.getSquadraCasa().getNome());
        NOMESQCASAfield.setEditable(false);
        
        NOMESQOSPITE = new JLabel("NOME SQUADRA OSPITE");
        NOMESQOSPITEfield = new JTextField(10);
        NOMESQOSPITEfield.setText(partita.getSquadraOspite().getNome());
        NOMESQOSPITEfield.setEditable(false);
        
        CITTA = new JLabel("CITTA");
        CITTAfield = new JTextField(10);
        CITTAfield.setText(partita.getCittaDoveSiSvolge());
        CITTAfield.setEditable(false);
        
        ARBITRO = new JLabel("ARBITRO");
        ARBITROfield = new JTextField(10);
        ARBITROfield.setText(partita.getArbitro().getNome()+" "+partita.getArbitro().getCognome());
        ARBITROfield.setEditable(false);
        
        STATOPARTITA = new JLabel("STATO PARTITA");
        STATOPARTITAfield = new JTextField(10);
        STATOPARTITAfield.setText(partita.getStatoPartita().toString());
        STATOPARTITAfield.setEditable(false);
        
        DefaultListModel modelGOALCASA = new DefaultListModel();
        for( Goal g : partita.getGoalSquadraCasaList() ) {
            modelGOALCASA.addElement(g);
        }
        jlistGOALCASA = new JList(modelGOALCASA);
        GOALSQCASAfield = new JScrollPane(jlistGOALCASA);
        GOALSQCASAfield.setBorder(BorderFactory.createTitledBorder("GOAL SQUADRA CASA"));
        
        DefaultListModel modelGOALOSPITE = new DefaultListModel();
        for( Goal g : partita.getGoalSquadraOspiteList() ) {
            modelGOALOSPITE.addElement(g);
        }
        jlistGOALOSPITE = new JList(modelGOALOSPITE);
        GOALSQOSPITEfield = new JScrollPane(jlistGOALOSPITE);
        GOALSQOSPITEfield.setBorder(BorderFactory.createTitledBorder("GOAL SQUADRA OSPITE"));

        DefaultListModel modelGIOCATORICASA = new DefaultListModel();
        for( Giocatore g : partita.getSquadraCasa().getGiocatori() ) {
            modelGIOCATORICASA.addElement(g.toString());
        }
        jlistCASA = new JList(modelGIOCATORICASA);
        jlistCASA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlistCASA.setSelectedIndex(0);
        listscrollpaneCASA = new JScrollPane(jlistCASA);
        listscrollpaneCASA.setVisible(true);
        listscrollpaneCASA.setBorder(BorderFactory.createTitledBorder("GIOCATORI SQUADRA CASA"));

        DefaultListModel modelGIOCATORIOSPITE = new DefaultListModel();
        for( Giocatore g : partita.getSquadraOspite().getGiocatori() ) {
            modelGIOCATORIOSPITE.addElement(g.toString());
        }
        jlistOSPITE = new JList(modelGIOCATORIOSPITE);
        jlistOSPITE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlistOSPITE.setSelectedIndex(0);
        listscrollpaneOSPITE = new JScrollPane(jlistOSPITE);
        listscrollpaneOSPITE.setVisible(true);
        listscrollpaneOSPITE.setBorder(BorderFactory.createTitledBorder("GIOCATORI SQUADRA OSPITE"));
        
        labelGIOCATORIcasa = new JLabel("Lista giocatori sq casa");
        labelGIOCATORIcasa.setLabelFor(jlistCASA);
        
        labelGIOCATORIospite = new JLabel("Lista giocatori sq ospite");
        labelGIOCATORIospite.setLabelFor(jlistOSPITE);
    }
    
    /**
     * Inserimento dei vari elementi negli appositi panels 
     */
    public void InserimentoElementi() {
        panelMODIFICHE.add(setGOALSQCASA, BorderLayout.EAST);
        panelMODIFICHE.add(setGOALSQOSPITE, BorderLayout.WEST);
        panelMODIFICHE.add(setPUNTICASA, BorderLayout.EAST);
        panelMODIFICHE.add(setPUNTIOSPITE, BorderLayout.WEST);
        panelMODIFICHE.add(NumeroGoalCasa);
        panelMODIFICHE.add(NumeroGoalOspite);
        
        panelGOALGIOCATORI.add(listscrollpaneCASA);
        panelGOALGIOCATORI.add(listscrollpaneOSPITE);
        panelGOALGIOCATORI.add(GOALSQCASAfield);
        panelGOALGIOCATORI.add(GOALSQOSPITEfield);
        
        panelBOTTONI.add(NOMESQCASA, BorderLayout.WEST);
        panelBOTTONI.add(NOMESQCASAfield, BorderLayout.CENTER);
        panelBOTTONI.add(setNOMESQCASA, BorderLayout.EAST);
        
        panelBOTTONI.add(NOMESQOSPITE, BorderLayout.WEST);
        panelBOTTONI.add(NOMESQOSPITEfield, BorderLayout.CENTER);
        panelBOTTONI.add(setNOMESQOSPITE, BorderLayout.EAST);
        
        panelBOTTONI.add(ARBITRO, BorderLayout.WEST);
        panelBOTTONI.add(ARBITROfield, BorderLayout.CENTER);
        panelBOTTONI.add(setARBITRO, BorderLayout.EAST);
        
        panelBOTTONI.add(CITTA, BorderLayout.WEST);
        panelBOTTONI.add(CITTAfield, BorderLayout.CENTER);
        panelBOTTONI.add(setCITTA, BorderLayout.EAST);
        
        panelBOTTONI.add(STATOPARTITA, BorderLayout.WEST);
        panelBOTTONI.add(STATOPARTITAfield, BorderLayout.CENTER);
        panelBOTTONI.add(setSTATOPARTITA, BorderLayout.EAST);
        
        panelBOTTONI.add(OKbutton, BorderLayout.WEST);
        panelBOTTONI.add(AGGIORNAbutton, BorderLayout.CENTER);
        panelBOTTONI.add(BACKbutton, BorderLayout.EAST);
    }
    
    /**
     * Posizionamento dei panels nel frame 
     */
    public void PosizionamentoPanels() {
        add(panelGOALGIOCATORI, BorderLayout.NORTH);
        add(panelMODIFICHE, BorderLayout.CENTER);
        add(panelBOTTONI, BorderLayout.SOUTH);
        pack();
    }
    
    private void initComponents() {
        
        CreazioneElementi();

        //----------------------------------------------------------------------------

        ActionListener NOMESQCASAlistener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if( gentorneo.getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                        String input = JOptionPane.showInputDialog(null, "Inserire modifica: ", "Modifica dati partita", JOptionPane.QUESTION_MESSAGE);
                        if( input != null && input.length() != 0 )
                            NOMESQCASAfield.setText(input);
                    } else if( gentorneo.getAutenticazione().equals(Autenticazione.NONAUTENTICATO) ) {
                        int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come organizzatore per modificare i dati!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION )
                        {
                          JFrame gentorneoGUI = new GeneratoreTorneoGUI(gentorneo, squadre, a);
                          gentorneoGUI.setVisible(true);
                          gentorneoGUI.setSize(1000, 675);
                          gentorneoGUI.setLocation(400, 240);
                        }
                    }
                }
            };
        setNOMESQCASA.addActionListener(NOMESQCASAlistener);
        
        //-------------------------------------------------------------------------

        ActionListener NOMESQOSPITElistener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if( gentorneo.getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                        String input = JOptionPane.showInputDialog(null, "Inserire modifica: ", "Modifica dati partita", JOptionPane.QUESTION_MESSAGE);
                        if( input != null && input.length() != 0 )
                            NOMESQOSPITEfield.setText(input);
                    } else if( gentorneo.getAutenticazione().equals(Autenticazione.NONAUTENTICATO) ) {
                        int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come arbitro per modificare i dati!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION)
                        {
                          JFrame gentorneoGUI = new GeneratoreTorneoGUI(gentorneo, squadre, a);
                          gentorneoGUI.setVisible(true);
                          gentorneoGUI.setSize(1000, 675);
                          gentorneoGUI.setLocation(400, 240);
                        }
                    }
                }
            };
        setNOMESQOSPITE.addActionListener(NOMESQOSPITElistener);
        
        //------------------------------------------------------------------------
        
        ActionListener PUNTICASAlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( partita.getArbitro().getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                    if( partita.getTipo().equals("italiana") ) {
                        JTextField punteggiofield = new JTextField();
                        JLabel punteggiolabel = new JLabel("Punteggio:   ");
                        JPanel panelinformazioni = new JPanel();
                        panelinformazioni.setLayout(new GridLayout(1, 2, 0, 0));
                        panelinformazioni.add(punteggiolabel, BorderLayout.WEST);
                        panelinformazioni.add(punteggiofield, BorderLayout.EAST);
                        int reply = JOptionPane.showConfirmDialog(null, panelinformazioni, "Inserire punteggio assegnato alla squadra", JOptionPane.OK_CANCEL_OPTION);
                        if( reply == JOptionPane.OK_OPTION && punteggiofield != null ) {
                            int punteggio = Integer.parseInt(punteggiofield.getText());
                            ((PartitaItaliana)partita).setPuntiSquadra(punteggio, partita.getSquadraCasa().getNome());
                        }
                    } else if( partita.getTipo().equals("eliminazione diretta") ) {
                        JOptionPane.showMessageDialog(null, "TORNEO AD ELIMINAZIONE DIRETTA NON PREVEDE PUNTEGGIO!", "Attenzione", JOptionPane.OK_OPTION);
                    }
                } else if(partita.getArbitro().getAutenticazione().equals(Autenticazione.NONAUTENTICATO)) {
                        int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come arbitro per modificare i dati!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION)
                        {
                          JFrame arbitroGUI = new ArbitroGUI(a);
                          arbitroGUI.setVisible(true);
                          arbitroGUI.setSize(1000, 300);
                          arbitroGUI.setLocation(400, 425);
                        }
                    }
            }
            
        };
        setPUNTICASA.addActionListener(PUNTICASAlistener);
        
        //--------------------------------------------------------------------------
        
        ActionListener PUNTIOSPITElistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( partita.getArbitro().getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                    if( partita.getTipo().equals("italiana") ) {
                        JTextField punteggiofield = new JTextField();
                        JLabel punteggiolabel = new JLabel("Punteggio:   ");
                        JPanel panelinformazioni = new JPanel();
                        panelinformazioni.setLayout(new GridLayout(1, 2, 0, 0));
                        panelinformazioni.add(punteggiolabel, BorderLayout.WEST);
                        panelinformazioni.add(punteggiofield, BorderLayout.EAST);
                        int reply = JOptionPane.showConfirmDialog(null, panelinformazioni, "Inserire punteggio assegnato alla squadra", JOptionPane.OK_CANCEL_OPTION);
                        if( reply == JOptionPane.OK_OPTION && punteggiofield != null ) {
                            int punteggio = Integer.parseInt(punteggiofield.getText());
                            ((PartitaItaliana)partita).setPuntiSquadra(punteggio, partita.getSquadraOspite().getNome());
                        }
                    } else if( partita.getTipo().equals("eliminazione diretta") ) {
                        JOptionPane.showMessageDialog(null, "TORNEO AD ELIMINAZIONE DIRETTA NON PREVEDE PUNTEGGIO!", "Attenzione", JOptionPane.OK_OPTION);
                    }
                } else if(partita.getArbitro().getAutenticazione().equals(Autenticazione.NONAUTENTICATO)) {
                        int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come arbitro per modificare i dati!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION)
                        {
                          JFrame arbitroGUI = new ArbitroGUI(a);
                          arbitroGUI.setVisible(true);
                          arbitroGUI.setSize(1000, 300);
                          arbitroGUI.setLocation(400, 425);
                        }
                    }
            }
            
        };
        setPUNTIOSPITE.addActionListener(PUNTIOSPITElistener);
        
        //----------------------------------------------------------------------------

        ActionListener ARBITROlistener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if( gentorneo.getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                        JLabel nomelabel = new JLabel("Nome: ");
                        JTextField nomefield = new JTextField();
                        JLabel cognomelabel = new JLabel("Cognome: ");
                        JTextField cognomefield = new JTextField();
                        JPanel panelinformazioni = new JPanel();
                        panelinformazioni.setLayout(new GridLayout(2, 2, 0, 0));
                        panelinformazioni.add(nomelabel, BorderLayout.WEST);
                        panelinformazioni.add(nomefield, BorderLayout.EAST);
                        panelinformazioni.add(cognomelabel, BorderLayout.WEST);
                        panelinformazioni.add(cognomefield, BorderLayout.EAST);
                        int input = JOptionPane.showConfirmDialog(null, panelinformazioni, "Modifica dati partita", JOptionPane.OK_CANCEL_OPTION);
                        String nome = nomefield.getText();
                        String cognome = cognomefield.getText();
                        for( Arbitro arbitro : a ) {
                            if( arbitro.getNome().equals(nome) && arbitro.getCognome().equals(cognome) ) {
                                partita.setArbitro(arbitro);
                                ARBITROfield.setText(nome+" "+cognome);
                            }
                        }
                    } else if(partita.getArbitro().getAutenticazione().equals(Autenticazione.NONAUTENTICATO)) {
                        int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come arbitro per modificare i dati!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION)
                        {
                          JFrame arbitroGUI = new ArbitroGUI(a);
                          arbitroGUI.setVisible(true);
                          arbitroGUI.setSize(1000, 300);
                          arbitroGUI.setLocation(400, 425);
                        }
                    }
                }
            };
        setARBITRO.addActionListener(ARBITROlistener);
        
        //------------------------------------------------------------------------

        ActionListener CITTAlistener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if( gentorneo.getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                        String input = JOptionPane.showInputDialog(null, "Inserire modifica: ", "Modifica dati partita", JOptionPane.QUESTION_MESSAGE);
                        if( input != null && input.length() != 0 ) {
                            partita.ModificaCitta(input);
                            CITTAfield.setText(input);
                        }
                    } else if( gentorneo.getAutenticazione().equals(Autenticazione.NONAUTENTICATO) ) {
                        int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come arbitro per modificare i dati!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            JFrame gentorneoGUI = new GeneratoreTorneoGUI(gentorneo, squadre, a);
                            gentorneoGUI.setVisible(true);
                            gentorneoGUI.setSize(1000, 675);
                            gentorneoGUI.setLocation(400, 240);
                        }
                    }
                }
            };
        setCITTA.addActionListener(CITTAlistener);
        
        //---------------------------------------------------------------------------
        
        ActionListener STATOPARTITAlistener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if( partita.getArbitro().getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                        JComboBox jcombo = new JComboBox(StatoPartita.values());
                        jcombo.setSelectedItem(partita.getStatoPartita());
                        int i = JOptionPane.showConfirmDialog(null, jcombo, "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if( jcombo.getSelectedItem().toString() != null && i == JOptionPane.YES_OPTION ) {
                            STATOPARTITAfield.setText(jcombo.getSelectedItem().toString());
                            String statopartita = STATOPARTITAfield.getText();
                            StatoPartita stato = partita.getStato(statopartita);
                            partita.ModificaStatoPartita(stato);
                            jcombo.setVisible(false);
                        }
                        if( i == JOptionPane.NO_OPTION )
                            jcombo.setVisible(false);
                    } else if(partita.getArbitro().getAutenticazione().equals(Autenticazione.NONAUTENTICATO)) {
                        int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come arbitro per modificare i dati!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION)
                        {
                          JFrame arbitroGUI = new ArbitroGUI(a);
                          arbitroGUI.setVisible(true);
                          arbitroGUI.setSize(1000, 300);
                          arbitroGUI.setLocation(400, 425);
                        }
                    }
                }
            };
        setSTATOPARTITA.addActionListener(STATOPARTITAlistener);
        
        //----------------------------------------------------------------------------
        
        ActionListener OKlistener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nomesqcasa = NOMESQCASAfield.getText();
                    partita.getSquadraCasa().ModificaNome(nomesqcasa);
                    
                    String nomesqospite = NOMESQOSPITEfield.getText();
                    partita.getSquadraOspite().ModificaNome(nomesqospite);
                    
                    String citta = CITTAfield.getText();
                    partita.ModificaCitta(citta);
                    
                    if( partita instanceof PartitaEliminazioneDiretta ) {
                        NumeroGoalCasa.setText("goal squadra casa\nregolari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraCasaRegolare()+"\tsupplementari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraCasaSupplementari()+"\trigori: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraCasaRigori());
                        NumeroGoalOspite.setText("goal squadra ospite\nregolari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraOspiteRegolare()+"\tsupplementari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraOspiteSupplementari()+"\trigori: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraOspiteRigori());
                    } else if( partita instanceof PartitaItaliana ) {
                        NumeroGoalCasa.setText("goal squadra casa\ntotali: "+((PartitaItaliana) partita).getGoalSquadraCasa());
                        NumeroGoalOspite.setText("goal squadra ospite\ntotali: "+((PartitaItaliana) partita).getGoalSquadraOspite());
                    }
                    setVisible(false);
                }
            };
        OKbutton.addActionListener(OKlistener);
        
        //-------------------------------------------------------------------------
        
        ActionListener AGGIORNAlistener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nomesqcasa = NOMESQCASAfield.getText();
                    partita.getSquadraCasa().ModificaNome(nomesqcasa);
                    
                    String nomesqospite = NOMESQOSPITEfield.getText();
                    partita.getSquadraOspite().ModificaNome(nomesqospite);
                   
                    String citta = CITTAfield.getText();
                    partita.ModificaCitta(citta);
                    
                    DefaultListModel NUOVOmodelgoalcasa = new DefaultListModel();
                    for( Goal g : partita.getGoalSquadraCasaList() ) {
                        NUOVOmodelgoalcasa.addElement(g);
                    }
                    jlistGOALCASA.setModel(NUOVOmodelgoalcasa);
                    
                    DefaultListModel NUOVOmodelgoalospite = new DefaultListModel();
                    for( Goal g : partita.getGoalSquadraOspiteList() ) {
                        NUOVOmodelgoalospite.addElement(g);
                    }
                    jlistGOALOSPITE.setModel(NUOVOmodelgoalospite);
                    
                    DefaultListModel NUOVOmodelgiocatoricasa = new DefaultListModel();
                    for( Giocatore g : partita.getSquadraCasa().getGiocatori() ) {
                        NUOVOmodelgiocatoricasa.addElement(g);
                    }
                    jlistCASA.setModel(NUOVOmodelgiocatoricasa);
                    
                    DefaultListModel NUOVOmodelgiocatoriospite = new DefaultListModel();
                    for( Giocatore g : partita.getSquadraOspite().getGiocatori() ) {
                        NUOVOmodelgiocatoriospite.addElement(g);
                    }
                    jlistOSPITE.setModel(NUOVOmodelgiocatoriospite);
                    
                    if( partita instanceof PartitaEliminazioneDiretta ) {
                        NumeroGoalCasa.setText("goal squadra casa\nregolari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraCasaRegolare()+"\tsupplementari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraCasaSupplementari()+"\trigori: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraCasaRigori());
                        NumeroGoalOspite.setText("goal squadra ospite\nregolari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraOspiteRegolare()+"\tsupplementari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraOspiteSupplementari()+"\trigori: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraOspiteRigori());
                    } else if( partita instanceof PartitaItaliana ) {
                        NumeroGoalCasa.setText("goal squadra casa\ntotali: "+((PartitaItaliana) partita).getGoalSquadraCasa());
                        NumeroGoalOspite.setText("goal squadra ospite\ntotali: "+((PartitaItaliana) partita).getGoalSquadraOspite());
                    }
                }
            };
        AGGIORNAbutton.addActionListener(AGGIORNAlistener);
        
        //-------------------------------------------------------------------------
        
        ActionListener BACKlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
            
        };
        BACKbutton.addActionListener(BACKlistener);
        
        //--------------------------------------------------------------------------
        // JCOMBOBOXES OF PLAYERS LISTENERS
        
        MouseListener casalistener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList theList = (JList) e.getSource();
                if (e.getClickCount() == 2) {
                    int index = theList.locationToIndex(e.getPoint());
                    JFrame giocatoregui = new GiocatoreGUI(partita, partita.getSquadraCasa().getGiocatori().get(index), torneo);
                    giocatoregui.setVisible(true);
                    giocatoregui.setSize(1000, 675);
                    giocatoregui.setLocation(400, 250);
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
        
        jlistCASA.addMouseListener(casalistener);
        
        //----------------------------------------------------------------------------
        
        MouseListener ospitelistener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList theList = (JList) e.getSource();
                if (e.getClickCount() == 2) {
                    int index = theList.locationToIndex(e.getPoint());
                    JFrame giocatoregui = new GiocatoreGUI(partita, partita.getSquadraOspite().getGiocatori().get(index), torneo);
                    giocatoregui.setVisible(true);
                    giocatoregui.setSize(1000, 675);
                    giocatoregui.setLocation(400, 250);
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
        jlistOSPITE.addMouseListener(ospitelistener);

        //----------------------------------------------------------------------------
        
        ActionListener GOALSQCASAlistener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if( partita.getArbitro().getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                        if( !partita.getStatoPartita().equals(StatoPartita.PROGRAMMATA) ) {
                            JTextField numerofield = new JTextField();
                            JLabel numerolabel = new JLabel("Numero giocatore:   ");
                            JTextField minutofield = new JTextField();
                            JLabel minutolabel = new JLabel("Minuto:   ");
                            JPanel panelinformazioni = new JPanel();
                            panelinformazioni.setLayout(new GridLayout(4, 1, 2, 2));
                            panelinformazioni.add(numerolabel, BorderLayout.WEST);
                            panelinformazioni.add(numerofield, BorderLayout.EAST);
                            panelinformazioni.add(minutolabel, BorderLayout.WEST);
                            panelinformazioni.add(minutofield, BorderLayout.EAST);
                            int reply = JOptionPane.showConfirmDialog(null, panelinformazioni, "Inserire nome, cognome e numero del giocatore e il minuto per aggiungere il goal", JOptionPane.OK_CANCEL_OPTION);
                            if( reply == JOptionPane.OK_OPTION &&  numerofield != null && minutofield != null ) {
                                int numero = Integer.parseInt(numerofield.getText());
                                int minuto = Integer.parseInt(minutofield.getText());
                                for( Giocatore g : partita.getSquadraCasa().getGiocatori() ) {
                                    if( g.getNumero() == numero ) {
                                        partita.setGoal(minuto, g);
                                        if( partita instanceof PartitaEliminazioneDiretta )
                                            NumeroGoalCasa.setText("goal squadra casa\nregolari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraCasaRegolare()+"\tsupplementari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraCasaSupplementari()+"\trigori: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraCasaRigori());
                                        else if( partita instanceof PartitaItaliana ) 
                                            NumeroGoalCasa.setText("goal squadra casa\ntotali: "+((PartitaItaliana) partita).getGoalSquadraCasa());
                                    }
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "IMPOSSIBILE INSERIRE GOAL PERCHE' PARTITA NON IN CORSO!", "Attenzione", JOptionPane.ERROR_MESSAGE);                                                            
                        }
                    } else if( partita.getArbitro().getAutenticazione().equals(Autenticazione.NONAUTENTICATO) ) {
                        int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come arbitro per modificare i dati!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION)
                        {
                          JFrame arbitroGUI = new ArbitroGUI(a);
                          arbitroGUI.setVisible(true);
                          arbitroGUI.setSize(1000, 300);
                          arbitroGUI.setLocation(400, 425);
                        }
                    }
                }
            };
        setGOALSQCASA.addActionListener(GOALSQCASAlistener);
        
        //------------------------------------------------------------------------

        ActionListener GOALSQOSPITElistener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if( partita.getArbitro().getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                        if( !partita.getStatoPartita().equals(StatoPartita.PROGRAMMATA) ) {
                            JTextField numerofield = new JTextField();
                            JLabel numerolabel = new JLabel("Numero giocatore:   ");
                            JTextField minutofield = new JTextField();
                            JLabel minutolabel = new JLabel("Minuto:   ");
                            JPanel panelinformazioni = new JPanel();
                            panelinformazioni.setLayout(new GridLayout(4, 1, 2, 2));
                            panelinformazioni.add(numerolabel, BorderLayout.WEST);
                            panelinformazioni.add(numerofield, BorderLayout.EAST);
                            panelinformazioni.add(minutolabel, BorderLayout.WEST);
                            panelinformazioni.add(minutofield, BorderLayout.EAST);
                            int reply = JOptionPane.showConfirmDialog(null, panelinformazioni, "Inserire nome, cognome e numero del giocatore e il minuto per aggiungere il goal", JOptionPane.OK_CANCEL_OPTION);
                            if( reply == JOptionPane.OK_OPTION &&  numerofield != null && minutofield != null ) {
                                int numero = Integer.parseInt(numerofield.getText());
                                int minuto = Integer.parseInt(minutofield.getText());
                                for( Giocatore g : partita.getSquadraOspite().getGiocatori() ) {
                                    if( g.getNumero() == numero ) {
                                        partita.setGoal(minuto, g);
                                        if( partita instanceof PartitaEliminazioneDiretta )
                                            NumeroGoalOspite.setText("goal squadra ospite\nregolari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraOspiteRegolare()+"\tsupplementari: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraOspiteSupplementari()+"\trigori: "+((PartitaEliminazioneDiretta) partita).getGoalSquadraOspiteRigori());
                                        else if( partita instanceof PartitaItaliana )
                                            NumeroGoalOspite.setText("goal squadra ospite\ntotali: "+((PartitaItaliana) partita).getGoalSquadraOspite());   
                                    }
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "IMPOSSIBILE INSERIRE GOAL PERCHE' PARTITA NON IN CORSO!", "Attenzione", JOptionPane.ERROR_MESSAGE);                                
                        }
                    } else if(partita.getArbitro().getAutenticazione().equals(Autenticazione.NONAUTENTICATO)) {
                        int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come arbitro per modificare i dati!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION)
                        {
                          JFrame arbitroGUI = new ArbitroGUI(a);
                          arbitroGUI.setVisible(true);
                          arbitroGUI.setSize(1000, 300);
                          arbitroGUI.setLocation(400, 425);
                        }
                    }
                }
            };
        setGOALSQOSPITE.addActionListener(GOALSQOSPITElistener);
        
        //-------------------------------------------------------------------------
        
        InserimentoElementi();
        PosizionamentoPanels();
    
    }
}
