package m2104.ile_interdite.modele;


import m2104.ile_interdite.util.Utils;

public class Ingenieur extends Aventurier {

    public Ingenieur(int id) {
        super(id, "Ingénieur", Utils.Pion.ROUGE);
    }

    @Override
    public String actionSpeciale() {
        return "assecher";
    }
}
