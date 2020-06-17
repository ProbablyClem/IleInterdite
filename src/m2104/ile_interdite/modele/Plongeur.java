package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;

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

        return list;
    }

}
