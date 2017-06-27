/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import torneo.Arbitro;
import torneo.Autenticazione;
import torneo.GeneratoreTorneo;
import torneo.Giocatore;
import torneo.Partita;
import torneo.Squadra;
import torneo.Torneo;

/**
 *
 * @author deboraquaini
 */
public class SceltaTorneoGUI extends JFrame {

    List<Squadra> squadre = new ArrayList<>();
    List<Arbitro> arbitri = new ArrayList<>();
    List<Giocatore> giocatori = new ArrayList<>();
    private GeneratoreTorneo gentorneo;
    private List<Torneo> tornei;
    // lista dei giocatori selezionati nella GUI
    List<Giocatore> giocatorigen = new ArrayList<>();
    
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
    private JButton generaarbitro;
    private JButton generagiocatore;
    private JButton generasquadra;
    private JButton chiudibutton;
    
    /**
     * @param tornei Lista di tutti i tornei registrati o creati
     * @param gentorneo Organizzatore dei tornei
     */
    public SceltaTorneoGUI(List<Torneo> tornei, GeneratoreTorneo gentorneo) {
        this.gentorneo = gentorneo;
        this.tornei = tornei;
        setTitle("TORNEI");
        setSize(1000, 565);
        for(int i = 0; i < tornei.size(); i++) {
            for( Partita p : tornei.get(i).getPartite() ) {
                if(!(arbitri.contains(p.getArbitro())))
                    arbitri.add(p.getArbitro());
                if(!squadre.contains(p.getSquadraCasa()))
                    squadre.add(p.getSquadraCasa());
                if(!squadre.contains(p.getSquadraOspite()))
                    squadre.add(p.getSquadraOspite());
                for( Giocatore giocatore : p.getSquadraCasa().getGiocatori() ) {
                    if(!giocatori.contains(giocatore)) {
                        giocatori.add(giocatore);
                    }
                }
                for( Giocatore giocatore : p.getSquadraOspite().getGiocatori() ) {
                    if(!giocatori.contains(giocatore)) {
                        giocatori.add(giocatore);
                    }
                }
            }
        }
        initComponents();
    }
    
    /**
     * Creazione degli elementi presenti nel frame
     */
    public void CreazioneElementi() {
        identificazionearbitro = new JButton("Identificazione arbitro");
        generatorneo = new JButton("Genera torneo");
        aggiorna = new JButton("Aggiorna liste");
        eliminatorneo = new JButton("Elimina torneo");
        generaarbitro = new JButton("Genera arbitro");
        generagiocatore = new JButton("Genera giocatore");
        generasquadra = new JButton("Genera squadra");
        chiudibutton = new JButton("Chiudi");
        
        panelLIST = new JPanel();
        panelLIST.setLayout(new GridLayout(1, 2, 2, 2));
        panelLIST.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
        panelBOTTONI = new JPanel();
        panelBOTTONI.setLayout(new GridLayout(2, 4, 2, 2));
        panelBOTTONI.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
        
        listmodelTORNEI = new DefaultListModel();
        for( Torneo t : tornei ) {
            listmodelTORNEI.addElement(t.toString());
        }
        listTORNEI = new JList(listmodelTORNEI);
        listTORNEI.setSelectionBackground(Color.GREEN);
        scrollpaneTORNEI = new JScrollPane(listTORNEI);
        scrollpaneTORNEI.setBorder(BorderFactory.createTitledBorder("TORNEI DISPONIBILI GIA' CREATI DA TEST - NON ELIMINABILI"));
        scrollpaneTORNEI.setVisible(true);

        listmodelTORNEICREATI = new DefaultListModel();
        for( Torneo t : gentorneo.getTorneiCreati() ) {
            listmodelTORNEICREATI.addElement(t.toString());
        }
        listTORNEICREATI = new JList(listmodelTORNEICREATI);
        listTORNEICREATI.setSelectionBackground(Color.YELLOW);
        scrollpaneTORNEICREATI = new JScrollPane(listTORNEICREATI);
        scrollpaneTORNEICREATI.setBorder(BorderFactory.createTitledBorder("TORNEI CREATI DALL'ORGANIZZATORE - ELIMINABILI"));
        scrollpaneTORNEICREATI.setVisible(true);
    }
    
