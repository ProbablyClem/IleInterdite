package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.modele.Carte;
import m2104.ile_interdite.modele.Grille;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Utils;
import patterns.observateur.Observable;
import patterns.observateur.Observateur;
import m2104.ile_interdite.vue.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class IHM extends Observable<Message> {

    private final VueInscriptionJoueurs vueInscription;
    private final HashMap<Aventurier, VueAventurier> vueAventuriers;
    private Grille grille;
    private MainWindow mainWindow;
    private ArrayList<Aventurier> aventuriers;

    public IHM(Observateur<Message> observateur, Grille grille) {
        this.grille = grille;
        this.addObservateur(observateur);
        this.vueAventuriers = new HashMap<>();
        this.vueInscription = new VueInscriptionJoueurs(this);
    }

    public void creerVuesAventuriers(ArrayList<Aventurier> aventuriers) {
        this.aventuriers = aventuriers;
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
        }
    }


    public void AfficherDeck(Utils.Deck deck, ArrayList<Carte> cartes) {
        VueDeck vueDeck = new VueDeck(deck.libelle, cartes);
        vueDeck.afficher();
    }


    public void setMessage(Aventurier a, String message){
        vueAventuriers.get(a).setMessage(message);
    }

    public void finPartie(String message){
        VueFinPartie v = new VueFinPartie(message);
    }

    public void updateGrille(){
        mainWindow.getGrillePanel().drawGrille();
        mainWindow.getGrillePanel().updateUI();
    }

    public void setVueAventuriers(Aventurier a){
        mainWindow.setAventurier(vueAventuriers.get(a));
    }

    public void afficherMainWindow(){
        mainWindow = new MainWindow(this, grille,vueAventuriers.get(aventuriers.get(0)));
    }

}
