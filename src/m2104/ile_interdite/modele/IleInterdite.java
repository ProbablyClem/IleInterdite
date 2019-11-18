package m2104.ile_interdite.modele;

import java.util.Arrays;
import m2104.ile_interdite.util.Message;
import patterns.observateur.Observable;
import patterns.observateur.Observateur;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class IleInterdite extends Observable<Message> {
    public IleInterdite(Observateur<Message> observateur) {
        this.addObservateur(observateur);
    }

    public String[] inscrireJoueurs(String[] nomJoueurs) {
        // TODO: à remplacer par une réelle assignation des types d'aventuriers
        String[] nomAventuriers = new String[nomJoueurs.length];
        Arrays.fill(nomAventuriers, "Aventurier");
        return nomAventuriers;
    }
}
