package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Carte;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueMain extends JPanel {

    private JLabel titre;
    private ArrayList<Carte> cartes;
    private JPanel pheader;
    private JPanel jcartes;

    public VueMain(ArrayList<Carte> cartes){
        this.cartes = cartes;
        this.setLayout(new BorderLayout());
        titre = new JLabel("Cartes Joueur");
        pheader= new JPanel();
        titre.setFont(new Font("Roboto",Font.BOLD,20));
        titre.setHorizontalAlignment(JLabel.CENTER);

        titre.setVerticalAlignment(JLabel.CENTER);
        pheader.setBackground(Color.white);

        pheader.add(titre, BorderLayout.NORTH);
        this.add(pheader,BorderLayout.NORTH);



        jcartes = new JPanel();
        jcartes.setLayout(new GridLayout(2,cartes.size(),0,10));
        jcartes.setBackground(Color.white);

        for (Carte C: cartes) {
            ImagePanel panel = C.getImage();
            panel.setBackground(Color.white);
            jcartes.add(panel);

    }
        this.add(jcartes,BorderLayout.CENTER);

    }
}
