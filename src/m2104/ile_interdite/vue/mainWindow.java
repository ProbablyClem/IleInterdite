package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.modele.Grille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainWindow extends JFrame{
    private IHM ihm;
    private JPanel mainPanel;
    private JPanel niveauPanel;
    private VueNiveau vueNiveau;
    private VueGrille grillePanel;
    private VueAventurier aventurierPanel;
    private Grille grille;

    public mainWindow(IHM ihm, Grille grille, Aventurier a){
        this.grille = grille;
        this.ihm = ihm;

        this.mainPanel = new JPanel(new BorderLayout());
        this.niveauPanel = new JPanel(new BorderLayout());
        this.vueNiveau = new VueNiveau(2);
        this.niveauPanel.add(vueNiveau, BorderLayout.SOUTH);
        JButton exit = new JButton("Quitter");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.niveauPanel.add(exit, BorderLayout.NORTH);

        this.grillePanel = new VueGrille(grille);
        this.aventurierPanel = new VueAventurier(ihm, a, a.actionSpeciale());

        mainPanel.add(niveauPanel, BorderLayout.WEST);
        mainPanel.add(grillePanel, BorderLayout.CENTER);
        mainPanel.add(aventurierPanel, BorderLayout.EAST);

        this.setContentPane(mainPanel);
        this.setSize(1750, 800);
        this.setLocation(400, 150);
        this.setVisible(true);
    }

    public void setAventurier(Aventurier a) {
        aventurierPanel = new VueAventurier(ihm, a, a.actionSpeciale());
        repaint();
    }
}
