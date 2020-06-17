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
        this.setLayout(new BorderLayout());
        titre = new JLabel("Cartes Joueur");
        titre.setFont(new Font("Roboto",Font.BOLD,20));
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setVerticalAlignment(JLabel.CENTER);
        this.add(titre, BorderLayout.NORTH);
        this.setBackground(Color.WHITE);
        lignehaut = new JPanel();

        if (cartes.size() > 5) {
            for (int i = 0; i < 5; i++) {
                ImagePanel imagePanel = cartes.get(i).getImage();
                imagePanel.setMinimumSize(new Dimension(100, 100));
                imagePanel.setPreferredSize(new Dimension(150, 150));
                imagePanel.setMaximumSize(new Dimension(200, 200));
                lignehaut.add(imagePanel);
            }
            JPanel lignebas = new JPanel();
            for (int i = 5; i < cartes.size(); i++) {
                ImagePanel imagePanel = cartes.get(i).getImage();
                imagePanel.setMinimumSize(new Dimension(100, 100));
                imagePanel.setPreferredSize(new Dimension(150, 150));
                imagePanel.setMaximumSize(new Dimension(200, 200));
                lignebas.add(imagePanel);
            }
            this.add(lignehaut, BorderLayout.CENTER);
            this.add(lignebas, BorderLayout.SOUTH);
        }
        else {
            for (int i = 0; i < cartes.size(); i++) {
                ImagePanel imagePanel = cartes.get(i).getImage();
                imagePanel.setMinimumSize(new Dimension(100, 100));
                imagePanel.setPreferredSize(new Dimension(150, 150));
                imagePanel.setMaximumSize(new Dimension(200, 200));
                lignehaut.add(imagePanel);
            }
            this.add(lignehaut);
        }






    }
}
