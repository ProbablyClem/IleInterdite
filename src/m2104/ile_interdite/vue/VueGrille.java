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
        this.setBackground(Color.white);
        for (Component C : this.getComponents()) {
            this.remove(C);
        }

        this.setLayout(new GridLayout(6, 6));

        for (Tuile[] T : this.grille.getTuiles()) {
            for (Tuile t : T) {
                try {
                    JPanel panel = t.getImage();
                    panel.setBackground(Color.white);
                    this.add(panel);
                    components.add(panel);
                    panel.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (panel.isEnabled()) {
                                ihm.notifierObservateurs(Message.choisirTuile(t, ihm.getMainWindow().getAventurierPanel().getCarteSelectionne()));
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (panel.isEnabled()) {
                                panel.setBorder(new SelectBorder(new Color(0, 250, 250)));
                            }
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
                    panel.setBackground(Color.white);
                    this.add(panel);
                    components.add(panel);
                }
            }
        }

        try{
            JPanel ptresor =grille.getTresors()[0].getImage();
            ptresor.setBackground(Color.white);
            components.get(0).add(ptresor);

        }
        catch (Exception e){
            components.get(0).add(new ImagePanel());
        }

        try{
            JPanel ptresor =grille.getTresors()[1].getImage();
            ptresor.setBackground(Color.white);
            components.get(5).add(ptresor);
        }
        catch (Exception e){
            components.get(5).add(new ImagePanel());
        }

        try{
            JPanel ptresor =grille.getTresors()[2].getImage();
            ptresor.setBackground(Color.white);
            components.get(30).add(ptresor);
        }
        catch (Exception e){
            components.get(30).add(new ImagePanel());
        }

        try{
            JPanel ptresor =grille.getTresors()[3].getImage();
            ptresor.setBackground(Color.white);
            components.get(35).add(ptresor);
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
                    components.get(i*6 + j).setBorder(new SelectBorder(this.ihm.getMainWindow().getAventurierPanel().getAventurier().getPion().getCouleur()));
                }
            }
        }
    }

}
