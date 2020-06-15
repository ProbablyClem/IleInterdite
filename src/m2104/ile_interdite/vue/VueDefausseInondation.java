package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.IleInterdite;

import javax.swing.*;
import java.awt.*;

public class VueDefausseInondation extends VueDeck {



    @Override
    public void decksize(JPanel cardisplay) {
    if (getIledeck().getDefausseInondation().size()<=4) {
        cardisplay.setLayout(new GridLayout(2,2));
    }
    else if (getIledeck().getDefausseInondation().size()<=8){
        cardisplay.setLayout(new GridLayout(6,4));
    }
    }

    @Override
    public void deckname(JLabel nomdeck) {
        JLabel decktitre= new JLabel("DEFAUSSE INONDATION");
        setNomdeck(decktitre);

    }
}
