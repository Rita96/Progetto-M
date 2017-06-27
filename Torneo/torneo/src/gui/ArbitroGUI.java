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
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import torneo.Arbitro;
import torneo.Autenticazione;

/**
 *
 * @author deboraquaini
 */
public class ArbitroGUI extends JFrame {
    
    JButton LogInButton;
    JButton BackButton;
    JButton LogoutButton;
    
    CercaPartiteGUI MatchesGUI;
    
    List<Arbitro> arbitriLIST;
    List List;
    
    JPanel InfosPanel;
    JPanel ButtonsPanel;
    JPanel TextPanel;
    
    JLabel labelCF;
    JLabel labelPASSWORD;
    
    JTextField fieldCF;
    JTextField fieldPASSWORD;
    
    JTextArea TextArea;
    
    /**
     * Lista di tutti gli arbitri registrati nel database o creati dall' organizzatore
     * @param a 
     */
    public ArbitroGUI(List<Arbitro> a) {
        this.arbitriLIST = a;
        this.setTitle("Autenticazione Arbitro");
        InitComponents();
    }
    
    /**
     * Creazione degli elementi presenti nel frame
     */
    public void CreazioneElementi() {
        LogInButton = new JButton("Log In");
        BackButton = new JButton("Indietro");
        LogoutButton = new JButton("Log out");
        
        InfosPanel = new JPanel();
        InfosPanel.setLayout(new GridLayout(2, 1, 2, 2));
        InfosPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 100));
        ButtonsPanel = new JPanel();
        ButtonsPanel.setLayout(new GridLayout(1, 3, 2, 2));
        ButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
        TextPanel = new JPanel();
        TextPanel.setLayout(new GridLayout(1, 1, 2, 2));
        TextPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        
        fieldCF = new JTextField(30);
        fieldCF.setText("");
        fieldCF.setEditable(true);
        labelCF = new JLabel("Codice fiscale: ");
        labelCF.setLabelFor(fieldCF);
        fieldPASSWORD = new JTextField(30);
        fieldPASSWORD.setText("");
        fieldPASSWORD.setEditable(true);
        labelPASSWORD = new JLabel("Password: ");
        labelPASSWORD.setLabelFor(fieldPASSWORD);
        
        TextArea = new JTextArea();
        TextArea.setText("Gli arbitri, loggandosi, possono modificare le partite in cui arbitrano - ");
        TextArea.setEditable(false);
    }
    
    /**
     * Inserimento dei vari elementi negli appositi panels 
     */
    public void InserimentoElementi() {
        InfosPanel.add(labelCF, BorderLayout.WEST);
        InfosPanel.add(fieldCF, BorderLayout.EAST);
        InfosPanel.add(labelPASSWORD, BorderLayout.WEST);
        InfosPanel.add(fieldPASSWORD, BorderLayout.EAST);
        
        ButtonsPanel.add(LogInButton, BorderLayout.WEST);
        ButtonsPanel.add(LogoutButton, BorderLayout.CENTER);
        ButtonsPanel.add(BackButton, BorderLayout.EAST);
        
        TextPanel.add(TextArea);
    }
    
    /**
     * Posizionamento dei panels nel frame 
     */
    public void PosizionamentoPanels() {
        add(InfosPanel, BorderLayout.NORTH);
        add(TextPanel, BorderLayout.CENTER);
        add(ButtonsPanel, BorderLayout.SOUTH);
        pack();
    }

    private void InitComponents() {
        
        CreazioneElementi();
        
        //---------------------------------------------------------------------------------
        
        ActionListener RefereeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String code = fieldCF.getText();
                String password = fieldPASSWORD.getText();
                int counter = 0;
                for( Arbitro arbitro : arbitriLIST ) {
                    arbitro.logIn(code, password);
                    if( arbitro.getAutenticazione().equals(Autenticazione.AUTENTICATO) ) {
                        TextArea.setText("Gli arbitri, loggandosi, possono modificare le partite in cui arbitrano - "+arbitro.logIn(code, password)+"\n\n");
                    } else {
                        counter++;
                    }
                }
                if( counter == arbitriLIST.size() ) {
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
        
        ActionListener LogoutListener = new ActionListener() {
            
            /**
             * 
             * @param e 
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = fieldCF.getText();
                String password = fieldPASSWORD.getText();
                int counter = 0;
                for( Arbitro arbitro : arbitriLIST ) {
                    if( arbitro.getCodiceFiscale().equals(code) && arbitro.getPassword().equals(password) ) {
                        TextArea.setText("Gli arbitri, loggandosi, possono modificare le partite in cui arbitrano - "+arbitro.logOut(code, password)+"\n\n");
                        fieldCF.setText("");
                        fieldPASSWORD.setText("");
                    } else {
                        counter++;
                    }
                }
                if( counter == arbitriLIST.size() ) {
                    TextArea.setText("Gli arbitri, loggandosi, possono modificare le partite in cui arbitrano - Log out già effettuato.");
                }
            }
            
        };
        LogoutButton.addActionListener(LogoutListener);
        
        //---------------------------------------------------------------------------------

        InserimentoElementi();
        PosizionamentoPanels();
    }
    
}
