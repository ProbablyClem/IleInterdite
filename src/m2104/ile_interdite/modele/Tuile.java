package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

public class Tuile {

    private String nom;
    private Utils.EtatTuile etat;
    private Utils.Tresor tresor;

    Tuile (String nom) {
        this.nom = nom;
        this.etat = Utils.EtatTuile.ASSECHEE;
    }

    Tuile (String nom, Utils.Tresor tresor) {
        this(nom);
        this.tresor = tresor;
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


    public void setEtat(Utils.EtatTuile etat) {
        this.etat = etat;
    }

}
