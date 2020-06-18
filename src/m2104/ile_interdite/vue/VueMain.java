package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Carte;
import m2104.ile_interdite.util.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueMain extends JPanel implements MouseListener {


    private VueAventurier vueAventurier;
    private JLabel titre;
    private ArrayList<Carte> cartes;
    private JPanel pheader;
    private JPanel jcartesup;
    private JPanel jcartesdown;
    private JPanel jtapis;
    private ImagePanel carteSelectionnée;

    public VueMain(ArrayList<Carte> cartes,VueAventurier vueAventurier){
        this.vueAventurier = vueAventurier;
        this.cartes = cartes;
        draw();
    }


    private void draw() {
        this.setLayout(new BorderLayout());
        titre = new JLabel("Cartes Joueur");
        pheader = new JPanel();
        titre.setFont(new Font("Roboto", Font.BOLD, 20));
        titre.setHorizontalAlignment(JLabel.CENTER);

        titre.setVerticalAlignment(JLabel.CENTER);
        pheader.setBackground(Color.white);

        pheader.add(titre, BorderLayout.NORTH);
        this.add(pheader, BorderLayout.NORTH);


        jtapis = new JPanel();
        jcartesup = new JPanel();
        jcartesdown = new JPanel();

        jcartesup.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        jcartesup.setPreferredSize(new Dimension(500, 300));

        jcartesdown.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        jcartesdown.setPreferredSize(new Dimension(500, 300));


        jtapis.setLayout(new BorderLayout());
        jcartesup.setBackground(Color.white);
        jcartesdown.setBackground(Color.white);

        if (cartes.size() <= 5) {


            for (Carte c : cartes) {
                jcartesup.setPreferredSize(new Dimension(500, 200));
                ImagePanel panel = c.getImage();
                panel.setPreferredSize(new Dimension(80, 110));
                panel.setEnabled(true);
                panel.addMouseListener(this);
//                if (carteSelectionnée == panel) {
//                    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
//                }
//                else {
//                    panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, true));
//                }
                panel.setBackground(Color.white);
                jcartesup.add(panel);
                this.add(jcartesup, BorderLayout.CENTER);
            }
        }
    }

    public Carte getCarteSelectionnée() {
        if (carteSelectionnée != null){
            return carteSelectionnée.getCarte();
        }
        else {
            return null;
        }

    }

    public void mouseClicked(MouseEvent e) {
        if(((ImagePanel) e.getSource()).getCarte().getNom().equalsIgnoreCase("Carte helicoptere") || ((ImagePanel) e.getSource()).getCarte().getNom().equalsIgnoreCase("Sac Sable") ){
            if (carteSelectionnée != null){
                if (carteSelectionnée != (ImagePanel)e.getSource()){
                    carteSelectionnée.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, true));
                    carteSelectionnée = (ImagePanel)e.getSource();
                    carteSelectionnée.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
                }
            }
            else {
                carteSelectionnée = (ImagePanel)e.getSource();
                carteSelectionnée.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
            }


            if(e.getClickCount()==1){
                if(((ImagePanel) e.getSource()).getCarte().getNom().equalsIgnoreCase("Carte helicoptere")){
                    vueAventurier.getIhm().notifierObservateurs(Message.helico());
                }
                else if(((ImagePanel) e.getSource()).getCarte().getNom().equalsIgnoreCase("Sac Sable")){
                    ((ImagePanel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
                    // panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
                    vueAventurier.getIhm().notifierObservateurs(Message.sacdesable(vueAventurier.getAventurier()));
                }
            }
            else if (e.getClickCount()==2){
                carteSelectionnée = null;
            }
            draw();
        }

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
}

