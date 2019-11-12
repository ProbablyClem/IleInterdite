package controller;

import fr.iut2.patterns.observer.Observer;
import model.IleInterdite;
import util.Message;
import util.Parameters;
import view.IHM;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class Controleur implements Observer<Message> {

    private final IleInterdite ileInterdite;
    private final IHM ihm;
    
    public Controleur() {
        this.ileInterdite = new IleInterdite(this);
        this.ihm = new IHM(this);

    }
    
    @Override
    public void processMessage(Message msg) {
        if (Parameters.LOGS) {
            System.out.println("Controleur.processMessage" + msg);
        }

        switch (msg.getCommande()) {
            case VALIDER_JOUEURS:
                this.ileInterdite.inscrireJoueurs(new String[] {"Toto"});
                this.ihm.creerVuesAventuriers();
                break;
            default:
                if (Parameters.LOGS) {
                    System.err.println("Action interdite : " + msg.getCommande().toString());
                }
        }
    }

        public static void main(String[] args) {   
            new Controleur();
        }
}
