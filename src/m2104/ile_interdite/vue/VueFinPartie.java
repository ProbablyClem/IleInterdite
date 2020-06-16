/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import m2104.ile_interdite.util.Message;

/**
 *
 * @author baptd
 */
public class VueFinPartie {
    private final IHM ihm;
    private final JFrame fenetre;

    
    private JLabel  textFin = new JLabel("<html><body><font color='blue'>A gagner ou pas shepa encore</body></html>");
    
    private final JButton relancer = new JButton("Relancer");
    private final JButton quitter = new JButton("Quitter");
    
    public VueFinPartie(IHM ihm) {
        this.ihm = ihm;

        fenetre = new JFrame();
        fenetre.setLayout(new BorderLayout());
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(400, 150);
        fenetre.setLocation(400, 0);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel panelBas = new JPanel(new GridLayout(1,3));
        JPanel panelHaut = new JPanel();
        
        JLabel blank = new JLabel(" ");
        
        panelBas.add(relancer);
        panelBas.add(blank);
        panelBas.add(quitter);
        
        textFin.setBackground(Color.red);
        panelHaut.add(textFin);
        
        mainPanel.add(panelHaut,BorderLayout.NORTH);
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        
        relancer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        
        fenetre.add(mainPanel);
        fenetre.setVisible(true);
    }
    
    
    
}
