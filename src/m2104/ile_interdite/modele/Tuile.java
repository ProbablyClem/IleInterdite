package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.vue.ImageFrame;

import java.util.ArrayList;

public class Tuile {

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

    public void ajouterAventurier(Aventurier aventurier) {
        this.aventuriers.add(aventurier);
    }

    public void supprimerAventurier(Aventurier aventurier) {
        this.aventuriers.remove(aventurier);
    }

    public ImageFrame getImage() {
        return new ImageFrame(image);
    }

    public static ArrayList<Tuile> getAllTuiles() {
        ArrayList<Tuile> allTuiles = new ArrayList<>();
        allTuiles.add(new Tuile("La Caverne des Ombres","src/images/cartes/LaCaverneDesOmbres.png", Utils.Tresor.CRISTAL));
        allTuiles.add(new Tuile("La Caverne du Brasier","src/images/cartes/CaverneDuBrasier.png", Utils.Tresor.CRISTAL));
        allTuiles.add(new Tuile("Les Dunes de l'Illusion", "src/images/cartes/LesDunesDeLIllusion.png"));
        allTuiles.add(new Tuile("Les Falaises de l'Oubli","src/images/cartes/LesFalaisesDeLOubli.png"));
        allTuiles.add(new Tuile("La Forêt Pourpre","src/images/cartes/LaForetPoupre.png"));
        allTuiles.add(new Tuile("L'Héliport","src/images/cartes/Heliport.png", Utils.Pion.BLEU));
        allTuiles.add(new Tuile("Le Jardin des Hurlements","src/images/cartes/", Utils.Tresor.ZEPHYR));
        allTuiles.add(new Tuile("Le Jardin des Murmures","src/images/cartes/", Utils.Tresor.ZEPHYR));
        allTuiles.add(new Tuile("Le Lagon Perdu","src/images/cartes/LeJardinDesHurlements.png"));
        allTuiles.add(new Tuile("Le Marais Brumeux","src/images/cartes/LeMaraisBrumeux.png"));
        allTuiles.add(new Tuile("L'Observatoire","src/images/cartes/Observatoire.png"));
        allTuiles.add(new Tuile("Le Palais de Corail","src/images/cartes/LePalaisDeCorail.png", Utils.Tresor.CALICE));
        allTuiles.add(new Tuile("Le Palais des Marées","src/images/cartes/LePalaisDesMarees.png", Utils.Tresor.CALICE));
        allTuiles.add(new Tuile("Le Pont des Abîmes","src/images/cartes/LePontDesAbimes.png"));
        allTuiles.add(new Tuile("La Porte d'Argent","src/images/cartes/LaPortedArgent.png", Utils.Pion.ORANGE));
        allTuiles.add(new Tuile("La Porte de Bronze","src/images/cartes/LaPorteDeBronze.png", Utils.Pion.ROUGE));
        allTuiles.add(new Tuile("La Porte de Cuivre","src/images/cartes/LaPorteDeCuivre.png", Utils.Pion.VERT));
        allTuiles.add(new Tuile("La Porte de Fer","src/images/cartes/LaPorteDeFer.png", Utils.Pion.VIOLET));
        allTuiles.add(new Tuile("La Porte d'Or","src/images/cartes/LaPorteDOr.png", Utils.Pion.JAUNE));
        allTuiles.add(new Tuile("Le Rocher Fantôme","src/images/cartes/LeRocherFantome.png"));
        allTuiles.add(new Tuile("Le Temple de la Lune","src/images/cartes/LeTempleDeLaLune.png", Utils.Tresor.PIERRE));
        allTuiles.add(new Tuile("Le Temple du Soleil","src/images/cartes/LeTempleDuSoleil.png", Utils.Tresor.PIERRE));
        allTuiles.add(new Tuile("La Tour de Guet","src/images/cartes/LaTourDeGuet.png"));
        allTuiles.add(new Tuile("Le Val du Crépuscule","src/images/cartes/LeValDuCrecupuscule.png"));

        return allTuiles;
    }

}
