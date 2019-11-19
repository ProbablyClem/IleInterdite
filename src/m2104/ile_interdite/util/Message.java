package m2104.ile_interdite.util;

import java.io.Serializable;

/**
 *
 * @author IUT2-Dept Info
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Utils.Commandes commande;
    private final Integer idAventurier;
    private final Integer idCarte;
    private final Utils.Tresor tresor;
    private final Integer idTuile;
    private final Integer nbJoueurs;

    private Message(Utils.Commandes commande, Integer idAventurier, Integer idCarte, Utils.Tresor tresor, Integer idTuile, Integer nbJoueurs) {
        this.commande = commande;
        this.idAventurier = idAventurier;
        this.idCarte = idCarte;
        this.tresor = tresor;
        this.idTuile = idTuile;
        this.nbJoueurs = nbJoueurs;
    }

    /**
     *
     * @param nbJoueurs
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#VALIDER_JOUEURS}
     */
    public static Message validerJoueurs(int nbJoueurs) {
        return new Message(Utils.Commandes.VALIDER_JOUEURS, null, null, null, null, nbJoueurs);
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#BOUGER}
     */
    public static Message bouger(int idAventurier) {
        return new Message(Utils.Commandes.BOUGER, idAventurier, null, null, null, null);
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#ASSECHER}
     */
    public static Message assecher(int idAventurier) {
        return new Message(Utils.Commandes.ASSECHER, idAventurier, null, null, null, null);
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#DONNER}
     */
    public static Message donner(int idAventurier) {
        return new Message(Utils.Commandes.DONNER, idAventurier, null, null, null, null);
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#RECUPERER_TRESOR}
     */
    public static Message recupererTresor(int idAventurier) {
        return new Message(Utils.Commandes.RECUPERER_TRESOR, idAventurier, null, null, null, null);
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#TERMINER}
     */
    public static Message terminer(int idAventurier) {
        return new Message(Utils.Commandes.TERMINER, idAventurier, null, null, null, null);
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#RECEVOIR}
     */
    public static Message recevoir(int idAventurier) {
        return new Message(Utils.Commandes.RECEVOIR, idAventurier, null, null, null, null);
    }

    /**
     *
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#CHOISIR_CARTE}
     */
    public static Message choisirCarte() {
        return new Message(Utils.Commandes.CHOISIR_CARTE, null, null, null, null, null);
    }

    /**
     *
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#CHOISIR_TUILE}
     */
    public static Message choisirTuile() {
        return new Message(Utils.Commandes.CHOISIR_TUILE, null, null, null, null, null);
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#DEPLACER}
     */
    public static Message deplacer(int idAventurier) {
        return new Message(Utils.Commandes.DEPLACER, idAventurier, null, null, null, null);
    }

    /**
     *
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#VOIR_DEFAUSSE}
     */
    public static Message voirDefausse() {
        return new Message(Utils.Commandes.VOIR_DEFAUSSE, null, null, null, null, null);
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
    public Boolean hasIdAventurier() {
        return idAventurier != null;
    }
    public Integer getIdAventurier() {
        return idAventurier;
    }

    /**
     * @return the idCarte
     */
    public Boolean hasIdCarte() {
        return idCarte != null;
    }
    public Integer getIdCarte() {
        return idCarte;
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
    public Boolean hasIdTuile() {
        return idTuile != null;
    }
    public Integer getIdTuile() {
        return idTuile;
    }

    @Override
    public String toString() {
        String txt = "";
        txt += commande.toString() + " ";
        if (hasIdAventurier()) {
            txt += " aventurier=" + idAventurier;
        }
        if (hasIdCarte()) {
            txt += " carte=" + idCarte;
        }
        if (hasIdTuile()) {
            txt += " tuile=" + idTuile;
        }
        if (hasTresor()) {
            txt += " tresor=" + tresor.toString();
        }
        return txt;
    }
}
