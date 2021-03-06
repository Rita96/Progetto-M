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
import javax.swing.JTextField;
import torneo.Arbitro;
import torneo.Cartellino;
import torneo.ColoreCartellino;
import torneo.Giocatore;
import torneo.Goal;
import torneo.Partita;
import torneo.Torneo;

/**
 *
 * @author deboraquaini
 */
public class GiocatoreGUI extends JFrame {
    
    private Partita partita;
    private Giocatore giocatore;
    private Torneo torneo;
    private List<Arbitro> a;
    
    private JPanel panelDATI;
    private JPanel panelBOTTONI;
    
    private JButton AGGIUNGICARTbutton;
    private JButton RIMUOVICARTbutton;
    
    private JLabel NOMElabel;
    private JTextField NOMEfield;
    
    private JLabel COGNOMElabel;
    private JTextField COGNOMEfield;
    
    private JLabel NUMEROlabel;
    private JTextField NUMEROfield;
    
    private JLabel GOALlabel;
    private JScrollPane GOALfield;
    private DefaultListModel modelGOAL;
    private JList JlistGOAL;
    
    private JLabel CARTELLINOlabel;
    private JScrollPane CARTELLINOfield;
    private DefaultListModel modelCARTELLINI;
    private JList JlistCARTELLINI;
    
    public GiocatoreGUI(Partita partita, Giocatore giocatore, Torneo torneo) {
        this.partita = partita;
        this.giocatore = giocatore;
        this.torneo = torneo;
        for( Partita p : torneo.getPartite() ) {
            if(!(a.contains(p.getArbitro()))) {
                a.add(p.getArbitro());
            }
        }
        setTitle("SCHEDA GIOCATORE "+giocatore.getNome()+" "+giocatore.getCognome()+" NUMERO "+giocatore.getNumero());
        initComponents();
    }

