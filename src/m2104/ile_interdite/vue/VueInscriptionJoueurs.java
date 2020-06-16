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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    private final JLabel textNomJ = new JLabel("Entrez vos noms :");
    private final JLabel niv = new JLabel("NIVEAUX");
    private JTextField [] saisieNomJoueurs = new JTextField[4];
    private final JButton inscrire = new JButton("Inscrire");
    private final JButton retour = new JButton("Quitter");
    private final JButton novice;
    private final JButton intermediaire;
    private final JButton elite;
    private final JButton legendaire;
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
        fenetre.setSize(500, 500);
        

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel panelChoix = new JPanel(new GridLayout(6,4));
        
        GridLayout g1 = new GridLayout(3, 4);
        g1.setHgap(5);
        g1.setVgap(10);
        JPanel panelBas = new JPanel();
        panelBas.setLayout(g1);
        
        JPanel panelTop = new JPanel(new GridLayout(2, 1));
        
        JLabel blank = new JLabel(" ");
        JLabel blank1 = new JLabel(" ");
        JLabel blank2 = new JLabel(" ");
        JLabel blank3 = new JLabel(" ");
        JLabel blank4 = new JLabel(" ");
       
        // nombre de joueurs
        choixNbJoueurs = new JComboBox<>(new Integer[] { 2, 3, 4 });
        panelChoix.add(new JLabel("Nombre de joueurs :"));
        panelChoix.add(choixNbJoueurs);

        // Saisie des noms de joueurs
        for(int i = 0; i < saisieNomJoueurs.length; i++) {
            saisieNomJoueurs[i] = new JTextField();
            labelNomJoueurs[i] = new JLabel("Nom du joueur No " + (i + 1) + " :");
            panelChoix.add(blank);
            panelChoix.add(labelNomJoueurs[i]);
            panelChoix.add(saisieNomJoueurs[i]);
            panelChoix.add(blank1);
            labelNomJoueurs[i].setEnabled(i < 2);
            saisieNomJoueurs[i].setEnabled(i < 2);
        }
        
        
        panelTop.add(textNomJ);
        
        textNomJ.setHorizontalAlignment(JLabel.CENTER);
        textNomJ.setFont(new Font("Roboto",Font.BOLD,20));
        
        panelBas.add(blank2);
        panelBas.add(niv);
        panelBas.add(blank3);
        panelBas.add(blank4);
        niv.setFont(new Font("Roboto",Font.BOLD,15));
        
        novice = new JButton("novice");
        novice.setBackground(Color.CYAN);
        panelBas.add(novice);
        
        intermediaire = new JButton("intérmediaire");
        intermediaire.setBackground(Color.CYAN);
        panelBas.add(intermediaire);
        
        elite = new JButton("élite");
        elite.setBackground(Color.CYAN);
        panelBas.add(elite);
        
        legendaire = new JButton("légendaire");
        legendaire.setBackground(Color.CYAN);
        panelBas.add(legendaire);
        
        panelBas.add(retour);
        retour.setBackground(Color.red);
        panelBas.add(blank);
        panelBas.add(blank1);
        panelBas.add(inscrire);
        inscrire.setBackground(Color.green);
        
        mainPanel.add(panelTop, BorderLayout.NORTH);
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        mainPanel.add(panelChoix, BorderLayout.CENTER);
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
