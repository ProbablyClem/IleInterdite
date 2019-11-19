package m2104.ile_interdite.vue;

import java.awt.Color;
import java.util.HashMap;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Utils;
import patterns.observateur.Observable;
import patterns.observateur.Observateur;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class IHM extends Observable<Message> {

//    private final VueInscription vueInscription;
    private final HashMap<Integer, VueAventurier> vueAventuriers;

    public IHM(Observateur<Message> observateur) {
        this.addObservateur(observateur);
        this.vueAventuriers = new HashMap<>();

        // démarrage à la main
        // TODO: à remplacer par une interaction correcte pendant l'initialisation
        this.notifierObservateurs(Message.validerJoueurs(1));
    }

    public void creerVuesAventuriers(String[] nomAventuriers) {
        // - le vrai nom du joueur est disponible dans la vue inscription
        // - le pouvoir est disponible dans le modèle
        for (int id = 0; id < nomAventuriers.length; ++id) {
            this.vueAventuriers.put(
                    id,
                    new VueAventurier(
                            this,
                            id,
                            "XXX",  // TODO: à remplacer par le vrai nom du joueur
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
