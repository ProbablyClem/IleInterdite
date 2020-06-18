package m2104.ile_interdite.modele;

import m2104.ile_interdite.vue.ImagePanel;

public abstract class Carte {
    private String image;
    private String nom;

    public Carte(String nom) {
        this.nom = nom;
    }

    public Carte(String image, String nom) {
        this.image = image;
        this.nom = nom;
    }

    public ImagePanel getImage() {
        return new ImagePanel(image, this);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
