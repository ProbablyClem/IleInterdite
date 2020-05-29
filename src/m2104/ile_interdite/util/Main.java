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
        ArrayList<Aventurier> allAventuriers = new ArrayList<>();
        allAventuriers.add(new Explorateur(0));
        allAventuriers.add(new Ingenieur(1));
        allAventuriers.add(new Messager(2));
        allAventuriers.add(new Navigateur(3));
        allAventuriers.add(new Pilote(4));
        allAventuriers.add(new Plongeur(5));

        Utils.melangerAventuriers(allAventuriers);

        Aventurier[] aventuriers = new Aventurier[4];

        for (int i = 0; i < 4; i++) {
            aventuriers[i] = allAventuriers.remove(0);
        }

        Grille grille = new Grille();
        System.out.println("Grille :");
        grille.showGrille();
        System.out.println("\nPersonnages :");
        for (Aventurier A: aventuriers) {
            System.out.println("\t" + A.getRole());
        }
    }

}