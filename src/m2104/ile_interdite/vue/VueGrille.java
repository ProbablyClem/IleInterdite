package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.*;
import m2104.ile_interdite.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueGrille extends JPanel {

    private final Grille grille;
    private ArrayList<JComponent> components;

    public VueGrille(Grille grille) {
        this.grille = grille;
        components = new ArrayList<>();

        this.setLayout(new GridLayout(6, 6));

        for (Tuile[] T: this.grille.getTuiles()) {
            for (Tuile t: T) {
                try {
                    JPanel panel = t.getImage();
                    this.add(panel);
                    components.add(panel);
                    panel.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            System.out.println("Click sur " + t.getNom());
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

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

        components.get(0).add(new ImageFrame("src/images/tresors/" + Utils.Tresor.CRISTAL.getPathPicture()), BorderLayout.CENTER);
        components.get(5).add(new ImageFrame("src/images/tresors/" + Utils.Tresor.CALICE.getPathPicture()), BorderLayout.CENTER);
        components.get(30).add(new ImageFrame("src/images/tresors/" + Utils.Tresor.PIERRE.getPathPicture()), BorderLayout.CENTER);
        components.get(35).add(new ImageFrame("src/images/tresors/" + Utils.Tresor.ZEPHYR.getPathPicture()), BorderLayout.CENTER);

    }



}
