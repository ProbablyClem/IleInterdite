package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Carte;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueMain extends JPanel {

    private JLabel titre;
    private ArrayList<Carte> cartes;
    private JPanel pheader;
    private JPanel lignehaut;

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



        lignehaut = new JPanel();

        if (cartes.size() > 5) {
            for (int i = 0; i < 5; i++) {
                ImagePanel imagePanel = cartes.get(i).getImage();
                imagePanel.setMinimumSize(new Dimension(100, 100));
                imagePanel.setPreferredSize(new Dimension(150, 150));
                imagePanel.setMaximumSize(new Dimension(200, 200));
                imagePanel.setBackground(Color.white);
                lignehaut.setBackground(Color.white);
                lignehaut.add(imagePanel);
            }
            JPanel lignebas = new JPanel();
            for (int i = 5; i < cartes.size(); i++) {
                ImagePanel imagePanel = cartes.get(i).getImage();
                imagePanel.setMinimumSize(new Dimension(100, 100));
                imagePanel.setPreferredSize(new Dimension(150, 150));
                imagePanel.setMaximumSize(new Dimension(200, 200));
                imagePanel.setBackground(Color.white);
                lignebas.setBackground(Color.white);
                lignebas.add(imagePanel);
            }
            lignehaut.setBackground(Color.white);
            this.setBackground(Color.white);
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
