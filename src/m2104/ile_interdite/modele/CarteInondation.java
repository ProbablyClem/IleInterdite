package m2104.ile_interdite.modele;

import m2104.ile_interdite.vue.ImageFrame;

import java.util.ArrayList;

public class CarteInondation extends Carte {

    private Tuile tuile;
    private ImageFrame image;


    public CarteInondation(String nom, ImageFrame image) {
        super(nom);
        this.image = image;
    }

    public CarteInondation(String nom, ImageFrame image, Tuile tuile) {
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
                    cartes.add(new CarteInondation("inondation" + t.getNom(), t.getImage(), t));
                }
            }
        }

        return cartes;
    }

    @Override
    public ImageFrame getImage() {
        return image;
    }
}
