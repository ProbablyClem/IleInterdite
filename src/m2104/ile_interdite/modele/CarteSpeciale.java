package m2104.ile_interdite.modele;

import java.util.ArrayList;

public abstract class CarteSpeciale extends Carte {


    public CarteSpeciale(String nom, String image) {
        super(nom, image);
    }

    public static ArrayList<Carte> getAllCartesSpeciale() {
        ArrayList<Carte> cartes = new ArrayList<>();

        cartes.addAll(CarteMonteeDesEaux.getAllCartes());
        cartes.addAll(CarteSacDeSable.getAllCartes());
        cartes.addAll(CarteHelicoptere.getAllCartes());

        return cartes;
    }

}