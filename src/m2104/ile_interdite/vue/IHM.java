package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.modele.Carte;
import m2104.ile_interdite.modele.Grille;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Utils;
import patterns.observateur.Observable;
import patterns.observateur.Observateur;

import java.util.ArrayList;
import java.util.HashMap;

public class IHM extends Observable<Message> {

    private final VueInscriptionJoueurs vueInscription;
    private final HashMap<Aventurier, VueAventurier> vueAventuriers;
    private Grille grille;
    private MainWindow mainWindow;
    private ArrayList<Aventurier> aventuriers;
    private int niveau;

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
        for (Aventurier A : aventuriers) {
            this.vueAventuriers.put(
                    A,
                    new VueAventurier(this, A, A.actionSpeciale())
            );
        }
    }


    public void AfficherDeck(Utils.Deck deck, ArrayList<Carte> cartes) {
        VueDeck vueDeck = new VueDeck(deck.libelle, cartes);
    }

    public MainWindow getMainWindow() {
        return this.mainWindow;
    }

    public void setMessage(Aventurier a, String message){
        vueAventuriers.get(a).setMessage(message);
    }

    public void finPartie(String message){
        VueFinPartie v = new VueFinPartie(message, this);
    }

    public void updateGrille(){
        mainWindow.getGrillePanel().drawGrille();
        mainWindow.getGrillePanel().updateUI();
    }

    public void updateActions(){
        mainWindow.getAventurierPanel().updateActions();
    }

    public void activerGrille(){
        this.mainWindow.getGrillePanel().etatGrille(true);
    }

    public void desactiverGrille(){
        this.mainWindow.getGrillePanel().etatGrille(false);
    }

    public void setVueAventuriers(Aventurier a){
        mainWindow.getAventurierPanel().update(a);
    }

    public void afficherMainWindow(int niveau){
        mainWindow = new MainWindow(this, grille,vueAventuriers.get(aventuriers.get(0)), niveau);
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
        mainWindow.getVueNiveau().setNiveau(niveau);
    }
}
