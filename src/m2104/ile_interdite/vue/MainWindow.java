package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Grille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends TitleFrame {
    private IHM ihm;
    private JPanel niveauPanel;
    private VueNiveau vueNiveau;
    private VueGrille grillePanel;
    private VueAventurier aventurierPanel;
    private Grille grille;

    public MainWindow(IHM ihm, Grille grille, VueAventurier v, int niveau){
        super("L'île Interdite", "src/images/bgile.png");

        this.grille = grille;
        this.ihm = ihm;
        main.setLayout(new BorderLayout());
        main.setBackground(Color.white);

        this.niveauPanel = new JPanel(new BorderLayout());
        this.vueNiveau = new VueNiveau(niveau);
        this.niveauPanel.add(vueNiveau, BorderLayout.SOUTH);
        JButton exit = new Button("Quitter", 0, 35, Color.RED, new Color(255, 120, 120));
        exit.setForeground(Color.white);
        exit.setFont(new Font("Roboto",Font.BOLD,17));
        exit.setOpaque(false);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.niveauPanel.add(exit, BorderLayout.NORTH);
        niveauPanel.setOpaque(false);

        this.grillePanel = new VueGrille(grille, ihm);
        this.aventurierPanel = v;
        v.update(v.getAventurier());



        main.add(niveauPanel, BorderLayout.WEST);
        main.add(grillePanel, BorderLayout.CENTER);
        main.add(aventurierPanel, BorderLayout.EAST);
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setSize(1580, 800);
        this.centrer();
    }


    public VueGrille getGrillePanel() {
        return grillePanel;
    }

    public VueAventurier getAventurierPanel() {
        return aventurierPanel;
    }

    public void activerGrille(){
        this.grillePanel.etatGrille(true);
    }

    public void desactiverGrille(){
        this.grillePanel.etatGrille(false);
    }

    public VueNiveau getVueNiveau() {
        return vueNiveau;
    }

}
