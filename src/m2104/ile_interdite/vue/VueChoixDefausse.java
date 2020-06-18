package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.modele.Carte;
import m2104.ile_interdite.util.Message;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueChoixDefausse extends TitleFrame{
    public VueChoixDefausse(IHM ihm, Aventurier aventurier) {
        super("Choix de la carte à défausser");

        main.setLayout(new GridLayout(1, aventurier.getCartes().size()));

        this.setMinimumSize(new Dimension(250, 200));
        this.setSize(120 * aventurier.getCartes().size(),200);

        for (Carte C: aventurier.getCartes()) {
            ImagePanel panel = C.getImage();
            panel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ihm.notifierObservateurs(Message.choixCarteDefausse(C));
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
