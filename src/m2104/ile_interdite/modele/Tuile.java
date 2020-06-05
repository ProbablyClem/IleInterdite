package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;

public class Tuile {

    private String nom;
    private Utils.EtatTuile etat;
    private Utils.Tresor tresor;
    private Utils.Pion departPion;
    private ArrayList<Aventurier> aventuriers;

    Tuile (String nom) {
        this.nom = nom;
        this.etat = Utils.EtatTuile.ASSECHEE;
        aventuriers = new ArrayList<>();
    }

    Tuile (String nom, Utils.Tresor tresor) {
        this(nom);
        this.tresor = tresor;
    }

    Tuile (String nom, Utils.Pion departPion) {
        this(nom);
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

    public static ArrayList<Tuile> getAllTuiles() {
        ArrayList<Tuile> allTuiles = new ArrayList<>();
        allTuiles.add(new Tuile("La Caverne des Ombres", Utils.Tresor.CRISTAL));
        allTuiles.add(new Tuile("La Caverne du Brasier", Utils.Tresor.CRISTAL));
        allTuiles.add(new Tuile("Les Dunes de l'Illusion"));
        allTuiles.add(new Tuile("Les Falaises de l'Oubli"));
        allTuiles.add(new Tuile("La Forêt Pourpre"));
        allTuiles.add(new Tuile("L'Héliport", Utils.Pion.BLEU));
        allTuiles.add(new Tuile("Le Jardin des Hurlements", Utils.Tresor.ZEPHYR));
        allTuiles.add(new Tuile("Le Jardin des Murmures", Utils.Tresor.ZEPHYR));
        allTuiles.add(new Tuile("Le Lagon Perdu"));
        allTuiles.add(new Tuile("Le Marais Brumeux"));
        allTuiles.add(new Tuile("L'Observatoire"));
        allTuiles.add(new Tuile("Le Palais de Corail", Utils.Tresor.CALICE));
        allTuiles.add(new Tuile("Le Palais des Marées", Utils.Tresor.CALICE));
        allTuiles.add(new Tuile("Le Pont des Abîmes"));
        allTuiles.add(new Tuile("La Porte d'Argent", Utils.Pion.ORANGE));
        allTuiles.add(new Tuile("La Porte de Bronze", Utils.Pion.ROUGE));
        allTuiles.add(new Tuile("La Porte de Cuivre", Utils.Pion.VERT));
        allTuiles.add(new Tuile("La Porte de Fer", Utils.Pion.VIOLET));
        allTuiles.add(new Tuile("La Porte d'Or", Utils.Pion.JAUNE));
        allTuiles.add(new Tuile("Le Rocher Fantôme"));
        allTuiles.add(new Tuile("Le Temple de la Lune", Utils.Tresor.PIERRE));
        allTuiles.add(new Tuile("Le Temple du Soleil", Utils.Tresor.PIERRE));
        allTuiles.add(new Tuile("La Tour de Guet"));
        allTuiles.add(new Tuile("Le Val du Crépuscule"));

        return allTuiles;
    }

}
