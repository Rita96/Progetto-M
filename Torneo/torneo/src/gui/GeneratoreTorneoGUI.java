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
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
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
import torneo.GeneratoreTorneo;
import torneo.Squadra;

/**
 *
 * @author deboraquaini
 */
public class GeneratoreTorneoGUI extends JFrame {
    
    private GeneratoreTorneo gentorneo;
    private List<Squadra> squadre;
    private List<Arbitro> arbitri;
    
    // liste degli elementi selezionati nella GUI
    List<Squadra> squadregen = new ArrayList<>();
    List<Arbitro> arbitrigen = new ArrayList<>();
    
    private JLabel stringagen;
    private JLabel stringalogin;
    private JLabel blankspaceuno;
    private JLabel blankspacedue;
    private JLabel nometorneolabel;
    private JTextField nometorneofield;
    private JLabel annotorneolabel;
    private JTextField annotorneofield;
    private JLabel cflabel;
    private JTextField cffield;
    private JLabel passwordlabel;
    private JTextField passwordfield;
    private JLabel tipolabel;
    private JComboBox tipofield;
    
    private JPanel panelalto;
    private JPanel panelcentrale;
    private JPanel panelbasso;
    
    private JButton okbutton;
    private JButton loginbutton;
    private JButton logoutbutton;
    private JButton backbutton;
    
    public GeneratoreTorneoGUI(GeneratoreTorneo gentorneo, List<Squadra> squadre, List<Arbitro> arbitri) {
        this.gentorneo = gentorneo;
        this.squadre = squadre;
        this.arbitri = arbitri;
        setTitle("GENERATORE TORNEO E LOG IN ORGANIZZATORE");
        initComponents();
    }

