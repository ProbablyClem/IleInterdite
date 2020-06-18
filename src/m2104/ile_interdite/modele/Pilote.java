package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;

public class Pilote extends Aventurier {

    private boolean AS;

    public Pilote() {
        super("Pilote", Utils.Pion.BLEU);
    }

    public boolean isAS() {
        return AS;
    }

    @Override
    public String getNom() {
        // c'est honteux mais ça marche
        setAS(true);
        return super.getNom();
    }

    public void setAS(boolean AS) {
        this.AS = AS;
    }

    @Override
    public void setEmplacement(Tuile nouveau) {
        if (this.getEmplacement() != null) {
            if (!super.getDeplacementsPossibles().contains(nouveau)) {
                setAS(false);
            }
        }
        super.setEmplacement(nouveau);
    }

    @Override
    public ArrayList<Tuile> getDeplacementsPossibles() {
        if (this.isAS()) {
            ArrayList<Tuile> list = this.getEmplacement().getGrille().getListTuiles();
            list.remove(this.getEmplacement());
            list.removeIf(T -> T.getEtat() == Utils.EtatTuile.COULEE);
            return list;
        } else {
            return super.getDeplacementsPossibles();
        }
    }

    @Override
    public String actionSpeciale() {
        return  "s'envoler";//Le Pilote peut aller une fois par tour sur n’importe quelle tuile pour 1 action.
    }
}
