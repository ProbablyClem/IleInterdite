package m2104.ile_interdite.controleur;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.modele.IleInterdite;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Parameters;
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
        this.ihm = new IHM(this);

        new VueNiveau(1);
        new VueFinPartie(ihm);
        new VueGrille(ihm, ileInterdite.getGrille());
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
            default:
                if (Parameters.LOGS) {
                    System.err.println("Action interdite : " + msg.getCommande().toString());
                }
        }
    }
}
