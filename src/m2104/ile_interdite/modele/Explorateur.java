package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.vue.ImagePanel;

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
        ArrayList<Tuile> list = super.getDeplacementsPossibles();

        for (Tuile T: this.getEmplacement().getGrille().getTuilesDiagonales(this.getEmplacement())) {
            if (T.getEtat() != Utils.EtatTuile.COULEE) {
                list.add(T);
            }
        }

        return list;
    }

    @Override
    public ArrayList<Tuile> getAssechementPossible() {
        ArrayList<Tuile> list = super.getAssechementPossible();

        for (Tuile T: this.getEmplacement().getGrille().getTuilesDiagonales(this.getEmplacement())) {
            if (T.getEtat() == Utils.EtatTuile.INONDEE) {
                list.add(T);
            }
        }

        return list;
    }
}
