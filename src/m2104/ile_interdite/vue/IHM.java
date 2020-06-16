package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.modele.Grille;
import m2104.ile_interdite.modele.Pilote;
import m2104.ile_interdite.modele.Carte;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Utils;
import patterns.observateur.Observable;
import patterns.observateur.Observateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class IHM extends Observable<Message> {

    private final VueInscriptionJoueurs vueInscription;
    private final HashMap<Aventurier, VueAventurier> vueAventuriers;

    private final JFrame window;
    private final JPanel mainPanel;
    private final JPanel niveauPanel;
    private final VueNiveau vueNiveau;
    private final VueGrille grillePanel;
    private VueAventurier aventurierPanel;

    public IHM(Observateur<Message> observateur, Grille grille) {
        window = new JFrame();

        this.addObservateur(observateur);
        this.vueAventuriers = new HashMap<>();
        this.vueInscription = new VueInscriptionJoueurs(this);

        this.mainPanel = new JPanel(new BorderLayout());
        this.niveauPanel = new JPanel(new BorderLayout());
        this.vueNiveau = new VueNiveau(2);
        this.niveauPanel.add(vueNiveau, BorderLayout.SOUTH);
        JButton exit = new JButton("Quitter");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.niveauPanel.add(exit, BorderLayout.NORTH);

        this.grillePanel = new VueGrille(this, grille);
        this.aventurierPanel = new VueAventurier(this, new Pilote(6), "YYY");

        mainPanel.add(niveauPanel, BorderLayout.WEST);
        mainPanel.add(grillePanel, BorderLayout.CENTER);
        mainPanel.add(aventurierPanel, BorderLayout.EAST);

        window.setContentPane(mainPanel);
        window.setSize(1750, 800);
        window.setVisible(true);
        window.setLocation(400, 150);
        window.setVisible(false);
        window.setVisible(true);
    }

    public void creerVuesAventuriers(ArrayList<Aventurier> aventuriers) {
        // - le pouvoir est disponible dans le modèle
        for (int i = 0; i < this.vueInscription.getNomJoueurs().length; i++) {
            aventuriers.get(i).setNom(this.vueInscription.getNomJoueurs()[i]);
        }
        //assert nomsJoueurs.length == nomAventuriers.length;
        for (int id = 0; id < aventuriers.size(); ++id) {
            this.vueAventuriers.put(
                    aventuriers.get(id),
                    new VueAventurier(this, aventuriers.get(id), aventuriers.get(id).actionSpeciale())
            );
            this.setAventurier(aventuriers.get(id));
        }
    }

    public void AfficherDeck(Utils.Deck deck, ArrayList<Carte> cartes) {
        VueDeck vueDeck = new VueDeck(deck.libelle, cartes);
        vueDeck.afficher();
    }

    public void setAventurier(Aventurier A) {
        aventurierPanel = this.vueAventuriers.get(A);
    }

    public void setMessage(Aventurier a, String message){
        vueAventuriers.get(a).setMessage(message);
    }

    public void finPartie(String message){
        VueFinPartie v = new VueFinPartie(message);
    }

}
