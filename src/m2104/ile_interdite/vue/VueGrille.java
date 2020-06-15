package m2104.ile_interdite.vue;

import javax.swing.*;
import java.awt.*;

public class VueGrille {

    private final IHM ihm;
    private final JPanel mainPanel;

    private final JPanel grille;

    VueGrille(IHM ihm) {
        this.ihm = ihm;

        this.mainPanel = new JPanel();

        this.grille = new JPanel(new GridLayout(6, 6));
    }



}
