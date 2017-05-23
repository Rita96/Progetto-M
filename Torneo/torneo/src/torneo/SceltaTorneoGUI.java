/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author debora
 */
public class SceltaTorneoGUI extends JFrame {

    private List<Torneo> tornei;
    private List<Arbitro> a;
    
    private JScrollPane scrollpane;
    
    private JList list;
    private DefaultListModel listmodel;
    
    private JPanel panelLIST;
    private JPanel panelBOTTONI;
    
    private JButton identificazionearbitro;
    private JButton cercapartite;
    
    public SceltaTorneoGUI(List<Torneo> tornei) {
        this.tornei = tornei;
        setTitle("TORNEI");
        setSize(1000, 565);
        initComponents();
    }

    private void initComponents() {
        
        identificazionearbitro = new JButton("Identificazione arbitro");
        cercapartite = new JButton("Cerca partite");
        
        panelBOTTONI = new JPanel();
        panelBOTTONI.setLayout(new GridLayout(1, 2, 0, 0));
        panelBOTTONI.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
        
        a = new ArrayList();
        
        panelLIST = new JPanel();
        panelLIST.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 100));
        listmodel = new DefaultListModel();
        for( Torneo t : tornei ) {
            listmodel.addElement(t.toString());
        }
        list = new JList(listmodel);
        list.setSelectedIndex(0);
        scrollpane = new JScrollPane(list);
        scrollpane.setVisible(true);
        
        //--------------------------------------------------------------------------
        
        ActionListener IDlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                Torneo t = tornei.get(index);
                for( Partita p : t.getPartite() ) {
                    if(!(a.contains(p.getArbitro()))) {
                        a.add(p.getArbitro());
                    }
                }
                JFrame arbitrogui = new ArbitroGUI(a, t);
                arbitrogui.setSize(1000, 655);
                arbitrogui.setLocation(400, 250);
                arbitrogui.setVisible(true);
            }
            
        };
        identificazionearbitro.addActionListener(IDlistener);
        
        //---------------------------------------------------------------------------
        
        ActionListener PARTITElistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                Torneo t = tornei.get(index);
                for( Partita p : t.getPartite() ) {
                    if(!(a.contains(p.getArbitro()))) {
                        a.add(p.getArbitro());
                    }
                }
                JFrame cercapartitegui = new CercaPartiteGUI(t);
                cercapartitegui.setSize(1000, 655);
                cercapartitegui.setLocation(400, 250);
                cercapartitegui.setVisible(true);
            }
        };
        cercapartite.addActionListener(PARTITElistener);
        
        //---------------------------------------------------------------------------
        
        panelLIST.add(scrollpane, BorderLayout.CENTER);
        panelBOTTONI.add(identificazionearbitro, BorderLayout.WEST);
        panelBOTTONI.add(cercapartite, BorderLayout.WEST);
        add(panelLIST, BorderLayout.CENTER);
        add(panelBOTTONI, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
