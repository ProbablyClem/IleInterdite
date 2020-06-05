package m2104.ile_interdite.modele;

import java.util.ArrayList;

public class CarteHelicoptere extends CarteSpeciale {

    @Override
    public String getNom() {
        return "Carte Hélicoptère";
    }

    @Override
    public void utiliser() {
        System.out.println("Utilisation de la " + this.getNom());
    }

    public static ArrayList<CarteHelicoptere> getAllCartes() {
        ArrayList<CarteHelicoptere> cartes = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            cartes.add(new CarteHelicoptere());
        }

        return cartes;
    }

}
