package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.vue.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tuile {

    private Grille grille;
    private String nom;
    private String image;
    private Utils.EtatTuile etat;
    private Utils.Tresor tresor;
    private Utils.Pion departPion;
    private ArrayList<Aventurier> aventuriers;

    Tuile (String nom) {
        this.nom = nom;
        this.etat = Utils.EtatTuile.ASSECHEE;
        aventuriers = new ArrayList<>();
    }

    Tuile (String nom, String image) {
        this.nom = nom;
        this.etat = Utils.EtatTuile.ASSECHEE;
        this.image = image;
        aventuriers = new ArrayList<>();
    }

    Tuile (String nom, String image, Utils.Tresor tresor) {
        this(nom, image);
        this.tresor = tresor;
    }

    Tuile (String nom,String image, Utils.Pion departPion) {
        this(nom, image);
        this.departPion = departPion;
    }

    public String getNom() {
        return this.nom;
    }

    public Utils.EtatTuile getEtat() {
        return this.etat;
    }

    public Utils.Tresor getTresor() {
        return this.tresor;
    }

    public Utils.Pion getDepartPion() {
        return this.departPion;
    }

    public void setEtat(Utils.EtatTuile etat) {
        this.etat = etat;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public Grille getGrille() {
        return grille;
    }

    public void ajouterAventurier(Aventurier aventurier) {
        this.aventuriers.add(aventurier);
    }

    public void supprimerAventurier(Aventurier aventurier) {
        this.aventuriers.remove(aventurier);
    }

    public ImagePanel getImage() {
        ImagePanel IF;
        if (etat.equals(Utils.EtatTuile.ASSECHEE)) {
            IF = new ImagePanel("src/images/tuiles/" + image + ".png", 3);
        } else if (etat.equals(Utils.EtatTuile.INONDEE)) {
            IF = new ImagePanel("src/images/tuiles/" + image + "_Inonde.png", 3);
        } else {
            IF = new ImagePanel();
        }

        IF.setLayout(new GridLayout(3, 2));

        for (Aventurier A: aventuriers) {
            ImagePanel pionIF = new ImagePanel(A.getPion().getPath());
            pionIF.setBackground(new Color(0, 0, 0, 0));
            IF.add(pionIF);
        }

        for (int i = 0; i < 6-aventuriers.size(); i++) {
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            IF.add(panel);
        }

        IF.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 4));
        return IF;
    }

    public ArrayList<Aventurier> getAventuriers() {
        return this.aventuriers;
    }

    public static ArrayList<Tuile> getAllTuiles() {
        ArrayList<Tuile> allTuiles = new ArrayList<>();
        allTuiles.add(new Tuile("La Caverne des Ombres","LaCarverneDesOmbres", Utils.Tresor.CRISTAL));
        allTuiles.add(new Tuile("La Caverne du Brasier","LaCarverneDuBrasier", Utils.Tresor.CRISTAL));
        allTuiles.add(new Tuile("Les Dunes de l'Illusion", "LesDunesDeLIllusion"));
        allTuiles.add(new Tuile("Les Falaises de l'Oubli","LesFalaisesDeLOubli"));
        allTuiles.add(new Tuile("La Forêt Pourpre","LaForetPourpre"));
        allTuiles.add(new Tuile("L'Héliport","Heliport", Utils.Pion.BLEU));
        allTuiles.add(new Tuile("Le Jardin des Hurlements","LeJardinDesHurlements", Utils.Tresor.ZEPHYR));
        allTuiles.add(new Tuile("Le Jardin des Murmures","LeJardinDesMurmures", Utils.Tresor.ZEPHYR));
        allTuiles.add(new Tuile("Le Lagon Perdu","LeLagonPerdu"));
        allTuiles.add(new Tuile("Le Marais Brumeux","LeMaraisBrumeux"));
        allTuiles.add(new Tuile("L'Observatoire","Observatoire"));
        allTuiles.add(new Tuile("Le Palais de Corail","LePalaisDeCorail", Utils.Tresor.CALICE));
        allTuiles.add(new Tuile("Le Palais des Marées","LePalaisDesMarees", Utils.Tresor.CALICE));
        allTuiles.add(new Tuile("Le Pont des Abîmes","LePontDesAbimes"));
        allTuiles.add(new Tuile("La Porte d'Argent","LaPortedArgent", Utils.Pion.ORANGE));
        allTuiles.add(new Tuile("La Porte de Bronze","LaPorteDeBronze", Utils.Pion.ROUGE));
        allTuiles.add(new Tuile("La Porte de Cuivre","LaPorteDeCuivre", Utils.Pion.VERT));
        allTuiles.add(new Tuile("La Porte de Fer","LaPorteDeFer", Utils.Pion.VIOLET));
        allTuiles.add(new Tuile("La Porte d'Or","LaPorteDOr", Utils.Pion.JAUNE));
        allTuiles.add(new Tuile("Le Rocher Fantôme","LeRocherFantome"));
        allTuiles.add(new Tuile("Le Temple de la Lune","LeTempleDeLaLune", Utils.Tresor.PIERRE));
        allTuiles.add(new Tuile("Le Temple du Soleil","LeTempleDuSoleil", Utils.Tresor.PIERRE));
        allTuiles.add(new Tuile("La Tour de Guet","LaTourDuGuet"));
        allTuiles.add(new Tuile("Le Val du Crépuscule","LeValDuCrepuscule"));

        return allTuiles;
    }

}
