package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Parameters;
import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;

public class CarteSacDeSable extends CarteSpeciale {



    public CarteSacDeSable() {
        super( "src/images/tresors/SacsDeSable.png", "Sac Sable");
    }



    public void assecher(Tuile tuile) {
        if (tuile.getEtat() == Utils.EtatTuile.INONDEE) {
            tuile.setEtat(Utils.EtatTuile.ASSECHEE);
        } else {
            System.out.println("La tuile ne peut pas être asséchée");
        }
    }

    public static ArrayList<CarteSacDeSable> getAllCartes() {
        ArrayList<CarteSacDeSable> cartes = new ArrayList<>();

        for (int i = 0; i < Parameters.NB_SACS_DE_SABLE; i++) {
            cartes.add(new CarteSacDeSable());
        }

        return cartes;
    }
}
