package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.*;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueGrille extends JPanel {

    private final Grille grille;
    private ArrayList<JPanel> components;
    private IHM ihm;

    public VueGrille(Grille grille, IHM ihm) {
        this.grille = grille;
        this.ihm = ihm;

        drawGrille();
        etatGrille(false);

    }



    public void etatGrille(boolean etat) {
        for (JPanel P: components) {
            P.setEnabled(etat);
        }
    }

    public void drawGrille() {

        components = new ArrayList<>();

        for (Component C : this.getComponents()) {
            this.remove(C);
        }

        this.setLayout(new GridLayout(6, 6));

        for (Tuile[] T : this.grille.getTuiles()) {
            for (Tuile t : T) {
                try {
                    JPanel panel = t.getImage();
                    this.add(panel);
                    components.add(panel);
                    panel.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (panel.isEnabled()) {
                                ihm.notifierObservateurs(Message.choisirTuile(t));
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (panel.isEnabled()) {
                                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
                            }
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            if (panel.isEnabled()) {
                                panel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 5));
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });
                } catch (NullPointerException e) {
                    JPanel panel = new JPanel(new BorderLayout());
                    this.add(panel);
                    components.add(panel);
                }
            }
        }

        try{
            components.get(0).add(grille.getTresors()[0].getImage());
        }
        catch (Exception e){
            components.get(0).add(new ImagePanel());
        }

        try{
            components.get(5).add(grille.getTresors()[1].getImage());
        }
        catch (Exception e){
            components.get(5).add(new ImagePanel());
        }

        try{
            components.get(30).add(grille.getTresors()[2].getImage());
        }
        catch (Exception e){
            components.get(30).add(new ImagePanel());
        }

        try{
            components.get(35).add(grille.getTresors()[3].getImage());
        }
        catch (Exception e){
            components.get(35).add(new ImagePanel());
        }

        etatGrille(true);
    }

    public void highlightTuiles(ArrayList<Tuile> tuiles) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (tuiles.contains(grille.getTuiles()[i][j])) {
                    components.get(i*6 + j).setBorder(BorderFactory.createLineBorder(Color.green, 5, true));
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame w = new JFrame();
        Grille grille = new Grille();
        VueGrille vueGrille = new VueGrille(grille, null);
        w.add(vueGrille);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(1000, 1000);
        w.setTitle("vueGrille");
        w.setVisible(true);
        Aventurier aventurier = new Pilote();
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new CarteTresor(null, null, Utils.Tresor.CALICE));
        cartes.add(new CarteTresor(null, null, Utils.Tresor.CALICE));
        cartes.add(new CarteTresor(null, null, Utils.Tresor.CALICE));
        cartes.add(new CarteTresor(null, null, Utils.Tresor.CALICE));
        aventurier.setEmplacement(new Tuile(null, null, Utils.Tresor.CALICE));
        aventurier.setCartes(cartes);
        if (aventurier.prendreTresor()){
            grille.PrendreTresor(Utils.Tresor.CALICE);
            vueGrille.drawGrille();
        }
    }
}
