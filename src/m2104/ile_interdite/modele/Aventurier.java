package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author IUT2-Dept Info
 */
public abstract class Aventurier {
    
    int id;
    private String role;
    private String nom;
    private Utils.Pion pion;
    private ArrayList<Carte> cartes;
    private Tuile emplacement;
    private ArrayList<Utils.Tresor> tresors;

    Aventurier (int id, String role, Utils.Pion pion) {
        this.id = id;
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

    public void donnerCarte(Carte carte) {
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

    public static ArrayList<Aventurier> getRandomAventuriers(int nb) {


        ArrayList<Aventurier> allAventuriers = new ArrayList<>();
        allAventuriers.add(new Ingenieur(1));
        allAventuriers.add(new Messager(2));
        allAventuriers.add(new Navigateur(3));
        allAventuriers.add(new Explorateur(4));
        allAventuriers.add(new Plongeur(5));

        Utils.melangerAventuriers(allAventuriers);

        ArrayList<Aventurier> aventuriers = new ArrayList<>();

        aventuriers.add(new Pilote(0));

        for (int i = 0; i < nb - 1; i++) {
            aventuriers.add(allAventuriers.remove(0));
        }

        Collections.shuffle(aventuriers);

        return aventuriers;
    }

}
