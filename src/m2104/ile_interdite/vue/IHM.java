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
        this.notifierObservateurs(new Message(Utils.Commandes.VALIDER_JOUEURS, null, null, null, null));
    }
    
    public void creerVuesAventuriers() {
        this.vueAventuriers.put(0, new VueAventurier(this, 0, "toto", "aventurier", "humain", 0, 1, Color.orange, Color.orange));
    }
}