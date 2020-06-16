package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

public class Explorateur extends Aventurier {

    public Explorateur() {
        super("Explorateur", Utils.Pion.VERT);
    }

    @Override
    public String actionSpeciale() {
    return "assecher";
    }
}
