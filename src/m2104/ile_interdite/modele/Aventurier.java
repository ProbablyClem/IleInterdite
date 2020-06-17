package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.vue.ImagePanel;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author IUT2-Dept Info
 */
public abstract class Aventurier {
    
    private int actions = 3;
    private String role;
    private String nom;
    private Utils.Pion pion;
    private ArrayList<Carte> cartes;
    private Tuile emplacement;
    private ArrayList<Utils.Tresor> tresors;

    Aventurier (String role, Utils.Pion pion) {
        this.role = role;
        this.pion = pion;
        this.cartes = new ArrayList<>();
        this.tresors = new ArrayList<>();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String getRole() {
        return this.role;
    }

    public Utils.Pion getPion() {
        return this.pion;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public int getActions() {
        return actions;
    }

    public Tuile getEmplacement() {
        return this.emplacement;
    }

    public void setEmplacement(Tuile nouveau) {
        if (this.emplacement != null) {
            emplacement.supprimerAventurier(this);
        }
        nouveau.ajouterAventurier(this);
        this.emplacement = nouveau;
    }

    public void seDeplacer(Tuile nouveau) {
        //TODO: function core
    }

    public void passerTour() {
        // TODO: function core
    }

    public void assecher() {
        // TODO: function core
    }

    public void prendreCarte(Carte carte) {
            this.cartes.add(carte);
    }

    public void prendreTresor() {
        // TODO: function core
    }

    public void tirerCarte() {
        // TODO: function core
    }

    public abstract String actionSpeciale();

    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    public ArrayList<Tuile> getDeplacementsPossibles() {
        Grille grille = getEmplacement().getGrille();
        Index index = Grille.getIndex(grille, getEmplacement());
        if (index == null) {
            return null;
        }
        ArrayList<Tuile> list = new ArrayList<>();
        if (index.n1 - 1 >= 0 && grille.getTuiles()[index.n1-1][index.n2] != null && grille.getTuiles()[index.n1-1][index.n2].getEtat() != Utils.EtatTuile.COULEE) {
            list.add(grille.getTuiles()[index.n1-1][index.n2]);
        }
        if (index.n1 + 1 < 6 && grille.getTuiles()[index.n1+1][index.n2] != null && grille.getTuiles()[index.n1+1][index.n2].getEtat() != Utils.EtatTuile.COULEE) {
            list.add(grille.getTuiles()[index.n1+1][index.n2]);
        }
        if (index.n2 - 1 >= 0 && grille.getTuiles()[index.n1][index.n2-1] != null && grille.getTuiles()[index.n1][index.n2-1].getEtat() != Utils.EtatTuile.COULEE) {
            list.add(grille.getTuiles()[index.n1][index.n2-1]);
        }
        if (index.n2 + 1 < 6 && grille.getTuiles()[index.n1][index.n2+1] != null && grille.getTuiles()[index.n1][index.n2+1].getEtat() != Utils.EtatTuile.COULEE) {
            list.add(grille.getTuiles()[index.n1][index.n2+1]);
        }

        return list;
    }

    public ImagePanel getImg() {
        return new ImagePanel("src/images/personnages/" + this.getClass().getSimpleName().toLowerCase() + ".png");
    }

    public static ArrayList<Aventurier> getRandomAventuriers(int nb) {


        ArrayList<Aventurier> allAventuriers = new ArrayList<>();
        allAventuriers.add(new Explorateur());
        allAventuriers.add(new Ingenieur());
        allAventuriers.add(new Messager());
        allAventuriers.add(new Navigateur());
        allAventuriers.add(new Plongeur());

        Utils.melangerAventuriers(allAventuriers);

        ArrayList<Aventurier> aventuriers = new ArrayList<>();

        aventuriers.add(new Pilote());

        for (int i = 0; i < nb - 1; i++) {
            aventuriers.add(allAventuriers.remove(0));
        }

        Collections.shuffle(aventuriers);

        return aventuriers;
    }

    public void donnerCarte(Aventurier A, Carte C) {
        if (this.getEmplacement() == A.getEmplacement()) {
            if (!C.getClass().getCanonicalName().contains("CarteSpeciale")) {
                System.out.println(C.getClass().getCanonicalName());
                A.prendreCarte(this.getCartes().remove(this.getCartes().indexOf(C)));
            }
        }
    }

}
