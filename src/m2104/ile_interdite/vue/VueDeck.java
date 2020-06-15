package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.IleInterdite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class VueDeck extends JPanel {
    private IHM vueIHM;
    private JLabel nomdeck;
    private JButton retour;
    private JPanel mainpanel;
    private JPanel panelhaut;
    private JPanel cardisplay;

    public IleInterdite getIledeck() {
        return iledeck;
    }

    private IleInterdite iledeck;




    public VueDeck(IHM ihm){
        this.vueIHM=ihm;
        retour= new JButton("RETOUR");
        retour.setBackground(Color.RED);
        panelhaut.add(nomdeck);
        mainpanel.setLayout(new BorderLayout());
        mainpanel.add(panelhaut,BorderLayout.NORTH);
    }

    public VueDeck(IHM vueIHM, JLabel nomdeck, JButton retour, JPanel mainpanel, JPanel panelhaut, JPanel cardisplay, IleInterdite iledeck) {
        this.vueIHM = vueIHM;
        this.nomdeck = nomdeck;
        this.retour = retour;
        this.mainpanel = mainpanel;
        this.panelhaut = panelhaut;
        this.cardisplay = cardisplay;
        this.iledeck = iledeck;
    }

    public abstract void decksize(JPanel cardisplay);
    public abstract void deckname(JLabel nomdeck);

    public void setNomdeck(JLabel nomdeck) {
        this.nomdeck = nomdeck;
    }
}
