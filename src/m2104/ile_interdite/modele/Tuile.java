package m2104.ile_interdite.modele;

public class Tuile {

    private String nom;
    private byte etat;
    private byte tresor;

    Tuile (String nom) {
        this.nom = nom;
        this.etat = 0;
        this.tresor = 0;
    }

    Tuile (String nom, byte tresor) {
        this.nom = nom;
        this.etat = 0;
        this.tresor = tresor;
    }

    public String getNom() {
        return this.nom;
    }

    public byte getEtat() {
        return this.etat;
    }

    public byte getTresor() {
        return this.tresor;
    }


    public void setEtat(byte etat) {
        this.etat = etat;
    }

}
