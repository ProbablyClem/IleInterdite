package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.awt.Color;

public class Plongeur extends Aventurier {

    public Plongeur(int id) {
        super(id, "Plongeur", Utils.Pion.VIOLET);
    }

    @Override
    public String actionSpeciale() {
        return "nager";//Le Navigateur peut déplacer d’autres joueurs d’1 ou 2 cases adjacentes par action
    }
}
