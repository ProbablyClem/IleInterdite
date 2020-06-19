package m2104.ile_interdite.controleur;

import m2104.ile_interdite.modele.*;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Parameters;
import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.vue.*;
import patterns.observateur.Observateur;

import java.util.ArrayList;
import java.util.HashSet;

public class Controleur implements Observateur<Message> {

    private IleInterdite ileInterdite;
    private IHM ihm;
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
    private VueChoixDefausse vueChoixDefausse;
    private Aventurier aventurierStock;
    HashSet<Tuile> list1;
    HashSet<Tuile> list2;
    ArrayList<Tuile> list3;

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
                        ihm.AfficherDeck(Utils.Deck.DEFFAUSSE_INONDATION, cartes);
                        break;
                    }
                break;
            case DEPLACER :
                if (aventurierActuel.getActions() > 0) {
                    listTuiles = aventurierActuel.getDeplacementsPossibles();

                    ihm.setMessage(aventurierActuel, "Choisir une tuile ou aller");
                    ihm.getMainWindow().getGrillePanel().highlightTuiles(listTuiles);

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
                        traiterMessage(Message.deplacer(aventurierActuel));
                    } else {
                        ihm.setMessage(aventurierActuel, "Déplacement impossible");
                    }
                } else if (etat == Utils.Etat.ASSECHER_CASE_JOUEUR) {
                    if (listTuiles.contains(msg.getTuile())){
                        aventurierActuel.assecher(msg.getTuile());
                        if (aventurierActuel.getActions() < 1) { ihm.getMainWindow().getAventurierPanel().DesactiverBoutons(); }
                        ihm.updateGrille();
                        ihm.updateActions();
                        traiterMessage(Message.assecher(aventurierActuel));
                    }
                    else {
                        ihm.setMessage(aventurierActuel, "Assechement impossible");
                    }

                } else if (etat == Utils.Etat.AS_NAVIGATEUR) {
                    if (list3.contains(msg.getTuile())) {
                        aventurierStock.getEmplacement().supprimerAventurier(aventurierStock);
                        aventurierStock.setEmplacement(msg.getTuile());
                        aventurierActuel.setActions(aventurierActuel.getActions() - 1);
                        if (aventurierActuel.getActions() < 1) { ihm.getMainWindow().getAventurierPanel().DesactiverBoutons(); }
                        ihm.updateGrille();
                        ihm.desactiverGrille();
                    } else {
                        ihm.setMessage(aventurierActuel, "Déplacement impossible sur cette tuile");
                    }
                }
                else if (etat == Utils.Etat.ASSECHER_CASE_CARTE){
                    if (listTuiles.contains(msg.getTuile())){
                        msg.getTuile().setEtat(Utils.EtatTuile.ASSECHEE);
                        ihm.updateGrille();
                        ihm.desactiverGrille();
                        aventurierActuel.getCartes().remove(msg.getCarte());
                        ihm.getMainWindow().getAventurierPanel().update(aventurierActuel);
                    }
                    else {
                        ihm.setMessage(aventurierActuel, "Assechement impossible");
                    }
                }
                else if(etat == Utils.Etat.HELICOPTERE){
                    if (listTuiles.contains(msg.getTuile())){
                        Tuile emplacementActuel = aventurierActuel.getEmplacement();
                        for (Aventurier a: aventurierActuel.getEmplacement().getAventuriers()) {
                            a.setEmplacement(msg.getTuile());
                        }
                        emplacementActuel.getAventuriers().clear();
                        ihm.updateGrille();
                        ihm.desactiverGrille();
                        aventurierActuel.getCartes().remove(msg.getCarte());
                        ihm.getMainWindow().getAventurierPanel().update(aventurierActuel);

                    }
                    else {
                        ihm.setMessage(aventurierActuel, "Assechement impossible");
                    }
                    ihm.desactiverGrille();
                }
                break;
            case ASSECHER :
                if (aventurierActuel.getActions() > 0 || (aventurierActuel instanceof Ingenieur && !((Ingenieur) aventurierActuel).isEtatAS())) {
                    listTuiles = aventurierActuel.getAssechementPossible();
                    ihm.setMessage(aventurierActuel,"Choisir une case à assecher");
                    etat = Utils.Etat.ASSECHER_CASE_JOUEUR;
                    ihm.getMainWindow().getGrillePanel().highlightTuiles(listTuiles);
                }
                else {
                    ihm.getMainWindow().getAventurierPanel().DesactiverBoutons();
                    ihm.setMessage(aventurierActuel, "Plus de points d'actions disponible");
                    ihm.desactiverGrille();
                }
                break;

            case SACDESABLE:
                listTuiles.clear();
                for (Tuile t: aventurierActuel.getEmplacement().getGrille().getListTuiles()){
                    if (t.getEtat()==Utils.EtatTuile.INONDEE  ){
                        listTuiles.add(t);
                    }
                }
                ihm.setMessage(aventurierActuel,"Choisir une case à assecher");

                etat = Utils.Etat.ASSECHER_CASE_CARTE;

                ihm.getMainWindow().getGrillePanel().highlightTuiles(listTuiles);

                break;


            case TERMINER :
                aventurierActuel.setActions(3);
                ihm.getMainWindow().getAventurierPanel().ActiverBoutons();
                ileInterdite.PiocherCartesTresor(aventurierActuel);
                ileInterdite.PiocherCartesInondation(ileInterdite.getNiveau());
                ihm.updateGrille();
                idJoueur ++;
                if (idJoueur == aventuriers.size()) {
                    idJoueur = 0;
                }
                aventurierActuel = aventuriers.get(idJoueur);
                ihm.setVueAventuriers(aventurierActuel);
                ihm.getMainWindow().desactiverGrille();

                if (aventurierActuel.getCartes().size() > 5 && etat != Utils.Etat.FIN_PARTIE){

                    vueChoixDefausse = new VueChoixDefausse(ihm, aventurierActuel);
                }
                break;
            case ACTION_SPECIALE :
                msg.getAventurier().actionSpeciale();
                break;
            case DONNER :
                if (aventurierActuel.getActions() < 1) {
                    ihm.setMessage(aventurierActuel, "Vous n'avez pas assez d'actions");
                }
                else{
                    etat = Utils.Etat.DON_CARTE;
                    if (aventurierActuel.getRole().equals("Messager") || aventurierActuel.getEmplacement().getAventuriers().size() > 1) {
                        if (aventurierActuel.getCartesTresor().size() != 0) {
                            ihm.setMessage(aventurierActuel, "Veuillez choisir une carte");
                            ihm.desactiverGrille();
                            ihm.updateGrille();
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
            case CHOIX_DEFAUSSE:
                ihm.setMessage(aventurierActuel, "Veuillez choisir une CARTE à défausser");
                carte = msg.getCarte();
                ileInterdite.getDefausseTresor().add(carte);
                aventurierActuel.getCartes().remove(carte);
                ihm.getMainWindow().getAventurierPanel().update(aventurierActuel);

                vueChoixDefausse.dispose();

                if (aventurierActuel.getCartes().size() > 5) {
                    vueChoixDefausse = new VueChoixDefausse(ihm, aventurierActuel);

                }
                break;
            case CHOIX_AVENTURIER :
                if (this.etat == Utils.Etat.AS_NAVIGATEUR) {
                    this.aventurierStock = msg.getAventurier();
                    list1 = new HashSet<>(msg.getAventurier().getEmplacement().getGrille().getTuilesAdjacentes(msg.getAventurier().getEmplacement()));
                    list2 = new HashSet<>();
                    for (Tuile T: list1) {
                        list2.addAll(T.getGrille().getTuilesAdjacentes(T));
                    }
                    list1.addAll(list2);
                    list1.remove(aventurierStock.getEmplacement());
                    list1.removeIf(T -> T.getEtat() == Utils.EtatTuile.COULEE);
                    list3 = new ArrayList<>(list1);
                    ihm.getMainWindow().getGrillePanel().highlightTuiles(list3);
                    vueChoixPerso.dispose();
                    if (aventurierActuel.getActions() < 1) { ihm.getMainWindow().getAventurierPanel().DesactiverBoutons(); }
                } else {
                    aventurierActuel.donnerCarte(msg.getAventurier(), carte);
                    aventurierActuel.setActions(aventurierActuel.getActions()-1);
                    if (aventurierActuel.getActions() < 1) {
                        ihm.getMainWindow().getAventurierPanel().DesactiverBoutons();
                    }
                    vueChoixPerso.dispose();

                    ihm.getMainWindow().getAventurierPanel().update(aventurierActuel);
                }
                break;
            case RECUPERER_TRESOR:
                ihm.desactiverGrille();
                if (aventurierActuel.getActions() < 1){
                    ihm.setMessage(aventurierActuel, "Vous n'avez pas assez d'actions");
                }
                if(aventurierActuel.prendreTresor()){
                    ileInterdite.PrendreTresor(aventurierActuel.getEmplacement().getTresor());
                    ihm.updateGrille();
                    Utils.afficherInformation("Vous avez recupere le tresor " + aventurierActuel.getEmplacement().getTresor() + " !" );
                    aventurierActuel.setActions(aventurierActuel.getActions()-1);
                    ihm.getMainWindow().getAventurierPanel().update(aventurierActuel);
                    if (aventurierActuel.getActions() < 1) { ihm.getMainWindow().getAventurierPanel().DesactiverBoutons(); }
                }
                else {
                    ihm.setMessage(aventurierActuel, "Vous n'avez pas les cartes neccesaires pour prendre le tresor");
                }
                break;
            case FIN_PARTIE:
                etat = Utils.Etat.FIN_PARTIE;
                ihm.getMainWindow().setEnabled(false);
                new VueFinPartie(msg.getTexte(), ihm);
                break;
            case RELANCER:
                this.ihm.getMainWindow().dispose();
                this.grille = new Grille();
                this.ihm = new IHM(this, grille);
                this.listTuiles = new ArrayList<>();
                break;
            case CARTE_HELICOPTERE:
                if(grille.nbTresors() < 1 && aventurierActuel.getEmplacement().getNom().equalsIgnoreCase("L'Héliport") && aventurierActuel.getEmplacement().getAventuriers().size() == aventuriers.size()){
                    Boolean checkCond = true;
                    new VueFinPartie((Parameters.EKIP ? "L'EKIP a " : "Vous avez") + "gagné !", ihm);
                }
                else{
                    listTuiles = grille.getListTuiles();
                    listTuiles.remove(aventurierActuel.getEmplacement());
                    listTuiles.removeIf(T -> T.getEtat() == Utils.EtatTuile.COULEE);
                    ihm.setMessage(aventurierActuel,"Choisir ou aller");
                    etat = Utils.Etat.HELICOPTERE;
                    ihm.getMainWindow().getGrillePanel().highlightTuiles(listTuiles);
                }
                break;
            case AS_NAVIGATEUR:
                if (aventurierActuel.getActions() > 0) {
                    etat = Utils.Etat.AS_NAVIGATEUR;
                    ArrayList<Aventurier> list = new ArrayList<>(aventuriers);
                    list.remove(aventurierActuel);
                    vueChoixPerso = new VueChoixPersonnage(ihm, list);
                } else {
                    ihm.setMessage(aventurierActuel, "Vous n'avez plus de points d'action");
                }

                break;
            default :
                if (Parameters.LOGS) {
                    System.err.println("Action interdite : " + msg.getCommande().toString());
                }
        }
    }
}
