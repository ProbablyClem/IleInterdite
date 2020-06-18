package m2104.ile_interdite.modele;


import m2104.ile_interdite.util.Utils;

public class Ingenieur extends Aventurier {

    private boolean etatAS;

    public Ingenieur() {
        super("Ingénieur", Utils.Pion.ROUGE);
    }

    @Override
    public String getNom() {
        etatAS = true;
        return super.getNom();
    }

    public void assecher(Tuile t) {
        t.setEtat(Utils.EtatTuile.ASSECHEE);
        if (!etatAS) {
            setActions(getActions() - 1);
        }
        etatAS = !etatAS;
    }

    @Override
    public String actionSpeciale() {
        return "assecher";
    }
}