    /**
     * Inserimento dei vari elementi negli appositi panels 
     */
    public void InserimentoElementi() {
        panelLIST.add(scrollpaneTORNEI, BorderLayout.WEST);
        panelLIST.add(scrollpaneTORNEICREATI, BorderLayout.EAST);
        panelBOTTONI.add(identificazionearbitro, BorderLayout.EAST);
        panelBOTTONI.add(aggiorna, BorderLayout.CENTER);
        panelBOTTONI.add(eliminatorneo, BorderLayout.CENTER);
        panelBOTTONI.add(generatorneo, BorderLayout.WEST);
        panelBOTTONI.add(generaarbitro, BorderLayout.WEST);
        panelBOTTONI.add(generagiocatore, BorderLayout.CENTER);
        panelBOTTONI.add(generasquadra, BorderLayout.CENTER);
        panelBOTTONI.add(chiudibutton, BorderLayout.EAST);
    }
    
    /**
     * Posizionamento dei panels nel frame 
     */
    public void PosizionamentoPanels() {
        add(panelLIST, BorderLayout.CENTER);
        add(panelBOTTONI, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        
        CreazioneElementi();
        
        //--------------------------------------------------------------------------
        
        ActionListener IDlistener = new ActionListener() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(arbitri.size());
                if( !(arbitri.size() == 0) ) {
                    JFrame arbitrogui = new ArbitroGUI(arbitri);
                    arbitrogui.setSize(1000, 300);
                    arbitrogui.setLocation(400, 425);
                    arbitrogui.setVisible(true);
                } else if( arbitri.size() == 0 ) {
                    JOptionPane.showMessageDialog(null, "NESSUN ARBITRO PRESENTE!", "Attenzione", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
        };
        identificazionearbitro.addActionListener(IDlistener);
        
        //---------------------------------------------------------------------------
        MouseListener Partitelistener = new MouseAdapter() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if( e.getClickCount() == 2 ) {
                    int index = listTORNEI.getSelectedIndex();
                    Torneo t = tornei.get(index);
                    JFrame cercapartitegui = new CercaPartiteGUI(t, gentorneo, squadre, arbitri);
                    cercapartitegui.setSize(1000, 675);
                    cercapartitegui.setLocation(400, 250);
                    cercapartitegui.setVisible(true);
                }
            }
        };
        listTORNEI.addMouseListener(Partitelistener);
        
        //---------------------------------------------------------------------------
        MouseListener Partitelistener2 = new MouseAdapter() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if( e.getClickCount() == 2 ) {
                    int index = listTORNEICREATI.getSelectedIndex();
                    Torneo t = gentorneo.getTorneiCreati().get(index);
                    JFrame cercapartitegui = new CercaPartiteGUI(t, gentorneo, squadre, arbitri);
                    cercapartitegui.setSize(1000, 675);
                    cercapartitegui.setLocation(400, 250);
                    cercapartitegui.setVisible(true);
                }
            }
        };
        listTORNEICREATI.addMouseListener(Partitelistener2);
        
        //---------------------------------------------------------------------------
        
        ActionListener GENERATORNEOlistener = new ActionListener() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame gentorneogui = new GeneratoreTorneoGUI(gentorneo, squadre, arbitri);
                gentorneogui.setSize(1000, 675);
                gentorneogui.setLocation(400, 250);
                gentorneogui.setVisible(true);
            }
        };
        generatorneo.addActionListener(GENERATORNEOlistener);
        
        //---------------------------------------------------------------------------
        
        ActionListener AGGIORNAlistener = new ActionListener() {
            
            /**
             * 
             * @param e 
             */
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
            
            /**
             * 
             * @param e 
             */
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
        
        ActionListener GENERAARBITROlistener = new ActionListener() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if( gentorneo.getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                    JLabel nomelabel = new JLabel("Nome: ");
                    JTextField nomefield = new JTextField();
                    JLabel cognomelabel = new JLabel("Cogome: ");
                    JTextField cognomefield = new JTextField();
                    JLabel cflabel = new JLabel("Codice fiscale: ");
                    JTextField cffield = new JTextField();
                    JLabel passlabel = new JLabel("Password: ");
                    JTextField passfield = new JTextField();
                    JPanel panelinformazioni = new JPanel();
                    panelinformazioni.setLayout(new GridLayout(4, 1, 0, 0));
                    panelinformazioni.add(nomelabel, BorderLayout.WEST);
                    panelinformazioni.add(nomefield, BorderLayout.EAST);
                    panelinformazioni.add(cognomelabel, BorderLayout.WEST);
                    panelinformazioni.add(cognomefield, BorderLayout.EAST);
                    panelinformazioni.add(cflabel, BorderLayout.WEST);
                    panelinformazioni.add(cffield, BorderLayout.EAST);
                    panelinformazioni.add(passlabel, BorderLayout.WEST);
                    panelinformazioni.add(passfield, BorderLayout.EAST);
                    int reply = JOptionPane.showConfirmDialog(null, panelinformazioni, "Inserire nome, cognome, codice fiscale e password del nuovo arbitro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if( reply == JOptionPane.OK_OPTION ) {
                        if( !nomefield.getText().equals("") && !cognomefield.getText().equals("") && !cffield.getText().equals("") && !passfield.getText().equals("") ) {
                            String nome = nomefield.getText();
                            String cognome = cognomefield.getText();
                            String cf = cffield.getText();
                            String pass = passfield.getText();
                            Arbitro arbitro = new Arbitro(nome, cognome, cf, pass, true);
                            arbitri.add(arbitro);
                        } else {
                            JOptionPane.showMessageDialog(null, "PARAMETRI MANCANTI!", "Attenzione", JOptionPane.ERROR_MESSAGE);
                        }
                    }   
                } else {
                    int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come organizzatore per creare nuovi elementi!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                      JFrame gentorneoGUI = new GeneratoreTorneoGUI(gentorneo, squadre, arbitri);
                      gentorneoGUI.setVisible(true);
                      gentorneoGUI.setSize(1000, 675);
                      gentorneoGUI.setLocation(400, 250);
                    }
                }
            }
        };
        generaarbitro.addActionListener(GENERAARBITROlistener);
        
        //---------------------------------------------------------------------------
        
        ActionListener GENERAGIOCATORElistener = new ActionListener() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if( gentorneo.getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                    JLabel nomelabel = new JLabel("Nome: ");
                    JTextField nomefield = new JTextField();
                    JLabel cognomelabel = new JLabel("Cogome: ");
                    JTextField cognomefield = new JTextField();
                    JLabel numerolabel = new JLabel("Numero: ");
                    JTextField numerofield = new JTextField();
                    JPanel panelinformazioni = new JPanel();
                    panelinformazioni.setLayout(new GridLayout(3, 1, 0, 0));
                    panelinformazioni.add(nomelabel, BorderLayout.WEST);
                    panelinformazioni.add(nomefield, BorderLayout.EAST);
                    panelinformazioni.add(cognomelabel, BorderLayout.WEST);
                    panelinformazioni.add(cognomefield, BorderLayout.EAST);
                    panelinformazioni.add(numerolabel, BorderLayout.WEST);
                    panelinformazioni.add(numerofield, BorderLayout.EAST);
                    int reply = JOptionPane.showConfirmDialog(null, panelinformazioni, "Inserire nome, cognome e numero del nuovo giocatore", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if( reply == JOptionPane.OK_OPTION ) {
                        if( !nomefield.getText().equals("") && !cognomefield.getText().equals("") && numerofield != null ) {
                            String nome = nomefield.getText();
                            String cognome = cognomefield.getText();
                            int numero = Integer.parseInt(numerofield.getText());
                            Giocatore giocatore = new Giocatore(nome, cognome, numero);
                            giocatori.add(giocatore);
                        } else {
                            JOptionPane.showMessageDialog(null, "PARAMETRI MANCANTI!", "Attenzione", JOptionPane.ERROR_MESSAGE);
                        }
                    }   
                } else {
                    int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come organizzatore per creare nuovi elementi!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                          JFrame gentorneoGUI = new GeneratoreTorneoGUI(gentorneo, squadre, arbitri);
                          gentorneoGUI.setVisible(true);
                          gentorneoGUI.setSize(1000, 675);
                          gentorneoGUI.setLocation(400, 250);
                        }
                }
            }
        };
        generagiocatore.addActionListener(GENERAGIOCATORElistener);
        
        //---------------------------------------------------------------------------
        
        ActionListener GENERASQUADRAlistener = new ActionListener() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if( gentorneo.getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                    giocatorigen.clear();
                    JLabel nomelabel = new JLabel("Nome: ");
                    JTextField nomefield = new JTextField();
                    JLabel colorelabel = new JLabel("Colore: ");
                    JTextField colorefield = new JTextField();
                    JLabel cittalabel = new JLabel("Città: ");
                    JTextField cittafield = new JTextField();
                    DefaultListModel modelGIOCATORI = new DefaultListModel();
                    for( Giocatore g : giocatori ) {
                        modelGIOCATORI.addElement(g.toString());
                    }
                    JList jlistgiocatori = new JList(modelGIOCATORI);
                    jlistgiocatori.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    JScrollPane scrollpane = new JScrollPane(jlistgiocatori);
                    scrollpane.setVisible(true);
                    scrollpane.setBorder(BorderFactory.createTitledBorder("GIOCATORI - Selezionare più giocatori"));
                    JPanel panelinformazioni = new JPanel();
                    panelinformazioni.setLayout(new GridLayout(2, 1, 0, 0));
                    JPanel paneldati = new JPanel();
                    paneldati.setLayout(new GridLayout(3, 2, 0, 0));
                    paneldati.add(nomelabel, BorderLayout.WEST);
                    paneldati.add(nomefield, BorderLayout.EAST);
                    paneldati.add(colorelabel, BorderLayout.WEST);
                    paneldati.add(colorefield, BorderLayout.EAST);
                    paneldati.add(cittalabel, BorderLayout.WEST);
                    paneldati.add(cittafield, BorderLayout.EAST);
                    panelinformazioni.add(scrollpane, BorderLayout.NORTH);
                    panelinformazioni.add(paneldati, BorderLayout.SOUTH);
                    int reply = JOptionPane.showConfirmDialog(null, panelinformazioni, "Inserire nome, cognome, colore e giocatori della nuova squadra", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if( reply == JOptionPane.OK_OPTION ) {
                        if( !nomefield.getText().equals("") && !colorefield.getText().equals("") && !cittafield.getText().equals("") ) {
                            for( int i : jlistgiocatori.getSelectedIndices() ) {
                                giocatorigen.add(giocatori.get(i));
                            }
                            if( giocatorigen.size() > 1 ) {
                                String nome = nomefield.getText();
                                String colore = colorefield.getText();
                                String citta = cittafield.getText();
                                Squadra squadra = new Squadra(nome, colore, citta, giocatorigen, true);
                                squadre.add(squadra);
                            } else {
                            JOptionPane.showMessageDialog(null, "E' NECESSARIO SELEZIONARE ALMENO DUE GIOCATORI!", "Attenzione", JOptionPane.ERROR_MESSAGE);                                
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "PARAMETRI MANCANTI!", "Attenzione", JOptionPane.ERROR_MESSAGE);
                        }
                    }   
                } else {
                    int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come organizzatore per creare nuovi elementi!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                          JFrame gentorneoGUI = new GeneratoreTorneoGUI(gentorneo, squadre, arbitri);
                          gentorneoGUI.setVisible(true);
                          gentorneoGUI.setSize(1000, 675);
                          gentorneoGUI.setLocation(400, 250);
                        }
                }
            }
        };
        generasquadra.addActionListener(GENERASQUADRAlistener);
        
        //---------------------------------------------------------------------------
        
        ActionListener CHIUDIlistener = new ActionListener() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        chiudibutton.addActionListener(CHIUDIlistener);
        
        //---------------------------------------------------------------------------
        
        InserimentoElementi();
        PosizionamentoPanels();
    }
}
