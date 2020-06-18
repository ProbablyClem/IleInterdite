package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Carte;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class VueMain extends JPanel {

    private JLabel titre;
    private ArrayList<Carte> cartes;
    private JPanel pheader;
    private JPanel jcartesup;
    private JPanel jcartesdown;
    private JPanel jtapis;
    public VueMain(ArrayList<Carte> cartes){
        this.cartes = cartes;
        this.setLayout(new BorderLayout());
        titre = new JLabel("Cartes Joueur");
        pheader= new JPanel();
        titre.setFont(new Font("Roboto",Font.BOLD,20));
        titre.setHorizontalAlignment(JLabel.CENTER);

        titre.setVerticalAlignment(JLabel.CENTER);
        pheader.setBackground(Color.white);

        pheader.add(titre, BorderLayout.NORTH);
        this.add(pheader,BorderLayout.NORTH);


        jtapis = new JPanel();
        jcartesup = new JPanel();
        jcartesdown=new JPanel();

        jcartesup.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        jcartesup.setPreferredSize(new Dimension(500,300));

        jcartesdown.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        jcartesdown.setPreferredSize(new Dimension(500,300));


        jtapis.setLayout(new BorderLayout());
        jcartesup.setBackground(Color.white);
        jcartesdown.setBackground(Color.white);


        for (Carte C: cartes) {

                    jcartesup.setPreferredSize(new Dimension(500,200));
                    ImagePanel panel = C.getImage();
                    panel.setPreferredSize(new Dimension(120,120));
                    panel.setBackground(Color.white);
                    jcartesup.add(panel);
                    this.add(jcartesup,BorderLayout.CENTER);

            }
    }


    }

