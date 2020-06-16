package m2104.ile_interdite.vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExempleFrame extends TitleFrame {

    ExempleFrame() {
        super("Titre de la fenetre");
        this.setSize(500, 300);

        main.setLayout(new GridLayout(4, 5));

        for (int i = 0; i < 20; i++) {
            Button btn = new Button("Quitter", 0, 0, Color.RED, Color.WHITE);
            main.add(btn);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }

        this.setVisible(true);
        this.centrer();
    }

    public static void main(String[] args) {
        new ExempleFrame();
    }

}
