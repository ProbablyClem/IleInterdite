package m2104.ile_interdite.controleur;

import m2104.ile_interdite.modele.*;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Parameters;
import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.vue.IHM;
import m2104.ile_interdite.vue.VueChoixCarte;
import m2104.ile_interdite.vue.VueChoixPersonnage;
import m2104.ile_interdite.vue.VueFinPartie;
import patterns.observateur.Observateur;

import java.util.ArrayList;

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
                if (ihm.getMainWindow() != null) {
                    ihm.setNiveau(niveau);
                }

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
                    ihm.getMainWindow().getAventurierPanel().DesactiverBoutons();
                    ihm.setMessage(aventurierActuel, "Plus de points d'actions");
                    ihm.desactiverGrille();
                }
                break;
            case CHOISIR_TUILE :
                if (etat == Utils.Etat.DEPLACER_JOUEUR) {
                    if (listTuiles.contains(msg.getTuile())) {
                        aventurierActuel.getEmplacement().supprimerAventurier(aventurierActuel);
                        aventurierActuel.setEmplacement(msg.getTuile());
                        aventurierActuel.setActions(aventurierActuel.getActions() - 1);
                        if (aventurierActuel.getActions() < 1) { ihm.getMainWindow().getAventurierPanel().DesactiverBoutons(); }
                        ihm.updateGrille();
                        ihm.updateActions();
                    } else {
                        ihm.setMessage(aventurierActuel, "Déplacement impossible");
                    }
                } else if (etat == Utils.Etat.ASSECHER_CASE) {
                    if (listTuiles.contains(msg.getTuile())){
                        aventurierActuel.assecher(msg.getTuile());
                        if (aventurierActuel.getActions() < 1) { ihm.getMainWindow().getAventurierPanel().DesactiverBoutons(); }
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
                    listTuiles = aventurierActuel.getAssechementPossible();
                    ihm.setMessage(aventurierActuel,"Choisir une case a assecher");
                    ihm.activerGrille();
                    etat = Utils.Etat.ASSECHER_CASE;
                    ihm.getMainWindow().getGrillePanel().highlightTuiles(listTuiles);
                    ihm.activerGrille();
                }
                else {
                    ihm.getMainWindow().getAventurierPanel().DesactiverBoutons();
                    ihm.setMessage(aventurierActuel, "Plus de points d'actions disponible");
                    ihm.desactiverGrille();
                }
                break;
            case TERMINER :
                aventurierActuel.setActions(3);
                ihm.getMainWindow().getAventurierPanel().ActiverBoutons();
                //ileInterdite.PiocherCartesTresor(aventurierActuel);
                ileInterdite.PiocherCartesInondation(ileInterdite.getNiveau());
                ihm.updateGrille();
                idJoueur ++;
                if (idJoueur == aventuriers.size()) {
                    idJoueur = 0;
                }
                aventurierActuel = aventuriers.get(idJoueur);
                ihm.setVueAventuriers(aventurierActuel);
                ihm.getMainWindow().desactiverGrille();
                break;
            case ACTION_SPECIALE :
                msg.getAventurier().actionSpeciale();
                break;
            case DONNER :
                if (aventurierActuel.getActions() < 1) {
                    ihm.setMessage(aventurierActuel, "Vous n'avez pas assez d'actions");
                }
                else{
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
                aventurierActuel.setActions(aventurierActuel.getActions()-1);
                if (aventurierActuel.getActions() < 1) { ihm.getMainWindow().getAventurierPanel().DesactiverBoutons(); }
                vueChoixPerso.dispose();
                ihm.getMainWindow().getAventurierPanel().update(aventurierActuel);
                break;
            case RECUPERER_TRESOR:
                if (aventurierActuel.getActions() < 1){
                    ihm.setMessage(aventurierActuel, "Vous n'avez pas assez d'actions");
                }
                if(aventurierActuel.prendreTresor()){
                    ileInterdite.PrendreTresor(aventurierActuel.getEmplacement().getTresor());
                    ihm.updateGrille();
                    Utils.afficherInformation("Vous avez recupere le tresor " + aventurierActuel.getEmplacement().getTresor() + " !" );
                    aventurierActuel.setActions(aventurierActuel.getActions()-1);
                    if (aventurierActuel.getActions() < 1) { ihm.getMainWindow().getAventurierPanel().DesactiverBoutons(); }
                }
                else {
                    ihm.setMessage(aventurierActuel, "Vous n'avez pas les tresors neccesaires pour prendre le tresor");
                }
                break;
            case FIN_PARTIE:
                new VueFinPartie(msg.getTexte());
                break;

            default :
                if (Parameters.LOGS) {
                    System.err.println("Action interdite : " + msg.getCommande().toString());
                }
        }
    }
}
