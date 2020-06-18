package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Carte;
import m2104.ile_interdite.modele.CarteTresor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class VueDeck extends TitleFrame {
    private IHM vueIHM;
    private JButton retour;
    private JPanel panelCartes;
    private JPanel panelBas;
    private JPanel grid;



    private ArrayList<Carte> cartes;



    public VueDeck(String nomDeck, ArrayList<Carte> cartes) {
        super(nomDeck);
        this.cartes = cartes;
        main.setLayout(new BorderLayout());

        panelCartes = new JPanel();
        panelCartes.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 10));
        panelBas = new JPanel(new BorderLayout());

        if (cartes.size() <= 4) {
            panelCartes.setLayout(new GridLayout(1, cartes.size()));
            this.setSize(new Dimension((cartes.size() > 2 ? 120 * cartes.size() : 300),280));
            panelCartes.setBackground(Color.white);
        } else {
            panelCartes.setLayout(new GridLayout(2, cartes.size() / 2 + cartes.size() % 2));
            this.setSize(new Dimension((cartes.size() / 2 + cartes.size() % 2 > 2 ? 120 * (cartes.size() / 2 + cartes.size() % 2) : 300),480));
            panelCartes.setBackground(Color.white);
        }

        for(Carte c : cartes){
            ImagePanel panel= c.getImage();
            panel.setBackground(Color.white);
            panelCartes.add(panel);


        }

        retour = new Button("FERMER",200,50,Color.decode("#c4443b"),Color.decode("#f02416"));
        retour.setFont(new Font("Roboto",Font.BOLD,20));
        retour.setBackground(Color.white);

        retour.addActionListener(e -> this.dispose());

        panelBas.add(retour, BorderLayout.CENTER);

        main.add(panelCartes, BorderLayout.CENTER);
        main.add(panelBas, BorderLayout.SOUTH);
        main.setBackground(Color.WHITE);

        this.setVisible(true);
        this.centrer();
    }
}
