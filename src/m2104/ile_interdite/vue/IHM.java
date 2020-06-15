package m2104.ile_interdite.vue;

import m2104.ile_interdite.util.Message;
import patterns.observateur.Observable;
import patterns.observateur.Observateur;

import java.awt.*;
import java.util.HashMap;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class IHM extends Observable<Message> {

    private final VueInscriptionJoueurs vueInscription;
    private final HashMap<Integer, oldVueAventurier> vueAventuriers;

    public IHM(Observateur<Message> observateur) {
        this.addObservateur(observateur);
        this.vueAventuriers = new HashMap<>();
        this.vueInscription = new VueInscriptionJoueurs(this);
    }

    public void creerVuesAventuriers(String[] nomAventuriers) {
        // - le pouvoir est disponible dans le modèle
        String[] nomsJoueurs = this.vueInscription.getNomJoueurs();
        assert nomsJoueurs.length == nomAventuriers.length;
        for (int id = 0; id < nomAventuriers.length; ++id) {
            this.vueAventuriers.put(
                    id,
                    new oldVueAventurier(
                            this,
                            id,
                            nomsJoueurs[id],
                            nomAventuriers[id],
                            "YYY",  // TODO: à remplacer par le bon pouvoir
                            id,
                            nomAventuriers.length,
                            Color.orange,
                            Color.orange
                    )
            );
        }
    }
}
