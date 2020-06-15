package m2104.ile_interdite.modele;


import java.util.ArrayList;


public class CarteHelicoptere extends CarteSpeciale {

    public CarteHelicoptere(){
        super("Carte helicoptere", "src/images/Helicoptere.png");
    }



    public static ArrayList<CarteHelicoptere> getAllCartes() {
        ArrayList<CarteHelicoptere> cartes = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            cartes.add(new CarteHelicoptere());
        }

        return cartes;
    }

}
