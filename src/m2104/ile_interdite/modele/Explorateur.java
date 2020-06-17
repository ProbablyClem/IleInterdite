package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;

public class Explorateur extends Aventurier {

    public Explorateur() {
        super("Explorateur", Utils.Pion.VERT);
    }

    @Override
    public String actionSpeciale() {
    return "assecher";
    }

    @Override
    public ArrayList<Tuile> getDeplacementsPossibles() {
        Grille grille = getEmplacement().getGrille();
        Index index = Grille.getIndex(grille, getEmplacement());
        if (index == null) {
            return null;
        }
        ArrayList<Tuile> list = super.getDeplacementsPossibles();
        if (index.n1 - 1 >= 0 && index.n2 - 1 >= 0 && grille.getTuiles()[index.n1-1][index.n2-1] != null && grille.getTuiles()[index.n1-1][index.n2-1].getEtat() != Utils.EtatTuile.COULEE) {
            list.add(grille.getTuiles()[index.n1-1][index.n2-1]);
        }
        if (index.n1 - 1 >= 0 && index.n2 + 1 < 6 && grille.getTuiles()[index.n1-1][index.n2+1] != null && grille.getTuiles()[index.n1-1][index.n2+1].getEtat() != Utils.EtatTuile.COULEE) {
            list.add(grille.getTuiles()[index.n1-1][index.n2+1]);
        }
        if (index.n1 + 1 < 6 && index.n2 - 1 >= 0 && grille.getTuiles()[index.n1+1][index.n2-1] != null && grille.getTuiles()[index.n1+1][index.n2-1].getEtat() != Utils.EtatTuile.COULEE) {
            list.add(grille.getTuiles()[index.n1+1][index.n2-1]);
        }
        if (index.n1 + 1 < 6 && index.n2 + 1 < 6 && grille.getTuiles()[index.n1+1][index.n2+1] != null && grille.getTuiles()[index.n1+1][index.n2+1].getEtat() != Utils.EtatTuile.COULEE) {
            list.add(grille.getTuiles()[index.n1+1][index.n2+1]);
        }

        return list;
    }
}
