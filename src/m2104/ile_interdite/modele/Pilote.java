package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

public class Pilote extends Aventurier {

    public Pilote() {
        super("Pilote", Utils.Pion.BLEU);
    }

    @Override
    public String actionSpeciale() {
return  "s'envoler";//Le Pilote peut aller une fois par tour sur n’importe quelle tuile pour 1 action.
    }
}
