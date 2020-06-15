package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Carte;
import m2104.ile_interdite.modele.CarteTresor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueDeck extends JPanel {
    private IHM vueIHM;
    private JLabel labelNomDeck;
    private JButton retour;
    private JPanel grid;
    private JPanel panelhaut;
    private ArrayList<Carte> cartes;


    public VueDeck(String nomDeck, ArrayList<Carte> cartes) {
        this.cartes = cartes;
        this.setLayout(new BorderLayout());

        panelhaut= new JPanel(new BorderLayout());

        retour= new JButton("RETOUR");
        retour.setBackground(Color.RED);
        panelhaut.add(retour, BorderLayout.WEST);
        labelNomDeck = new JLabel(nomDeck);
        panelhaut.add(labelNomDeck, BorderLayout.CENTER);

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
    }

    public static void main(String[] args) {
        ArrayList<Carte> c = new ArrayList<>();
        CarteTresor carte = new CarteTresor("src/images/ocean.jpg", "Carte", null);
        CarteTresor cartee = new CarteTresor("src/images/ocean.jpg", "Carte", null);
        c.add(carte);
        c.add(cartee);
         VueDeck vueDeck= new VueDeck("Deck", c);
         JFrame w = new JFrame();
         w.add(vueDeck);
         w.setSize(500, 300);
         w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         w.setTitle("Vueflex");
         w.setVisible(true);
    }
}
