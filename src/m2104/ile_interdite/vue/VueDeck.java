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

public class VueDeck extends JPanel {
    private IHM vueIHM;
    private JLabel labelNomDeck;
    private JButton retour;
    private JPanel grid;
    private JPanel panelhaut;



    private ArrayList<Carte> cartes;
    private ArrayList<CarteTresor> cartesTresors;


    public VueDeck(String nomDeck, ArrayList<Carte> cartes) {
        this.cartes = cartes;
        this.setLayout(new BorderLayout());

        panelhaut= new JPanel(new BorderLayout());

        retour= new Button("RETOUR",200,50);
        retour.setFont(new Font("Roboto",Font.BOLD,20));

        retour.setBackground(Color.RED);
        panelhaut.add(retour, BorderLayout.WEST);
        labelNomDeck = new JLabel(nomDeck);

        labelNomDeck.setFont(new Font("Roboto",Font.BOLD,30));

        labelNomDeck.setVerticalAlignment(JLabel.CENTER);
        labelNomDeck.setHorizontalAlignment(JLabel.CENTER);
        panelhaut.add(labelNomDeck, BorderLayout.CENTER);
        panelhaut.setBackground(Color.lightGray);

        this.add(panelhaut,BorderLayout.NORTH);


        if (cartes.size() <= 14){
            grid = new JPanel(new GridLayout(1, 14));

        }
        else {
            grid = new JPanel(new GridLayout(2, 14));

        }

        for(Carte c : cartes){
            grid.add(c.getImage());
        }
        this.add(grid, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(700,700));
    }



    public static void main(String[] args) {
        ArrayList<Carte> c = new ArrayList<>();
        CarteTresor carte = new CarteTresor("src/images/cartes/SacsDeSable.png", "Carte", null);
        CarteTresor cartee = new CarteTresor("src/images/ocean.jpg", "Carte", null);
        c.add(carte);
        c.add(cartee);
        VueDeck vueDeck= new VueDeck("DECK", c);
        JFrame w = new JFrame();
        w.add(vueDeck);
        w.setPreferredSize(new Dimension(700,700));
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setTitle("Vueflex");
        w.setVisible(true);
    }

    public void afficher(){
        TitleFrame w = new TitleFrame("Vueflex");
        w.add(this);
        retour.addActionListener(e -> w.dispose());
        w.setSize(500, 300);
        w.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        w.setVisible(true);
    }
}
