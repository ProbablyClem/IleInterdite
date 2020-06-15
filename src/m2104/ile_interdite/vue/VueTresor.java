package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.IleInterdite;

import javax.swing.*;
import java.awt.*;

public class VueTresor extends VueDeck {



    @Override
    public void decksize(JPanel cardisplay) {
        if (getIledeck().getDeckTresor().size()<=4) {
            cardisplay.setLayout(new GridLayout(2,2));
            for(int i = 0;i<getIledeck().getDeckInondation().size();i++ ){
                cardisplay.add(getIledeck().getDeckTresor().get(i).getImage());
            }
        }
        else if (getIledeck().getDeckTresor().size()<=8){
            cardisplay.setLayout(new GridLayout(6,4));
            for(int i = 0;i<getIledeck().getDeckInondation().size();i++ ){
                cardisplay.add(getIledeck().getDeckTresor().get(i).getImage());
            }
        }
    }
    @Override
    public void deckname(JLabel nomdeck) {
        JLabel decktitre= new JLabel("CARTE TRESOR");
        getNomdeck().setText("CARTE TRESOR");
        setNomdeck(decktitre);

    }
}
