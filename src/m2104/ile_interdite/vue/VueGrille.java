package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.*;
import m2104.ile_interdite.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VueGrille {

    private final IHM ihm;
    private final Grille grille;
    private final JPanel mainPanel;

    private final JPanel topPanel;
    private final JPanel grillePanel;
    private final JPanel bottomPanel;

    public VueGrille(IHM ihm, Grille grille) {
        this.ihm = ihm;
        this.grille = grille;
        this.mainPanel = new JPanel(new BorderLayout());

        this.topPanel = new JPanel(new BorderLayout());
        this.grillePanel = new JPanel(new GridLayout(6, 6));
        this.bottomPanel = new JPanel(new BorderLayout());

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(grillePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        for (Tuile[] T: this.grille.getTuiles()) {
            for (Tuile t: T) {
                try {
                    JPanel panel = t.getImage();
                    grillePanel.add(panel);
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
                    grillePanel.add(new JPanel());
                }
            }
        }

        topPanel.add(new ImageFrame(Utils.Tresor.CRISTAL.getPathPicture()), BorderLayout.WEST);
        topPanel.add(new ImageFrame(Utils.Tresor.CALICE.getPathPicture()), BorderLayout.EAST);
        bottomPanel.add(new ImageFrame(Utils.Tresor.PIERRE.getPathPicture()), BorderLayout.WEST);
        bottomPanel.add(new ImageFrame(Utils.Tresor.ZEPHYR.getPathPicture()), BorderLayout.EAST);

        JFrame testFrame = new JFrame();
        testFrame.add(mainPanel);
        testFrame.setSize(800, 800);
        testFrame.setVisible(true);
    }



}
