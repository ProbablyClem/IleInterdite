package m2104.ile_interdite.vue;

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

    public mainWindow(IHM ihm, Grille grille, VueAventurier v){

        this.grille = grille;
        this.ihm = ihm;

        this.mainPanel = new JPanel(new BorderLayout());
        this.niveauPanel = new JPanel(new BorderLayout());
        this.vueNiveau = new VueNiveau(2);
        this.niveauPanel.add(vueNiveau, BorderLayout.SOUTH);
        JButton exit = new Button("Quitter", 0, 35, Color.RED, new Color(255, 120, 120));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.niveauPanel.add(exit, BorderLayout.NORTH);

        this.grillePanel = new VueGrille(grille, ihm);
        this.aventurierPanel = v;

        mainPanel.add(niveauPanel, BorderLayout.WEST);
        mainPanel.add(grillePanel, BorderLayout.CENTER);
        mainPanel.add(aventurierPanel, BorderLayout.EAST);

        this.setContentPane(mainPanel);
        this.setSize(1750, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setAventurier(VueAventurier v) {
        aventurierPanel = v;
        repaint();
    }

    public VueGrille getGrillePanel() {
        return grillePanel;
    }

    public void activerGrille(){
        this.grillePanel.setEnabled(true);
    }

    public void desactiverGrille(){
        this.grillePanel.setEnabled(false);
    }
}
