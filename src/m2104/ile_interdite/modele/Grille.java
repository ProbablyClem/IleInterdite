package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Grille {

    private Tuile[][] tuiles = new Tuile[6][6];

    private byte niveauEau;

    public Grille() {

        ArrayList<Tuile> listTuiles = Tuile.getAllTuiles();
        Collections.shuffle(listTuiles);
        for (int i = 1; i <= tuiles.length; i++) {
            for (int j = 1; j <= tuiles[0].length; j++) {
                if (!(i * j < 3 || i * j == 5 || (((i * j == 6) || (i * j == 12)) && ((i % 6 == 0) || (j % 6 == 0))) || i * j > 25)) {
                    tuiles[i-1][j-1] = listTuiles.remove(0);
                }
            }
        }
    }

    public Tuile[][] getTuiles() {
        return this.tuiles;
    }

    public void showGrille() {
        for (int i = 0; i < tuiles.length; i++) {
            for (int j = 0; j < tuiles[0].length; j++) {
                try {
                    if (tuiles[i][j].getEtat().equals(Utils.EtatTuile.ASSECHEE)) {
                        System.out.print(" [" + this.tuiles[i][j].getNom().substring(0, 10) + "] ");
                    } else if (tuiles[i][j].getEtat().equals(Utils.EtatTuile.INONDEE)) {
                        System.out.print(" ~" + this.tuiles[i][j].getNom().substring(0, 10) + "~ ");
                    } else {
                        System.out.print(" #" + this.tuiles[i][j].getNom().substring(0, 10) + "# ");
                    }
                } catch (NullPointerException e) {
                    System.out.print("              ");
                }
            }
            System.out.print("\n");
        }
    }
}
