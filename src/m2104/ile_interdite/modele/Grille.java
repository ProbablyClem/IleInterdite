package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;
import java.util.Collections;

public class Grille {

    private Tuile[][] tuiles = new Tuile[6][6];
    private Tresor[] tresors = new Tresor[4];
    private byte niveauEau;

    public Grille() {

        ArrayList<Tuile> listTuiles = Tuile.getAllTuiles();
        Collections.shuffle(listTuiles);
        for (int i = 1; i <= tuiles.length; i++) {
            for (int j = 1; j <= tuiles[0].length; j++) {
                if (!(i * j < 3 || i * j == 5 || (((i * j == 6) || (i * j == 12)) && ((i % 6 == 0) || (j % 6 == 0))) || i * j > 25)) {
                    listTuiles.get(0).setGrille(this);
                    tuiles[i-1][j-1] = listTuiles.remove(0);
                }
            }
        }

        tresors[0] = new Tresor(Utils.Tresor.CRISTAL);
        tresors[1] = new Tresor(Utils.Tresor.CALICE);
        tresors[2] = new Tresor(Utils.Tresor.PIERRE);
        tresors[3] = new Tresor(Utils.Tresor.ZEPHYR);

    }

    public Tuile[][] getTuiles() {
        return this.tuiles;
    }

    public ArrayList<Tuile> getListTuiles() {
        ArrayList<Tuile> list = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (getTuiles()[i][j] != null) {
                    list.add(getTuiles()[i][j]);
                }
            }
        }

        return list;
    }

    public void showGrille() {
        for (Tuile[] T : tuiles) {
            for (Tuile t: T) {
                try {
                    if (t.getEtat().equals(Utils.EtatTuile.ASSECHEE)) {
                        System.out.print(" [" + t.getNom().substring(0, 10) + "] ");
                    } else if (t.getEtat().equals(Utils.EtatTuile.INONDEE)) {
                        System.out.print(" ~" + t.getNom().substring(0, 10) + "~ ");
                    } else {
                        System.out.print(" #" + t.getNom().substring(0, 10) + "# ");
                    }
                } catch (NullPointerException e) {
                    System.out.print("              ");
                }
            }
            System.out.print("\n");
        }
    }

    public static Index getIndex(Grille G, Tuile T) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (G.getTuiles()[i][j] != null) {
                    if (G.getTuiles()[i][j] == T) {
                        return new Index(i, j);
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Tuile> getTuilesAdjacentes(Tuile T) {
        Index i = Grille.getIndex(this, T);
        ArrayList<Tuile> list = new ArrayList<>();

        if (i != null) {
            if (i.n1 - 1 >= 0 && this.getTuiles()[i.n1 - 1][i.n2    ] != null) list.add(this.getTuiles()[i.n1 - 1][i.n2    ]);
            if (i.n1 + 1 < 6  && this.getTuiles()[i.n1 + 1][i.n2    ] != null) list.add(this.getTuiles()[i.n1 + 1][i.n2    ]);
            if (i.n2 - 1 >= 0 && this.getTuiles()[i.n1    ][i.n2 - 1] != null) list.add(this.getTuiles()[i.n1    ][i.n2 - 1]);
            if (i.n2 + 1 < 6  && this.getTuiles()[i.n1    ][i.n2 + 1] != null) list.add(this.getTuiles()[i.n1    ][i.n2 + 1]);
        }

        return list;
    }

    public ArrayList<Tuile> getTuilesDiagonales(Tuile T) {
        Index i = Grille.getIndex(this, T);
        ArrayList<Tuile> list = new ArrayList<>();

        if (i != null) {
            if (i.n1 - 1 >= 0 && i.n2 - 1 >= 0 && this.getTuiles()[i.n1 - 1][i.n2 - 1] != null) list.add(this.getTuiles()[i.n1 - 1][i.n2 - 1]);
            if (i.n1 - 1 >= 0 && i.n2 + 1 <  6 && this.getTuiles()[i.n1 - 1][i.n2 + 1] != null) list.add(this.getTuiles()[i.n1 - 1][i.n2 + 1]);
            if (i.n1 + 1 <  6 && i.n2 - 1 >= 0 && this.getTuiles()[i.n1 + 1][i.n2 - 1] != null) list.add(this.getTuiles()[i.n1 + 1][i.n2 - 1]);
            if (i.n1 + 1 <  6 && i.n2 + 1 <  6 && this.getTuiles()[i.n1 + 1][i.n2 + 1] != null) list.add(this.getTuiles()[i.n1 + 1][i.n2 + 1]);
        }

        return list;
    }

    public Tresor[] getTresors() {
        return tresors;
    }

    public void PrendreTresor(Utils.Tresor t){
        int cpt = 0;
        while(tresors[cpt].getTresor() != t){
            cpt++;
        }
        tresors[cpt] = null;
    }

    public int nbTresors(){
        int cpt = 0;
        for (int i = 0; i < 4; i++) {
            if(tresors[i] != null){
                cpt ++;
            }
        }
        return cpt;
    }
}

class Index {
    int n1, n2;

    Index(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }
}