package m2104.ile_interdite.modele;


import java.util.ArrayList;


public class CarteHelicoptere extends CarteSpeciale {

    public CarteHelicoptere(){
        super("src/images/Helicoptere.png", "Carte helicoptere");
    }



    public static ArrayList<CarteHelicoptere> getAllCartes() {
        ArrayList<CarteHelicoptere> cartes = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            cartes.add(new CarteHelicoptere());
        }

        return cartes;
    }

}
