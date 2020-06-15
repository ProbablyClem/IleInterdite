package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Carte;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueMain extends JPanel {

    private JLabel titre;
    private ArrayList<Carte> cartes;
    private JPanel lignehaut;

    public VueMain(ArrayList<Carte> cartes){
        this.cartes = cartes;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        titre = new JLabel("Cartes Joueur");
        this.add(titre, BorderLayout.NORTH);

        lignehaut = new JPanel();

        if (cartes.size() > 5) {
            for (int i = 0; i < 5; i++) {
                ImageFrame imageFrame = cartes.get(i).getImage();
                imageFrame.setMinimumSize(new Dimension(100, 100));
                imageFrame.setPreferredSize(new Dimension(150, 150));
                imageFrame.setMaximumSize(new Dimension(200, 200));
                lignehaut.add(imageFrame);
            }
            JPanel lignebas = new JPanel();
            for (int i = 5; i < cartes.size(); i++) {
                ImageFrame imageFrame = cartes.get(i).getImage();
                imageFrame.setMinimumSize(new Dimension(100, 100));
                imageFrame.setPreferredSize(new Dimension(150, 150));
                imageFrame.setMaximumSize(new Dimension(200, 200));
                lignebas.add(imageFrame);
            }
            this.add(lignehaut, BorderLayout.CENTER);
            this.add(lignebas, BorderLayout.SOUTH);
        }
        else {
            for (int i = 0; i < cartes.size(); i++) {
                ImageFrame imageFrame = cartes.get(i).getImage();
                imageFrame.setMinimumSize(new Dimension(100, 100));
                imageFrame.setPreferredSize(new Dimension(150, 150));
                imageFrame.setMaximumSize(new Dimension(200, 200));
                lignehaut.add(imageFrame);
            }
            this.add(lignehaut);
        }






    }
}
