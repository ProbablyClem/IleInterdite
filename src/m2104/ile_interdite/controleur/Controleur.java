package m2104.ile_interdite.controleur;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.modele.Carte;
import m2104.ile_interdite.modele.CarteInondation;
import m2104.ile_interdite.modele.IleInterdite;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Parameters;
import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.vue.IHM;
import m2104.ile_interdite.vue.VueFinPartie;
import m2104.ile_interdite.vue.VueGrille;
import m2104.ile_interdite.vue.VueNiveau;
import patterns.observateur.Observateur;

import java.util.ArrayList;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class Controleur implements Observateur<Message> {

    private final IleInterdite ileInterdite;
    private final IHM ihm;

    public Controleur() {
        this.ileInterdite = new IleInterdite(this);
        this.ihm = new IHM(this, ileInterdite.getGrille());

        new VueFinPartie("cc");
    }

    @Override
    public void traiterMessage(Message msg) {
        if (Parameters.LOGS) {
            System.out.println("Controleur.traiterMessage" + msg);
        }

        switch (msg.getCommande()) {
            case VALIDER_JOUEURS:
                assert msg.hasNbJoueurs();
                ArrayList<Aventurier> aventuriers =
                        Aventurier.getRandomAventuriers(msg.getNbJoueurs());
                this.ihm.creerVuesAventuriers(aventuriers);
                break;
            case VOIR_DECK:
                switch (msg.getDeck()){
                    case DECK_TRESOR:
                        ihm.AfficherDeck(Utils.Deck.DECK_TRESOR, ileInterdite.getDeckTresor());
                        break;
                    case DECK_INONDATION:
                        ArrayList<Carte> c = new ArrayList<>();
                        for(CarteInondation carteInondation : ileInterdite.getDeckInondation()){
                            c.add((Carte)carteInondation);
                        }
                        ihm.AfficherDeck(Utils.Deck.DECK_INONDATION, c);
                        break;
                    case DEFFAUSSE_TRESOR:
                        ihm.AfficherDeck(Utils.Deck.DEFFAUSSE_TRESOR, ileInterdite.getDefausseTresor());
                        break;
                    case DEFFAUSSE_INONDATION:
                        ArrayList<Carte> cartes = new ArrayList<>();
                        for(CarteInondation carteInondation : ileInterdite.getDeckInondation()){
                            cartes.add((Carte)carteInondation);
                        }
                        ihm.AfficherDeck(Utils.Deck.DECK_INONDATION, cartes);
                        break;
                }

            default:
                if (Parameters.LOGS) {
                    System.err.println("Action interdite : " + msg.getCommande().toString());
                }
        }
    }
}
