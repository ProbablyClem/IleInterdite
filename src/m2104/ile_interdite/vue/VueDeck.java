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
    private IleInterdite iledeck;

    public IleInterdite getIledeck() {
        return iledeck;
    }

    public VueDeck() {
        nomdeck= new JLabel("FOSSE");
        retour= new JButton("RETOUR");
        retour.setBackground(Color.RED);
        panelhaut= new JPanel();
        mainpanel= new JPanel();
        cardisplay=new JPanel();
        retour=new JButton();
        panelhaut.add(retour);
        panelhaut.add(nomdeck);
        panelhaut.add(retour);
        this.setLayout(new BorderLayout());
        this.add(panelhaut,BorderLayout.NORTH);
        this.add(cardisplay);
        this.add(mainpanel);
    }

    public JLabel getNomdeck() {
        return nomdeck;
    }

    public static void main(String[] args) {
         VueCartesInondation vueDeck= new VueCartesInondation();
         JFrame w = new JFrame();
         w.add(vueDeck);
         w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         w.setTitle("Vueflex");
         w.setVisible(true);
    }
    public abstract void decksize(JPanel cardisplay);
    public abstract void deckname(JLabel nomdeck);

    public void setNomdeck(JLabel nomdeck) {
        this.nomdeck = nomdeck;
    }
}
