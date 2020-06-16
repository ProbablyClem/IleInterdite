package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Grille;
import m2104.ile_interdite.modele.Tuile;
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
                                System.out.println("Click sur " + t.getNom());
                                ihm.notifierObservateurs(Message.choisirTuile(t));
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (panel.isEnabled()) {
                                panel.setBackground(Color.BLACK);
                            }
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            if (panel.isEnabled()) {
                                panel.setBackground(components.get(0).getBackground());
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

        components.get(0).add(new ImagePanel("src/images/tresors/" + Utils.Tresor.CRISTAL.getPathPicture(), 20), BorderLayout.CENTER);
        components.get(5).add(new ImagePanel("src/images/tresors/" + Utils.Tresor.CALICE.getPathPicture(), 20), BorderLayout.CENTER);
        components.get(30).add(new ImagePanel("src/images/tresors/" + Utils.Tresor.PIERRE.getPathPicture(), 20), BorderLayout.CENTER);
        components.get(35).add(new ImagePanel("src/images/tresors/" + Utils.Tresor.ZEPHYR.getPathPicture(), 20), BorderLayout.CENTER);

        etatGrille(true);
    }

}
