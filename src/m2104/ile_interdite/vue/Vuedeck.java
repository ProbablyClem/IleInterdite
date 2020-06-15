package m2104.ile_interdite.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Vuedeck extends JPanel {
    private IHM vueIHM;
    private JLabel nomdeck;
    private JButton retour;
    private JPanel mainpanel;
    private JPanel panelhaut;
    private JPanel cardisplay;

    public Vuedeck(IHM ihm){
        this.vueIHM=ihm;
        retour= new JButton("RETOUR");
        retour.setBackground(Color.RED);
        panelhaut.add(nomdeck);
        mainpanel.setLayout(new BorderLayout());

    }
}
