package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Carte;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueMain extends JPanel {

    private JLabel titre;
    private ArrayList<Carte> cartes;
    private JPanel grille;

    public VueMain(ArrayList<Carte> cartes){
        this.cartes = cartes;
        titre = new JLabel("Carte Joueur");

        this.setLayout(new BorderLayout());
        titre = new JLabel("Carte Joueur");
        this.add(titre, BorderLayout.NORTH);

        if (cartes.size() <= 5) {
            grille = new JPanel(new GridLayout(1, 5));
        }
        else {
            grille = new JPanel(new GridLayout(2, 5));
        }

        for (int i = 0; i < cartes.size(); i++) {
            grille.add(cartes.get(i).getImage());
        }
        this.add(grille);

    }

}
