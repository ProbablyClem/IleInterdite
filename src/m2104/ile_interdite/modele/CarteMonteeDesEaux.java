package m2104.ile_interdite.modele;

import java.util.ArrayList;

public class CarteMonteeDesEaux extends CarteSpeciale {


    public CarteMonteeDesEaux(){
        super("Carte montée des eaux", "src/images/MonteeDesEaux.png");
    }



    public static ArrayList<CarteMonteeDesEaux> getAllCartes() {
        ArrayList<CarteMonteeDesEaux> cartes = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            cartes.add(new CarteMonteeDesEaux());
        }

        return cartes;
    }
}
