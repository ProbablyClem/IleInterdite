package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;

public class CarteTresor extends Carte {
    
    private Utils.Tresor tresor;


    public CarteTresor(String image, String nom, Utils.Tresor tresor) {
        super(image, nom);
        this.tresor = tresor;
    }

    public Utils.Tresor getTresor() {
        return tresor;
    }

    public static ArrayList<Carte> getAllCartes() {
        ArrayList<Carte> cartes = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            cartes.add(new CarteTresor("src/images/cartes/Calice.png","Calice", Utils.Tresor.CALICE));
            cartes.add(new CarteTresor("src/images/cartes/Pierre.png","Pierre", Utils.Tresor.PIERRE));
            cartes.add(new CarteTresor("src/images/cartes/Zephyr.png","Zephyr", Utils.Tresor.ZEPHYR));
            cartes.add(new CarteTresor( "src/images/cartes/Cristal.png","Cristal", Utils.Tresor.CRISTAL));
        }

        return cartes;
    }
}
