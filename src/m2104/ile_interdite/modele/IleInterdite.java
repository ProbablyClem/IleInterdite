package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Utils;
import patterns.observateur.Observable;
import patterns.observateur.Observateur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class IleInterdite extends Observable<Message> {

    private ArrayList<Aventurier> aventuriers;
    private Grille grille;
    private ArrayList<Carte> deckTresor;
    private ArrayList<Carte> defausseTresor;
    private ArrayList<CarteInondation> deckInondation;
    private ArrayList<CarteInondation> defausseInondation;
    private int niveau = 2;

    public IleInterdite(Observateur<Message> observateur, ArrayList<Aventurier> aventuriers, Grille grille) {
        this.addObservateur(observateur);

        this.grille = grille;

        this.aventuriers = aventuriers;

        this.deckTresor = CarteSpeciale.getAllCartesSpeciale();
        this.deckTresor.addAll(CarteTresor.getAllCartes());

        this.defausseTresor = new ArrayList<>();
        this.deckInondation = CarteInondation.getAllCartesInondation(grille);
        this.defausseInondation = new ArrayList<>();


        // Placement des aventuriers sur la grille
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

        Collections.shuffle(deckTresor);

        // Distribution des cartes
        for (Aventurier a: aventuriers) {
            PiocherCartesTresor(a);
        }

        Collections.shuffle(deckTresor); // on évite ici que les cartes montée des eaux se retrouvent toutes à la fin

        Collections.shuffle(deckInondation);

        PiocherCartesInondation(6);

        // Affichage CLI de la grille
        grille.showGrille();

    }

    public ArrayList<Aventurier> getAventuriers() {
        return aventuriers;
    }

    public Grille getGrille() {
        return grille;
    }

    public ArrayList<Carte> getDeckTresor() {
        return deckTresor;
    }

    public ArrayList<Carte> getDefausseTresor() {
        return defausseTresor;
    }

    public ArrayList<CarteInondation> getDeckInondation() {
        return deckInondation;
    }

    public ArrayList<CarteInondation> getDefausseInondation() {
        return defausseInondation;
    }

    public String[] inscrireJoueurs(int nbJoueurs) {
        String[] nomAventuriers = new String[nbJoueurs];

        for (int i = 0; i < nbJoueurs; i++) {
            nomAventuriers[i] = aventuriers.get(i).getRole();
        }
        return nomAventuriers;
    }

    public void  PiocherCartesTresor(Aventurier a){

        for (int i = 0; i < 2; i++) {
            if (deckTresor.size() < 1) {
                Collections.shuffle(defausseTresor);
                deckTresor = defausseTresor;
            }
            if(deckTresor.get(i) instanceof CarteMonteeDesEaux ){
                Utils.afficherInformation("L'eau monte !");
                this.niveau ++;
                Collections.shuffle(defausseInondation);
                for(CarteInondation c : defausseInondation){
                    deckInondation.add(0, c);
                }
                defausseInondation.clear();
                notifierObservateurs(Message.niveau(niveau));
                defausseTresor.add(deckTresor.get(i));
            }
            else{
                a.prendreCarte(deckTresor.get(i));
            }

            deckTresor.remove(deckTresor.get(i));
        }
    }

    public void PiocherCartesInondation(int nbCartes) {
        for (int i = 0; i < nbCartes; i++) {
            if (deckInondation.size() < 1) {
                Collections.shuffle(defausseInondation);
                deckInondation = defausseInondation;
            }


            if (deckInondation.get(0).utiliser() == Utils.EtatTuile.COULEE) {

                if(deckInondation.get(0).getTuile().getNom().equals("L'Héliport")){
                    notifierObservateurs(Message.finPartie("L'Héliport a coulé"));

                }
                try {
                    //les aventuriers present sur la tuile coulé essayent de se refugier sur une tuile adjacente
                    ArrayList<Aventurier> aventuriers = deckInondation.get(0).getTuile().getAventuriers();
                    for (Aventurier a : aventuriers) {
                        ArrayList<Tuile> tuilesDispo = new ArrayList<>();
                        tuilesDispo.addAll(grille.getTuilesAdjacentes(deckInondation.get(0).getTuile()));
                        if (a instanceof Explorateur) {
                            tuilesDispo.addAll(grille.getTuilesDiagonales(deckInondation.get(0).getTuile()));
                        }


                        tuilesDispo.removeIf(t -> t.getEtat() == Utils.EtatTuile.COULEE);
                        //si aucune tuile n'est accessible le joueur meurt
                        if (tuilesDispo.size() < 1) {
                            notifierObservateurs(Message.finPartie("Le joueur " + deckInondation.get(0).getTuile().getAventuriers().get(0).getNom() + "s'est noyé"));
                        } else {
                            //on choisis une des tuiles disponible aleatoirement
                            int randomNum = ThreadLocalRandom.current().nextInt(0, tuilesDispo.size());
                            a.setEmplacement(tuilesDispo.get(randomNum));
                        }

                    }
                    deckInondation.get(0).getTuile().getAventuriers().clear();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

                if (deckInondation.get(0).getTuile().getTresor() != null) {
                    boolean carteExiste = false;
                    for (Tuile t : grille.getListTuiles()) {
                        if (t.getTresor() != null && t.getEtat() != Utils.EtatTuile.COULEE) {
                            if (t != deckInondation.get(0).getTuile() && t.getTresor() == deckInondation.get(0).getTuile().getTresor()) {
                                carteExiste = true;
                                break;
                            }
                        }
                    }
                    if (!carteExiste) {
                        notifierObservateurs(Message.finPartie("Le tresor " + deckInondation.get(0).getTuile().getTresor() + " a coulé !"));
                    }
                }
            }

            defausseInondation.add(deckInondation.remove(0));
        }
    }

    public int getNiveau(){
        return niveau;
    }

    public void PrendreTresor(Utils.Tresor t){
        grille.PrendreTresor(t);
    }
}
