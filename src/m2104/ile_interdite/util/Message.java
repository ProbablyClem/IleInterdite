package m2104.ile_interdite.util;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.modele.Carte;
import m2104.ile_interdite.modele.Tuile;

import java.io.Serializable;

/**
 *
 * @author IUT2-Dept Info
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private Utils.Commandes commande;
    private Aventurier aventurier;
    private Carte carte;
    private Utils.Tresor tresor;
    private Tuile tuile;
    private Integer nbJoueurs;
    private Utils.Deck deck;
    private int niveau;
    private String texte;

    private Message(Utils.Commandes commande, Aventurier aventurier, Carte carte, Utils.Tresor tresor, Tuile tuile, Integer nbJoueurs, Utils.Deck deck, int niveau) {
        this.commande = commande;
        this.aventurier = aventurier;
        this.carte = carte;
        this.tresor = tresor;
        this.tuile = tuile;
        this.nbJoueurs = nbJoueurs;
        this.deck = deck;
        this.niveau = niveau;
    }

    private Message(Utils.Commandes commande, String texte){
        this.commande = commande;
        this.texte = texte;
    }



    /**
     *
     * @param nbJoueurs
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#VALIDER_JOUEURS}
     */
    public static Message validerJoueurs(int nbJoueurs) {
        return new Message(Utils.Commandes.VALIDER_JOUEURS, null, null, null, null, nbJoueurs, null, 0);
    }

    /**
     *
     * @param aventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#BOUGER}
     */
    public static Message bouger(Aventurier aventurier) {
        return new Message(Utils.Commandes.BOUGER, aventurier, null, null, null, null, null, 0);
    }

    /**
     *
     * @param aventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#ASSECHER}
     */
    public static Message assecher(Aventurier aventurier) {
        return new Message(Utils.Commandes.ASSECHER, aventurier, null, null, null, null, null, 0);
    }

    /**
     *
     * @param aventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#DONNER}
     */
    public static Message donner(Aventurier aventurier) {
        return new Message(Utils.Commandes.DONNER, aventurier, null, null, null, null, null, 0);
    }

    public static Message choixCarte(Carte carte) {
        return new Message(Utils.Commandes.CHOIX_CARTE, null, carte, null, null, null, null, 0);
    }

    public static Message choixAventurier(Aventurier aventurier) {
        return new Message(Utils.Commandes.CHOIX_AVENTURIER, aventurier, null, null, null, null, null, 0);
    }

    /**
     *
     * @param aventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#RECUPERER_TRESOR}
     */
    public static Message recupererTresor(Aventurier aventurier) {
        return new Message(Utils.Commandes.RECUPERER_TRESOR, aventurier, null, null, null, null, null, 0);
    }

    /**
     *
     * @param aventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#TERMINER}
     */
    public static Message terminer(Aventurier aventurier) {
        return new Message(Utils.Commandes.TERMINER, aventurier, null, null, null, null, null, 0);
    }

    /**
     *
     * @param aventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#RECEVOIR}
     */
    public static Message recevoir(Aventurier aventurier) {
        return new Message(Utils.Commandes.RECEVOIR, aventurier, null, null, null, null, null, 0);
    }

    /**
     *
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#CHOISIR_CARTE}
     */
    public static Message choisirCarte() {
        return new Message(Utils.Commandes.CHOISIR_CARTE, null, null, null, null, null, null, 0);
    }

    /**
     *
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#CHOISIR_TUILE}
     */
    public static Message choisirTuile(Tuile t) {
        return new Message(Utils.Commandes.CHOISIR_TUILE, null, null, null, t, null, null, 0);
    }

    /**
     *
     * @param aventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#DEPLACER}
     */
    public static Message deplacer(Aventurier aventurier) {
        return new Message(Utils.Commandes.DEPLACER, aventurier, null, null, null, null, null, 0);
    }

    public static Message finPartie(String texte){
        return new Message(Utils.Commandes.FIN_PARTIE, texte);
    }

    /**
     *
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#VOIR_DEFAUSSE}
     */
    public static Message voirDefausse() {
        return new Message(Utils.Commandes.VOIR_DEFAUSSE, null, null, null, null, null, null, 0);
    }

    public static Message voirDeck(Utils.Deck deck) {
        return new Message(Utils.Commandes.VOIR_DECK, null, null, null, null, null, deck, 0);
    }

    public static Message actionSpecial() {
        return new Message(Utils.Commandes.ACTION_SPECIALE, null, null, null, null, null, null, 0);
    }

    public static Message niveau(int niveau){
        return new Message(Utils.Commandes.CHOIX_NIVEAU, null, null, null, null, null, null, niveau);
    }

    public static Message finPartie(){
        return new Message(Utils.Commandes.FIN_PARTIE, null, null, null, null, null, null, 0);
    }


    /**
     * @return the commande
     */
    public Boolean hasCommande() {
        return commande != null;
    }
    public Utils.Commandes getCommande() {
        return commande;
    }

    /**
     * @return the idAventurier`
     */
    public Boolean hasAventurier() {
        return aventurier != null;
    }
    public Aventurier getAventurier() {
        return aventurier;
    }

    /**
     * @return the idCarte
     */
    public Boolean hasCarte() {
        return carte != null;
    }
    public Carte getCarte() {
        return carte;
    }

    /**
     * @return the tresor
     */
    public Boolean hasTresor() {
        return tresor != null;
    }
    public Utils.Tresor getTresor() {
        return tresor;
    }

    /**
     *
     * @return the nbJoueurs
     */
    public Boolean hasNbJoueurs() {
        return nbJoueurs != null;
    }
    public Integer getNbJoueurs() {
        return nbJoueurs;
    }

    /**
     * @return the idTuile
     */
    public Boolean hasTuile() {
        return tuile != null;
    }
    public Tuile getTuile() {
        return tuile;
    }

    @Override
    public String toString() {
        String txt = "";
        txt += commande.toString() + " ";
        if (hasAventurier()) {
            txt += " aventurier=" + aventurier.getNom();
        }
        if (hasCarte()) {
            txt += " carte=" + carte;
        }
        if (hasTuile()) {
            txt += " tuile=" + tuile;
        }
        if (hasTresor()) {
            txt += " tresor=" + tresor.toString();
        }
        return txt;
    }

    public Utils.Deck getDeck() {
        return deck;
    }

    public int getNiveau() {
        return niveau;
    }

    public String getTexte() {
        return texte;
    }
}
