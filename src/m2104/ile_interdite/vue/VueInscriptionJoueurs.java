package m2104.ile_interdite.vue;

import m2104.ile_interdite.util.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

public class VueInscriptionJoueurs extends TitleFrame implements ActionListener {
    private final IHM ihm;

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
        super("L'île Interdite");
        this.setSize(500, 300);

        this.ihm = ihm;
        
        nivEaux = new VueNiveau(1);

        JPanel panelChoix = new JPanel(new GridLayout(5,2));
        panelChoix.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 10));

        JPanel panelBas = new JPanel();
        panelBas.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        JPanel panelTop = new JPanel(new BorderLayout());

        // nombre de joueurs
        choixNbJoueurs = new JComboBox<>(new Integer[] { 2, 3, 4 });
        panelChoix.add(new JLabel("Nombre de joueurs :"));
        panelChoix.add(choixNbJoueurs);
        panelChoix.setBackground(Color.white);

        // Saisie des noms de joueurs
        for(int i = 0; i < saisieNomJoueurs.length; i++) {
            labelNomJoueurs[i] = new JLabel("Nom du joueur " + (i + 1) + " :");
            saisieNomJoueurs[i] = new JTextField();
            panelChoix.add(labelNomJoueurs[i]);
            panelChoix.add(saisieNomJoueurs[i]);
            labelNomJoueurs[i].setEnabled(i < 2);
            saisieNomJoueurs[i].setEnabled(i < 2);
        }


        JPanel panelLvl = new JPanel(new GridLayout(1, 4));

        panelTop.add(niv, BorderLayout.NORTH);
        panelTop.add(panelLvl, BorderLayout.CENTER);
        panelTop.setBackground(Color.white);

        niv.setFont(new Font("Roboto",Font.BOLD,15));
        
        novice = new Button("Novice", 0, 35, Color.BLACK, Color.WHITE);
        panelLvl.add(novice);
        
        intermediaire = new Button("Intermédiaire", 0, 35, Color.BLACK, Color.WHITE);
        panelLvl.add(intermediaire);
        
        elite = new Button("Élite", 0, 35, Color.BLACK, Color.WHITE);
        panelLvl.add(elite);
        
        legendaire = new Button("Légendaire", 0, 35, Color.BLACK, Color.WHITE);
        panelLvl.add(legendaire);

        resetColorButtons();
        buttonSelection(intermediaire);

        retour = new Button("Quitter", 150, 35, Color.RED, new Color(255, 120, 120));
        panelBas.add(retour, BorderLayout.WEST);

        inscrire = new Button("Jouer", 150, 35, Color.GREEN, new Color(120, 255, 120));
        panelBas.add(inscrire, BorderLayout.EAST);
        panelBas.setBackground(Color.white);
        main.setLayout(new BorderLayout());

        main.add(panelTop, BorderLayout.NORTH);
        main.add(panelChoix, BorderLayout.CENTER);
        main.add(panelBas, BorderLayout.SOUTH);
        main.setBackground(Color.white);
        this.setBackground(Color.white);
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
        inscrire.addActionListener(this);

        retour.addActionListener(this);
        
        novice.addActionListener(this);
        
        intermediaire.addActionListener(this);
        
        elite.addActionListener(this);
        
        legendaire.addActionListener(this);

        this.centrer();
        this.setVisible(true);
    }

    public VueInscriptionJoueurs getThis() {
        return this;
    }

    public String[] getNomJoueurs() {
        return Arrays.copyOf(this.nomJoueurs, this.nomJoueurs.length);
    }

    public void resetColorButtons() {
        novice.setColors(new Color(0, 250, 0), new Color(175, 250, 175));
        intermediaire.setColors(new Color(250, 220, 0), new Color(250, 220, 175));
        elite.setColors(new Color(0, 220, 250), new Color(175, 220, 250));
        legendaire.setColors(new Color(220, 0, 250), new Color(220, 150, 250));
    }

    public void buttonSelection(Button btn) {
        resetColorButtons();
        btn.setColors(Color.DARK_GRAY, Color.LIGHT_GRAY);
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == novice){
            buttonSelection(novice);
            ihm.notifierObservateurs(Message.niveau(1));
        }
        else if (e.getSource() == intermediaire){
            buttonSelection(intermediaire);
            ihm.notifierObservateurs(Message.niveau(2));
        }
        else if (e.getSource() == elite){
            buttonSelection(elite);
            ihm.notifierObservateurs(Message.niveau(3));
        }
        else if (e.getSource() == legendaire){
            buttonSelection(legendaire);
            ihm.notifierObservateurs(Message.niveau(4));
        }
        else if (e.getSource() == inscrire){
            // Remplissage du tableau contenant le nom des joueurs
            int nbJoueurs = (int) choixNbJoueurs.getSelectedItem();

            nomJoueurs = new String[nbJoueurs];
            for (int i = 0; i < nbJoueurs; ++i) {
                nomJoueurs[i] = saisieNomJoueurs[i].getText();
            }

            ihm.notifierObservateurs(Message.validerJoueurs(nbJoueurs));
            getThis().dispose();
        }
        else if (e.getSource() == retour) {
            System.exit(0);
        }
    }
}
