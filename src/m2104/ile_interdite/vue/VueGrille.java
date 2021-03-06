package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Grille;
import m2104.ile_interdite.modele.Tuile;
import m2104.ile_interdite.util.Message;

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
            if (P.isEnabled()){
                P.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            }
        }

    }

    public void drawGrille() {

        components = new ArrayList<>();
        this.setOpaque(false);
        for (Component C : this.getComponents()) {
            this.remove(C);
        }

        this.setLayout(new GridLayout(6, 6));

        for (Tuile[] T : this.grille.getTuiles()) {
            for (Tuile t : T) {
                try {
                    JPanel panel = t.getImage();
                    panel.setOpaque(false);
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
                    panel.setOpaque(false);
                    this.add(panel);
                    components.add(panel);
                }
            }
        }

        try{
            JPanel ptresor =grille.getTresors()[0].getImage();
            ptresor.setOpaque(false);
            components.get(0).add(ptresor);

        }
        catch (Exception e){
            ImagePanel blankPanel = new ImagePanel();
            blankPanel.setOpaque(false);
            components.get(35).add(blankPanel);
        }

        try{
            JPanel ptresor =grille.getTresors()[1].getImage();
            ptresor.setOpaque(false);
            components.get(5).add(ptresor);
        }
        catch (Exception e){
            ImagePanel blankPanel = new ImagePanel();
            blankPanel.setOpaque(false);
            components.get(35).add(blankPanel);
        }

        try{
            JPanel ptresor =grille.getTresors()[2].getImage();
            ptresor.setOpaque(false);
            components.get(30).add(ptresor);
        }
        catch (Exception e){
            ImagePanel blankPanel = new ImagePanel();
            blankPanel.setOpaque(false);
            components.get(35).add(blankPanel);
        }

        try{
            JPanel ptresor =grille.getTresors()[3].getImage();
            ptresor.setOpaque(false);
            components.get(35).add(ptresor);
        }
        catch (Exception e){
            ImagePanel blankPanel = new ImagePanel();
            blankPanel.setOpaque(false);
            components.get(35).add(blankPanel);
        }

        etatGrille(false);
    }

    public void highlightTuiles(ArrayList<Tuile> tuiles) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (tuiles.contains(grille.getTuiles()[i][j])) {
                    components.get(i*6 + j).setBorder(new SelectBorder(this.ihm.getMainWindow().getAventurierPanel().getAventurier().getPion().getCouleur()));
                    components.get(i*6 + j).setEnabled(true);
                }
                else {
                    components.get(i*6 + j).setBorder(javax.swing.BorderFactory.createEmptyBorder());
                    components.get(i*6 + j).setEnabled(false);
                }
            }
        }
    }

}
