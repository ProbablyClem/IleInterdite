package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.vue.ImagePanel;

import java.util.ArrayList;

public class CarteInondation extends Carte {

    private Tuile tuile;
    private ImagePanel image;

    public CarteInondation(String nom, ImagePanel image, Tuile tuile) {
        super(nom);
        this.tuile = tuile;
        this.image = image;
    }


    public static ArrayList<CarteInondation> getAllCartesInondation(Grille grille) {
        ArrayList<CarteInondation> cartes = new ArrayList<>();

        for (Tuile[] T: grille.getTuiles()) {
            for (Tuile t: T) {
                //deux boucles for sont requises ici pour parcourir les 2 dimensions de l'array
                if (t != null) {
                    cartes.add(new CarteInondation("inondation" + t.getNom(), new ImagePanel("src/images/cartes/" + t.getImageString()+".png"), t));
                }
            }
        }

        return cartes;
    }

    public Utils.EtatTuile utiliser() {
        if (this.tuile.getEtat().equals(Utils.EtatTuile.ASSECHEE)) {
            this.tuile.setEtat(Utils.EtatTuile.INONDEE);
        } else {
            this.tuile.setEtat(Utils.EtatTuile.COULEE);
            if(this.tuile.getAventuriers().size() > 0){
            }
        }
        return this.tuile.getEtat();
    }

    @Override
    public ImagePanel getImage() {
        return image;
    }

    public Tuile getTuile() {
        return tuile;
    }
}
