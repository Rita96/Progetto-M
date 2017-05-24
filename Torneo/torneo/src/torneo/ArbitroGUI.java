/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

/**
 *
 * @author debora
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author debora
 */
public class ArbitroGUI extends JFrame {
    
    JButton LogInButton;
    JButton BackButton;
    
    CercaPartiteGUI MatchesGUI;
    
    List<Arbitro> RefereesList;
    Torneo Tournament;
    List List;
    
    JPanel InfosPanel;
    JPanel ButtonsPanel;
    JPanel TextPanel;
    
    JLabel labelNAME;
    JLabel labelSURNAME;
    JLabel labelCODE;
    JLabel labelPASSWORD;
    
    JTextField fieldNAME;
    JTextField fieldSURNAME;
    JTextField fieldCODE;
    JTextField fieldPASSWORD;
    
    JTextArea TextArea;
    
    public ArbitroGUI(List<Arbitro> a, Torneo torneo) {
        this.Tournament = torneo;
        this.RefereesList = a;
        for( Partita p : torneo.getPartite() ) {
            if(!(a.contains(p.getArbitro()))) {
                a.add(p.getArbitro());
            }
        }
        this.setTitle("Autenticazione Arbitro");
        InitComponents();
    }

    private void InitComponents() {
        
        LogInButton = new JButton("Log In");
        BackButton = new JButton("Indietro");
        
        InfosPanel = new JPanel();
        InfosPanel.setLayout(new GridLayout(5, 1, 2, 2));
        InfosPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 100));
        ButtonsPanel = new JPanel();
        ButtonsPanel.setLayout(new GridLayout(1, 2, 2, 2));
        ButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
        TextPanel = new JPanel();
        TextPanel.setLayout(new GridLayout(1, 1, 2, 2));
        TextPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        
        fieldNAME = new JTextField(30);
        fieldNAME.setText("");
        fieldNAME.setEditable(true);
        labelNAME = new JLabel("Name: ");
        labelNAME.setLabelFor(fieldNAME);
        fieldSURNAME = new JTextField(30);
        fieldSURNAME.setText("");
        fieldSURNAME.setEditable(true);
        labelSURNAME = new JLabel("Surname: ");
        labelSURNAME.setLabelFor(fieldSURNAME);
        fieldCODE = new JTextField(30);
        fieldCODE.setText("");
        fieldCODE.setEditable(true);
        labelCODE = new JLabel("Identification Code: ");
        labelCODE.setLabelFor(fieldCODE);
        fieldPASSWORD = new JTextField(30);
        fieldPASSWORD.setText("");
        fieldPASSWORD.setEditable(true);
        labelPASSWORD = new JLabel("Password: ");
        labelPASSWORD.setLabelFor(fieldPASSWORD);
        
        TextArea = new JTextArea();
        TextArea.setText("Gli arbitri, loggandosi, possono modificare le partite in cui arbitrano - ");
        TextArea.setEditable(false);
        
        //---------------------------------------------------------------------------------
        
        ActionListener RefereeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String code = fieldCODE.getText();
                String password = fieldPASSWORD.getText();
                int counter = 0;
                for( Arbitro arbitro : RefereesList ) {
                    arbitro.logIn(code, password);
                    if( arbitro.getAutenticazione().equals("AUTENTICATO") ) {
                        TextArea.setText("Gli arbitri, loggandosi, possono modificare le partite in cui arbitrano - "+arbitro.logIn(code, password)+"\n\n");
                        for( Partita p : Tournament.getPartite() ) {
                            if( p.getArbitro() == arbitro )
                                TextArea.append(p.toString()+"\n");
                        }
                    } else {
                        counter++;
                    }
                }
                if( counter == RefereesList.size() ) {
                    TextArea.setText("Gli arbitri, loggandosi, possono modificare le partite in cui arbitrano - CREDENZIALI SCORRETTE");
                }
            }
        };
        
        LogInButton.addActionListener(RefereeListener);
        
        //---------------------------------------------------------------------------------
        
        ActionListener BackListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
            
        };
        BackButton.addActionListener(BackListener);
        
        //---------------------------------------------------------------------------------
        
        InfosPanel.add(labelNAME, BorderLayout.WEST);
        InfosPanel.add(fieldNAME, BorderLayout.EAST);
        InfosPanel.add(labelSURNAME, BorderLayout.WEST);
        InfosPanel.add(fieldSURNAME, BorderLayout.EAST);
        InfosPanel.add(labelCODE, BorderLayout.WEST);
        InfosPanel.add(fieldCODE, BorderLayout.EAST);
        InfosPanel.add(labelPASSWORD, BorderLayout.WEST);
        InfosPanel.add(fieldPASSWORD, BorderLayout.EAST);
        ButtonsPanel.add(LogInButton);
        ButtonsPanel.add(BackButton);
        TextPanel.add(TextArea);
        add(InfosPanel, BorderLayout.NORTH);
        add(TextPanel, BorderLayout.CENTER);
        add(ButtonsPanel, BorderLayout.SOUTH);
        pack();
    }
    
}
