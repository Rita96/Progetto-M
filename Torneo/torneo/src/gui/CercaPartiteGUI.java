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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import torneo.Arbitro;
import torneo.EliminazioneDiretta;
import torneo.GeneratoreTorneo;
import torneo.Italiana;
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
    private JLabel labelNOME;
    private JLabel labelTIPOLOGIA;
    private JTextField fieldNOME;
    private JTextField fieldTIPOLOGIA;
    private JTextArea sezionecomunicazioni;
    private JList list;
    private JScrollPane listscrollpane;
    
    public CercaPartiteGUI(Torneo torneo, GeneratoreTorneo gentorneo, List<Squadra> squadre, List<Arbitro> arbitri) {
        this.torneo = torneo;
        this.gentorneo = gentorneo;
        this.arbitri = arbitri;
        this.squadre = squadre;
        this.setTitle("PARTITE TORNEO "+torneo.getNome());
        initComponents();
    }

    private void initComponents() {
        panelcampi = new JPanel();
        panelcampi.setLayout(new GridLayout(2, 1, 2, 2));
        panelcampi.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 100));
        panelbottoni = new JPanel();
        panelbottoni.setLayout(new GridLayout(1, 2, 2, 2));
        panelbottoni.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
        
        cerca = new JButton("Cerca Classifica");
        indietro = new JButton("Indietro");
        
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
        
        //------------------------------------------------------------------------
        
        MouseListener m = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList theList = (JList) e.getSource();
                if (e.getClickCount() == 2) {
                    int index = theList.locationToIndex(e.getPoint());
                    JFrame modificarisGUI = new ModificaRisultatiGUI(torneo.getPartite().get(index), torneo, squadre, arbitri, gentorneo);
                    modificarisGUI.setSize(1000, 655);
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
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
            
        };
        indietro.addActionListener(al);
        
        //------------------------------------------------------------------------
        
        ActionListener cercalistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame classificagui = new ClassificaGUI(torneo);
                classificagui.setSize(1000, 655);
                classificagui.setLocation(400, 250);
                classificagui.setVisible(true);
            }
            
        };
        cerca.addActionListener(cercalistener);
        
        //------------------------------------------------------------------------
        
        panelcampi.add(labelNOME, BorderLayout.WEST);
        panelcampi.add(fieldNOME, BorderLayout.EAST);
        panelcampi.add(labelTIPOLOGIA, BorderLayout.WEST);
        panelcampi.add(fieldTIPOLOGIA, BorderLayout.EAST);
        panelbottoni.add(cerca, BorderLayout.EAST);
        panelbottoni.add(indietro, BorderLayout.WEST);
        add(panelcampi, BorderLayout.NORTH);
        add(listscrollpane, BorderLayout.CENTER);
        add(panelbottoni, BorderLayout.SOUTH);
    }
}
