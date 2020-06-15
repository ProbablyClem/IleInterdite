package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

import java.util.ArrayList;

public class CarteInondation extends Carte {

    private Tuile tuile;
    
    private String ChmnAccès;

    public CarteInondation() {
        this.ChmnAccès = "\\ileInterditeSprites\\tuilesVerso";
    }

    CarteInondation (Tuile tuile) {
        this.tuile = tuile;
    }

    @Override
    public String getNom() {
        return ("Carte Inondation de " + this.tuile.getNom());
    }

    
    @Override
    public void utiliser() {
        if (this.tuile.getEtat() == Utils.EtatTuile.ASSECHEE) {
            this.tuile.setEtat(Utils.EtatTuile.INONDEE);
        } else {
            System.out.println("La tuile ne peut pas être inondée");
        }
    }

    public static ArrayList<CarteInondation> getAllCartesInondation(Grille grille) {
        ArrayList<CarteInondation> cartes = new ArrayList<>();

        for (Tuile[] T: grille.getTuiles()) {
            for (Tuile t: T) {
                //deux boucles for sont requises ici pour parcourir les 2 dimensions de l'array
                if (t != null) {
                    cartes.add(new CarteInondation(t));
                }
            }
        }

        return cartes;
    }
    @Override
    public String getImage(){
        return ChmnAccès;
        
}
}