    private void initComponents() {
        
        AGGIUNGICARTbutton = new JButton("Aggiungi cartellino");
        RIMUOVICARTbutton = new JButton("Rimuovi cartellino");
        
        panelDATI = new JPanel();
        panelDATI.setLayout(new GridLayout(5, 2, 0, 0));
        panelDATI.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 100));
        
        panelBOTTONI = new JPanel();
        panelBOTTONI.setLayout(new GridLayout(1, 2, 0, 0));
        panelBOTTONI.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
        
        NOMElabel = new JLabel("Nome:   ");
        NOMEfield = new JTextField(10);
        NOMEfield.setEditable(false);
        NOMEfield.setText(giocatore.getNome());
        
        COGNOMElabel = new JLabel("Cognome:   ");
        COGNOMEfield = new JTextField(10);
        COGNOMEfield.setEditable(false);
        COGNOMEfield.setText(giocatore.getCognome());
        
        NUMEROlabel = new JLabel("Numero:   ");
        NUMEROfield = new JTextField(10);
        NUMEROfield.setEditable(false);
        NUMEROfield.setText(String.valueOf(giocatore.getNumero()));
        
        GOALlabel = new JLabel("Goal:   ");
        modelGOAL = new DefaultListModel();
        if( partita.getSquadraCasa().getGiocatori().contains(giocatore) ) {
            for( Goal g : partita.getGoalSquadraCasaList() ) {
                if( g.getGiocatore() == giocatore )  {
                    modelGOAL.addElement(g.toString());
                }
            }
            JlistGOAL = new JList(modelGOAL);
            GOALfield = new JScrollPane(JlistGOAL);      
        } else if( partita.getSquadraOspite().getGiocatori().contains(giocatore) ) {
            for( Goal g : partita.getGoalSquadraOspiteList() ) {
                if( g.getGiocatore() == giocatore ) {
                    modelGOAL.addElement(g.toString());
                }
            }
            JlistGOAL = new JList(modelGOAL);
            GOALfield = new JScrollPane(JlistGOAL);       
        }
        
        CARTELLINOlabel = new JLabel("Cartellini:   ");
        modelCARTELLINI = new DefaultListModel();
        if( partita.getSquadraCasa().getGiocatori().contains(giocatore) ) {
            for( Cartellino c : partita.getCartelliniSquadraCasa() ) {
                if( c.getGiocatore() == giocatore ) {
                    modelCARTELLINI.addElement(c);
                }
            }
            JlistCARTELLINI = new JList(modelCARTELLINI);
            CARTELLINOfield = new JScrollPane(JlistCARTELLINI);
        } else if( partita.getSquadraOspite().getGiocatori().contains(giocatore) ) {
            for( Cartellino c : partita.getCartelliniSquadraOspite() ) {
                if( c.getGiocatore() == giocatore ) {
                    modelCARTELLINI.addElement(c);
                }
            }
            JlistCARTELLINI = new JList(modelCARTELLINI);
            CARTELLINOfield = new JScrollPane(JlistCARTELLINI);
        }
        
        //---------------------------------------------------------------------------
        
        ActionListener AGGCARTlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( partita.getArbitro().getAutenticazione().equals("AUTENTICATO") ) {
                    JComboBox jcombo = new JComboBox(ColoreCartellino.values());
                    int i = JOptionPane.showConfirmDialog(null, jcombo, "Inserire colore cartellino:", JOptionPane.YES_NO_OPTION);
                    if( jcombo.getSelectedItem() == ColoreCartellino.GIALLO && i == JOptionPane.YES_OPTION ) {
                        Cartellino cartellinoGIALLO = new Cartellino(ColoreCartellino.GIALLO, giocatore);
                        modelCARTELLINI.addElement(cartellinoGIALLO);
                        partita.setCartellino(ColoreCartellino.GIALLO, giocatore);
                        jcombo.setVisible(false);
                    } else if( jcombo.getSelectedItem() == ColoreCartellino.ROSSO && i == JOptionPane.YES_OPTION ) {
                        Cartellino cartellinoROSSO = new Cartellino(ColoreCartellino.ROSSO, giocatore);
                        modelCARTELLINI.addElement(cartellinoROSSO);
                        partita.setCartellino(ColoreCartellino.ROSSO, giocatore);
                        jcombo.setVisible(false);
                    } else if( i == JOptionPane.NO_OPTION )
                        jcombo.setVisible(false);
                } else {
                        int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come arbitro per modificare i dati!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            JFrame arbitroGUI = new ArbitroGUI(a);
                            arbitroGUI.setSize(1000, 610);
                            arbitroGUI.setLocation(400, 250);
                            arbitroGUI.setVisible(true);
                        }
                    }
            }
        };
        AGGIUNGICARTbutton.addActionListener(AGGCARTlistener);
        
        //----------------------------------------------------------------------------
        
        ActionListener RIMCARTlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( partita.getArbitro().getAutenticazione().equals("AUTENTICATO") ) {
                    int index = JlistCARTELLINI.getSelectedIndex();
                    if( JlistCARTELLINI.isSelectionEmpty() == true && modelCARTELLINI.isEmpty() == true ) {
                        JOptionPane.showConfirmDialog(null, "Lista di cartellini del giocatore "+giocatore.getNome()+" "+giocatore.getCognome()+" vuota.", "Attenzione", JOptionPane.CLOSED_OPTION);
                    }
                    if( partita.getSquadraCasa().getGiocatori().contains(giocatore) && JlistCARTELLINI.isSelectionEmpty() == false ) {
                        Cartellino c = partita.getCartelliniSquadraCasa().get(index);
                        modelCARTELLINI.removeElementAt(index);
                        partita.getCartelliniSquadraCasa().remove(c);
                    } else if( partita.getSquadraOspite().getGiocatori().contains(giocatore) && JlistCARTELLINI.isSelectionEmpty() == false ) {
                        Cartellino c = partita.getCartelliniSquadraOspite().get(index);
                        modelCARTELLINI.removeElementAt(index);
                        partita.getCartelliniSquadraOspite().remove(c);
                    }
                    } else {
                        int reply = JOptionPane.showConfirmDialog(null, "E' necessario autenticarsi come arbitro per modificare i dati!", "Attenzione!", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            JFrame arbitroGUI = new ArbitroGUI(a);
                            arbitroGUI.setSize(1000, 610);
                            arbitroGUI.setLocation(400, 250);
                            arbitroGUI.setVisible(true);
                        }
                }
            }
        };
        RIMUOVICARTbutton.addActionListener(RIMCARTlistener);
        
        //----------------------------------------------------------------------------
        
        panelDATI.add(NOMElabel, BorderLayout.WEST);
        panelDATI.add(NOMEfield, BorderLayout.EAST);
        
        panelDATI.add(COGNOMElabel, BorderLayout.WEST);
        panelDATI.add(COGNOMEfield, BorderLayout.EAST);
        
        panelDATI.add(NUMEROlabel, BorderLayout.WEST);
        panelDATI.add(NUMEROfield, BorderLayout.EAST);
        
        panelDATI.add(GOALlabel, BorderLayout.WEST);
        panelDATI.add(GOALfield, BorderLayout.EAST);
        
        panelDATI.add(CARTELLINOlabel, BorderLayout.WEST);
        panelDATI.add(CARTELLINOfield, BorderLayout.EAST);
        
        panelBOTTONI.add(AGGIUNGICARTbutton);     
        panelBOTTONI.add(RIMUOVICARTbutton);
        
        add(panelDATI, BorderLayout.CENTER);
        add(panelBOTTONI, BorderLayout.SOUTH);
        pack();
    }
}
