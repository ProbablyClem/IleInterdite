package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.modele.CarteTresor;
import m2104.ile_interdite.modele.IleInterdite;
import m2104.ile_interdite.modele.Messager;
import m2104.ile_interdite.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueAventurier extends JPanel implements ActionListener {
    private IleInterdite ileInterdite;
    private Aventurier aventurier;
    private JLabel nomAventurier;
    private JLabel nbActionsLabel;
    private JLabel textField;
    private JButton seDeplacer;
    private JButton assecher;
    private JButton finirTour;
    private JButton actionSpecial;
    private JButton donnerCarte;
    private JButton prendreTresor;
    private JButton cartesTresor;
    private JButton deffauseeTresor;
    private JButton deffauseInondation;
    private JButton cartesInondation;
    private int nbActions;

    public VueAventurier(Aventurier aventurier, IleInterdite ileInterdite){
        this.ileInterdite = ileInterdite;
        this.aventurier = aventurier;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel header = new JPanel(new BorderLayout());

        nomAventurier = new JLabel(aventurier.getNom());
        header.add(nomAventurier, BorderLayout.WEST);

        nbActionsLabel = new JLabel(nbActions + " /3");
        header.add(nbActionsLabel, BorderLayout.EAST);

        this.add(header);

        textField = new JLabel("Choisir une action");
        this.add(textField);

        JPanel actionsPanel = new JPanel(new GridLayout(2, 3));
        seDeplacer = new JButton("Se Deplacer");
        actionsPanel.add(seDeplacer);

        assecher = new JButton("Assecher");
        actionsPanel.add(assecher);

        finirTour = new JButton("Finir Tour");
        actionsPanel.add(finirTour);

        actionSpecial = new JButton("Action Special");
        actionsPanel.add(actionSpecial);

        donnerCarte = new JButton("Donner Carte");
        actionsPanel.add(donnerCarte);

        prendreTresor = new JButton("Prendre Tresor");
        actionsPanel.add(prendreTresor);

        this.add(actionsPanel);

        VueMain main = new VueMain(aventurier.getCartes());
        this.add(main);

        JPanel decks = new JPanel(new GridLayout(0, 4));
        cartesTresor = new JButton("Carte Tresor");
        cartesTresor.addActionListener(this);
        decks.add(cartesTresor);

        deffauseeTresor = new JButton("Deffausse Carte Tresor");
        deffauseeTresor.addActionListener(this);
        decks.add(deffauseeTresor);

        deffauseInondation = new JButton("Deffausse Carte Innondation");
        deffauseInondation.addActionListener(this);
        decks.add(deffauseInondation);

        cartesInondation = new JButton("Carte Inondation");
        cartesInondation.addActionListener(this);
        decks.add(cartesInondation);

        this.add(decks);
    }

    //test
    public static void main(String[] args) {
        Aventurier aventurier = new Messager(1);
        aventurier.setNom("Clement");
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        VueAventurier v = new VueAventurier(aventurier, null);
        JFrame w = new JFrame();
        w.add(v);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.pack();
        w.setTitle("vueAventurier");
        w.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cartesTresor){
            VueDeck v = new VueDeck("Deck Cartes Tresor", ileInterdite.getDeckTresor());
            v.afficher();
        }
        else if (e.getSource() == deffauseeTresor){
            VueDeck v = new VueDeck("Deffausse Cartes Tresor", ileInterdite.getDeckTresor());
            v.afficher();
        }
        else if (e.getSource() == deffauseInondation){
            VueDeck v = new VueDeck("Deffause Cartes Inondation", ileInterdite.getDeckTresor());
            v.afficher();
        }
        else if (e.getSource() == cartesInondation){
            VueDeck v = new VueDeck("Deck Cartes Inondation", ileInterdite.getDeckTresor());
            v.afficher();
        }
    }
}
