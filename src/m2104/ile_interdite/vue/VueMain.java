package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Carte;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class VueMain extends JPanel {


    private VueAventurier vueAventurier;
    private JLabel titre;
    private ArrayList<Carte> cartes;
    private JPanel pheader;
    private JPanel jcartesup;
    private JPanel jcartesdown;
    private JPanel jtapis;
    public VueMain(ArrayList<Carte> cartes,VueAventurier vueAventurier){
        this.vueAventurier=vueAventurier;
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

        if (cartes.size()<=5){
        for (Carte C: cartes) {

                    jcartesup.setPreferredSize(new Dimension(500,200));
                    ImagePanel panel = C.getImage();
                    panel.setPreferredSize(new Dimension(80,110));
                    panel.setEnabled(true);
                    panel.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(e.getClickCount()==1){
                                if (C.getNom().equalsIgnoreCase("Carte helicoptere")){

                            }
                                else if (C.getNom().equalsIgnoreCase("Carte montée des eaux")){
                                    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
                                }
                                else if ( C.getNom().equalsIgnoreCase("Sac Sable")){
                                    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
                                    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
                                    Message m=Message.sacdesable(vueAventurier.getAventurier());
                                    vueAventurier.getIhm().notifierObservateurs(m);
                                }
                            }
                            else if (e.getClickCount()==2){
                                panel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 5));
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
                    });
                    panel.setBackground(Color.white);

                    jcartesup.add(panel);
                    this.add(jcartesup,BorderLayout.CENTER);


            }
    }


    }}

