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
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import torneo.ClassificaEliminazioneDiretta;
import torneo.ClassificaItaliana;
import torneo.EliminazioneDiretta;
import torneo.Italiana;
import torneo.Squadra;
import torneo.Torneo;

/**
 *
 * @author deboraquaini
 */
public class ClassificaGUI extends JFrame {
    
    private Torneo torneo;
    private ClassificaEliminazioneDiretta classeldir;
    private ClassificaItaliana classita;
    
    private DefaultListModel listmodel;
    private JScrollPane scrollpane;
    private JList jlist;
    
    private JPanel panellabel;
    private JPanel panelarea;
    private JPanel panelbottone;
    
    private JLabel label;
    private JButton backbutton;
    private JButton refreshbutton;
    
    public ClassificaGUI(Torneo torneo) {
        this.torneo = torneo;
        if( torneo instanceof EliminazioneDiretta ) {
            classeldir = new ClassificaEliminazioneDiretta((EliminazioneDiretta) torneo);
        } else if( torneo instanceof Italiana ) {
            classita = new ClassificaItaliana((Italiana) torneo);
        }
        setTitle("CLASSIFICA DEL TORNEO "+torneo.getNome());
        initComponents();
    }

    private void initComponents() {
        
        label = new JLabel("Classifica del torneo");
        backbutton = new JButton("Indietro");
        refreshbutton = new JButton("Aggiorna");
        
        panellabel = new JPanel();
        
        panelarea = new JPanel();
        if( torneo instanceof EliminazioneDiretta ) {
            listmodel = new DefaultListModel();
            List<Squadra> lista = new ArrayList(classeldir.printRisultato());
            for( Squadra s : lista ) {
                listmodel.addElement(s);
            }
            jlist = new JList(listmodel);
            scrollpane = new JScrollPane(jlist);
            scrollpane.setVisible(true);
        } else if( torneo instanceof Italiana ) {
            JTextArea area = new JTextArea();
            area.setEditable(false);
            area.setText(classita.toString());
            scrollpane = new JScrollPane(area);
            scrollpane.setVisible(true);
        }
        panelarea = new JPanel();
        panelbottone = new JPanel();
        panelbottone.setLayout(new GridLayout(1, 2, 0, 0));
        panelbottone.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
        
        //---------------------------------------------------------------------------
        
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
            
        };
        backbutton.addActionListener(listener);
        
        //-----------------------------------------------------------------------------
        
        panellabel.add(label);
        panelarea.add(scrollpane);
        panelbottone.add(refreshbutton, BorderLayout.EAST);
        panelbottone.add(backbutton, BorderLayout.WEST);
        add(panellabel, BorderLayout.NORTH);
        add(panelarea, BorderLayout.CENTER);
        add(panelbottone, BorderLayout.SOUTH);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
