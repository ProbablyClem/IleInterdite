package m2104.ile_interdite.modele;

import java.util.ArrayList;

public class CarteMonteeDesEaux extends CarteSpeciale {

    private Grille grille;

    CarteMonteeDesEaux (Grille grille) {
        this.grille = grille;
    }

    @Override
    public String getNom() {
        return "Carte Montée des Eaux";
    }

    @Override
    public void utiliser() {
        System.out.println("Utilisation de Montée des Eaux !");
    }

    public static ArrayList<CarteMonteeDesEaux> getAllCartes(Grille grille) {
        ArrayList<CarteMonteeDesEaux> cartes = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            cartes.add(new CarteMonteeDesEaux(grille));
        }

        return cartes;
    }
}
