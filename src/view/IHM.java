package view;

import fr.iut2.patterns.observer.Observable;
import fr.iut2.patterns.observer.Observer;
import java.awt.Color;
import java.util.HashMap;
import util.Message;
import util.Utils;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class IHM extends Observable<Message> {

//    private final VueInscription vueInscription;
    private final HashMap<Integer, VueAventurier> vuesAventuriers;

    public IHM(Observer<Message> observer) {
        this.registerObserver(observer);
        this.vuesAventuriers = new HashMap<>();
                
        // démarrage à la main: à remplacer par une interaction correcte pendant
        // l'initialisation
        this.notifyObservers(new Message(Utils.Commandes.VALIDER_JOUEURS, null, null, null, null));
    }
    
    public void creerVuesAventuriers() {
        this.vuesAventuriers.put(0, new VueAventurier(this, 0, "toto", "aventurier", "humain", 0, 1, Color.orange, Color.orange));
    }
}
