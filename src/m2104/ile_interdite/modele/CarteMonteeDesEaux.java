package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Parameters;

import java.util.ArrayList;

public class CarteMonteeDesEaux extends CarteSpeciale {


    public CarteMonteeDesEaux(){
        super( "src/images/cartes/MonteeDesEaux.png", "Carte montée des eaux");
    }



    public static ArrayList<CarteMonteeDesEaux> getAllCartes() {
        ArrayList<CarteMonteeDesEaux> cartes = new ArrayList<>();

        for (int i = 0; i < Parameters.NB_MONTEES_DES_EAUX; i++) {
            cartes.add(new CarteMonteeDesEaux());
        }

        return cartes;
    }
}
