package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;

public class CarteTresor extends Carte {
    
    private Utils.Tresor tresor;


    public CarteTresor(String image, String nom, Utils.Tresor tresor) {
        super(image, nom);
        this.tresor = tresor;
    }


    public static ArrayList<Carte> getAllCartes() {
        ArrayList<Carte> cartes = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            cartes.add(new CarteTresor("Calice","src/images/Calice.png", Utils.Tresor.CALICE));
            cartes.add(new CarteTresor("Pierre", "src/images/Pierre.png", Utils.Tresor.PIERRE));
            cartes.add(new CarteTresor("Zephyr", "src/images/Zephyr.png", Utils.Tresor.ZEPHYR));
            cartes.add(new CarteTresor("Cristal", "src/images/Cristal.png", Utils.Tresor.CRISTAL));
        }

        return cartes;
    }
}
