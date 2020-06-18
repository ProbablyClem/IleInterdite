package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

public class Navigateur extends Aventurier {

    private boolean ASutilisee;

    public Navigateur() {
        super("Navigateur", Utils.Pion.JAUNE);
    }

    @Override
    public String getNom() {
        ASutilisee = false;
        return super.getNom();
    }

    public boolean isASutilisee() {
        return ASutilisee;
    }

    public void utiliserAS() {
        ASutilisee = true;
    }

    @Override
    public String actionSpeciale() {
        return "bouger un joueur";
    }
}
