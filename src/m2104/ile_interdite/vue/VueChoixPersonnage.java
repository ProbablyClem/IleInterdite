package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.util.Message;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueChoixPersonnage extends TitleFrame {
    public VueChoixPersonnage(IHM ihm, ArrayList<Aventurier> aventuriers) {
        super("Choix du personnage");

        main.setLayout(new GridLayout(1, aventuriers.size()));

        this.setMinimumSize(new Dimension(250, 200));
        this.setSize(120 * aventuriers.size(),200);

        for (Aventurier A: aventuriers) {
            ImagePanel panel = A.getImg();
            panel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ihm.notifierObservateurs(Message.choixAventurier(A));
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
