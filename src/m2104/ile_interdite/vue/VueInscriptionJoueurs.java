package m2104.ile_interdite.vue;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import javax.swing.*;

import m2104.ile_interdite.util.Message;

/**
 *
 * @author Yann Laurillau <yann.laurillau@iut2.univ-grenoble-alpes.fr>
 */
public class VueInscriptionJoueurs {
    private final IHM ihm;
    private final JFrame fenetre;

    private JComboBox<Integer> choixNbJoueurs;
    private JLabel [] labelNomJoueurs = new JLabel[4];
    private final JLabel textNomJ = new JLabel("L'île Interdite :");
    private final JLabel niv = new JLabel("Niveau", SwingConstants.CENTER);
    private JTextField [] saisieNomJoueurs = new JTextField[4];
    private final Button retour;
    private final Button inscrire;
    private final Button novice;
    private final Button intermediaire;
    private final Button elite;
    private final Button legendaire;
    //private final JButton plus = new JButton("+");
    //private final JButton moins = new JButton("-");
    //private JLabel numJ = new JLabel("2");
    private VueNiveau nivEaux;

    private String[] nomJoueurs;

    public VueInscriptionJoueurs(IHM ihm) {
        this.ihm = ihm;
        
        nivEaux = new VueNiveau(1);
        fenetre = new JFrame();
        fenetre.setLayout(new BorderLayout());
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(500, 300);
        fenetre.setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel panelChoix = new JPanel(new GridLayout(5,2));

        JPanel panelBas = new JPanel();
        panelBas.setLayout(new BorderLayout());
        
        JPanel panelTop = new JPanel(new BorderLayout());

        // nombre de joueurs
        choixNbJoueurs = new JComboBox<>(new Integer[] { 2, 3, 4 });
        panelChoix.add(new JLabel("Nombre de joueurs :"));
        panelChoix.add(choixNbJoueurs);

        // Saisie des noms de joueurs
        for(int i = 0; i < saisieNomJoueurs.length; i++) {
            labelNomJoueurs[i] = new JLabel("Nom du joueur " + (i + 1) + " :");
            saisieNomJoueurs[i] = new JTextField();
            panelChoix.add(labelNomJoueurs[i]);
            panelChoix.add(saisieNomJoueurs[i]);
            labelNomJoueurs[i].setEnabled(i < 2);
            saisieNomJoueurs[i].setEnabled(i < 2);
        }
        

        
        textNomJ.setHorizontalAlignment(JLabel.CENTER);
        textNomJ.setFont(new Font("Roboto",Font.BOLD,20));


        JPanel panelLvl = new JPanel(new GridLayout(1, 4));

        panelTop.add(textNomJ, BorderLayout.NORTH);
        panelTop.add(niv, BorderLayout.CENTER);
        panelTop.add(panelLvl, BorderLayout.SOUTH);

        niv.setFont(new Font("Roboto",Font.BOLD,15));
        
        novice = new Button("Novice", 0, 35, new Color(0, 250, 0), new Color(175, 250, 175));
        panelLvl.add(novice);
        
        intermediaire = new Button("Intermédiaire", 0, 35, new Color(250, 220, 0), new Color(250, 220, 175));
        panelLvl.add(intermediaire);
        
        elite = new Button("Élite", 0, 35, new Color(0, 220, 250), new Color(175, 220, 250));
        panelLvl.add(elite);
        
        legendaire = new Button("Légendaire", 0, 35, new Color(220, 0, 250), new Color(220, 150, 250));
        panelLvl.add(legendaire);

        retour = new Button("Quitter", 150, 35, Color.RED, new Color(255, 120, 120));
        panelBas.add(retour, BorderLayout.WEST);

        inscrire = new Button("Jouer", 150, 35, Color.GREEN, new Color(120, 255, 120));
        panelBas.add(inscrire, BorderLayout.EAST);
        
        mainPanel.add(panelTop, BorderLayout.NORTH);
        mainPanel.add(panelChoix, BorderLayout.CENTER);
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        fenetre.add(mainPanel);

        // Choix du nombre de joueurs
        choixNbJoueurs.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int nb = (Integer) choixNbJoueurs.getSelectedItem();

                for(int i = 0; i < saisieNomJoueurs.length; i++) {
                    labelNomJoueurs[i].setEnabled(i < nb);
                    saisieNomJoueurs[i].setEnabled(i < nb);
                }
            }
        });

        // Inscription des joueurs
        inscrire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remplissage du tableau contenant le nom des joueurs
                int nbJoueurs = (int) choixNbJoueurs.getSelectedItem();

                nomJoueurs = new String[nbJoueurs];
                for (int i = 0; i < nbJoueurs; ++i) {
                    nomJoueurs[i] = saisieNomJoueurs[i].getText();
                }

                ihm.notifierObservateurs(Message.validerJoueurs(nbJoueurs));
                fenetre.dispose();
            }
        });
        
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        novice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nivEaux.setNiveau(1);
            }
        });
        
        intermediaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nivEaux.setNiveau(2);
            }
        });
        
        elite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nivEaux.setNiveau(3);
            }
        });
        
        legendaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nivEaux.setNiveau(4);
            }
        });

        fenetre.setVisible(true);
    }

    public String[] getNomJoueurs() {
        return Arrays.copyOf(this.nomJoueurs, this.nomJoueurs.length);
    }
    
    
}
