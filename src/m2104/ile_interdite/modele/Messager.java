package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

public class Messager extends Aventurier {

    public Messager(int id) {
        super(id, "Messager", Utils.Pion.ORANGE);
    }

    @Override
    public String actionSpeciale() {
        return "digaonales";
    }
}
