package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

public class Navigateur extends Aventurier {

    public Navigateur() {
        super("Navigateur", Utils.Pion.JAUNE);
    }

    @Override
    public String actionSpeciale() {
    return "bouger un joueur";
        }
}
