package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;

public class CarteTresor extends CarteSpeciale {
    
    private Utils.Tresor tresor;

    CarteTresor(Utils.Tresor tresor) {
        this.tresor = tresor;
    }

    @Override
    public String getNom() {
        return ("Carte trésor " + this.tresor.name());
    }

    @Override
    public void utiliser() {
        System.out.println("Utilisation de " + this.getNom());
    }

    public static ArrayList<CarteTresor> getAllCartes() {
        ArrayList<CarteTresor> cartes = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            cartes.add(new CarteTresor(Utils.Tresor.CALICE));
            cartes.add(new CarteTresor(Utils.Tresor.PIERRE));
            cartes.add(new CarteTresor(Utils.Tresor.ZEPHYR));
            cartes.add(new CarteTresor(Utils.Tresor.CRISTAL));
        }

        return cartes;
    }
    @Override
    public String getImage(){
        return null;
        
}
}
