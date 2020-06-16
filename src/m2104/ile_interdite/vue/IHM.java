package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.util.Message;
import patterns.observateur.Observable;
import patterns.observateur.Observateur;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class IHM extends Observable<Message> {

    private final VueInscriptionJoueurs vueInscription;
    private final HashMap<Aventurier, VueAventurier> vueAventuriers;

    public IHM(Observateur<Message> observateur) {
        this.addObservateur(observateur);
        this.vueAventuriers = new HashMap<>();
        this.vueInscription = new VueInscriptionJoueurs(this);
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
        }
    }
}
