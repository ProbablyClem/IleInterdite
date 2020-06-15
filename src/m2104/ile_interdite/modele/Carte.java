package m2104.ile_interdite.modele;

import java.io.File;
import java.util.ArrayList;

public abstract class Carte {

    Aventurier joueur;

    public void setJoueur(Aventurier joueur) {
        this.joueur = joueur;
    }

    public Aventurier getJoueur() {
        return this.joueur;
    }

    public void rmvJoueur() {
        this.joueur = null;
    }

    public abstract String getNom();

    public abstract void utiliser();
    
    public abstract String getImage();
}
