package m2104.ile_interdite.util;

import m2104.ile_interdite.modele.Aventurier;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Utils {

    public static enum Etat{
        DEPLACER_JOUEUR,
        ASSECHER_CASE,
    }
    public static enum Commandes {
        VALIDER_JOUEURS("Valider l'inscription des joueurs"),
        BOUGER("Déplacer son pion"),
        ASSECHER("Assécher une tuile"),
        DONNER("Donner une carte à un autre joueur"),
        RECUPERER_TRESOR("Récupérer le trésor de la tuile"),
        TERMINER("Terminer son tour"),
        RECEVOIR("Recevoir la carte donnée par un autre joueur"),
        CHOISIR_CARTE("Utiliser une carte trésor"),
        CHOISIR_TUILE("Sélectionner une tuile"),
        DEPLACER("Déplacer un autre joueur"),
        VOIR_DEFAUSSE("Un joueur souhaite voir la défausse de cartes Tirage"),
        VOIR_DECK("Voir un deck"),
        ACTION_SPECIALE("Action spéciale"),
        CHOIX_NIVEAU("Choix du niveau de difficulté"),
        CHOIX_CARTE("Choix de la carte liée à certaines commandes"),
        CHOIX_AVENTURIER("Choix de l'aventurier lié à certaines commandes"),
        FIN_PARTIE("La partie est fini"),
        RELANCER("Relancer la partie");

        private final String libelle ;

        Commandes(String libelle) {
            this.libelle = libelle ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }
    }

    public static enum EtatTuile {
        ASSECHEE("Asséchée"),
        INONDEE("Inondée"),
        COULEE("Coulée");

        private final String libelle ;

        EtatTuile(String libelle) {
            this.libelle = libelle ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }
    }

    public static enum Tresor {
        PIERRE("La Pierre Sacrée", new Color(141,79,9), new Color(255,242,0), "src/images/tresors/pierre.png"),
        ZEPHYR("La statue du Zéphyr", new Color(255,215,0), new Color(208,26,136), "src/images/tresors/zephyr.png"),
        CRISTAL("Le Cristal Ardent", new Color(219,56,154), new Color(99,187,242), "src/images/tresors/cristal.png"),
        CALICE("Le Calice de l'Onde", new Color(27,188,245), new Color(141,79,9), "src/images/tresors/calice.png") ;

        private final String libelle;
        private final Color bgColor ;
        private final Color textColor ;
        private final String pathPicture ;

        Tresor(String libelle, Color bgColor, Color textColor, String pathPicture) {
            this.libelle = libelle;
            this.bgColor = bgColor ;
            this.textColor = textColor ;
            this.pathPicture = pathPicture ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }

        public Color getBgColor() {
            return this.bgColor ;
        }

        public Color getTextColor() {
            return this.textColor ;
        }

        public String getPathPicture() {
            return this.pathPicture ;
        }

        public static Tresor getFromName(String name) {
            for (Tresor tresor: Tresor.values()) {
                if (tresor.name().equals(name)) {
                    return tresor;
                }
            }
            return null;
        }
    }

    public static enum Pion {
        ROUGE("Rouge", new Color(255, 0, 0), new Color(176, 79, 79), new Color(255, 145, 145), new Color(226,166,166), "pionRouge.png"),
        VERT("Vert", new Color(0, 195, 0), new Color(79, 153, 79), new Color(145, 255, 145), new Color(166,226,166), "pionVert.png"),
        BLEU("Bleu", new Color(55,194,198), new Color(100,153,154), new Color(175,221,221), new Color(202,219,219), "pionBleu.png"),
        ORANGE("Orange", new Color(255, 148, 0), new Color(176, 135, 79), new Color(255, 199, 127), new Color(246,198,135), "pionBronze.png"),
        VIOLET("Violet", new Color(204, 94, 255), new Color(146, 115, 176), new Color(211, 164, 234), new Color(202,176,214), "pionViolet.png"),
        JAUNE("Jaune", new Color(255, 255, 0), new Color(176, 176, 79), new Color(255, 255, 140), new Color(245,245,148), "pionJaune.png") ;

        private final String libelle ;
        private final Color couleur ;
        private final Color couleurGrisee ;
        private final Color couleurSelectionTuileAssechee ;
        private final Color couleurSelectionTuileInondee ;
        private final String picturePath ;

        Pion (String libelle, Color couleur, Color couleurGrisee, Color couleurSelectionTuileAssechee, Color couleurSelectionTuileInondee, String path) {
            this.libelle = libelle ;
            this.couleur = couleur ;
            this.couleurGrisee = couleurGrisee ;
            this.couleurSelectionTuileAssechee = couleurSelectionTuileAssechee ;
            this.couleurSelectionTuileInondee = couleurSelectionTuileInondee ;
            this.picturePath = path ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }

        public Color getCouleur() {
            return this.couleur ;
        }

        public Color getCouleurGrisee() {
            return this.couleurGrisee ;
        }

        public Color getCouleurSelectionAssechee() {
            return this.couleurSelectionTuileAssechee ;
        }

        public Color getCouleurSelectionInondee() {
            return this.couleurSelectionTuileInondee ;
        }

        public String getPath() {
            return "src/images/pions/" + this.picturePath ;
        }

        public static Pion getFromName(String name) {
            for (Pion pion: Pion.values()) {
                if (pion.name().equals(name)) {
                    return pion;
                }
            }
            return null;
        }


    }

    public static enum Deck{
        DECK_TRESOR("Deck cartes tresor"),
        DECK_INONDATION("Deck cartes inondation"),
        DEFFAUSSE_TRESOR("Deffausse cartes tresor"),
        DEFFAUSSE_INONDATION("Deffausse cartes inondation");

        public final String libelle;

        Deck(String libelle){
            this.libelle = libelle;
        }
    }

    public static String toRGB(Color couleur) {
        return "#"+Integer.toHexString(couleur.getRGB()).substring(2);
    }

    public static ArrayList<Aventurier> melangerAventuriers(ArrayList<Aventurier> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList ;
    }

    public static Integer[] melangerPositions(Integer[] tableau) {
        if (Parameters.ALEAS) {
            Collections.shuffle(Arrays.asList(tableau));
        }
        return tableau ;
    }

    /**
     * Permet de poser une question à laquelle l'utilisateur répond par oui ou non
     * @param question texte à afficher
     * @return true si l'utilisateur répond oui, false sinon
     */
    public static Boolean poserQuestion(String question) {
        System.out.println("Divers.poserQuestion(" + question + ")");
        int reponse = JOptionPane.showConfirmDialog (null, question, "", JOptionPane.YES_NO_OPTION) ;
        System.out.println("\tréponse : " + (reponse == JOptionPane.YES_OPTION ? "Oui" : "Non"));
        return reponse == JOptionPane.YES_OPTION;
    }

    /**
     * Permet d'afficher un message d'information avec un bouton OK
     * @param message Message à afficher
     */
    public static void afficherInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.OK_OPTION);
    }
}
