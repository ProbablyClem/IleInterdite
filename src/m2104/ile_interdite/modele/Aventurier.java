package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.vue.ImagePanel;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Aventurier {

    private int actions = 3;
    private String role;
    private String nom;
    private Utils.Pion pion;
    private ArrayList<Carte> cartes;
    private Tuile emplacement;
    private ArrayList<Utils.Tresor> tresors;

    Aventurier(String role, Utils.Pion pion) {
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
        nouveau.ajouterAventurier(this);
        this.emplacement = nouveau;
    }

    public void seDeplacer(Tuile nouveau) {
        //TODO: function core
    }

    public void passerTour() {
        // TODO: function core
    }

    public void assecher(Tuile t) {
        t.setEtat(Utils.EtatTuile.ASSECHEE);
        setActions(actions - 1);
    }

    public void prendreCarte(Carte carte) {
        this.cartes.add(carte);
    }

    public boolean prendreTresor() {

        ArrayList<CarteTresor> cartesTresor = new ArrayList<>();
        for (Carte c : cartes) {
            if (c instanceof CarteTresor && ((CarteTresor) c).getTresor() == emplacement.getTresor()) {
                cartesTresor.add((CarteTresor) c);
            }
        }
        if (cartesTresor.size() >= 4) {
            for (int i = 0; i < 4; i++) {
                cartes.remove(cartesTresor.get(i));
            }
            return true;
        }
        else {
            return false;
        }
    }


    public void tirerCarte() {
        // TODO: function core
    }

    public abstract String actionSpeciale();

    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    public ArrayList<Tuile> getDeplacementsPossibles() {
        ArrayList<Tuile> list = new ArrayList<>();

        for (Tuile T: this.getEmplacement().getGrille().getTuilesAdjacentes(this.getEmplacement())) {
            if (T.getEtat() != Utils.EtatTuile.COULEE) {
                list.add(T);
            }
        }

        return list;
    }

    public ArrayList<Tuile> getAssechementPossible(){
        ArrayList<Tuile> list = new ArrayList<>();

        if (emplacement.getEtat() == Utils.EtatTuile.INONDEE){
            list.add(emplacement);
        }

        for (Tuile T: this.getEmplacement().getGrille().getTuilesAdjacentes(this.getEmplacement())) {
            if (T.getEtat() == Utils.EtatTuile.INONDEE) {
                list.add(T);
            }
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
        allAventuriers.add(new Pilote());

        Utils.melangerAventuriers(allAventuriers);

        ArrayList<Aventurier> aventuriers = new ArrayList<>();


        for (int i = 0; i < nb; i++) {
            aventuriers.add(allAventuriers.remove(0));
        }

        Collections.shuffle(aventuriers);

        return aventuriers;
    }

    public void donnerCarte(Aventurier A, Carte C) {
        if (this.getEmplacement() == A.getEmplacement()) {
            if (C.getClass().getSimpleName().equals("CarteTresor")) {
                A.prendreCarte(this.getCartes().remove(this.getCartes().indexOf(C)));
            }
        }
    }

    public ArrayList<Carte> getCartesTresor() {
        ArrayList<Carte> cartes = new ArrayList<>();

        for (Carte C: getCartes()) {
            if (C instanceof CarteTresor) {
                cartes.add(C);
            }
        }

        return cartes;
    }

    public void setCartes(ArrayList<Carte> cartes) {
        this.cartes = cartes;
    }
}
