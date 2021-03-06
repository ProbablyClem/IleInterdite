package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;
import java.util.HashSet;

public class Plongeur extends Aventurier {

    public Plongeur() {
        super("Plongeur", Utils.Pion.VIOLET);
    }

    @Override
    public String actionSpeciale() {
        return "nager";//Le Navigateur peut déplacer d’autres joueurs d’1 ou 2 cases adjacentes par action
    }

    @Override
    public ArrayList<Tuile> getDeplacementsPossibles() {
        ArrayList<Tuile> list = super.getDeplacementsPossibles();

        ArrayList<Tuile> inondees = getToutesTuilesInondeesAdjacentes(new ArrayList<>(), this.getEmplacement());

        list.addAll(inondees);

        for (Tuile T: inondees) {
            list.addAll(T.getGrille().getTuilesAdjacentes(T));
        }

        HashSet set = new HashSet<Tuile>(list);
        list = new ArrayList<Tuile>(set);

        list.removeIf(T -> T.getEtat() == Utils.EtatTuile.COULEE);

        list.remove(getEmplacement());

        return list;

        // (╯°□°）╯︵ ┻━┻
    }

    public static ArrayList<Tuile> getToutesTuilesInondeesAdjacentes(ArrayList<Tuile> passed, Tuile T) {
        for (Tuile t: T.getGrille().getTuilesAdjacentes(T)) {
            if (!passed.contains(t) && t.getEtat() != Utils.EtatTuile.ASSECHEE) {
                passed.add(t);
                getToutesTuilesInondeesAdjacentes(passed, t);
            }
        }

        return passed;
    }
}
