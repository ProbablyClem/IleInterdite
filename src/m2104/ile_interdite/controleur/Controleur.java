package m2104.ile_interdite.controleur;

import m2104.ile_interdite.modele.*;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Parameters;
import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.vue.IHM;
import patterns.observateur.Observateur;

import java.util.ArrayList;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class Controleur implements Observateur<Message> {

    private IleInterdite ileInterdite;
    private final IHM ihm;
    private ArrayList<Aventurier> aventuriers;
    private Aventurier aventurierActuel;
    private Utils.Etat etat;
    private Grille grille;

    public Controleur() {
        this.grille = new Grille();
        this.ihm = new IHM(this, grille);

    }


    @Override
    public void traiterMessage(Message msg) {
        if (Parameters.LOGS) {
            System.out.println("Controleur.traiterMessage" + msg);
        }

        switch (msg.getCommande()) {
            case VALIDER_JOUEURS:
                assert msg.hasNbJoueurs();
                aventuriers = Aventurier.getRandomAventuriers(msg.getNbJoueurs());

                this.ihm.creerVuesAventuriers(aventuriers);
                this.ileInterdite = new IleInterdite(this, aventuriers, grille);
                this.ihm.afficherMainWindow();
                aventurierActuel = aventuriers.get(0);
                ihm.setMessage(aventurierActuel, "Choisir une action");
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
                    break;
            case DEPLACER:
                ihm.setMessage(aventurierActuel, "Choisir une tuile ou aller");
                //todo activer grille
                etat = Utils.Etat.DEPLACER_JOUEUR;
                break;
            case CHOISIR_TUILE:
                if(etat == Utils.Etat.DEPLACER_JOUEUR){
                    aventurierActuel.setEmplacement(msg.getTuile());
                    aventurierActuel.setActions(aventurierActuel.getActions()-1);
                    ihm.updateGrille();
                    ihm.updateActions();
                }
                else if(etat == Utils.Etat.ASSECHER_CASE){
                    msg.getTuile().setEtat(Utils.EtatTuile.ASSECHEE);
                }
                break;
            case ASSECHER:
                ihm.setMessage(aventurierActuel,"Choisir une case a assecher");
                //todo activer grille
                etat = Utils.Etat.ASSECHER_CASE;
                break;
            case TERMINER:
                aventurierActuel = aventuriers.get(aventuriers.indexOf(aventurierActuel) + 1 % aventuriers.size());
                ihm.setVueAventuriers(aventurierActuel);
                break;
            case ACTION_SPECIALE:
                msg.getAventurier().actionSpeciale();
                break;
            case DONNER:
                break;


            default:
                if (Parameters.LOGS) {
                    System.err.println("Action interdite : " + msg.getCommande().toString());
                }
        }
    }
}