    private void initComponents() {
        
        okbutton = new JButton("Crea torneo");
        loginbutton = new JButton("Log in");
        logoutbutton = new JButton("Log out");
        backbutton = new JButton("Indietro");
        
        panelalto = new JPanel();
        panelalto.setLayout(new GridLayout(1, 2, 0, 0));
        panelalto.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 100));
        panelcentrale = new JPanel();
        panelcentrale.setLayout(new GridLayout(8, 2, 0, 0));
        panelcentrale.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        panelbasso = new JPanel();
        panelbasso.setLayout(new GridLayout(1, 4, 0, 0));
        panelbasso.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
        
       
        nometorneolabel = new JLabel("Nome torneo: ");
        nometorneofield = new JTextField();
        annotorneolabel = new JLabel("Anno torneo: ");
        annotorneofield = new JTextField();
        cflabel = new JLabel("Codice fiscale: ");
        cffield = new JTextField();
        passwordlabel = new JLabel("Password: ");
        passwordfield = new JTextField();
        tipolabel = new JLabel("Tipo: ");
        String[] tipologie = {"ELIMINAZIONE DIRETTA", "ITALIANA" };
        tipofield = new JComboBox(tipologie);
        
        stringagen = new JLabel("AREA GENERAZIONE TORNEO");
        stringalogin = new JLabel("AREA LOG IN GENERATORE");
        blankspaceuno = new JLabel("");
        blankspacedue = new JLabel("");
        
        DefaultListModel modelSQUADRE = new DefaultListModel();
        for( Squadra s : squadre ) {
            modelSQUADRE.addElement(s);
        }
        final JList jlistSQUADRE = new JList(modelSQUADRE);
        jlistSQUADRE.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jlistSQUADRE.setSelectedIndex(0);
        jlistSQUADRE.setSelectionBackground(Color.cyan);
        JScrollPane SQUADREscroll = new JScrollPane(jlistSQUADRE);
        SQUADREscroll.setVisible(true);
        SQUADREscroll.setBorder(BorderFactory.createTitledBorder("LISTA SQUADRE - Selezionare più squadre"));
        
        DefaultListModel modelARBITRI = new DefaultListModel();
        for( Arbitro a : arbitri ) {
            modelARBITRI.addElement(a);
        }
        final JList jlistARBITRI = new JList(modelARBITRI);
        jlistARBITRI.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jlistARBITRI.setSelectedIndex(0);
        jlistARBITRI.setSelectionBackground(Color.yellow);
        JScrollPane ARBITRIscroll = new JScrollPane(jlistARBITRI);
        ARBITRIscroll.setVisible(true);
        ARBITRIscroll.setBorder(BorderFactory.createTitledBorder("LISTA ARBITRI"));
        
        //---------------------------------------------------------------------------------
        
        ActionListener OKbuttonlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] potenzedidue = {2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
                int counter = 0;
                squadregen.clear();
                arbitrigen.clear();
                System.out.println(squadregen);
                System.out.println(arbitrigen);
                if( gentorneo.getAutenticazione().equals("AUTENTICATO") ) {
                    String nometorneo = nometorneofield.getText();
                    for( int i : jlistSQUADRE.getSelectedIndices() ) {
                        squadregen.add(squadre.get(i));
                    }
                    for( int i : jlistARBITRI.getSelectedIndices() ) {
                        arbitrigen.add(arbitri.get(i));
                    }
                    System.out.println(squadregen);
                    System.out.println(arbitrigen);
                    if( (!(nometorneo.equals(""))) && (!(annotorneofield.getText().equals("")) && squadregen.size() != 0) && (arbitrigen.size() != 0) ) {
                        if( tipofield.getSelectedItem().toString().equals("ELIMINAZIONE DIRETTA") ) {
                            for( int i = 0; i < potenzedidue.length; i++ ) {
                                if( (squadregen.size() == potenzedidue[i]) && (arbitrigen.size() >= 2) ) {
                                    int anno = Integer.valueOf(annotorneofield.getText());
                                    gentorneo.eliminazioneDiretta(nometorneo, anno, squadregen, arbitrigen);
                                    stringagen.setText("AREA GENERAZIONE TORNEO - TORNEO AD EL. DIRETTA CREATO");
                                } else
                                    counter++;
                            }
                            if( (arbitrigen.size() < 2) || (counter == potenzedidue.length) )
                                JOptionPane.showMessageDialog(null, "IL TORNEO AD ELEMINAZIONE DIRETTA NECESSITA UN NUMERO DI SQUADRE CHE SIA POTENZA DI DUE E DI ALMENO 2 ARBITRI!", "Attenzione", JOptionPane.ERROR_MESSAGE);
                        } 
                        else if( tipofield.getSelectedItem().toString().equals("ITALIANA") ) {
                            if( squadregen.size() >= 2 ) {
                                int anno = Integer.valueOf(annotorneofield.getText());
                                gentorneo.italiana(nometorneo, anno, squadregen, arbitrigen);
                                stringagen.setText("AREA GENERAZIONE TORNEO - TORNEO ALL'ITALIANA CREATO");
                            } else {
                                JOptionPane.showMessageDialog(null, "IL TORNEO DEVE AVERE ALMENO DUE SQUADRE PARTECIPANTI!", "Attenzione", JOptionPane.ERROR_MESSAGE);                                
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INFORMAZIONI MANCANTI!", "Attenzione", JOptionPane.ERROR_MESSAGE);
                    }
                } else if( gentorneo.getAutenticazione().equals("NONAUTENTICATO") ) {
                    JOptionPane.showMessageDialog(null, "E' NECESSARIO AUTENTICARSI COME ORGANIZZATORE PER PROCEDERE!", "Attenzione", JOptionPane.OK_OPTION);
                }
            }
            
        };
        okbutton.addActionListener(OKbuttonlistener);
        
        //---------------------------------------------------------------------------------
        
        ActionListener LOGINbuttonlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cf = cffield.getText();
                String p = passwordfield.getText();
                stringalogin.setText("AREA LOG IN ORGANIZZATORE - "+gentorneo.logIn(cf, p));
                if( gentorneo.getAutenticazione().equals("AUTENTICATO") )
                    JOptionPane.showMessageDialog(null, "AUTENTICAZIONE AVVENUTA CORRETTAMENTE!", "Attenzione", JOptionPane.INFORMATION_MESSAGE);
                else if( gentorneo.getAutenticazione().equals("NONAUTENTICATO") )
                    JOptionPane.showMessageDialog(null, "CREDENZIALI SCORRETTE! IMPOSSIBILE PROCEDERE", "Attenzione", JOptionPane.OK_OPTION);
            }
            
        };
        loginbutton.addActionListener(LOGINbuttonlistener);
        
        //---------------------------------------------------------------------------------
        
        ActionListener LogoutListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = cffield.getText();
                String password = passwordfield.getText();
                if( gentorneo.getCf().equals(code) && gentorneo.getPassword().equals(password) ) {
                    stringalogin.setText("AREA LOG IN GENERATORE - "+gentorneo.logOut(code, password)+"\n\n");
                    cffield.setText("");
                    passwordfield.setText("");
                }
            }
        };
        logoutbutton.addActionListener(LogoutListener);
        
        //-------------------------------------------------------------------------------
        
        ActionListener BACKbuttonlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
            
        };
        backbutton.addActionListener(BACKbuttonlistener);
        
        //---------------------------------------------------------------------------------
        
        panelalto.add(SQUADREscroll, BorderLayout.WEST);
        panelalto.add(ARBITRIscroll, BorderLayout.EAST);
        panelcentrale.add(stringagen, BorderLayout.WEST);
        panelcentrale.add(blankspaceuno, BorderLayout.EAST);
        panelcentrale.add(nometorneolabel, BorderLayout.WEST);
        panelcentrale.add(nometorneofield, BorderLayout.EAST);
        panelcentrale.add(annotorneolabel, BorderLayout.WEST);
        panelcentrale.add(annotorneofield, BorderLayout.EAST);
        panelcentrale.add(tipolabel, BorderLayout.WEST);
        panelcentrale.add(tipofield, BorderLayout.EAST);
        panelcentrale.add(stringalogin, BorderLayout.WEST);
        panelcentrale.add(blankspacedue, BorderLayout.EAST);
        panelcentrale.add(cflabel, BorderLayout.WEST);
        panelcentrale.add(cffield, BorderLayout.EAST);
        panelcentrale.add(passwordlabel, BorderLayout.WEST);
        panelcentrale.add(passwordfield, BorderLayout.EAST);
        panelbasso.add(okbutton, BorderLayout.WEST);
        panelbasso.add(loginbutton, BorderLayout.CENTER);
        panelbasso.add(logoutbutton, BorderLayout.CENTER);
        panelbasso.add(backbutton, BorderLayout.EAST);
        add(panelalto, BorderLayout.NORTH);
        add(panelcentrale, BorderLayout.CENTER);
        add(panelbasso, BorderLayout.SOUTH);
        pack();
    }
}
