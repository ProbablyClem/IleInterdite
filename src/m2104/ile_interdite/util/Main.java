package m2104.ile_interdite.util;

import m2104.ile_interdite.controleur.Controleur;
import m2104.ile_interdite.modele.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class Main {

    public static void main(String[] args) {
        //new Controleur();

        Aventurier[] aventuriers = Aventurier.getRandomAventuriers(4);

        System.out.println("\nPersonnages :");
        for (Aventurier A: aventuriers) {
            System.out.println("\t" + A.getRole());
        }

        Grille grille = new Grille();


        for (Aventurier A: aventuriers) {
            for (Tuile[] T: grille.getTuiles()) {
                for (Tuile t: T) {
                    if (t != null) {
                        if (t.getDepartPion() != null) {
                            if (t.getDepartPion().equals(A.getPion())) {
                                A.setEmplacement(t);
                            }
                        }
                    }
                }
            }
        }

        ArrayList<CarteSpeciale> cartesSpeciales = CarteSpeciale.getAllCartesSpeciale(grille);
        Collections.shuffle(cartesSpeciales);

        System.out.println("Cartes Spéciales :");

        for (Aventurier A: aventuriers) {
            System.out.println("\t" + A.getRole() + ": " + A.getEmplacement().getNom());
            for (int i = 0; i < 2; i++) {
                while (cartesSpeciales.get(0).getClass().getSimpleName().equals("CarteMonteeDesEaux")) {
                    cartesSpeciales.add(cartesSpeciales.remove(0));
                }

                System.out.println("\t\t" + cartesSpeciales.get(0).getNom());
                A.donnerCarte(cartesSpeciales.remove(0));
            }
        }

        Collections.shuffle(cartesSpeciales); // on évite ici que les cartes montée des eaux se retrouvent toutes à la fin
        System.out.println("\tAutre");
        for (CarteSpeciale C: cartesSpeciales) {
            System.out.println("\t\t" + C.getNom());
        }

        ArrayList<CarteInondation> cartesInondation = CarteInondation.getAllCartesInondation(grille);
        ArrayList<CarteInondation> defausseInondation = new ArrayList<>();
        Collections.shuffle(cartesInondation);

        for (int i = 0; i < 6; i++) {
            cartesInondation.get(0).utiliser();
            defausseInondation.add(cartesInondation.remove(0));
        }

        System.out.println("Cartes Inondation:");
        for (Carte C: cartesInondation) {
            System.out.println("\t" + C.getNom());
        }

        System.out.println("Défausse Cartes Inondation:");
        for (Carte C: defausseInondation) {
            System.out.println("\t" + C.getNom());
        }

        System.out.println("Grille :");
        grille.showGrille();

    }

}