package m2104.ile_interdite.controleur;

import m2104.ile_interdite.modele.*;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Parameters;
import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.vue.IHM;
import m2104.ile_interdite.vue.VueChoixCarte;
import m2104.ile_interdite.vue.VueChoixPersonnage;
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
    private int niveau = 2;
    private int idJoueur = 0;
    private ArrayList<Tuile> listTuiles;
    private VueChoixCarte vueChoixCarte;
    private Carte carte;
    private VueChoixPersonnage vueChoixPerso;

    public Controleur() {
        this.grille = new Grille();
        this.ihm = new IHM(this, grille);
        this.listTuiles = new ArrayList<>();
    }

    @Override
    public void traiterMessage(Message msg) {
        if (Parameters.LOGS) {
            System.out.println("Controleur.traiterMessage" + msg);
        }

        switch (msg.getCommande()) {
            case VALIDER_JOUEURS :
                assert msg.hasNbJoueurs();
                aventuriers = Aventurier.getRandomAventuriers(msg.getNbJoueurs());

                this.ihm.creerVuesAventuriers(aventuriers);
                this.ileInterdite = new IleInterdite(this, aventuriers, grille);
                this.ihm.afficherMainWindow(niveau);
                aventurierActuel = aventuriers.get(0);
                ihm.setMessage(aventurierActuel, "Choisir une action");
                break;
            case CHOIX_NIVEAU :
                this.niveau = msg.getNiveau();
                System.out.println(msg.getNiveau());
                break;
            case VOIR_DECK :
                switch (msg.getDeck()) {
                    case DECK_TRESOR :
                        ihm.AfficherDeck(Utils.Deck.DECK_TRESOR, ileInterdite.getDeckTresor());
                        break;
                    case DECK_INONDATION:
                        ArrayList<Carte> c = new ArrayList<>();
                        for (CarteInondation carteInondation : ileInterdite.getDeckInondation()) {
                            c.add((Carte) carteInondation);
                        }
                        ihm.AfficherDeck(Utils.Deck.DECK_INONDATION, c);
                        break;
                    case DEFFAUSSE_TRESOR :
                        ihm.AfficherDeck(Utils.Deck.DEFFAUSSE_TRESOR, ileInterdite.getDefausseTresor());
                        break;
                    case DEFFAUSSE_INONDATION :
                        ArrayList<Carte> cartes = new ArrayList<>();
                        for (CarteInondation carteInondation : ileInterdite.getDefausseInondation()) {
                            cartes.add((Carte) carteInondation);
                        }
                        ihm.AfficherDeck(Utils.Deck.DECK_INONDATION, cartes);
                        break;
                    }
                break;
            case DEPLACER :
                if (aventurierActuel.getActions() > 0) {
                    listTuiles = aventurierActuel.getDeplacementsPossibles();

                    ihm.setMessage(aventurierActuel, "Choisir une tuile ou aller");
                    ihm.getMainWindow().getGrillePanel().highlightTuiles(listTuiles);

                    ihm.activerGrille();
                    etat = Utils.Etat.DEPLACER_JOUEUR;
                }
                else {
                    ihm.setMessage(aventurierActuel, "Plus de points d'actions");
                    ihm.desactiverGrille();
                }
                break;
            case CHOISIR_TUILE :
                if (etat == Utils.Etat.DEPLACER_JOUEUR) {
                    if (listTuiles.contains(msg.getTuile())) {
                        aventurierActuel.setEmplacement(msg.getTuile());
                        aventurierActuel.setActions(aventurierActuel.getActions() - 1);
                        ihm.updateGrille();
                        ihm.updateActions();
                    } else {
                        ihm.setMessage(aventurierActuel, "Déplacement impossible");
                    }
                } else if (etat == Utils.Etat.ASSECHER_CASE) {
                    if (listTuiles.contains(msg.getTuile())){
                        aventurierActuel.assecher(msg.getTuile());
                        ihm.updateGrille();
                        ihm.updateActions();
                    }
                    else {
                        ihm.setMessage(aventurierActuel, "Déplacement impossible");
                    }
                }
                break;
            case ASSECHER :
                if (aventurierActuel.getActions() > 0){
                    listTuiles = aventurierActuel.gettAssechementPossible();
                    ihm.setMessage(aventurierActuel,"Choisir une case a assecher");
                    ihm.activerGrille();
                    etat = Utils.Etat.ASSECHER_CASE;
                    ihm.getMainWindow().getGrillePanel().highlightTuiles(listTuiles);
                    ihm.activerGrille();
                }
                else {
                    ihm.setMessage(aventurierActuel, "Plus de points d'actions disponible");
                    ihm.desactiverGrille();
                }
                break;
            case TERMINER :
                idJoueur = idJoueur + 1 % aventuriers.size();
                aventurierActuel = aventuriers.get(idJoueur);
                ihm.setVueAventuriers(aventurierActuel);
                break;
            case ACTION_SPECIALE :
                msg.getAventurier().actionSpeciale();
                break;
            case DONNER :
                if (aventurierActuel.getRole().equals("Messager") || aventurierActuel.getEmplacement().getAventuriers().size() > 1) {
                    if (aventurierActuel.getCartesTresor().size() != 0) {
                        ihm.setMessage(aventurierActuel, "Veuillez choisir une carte");
                        vueChoixCarte = new VueChoixCarte(ihm, aventurierActuel);
                    } else {
                        ihm.setMessage(aventurierActuel, "Vous n'avez pas de carte à donner");
                    }
                } else {
                    ihm.setMessage(aventurierActuel, "Il n'y a personne sur votre case");
                }
                break;
            case CHOIX_CARTE :
                ihm.setMessage(aventurierActuel, "Veuillez choisir une personne");
                ArrayList<Aventurier> memeCase = new ArrayList<>(!aventurierActuel.getRole().equals("Messager") ? aventurierActuel.getEmplacement().getAventuriers() : aventuriers);
                memeCase.remove(aventurierActuel);
                vueChoixPerso = new VueChoixPersonnage(ihm, memeCase);
                carte = msg.getCarte();
                vueChoixCarte.dispose();
                break;
            case CHOIX_AVENTURIER :
                aventurierActuel.donnerCarte(msg.getAventurier(), carte);
                vueChoixPerso.dispose();
                break;


            default :
                if (Parameters.LOGS) {
                    System.err.println("Action interdite : " + msg.getCommande().toString());
                }
        }
    }
}
