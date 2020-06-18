/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueFinPartie extends TitleFrame {
    

    
    private JLabel  textFin;
    private String message;
    private final Button relancer;
    private final JButton quitter;
    
    public VueFinPartie(String message) {
        super("L'île Interdite");
        this.message = message;
        
        
        textFin = new JLabel("<html><body><font color='blue'>"+ message +"</body></html>"); //penser a mettre un <br> dans le message pour un affichage propre
        textFin.setFont(new Font("Roboto",Font.BOLD,15));
        this.setSize(400, 250);
        this.setLocation(400, 0);
        
        
        JPanel panelBas = new JPanel(new GridLayout(1,3));
        JPanel panelHaut = new JPanel();
        
        JLabel blank = new JLabel(" ");
        
        relancer = new Button("Relancer",150, 35, Color.GREEN, new Color(120, 255, 120));
        quitter = new Button("Quitter", 150, 35, Color.RED, new Color(255, 120, 120));
        panelBas.add(quitter);
        panelBas.add(blank);
        panelBas.add(relancer);
        
        textFin.setBackground(Color.red);
        panelHaut.add(textFin);
        
        main.setLayout(new BorderLayout());

        main.add(panelHaut,BorderLayout.NORTH);
        main.add(panelBas, BorderLayout.SOUTH);
        
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
        
        
        
        this.setVisible(true);
        this.centrer();
    }
    
    
    
}
