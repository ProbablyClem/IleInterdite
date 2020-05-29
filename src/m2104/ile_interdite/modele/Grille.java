package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;
import java.util.Collections;

public class Grille {

    private Tuile[][] tuiles = new Tuile[6][6];

    public Grille() {
        ArrayList<Tuile> listTuiles = getAllTuiles();
        Collections.shuffle(listTuiles);
        for (int i = 1; i <= tuiles.length; i++) {
            for (int j = 1; j <= tuiles[0].length; j++) {
                if (!(i * j < 3 || i * j == 5 || (((i * j == 6) || (i * j == 12)) && ((i % 6 == 0) || (j % 6 == 0))) || i * j > 25)) {
                    tuiles[i-1][j-1] = listTuiles.remove(0);
                }
            }
        }
    }

    public ArrayList<Tuile> getAllTuiles() {
        ArrayList<Tuile> allTuiles = new ArrayList<>();
        allTuiles.add(new Tuile("La Caverne des Ombres", Utils.Tresor.CRISTAL));
        allTuiles.add(new Tuile("La Caverne du Brasier", Utils.Tresor.CRISTAL));
        allTuiles.add(new Tuile("Les Dunes de l'Illusion"));
        allTuiles.add(new Tuile("Les Falaises de l'Oubli"));
        allTuiles.add(new Tuile("La Forêt Pourpre"));
        allTuiles.add(new Tuile("L'Héliport"));
        allTuiles.add(new Tuile("Le Jardin des Hurlements", Utils.Tresor.ZEPHYR));
        allTuiles.add(new Tuile("Le Jardin des Murmures", Utils.Tresor.ZEPHYR));
        allTuiles.add(new Tuile("Le Lagon Perdu"));
        allTuiles.add(new Tuile("Le Marais Brumeux"));
        allTuiles.add(new Tuile("L'Observatoire"));
        allTuiles.add(new Tuile("Le Palais de Corail", Utils.Tresor.CALICE));
        allTuiles.add(new Tuile("Le Palais des Marées", Utils.Tresor.CALICE));
        allTuiles.add(new Tuile("Le Pont des Abîmes"));
        allTuiles.add(new Tuile("La Porte d'Argent"));
        allTuiles.add(new Tuile("La Porte de Bronze"));
        allTuiles.add(new Tuile("La Porte de Cuivre"));
        allTuiles.add(new Tuile("La Porte de Fer"));
        allTuiles.add(new Tuile("La Porte d'Or"));
        allTuiles.add(new Tuile("Le Rocher Fantôme"));
        allTuiles.add(new Tuile("Le Temple de la Lune", Utils.Tresor.PIERRE));
        allTuiles.add(new Tuile("Le Temple du Soleil", Utils.Tresor.PIERRE));
        allTuiles.add(new Tuile("La Tour de Guet"));
        allTuiles.add(new Tuile("Le Val du Crépuscule"));

        return allTuiles;
    }

    public void showGrille() {
        for (int i = 0; i < tuiles.length; i++) {
            for (int j = 0; j < tuiles[0].length; j++) {
                try {
                    System.out.print(" [" + this.tuiles[i][j].getNom().substring(0, 10) + "] ");
                } catch (NullPointerException e) {
                    System.out.print("              ");
                }
            }
            System.out.printf("\n");
        }
    }
}
