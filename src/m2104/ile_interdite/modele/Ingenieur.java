package m2104.ile_interdite.modele;


import m2104.ile_interdite.util.Utils;

public class Ingenieur extends Aventurier {

    public Ingenieur() {
        super("Ingénieur", Utils.Pion.ROUGE);
    }

    @Override
    public String actionSpeciale() {
        return "assecher";
    }
}
