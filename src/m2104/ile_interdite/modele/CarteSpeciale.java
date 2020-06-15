package m2104.ile_interdite.modele;

import java.util.ArrayList;

public abstract class CarteSpeciale extends Carte {

    public static ArrayList<CarteSpeciale> getAllCartesSpeciale() {
        ArrayList<CarteSpeciale> cartes = new ArrayList<>();

        cartes.addAll(CarteMonteeDesEaux.getAllCartes());
        cartes.addAll(CarteSacDeSable.getAllCartes());
        cartes.addAll(CarteTresor.getAllCartes());
        cartes.addAll(CarteHelicoptere.getAllCartes());

        return cartes;
    }

}