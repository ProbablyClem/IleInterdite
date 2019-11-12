package model;

import fr.iut2.patterns.observer.Observable;
import fr.iut2.patterns.observer.Observer;
import util.Message;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class IleInterdite extends Observable<Message> {
    public IleInterdite(Observer<Message> observer) {
        this.registerObserver(observer);
    }
    
    public void inscrireJoueurs(String[] nomJoueurs) {
        // TODO: à remplir
    }
}
