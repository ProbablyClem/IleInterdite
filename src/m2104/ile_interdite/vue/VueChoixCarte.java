package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.modele.Carte;
import m2104.ile_interdite.util.Message;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueChoixCarte extends TitleFrame {
    public VueChoixCarte(IHM ihm, Aventurier aventurier) {
        super("Choix de la carte");

        ArrayList<Carte> cartes = new ArrayList<>();
        for (Carte C: aventurier.getCartes()) {
            if (!C.getClass().getCanonicalName().contains("CarteSpeciale")) {
                cartes.add(C);
            }
        }

        main.setLayout(new GridLayout(1, cartes.size()));

        this.setMinimumSize(new Dimension(250, 200));
        this.setSize(120 * cartes.size(),200);

        for (Carte C: cartes) {
            ImagePanel panel = C.getImage();
            panel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Click sur " + C.getNom());
                    ihm.notifierObservateurs(Message.choixCarte(C));
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
            main.add(panel);
        }
        this.centrer();
        this.setVisible(true);
    }
}
